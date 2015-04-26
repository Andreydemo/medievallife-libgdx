package com.demosoft.game.medievallife;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.demosoft.game.medievallife.core.AbstractGameObject;

@Component
public class BackgroundRenderListener extends RenderListener {
	@Autowired
	private ContextConteiner context;

	@Autowired
	ChunkCulculator chunkCulculator;

	private ArrayList<Chunk> activeChunks = new ArrayList<Chunk>();
	TextureRegion backGround;
	TextureRegion groundOfBackGround;
	Chunk mainActiveChunk;

	@PostConstruct
	public void init() {
		backGround = context.getSpritesLoader().getSprite(ContextConteiner.BACKGROUND_SPRITE_ID);//
		groundOfBackGround = context.getSpritesLoader().getSprite("b44-0-1");
		mainActiveChunk = chunkCulculator.calculateFirstChunk(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		activeChunks.add(mainActiveChunk);
	}

	@Override
	public void render() {
		chunkCulculator.processChunks();
		for (Chunk chunk : activeChunks) {

			for (int j = 0; j < chunk.getHeight(); j++) {
				for (int i = 0; i < chunk.getWidth(); i++) {
					context.getBatch().draw(backGround, chunk.getFirstPointIn().x + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2,
							chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2 - AbstractGameObject.SCREEN_HEIGHT2);
					context.getFont().draw(context.getBatch(), j + " " + i,
							chunk.getFirstPointIn().x + AbstractGameObject.SCREEN_WIDTH2 + i * AbstractGameObject.SCREEN_WIDTH2 + j * AbstractGameObject.SCREEN_WIDTH2,
							chunk.getFirstPointIn().y - i * AbstractGameObject.SCREEN_HEIGHT2 + j * AbstractGameObject.SCREEN_HEIGHT2);
				}
			}
		}
	}

}
