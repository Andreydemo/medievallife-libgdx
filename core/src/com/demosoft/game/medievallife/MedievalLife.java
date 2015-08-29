package com.demosoft.game.medievallife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.AbstarctGame;
import com.demosoft.game.medievallife.spring.SpringTools;
import com.demosoft.game.medievallife.ui.LoadingScreen;

public class MedievalLife extends AbstarctGame {

	private ContextConteiner context;

	@Override
	public void create() {// b2-0-1
		SpringTools.initContext();
		context = SpringTools.getBean(ContextConteiner.class);
		context.setGame(this);
		SpringTools.postInit();
		setScreen(SpringTools.getBean(LoadingScreen.class));

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
		System.out.println("game resize");
		Gdx.graphics.setDisplayMode(width, height, Gdx.graphics.isFullscreen());
		this.create();
		context.getViewport().update(width, height);
		context.getCamera().update();
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
