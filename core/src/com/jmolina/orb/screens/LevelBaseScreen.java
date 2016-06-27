package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.situations.Situation;

public class LevelBaseScreen extends BaseScreen {

    private Box2DDebugRenderer debugRenderer;

    private World world;
    private Viewport worldViewport;
    private Viewport gestureViewport;
    private Viewport hudViewport;
    private Stage gestureStage;
    private Stage hudStage;
    private SnapshotArray<Element> elements;
    private SnapshotArray<Situation> situations;

    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;


    /**
     * En este elemento se centra la camara
     * No debe actualizarse su posicion con syncActor / syncBody, ¿o si?
     */
    private Element orb;

    public final static float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    private final static float WORLD_WIDTH = VIEWPORT_WIDTH * RATIO_METER_PIXEL;
    private final static float WORLD_HEIGHT = VIEWPORT_HEIGHT * RATIO_METER_PIXEL;
    public final static float WORLD_GRID_UNIT = WORLD_WIDTH / 12f;

    private final Vector2 GRAVITY = new Vector2(0, -40f);
    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;
    private final float TIME_STEP = 1/120f;
    private final int VELOCITY_INTERACTIONS = 8;
    private final int POSITION_INTERACTIONS = 3;

    public LevelBaseScreen(SuperManager superManager) {
        super(superManager);

        elements = new SnapshotArray<Element>();
        situations = new SnapshotArray<Situation>();
        worldViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera());
        world = new World(GRAVITY, true);
        debugRenderer = new Box2DDebugRenderer(true, false, false, true, true, true);
        resetWorldCamera();

        gestureHandler = new GestureHandler();
        gestureDetector = new GestureDetector(
                HALF_TAP_SQUARE_SIZE,
                TAP_COUNT_INTERVAL,
                LONG_PRESS_DURATION,
                MAX_FLING_DELAY,
                gestureHandler
        );

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

        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));

        // Sync Actor -> Body
        world.step(TIME_STEP, VELOCITY_INTERACTIONS, POSITION_INTERACTIONS);

        // Follow camera. Tendria que haber siempre un OrbElement
        if (orb != null) {
            worldViewport.getCamera().position.x = orb.getBody().getPosition().x;
            worldViewport.getCamera().position.y = orb.getBody().getPosition().y;
            worldViewport.getCamera().update();
        }

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
                worldViewport.getCamera().position.x + WORLD_WIDTH * 0.5f,
                worldViewport.getCamera().position.y + WORLD_HEIGHT * 0.5f,
                0);
        worldViewport.getCamera().update();
    }

    public void syncActors() {
        // Elementos independientes TODO ¿Habra?
        for (Element element : elements) {
            element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, RATIO_METER_PIXEL);
        }

        // Elementos dentro de Situaciones
        for (Situation situation : situations ) {
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, RATIO_METER_PIXEL);
            }
        }
    }

    public void syncActor(Element element) {
        element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, RATIO_METER_PIXEL);
    }

    public float u(float unit){
        return WORLD_GRID_UNIT * unit;
    }

    public void setOrb (Element element) {
        this.orb = element;
        gestureHandler.setOrb(element);
    }

    public Element getOrb () {
        return this.orb;
    }

    public void addSituation (Situation situation) {
        this.situations.add(situation);
        int size = this.situations.size;
        situation.setOrder(size-1);

        for (Element element : situation.getElements()) {
            addMainActor(element.getActor());
            syncActor(element);
        }
    }

    public SnapshotArray<Situation> getSituations () {
        return this.situations;
    }

}
