package com.demosoft.game.medievallife;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii Korkoshko on 14.01.2016.
 */
@Component
public class WorldContext {
    private World world;
    private BodyDef chainBodyDef;

    public WorldContext() {
        world = new World(new Vector2(0, 0), false);
        chainBodyDef = new BodyDef();
        chainBodyDef.type = BodyDef.BodyType.StaticBody;
    }

    public Body getNewBody() {
        return world.createBody(chainBodyDef);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
