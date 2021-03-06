/*
 * Copyright 2013 Benedikt Vogler.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * * Neither the name of Bombing Games nor Benedikt Vogler nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.demosoft.game.medievallife.core.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public abstract class AbstractGameObject {
    /**Screen depth of a block/object sprite in pixels. This is the length from the top to the middle border of the block.
     */
    public static final int SCREEN_DEPTH = 80;
    /**The half (1/2) of SCREEN_DEPTH. The short form of: SCREEN_DEPTH/2*/
    public static final int SCREEN_DEPTH2 = SCREEN_DEPTH / 2;
    /**A quarter (1/4) of SCREEN_DEPTH. The short form of: SCREEN_DEPTH/4*/
    public static final int SCREEN_DEPTH4 = SCREEN_DEPTH / 4;
    
    /**
     * The width (x-axis) of the sprite size
     */
    public static final int SCREEN_WIDTH = 160;
    /**The half (1/2) of SCREEN_WIDTH. The short form of: SCREEN_WIDTH/2*/
    public static final int SCREEN_WIDTH2 = SCREEN_WIDTH / 2;
    /**A quarter (1/4) of SCREEN_WIDTH. The short form of: SCREEN_WIDTH/4*/
    public static final int SCREEN_WIDTH4 = SCREEN_WIDTH / 4;
    
    /**
     * The height (y-axis) of the sprite size
     */
    public static final int SCREEN_HEIGHT = 80;
    /**The half (1/2) of SCREEN_HEIGHT. The short form of: SCREEN_WIDTH/2*/
    public static final int SCREEN_HEIGHT2 = SCREEN_HEIGHT / 2;
    /**A quarter (1/4) of SCREEN_HEIGHT. The short form of: SCREEN_WIDTH/4*/
    public static final int SCREEN_HEIGHT4 = SCREEN_HEIGHT / 4;
    
    /**
     * The game spaces dimension in pixel (edge length). 1 game meter ^= 1 GAME_EDGELENGTH
     * The value is calculated by SCREEN_HEIGHT*sqrt(2) because of the axis shortening.
     */
    public static final int GAME_EDGELENGTH = (int) (SCREEN_HEIGHT * Math.sqrt(2));
    
	    /**
     * Half (1/2) of GAME_EDGELENGTH
     */
    public static final int GAME_EDGELENGTH2 = GAME_EDGELENGTH/2;
    /**
     * The game space dimension size's aequivalent to SCREEN_DEPTH or SCREEN_WIDTH.
     * Because the x axis is not shortened those two are equal.
     */
    public static final int GAME_DIAGLENGTH = SCREEN_WIDTH;
    
    /**Half (1/2) of GAME_DIAGLENGTH
     */
    public static final int GAME_DIAGLENGTH2 = SCREEN_WIDTH2;
    
    /**the max. amount of different object types*/
    public static final int OBJECTTYPESNUM = 99;
      /**the max. amount of different values*/
    public static final int VALUESNUM = 25;
    

        
    /**The sprite texture which contains every object texture*/
    private static TextureAtlas spritesheet;
    private static Pixmap pixmap;
    private static AtlasRegion[][][] sprites = new AtlasRegion['z'][OBJECTTYPESNUM][VALUESNUM];//{category}{id}{value}
    private static int drawCalls =0;
    
    private final int id; 
    private byte value;
    private boolean obstacle, transparent, hidden; 
    private float lightlevel = 1f;
    private float rotation;
	private int graphicsID;
	/**
	 * number between 0 and 1000
	 */
	private float health = 1000;
	
    
    /**
     * Creates an object. Use getInstance() to create blocks or entitys.
     * @param id the id of the object
     * @param value 
     * @see com.BombingGames.WurfelEngine.Core.Gameobjects.Block#getInstance(int) 
     */
    protected AbstractGameObject(int id, int value) {
        this.id = id;
		this.graphicsID = id;
        this.value = (byte)value;
    }
    
    /**
     * Get the category letter for accessing sprites.
     * @return
     */
    public abstract char getCategory();
    
    /**
     * Place you static update methods here.
     * @param delta 
     */
    public static void updateStaticUpdates(float delta){
       // Sea.staticUpdate(delta);
    }
    
     /**
     *
     * @return
     */
    public static AtlasRegion[][][] getSprites() {
        return sprites;
    }

    /**
     * Reset couner for this frame
     */
    public static void resetDrawCalls() {
        AbstractGameObject.drawCalls = 0;
    }

    /**
     * Maybe not quite correct. A single block has only one drawcall even it should consist of three.
     * @return 
     */
    public static int getDrawCalls() {
        return drawCalls;
    }
    
    /**
     * When calling sprite.draw this hsould also be called for statistics.
     */
    protected void increaseDrawCalls(){
        drawCalls++;
    }
        
    /**
     * Load the spritesheet from memory.
     */
    public static void loadSheet() {
        //spritesheet = new TextureAtlas(Gdx.files.internal("com/BombingGames/Game/Blockimages/Spritesheet.txt"), true);
        Gdx.app.log("AGameObject", "getting spritesheet");
        if (spritesheet == null) {
           // spritesheet = WE.getAsset(WE.getCurrentConfig().getSpritesheetPath()+".txt");
        }
        
        //load again for pixmap, allows access to image color data;
        if (pixmap == null) {
            //pixmap = WurfelEngine.getInstance().manager.get("com/BombingGames/Game/Blockimages/Spritesheet.png", Pixmap.class);
            /*pixmap = new Pixmap(
            //    Gdx.files.internal(WE.getCurrentConfig().getSpritesheetPath()+".png")
            );*/
        }
    }

    /**
     * Returns a sprite texture. You may use your own method like in <i>Block</i>.
     * @param category the category of the sprite e.g. "b" for blocks
     * @param id the id of the object
     * @param value the value of the object
     * @return 
     */
    public static AtlasRegion getSprite(final char category, final int id, final int value) {
        if (spritesheet == null) return null;
        if (sprites[category][id][value] == null){ //load if not already loaded
            AtlasRegion sprite = spritesheet.findRegion(category+Integer.toString(id)+"-"+value);
            if (sprite == null){ //if there is no sprite show the default "sprite not found sprite" for this category
                Gdx.app.debug("Spritesheet", category+Integer.toString(id)+"-"+value + " not found");
                sprite = getSpritesheet().findRegion(category+"0-0");
                if (sprite == null) {//load generic error sprite if category sprite failed
                    sprite = getSpritesheet().findRegion("error");
                    if (sprite == null) throw new NullPointerException("Sprite and category error not found and even the generic error sprite could not be found. Something with the sprites is fucked up.");
                }
            }
            sprites[category][id][value] = sprite;
            return sprite;
        } else {
            return sprites[category][id][value];
        }
    }


    //getter & setter
    
     /**
     * Returns the spritesheet used for rendering.
     * @return the spritesheet used by the objects
     */
    public static TextureAtlas getSpritesheet() {
        return spritesheet;
    }

    /**
     *
     * @return
     */
    public static Pixmap getPixmap() {
        return pixmap;
    }
    
    /**
     * Draws an object in the color of the light engine and with the lightlevel. Only draws if not hidden.
     * @param pos the coordinates where the object should be rendered
     * @param view the view using this render method
     * @param camera The camera rendering the scene
     *//*
    public void render(View view, Camera camera, AbstractPosition pos) {
        render(
            view,
            camera,
            pos,
            WE.getCurrentConfig().shouldAutoShade()
                ? Color.GRAY.cpy()
                :
                    Controller.getLightEngine() != null
                        ? Controller.getLightEngine().getColor()
                        : Color.GRAY.cpy()
        );
    }*/
    
    /**
     * Draws an object in the color of the light engine and with the lightlevel. Only draws if not hidden and not clipped.
     * @param pos the coordinates where the object should be rendered
     * @param view the view using this render method
     * @param camera The camera rendering the scene
     * @param scale
     */
  /*  public void render(View view, Camera camera, AbstractPosition pos, float scale) {
        render(
            view,
            camera,
            pos,
            WE.getCurrentConfig().shouldAutoShade()
                ? Color.GRAY.cpy()
                :
                    Controller.getLightEngine() != null
                        ? Controller.getLightEngine().getColor()
                        : Color.GRAY.cpy(),
            scale
        );
    }*/
    
     /**
     * Draws an object if it is not hidden and not clipped.
     * @param pos the coordinates where the object is rendered
     * @param view the view using this render method
     * @param camera The camera rendering the scene
     * @param color  custom blending color
     */
    /*public void render(View view, Camera camera, AbstractPosition pos, Color color) {
        render(
            view,
            camera,
            pos,
            color,
            WE.getCurrentConfig().useScalePrototype()//if using scale prototype scale the objects
                ? pos.getPoint().getHeight()/(Map.getGameHeight())
                : 0
        );
    }*/
    
         /**
     * Draws an object if it is not hidden and not clipped.
     * @param pos the posiiton where the object is rendered. The center of the object.
     * @param view the view using this render method
     * @param camera The camera rendering the scene
     * @param color  custom blending color
     * @param scale relative value
     */
    /*public void render(View view, Camera camera, AbstractPosition pos, Color color, float scale) {
        //draw the object except hidden ones
        if (!hidden) {             
            render(
                view,
                pos.getProjectedPosX(view),
                pos.getProjectedPosY(view),
                color,
                scale
            );
        }
    }*/
    
        /**
     * Renders at a custom position with the global light.
     * @param view the view using this render method
     * @param xPos rendering position
     * @param yPos rendering position
     */
   /* public void render(View view, int xPos, int yPos) {
        render(
            view,
            xPos,
            yPos,
            WE.getCurrentConfig().shouldAutoShade()
                ? Color.GRAY.cpy()
                :
                    Controller.getLightEngine() != null
                        ? Controller.getLightEngine().getColor()
                        : Color.GRAY.cpy(),
            0
        );
    }
    */
    /**
     * Renders at a custom position with the global light.
     * @param view the view using this render method
     * @param xPos rendering position
     * @param yPos rendering position
     * @param scale relative value
     */
    /*public void render(View view, int xPos, int yPos, float scale) {
        render(
            view,
            xPos,
            yPos,
            WE.getCurrentConfig().shouldAutoShade()
                ? Color.GRAY.cpy()
                :
                    Controller.getLightEngine() != null
                        ? Controller.getLightEngine().getColor()
                        : Color.GRAY.cpy(),
            scale
        );
    }*/
    
    /**
     * Renders at a custom position with a custom light.
     * @param view
     * @param xPos rendering position, center of sprite (screen space?)
     * @param yPos rendering position, center of sprite (screen space?)
     * @param color custom blending color
     * @param scale relative value
     */
   /* public void render(View view, int xPos, int yPos, Color color, float scale) {
		if (id != 0){
			AtlasRegion texture = getSprite(getCategory(), graphicsID, value);
			Sprite sprite = new Sprite(texture);
			sprite.setOrigin(SCREEN_WIDTH2, SCREEN_HEIGHT2+texture.offsetY);
			sprite.rotate(rotation);
			sprite.scale(scale);

			sprite.setPosition(
				xPos+texture.offsetX-texture.originalWidth/2,
				yPos//center
					-SCREEN_HEIGHT2
					+texture.offsetY
			);

			sprite.setColor(color);
			sprite.draw(view.getBatch());
        
        if (WE.getCurrentConfig().debugObjects()){
            ShapeRenderer sh = view.getShapeRenderer();
            sh.begin(ShapeRenderer.ShapeType.Line);
            //sprite outline
            sh.rect(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getHeight()
            );
            //crossing lines
            sh.line(
                xPos-SCREEN_WIDTH2,
                yPos-SCREEN_DEPTH2,
                xPos+SCREEN_WIDTH2,
                yPos+SCREEN_DEPTH2
            );
            sh.line(
                xPos-SCREEN_WIDTH2,
                yPos+SCREEN_DEPTH2,
                xPos+SCREEN_WIDTH2,
                yPos-SCREEN_DEPTH2
            );
            //bounding box
            sh.line(xPos-SCREEN_WIDTH2, yPos, xPos, yPos-SCREEN_DEPTH2);
            sh.line(xPos-SCREEN_WIDTH2, yPos, xPos, yPos+SCREEN_DEPTH2);
            sh.line(xPos, yPos-SCREEN_DEPTH2, xPos+SCREEN_WIDTH2, yPos);
            sh.line(xPos, yPos+SCREEN_DEPTH2, xPos+SCREEN_WIDTH2, yPos);
            sh.end();
        }

			drawCalls++;
		}
    }*/
    
    //getter & setter

	/**
     * returns the id of a object
     * @return getId
     */
    public int getId() {
        return this.id;
    }

	/**
	 * the id of the sprite. should be the same as id but in some cases some objects share their sprites.
	 * @return 
	 */
	public int getSpriteId() {
		return graphicsID;
	}

    /**
     * How bright is the object?
     * The lightlevel is a number between 0 and 1. 1 is full bright. 0 is black. Default is .5.
     * @return
     */
    public float getLightlevel() {
        return lightlevel;
    }

    /**
     * Returns the name of the object
     * @return the name of the object
     */
    public abstract String getName();
    
    /**
     * Get the value. It is like a sub-id and can identify the status.
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the rotation of the object.
     * @return
     */
    public float getRotation() {
        return rotation;
    }
    
    
    /**
     * Returns true, when set as hidden. Hidden objects are not rendered even when they are clipped ("clipped" by the meaning of the raytracing).
     * @return if the object is invisible
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Is this object an obstacle or can you pass through?
     * @return
     */
    public boolean isObstacle() {
        return obstacle;
    }

    /**
     * Can light travel through object?
     * @return
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * Set the brightness of the object.
     * The lightlevel is a scaling factor between.
     * @param lightlevel  1 is full bright. 0 is black.
     */
    public void setLightlevel(float lightlevel) {
        this.lightlevel = lightlevel;
    }

    /**
     * Make the object to an obstacle or passable.
     * @param obstacle true when obstacle. False when passable.
     */
    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    /**
     * Has the object transparent areas?
     * @param transparent
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    /**
     * Set the value of the object.
     * @param value
     */
    public void setValue(int value) {
        this.value = (byte)value;
    }


    /**
     * Hide an object. It won't be rendered even if it is clipped.
     * @param hidden
     */
    public void setHidden(boolean hidden){
        this.hidden = hidden;
    }

    /**
     *
     * @param rotation set the rotation in degrees.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    
	/**
	 * the id of the sprite. should be the same as id but in some cases some objects share their sprites.
	 * @param id 
	 */
	public void setGraphicsId(int id) {
		graphicsID = id;
	}
	
	
    /**
     *
     */
    public static void staticDispose(){
        spritesheet.dispose();//is this line needed?
     //   WE.getAssetManager().unload(WE.getCurrentConfig().getSpritesheetPath()+".txt");
        spritesheet = null;
        sprites = new AtlasRegion['z'][OBJECTTYPESNUM][VALUESNUM];
        //pixmap.dispose();
        pixmap = null;
    }

	   /**
     *
     * @return from maximum 1000
     */
	public float getHealth() {
		return health;
	}

	/**
	 * clamps to [0..1000]
	 * @param health 
	 */
	public void setHealth(float health) {
		if (health>1000)health=1000;
		if (health<0)health=0;
		this.health = health;
	}
	
}