package com.demosoft.game.medievallife;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@Component
public class SpritesLoader {

	private TextureAtlas atlas;
	public TextureAtlas testAtlas;
	public AssetManager manager = new AssetManager();

	@PostConstruct
	public void init() {
		atlas = new TextureAtlas(Gdx.files.internal("Spritesheet.txt"));
		testAtlas =new TextureAtlas(Gdx.files.internal("thrustcopterassets.txt"));
		//testAtlas = manager.get("thrustcopterassets.txt", TextureAtlas.class);
	}

	public TextureRegion getSprite(String id) {
		return atlas.findRegion(id);
	}
}
