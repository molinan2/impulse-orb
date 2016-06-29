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
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;
import com.jmolina.orb.stages.ParallaxStage;

public class LevelBaseScreen extends BaseScreen {

    private Box2DDebugRenderer debugRenderer;

    private World world;
    private Viewport worldViewport;
    private Viewport gestureViewport;
    private Viewport hudViewport;
    private GestureStage gestureStage;
    private HUDStage hudStage;
    private SnapshotArray<Element> elements;
    private SnapshotArray<Situation> situations;

    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;


    // Temporal
    private ParallaxStage parallaxStage;
    private Viewport parallaxViewport;



    /**
     * En este elemento se centra la camara
     * No debe actualizarse su posicion con syncActor / syncBody, ¿o si?
     */
    private Orb orb;
    private float blockTimer;

    public final static float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    public final static float PIXELS_PER_METER = 1 / RATIO_METER_PIXEL;
    private final static float WORLD_WIDTH = VIEWPORT_WIDTH * RATIO_METER_PIXEL;
    private final static float WORLD_HEIGHT = VIEWPORT_HEIGHT * RATIO_METER_PIXEL;

    private final Vector2 GRAVITY = new Vector2(0, -60f);
    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;
    private final float WORLD_TIME_STEP = 1/120f;
    private final int WORLD_VELOCITY_INTERACTIONS = 8;
    private final int WORLD_POSITION_INTERACTIONS = 3;

    public LevelBaseScreen(SuperManager superManager) {
        super(superManager);

        elements = new SnapshotArray<Element>();
        situations = new SnapshotArray<Situation>();
        worldViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera());
        world = new World(GRAVITY, true);
        debugRenderer = new Box2DDebugRenderer(true, false, false, true, true, true);

        resetWorldCamera();

        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudStage = new HUDStage(hudViewport, getAssetManager());

        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        gestureStage = new GestureStage(gestureViewport, getAssetManager());
        gestureHandler = new GestureHandler(gestureStage, hudStage);
        gestureDetector = new GestureDetector(
                HALF_TAP_SQUARE_SIZE,
                TAP_COUNT_INTERVAL,
                LONG_PRESS_DURATION,
                MAX_FLING_DELAY,
                gestureHandler
        );



        addProcessor(gestureStage);
        addProcessor(gestureDetector);
        addProcessor(hudStage);

        setOrb(new Orb(getAssetManager(), getWorld(), PIXELS_PER_METER));
        blockTimer = 0f;





        // getBackgroundStage().clear();
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        parallaxStage = new ParallaxStage(parallaxViewport, getAssetManager());
    }


    /**
     * Screen Overrides
     */

    @Override
    public void render(float delta) {
        clearColor();

        // TODO Temporal para restaurar el movimiento en el orbe
        if (getOrb().isLocked()) {
            if (blockTimer < 0.5f) {
                blockTimer += Gdx.graphics.getDeltaTime();
            }
            else {
                blockTimer = 0f;
                getOrb().unlock();
            }
        }
        else {
            blockTimer = 0f;
        }

        // TODO Temporal para decrementar el gauge con el tiempo
        hudStage.decrease(0.001f);

        parallaxStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getGestureStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));

        // TODO Sync Actor -> Body
        world.step(WORLD_TIME_STEP, WORLD_VELOCITY_INTERACTIONS, WORLD_POSITION_INTERACTIONS);

        // Follow camera al Orb
        worldViewport.getCamera().position.x = orb.getBody().getPosition().x;
        worldViewport.getCamera().position.y = orb.getBody().getPosition().y;
        worldViewport.getCamera().update();

        syncActors();



        parallaxStage.draw(
                worldViewport.getCamera().position.x,
                worldViewport.getCamera().position.y
        );
        // getBackgroundStage().draw();
        getMainStage().draw();
        getGestureStage().draw();
        getHUDStage().draw();
        // debugRenderer.render(world, worldViewport.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldViewport.update(width, height);
        gestureViewport.update(width, height);
        hudViewport.update(width, height);
        parallaxViewport.update(width, height);
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

    /**
     * Iguala la posicion y rotacion de los Actors a la de sus Bodies
     */
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

        // Orb
        orb.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, RATIO_METER_PIXEL);
    }

    public void syncActor(Element element) {
        element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, RATIO_METER_PIXEL);
    }

    public void setOrb (Orb orb) {
        this.orb = orb;
        gestureHandler.setOrb(orb);
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, RATIO_METER_PIXEL);
    }

    public Orb getOrb () {
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

    public GestureStage getGestureStage () {
        return this.gestureStage;
    }

    public HUDStage getHUDStage () {
        return this.hudStage;
    }

}
