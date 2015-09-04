package com.demosoft.game.medievallife.core.render;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.*;
import com.demosoft.game.medievallife.core.log.Logger;
import com.demosoft.game.medievallife.map.MapManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class BackgroundRenderListener extends RenderListener {
    @Autowired
    private ContextConteiner context;

    @Autowired
    ChunkCulculator chunkCulculator;

    @Autowired
    GameObjectFactory objectFactory;

    @Autowired
    Logger logger;

    @Autowired
    MapManager mapManager;

    private ArrayList<Chunk> activeChunks = new ArrayList<Chunk>();
    TextureRegion backGround;
    TextureRegion backGround0;
    TextureRegion backGround1;
    TextureRegion groundOfBackGround;
    Block testBlock;
    Block testBlock2;
    Block dirtBlock;
    Block stonePreparedBlock;
    Block GravelBlock;
    Block WhiteBlock;
    Block grassBlock;
    Block brickBlock;
    Block sandBlock;
    Chunk mainActiveChunk;
    List<MapObject> map = new ArrayList<>();

    @PostConstruct
    public void init() {
        backGround = context.getSpritesLoader().getSprite(ContextConteiner.BACKGROUND_SPRITE_ID);//
        backGround0 = context.getSpritesLoader().getSprite(ContextConteiner.BACKGROUND_SPRITE_ID_0);
        backGround1 = context.getSpritesLoader().getSprite(ContextConteiner.BACKGROUND_SPRITE_ID_1);
        groundOfBackGround = context.getSpritesLoader().getSprite("b44-0-1");
        mainActiveChunk = chunkCulculator.calculateMainChunk(context.getCamera());
        System.out.println("chunk- CENTRE ->> " + mainActiveChunk.getCentre());
        System.out.println("chunk- getFirstPointIn ->> " + mainActiveChunk.getFirstPointIn());
        activeChunks.add(mainActiveChunk);
        testBlock = objectFactory.getStoneBlock();
        testBlock2 = objectFactory.getWaterBlock();
        sandBlock = objectFactory.getSandBlock();
        dirtBlock = objectFactory.getDirtBlock();
        stonePreparedBlock = objectFactory.getStoneBlockPrepared();
        GravelBlock = objectFactory.getGravelBlock();
        WhiteBlock = objectFactory.getWhiteBlock();
        grassBlock = objectFactory.getGrassBlock();
        brickBlock = objectFactory.getBrickBlock();
        testBlock.setGridPositon(new Vector3(10, 0, 10));
        testBlock2.setGridPositon(new Vector3(30, 0, 10));
        sandBlock.setGridPositon(new Vector3(50, 0, 10));
        dirtBlock.setGridPositon(new Vector3(70, 0, 10));
        stonePreparedBlock.setGridPositon(new Vector3(90, 0, 10));
        GravelBlock.setGridPositon(new Vector3(110, 0, 10));
        WhiteBlock.setGridPositon(new Vector3(130, 0, 10));
        grassBlock.setGridPositon(new Vector3(150, 0, 10));
        brickBlock.setGridPositon(new Vector3(170, 0, 10));
    }

    @Override
    public void render() {
        chunkCulculator.processChunks();
        for (MapObject obj : mapManager.getMap()) {
            if (chunkCulculator.cunkInScreen(obj)) {
                context.getBatch().draw(backGround, obj.getTopLeftPoint().x,
                        obj.getTopLeftPoint().y);
            }
        }
        brickBlock.drawUp(context.getBatch());
        grassBlock.drawUp(context.getBatch());
        WhiteBlock.drawUp(context.getBatch());
        GravelBlock.drawUp(context.getBatch());
        stonePreparedBlock.drawUp(context.getBatch());
        dirtBlock.drawUp(context.getBatch());
        sandBlock.drawUp(context.getBatch());
        testBlock2.drawUp(context.getBatch());
        testBlock.drawUp(context.getBatch());
    }

}
