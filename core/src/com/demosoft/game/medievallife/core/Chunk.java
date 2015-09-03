package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

/**
 * @author Andrii_Korkoshko
 */
public class Chunk {

    private List<MapObject> activeObjcts;

    /**
     * Position in excel map
     */
    private Vector2 startPostion;

    /**
     * count of excels
     */
    private int height;
    /**
     * count of excels
     */
    private int width;
    /**
     * count of excels
     */
    private int diagonalLenght;

    /**
     * start point of rendering int world (px)
     */
    private Vector2 firstPointIn;

    private Vector2 centre;

    private Vector2 sizeInWorld;

    public Chunk(Vector2 startPostion, int height, int width) {
        this.startPostion = startPostion;
        this.height = height;
        this.width = width;
        this.sizeInWorld = new Vector2(AbstractGameObject.SCREEN_WIDTH * width, AbstractGameObject.SCREEN_HEIGHT
                * height);
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
        return "Chunk [startPostion=" + startPostion + ", height=" + height + ", width=" + width + ", diagonalLenght="
                + diagonalLenght + ", firstPointIn=" + firstPointIn + ", centre=" + centre + ", sizeInWorld="
                + sizeInWorld + "]";
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

    public Vector2 getStartPostion() {
        return startPostion;
    }

    public void setStartPostion(Vector2 startPostion) {
        this.startPostion = startPostion;
    }

    public List<MapObject> getActiveObjcts() {
        return activeObjcts;
    }

    public void setActiveObjcts(List<MapObject> activeObjcts) {
        this.activeObjcts = activeObjcts;
    }
}

