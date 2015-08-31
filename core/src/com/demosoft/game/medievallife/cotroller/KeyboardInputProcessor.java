package com.demosoft.game.medievallife.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.player.PlayerManager;
import com.demosoft.game.medievallife.spring.SpringTools;
import com.demosoft.game.medievallife.ui.DebugScene;

@Component
public class KeyboardInputProcessor implements InputProcessor {
    int upOffset = 0;
    int leftOffset = 0;
    private boolean isPlayerMoved = false;
    private OrthographicCamera camera;

    @Autowired
    private CameraManager cameraManager;

    @Autowired
    private FlagController flags;

    @Autowired
    private ContextConteiner context;

    @Autowired
    private PlayerManager playerManager;

    public KeyboardInputProcessor() {
    }

    public KeyboardInputProcessor(OrthographicCamera camera) {
        this.camera = camera;
    }

    public static final int NUM_KEYBOARD_KEYS = 155; // Input.Keys in libGDX has 155 keys.

    public static boolean[] keys;
    public static boolean[] pkeys;

    static {
        keys = new boolean[NUM_KEYBOARD_KEYS];
        pkeys = new boolean[NUM_KEYBOARD_KEYS];
    }

    public static void setKey(int i, boolean b) {
        keys[i] = b;
    }

    public static boolean isDown(int i) {
        return keys[i];
    }

    public static boolean isPressed(int i) {
        return keys[i] && !pkeys[i];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYBOARD_KEYS; i++)
            pkeys[i] = keys[i];
    }

    public void processKeyPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cameraManager.moveTop();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cameraManager.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            cameraManager.moveDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            cameraManager.moveRight();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            if (!flags.debugEnabled) {
                context.saveActiveScene();
                context.getGame().setScreen(SpringTools.getBean(DebugScene.class));
                flags.debugEnabled = true;
            }
        }

        isPlayerMoved = false;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerManager.moveUpLeft();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerManager.moveUpRight();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerManager.moveDownLeft();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerManager.moveDownRight();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerManager.moveUp();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerManager.moveDown();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerManager.moveLeft();
            isPlayerMoved = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerManager.moveRight();
            isPlayerMoved = true;
        }
        playerManager.proccedMovement(isPlayerMoved);

    }

    @Override
    public boolean keyDown(int keycode) {
        // setKey(keycode, true);

        switch (keycode) {
            case Input.Keys.PLUS:
                cameraManager.incZoom();
                break;
            case Input.Keys.MINUS:
                cameraManager.decZoom();
                break;
            default:
                // System.out.println("unprocessed keycode:" + keycode);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        //  setKey(keycode, false);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount < 0) {
            cameraManager.incZoom();
        } else {
            cameraManager.decZoom();
        }
        return false;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

}
