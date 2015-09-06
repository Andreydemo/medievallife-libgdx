package com.demosoft.game.medievallife;

import box2dLight.DirectionalLight;
import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.BaseScene;
import com.demosoft.game.medievallife.core.IsometricCamera;

import java.util.ArrayList;

@Component
public class MedievalLifeScene extends BaseScene {

    @Autowired
    private ContextConteiner context;
    static RayHandler rayHandler;
    World world;
    Body groundBody;

    boolean firstShow = true;
    float sunDirection = -90f;
    static final float RADIUS = 1f;
    static final int RAYS_PER_BALL = 128;
    static final int BALLSNUM = 5;
    static final float LIGHT_DISTANCE = 16f;
    ArrayList<Light> lights = new ArrayList<Light>(BALLSNUM);
    ArrayList<Body> balls = new ArrayList<Body>(BALLSNUM);
    static Color sunColor = new Color(1, 1, 1, 0.5f);

    private void createBoxes() {
        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(RADIUS);

        FixtureDef def = new FixtureDef();
        def.restitution = 0.9f;
        def.friction = 0.01f;
        def.shape = ballShape;
        def.density = 1f;
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = BodyDef.BodyType.DynamicBody;

        for (int i = 0; i < BALLSNUM; i++) {
            // Create the BodyDef, set a random position above the
            // ground and create a new body
            boxBodyDef.position.x = -20 + (float) (Math.random() * 40);
            boxBodyDef.position.y = 10 + (float) (Math.random() * 15);
            Body boxBody = world.createBody(boxBodyDef);
            boxBody.createFixture(def);
            balls.add(boxBody);
        }
        ballShape.dispose();
    }

    void clearLights() {
        if (lights.size() > 0) {
            for (Light light : lights) {
                light.remove();
            }
            lights.clear();
        }
        // groundBody.setActive(true);
    }

    void initPointLights() {
        clearLights();
        for (int i = 0; i < BALLSNUM; i++) {
            PointLight light = new PointLight(
                    rayHandler, RAYS_PER_BALL, null, LIGHT_DISTANCE, 0f, 0f);
            light.attachToBody(balls.get(i), RADIUS / 2f, RADIUS / 2f);
            light.setColor(
                    MathUtils.random(),
                    MathUtils.random(),
                    MathUtils.random(),
                    1f);
            lights.add(light);
        }
    }

    private void createPhysicsWorld() {

        world = new World(new Vector2(0, 0), false);

        /*float halfWidth = Gdx.graphics.getWidth() / 2f;
        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(new Vector2[]{
                new Vector2(-halfWidth, 0f),
                new Vector2(halfWidth, 0f),
                new Vector2(halfWidth, Gdx.graphics.getHeight()*2),
                new Vector2(-halfWidth, Gdx.graphics.getHeight()*2)});
        BodyDef chainBodyDef = new BodyDef();
        chainBodyDef.type = BodyDef.BodyType.StaticBody;*/
        //groundBody = world.createBody(chainBodyDef);
        // groundBody.createFixture(chainShape, 0);
        //chainShape.dispose();
        // createBoxes();
    }

    static DirectionalLight light;

    public void create() {
        createPhysicsWorld();

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);


        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0f, 0f, 0f, 1f);
        rayHandler.setBlurNum(0);

      /*  new PointLight(rayHandler, RAYS_PER_BALL, new Color(1, 0, 1, 1), LIGHT_DISTANCE, 0, 0);
        new PointLight(rayHandler, RAYS_PER_BALL, new Color(1, 1, 0, 1), LIGHT_DISTANCE, 100, 100);
        new PointLight(rayHandler, RAYS_PER_BALL, new Color(1, 1, 1, 1), 200, 200, 200);
        new PointLight(rayHandler, RAYS_PER_BALL, new Color(1, 0, 0, 1), LIGHT_DISTANCE, 300, 300);*/
        //initPointLights();
        new PointLight(rayHandler, RAYS_PER_BALL, new Color(1, 1, 1, 1), 600, 200, 200);
        rayHandler.setCombinedMatrix(context.getCamera());
        //  sunDirection = MathUtils.random(0f, 360f);
        // groundBody.setActive(false);
        //sunDirection = MathUtils.random(0f, 360f);
        light = new DirectionalLight(rayHandler, 4 * RAYS_PER_BALL, sunColor, -89);
        System.out.printf("staaaaaaaaaaaaaaar");
        Thread t = new Thread(new Runnable() {
            boolean sunRise = false;

            @Override
            public void run() {
                while (true) {
                    try {

                        if (!sunRise) {
                            sunColor.a -= 0.01f;
                        }
                        if (sunRise) {
                            sunColor.a += 0.01f;
                        }

                        if (sunColor.a >= 0.00f && sunColor.a < 0.02f) {
                            sunRise = true;
                        }
                        if (sunColor.a >= 0.99f) {
                            sunRise = false;
                        }
                        synchronized (light) {
                            light.remove();
                            light = new DirectionalLight(rayHandler, 4 * RAYS_PER_BALL, sunColor, -89);
                        }
                        Thread.sleep(500);
                        context.logger.logInfo(String.valueOf(sunColor.a));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
        // DirectionalLight light2 = new DirectionalLight(rayHandler, 4 * RAYS_PER_BALL, new Color(1, 1, 1, 1), 0);
        lights.add(light);
    }

    boolean sunRise = false;

    public void renderLight() {
        /** BOX2D LIGHT STUFF BEGIN */
        rayHandler.setCombinedMatrix(context.getCamera());
        synchronized (light) {
            rayHandler.update();
            rayHandler.render();
        }
        /** BOX2D LIGHT STUFF END */
    }

    @Override
    public void show() {
        create();
        super.show();
        if (firstShow) {
            context.getPlayer().processAtlass(context.getSpritesLoader().manager.get("player.pack", TextureAtlas.class),
                    context.getSpritesLoader().manager.get("isometric_hero/hero.pack", TextureAtlas.class));

            firstShow = false;
        }
    }

    @Override
    public void render(float delta) {

        context.getCamera().update();
        Gdx.gl.glClearColor(getRgbColorValue(135), getRgbColorValue(206), getRgbColorValue(250), 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        context.getBatch().setProjectionMatrix(context.getCamera().combined);
        context.getBatch().begin();
        context.getInputProcessor().processKeyPressed();
        context.getRenderingManager().render();
        drawScene();
        drawFps();
        drawZoom();
        drawPlayerInfo();
        context.getBatch().end();
        context.getBatch().begin();
        renderLight();
        context.getBatch().end();
        super.render(delta);
    }

    public float getRgbColorValue(int value) {
        return value / 255.0f;
    }

    public void drawFps() {
        context.getFont().draw(context.getBatch(), "fps: " + Gdx.graphics.getFramesPerSecond(), translateX(20), translateY(30));
    }

    public void drawZoom() {
        context.getFont().draw(context.getBatch(), "zoom: " + context.getCameraManager().getCamera().zoom, translateX(20), translateY(50));
        Camera cam = context.getCameraManager().getCamera();
        context.getFont().draw(context.getBatch(), "camera props: viewportHeight" + cam.viewportHeight + " viewportWidth: " + cam.viewportWidth + " pos:" + cam.position,
                translateX(20), translateY(70));
    }

    public void drawPlayerInfo() {
        context.getFont().draw(context.getBatch(), "grid position: " + context.getPlayer().getGridPositon() + " world position:" + context.getPlayer().getWorldPositon(), translateX(20), translateY(90));
    }

    public void drawScene() {
        if (Gdx.input.isTouched()) {

            context.getFont().draw(context.getBatch(), "x: " + Gdx.input.getX() + " y: " + Gdx.input.getY(), translateX(Gdx.input.getX()), translateY(Gdx.input.getY()));
            context.getFont().draw(context.getBatch(), "unproject x: " + translateX(Gdx.input.getX()) + " y: " + translateY(Gdx.input.getY()), translateX(Gdx.input.getX()),
                    translateY(Gdx.input.getY()) + 20);
            Vector3 onGrid = new Vector3(translateX(Gdx.input.getX()), translateY(Gdx.input.getY()), 0);
            IsometricCamera.worldToGrid(onGrid);
            context.getFont().draw(context.getBatch(), "on grid x: " + onGrid.x + " y: " + onGrid.y,
                    translateX(Gdx.input.getX()), translateY(Gdx.input.getY()) + 40);

        }

    }

    public float translateY(int y) {
        if (!context.isFlipped()) {
            return Gdx.graphics.getHeight() - y;
        }
        Vector3 v = new Vector3(0, y, 0);
        return context.getCameraManager().getCamera().unproject(v).y;
    }

    public float translateX(int x) {
        Vector3 v = new Vector3(x, 0, 0);
        return context.getCameraManager().getCamera().unproject(v).x;
    }

}
