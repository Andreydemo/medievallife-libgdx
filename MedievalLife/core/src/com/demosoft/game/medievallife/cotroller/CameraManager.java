package com.demosoft.game.medievallife.cotroller;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.math.Vector2;
import com.demosoft.game.medievallife.core.IsometricCamera;

@Component
public class CameraManager {

	private IsometricCamera camera = new IsometricCamera();

	private Vector2 camermove = new Vector2();
	public static final float maxZoom = 0.5f;
	public static final float minZoom = 1.5f;
	public static final float zoomStep = 1.05f;
	private boolean blocked = false;

	public CameraManager() {
		System.out.println("manager");
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

	public void moveTop() {
		if (camera.position.y > camera.viewportHeight / 2 || !blocked) {
			camera.translate(0, -6);
		}
	}

	public void moveDown() {
		camera.translate(0, 6);
	}

	public void moveLeft() {
		if (camera.position.x > camera.viewportWidth / 2 ||  !blocked) {
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

}
