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

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.jmolina.orb.interfaces.Reseteable;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;

/**
 * Elemento calentador
 */
public class Heater extends Element implements Reseteable {

    private final float TICK_AMOUNT = 0.2f;
    private final float TICK_PERIOD = 0.66f;
    private final float BLINK_HALF_TIME = 0.5f;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param w Anchura
     * @param h Altura
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     */
    public Heater(AssetManager am, World world, float ppm, float w, float h, float x, float y) {
        super(am, world, am.getGameAtlas().findRegion(Atlas.GAME_HEAT), ppm,
                Geometry.SQUARE, Flavor.BLUE,
                w, h, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.HEAT);
        getUserData().tick.amount = TICK_AMOUNT;
        getUserData().tick.period = TICK_PERIOD;
        getActor().addAction(blinkAction());
    }

    /**
     * Resetea la animacion del elemento
     */
    @Override
    public void reset() {
        getActor().clearActions();
        getActor().addAction(alpha(1));
        getActor().act(0);
        getActor().addAction(blinkAction());
    }

    /**
     * Devuelve una animacion de parpadeo
     */
    private Action blinkAction() {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                alpha(1, BLINK_HALF_TIME, Interpolation.pow2),
                alpha(0.4f, BLINK_HALF_TIME, Interpolation.pow2)
        ));

        return forever;
    }

}
