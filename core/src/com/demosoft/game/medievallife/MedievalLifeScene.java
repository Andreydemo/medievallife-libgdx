package com.demosoft.game.medievallife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.BaseScene;
import com.demosoft.game.medievallife.core.IsometricCamera;

@Component
public class MedievalLifeScene extends BaseScene {

	@Autowired
	private ContextConteiner context;

	boolean firstShow = true;

	@Override
	public void show() {
		super.show();
		if (firstShow) {
			context.getPlayer().processAtlass(context.getSpritesLoader().manager.get("player.pack", TextureAtlas.class),
					context.getSpritesLoader().manager.get("isometric_hero/hero.pack", TextureAtlas.class));

			firstShow = false;
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(getRgbColorValue(135), getRgbColorValue(206), getRgbColorValue(250), 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		context.getCamera().update();
		context.getBatch().setProjectionMatrix(context.getCamera().combined);
		context.getBatch().begin();
		context.getInputProcessor().processKeyPressed();
		context.getRenderingManager().render();
		drawScene();
		drawFps();
		drawZoom();
		context.getBatch().end();
		super.render(delta);
	}

	public float getRgbColorValue(int value) {
		return value / 255.0f;
	}

	public void drawFps() {
		context.getFont().draw(context.getBatch(), "fps: " + Gdx.graphics.getFramesPerSecond(), translateX(20), translateY(30));
	}

	public void drawZoom() {
		context.getFont().draw(context.getBatch(), "zoom: " + context.getCameraManager().getCamera().zoom, translateX(20), translateY(50));
		Camera cam = context.getCameraManager().getCamera();
		context.getFont().draw(context.getBatch(), "camera props: viewportHeight" + cam.viewportHeight + " viewportWidth: " + cam.viewportWidth + " pos:" + cam.position,
				translateX(20), translateY(70));
	}

	public void drawScene() {
		if (Gdx.input.isTouched()) {

			context.getFont().draw(context.getBatch(), "x: " + Gdx.input.getX() + " y: " + Gdx.input.getY(), translateX(Gdx.input.getX()), translateY(Gdx.input.getY()));
			context.getFont().draw(context.getBatch(), "unproject x: " + translateX(Gdx.input.getX()) + " y: " + translateY(Gdx.input.getY()), translateX(Gdx.input.getX()),
					translateY(Gdx.input.getY()) + 20);
			Vector3 onGrid = new Vector3( translateX(Gdx.input.getX()) , translateY(Gdx.input.getY()),0);
			IsometricCamera.worldToGrid(onGrid );
			context.getFont().draw(context.getBatch(), "on grid x: " + onGrid.x + " y: " + onGrid.y, 
					translateX(Gdx.input.getX()),translateY(Gdx.input.getY()) + 40);

		}

	}

	public float translateY(int y) {
		if (!context.isFlipped()) {
			return Gdx.graphics.getHeight() - y;
		}
		Vector3 v = new Vector3(0, y, 0);
		return context.getCameraManager().getCamera().unproject(v).y;
	}

	public float translateX(int x) {
		Vector3 v = new Vector3(x, 0, 0);
		return context.getCameraManager().getCamera().unproject(v).x;
	}

}
