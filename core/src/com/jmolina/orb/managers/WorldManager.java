package com.jmolina.orb.managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.interfaces.LevelManager;
import com.jmolina.orb.listeners.ContactHandler;
import com.jmolina.orb.var.Var;

/**
 * Manager del mundo fisico
 */
public class WorldManager {

    /** Variables internas del mundo */
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float TIME_STEP = Var.FPS;
    private final int STEP_MULTIPLIER = 4;
    private final int VELOCITY_ITERATIONS = 8;
    private final int POSITION_ITERATIONS = 3;

    /** Indica que los pasos de la simulacion deben hacerse acumulados */
    private boolean accumulated;

    /** Acumulador de tiempo para los pasos acumulados */
    private float accumulator;

    /** Mundo fisico */
    private World world;

    /** Gestor del listener de contacto entre cuerpos */
    private ContactHandler contactHandler;

    /**
     * Constructor
     */
    public WorldManager() {
        this(false);
    }

    /**
     * Constructor
     *
     * @param accumulated Indica si los pasos de simulacion son acumulados
     */
    public WorldManager(boolean accumulated) {
        this.accumulated = accumulated;
        accumulator = 0f;
        world = new World(WORLD_GRAVITY, true);
    }

    /**
     * Fija el gestor del listener de contacto
     *
     * @param levelManager LevelManager
     * @param orb Orbe
     */
    public void bindContactHandler(LevelManager levelManager, Orb orb) {
        contactHandler = new ContactHandler(levelManager, orb);
        world.setContactListener(contactHandler);
    }

    /**
     * Libera recursos
     */
    public void dispose() {
        world.dispose();
    }

    /**
     * Devuelve el mundo fisico
     */
    public World getWorld() {
        return world;
    }

    /**
     * Avanza la simulación usando el método del acumulador. Este método es útil para entornos con
     * pocos recursos, en los que es habitual que el tiempo de frame supere el tiempo máximo por
     * frame (1/60 a 60 fps), ya que permite "avanzar más" la simulación hasta alcanzar el tiempo
     * de frame. Tiene la desventaja de que quedan residuos temporales en el acumulador, que pueden
     * provocar saltos en frames posteriores (aliasing temporal).
     *
     * Si sobran los recursos, es más recomendable usar un timestep fijo.
     */
    public void step(float delta) {
        if (accumulated) accumulatedStep(delta);
        else multipleStep();
    }

    /**
     * Acumula el tiempo de frame y realiza pasos de la simulación hasta alcanzarlo. Es más apropiado
     * cuando la ejecución es lenta (más de 16 ms de tiempo de frame).
     *
     * @param delta
     */
    private void accumulatedStep(float delta) {
        float frameTime = Math.min(delta, 0.166666f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

    /**
     * Realiza {@link #STEP_MULTIPLIER} pasos de la simulación física por cada fotograma. Un
     * mayor número de pasos aumenta la precisión de las colisiones, a costa de mayor consumo de
     * recursos. Es más apropiado cuando la ejecución es muy rápida (menos de 16 ms de tiempo de
     * frame).
     */
    private void multipleStep() {
        for (int i = 0; i< STEP_MULTIPLIER; i++) {
            world.step(
                    TIME_STEP / (float) STEP_MULTIPLIER,
                    VELOCITY_ITERATIONS,
                    POSITION_ITERATIONS
            );
        }
    }

}
