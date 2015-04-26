package com.demosoft.game.medievallife;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.demosoft.game.medievallife.core.IsometricCamera;
import com.demosoft.game.medievallife.cotroller.CameraManager;
import com.demosoft.game.medievallife.cotroller.KeboardInputProcessor;

@Component
public class ContextConteiner {

	private InputMultiplexer inpMulPlex;
	private IsometricCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	public static final String BACKGROUND_SPRITE_ID = "b2-0-1";
	private BitmapFont font;
	
	private boolean flipped = true;

	@Autowired
	private KeboardInputProcessor inputProcessor;
	@Autowired
	private SpritesLoader spritesLoader;
	@Autowired
	private CameraManager cameraManager;
	@Autowired
	private RenderingManager renderingManager;

	public ContextConteiner() {
		batch = new SpriteBatch();

	}

	@PostConstruct
	public void init() {
		font  = new BitmapFont(true);
		camera = cameraManager.getCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		inputProcessor.setCamera(camera);
		addInputProcessor(inputProcessor);
	}

	/**
	 * Add an inputProcessor to the views.
	 * 
	 * @param processor
	 */
	public void addInputProcessor(final InputProcessor processor) {
		if (Gdx.input.getInputProcessor() != null) {
			inpMulPlex = new InputMultiplexer(Gdx.input.getInputProcessor());
		} else {
			inpMulPlex = new InputMultiplexer();
		}
		inpMulPlex.addProcessor(processor);
		Gdx.input.setInputProcessor(inpMulPlex);
	}

	public InputMultiplexer getInpMulPlex() {
		return inpMulPlex;
	}

	public void setInpMulPlex(InputMultiplexer inpMulPlex) {
		this.inpMulPlex = inpMulPlex;
	}

	public IsometricCamera getCamera() {
		return camera;
	}

	public void setCamera(IsometricCamera camera) {
		this.camera = camera;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public SpritesLoader getSpritesLoader() {
		return spritesLoader;
	}

	public void setSpritesLoader(SpritesLoader spritesLoader) {
		this.spritesLoader = spritesLoader;
	}


	public KeboardInputProcessor getInputProcessor() {
		return inputProcessor;
	}

	public void setInputProcessor(KeboardInputProcessor inputProcessor) {
		this.inputProcessor = inputProcessor;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

	public RenderingManager getRenderingManager() {
		return renderingManager;
	}

	public void setRenderingManager(RenderingManager renderingManager) {
		this.renderingManager = renderingManager;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

}
