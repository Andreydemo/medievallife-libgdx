package com.demosoft.game.medievallife;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class RenderListener {

	@Autowired
	private RenderingManager renderingManager;

	@PostConstruct
	void register() {
		renderingManager.addRenderListener(this);
	}

	abstract public void render();

}
