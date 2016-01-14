package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.demosoft.game.medievallife.WorldContext;

public class Block extends AbstractGameObject {
    private Vector3 gridPositon;
    private Vector3 worldPositon;
    private TextureRegion up;
    private TextureRegion right;
    private TextureRegion left;
    private Body leftBody;
    private Body rightBody;
    private Fixture leftFixture;
    private Fixture rightFixture;
    private WorldContext worldLink;

    private ChainShape leftChainShape;
    private ChainShape rightChainShape;

    public Block() {
    }

    public Block(Vector3 gridPositon) {
        this.gridPositon = gridPositon;
        worldPositon = new Vector3(gridPositon);
        IsometricCamera.gridToWorld(worldPositon);
        leftChainShape = new ChainShape();
        leftChainShape.createLoop(new Vector2[]{
                new Vector2(worldPositon.x, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT2),
                new Vector2(worldPositon.x, worldPositon.y + SCREEN_HEIGHT2),
                new Vector2(worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT),

                new Vector2(worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT),
                new Vector2(worldPositon.x + SCREEN_WIDTH2 - 1, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT - 1),

                new Vector2(worldPositon.x + SCREEN_WIDTH2 - 1, worldPositon.y + SCREEN_HEIGHT + 1),
                new Vector2(worldPositon.x + 1, worldPositon.y + SCREEN_HEIGHT2 + 1),
                new Vector2(worldPositon.x + 1, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT2 - 1)
        });
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    public void drawUp(SpriteBatch batch) {
        batch.draw(up, worldPositon.x, worldPositon.y);
        batch.draw(left, worldPositon.x, worldPositon.y + SCREEN_HEIGHT2);
        batch.draw(right, worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT2);
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
        worldPositon = new Vector3(gridPositon);
        IsometricCamera.gridToWorld(worldPositon);
        leftChainShape = new ChainShape();
        rightChainShape = new ChainShape();
        leftChainShape.createLoop(new Vector2[]{
                new Vector2(worldPositon.x, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT2),
                new Vector2(worldPositon.x, worldPositon.y + SCREEN_HEIGHT2),
                new Vector2(worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT),

                new Vector2(worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT),
                new Vector2(worldPositon.x + SCREEN_WIDTH2 - 1, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT - 1),

                new Vector2(worldPositon.x + SCREEN_WIDTH2 - 1, worldPositon.y + SCREEN_HEIGHT + 1),
                new Vector2(worldPositon.x + 1, worldPositon.y + SCREEN_HEIGHT2 + 1),
                new Vector2(worldPositon.x + 1, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT2 - 1)
        });

        rightChainShape.createLoop(new Vector2[]{
                new Vector2(worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT),
                new Vector2(worldPositon.x + SCREEN_WIDTH2, worldPositon.y + SCREEN_HEIGHT),
                new Vector2(worldPositon.x + SCREEN_WIDTH, worldPositon.y + SCREEN_HEIGHT2),

                new Vector2(worldPositon.x + SCREEN_WIDTH, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT2),
                new Vector2(worldPositon.x + SCREEN_WIDTH - 1, worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT2 - 1),

                new Vector2(worldPositon.x + SCREEN_WIDTH - 1, worldPositon.y + SCREEN_HEIGHT2 + 1 ),
                new Vector2(worldPositon.x + SCREEN_WIDTH2 + 1 , worldPositon.y + SCREEN_HEIGHT + 1 ),
                new Vector2(worldPositon.x + SCREEN_WIDTH2 + 1 , worldPositon.y + SCREEN_HEIGHT + SCREEN_HEIGHT - 1 ),
        });

        if (leftBody != null && leftFixture != null) {
            leftBody.destroyFixture(leftFixture);
        }
        this.setLeftFixture(leftBody.createFixture(this.getLeftChainShape(), 5));

        if (rightBody != null && rightFixture != null) {
            rightBody.destroyFixture(rightFixture);
        }
        this.setRightFixture(rightBody.createFixture(this.getRightChainShape(), 5));
    }

    public Vector3 getWorldPositon() {
        return worldPositon;
    }

    public void setWorldPositon(Vector3 worldPositon) {
        this.worldPositon = worldPositon;
    }

    public ChainShape getLeftChainShape() {
        return leftChainShape;
    }

    public void setLeftChainShape(ChainShape leftChainShape) {
        this.leftChainShape = leftChainShape;
    }

    public ChainShape getRightChainShape() {
        return rightChainShape;
    }

    public void setRightChainShape(ChainShape rightChainShape) {
        this.rightChainShape = rightChainShape;
    }

    public Fixture getLeftFixture() {
        return leftFixture;
    }

    public void setLeftFixture(Fixture leftFixture) {
        this.leftFixture = leftFixture;
    }

    public Fixture getRightFixture() {
        return rightFixture;
    }

    public void setRightFixture(Fixture rightFixture) {
        this.rightFixture = rightFixture;
    }

    public WorldContext getWorldLink() {
        return worldLink;
    }

    public void setWorldLink(WorldContext worldLink) {
        this.worldLink = worldLink;
    }

    public Body getLeftBody() {
        return leftBody;
    }

    public void setLeftBody(Body leftBody) {
        this.leftBody = leftBody;
    }

    public Body getRightBody() {
        return rightBody;
    }

    public void setRightBody(Body rightBody) {
        this.rightBody = rightBody;
    }
}
