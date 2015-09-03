package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Block extends AbstractGameObject {
    private Vector3 gridPositon;
    private TextureRegion up;
    private TextureRegion right;
    private TextureRegion left;

    public Block() {
    }

    public Block(Vector3 gridPositon) {
        this.gridPositon = gridPositon;
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    public void drawUp(SpriteBatch batch) {
        Vector3 worldPositon = new Vector3(gridPositon);
        IsometricCamera.gridToWorld(worldPositon);
        batch.draw(up, worldPositon.x, worldPositon.y);
        batch.draw(left, worldPositon.x , worldPositon.y + SCREEN_HEIGHT2 );
        batch.draw(right, worldPositon.x + SCREEN_WIDTH2 , worldPositon.y + SCREEN_HEIGHT2 );
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

    public Vector3 getGridPositon() {
        return gridPositon;
    }

    public void setGridPositon(Vector3 gridPositon) {
        this.gridPositon = gridPositon;
    }
}
