package com.demosoft.game.medievallife.player;

import com.demosoft.game.medievallife.cotroller.CameraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.math.Vector3;

@Component
public class PlayerManager {

    @Autowired
    private Player player;

    @Autowired
    private CameraManager cameraManager;

    private boolean moovedLeft = false;
    private boolean moovedUpLeft = false;
    private boolean moovedUpRight = false;
    private boolean moovedRight = false;
    private boolean moovedUp = false;
    private boolean moovedDown = false;
    private boolean moovedDownLeft = false;
    private boolean moovedDownRight = false;

    public void moveLeft() {
        if (!moovedLeft) {
            player.getActiveTextures().setAnimationTextures(player.getLeftRunTextures());
            resetFlags();
            moovedLeft = true;
        }
        player.changeGridPositin(new Vector3(-0.5f, -0.5f, 0));
    }
    public void moveUpLeft() {
        if (!moovedUpLeft) {
            player.getActiveTextures().setAnimationTextures(player.getUpLeftRunTextures());
            resetFlags();
            moovedUpLeft = true;
        }
        player.changeGridPositin(new Vector3(0.375f, -1.125f, 0));
    }

    public void moveUpRight() {
        if (!moovedUpRight) {
            player.getActiveTextures().setAnimationTextures(player.getUpRightRunTextures());
            resetFlags();
            moovedUpRight = true;
        }
        player.changeGridPositin(new Vector3(1.125f, -0.375f, 0));
    }

    public void moveDownLeft() {
        if (!moovedDownLeft) {
            player.getActiveTextures().setAnimationTextures(player.getDownLeftRunTextures());
            resetFlags();
            moovedDownLeft = true;
        }
        player.changeGridPositin(new Vector3(-1.125f, 0.375f, 0));
    }

    public void moveDownRight() {
        if (!moovedDownRight) {
            player.getActiveTextures().setAnimationTextures(player.getDownRightRunTextures());
            resetFlags();
            moovedDownRight = true;
        }
        player.changeGridPositin(new Vector3(-0.375f, 1.125f, 0));
    }

    public void moveRight() {
        if (!moovedRight) {
            player.getActiveTextures().setAnimationTextures(player.getRightRunTextures());
            resetFlags();
            moovedRight = true;
        }
        player.changeGridPositin(new Vector3(0.5f, 0.5f, 0));
    }

    public void moveUp() {
        if (!moovedUp) {
            player.getActiveTextures().setAnimationTextures(player.getUpRunTextures());
            resetFlags();
            moovedUp = true;
        }
        player.changeGridPositin(new Vector3(1, -1, 0));
    }

    public void moveDown() {
        if (!moovedDown) {
            player.getActiveTextures().setAnimationTextures(player.getDownRunTextures());
            resetFlags();
            moovedDown = true;
        }
        player.changeGridPositin(new Vector3(-1, 1, 0));
    }

    public void proccedMovement(boolean isMoved) {
        cameraManager.focusOnPlayer(player);
        if (!isMoved) {
            if (moovedLeft)
                player.getActiveTextures().setAnimationTextures(player.getLeftStayTextures());
            if (moovedUp)
                player.getActiveTextures().setAnimationTextures(player.getUpStayTextures());
            if (moovedRight)
                player.getActiveTextures().setAnimationTextures(player.getRightStayTextures());
            if (moovedDown)
                player.getActiveTextures().setAnimationTextures(player.getDownStayTextures());
            if(moovedUpLeft)
                player.getActiveTextures().setAnimationTextures(player.getUpLeftStayTextures());
            if(moovedUpRight)
                player.getActiveTextures().setAnimationTextures(player.getUpRightStayTextures());
            if(moovedDownLeft)
                player.getActiveTextures().setAnimationTextures(player.getDownLeftStayTextures());
            if(moovedDownRight)
                player.getActiveTextures().setAnimationTextures(player.getDownRightStayTextures());
            resetFlags();
        }

    }

    private void resetFlags() {
        moovedLeft = false;
        moovedRight = false;
        moovedUp = false;
        moovedDown = false;
        moovedUpLeft = false;
        moovedUpRight= false;
        moovedDownLeft = false;
        moovedDownRight= false;
    }
}
