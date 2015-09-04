package com.demosoft.game.medievallife.stub;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.GlobalConfigs;
import com.demosoft.game.medievallife.core.MapObject;
import com.demosoft.game.medievallife.core.log.Logger;
import com.demosoft.game.medievallife.map.MapManager;
import com.demosoft.game.medievallife.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 04.09.2015.
 */
@Component
public class MapLoaderTask implements Runnable {
    @Autowired
    private GlobalConfigs configs;
    @Autowired
    private ContextConteiner context;
    @Autowired
    private StubMapProvider mapProvider;
    @Autowired
    private MapManager mapManager;
    @Autowired
    Player player;
    @Autowired
    Logger logger;
    public static Long threadId;

    private Vector3 loadedForPosition;

    private Date lastReloadingDate;

    @PostConstruct
    public void init() {
        System.out.println("MapLoaderTask threadId" + threadId);
        if(threadId != null){
            for (Thread t : Thread.getAllStackTraces().keySet()){
                if (t.getId()==threadId || "MapLoaderTask".equalsIgnoreCase(t.getName())){
                    t.interrupt();
                    System.out.println("Interapted" + threadId);
                }
            }
        }
        Thread currentThread = new Thread(this);
        currentThread.setDaemon(true);
        currentThread.setName("MapLoaderTask");
        currentThread.start();
        threadId = currentThread.getId();
    }

    @Override
    public void run() {
        System.out.println("MapLoaderTask start");
        while (!Thread.currentThread().isInterrupted()) {
            if (mapManager.getMap() == null) {
                mapManager.setMap(load(player.getGridPositon()));
            }
            // logger.logInfo("differences x: " + Math.abs(loadedForPosition.x - player.getGridPositon().x) + " y: " + Math.abs(loadedForPosition.y - player.getGridPositon().y) + " limit: " + configs.getInMemoryMapBufSize() * 7.5);
            if(loadedForPosition == null) {
                mapManager.setMap(load(player.getGridPositon()));
                logger.logInfo("initial: " + loadedForPosition);
            }
            if (Math.abs(loadedForPosition.x - player.getGridPositon().x) > configs.getInMemoryMapBufSize() * 7.5 || Math.abs(loadedForPosition.y - player.getGridPositon().y) > configs.getInMemoryMapBufSize()/2 * 7.5) {
                mapManager.setMap(load(player.getGridPositon()));
                lastReloadingDate = new Date();
                logger.logInfo("Loaded new par of map for player pos: " + loadedForPosition +  " from " + Thread.currentThread().getId());
            }
        }
        System.out.println("MapLoaderTask end");
    }

    public List<MapObject> load(Vector3 playerGridPosition) {
        Vector2 startPoint = new Vector2((-1 * configs.getInMemoryMapBufSize() / 2) * 10, (-1 * configs.getInMemoryMapBufSize() / 2) * 10);
        startPoint.x+=playerGridPosition.x;
        startPoint.y+=playerGridPosition.y;
        startPoint.x -=  startPoint.x % 10;
        startPoint.y -=  startPoint.y % 10;
        Vector2 vBug = new Vector2(startPoint);
        List<MapObject> result = new LinkedList<>();
        for (int i = 0; i < configs.getInMemoryMapBufSize(); i++) {
            vBug.x = startPoint.x;
            for (int j = 0; j < configs.getInMemoryMapBufSize(); j++) {
                MapObject buf =  mapProvider.getMapHash().get(vBug);
                if(buf != null) {
                    result.add(buf);
                }
                vBug.x = vBug.x + 10;
            }
            vBug.y = vBug.y + 10;

        }
        loadedForPosition = new Vector3(playerGridPosition);
        return result;
    }
}
