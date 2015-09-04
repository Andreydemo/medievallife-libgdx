package com.demosoft.game.medievallife.player;

import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.cotroller.CameraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        player.changeGridPositin(new Vector3(0.0f, -1.0f, 0));
    }

    public void moveUpRight() {
        if (!moovedUpRight) {
            player.getActiveTextures().setAnimationTextures(player.getUpRightRunTextures());
            resetFlags();
            moovedUpRight = true;
        }
        player.changeGridPositin(new Vector3(1.0f, 0.0f, 0));
    }

    public void moveDownLeft() {
        if (!moovedDownLeft) {
            player.getActiveTextures().setAnimationTextures(player.getDownLeftRunTextures());
            resetFlags();
            moovedDownLeft = true;
        }
        player.changeGridPositin(new Vector3(-1.0f, 0.0f, 0));
    }

    public void moveDownRight() {
        if (!moovedDownRight) {
            player.getActiveTextures().setAnimationTextures(player.getDownRightRunTextures());
            resetFlags();
            moovedDownRight = true;
        }
        player.changeGridPositin(new Vector3(0.0f, 1.0f, 0));
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

    public void jump() {
        if (!player.isInJump()) {
            Thread jumpThread = new Thread(new JumpEvent(player));
            jumpThread.setDaemon(true);
            jumpThread.start();
        }
    }

    private class JumpEvent implements Runnable {

        Player player;

        public JumpEvent(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            player.setInJump(true);
            try {
                for (int i = 0; i < 100; i++) {
                    player.changeGridPositin(new Vector3(0, 0, 0.125f));
                    Thread.sleep(5);
                }
                for (int i = 0; i < 100; i++) {
                    player.changeGridPositin(new Vector3(0, 0, -0.125f));
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                player.setInJump(false);
            }
        }
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
            if (moovedUpLeft)
                player.getActiveTextures().setAnimationTextures(player.getUpLeftStayTextures());
            if (moovedUpRight)
                player.getActiveTextures().setAnimationTextures(player.getUpRightStayTextures());
            if (moovedDownLeft)
                player.getActiveTextures().setAnimationTextures(player.getDownLeftStayTextures());
            if (moovedDownRight)
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
        moovedUpRight = false;
        moovedDownLeft = false;
        moovedDownRight = false;
    }
}
