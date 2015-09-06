package com.demosoft.game.medievallife;

import java.nio.ByteBuffer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.demosoft.game.medievallife.core.AbstarctGame;
import com.demosoft.game.medievallife.core.IsometricCamera;
import com.demosoft.game.medievallife.core.log.LogLevel;
import com.demosoft.game.medievallife.core.log.Logger;
import com.demosoft.game.medievallife.cotroller.CameraManager;
import com.demosoft.game.medievallife.cotroller.KeyboardInputProcessor;
import com.demosoft.game.medievallife.player.Player;

@Component
public class ContextConteiner {

	private InputMultiplexer inpMulPlex;
	private IsometricCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	public static final String BACKGROUND_SPRITE_ID_0 = "b2-0-0";
	public static final String BACKGROUND_SPRITE_ID_1 = "b2-0-2";
	public static final String BACKGROUND_SPRITE_ID = "b2-0-1";
	private BitmapFont font;
	private AbstarctGame game;
	private IActivityRequestHandler handler;
	private boolean soundEnabled = true;
	private float soundVolume = 0;
	boolean contextActive = false;

	private boolean flipped = true;

	private Screen activeScene;
	private Pixmap activeSceneShot;

	@Autowired
	private KeyboardInputProcessor inputProcessor;
	@Autowired
	private SpritesLoader spritesLoader;
	@Autowired
	private CameraManager cameraManager;
	@Autowired
	private RenderingManager renderingManager;
	@Autowired
	public Logger logger;
	@Autowired
	MedievalLifeScene medievalLifeScene;
	
	@Autowired
	private Player player; 

	public ContextConteiner() {
		batch = new SpriteBatch();

	}

	@PostConstruct
	public void init() {
		font = new BitmapFont(true);
		camera = cameraManager.getCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		inputProcessor.setCamera(camera);
		addInputProcessor(inputProcessor);
		logger.setCurrentLevel(LogLevel.INFO);
		contextActive = true;
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
	
	 private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
	        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

	        if (yDown) {
	            // Flip the pixmap upside down
	            ByteBuffer pixels = pixmap.getPixels();
	            int numBytes = w * h * 4;
	            byte[] lines = new byte[numBytes];
	            int numBytesPerLine = w * 4;
	            for (int i = 0; i < h; i++) {
	                pixels.position((h - i - 1) * numBytesPerLine);
	                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
	            }
	            pixels.clear();
	            pixels.put(lines);
	        }

	        return pixmap;
	    }

	public void saveActiveScene() {
		activeScene = game.getScreen();
		activeSceneShot = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
	}

	public void loadActiveScene() {
		game.setScreen(activeScene);
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

	public KeyboardInputProcessor getInputProcessor() {
		return inputProcessor;
	}

	public void setInputProcessor(KeyboardInputProcessor inputProcessor) {
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

	public AbstarctGame getGame() {
		return game;
	}

	public void setGame(AbstarctGame game) {
		this.game = game;
	}

	public IActivityRequestHandler getHandler() {
		return handler;
	}

	public void setHandler(IActivityRequestHandler handler) {
		this.handler = handler;
	}

	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	public Screen getActiveScene() {
		return activeScene;
	}

	public void setActiveScene(Screen activeScene) {
		this.activeScene = activeScene;
	}

	public Pixmap getActiveSceneShot() {
		return activeSceneShot;
	}

	public void setActiveSceneShot(Pixmap activeSceneShot) {
		this.activeSceneShot = activeSceneShot;
	}

	public Player getPlayer() {
	    return player;
	}

	public void setPlayer(Player player) {
	    this.player = player;
	}

	public boolean isContextActive() {
		return contextActive;
	}
}
