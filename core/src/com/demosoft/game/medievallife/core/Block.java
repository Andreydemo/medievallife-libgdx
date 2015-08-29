package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block extends AbstractGameObject {
	private TextureRegion up;
	private TextureRegion right;
	private TextureRegion left;
	

	@Override
	public void render(SpriteBatch batch) {
			
		
	}
	private  void drawUp(SpriteBatch batch){
		int y = 0;
		batch.draw(up, 0, 0);
	}


	public TextureRegion getUp() {
		return up;
	}


	public void setUp(TextureRegion up) {
		this.up = up;
	}


	public TextureRegion getRight() {
		return right;
	}


	public void setRight(TextureRegion right) {
		this.right = right;
	}


	public TextureRegion getLeft() {
		return left;
	}


	public void setLeft(TextureRegion left) {
		this.left = left;
	}

}
