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
        block.setLeft(spritesLoader.getSprite("b2-0-2"));
        block.setUp(spritesLoader.getSprite("b2-0-1"));
        block.setRight(spritesLoader.getSprite("b2-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getStoneBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b7-0-2"));
        block.setUp(spritesLoader.getSprite("b7-0-1"));
        block.setRight(spritesLoader.getSprite("b7-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getStoneBlockPrepared() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b6-0-2"));
        block.setUp(spritesLoader.getSprite("b6-0-1"));
        block.setRight(spritesLoader.getSprite("b6-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getGravelBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b5-0-2"));
        block.setUp(spritesLoader.getSprite("b5-0-1"));
        block.setRight(spritesLoader.getSprite("b5-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getWhiteBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b44-1-2"));
        block.setUp(spritesLoader.getSprite("b44-1-1"));
        block.setRight(spritesLoader.getSprite("b44-1-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }
    public Block getGrassBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b1-0-2",true));
        block.setUp(spritesLoader.getSprite("b1-0-1"));
        block.setRight(spritesLoader.getSprite("b1-0-0",true));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }
    public Block getBrickBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b20-0-2"));
        block.setUp(spritesLoader.getSprite("b20-0-1"));
        block.setRight(spritesLoader.getSprite("b20-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getWaterBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b9-0-2"));
        block.setUp(spritesLoader.getSprite("b9-0-1"));
        block.setRight(spritesLoader.getSprite("b9-0-0"));
        block.setTypeName(DIRT_BCLOCK_TYPE);
        return block;
    }

    public Block getSandBlock() {
        Block block = new Block();
        block.setLeft(spritesLoader.getSprite("b8-0-2"));
        block.setUp(spritesLoader.getSprite("b8-0-1"));
        block.setRight(spritesLoader.getSprite("b8-0-0"));
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
