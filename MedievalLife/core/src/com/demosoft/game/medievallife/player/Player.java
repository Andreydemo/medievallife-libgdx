package com.demosoft.game.medievallife.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.IsometricCamera;

@Component
public class Player {

    public static final int TEXTURES_COUNT = 4;
    
    public static final int RUN_TEXTURES_COUNT = 8;
    public static final int STAY_TEXTURES_COUNT = 4;
    
    public static final String UP_TEXTURES = "player-up-";
    public static final String DOWN_TEXTURES = "player-down-";
    public static final String LEFT_TEXTURES = "player-left-";
    public static final String RIGHT_TEXTURES = "player-right-";
    
    public static final String LEFT_RUN_TEXTURES = "hero-run-left-";
    public static final String LEFT_SATY_TEXTURES = "hero-stay-left-";
    public static final String UP_RUN_TEXTURES = "hero-run-up-";
    public static final String UP_SATY_TEXTURES = "hero-stay-up-";

    public static final int PLAYER_HEIGHT = 100;
    public static final int PLAYER_HEIGHT2 = 50;
    public static final int PLAYER_WIDTH = 80;
    public static final int PLAYER_WIDTH2 = 40;

    private List<TextureRegion> upTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    private List<TextureRegion> downTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    private List<TextureRegion> leftTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    
    private List<TextureRegion> leftRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> leftStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);
    private List<TextureRegion> upRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> upStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);
    
    private List<TextureRegion> rightTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    private AnimationHolder activeTextures;

    private boolean inited = false;

    private Vector3 gridPositon = new Vector3(-5, 90, 0);
    private Vector3 worldPositon;

    public Player() {
	Vector3 worldPositon = new Vector3(gridPositon);
	IsometricCamera.gridToWorld(worldPositon);
	this.worldPositon = worldPositon;
	this.worldPositon.add(-PLAYER_WIDTH2, -PLAYER_HEIGHT, 0);
    }

    public void changeGridPositin(Vector3 diff) {
	gridPositon.add(diff);
	Vector3 worldPositon = new Vector3(gridPositon);
	IsometricCamera.gridToWorld(worldPositon);
	this.worldPositon = worldPositon;
	this.worldPositon.add(-PLAYER_WIDTH2, -PLAYER_HEIGHT, 0);
    }

    public void addDownTextures(TextureRegion region) {
	downTextures.add(region);
    }

    public void addUpTextures(TextureRegion region) {
	upTextures.add(region);
    }

    public void addLeftTextures(TextureRegion region) {
	leftTextures.add(region);
    }

    public void addRightTextures(TextureRegion region) {
	rightTextures.add(region);
    }

    public void processAtlass(TextureAtlas atlas,TextureAtlas atlas1) {
	for (int i = 0; i < TEXTURES_COUNT; i++) {
	    TextureRegion buf = atlas.findRegion(DOWN_TEXTURES + i);
	    buf.flip(false, true);
	    addDownTextures(buf);

	    buf = atlas.findRegion(UP_TEXTURES + i);
	    buf.flip(false, true);
	    addUpTextures(buf);

	    buf = atlas.findRegion(LEFT_TEXTURES + i);
	    buf.flip(false, true);
	    addLeftTextures(buf);

	    buf = atlas.findRegion(RIGHT_TEXTURES + i);
	    buf.flip(false, true);
	    addRightTextures(buf);
	}
	
	for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
	    TextureRegion buf = atlas1.findRegion(LEFT_SATY_TEXTURES + i);
	    buf.flip(false, true);
	    this.leftStayTextures.add(buf);
	}
	for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
	    TextureRegion buf = atlas1.findRegion(LEFT_RUN_TEXTURES + i);
	    buf.flip(false, true);
	    this.leftRunTextures.add(buf);
	}
	for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
	    TextureRegion buf = atlas1.findRegion(UP_SATY_TEXTURES + i);
	    buf.flip(false, true);
	    this.upStayTextures.add(buf);
	}
	for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
	    TextureRegion buf = atlas1.findRegion(UP_RUN_TEXTURES + i);
	    buf.flip(false, true);
	    this.upRunTextures.add(buf);
	}
	activeTextures = new AnimationHolder(leftStayTextures);
	inited = true;
    }

    public TextureRegion[] getDownTexturesArray() {
	TextureRegion[] returnValue = new TextureRegion[downTextures.size()];
	downTextures.toArray(returnValue);
	return returnValue;
    }

    public boolean isInited() {
	return inited;
    }

    public void setInited(boolean inited) {
	this.inited = inited;
    }

    public Vector3 getGridPositon() {
	return gridPositon;
    }

    public void setGridPositon(Vector3 gridPositon) {
	this.gridPositon = gridPositon;
    }

    public Vector3 getWorldPositon() {
	return worldPositon;
    }

    public void setWorldPositon(Vector3 worldPositon) {
	this.worldPositon = worldPositon;
    }

    public List<TextureRegion> getUpTextures() {
	return upTextures;
    }

    public void setUpTextures(List<TextureRegion> upTextures) {
	this.upTextures = upTextures;
    }

    public List<TextureRegion> getDownTextures() {
	return downTextures;
    }

    public void setDownTextures(List<TextureRegion> downTextures) {
	this.downTextures = downTextures;
    }

    public List<TextureRegion> getLeftTextures() {
	return leftTextures;
    }

    public void setLeftTextures(List<TextureRegion> leftTextures) {
	this.leftTextures = leftTextures;
    }

    public List<TextureRegion> getRightTextures() {
	return rightTextures;
    }

    public void setRightTextures(List<TextureRegion> rightTextures) {
	this.rightTextures = rightTextures;
    }

    public AnimationHolder getActiveTextures() {
        return activeTextures;
    }

    public void setActiveTextures(AnimationHolder activeTextures) {
        this.activeTextures = activeTextures;
    }

	public List<TextureRegion> getLeftRunTextures() {
		return leftRunTextures;
	}

	public void setLeftRunTextures(List<TextureRegion> leftRunTextures) {
		this.leftRunTextures = leftRunTextures;
	}

	public List<TextureRegion> getLeftStayTextures() {
		return leftStayTextures;
	}

	public void setLeftStayTextures(List<TextureRegion> leftStayTextures) {
		this.leftStayTextures = leftStayTextures;
	}

	public static int getStayTexturesCount() {
		return STAY_TEXTURES_COUNT;
	}

	public List<TextureRegion> getUpRunTextures() {
		return upRunTextures;
	}

	public void setUpRunTextures(List<TextureRegion> upRunTextures) {
		this.upRunTextures = upRunTextures;
	}

	public List<TextureRegion> getUpStayTextures() {
		return upStayTextures;
	}

	public void setUpStayTextures(List<TextureRegion> upStayTextures) {
		this.upStayTextures = upStayTextures;
	}

}
