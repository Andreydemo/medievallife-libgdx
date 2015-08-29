package com.demosoft.game.medievallife.core;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public abstract class AbstractGameObject {
	/**
	 * Screen depth of a block/object sprite in pixels. This is the length from
	 * the top to the middle border of the block.
	 */
	public static final int SCREEN_DEPTH = 80;
	/** The half (1/2) of SCREEN_DEPTH. The short form of: SCREEN_DEPTH/2 */
	public static final int SCREEN_DEPTH2 = SCREEN_DEPTH / 2;
	/** A quarter (1/4) of SCREEN_DEPTH. The short form of: SCREEN_DEPTH/4 */
	public static final int SCREEN_DEPTH4 = SCREEN_DEPTH / 4;

	/**
	 * The width (x-axis) of the sprite size
	 */
	public static final int SCREEN_WIDTH = 160;
	/** The half (1/2) of SCREEN_WIDTH. The short form of: SCREEN_WIDTH/2 */
	public static final int SCREEN_WIDTH2 = SCREEN_WIDTH / 2;
	/** A quarter (1/4) of SCREEN_WIDTH. The short form of: SCREEN_WIDTH/4 */
	public static final int SCREEN_WIDTH4 = SCREEN_WIDTH / 4;

	/**
	 * The height (y-axis) of the sprite size
	 */
	public static final int SCREEN_HEIGHT = 80;
	/** The half (1/2) of SCREEN_HEIGHT. The short form of: SCREEN_WIDTH/2 */
	public static final int SCREEN_HEIGHT2 = SCREEN_HEIGHT / 2;
	/** A quarter (1/4) of SCREEN_HEIGHT. The short form of: SCREEN_WIDTH/4 */
	public static final int SCREEN_HEIGHT4 = SCREEN_HEIGHT / 4;

	/**
	 * The game spaces dimension in pixel (edge length). 1 game meter ^= 1
	 * GAME_EDGELENGTH The value is calculated by SCREEN_HEIGHT*sqrt(2) because
	 * of the axis shortening.
	 */
	public static final int GAME_EDGELENGTH = (int) (SCREEN_HEIGHT * Math.sqrt(2));

	/**
	 * Half (1/2) of GAME_EDGELENGTH
	 */
	public static final int GAME_EDGELENGTH2 = GAME_EDGELENGTH / 2;
	/**
	 * The game space dimension size's aequivalent to SCREEN_DEPTH or
	 * SCREEN_WIDTH. Because the x axis is not shortened those two are equal.
	 */
	public static final int GAME_DIAGLENGTH = SCREEN_WIDTH;

	/**
	 * Half (1/2) of GAME_DIAGLENGTH
	 */
	public static final int GAME_DIAGLENGTH2 = SCREEN_WIDTH2;
	
	private String typeName;

	private Vector3 position;

	private ArrayList<TextureRegion> sprites = new ArrayList<>();

	public AbstractGameObject() {
	}
	
	abstract public void render(SpriteBatch batch);

	public TextureRegion get(int index) {
		return sprites.get(index);
	}

	public boolean add(TextureRegion e) {
		return sprites.add(e);
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public ArrayList<TextureRegion> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<TextureRegion> sprites) {
		this.sprites = sprites;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
