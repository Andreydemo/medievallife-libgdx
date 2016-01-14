package com.demosoft.game.medievallife.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.RenderListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            if (context.getPlayer().getActiveTextures().isTexuresChanged()) {
                walkDownAnimation = new Animation(0.12f, context.getPlayer().getActiveTextures().getTexturesArray());
                context.getPlayer().getActiveTextures().setTexuresChanged(false);
            }
            stateTime += Gdx.graphics.getDeltaTime(); // #15
            currentFrame = walkDownAnimation.getKeyFrame(stateTime, true); // #
            context.getBatch().draw(currentFrame, context.getPlayer().getWorldPositon().x - 96,
                    context.getPlayer().getWorldPositon().y - 96);
            context.getFont().draw(context.getBatch(), "o", context.getPlayer().getWorldPositon().x, context.getPlayer().getWorldPositon().y);
        }

    }

    public float translateY(float y) {
        if (!context.isFlipped()) {
            return Gdx.graphics.getHeight() - y;
        }
        Vector3 v = new Vector3(0, y, 0);
        return context.getCameraManager().getCamera().unproject(v).y;
    }

    public float translateX(float x) {
        Vector3 v = new Vector3(x, 0, 0);
        return context.getCameraManager().getCamera().unproject(v).x;
    }

}
