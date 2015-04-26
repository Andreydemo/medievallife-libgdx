package com.demosoft.game.medievallife;

import com.badlogic.gdx.math.Vector2;
import com.demosoft.game.medievallife.core.AbstractGameObject;

public class Chunk {

	private int x;
	private int y;
	private int height;
	private int width;
	private int diagonalLenght;
	private Vector2 firstPointIn;
	private Vector2 centre;
	private Vector2 sizeInWorld; 

	public Chunk(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.sizeInWorld = new Vector2(AbstractGameObject.SCREEN_WIDTH * width, AbstractGameObject.SCREEN_HEIGHT * height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public int getDiagonalLenght() {
		return diagonalLenght;
	}

	public void setDiagonalLenght(int diagonalLenght) {
		this.diagonalLenght = diagonalLenght;
	}

	@Override
	public String toString() {
		return "Chunk [x=" + x + ", y=" + y + ", height=" + height + ", width=" + width + ", diagonalLenght=" + diagonalLenght + ", firstPointIn=" + firstPointIn
				+ "]";
	}

	public Vector2 getFirstPointIn() {
		return firstPointIn;
	}

	public void setFirstPointIn(Vector2 firstPointInTheWorld) {
		this.firstPointIn = firstPointInTheWorld;
	}

	public Vector2 getCentre() {
		return centre;
	}

	public void setCentre(Vector2 centre) {
		this.centre = centre;
	}

	public Vector2 getSizeInWorld() {
		return sizeInWorld;
	}

	public void setSizeInWorld(Vector2 sizeInWorld) {
		this.sizeInWorld = sizeInWorld;
	}

}
