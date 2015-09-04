package com.demosoft.game.medievallife.map;

import com.badlogic.gdx.math.Vector2;
import com.demosoft.game.medievallife.core.MapObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 04.09.2015.
 */
@Component
@Scope("singleton")
public class MapManager {

    private static List<MapObject> map;

    private Vector2 loadedRange1;
    private Vector2 loadedRange2;


    @PostConstruct
    public void init(){
        System.out.println("map manager" + this);
    }

    public List<MapObject> getMap() {
        return map;
    }

    public void setMap(List<MapObject> map) {
        this.map = map;
    }
}
