package com.demosoft.game.medievallife.stub;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.IsometricCamera;
import com.demosoft.game.medievallife.core.MapObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii_Korkoshko on 03.09.2015.
 */
@Component
public class StubMapProvider {

    private int size = 500;
    private List<MapObject> map = new ArrayList<>(size * size);
    private Map<Vector2, MapObject> mapHash = new HashMap<>(size * size);

    @PostConstruct
    public void init() {
        long time = System.currentTimeMillis();
        generate();
        System.out.println("stub db initilization time: " + (System.currentTimeMillis() - time) + " ms");
        System.out.println("" + map.size() + " " + mapHash.size());
    }

    public List<MapObject> generate() {
        Vector2 startPoint = new Vector2((-1 * size / 2) * 10, (-1 * size / 2) * 10);
        Vector2 vBug = new Vector2(startPoint);
        MapObject buf = null;
        for (int i = 0; i < size; i++) {
            vBug.x = startPoint.x;
            for (int j = 0; j < size; j++) {
                buf = new MapObject();
                buf.setGridPosition(new Vector2(vBug));
                Vector3 topLeft = IsometricCamera.getGridToWorld(new Vector3(vBug, 0));
                buf.setTopLeftPoint(new Vector2(topLeft.x, topLeft.y));
                buf.setTopRightPoint(new Vector2(topLeft.x + MapObject.WIDTH, topLeft.y));
                buf.setBottomRightPoint(new Vector2(topLeft.x + MapObject.WIDTH, topLeft.y + MapObject.HEIGHT));
                buf.setBottomLeftPoint(new Vector2(topLeft.x, topLeft.y + MapObject.HEIGHT));
                map.add(buf);
                mapHash.put(buf.getGridPosition(), buf);
                vBug.x = vBug.x + 10;
            }
            vBug.y = vBug.y + 10;
        }
      /*  System.out.printf(map.toString());*/
        return map;
    }

    public static void main(String[] args) {
        new StubMapProvider().generate();
    }

    public List<MapObject> getMap() {
        return map;
    }

    public void setMap(List<MapObject> map) {
        this.map = map;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Vector2, MapObject> getMapHash() {
        return mapHash;
    }

    public void setMapHash(Map<Vector2, MapObject> mapHash) {
        this.mapHash = mapHash;
    }
}
