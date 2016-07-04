package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.listeners.OrbContact;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;
import com.jmolina.orb.stages.ParallaxStage;


/**
 * TODO
 *
 * Temporalmente, esta pantalla está actuando como GameManager
 *
 * Solo se pueden añadir elementos mediante Situations, no directamente.
 * World camera is always centered at Orb position
 */
public class LevelScreen extends BaseScreen {

    /**
     * Constants
     */
    public final static float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    public final static float PIXELS_PER_METER = 1 / RATIO_METER_PIXEL;
    private final static float WORLD_WIDTH = VIEWPORT_WIDTH * RATIO_METER_PIXEL;
    private final static float WORLD_HEIGHT = VIEWPORT_HEIGHT * RATIO_METER_PIXEL;
    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = 1/60f;
    private final int WORLD_VELOCITY_INTERACTIONS = 8;
    private final int WORLD_POSITION_INTERACTIONS = 3;
    private final float ORB_LOCK_TIME = 0.5f;
    private final float GAUGE_INCREMENT_DEFAULT = 0.2f;

    /**
     * Fields
     */
    private World world;
    private OrbContact orbContact;
    private Viewport worldViewport, gestureViewport, hudViewport, parallaxViewport;
    private GestureStage gestureStage;
    private ParallaxStage parallaxStage;
    private HUDStage hudStage;
    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;
    private SnapshotArray<Situation> situations;
    private Orb orb;
    private float orbLockTimer = 0f;
    private boolean paused = false;
    // private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    /**
     * Constructor
     * @param superManager SuperManager
     */
    public LevelScreen(SuperManager superManager) {
        super(superManager);

        situations = new SnapshotArray<Situation>();
        worldViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera());
        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudStage = new HUDStage(getAssetManager(), this, hudViewport);
        gestureStage = new GestureStage(gestureViewport, getAssetManager());
        parallaxStage = new ParallaxStage(getAssetManager(), parallaxViewport);
        world = new World(WORLD_GRAVITY, true);

        orbContact = new OrbContact(){
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().equals(getOrb().getBody().getFixtureList().first())) {
                    if (contact.getFixtureB().getUserData() == Element.Behaviour.RED) {
                        // Destruccion
                        System.out.println(contact.getFixtureB().getUserData());
                    }
                }
                else if (contact.getFixtureB().equals(getOrb().getBody().getFixtureList().first())) {
                    if (contact.getFixtureA().getUserData() == Element.Behaviour.RED) {
                        // Destruccion
                        System.out.println(contact.getFixtureB().getUserData());
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }
        };

        world.setContactListener(orbContact);

        orb = new Orb(getAssetManager(), getWorld());

        resetWorldCamera();
        addOrb(orb);

        gestureHandler = new GestureHandler(this, gestureStage, hudStage, orb);
        gestureDetector = new GestureDetector(
                HALF_TAP_SQUARE_SIZE,
                TAP_COUNT_INTERVAL,
                LONG_PRESS_DURATION,
                MAX_FLING_DELAY,
                gestureHandler
        );

        // Los primeros Processor reciben antes los eventos
        addProcessor(hudStage);
        addProcessor(gestureStage);
        addProcessor(gestureDetector);
    }


    /**
     * Screen Overrides
     */

    /**
     * TODO
     * Este método es muy lento para Android y se ralentiza.
     * Los métodos más lentos son syncActors() y draw()
     */
    @Override
    public void render(float delta) {
        clearColor();
        unlockOrb();
        act();
        syncBodies();

        if (!isPausedGame()) {
            world.step(WORLD_TIME_STEP, WORLD_VELOCITY_INTERACTIONS, WORLD_POSITION_INTERACTIONS);
        }

        followCamera();
        syncActors();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldViewport.update(width, height);
        gestureViewport.update(width, height);
        hudViewport.update(width, height);
        parallaxViewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        hudStage.dispose();
        gestureStage.dispose();
        parallaxStage.dispose();
    }


    /**
     * Class methods
     */

    public World getWorld() {
        return world;
    }

    /**
     * Posiciona la cámara del mundo de forma que coincide el (0,0) de la escena con el del mundo
     */
    private void resetWorldCamera() {
        worldViewport.getCamera().position.set(
                worldViewport.getCamera().position.x + WORLD_WIDTH * 0.5f,
                worldViewport.getCamera().position.y + WORLD_HEIGHT * 0.5f,
                0
        );

        worldViewport.getCamera().update();
    }

    /**
     * Iguala la posicion y rotacion de los Actors a las de sus Bodies
     */
    public void syncActors() {
        for (Situation situation : situations ) {
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
            }
        }

        orb.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
    }

    /**
     * TODO
     * Iguala la posicion y rotacion de los Bodies a la de sus Actors
     */
    private void syncBodies() {

    }

    public void syncActor(Element element) {
        element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
    }

    public void addOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
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

    /**
     * Hace que la camara siga al Orb, igualando su posicion
     */
    private void followCamera() {
        worldViewport.getCamera().position.x = orb.getBody().getPosition().x;
        worldViewport.getCamera().position.y = orb.getBody().getPosition().y;
        worldViewport.getCamera().update();
    }

    private void act() {
        parallaxStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        gestureStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        hudStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    private void draw() {
        parallaxStage.draw(worldViewport.getCamera().position.x, worldViewport.getCamera().position.y);
        getMainStage().draw();
        gestureStage.draw();
        hudStage.draw();
        // debugRenderer.render(world, worldViewport.getCamera().combined);
    }

    /**
     * TODO
     * Desbloquea el Orb transcurrido el tiempo de bloqueo.
     * Temporal mientras implemento el mecanismo controlador
     */
    private void unlockOrb() {
        if (getOrb().isLocked()) {
            if (orbLockTimer < ORB_LOCK_TIME) {
                orbLockTimer += Gdx.graphics.getDeltaTime();
            }
            else {
                orbLockTimer = 0f;
                getOrb().unlock();
            }
        }
        else {
            orbLockTimer = 0f;
        }
    }

    /**
     * GameManager
     */

    public void pauseGame() {
        paused = true;
        gestureHandler.pause();
        hudStage.pause();
    }

    public void resumeGame() {
        Runnable callback = new Runnable() {
            @Override
            public void run() {
                gestureHandler.resume();
                paused = false;
            }
        };

        hudStage.resume(callback);
    }

    public boolean isPausedGame() {
        return paused;
    }

    public void incrementGauge () {
        incrementGauge(GAUGE_INCREMENT_DEFAULT);
    }

    public void incrementGauge (float increment) {
        hudStage.increment(increment);
    }

}
