package com.demosoft.game.medievallife.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.demosoft.game.medievallife.CommonUtils;
import com.demosoft.game.medievallife.MedievalLifeScene;
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

    public static final String UP_LEFT_RUN_TEXTURES = "hero-run-up-left-";
    public static final String UP_LEFT_SATY_TEXTURES = "hero-stay-up-left-";

    public static final String UP_RIGHT_RUN_TEXTURES = "hero-run-up-right-";
    public static final String UP_RIGHT_SATY_TEXTURES = "hero-stay-up-right-";

    public static final String UP_RUN_TEXTURES = "hero-run-up-";
    public static final String UP_SATY_TEXTURES = "hero-stay-up-";

    public static final String RIGHT_RUN_TEXTURES = "hero-run-right-";
    public static final String RIGHT_SATY_TEXTURES = "hero-stay-right-";

    public static final String DOWN_RUN_TEXTURES = "hero-run-down-";
    public static final String DOWN_SATY_TEXTURES = "hero-stay-down-";

    public static final String DOWN_LEFT_RUN_TEXTURES = "hero-run-down-left-";
    public static final String DOWN_LEFT_SATY_TEXTURES = "hero-stay-down-left-";

    public static final String DOWN_RIGHT_RUN_TEXTURES = "hero-run-down-right-";
    public static final String DOWN_RIGHT_SATY_TEXTURES = "hero-stay-down-right-";

    public static final int PLAYER_HEIGHT = 192;
    public static final int PLAYER_HEIGHT2 = 96;
    public static final int PLAYER_WIDTH = 192;
    public static final int PLAYER_WIDTH2 = 96;

    private List<TextureRegion> upTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    private List<TextureRegion> downTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    private List<TextureRegion> leftTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);

    private List<TextureRegion> leftRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> leftStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> upLeftRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> upLeftStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> upRightRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> upRightStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> upRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> upStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> rightRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> rightStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> downRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> downStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> downLeftRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> downLeftStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> downRightRunTextures = new ArrayList<TextureRegion>(RUN_TEXTURES_COUNT);
    private List<TextureRegion> downRightStayTextures = new ArrayList<TextureRegion>(STAY_TEXTURES_COUNT);

    private List<TextureRegion> rightTextures = new ArrayList<TextureRegion>(TEXTURES_COUNT);
    private AnimationHolder activeTextures;

    private boolean inJump;

    private boolean inited = false;

    private Vector3 gridPositon = new Vector3(-5, 90, 0);
    private Vector3 worldPositon;

    private PointLight viewArea;

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
        if (viewArea != null) {
            viewArea.setPosition(CommonUtils.convert(worldPositon));
        }
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

    public void processAtlass(TextureAtlas atlas, TextureAtlas atlas1) {
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

        for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(RIGHT_SATY_TEXTURES + i);
            buf.flip(false, true);
            this.rightStayTextures.add(buf);
        }
        for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(RIGHT_RUN_TEXTURES + i);
            buf.flip(false, true);
            this.rightRunTextures.add(buf);
        }
        for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(DOWN_SATY_TEXTURES + i);
            buf.flip(false, true);
            this.downStayTextures.add(buf);
        }
        for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(DOWN_RUN_TEXTURES + i);
            buf.flip(false, true);
            this.downRunTextures.add(buf);
        }
        for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(UP_LEFT_SATY_TEXTURES + i);
            buf.flip(false, true);
            this.upLeftStayTextures.add(buf);
        }
        for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(UP_LEFT_RUN_TEXTURES + i);
            buf.flip(false, true);
            this.upLeftRunTextures.add(buf);
        }
        for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(UP_RIGHT_SATY_TEXTURES + i);
            buf.flip(false, true);
            this.upRightStayTextures.add(buf);
        }
        for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(UP_RIGHT_RUN_TEXTURES + i);
            buf.flip(false, true);
            this.upRightRunTextures.add(buf);
        }

        for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(DOWN_LEFT_SATY_TEXTURES + i);
            buf.flip(false, true);
            this.downLeftStayTextures.add(buf);
        }
        for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(DOWN_LEFT_RUN_TEXTURES + i);
            buf.flip(false, true);
            this.downLeftRunTextures.add(buf);
        }
        for (int i = 0; i < STAY_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(DOWN_RIGHT_SATY_TEXTURES + i);
            buf.flip(false, true);
            this.downRightStayTextures.add(buf);
        }
        for (int i = 0; i < RUN_TEXTURES_COUNT; i++) {
            TextureRegion buf = atlas1.findRegion(DOWN_RIGHT_RUN_TEXTURES + i);
            buf.flip(false, true);
            this.downRightRunTextures.add(buf);
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

    public List<TextureRegion> getRightStayTextures() {
        return rightStayTextures;
    }

    public void setRightStayTextures(List<TextureRegion> rightStayTextures) {
        this.rightStayTextures = rightStayTextures;
    }

    public List<TextureRegion> getRightRunTextures() {
        return rightRunTextures;
    }

    public void setRightRunTextures(List<TextureRegion> rightRunTextures) {
        this.rightRunTextures = rightRunTextures;
    }

    public List<TextureRegion> getDownRunTextures() {
        return downRunTextures;
    }

    public void setDownRunTextures(List<TextureRegion> downRunTextures) {
        this.downRunTextures = downRunTextures;
    }

    public List<TextureRegion> getDownStayTextures() {
        return downStayTextures;
    }

    public void setDownStayTextures(List<TextureRegion> downStayTextures) {
        this.downStayTextures = downStayTextures;
    }

    public List<TextureRegion> getUpLeftStayTextures() {
        return upLeftStayTextures;
    }

    public void setUpLeftStayTextures(List<TextureRegion> upLeftStayTextures) {
        this.upLeftStayTextures = upLeftStayTextures;
    }

    public List<TextureRegion> getUpLeftRunTextures() {
        return upLeftRunTextures;
    }

    public void setUpLeftRunTextures(List<TextureRegion> upLeftRunTextures) {
        this.upLeftRunTextures = upLeftRunTextures;
    }

    public List<TextureRegion> getUpRightRunTextures() {
        return upRightRunTextures;
    }

    public void setUpRightRunTextures(List<TextureRegion> upRightRunTextures) {
        this.upRightRunTextures = upRightRunTextures;
    }

    public List<TextureRegion> getUpRightStayTextures() {
        return upRightStayTextures;
    }

    public void setUpRightStayTextures(List<TextureRegion> upRightStayTextures) {
        this.upRightStayTextures = upRightStayTextures;
    }

    public List<TextureRegion> getDownLeftRunTextures() {
        return downLeftRunTextures;
    }

    public void setDownLeftRunTextures(List<TextureRegion> downLeftRunTextures) {
        this.downLeftRunTextures = downLeftRunTextures;
    }

    public List<TextureRegion> getDownLeftStayTextures() {
        return downLeftStayTextures;
    }

    public void setDownLeftStayTextures(List<TextureRegion> downLeftStayTextures) {
        this.downLeftStayTextures = downLeftStayTextures;
    }

    public List<TextureRegion> getDownRightRunTextures() {
        return downRightRunTextures;
    }

    public void setDownRightRunTextures(List<TextureRegion> downRightRunTextures) {
        this.downRightRunTextures = downRightRunTextures;
    }

    public List<TextureRegion> getDownRightStayTextures() {
        return downRightStayTextures;
    }

    public void setDownRightStayTextures(List<TextureRegion> downRightStayTextures) {
        this.downRightStayTextures = downRightStayTextures;
    }

    public boolean isInJump() {
        return inJump;
    }

    public void setInJump(boolean inJump) {
        this.inJump = inJump;
    }

    public PointLight getViewArea() {
        return viewArea;
    }

    public void setViewArea(PointLight viewArea) {
        this.viewArea = viewArea;
    }
}
