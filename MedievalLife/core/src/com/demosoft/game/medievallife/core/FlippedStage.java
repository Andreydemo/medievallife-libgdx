package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * 
 * @author Andrii_Korkoshko
 *
 */
public class FlippedStage extends Stage {

    private boolean flliped = false;

    public FlippedStage(Viewport viewport) {
	super(viewport);
    }

    public FlippedStage(Viewport viewport, boolean flliped) {
	super(viewport);
	this.flliped = flliped;
    }

    public FlippedStage() {
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	if (flliped) {
	    return super.touchUp(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
	} else {
	    return super.touchUp(screenX, screenY, pointer, button);
	}
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	if (flliped) {
	    return super.touchDown(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
	} else {
	    return super.touchDown(screenX, screenY, pointer, button);
	}
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
	if (flliped) {
	    return super.touchDragged(screenX, Gdx.graphics.getHeight() - screenY, pointer);
	} else {
	    return super.touchDragged(screenX, screenY, pointer);
	}
    }

    public boolean isFlliped() {
	return flliped;
    }

    public void setFlliped(boolean flliped) {
	this.flliped = flliped;
    }

}
