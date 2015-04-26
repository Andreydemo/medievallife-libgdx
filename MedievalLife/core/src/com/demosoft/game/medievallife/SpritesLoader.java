package com.demosoft.game.medievallife;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@Component
public class SpritesLoader {

	TextureAtlas atlas;
	@PostConstruct
	public void init(){
		atlas = new TextureAtlas(Gdx.files.internal("Spritesheet.txt"));
	}
	
	public TextureRegion getSprite(String id){
		return atlas.findRegion(id);
	}
}
