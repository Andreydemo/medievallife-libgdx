package com.demosoft.game.medievallife.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demosoft.game.medievallife.SpritesLoader;

@Component
public class GameObjectFactory {
	
	public static final String DIRT_BCLOCK_TYPE = "dirt";
	
	@Autowired
	private SpritesLoader spritesLoader;
	
	public AbstractGameObject getDirtBlock(){
		Block block = new Block();
		block.setLeft(spritesLoader.getSprite("b1-0-0"));
		block.setUp(spritesLoader.getSprite("b1-0-1"));
		block.setRight(spritesLoader.getSprite("b1-0-2"));
		block.setTypeName(DIRT_BCLOCK_TYPE);
		return block;
	}
	
	
	public AbstractGameObject getObject(String id){
		switch (id) {
		case DIRT_BCLOCK_TYPE:
			return getDirtBlock();
		default:
			break;
		}
		return null;
	}

}
