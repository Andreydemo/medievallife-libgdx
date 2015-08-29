package com.demosoft.game.medievallife.core.render;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.AbstractGameObject;
import com.demosoft.game.medievallife.core.Chunk;
import com.demosoft.game.medievallife.core.RenderListener;
import com.demosoft.game.medievallife.core.log.Logger;

@Component
public class BackgroundRenderListener extends RenderListener {
	@Autowired
	private ContextConteiner context;

	@Autowired
	ChunkCulculator chunkCulculator;
	
	@Autowired
	Logger logger;

	private ArrayList<Chunk> activeChunks = new ArrayList<Chunk>();
	TextureRegion backGround;
	TextureRegion groundOfBackGround;
	Chunk mainActiveChunk;

	@PostConstruct
	public void init() {
		backGround = context.getSpritesLoader().getSprite(ContextConteiner.BACKGROUND_SPRITE_ID);//
		groundOfBackGround = context.getSpritesLoader().getSprite("b44-0-1");
		mainActiveChunk = chunkCulculator.calculateMainChunk(context.getCamera());
		System.out.println("chunk- CENTRE ->> " + mainActiveChunk.getCentre());
		System.out.println("chunk- getFirstPointIn ->> " + mainActiveChunk.getFirstPointIn());
		activeChunks.add(mainActiveChunk);
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
					if(i == 0 && j==0){
					   // chunk.getFirstPointIn().x++; 
					/*System.out.println();
					System.out.println("draw on: x: " +  chunk.getFirstPointIn().x + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2);
					System.err.println();*/
					}
					context.getFont().draw(context.getBatch(), j + " " + i,
							chunk.getFirstPointIn().x + AbstractGameObject.SCREEN_WIDTH2 + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2,
							chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2);
				}
			}
		}
	}

}
