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

package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Grupo de actores que dibuja el orbe a partir de 4 fragmentos:
 *
 * 1: Top left
 * 2: Top right
 * 3: Bottom left
 * 4: Bottom right
 */
public class Fragments extends BaseGroup {

    private final float ANIMATION_TIME = 2.0f;

    /** Los 4 fragmentos y una base de explosion */
    private Image explosion, fragment1, fragment2, fragment3, fragment4;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Fragments(AssetManager am) {
        super(am);

        explosion = new Image(findRegion(Atlas.GAME_CIRCLE_RED));
        fragment1 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_1));
        fragment2 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_2));
        fragment3 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_3));
        fragment4 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_4));

        addActor(explosion);
        addActor(fragment1);
        addActor(fragment2);
        addActor(fragment3);
        addActor(fragment4);

        explosion.addAction(alpha(0));

        setTransform(true);
        setSize(Utils.cell(1), Utils.cell(1));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setRotation(0);
        setScale(1);

        reset();
    }

    /**
     * Devuelve un numero aleatorio que representa una distancia que recorreran los fragmentos al
     * destruirse el orbe.
     */
    private float randomDistance() {
        return Utils.cell(4) * (float) (Math.random() - 0.5f);
    }

    /**
     * Devuelve un angulo aleatorio que representa el giro que daran los fragmentos al destruirse
     * el orbe.
     */
    private float randomAngle() {
        return 4 * 360 * (float) (2 * Math.random() - 1);
    }

    /**
     * Ejecuta la animacion de destruccion del orbe.
     */
    public void destroy() {
        explosion.addAction(sequence(
                alpha(0.4f),
                parallel(
                        scaleTo(3, 3, 0.3f, Interpolation.exp5Out),
                        fadeOut(0.3f, Interpolation.exp5Out)
                )
        ));
        fragment1.addAction(getDestructionAction());
        fragment2.addAction(getDestructionAction());
        fragment3.addAction(getDestructionAction());
        fragment4.addAction(getDestructionAction());

        addAction(sequence(
                delay(0.25f * ANIMATION_TIME),
                fadeOut(0.5f * ANIMATION_TIME, Interpolation.pow2In)
        ));
    }

    /**
     * Resetea todos los fragmentos y la explosion a su estado inicial.
     */
    public void reset() {
        clearActions();
        addAction(alpha(1));

        explosion.clearActions();
        fragment1.clearActions();
        fragment2.clearActions();
        fragment3.clearActions();
        fragment4.clearActions();

        explosion.setPosition(Utils.cell(0), Utils.cell(0));
        fragment1.setPosition(Utils.cell(0), Utils.cell(0.5f));
        fragment2.setPosition(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment3.setPosition(Utils.cell(0f), Utils.cell(0f));
        fragment4.setPosition(Utils.cell(0.5f), Utils.cell(0));
        explosion.setSize(Utils.cell(1), Utils.cell(1));
        fragment1.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment2.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment3.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment4.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        explosion.addAction(alpha(0));
        fragment1.addAction(alpha(1));
        fragment2.addAction(alpha(1));
        fragment3.addAction(alpha(1));
        fragment4.addAction(alpha(1));
        fragment1.setRotation(0);
        fragment2.setRotation(0);
        fragment3.setRotation(0);
        fragment4.setRotation(0);
        explosion.setOrigin(0.5f * explosion.getWidth(), 0.5f * explosion.getHeight());
        fragment1.setOrigin(0.5f * fragment1.getWidth(), 0.5f * fragment1.getHeight());
        fragment2.setOrigin(0.5f * fragment2.getWidth(), 0.5f * fragment2.getHeight());
        fragment3.setOrigin(0.5f * fragment3.getWidth(), 0.5f * fragment3.getHeight());
        fragment4.setOrigin(0.5f * fragment4.getWidth(), 0.5f * fragment4.getHeight());
        explosion.setScale(1.5f);
    };

    /**
     * Devuelve una animacion de destruccion para un fragmento
     */
    private Action getDestructionAction() {
        return new ParallelAction(
                Actions.rotateBy(randomAngle(), ANIMATION_TIME),
                Actions.moveBy(randomDistance(), randomDistance(), ANIMATION_TIME)
        );
    }

}
