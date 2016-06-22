package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
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
import com.jmolina.orb.actors.Ball;
import com.jmolina.orb.actors.Box;
import com.jmolina.orb.interfaces.SuperManager;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Level extends BaseScreen implements InputProcessor {

    private BitmapFont font;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    // private Body ball, ground;

    private Viewport vp;
    private Camera camera;
    private Vector3 point = new Vector3();
    private final float SCENE_WIDTH = VIEWPORT_WIDTH * 0.01f;
    private final float SCENE_HEIGHT = VIEWPORT_HEIGHT * 0.01f;
    private final float UNIT = SCENE_WIDTH / 12f;

    BodyDef dynamicBodyDef;
    PolygonShape square;
    CircleShape circle;
    FixtureDef boxFixtureDef, circleFixtureDef;

    private Stage stage;
    private Ball ball;
    private Box box;
    private Body ballBody, boxBody;


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

        stage = new Stage(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));

        world = new World(new Vector2(0,-9.8f), true);
        debugRenderer = new Box2DDebugRenderer(true, false, false, true, true, true);

        // Creates a ground to avoid objects falling forever
        float posX = 0.5f * SCENE_WIDTH;
        float posY = 2.25f;
        createBox(posX, posY, 8 * UNIT, 0.5f * UNIT);

        box = new Box();

        /*box.setPosition(
                posX * 100f - 0.5f * box.getWidth(),
                posY * 100f - 0.5f * box.getHeight()
        );*/

        stage.addActor(box);

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                createBoxOther(i, j, 0.1f, 0.1f);
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
        circleFixtureDef.restitution = 0.8f;
    }

    private void createBox(float x, float y, float w, float h) {
        // Create a static body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(x, y);
        boxBody = world.createBody(bodyDef);

        // Create a rectangle shape which will fit the world_width and 1 meter high
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(0.5f * w, 0.5f * h);
        // Create a fixture from our rectangle shape and add it to our ground body
        boxBody.createFixture(groundShape, 0.0f);
        groundShape.dispose();
    }

    private void createBoxOther(float x, float y, float w, float h) {
        // Create a static body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body boxBody = world.createBody(bodyDef);

        // Create a rectangle shape which will fit the world_width and 1 meter high
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(0.5f * w, 0.5f * h);
        // Create a fixture from our rectangle shape and add it to our ground body
        boxBody.createFixture(groundShape, 0.0f);
        groundShape.dispose();
    }

    private void createSquare(float x, float y) {
        dynamicBodyDef.position.set(x,y);
        Body body = world.createBody(dynamicBodyDef);
        body.createFixture(boxFixtureDef);
    }

    private void createCircle(float x, float y) {
        dynamicBodyDef.position.set(x,y);
        ballBody = world.createBody(dynamicBodyDef);
        ballBody.createFixture(circleFixtureDef);
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

        // Follow camera
        if (ballBody != null){
            vp.getCamera().position.x = ballBody.getPosition().x;
            vp.getCamera().position.y = ballBody.getPosition().y;
            vp.getCamera().update();
        }

        world.step(1/60f, 6, 2);

        debugRenderer.render(world, vp.getCamera().combined);

        // Update position
        // Sync World -> Scene
        // Sync Camera
        if (ballBody != null){
            float offsetX = SCENE_WIDTH * 0.5f;
            float offsetY = SCENE_HEIGHT * 0.5f;

            ball.setPosition(
                    100 * (ballBody.getPosition().x - (vp.getCamera().position.x - offsetX)) - 0.5f * ball.getWidth(),
                    100 * (ballBody.getPosition().y - (vp.getCamera().position.y - offsetY)) - 0.5f * ball.getHeight()
            );

            // Vector3 vector = new Vector3(ballBody.getPosition().x, ballBody.getPosition().y, 0);
            //vp.getCamera().project(vector, 0, 0, vp.getScreenWidth(), vp.getScreenHeight());
            //vp.project(vector);
            //ball.setPosition(vector.x, vector.y);

            ball.setRotation(MathUtils.radiansToDegrees * ballBody.getAngle());
        }



        if (boxBody != null){
            float offsetX = SCENE_WIDTH * 0.5f;
            float offsetY = SCENE_HEIGHT * 0.5f;

            box.setPosition(
                    100 * (boxBody.getPosition().x - (vp.getCamera().position.x - offsetX)) - 0.5f * box.getWidth(),
                    100 * (boxBody.getPosition().y - (vp.getCamera().position.y - offsetY)) - 0.5f * box.getHeight()
            );

            box.setRotation(MathUtils.radiansToDegrees * boxBody.getAngle());
        }

        stage.getViewport().apply();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
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

            ball = new Ball();
            ball.setPosition(screenX - 0.5f * ball.getWidth(), (VIEWPORT_HEIGHT - screenY) - 0.5f * ball.getHeight());
            stage.addActor(ball);

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
