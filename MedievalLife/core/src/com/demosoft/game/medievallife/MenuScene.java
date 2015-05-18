package com.demosoft.game.medievallife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.demosoft.game.medievallife.core.FlippedStage;
import com.demosoft.game.medievallife.spring.SpringTools;

public class MenuScene extends BaseScene {
	private FlippedStage stage;
	private Image screenBg;
	private Skin skin;
	private Image title;
	//private Label helpTip;
	private Table table;
	private Table options;
	private Table exit;
	private CheckBox muteCheckBox;
	private Slider volumeSlider;
	private TextButton backButton;
	private TextButton playButton;
	private TextButton optionsButton;
	private TextButton exitButton;
	private TextButton lbButton;
	private TextButton achButton;
	private TextButton yesButton;
	private TextButton noButton;
	private boolean menuShown;
	private boolean exitShown;

	private ContextConteiner context;

	public void moveBackgroundToCentre(Image bg) {
		int sWidth = Gdx.graphics.getWidth();
		int sHeight = Gdx.graphics.getHeight();
		if (sWidth == bg.getWidth() && sHeight == bg.getHeight()) {
			return;
		}
		Vector2 diff = new Vector2(sWidth / 2 - bg.getWidth() / 2, sHeight / 2 - bg.getHeight() / 2);
		bg.setX(diff.x);
		bg.setY(diff.y);
		System.out.println(screenBg.getX() + " " + screenBg.getY() + "; " + screenBg.getWidth() + " " + screenBg.getHeight());
	}

	public MenuScene(final ContextConteiner context) {
		this.context = context;

		if (context.getGame().isAndroid()) {
			context.getHandler().setTrackerScreenName("com.csharks.thrustcopter.MenuScene");
			context.getHandler().showAds(true);
		}

		stage = new FlippedStage(context.getViewport());
		stage.setFlliped(true);
		if( Gdx.input.getInputProcessor() instanceof InputMultiplexer){
			((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		}
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		screenBg = new Image(context.getSpritesLoader().manager.get("menu_background.jpg", Texture.class));
		moveBackgroundToCentre(screenBg);
		System.out.println(screenBg.getX() + " " + screenBg.getY() + "; " + screenBg.getWidth() + " " + screenBg.getHeight());
		
		title = new Image(context.getSpritesLoader().manager.get("logo.png", Texture.class));
		//helpTip = new Label("Tap around the plane to move it!", skin);
	//	helpTip.setColor(Color.NAVY);

		table = new Table();
		playButton = new TextButton("PLAY GAME", skin); 
		table.add(playButton).padBottom(10);
		table.row();
		optionsButton = new TextButton("SOUND OPTIONS", skin);
		table.add(optionsButton).padBottom(10);
		table.row();
		lbButton = new TextButton("LEADERBOARD", skin);
		table.add(lbButton).padBottom(10);
		table.row();
		achButton = new TextButton("ACHIEVEMENTS", skin);
		table.add(achButton).padBottom(10);
		table.row();
		exitButton = new TextButton("EXIT GAME", skin);
		table.add(exitButton);
		table.setPosition(Gdx.graphics.getWidth()/2 - table.getWidth()/2, -200);

		options = new Table();
		Label soundTitle = new Label("SOUND OPTIONS", skin);
		soundTitle.setColor(Color.NAVY);
		options.add(soundTitle).padBottom(25).colspan(2);
		options.row();
		muteCheckBox = new CheckBox(" MUTE ALL", skin);
		options.add(muteCheckBox).padBottom(10).colspan(2);
		options.row();
		options.add(new Label("VOLUME ", skin)).padBottom(10).padRight(10);
		volumeSlider = new Slider(0, 2, 0.2f, false, skin);
		options.add(volumeSlider).padTop(10).padBottom(20);
		options.row();
		backButton = new TextButton("BACK", skin);
		options.add(backButton).colspan(2).padTop(20);
		options.setPosition(400, -200);
		muteCheckBox.setChecked(!context.isSoundEnabled());
		volumeSlider.setValue(context.getSoundVolume());

		exit = new Table();
		Label exitTitle = new Label("Confirm Exit", skin);
		exitTitle.setColor(Color.NAVY);
		exit.add(exitTitle).padBottom(25).colspan(2);
		exit.row();
		yesButton = new TextButton("YES", skin);
		exit.add(yesButton).padBottom(20);
		noButton = new TextButton("NO", skin);
		exit.add(noButton).padBottom(20);
		exit.setPosition(400, -200);

		stage.addActor(screenBg);
		stage.addActor(title);
		//stage.addActor(helpTip);
		stage.addActor(table);
		stage.addActor(options);
		stage.addActor(exit);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				context.getGame().setScreen(SpringTools.getBean(MedievalLifeScene.class));
			}
		});
		optionsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showMenu(false);
			}
		});
		lbButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (context.getGame().isAndroid()) {
					context.getHandler().showScores();
				} else {
					// game.setScreen(new LeaderboardScene(game));
				}

			}
		});
		achButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (context.getGame().isAndroid()) {
					context.getHandler().showAchievements();
				}
			}
		});
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
				// or System.exit(0);
			}
		});
		yesButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
				// or System.exit(0);
			}
		});
		noButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showExit(!exitShown);
			}
		});
		volumeSlider.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				context.setSoundVolume(volumeSlider.getValue());
			}
		});
		muteCheckBox.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				context.setSoundEnabled(!muteCheckBox.isChecked());
			}
		});
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// game.saveAll();
				showMenu(true);
			}
		});
	}

	@Override
	public void show() {
		title.setPosition(Gdx.graphics.getWidth()/2 -   title.getWidth() / 2, 0);
		//helpTip.setPosition(400 - helpTip.getWidth() / 2, 30);

		MoveToAction actionMove = Actions.action(MoveToAction.class);
		actionMove.setPosition(Gdx.graphics.getWidth()/2 -   title.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/10 - title.getHeight());
		actionMove.setDuration(2);
		actionMove.setInterpolation(Interpolation.elasticOut);
		title.addAction(actionMove);

		showMenu(true);
	}

	@Override
	public void hide() {
		stage.clear();
	}

	private void showMenu(boolean flag) {
		MoveToAction actionMove1 = Actions.action(MoveToAction.class);// out
		actionMove1.setPosition(Gdx.graphics.getWidth()/2 - options.getWidth()/2, -200);
		actionMove1.setDuration(1);
		actionMove1.setInterpolation(Interpolation.swingIn);

		MoveToAction actionMove2 = Actions.action(MoveToAction.class);// in
		actionMove2.setPosition(Gdx.graphics.getWidth()/2 - table.getWidth()/2, 190);
		actionMove2.setDuration(1.5f);
		actionMove2.setInterpolation(Interpolation.swing);

		if (flag) {
			table.addAction(actionMove2);
			options.addAction(actionMove1);
		} else {
			options.addAction(actionMove2);
			table.addAction(actionMove1);
		}
		menuShown = flag;
		exitShown = false;
	}

	private void showExit(boolean flag) {
		MoveToAction actionMove1 = Actions.action(MoveToAction.class);// out
		actionMove1.setPosition(400, -200);
		actionMove1.setDuration(1);
		actionMove1.setInterpolation(Interpolation.swingIn);

		MoveToAction actionMove2 = Actions.action(MoveToAction.class);// in
		actionMove2.setPosition(400, 190);
		actionMove2.setDuration(1.5f);
		actionMove2.setInterpolation(Interpolation.swing);

		if (flag) {
			exit.addAction(actionMove2);
			table.addAction(actionMove1);
		} else {
			table.addAction(actionMove2);
			exit.addAction(actionMove1);
		}
		exitShown = flag;
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		context.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// Show the loading screen
		stage.act();
		stage.draw();

		// Table.drawDebug(stage);
		super.render(delta);
		context.getCamera().setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	protected void handleBackPress() {
		if (!menuShown) {
			showMenu(!menuShown);
		} else {
			showExit(!exitShown);
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		System.out.println("mune resize");
		Gdx.graphics.setDisplayMode(width, height, Gdx.graphics.isFullscreen());
		moveBackgroundToCentre(screenBg);
		context.getViewport().update(width, height);
		context.getCamera().update();
	}

}
