package com.demosoft.game.medievallife;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.demosoft.game.medievallife.core.RenderListener;

@Component
public class RenderingManager {

	private ArrayList<RenderListener> renderListeners = new ArrayList<>();

	public void render() {
		for (RenderListener renderListener : renderListeners) {
			renderListener.render();
		}
	}

	public void addRenderListener(RenderListener renderListener) {
		renderListeners.add(renderListener);
	}

}
