package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.widgets.Button;

public class LevelBaseScreen extends BaseScreen {

    private Box2DDebugRenderer debugRenderer;

    private World world;
    private Viewport worldViewport;
    private Viewport gestureViewport;
    private Viewport hudViewport;
    private Stage gestureStage;
    private Stage hudStage;
    private SnapshotArray<Element> elements;

    private GestureDetector gestureDetector;


    /**
     * En este elemento se centra la camara
     * No debe actualizarse su posicion con syncActor / syncBody, ¿o si?
     */
    private Element orb;

    public final static float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    private final static float SCENE_WIDTH = VIEWPORT_WIDTH * RATIO_METER_PIXEL;
    private final static float SCENE_HEIGHT = VIEWPORT_HEIGHT * RATIO_METER_PIXEL;
    public final static float SCENE_GRID_UNIT = SCENE_WIDTH / 12f;
    private final Vector2 GRAVITY = new Vector2(0, -9.8f);

    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;

    public LevelBaseScreen(SuperManager superManager) {
        super(superManager);

        elements = new SnapshotArray<Element>();
        worldViewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, new OrthographicCamera());
        world = new World(GRAVITY, true);
        debugRenderer = new Box2DDebugRenderer(true, false, false, true, true, true);
        resetWorldCamera();

        gestureDetector = new GestureDetector(HALF_TAP_SQUARE_SIZE,
                TAP_COUNT_INTERVAL,
                LONG_PRESS_DURATION,
                MAX_FLING_DELAY,
                new GestureHandler());

        gestureStage = new Stage();

        addProcessor(gestureStage);
        addProcessor(gestureDetector);
    }


    /**
     * Screen Overrides
     */

    @Override
    public void render(float delta) {
        clearColor();

        // Follow camera. Tendria que haber siempre un OrbElement
        if (orb != null) {
            worldViewport.getCamera().position.x = orb.getBody().getPosition().x;
            worldViewport.getCamera().position.y = orb.getBody().getPosition().y;
        }

        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

        // Sync Actor -> Body
        world.step(1/60f, 6, 2);
        syncActors(); // Sync Body -> Actor

        getBackgroundStage().draw();
        getMainStage().draw();
        // debugRenderer.render(world, worldViewport.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldViewport.update(width, height); // Tiene sentido ?
    }

    /**
     * Class methods
     */

    public void addElement(Element element) {
        // Añade el elemento a mainStage con addMainActor
        // Registra el elemento a la lista de elementos, para:
        // -actualizar Body->World,
        // -actualizar World->Body
        // -y leer otras propiedades
        addMainActor(element.getActor());
        syncActor(element);
        elements.add(element);
    }

    public SnapshotArray<Element> getElements () {
        return elements;
    }

    public World getWorld() {
        return world;
    }

    /**
     * Translates camera to get (0,0) as the initial origin of the Box2D world
     */
    private void resetWorldCamera() {
        worldViewport.getCamera().position.set(
                worldViewport.getCamera().position.x + SCENE_WIDTH * 0.5f,
                worldViewport.getCamera().position.y + SCENE_HEIGHT * 0.5f,
                0);
        worldViewport.getCamera().update();
    }

    public void syncActors() {
        for (Element element: elements) {
            element.syncActor(worldViewport, SCENE_WIDTH, SCENE_HEIGHT, RATIO_METER_PIXEL);
        }
    }

    public void syncActor(Element element) {
        element.syncActor(worldViewport, SCENE_WIDTH, SCENE_HEIGHT, RATIO_METER_PIXEL);
    }

    public float u(float unit){
        return SCENE_GRID_UNIT * unit;
    }

    public void setOrb (Element element) {
        this.orb = element;
    }

    public Element getOrb () {
        return this.orb;
    }


    /**
     * Gesture Handler
     */
    public class GestureHandler implements GestureDetector.GestureListener {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            System.out.println("tap");

            // TODO: Que cuando se detenga, este un tiempo detenido

            getOrb().getBody().setLinearVelocity(0,0);
            getOrb().getBody().setAngularVelocity(0);
            getOrb().getBody().setLinearDamping(0);
            getOrb().getBody().setAngularDamping(0);

            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            System.out.println("fling X: " + velocityX + ", Y: " + velocityY);

            getOrb().getBody().applyLinearImpulse(
                    velocityX * 0.01f,
                    velocityY * -0.01f,
                    getOrb().getBody().getPosition().x,
                    getOrb().getBody().getPosition().y,
                    true
            );

            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            return false;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {
        }

    }

}
