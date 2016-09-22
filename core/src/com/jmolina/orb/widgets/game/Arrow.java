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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseActor;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Flecha que representa un gesto de impulso
 */
public class Arrow extends BaseGroup {

    private final float LINE_WIDTH_RATIO = 0.125f;

    /** Componentes de la flecha: base, linea y punta de flecha */
    private BaseActor base, line, arrowhead;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param pixelsPerMeter Ratio de pixels/metros
     */
    public Arrow(AssetManager am, float pixelsPerMeter) {
        super(am);

        base = new BaseActor(findRegion(Atlas.GAME_GESTURE_BASE));
        line = new BaseActor(findRegion(Atlas.GAME_GESTURE_LINE));
        arrowhead = new BaseActor(findRegion(Atlas.GAME_GESTURE_ARROWHEAD));

        base.setScale(pixelsPerMeter / base.getWidth());

        line.setScale(
                pixelsPerMeter / line.getWidth(),
                LINE_WIDTH_RATIO * pixelsPerMeter / line.getHeight()
        );

        arrowhead.setScale(pixelsPerMeter / arrowhead.getWidth());

        addActor(base);
        addActor(line);
        addActor(arrowhead);
        setTransform(false);
    }

    /**
     * Fija el punto de inicio y fin de la flecha
     *
     * @param start Punto de inicio en pixeles
     * @param end Punto de fin en pixeles
     */
    public void set(Vector2 start, Vector2 end) {
        Vector2 direction;
        float angle, distance;

        base.setPosition(
                start.x - 0.5f * base.getWidth(),
                start.y - 0.5f * base.getHeight()
        );

        arrowhead.setPosition(
                end.x - 0.5f * arrowhead.getWidth(),
                end.y - 0.5f * arrowhead.getHeight()
        );

        direction = new Vector2(end.x - start.x, end.y - start.y);
        angle = MathUtils.radiansToDegrees * MathUtils.atan2(direction.y, direction.x);
        distance = (float) Math.sqrt(direction.x * direction.x + direction.y * direction.y);

        arrowhead.setRotation(angle);
        line.setRotation(angle);
        line.setScaleX(distance / line.getWidth());

        line.setPosition(
                0.5f * (base.getX() + arrowhead.getX()),
                0.5f * (base.getY() + arrowhead.getY())
        );
    }

}
