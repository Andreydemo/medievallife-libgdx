package com.demosoft.game.medievallife.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.RenderListener;

@Component
public class PlayerRenderListener extends RenderListener {

    private Animation walkDownAnimation; // #3
    private TextureRegion currentFrame; // #7

    float stateTime; // #8

    @Autowired
    private ContextConteiner context;

    public void init() {
	walkDownAnimation = new Animation(0.12f, context.getPlayer().getActiveTextures().getTexturesArray());
	stateTime = 0;
    }

    @Override
    public void render() {
	if (context.getPlayer().isInited()) {
	    if (walkDownAnimation == null) {
		init();
	    }
	    if(context.getPlayer().getActiveTextures().isTexuresChanged()){ 
		walkDownAnimation = new Animation(0.12f, context.getPlayer().getActiveTextures().getTexturesArray());
		context.getPlayer().getActiveTextures().setTexuresChanged(false);
	    }
	    stateTime += Gdx.graphics.getDeltaTime(); // #15
	    currentFrame = walkDownAnimation.getKeyFrame(stateTime, true); // #
	    context.getBatch().draw(currentFrame, context.getPlayer().getWorldPositon().x,
		    context.getPlayer().getWorldPositon().y);
	}

    }

}
