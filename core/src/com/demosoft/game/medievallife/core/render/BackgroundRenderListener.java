package com.demosoft.game.medievallife.core.render;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.log.Logger;

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

    private ArrayList<Chunk> activeChunks = new ArrayList<Chunk>();
    TextureRegion backGround;
    TextureRegion backGround0;
    TextureRegion backGround1;
    TextureRegion groundOfBackGround;
    Block testBlock;
    Chunk mainActiveChunk;

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
        testBlock.setGridPositon(new Vector3(0,0,0));
        //activeChunks.addAll(chunkCulculator.generateChunks(mainActiveChunk));
    }

    @Override
    public void render() {
        chunkCulculator.processChunks();
        for (Chunk chunk : activeChunks) {
            for (int j = 0; j < chunk.getHeight(); j++) {
                for (int i = 0; i < chunk.getWidth(); i++) {
                    context.getBatch().draw(backGround, chunk.getFirstPointIn().x + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2,
                            chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2 - AbstractGameObject.SCREEN_HEIGHT2);
                    if (i == 0 && j == 0) {
                        // chunk.getFirstPointIn().x++;
                    /*System.out.println();
					System.out.println("draw on: x: " +  chunk.getFirstPointIn().x + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2);
					System.err.println();*/
                    }
                    context.getFont().draw(context.getBatch(), j + " " + i,
                            chunk.getFirstPointIn().x + AbstractGameObject.SCREEN_WIDTH2 + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2,
                            chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2);
                    if (i == 0) {
                        context.getBatch().draw(backGround1, chunk.getFirstPointIn().x + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2,
                                chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2);
                    }
                    if (j == chunk.getHeight() - 1) {
                        context.getBatch().draw(backGround0, chunk.getFirstPointIn().x + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2 + AbstractGameObject.SCREEN_WIDTH2,
                                chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2);
                    }


                }
            }
        }
        testBlock.drawUp(context.getBatch());
    }

}
