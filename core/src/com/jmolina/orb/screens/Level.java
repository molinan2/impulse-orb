package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.interfaces.SuperManager;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Level extends BaseScreen implements InputProcessor {

    private BitmapFont font;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body ball, ground;
    private Stage stage;
    private Viewport vp;
    private Camera camera;
    private Vector3 point = new Vector3();
    private final float SCENE_WIDTH = VIEWPORT_WIDTH * 0.01f;
    private final float SCENE_HEIGHT = VIEWPORT_HEIGHT * 0.01f;

    BodyDef dynamicBodyDef;
    PolygonShape square;
    CircleShape circle;
    FixtureDef boxFixtureDef, circleFixtureDef;


    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public Level(SuperManager superManager) {
        super(superManager);

        camera = new OrthographicCamera();
        vp = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);

        // Center camera to get (0,0) as the origin of the Box2D world
        vp.getCamera().position.set(
                vp.getCamera().position.x + SCENE_WIDTH * 0.5f,
                vp.getCamera().position.y + SCENE_HEIGHT * 0.5f,
                0);
        vp.getCamera().update();

        world = new World(new Vector2(0,-9.8f), true);
        debugRenderer = new Box2DDebugRenderer(true, false, true, true, true, true);

        // Creates a ground to avoid objects falling forever
        // createStaticBox(1.0f, 1.0f, 0.2f, 0.2f);
        createStaticBox(0.5f * SCENE_WIDTH, 2.25f, 0.8f * SCENE_WIDTH, 0.2f);

        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                createStaticBox(i, j, 0.1f, 0.1f);
            }
        }

        // Default Body Definition
        dynamicBodyDef = new BodyDef();
        dynamicBodyDef.type = BodyType.DynamicBody;

        square = new PolygonShape();
        square.setAsBox(0.5f * 1, 0.5f * 1); // 1 meter-sided square
        circle = new CircleShape();
        circle.setRadius(0.25f);

        // Fixture definition for our shapes
        boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = square;
        boxFixtureDef.density = 0.8f;
        boxFixtureDef.friction = 0.8f;
        boxFixtureDef.restitution = 0.15f;

        // Fixture definition for our shapes
        circleFixtureDef = new FixtureDef();
        circleFixtureDef.shape = circle;
        circleFixtureDef.density = 0.5f;
        circleFixtureDef.friction = 0.4f;
        circleFixtureDef.restitution = 0.6f;


    }

    private void createStaticBox(float x, float y, float w, float h) {
        // Create a static body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        // Create a rectangle shape which will fit the world_width and 1 meter high
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(0.5f * w, 0.5f * h);
        // Create a fixture from our rectangle shape and add it to our ground body
        body.createFixture(groundShape, 0.0f);
        groundShape.dispose();
    }

    private void createSquare(float x, float y) {
        dynamicBodyDef.position.set(x,y);
        Body body = world.createBody(dynamicBodyDef);
        body.createFixture(boxFixtureDef);
    }

    private void createCircle(float x, float y) {
        dynamicBodyDef.position.set(x,y);
        Body body = world.createBody(dynamicBodyDef);
        body.createFixture(circleFixtureDef);
    }

    private void handleInput() {
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {

            if (Gdx.input.getX() < VIEWPORT_WIDTH * 0.2f) {
                vp.getCamera().translate(-0.02f, 0, 0);
            }
            else if (Gdx.input.getX() > VIEWPORT_WIDTH * 0.8f) {
                vp.getCamera().translate(0.02f, 0, 0);
            }

            if (Gdx.input.getY() < VIEWPORT_HEIGHT * 0.2f) {
                vp.getCamera().translate(0, 0.02f, 0);
            }
            else if (Gdx.input.getY() > VIEWPORT_HEIGHT * 0.8f) {
                vp.getCamera().translate(0, -0.02f, 0);
            }

            vp.getCamera().update();
        }
    }


    /**
     * Screen Overrides
     */

    @Override
    public void render(float delta) {
        clearColor();
        handleInput();
        world.step(1/60f, 6, 2);
        debugRenderer.render(world, vp.getCamera().combined);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }


    /**
     * Input Processor Overrides
     */

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            point.set(screenX, screenY, 0);
            vp.getCamera().unproject(point);
            createCircle(point.x,point.y);
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
