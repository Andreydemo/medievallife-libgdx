package com.demosoft.game.medievallife.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demosoft.game.medievallife.SpritesLoader;

@Component
public class GameObjectFactory {

    public static final String DIRT_BCLOCK_TYPE = "dirt";
    public static final String STONE_BCLOCK_TYPE = "stone";

    @Autowired
    private SpritesLoader spritesLoader;

    public Block getDirtBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b1-0-0"));
        block.setUp(spritesLoader.getSprite("b1-0-1"));
        block.setRight(spritesLoader.getSprite("b1-0-2"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getStoneBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b3-0-2"));
        block.setUp(spritesLoader.getSprite("b3-0-1"));
        block.setRight(spritesLoader.getSprite("b3-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public AbstractGameObject getObject(String id) {
        switch (id) {
            case DIRT_BCLOCK_TYPE:
                return getDirtBlock();
            case STONE_BCLOCK_TYPE:
                return getStoneBlock();
            default:
                break;
        }
        return null;
    }

}
