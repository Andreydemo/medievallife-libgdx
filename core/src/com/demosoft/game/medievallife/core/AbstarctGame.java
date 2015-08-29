package com.demosoft.game.medievallife.core;

import com.badlogic.gdx.Game;

public abstract class AbstarctGame extends Game {

	private boolean isAndroid;

	public boolean isAndroid() {
		return isAndroid;
	}

	public void setAndroid(boolean isAndroid) {
		this.isAndroid = isAndroid;
	}

}
