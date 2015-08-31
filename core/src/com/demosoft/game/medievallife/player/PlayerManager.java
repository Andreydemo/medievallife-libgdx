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
    private boolean moovedRight = false;
    private boolean moovedUp = false;
    private boolean moovedDown = false;

    public void moveLeft() {
        if (!moovedLeft) {
            player.getActiveTextures().setAnimationTextures(player.getLeftRunTextures());
            resetFlags();
            moovedLeft = true;
        }
        player.changeGridPositin(new Vector3(-0.5f, -0.5f, 0));
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
            resetFlags();
        }

    }

    private void resetFlags() {
        moovedLeft = false;
        moovedRight = false;
        moovedUp = false;
        moovedDown = false;
    }
}
