package com.demosoft.game.medievallife.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.BaseScene;
import com.demosoft.game.medievallife.core.FlippedStage;
import com.demosoft.game.medievallife.core.log.Logger;
import com.demosoft.game.medievallife.cotroller.FlagController;
import com.demosoft.game.medievallife.spring.SpringTools;

@Component
public class DebugScene extends BaseScene {
	Image screenBg;
	TextureAtlas atlas;

	FlippedStage stage;
	Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	SelectBox<String> debugLevel = new SelectBox<String>(skin);
	
	public static final Vector2 debugSize = new Vector2(800, 600);

	@Autowired
	private ContextConteiner context;

	@Autowired
	private FlagController flags;

	@Autowired
	private Logger logger;

	@PostConstruct
	private void init() {
		debugLevel.setItems(new String[] {"Item 1", "Item 2", "Item 3"});
		debugLevel.setSelectedIndex(0);
		debugLevel.setBounds(300, 100, 200, 50);
		debugLevel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("->" + event);
				super.clicked(event, x, y);
			}
		});
		atlas = new TextureAtlas(Gdx.files.internal("newUi.pack"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		screenBg.setSize(debugSize.x, debugSize.y);
		stage = new FlippedStage(context.getViewport());
		stage.setFlliped(true);
		if( Gdx.input.getInputProcessor() instanceof InputMultiplexer){
			((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		}
		stage.addActor(screenBg);
		stage.addActor(debugLevel);
		stage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("-> stage " + event);
				super.clicked(event, x, y);
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		context.getActiveScene().render(delta);
		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			if (flags.debugEnabled) {
				context.loadActiveScene();
				flags.debugEnabled = false;
			} else {
				context.saveActiveScene();
				context.getGame().setScreen(SpringTools.getBean(DebugScene.class));
				flags.debugEnabled = true;
			}
		}
		context.getCameraManager().flipCamera(false);
		foolowToCamera(screenBg);
		stage.act();
		stage.draw();
		super.render(delta);
		context.getCameraManager().flipCamera(true);
		logger.logDebug(context.getCamera().position + " " + (float) (screenBg.getX() + screenBg.getWidth() / 2) + ":" + (float) (screenBg.getY() + screenBg.getHeight() / 2));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	public Vector2 getImageCentre(Image image) {
		return new Vector2((float) (image.getX() + image.getWidth() / 2), (float) (image.getY() + image.getHeight() / 2));
	}
	
	public void foolowToCamera(Image image){
		Vector2 imageCentre = getImageCentre(image);
		Vector2 diff = new Vector2(context.getCamera().position.x -imageCentre.x, context.getCamera().position.y -imageCentre.y);
		image.setX(image.getX() +diff.x);
		image.setY(image.getY() +diff.y);
		
	}
	
	

}
