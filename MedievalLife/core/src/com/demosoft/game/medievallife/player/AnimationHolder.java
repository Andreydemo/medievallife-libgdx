package com.demosoft.game.medievallife.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHolder {

    private List<TextureRegion> animationTextures;

    private boolean texuresChanged = false;

    public AnimationHolder() {
	animationTextures = new ArrayList<>();
    }

    public TextureRegion[] getTexturesArray() {
	TextureRegion[] returnValue = new TextureRegion[animationTextures.size()];
	animationTextures.toArray(returnValue);
	return returnValue;
    }

    public AnimationHolder(List<TextureRegion> animationTextires) {
	super();
	this.animationTextures = animationTextires;
    }

    public List<TextureRegion> getAnimationTextures() {
	return animationTextures;
    }

    public void setAnimationTextures(List<TextureRegion> animationTextires) {
	this.animationTextures = animationTextires;
	texuresChanged = true;
    }

    public boolean isTexuresChanged() {
	return texuresChanged;
    }

    public void setTexuresChanged(boolean texuresChanged) {
	this.texuresChanged = texuresChanged;
    }

    public void freezAnimation() {
	animationTextures = Arrays.asList(animationTextures.get(0));
	texuresChanged = true;
    }

}
