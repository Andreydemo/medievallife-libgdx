package com.demosoft.game.medievallife.cotroller;

import com.demosoft.game.medievallife.player.Player;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.IsometricCamera;

@Component
public class CameraManager {

	private IsometricCamera camera = new IsometricCamera();

	private Vector2 camermove = new Vector2();
	public static final float maxZoom = 0.5f;
	public static final float minZoom = 1.5f;
	public static final float zoomStep = 1.05f;
	private boolean blocked = false;
	private boolean flipped = false;

	public CameraManager() {
	}

	public Vector2 getCamermove() {
		return camermove;
	}

	public void setCamermove(Vector2 camermove) {
		this.camermove = camermove;
	}

	public IsometricCamera getCamera() {
		return camera;
	}

	public void setCamera(IsometricCamera camera) {
		this.camera = camera;
	}

	public void moveRight() {
		camera.translate(6, 0);
	}

	public void flipCamera(boolean flip) {
		Vector3 oldPos = new Vector3(camera.position);
		camera.setToOrtho(flip, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(oldPos);
		flipped = flip;
	}

	public void moveTop() {
		if (camera.position.y > camera.viewportHeight / 2 || !blocked) {
			camera.translate(0, -6);
		}
	}

	public void moveDown() {
		camera.translate(0, 6);
	}

	public void moveLeft() {
		if (camera.position.x > camera.viewportWidth / 2 || !blocked) {
			camera.translate(-6, 0);
		}
	}

	public void incZoom() {
		float zoom = camera.zoom;
		zoom /= zoomStep;
		if (zoom > maxZoom) {
			camera.zoom = zoom;
		}
	}

	public void decZoom() {
		float zoom = camera.zoom;
		zoom *= zoomStep;
		if (zoom < minZoom) {
			camera.zoom = zoom;
		}
	}

	public void focusOnPlayer(Player player){
		camera.position.x = player.getWorldPositon().x;
		camera.position.y = player.getWorldPositon().y;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

}
