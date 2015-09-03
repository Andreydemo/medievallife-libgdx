package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Andrii Korkoshko on 03.09.2015.
 */
public class MapObject {

    private Vector2 gridPosition;

    public static int HEIGHT = 80;
    public static int WIDTH = 160;

    private Vector2 topLeftPoint;
    private Vector2 topRightPoint;
    private Vector2 bottomLeftPoint;
    private Vector2 bottomRightPoint;

    @Override
    public String toString() {
        return "{ |" + gridPosition.toString() + "| " + topLeftPoint + " : " + bottomRightPoint;
    }

    public Vector2 getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(Vector2 gridPosition) {
        this.gridPosition = gridPosition;
    }

    public Vector2 getTopLeftPoint() {
        return topLeftPoint;
    }

    public void setTopLeftPoint(Vector2 topLeftPoint) {
        this.topLeftPoint = topLeftPoint;
    }

    public Vector2 getTopRightPoint() {
        return topRightPoint;
    }

    public void setTopRightPoint(Vector2 topRightPoint) {
        this.topRightPoint = topRightPoint;
    }

    public Vector2 getBottomLeftPoint() {
        return bottomLeftPoint;
    }

    public void setBottomLeftPoint(Vector2 bottomLeftPoint) {
        this.bottomLeftPoint = bottomLeftPoint;
    }

    public Vector2 getBottomRightPoint() {
        return bottomRightPoint;
    }

    public void setBottomRightPoint(Vector2 bottomRightPoint) {
        this.bottomRightPoint = bottomRightPoint;
    }

    public static void main(String[] args) {
        System.out.println(IsometricCamera.getGridToWorld(new Vector3(10, 0, 0)));
    }
}
