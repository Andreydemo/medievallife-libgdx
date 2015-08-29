package com.demosoft.game.medievallife.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.demosoft.game.medievallife.RenderingManager;

public abstract class RenderListener {

	@Autowired
	private RenderingManager renderingManager;

	@PostConstruct
	void register() {
		renderingManager.addRenderListener(this);
	}

	abstract public void render();

}
