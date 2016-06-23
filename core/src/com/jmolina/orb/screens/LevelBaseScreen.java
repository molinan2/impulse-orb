package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actors.Element;
import com.jmolina.orb.interfaces.SuperManager;

public class LevelBaseScreen extends BaseScreen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Viewport worldViewport;
    private Viewport gestureViewport;
    private Viewport hudViewport;
    private Stage gestureStage;
    private Stage hudStage;
    private SnapshotArray<Element> elements;

    private final float RATIO_METER_PIXEL = 0.01f;
    private final float SCENE_WIDTH = VIEWPORT_WIDTH * RATIO_METER_PIXEL;
    private final float SCENE_HEIGHT = VIEWPORT_HEIGHT * RATIO_METER_PIXEL;
    private final float SCENE_UNIT = SCENE_WIDTH / 12f;

    public LevelBaseScreen(SuperManager superManager) {
        super(superManager);

        elements = new SnapshotArray<Element>();
        worldViewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, new OrthographicCamera());
        world = new World(new Vector2(0,-9.8f), true);
        debugRenderer = new Box2DDebugRenderer(true, false, false, true, true, true);
        resetWorldCamera();
    }


    /**
     * Screen Overrides
     */

    @Override
    public void render(float delta) {
        clearColor();

        // Follow camera. Tendria que haber siempre un OrbElement

        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

        // Sync Actor -> Body
        world.step(1/60f, 6, 2);
        syncActors(); // Sync Body -> Actor

        getBackgroundStage().draw();
        //debugRenderer.render(world, worldViewport.getCamera().combined);
        getMainStage().draw();
    }

    /**
     * Sobreescribo temporalmente para evitar el efecto de transicion
     */
    @Override
    public void show() {
        // super.show();
        clearRootActions();
        setAsInputProcessor();
        // TODO: El InputProcessor seria gestureStage (o hudStage)
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

}
