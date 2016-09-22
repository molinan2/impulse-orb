/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.game.Fragments;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Orbe. Es el protagonista de cada nivel.
 */
public class Orb extends Element {

    public static final float INTRO_TIME = 1f;
    public static final float OUTRO_TIME = 1.2f;

    private final float DEFAULT_X = 6;
    private final float DEFAULT_Y = 2;
    private final float DIAMETER = 1.0f;
    private final float INFINITE_DAMPING = 10000f;
    private final float OVER_DAMPING = 10f;
    private final float HEAT_MIN = 0f;
    private final float HEAT_MAX = 1f;
    private final float HEAT_INCREMENT = 0.2f;
    private final float COOLING_RATE = 0.2f;
    private final float OVERLOAD_TIME = 2f;
    private final float FREEZE_TIME = 1f;
    private final float SCALE_CORRECTION = 1.025f;

    /** Indicadores de sobrecarga y paralizacion */
    private boolean frozen, overloaded;

    /** Nivel de calor, escala natural (sin escalado por accion), tiempo de paralizacion, tiempo de sobrecarga */
    private float heat, naturalScale, freezeTime, overloadTime;

    /** Posicion inicial */
    private Vector2 startPosition;

    /** Fragmentos del orbe. Es el actor visible del orbe. */
    private Fragments fragments;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Ratio de conversion pixeles/metros
     */
    public Orb(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter, Geometry.CIRCLE, Flavor.GREEN, 1f, 1f, 6, 2, 0);

        startPosition = new Vector2();
        heat = 0f;
        frozen = overloaded = false;
        fragments = new Fragments(am);
        naturalScale = pixelsPerMeter * SCALE_CORRECTION * DIAMETER / fragments.getWidth();
        fragments.setScale(naturalScale);
        setActor(fragments);
        getBody().setSleepingAllowed(false); // Evita que se quede dormido. ¡La Gravedad no despierta!
        setPosition(DEFAULT_X, DEFAULT_Y);
        setStartPosition(DEFAULT_X, DEFAULT_Y);
    }

    /**
     * Paraliza el orbe. Anula las fuerzas que afectan al orbe aplicando un amortiguamiento infinito.
     */
    public void freeze() {
        frozen = true;
        freezeTime = 0f;

        getBody().setLinearDamping(INFINITE_DAMPING);
        getBody().setAngularDamping(INFINITE_DAMPING);
    }

    /**
     * Desparaliza el orbe.
     */
    public void unfreeze() {
        frozen = false;
        freezeTime = 0f;
        getBody().setLinearDamping(0);
        getBody().setAngularDamping(0);
    }

    /**
     * Resetea la velocidad del orbe (lineal y angular)
     */
    private void resetVelocity() {
        getBody().setLinearVelocity(0, 0);
        getBody().setAngularVelocity(0);
    }

    /**
     * Indica si el orbe esta paralizado
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Aumenta el calor del orbe en la cantidad por defecto
     */
    public void increaseHeat() {
        increaseHeat(HEAT_INCREMENT);
    }

    /**
     * Aumenta el calor del orbe en un incremento
     *
     * @param increment Incremento de calor
     */
    public void increaseHeat(float increment) {
        if (!isOverloaded()) {
            heat = MathUtils.clamp(this.heat + increment, HEAT_MIN, HEAT_MAX);
        }
    }

    /**
     * Decrementa el calor del orbe en un decremento
     *
     * @param decrement Decremento de calor
     */
    public void decreaseHeat (float decrement) {
        increaseHeat(-decrement);
    }

    /**
     * Fija el nivel de calor
     *
     * @param heat Nivel de calor
     */
    private void setHeat(float heat) {
        this.heat = heat;
    }

    /**
     * Restablece el nivel de calor al minimo y desactiva la sobrecarga
     */
    private void resetHeat () {
        setHeat(HEAT_MIN);
        setOverloaded(false);
    }

    /**
     * Devuelve el nivel de calor
     */
    public float getHeat () {
        return heat;
    }

    /**
     * Indica si el calor esta al maximo nivel
     */
    public boolean isHeatMaxed() {
        return getHeat() >= HEAT_MAX;
    }

    /**
     * Ejecuta la animacion de destruccion del orbe
     */
    public void destroy() {
        fragments.destroy();
    }

    /**
     * Indica si el orbe esta sobrecargado
     */
    public boolean isOverloaded() {
        return overloaded;
    }

    /**
     * Fija la sobrecarga
     *
     * @param overloaded Si esta sobrecargado
     */
    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
        overloadTime = 0f;
    }

    /**
     * Devuelve la escala natural
     */
    public float getNaturalScale() {
        return naturalScale;
    }

    /**
     * Restablece el angulo del orbe
     */
    private void resetAngle() {
        getActor().setRotation(0);
        getBody().setTransform(getBody().getPosition().x, getBody().getPosition().y, 0);
    }

    /**
     * Resetea los parametros del orbe y lo coloca en su posicion inicial
     */
    public void reset() {
        setPosition(startPosition.x, startPosition.y);
        resetAngle();
        resetHeat();
        resetVelocity();
        fragments.reset();
        unfreeze();
    }

    /**
     * Ejecuta la animacion de introduccion
     */
    public void applyIntroAction() {
        getActor().addAction(sequence(
                parallel(
                        scaleBy(4 * this.getNaturalScale(), 4 * this.getNaturalScale(), 0),
                        rotateTo(0, 0),
                        alpha(0)
                ),
                parallel(
                        rotateTo(360, INTRO_TIME, Interpolation.exp5),
                        scaleTo(this.getNaturalScale(), this.getNaturalScale(), INTRO_TIME, Interpolation.pow2),
                        fadeIn(INTRO_TIME, Interpolation.pow2)
                )
        ));
    }

    /**
     * Ejecuta la animacion de salida
     *
     * @param toSuccess Callback de cambio a la pantalla de exito
     */
    public void applyOutroAction(Runnable toSuccess) {
        getActor().addAction(sequence(
                parallel(
                        rotateBy(1080, OUTRO_TIME, Interpolation.pow2Out),
                        scaleTo(4 * this.getNaturalScale(), 4 * this.getNaturalScale(), OUTRO_TIME, Interpolation.pow2),
                        fadeOut(OUTRO_TIME, Interpolation.pow2)
                ),
                run(toSuccess)
        ));
    }

    /**
     * Actualiza eltiempo de paralizacion
     */
    public void updateFreezeTime() {
        if (isFrozen()) {
            freezeTime += Gdx.graphics.getRawDeltaTime();

            if (freezeTime > FREEZE_TIME)
                unfreeze();
        }
    }

    /**
     * Actualiza el nivel de calor
     */
    public void updateHeat() {
        if (isOverloaded())
            overloadTime += Gdx.graphics.getRawDeltaTime();
        else
            decreaseHeat(COOLING_RATE * Gdx.graphics.getRawDeltaTime());

        if (overloadTime > OVERLOAD_TIME)
            setOverloaded(false);
    }

    /**
     * Fija el valor de la posicion inicial
     *
     * @param x Coordenada X de la posicion inicial
     * @param y Coordenada Y de la posicion inicial
     */
    public void setStartPosition(float x, float y) {
        startPosition.set(x, y);
    }

    /**
     * Devuelve la posicion inicial
     */
    public Vector2 getStartPosition() {
        return startPosition;
    }

}
