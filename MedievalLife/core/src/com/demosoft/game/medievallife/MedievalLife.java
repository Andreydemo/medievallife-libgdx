package com.demosoft.game.medievallife;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.spring.SpringTools;

public class MedievalLife extends ApplicationAdapter {

	private ContextConteiner context;

	@Override
	public void create() {// b2-0-1
		SpringTools.initContext();
		context = SpringTools.getBean(ContextConteiner.class);

	}

	@Override
	public void render() {
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
	}

	public void drawScene() {
		if (Gdx.input.isTouched()) {

			context.getFont().draw(context.getBatch(), "x: " + Gdx.input.getX() + " y: " + Gdx.input.getY(), translateX(Gdx.input.getX()), translateY(Gdx.input.getY()));
			context.getFont().draw(context.getBatch(), "unproject x: " + translateX(Gdx.input.getX()) + " y: " + translateY(Gdx.input.getY()), translateX(Gdx.input.getX()),
					translateY(Gdx.input.getY()) + 20);

		}

	}

	@Override
	public void resize(int width, int height) {
		context.getViewport().update(width, height);
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

}
