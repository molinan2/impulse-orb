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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.game.RadialField;


/**
 * Elemento magnético radial
 */
public class RadialMagnetic extends Magnetic {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param flavor Sabor
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     * @param threshold Umbral en unidades del mundo
     * @param polarity Polaridad
     */
    public RadialMagnetic(AssetManager am, World world, float ppm, Flavor flavor, float diameter, float x, float y, float threshold, Polarity polarity) {
        super(
                am, world, ppm,
                Geometry.CIRCLE, flavor,
                diameter, diameter, x, y, 0,
                threshold, polarity
        );

        RadialField radialField = new RadialField(am, getPPM(), flavor, diameter, threshold, polarity);
        setActor(radialField);
        setThreshold(threshold);
        setPolarity(polarity);
    }

    /**
     * Devuelve la fuerza que ejerce este elemento sobre un punto dado.
     * <p>
     * La fórmula de la atracción está simplificada:
     * - Sólo depende de la inversa de la distancia, siempre que el punto haya sobrepasado el umbral.
     * - Sólo se computan los centroides (i.e. no se atrae el Orb si su centroide no cae dentro del
     * campo de actuación).
     *
     * @param point Punto expresado en unidades del mundo
     */
    @Override
    public Vector2 getForce(Vector2 point) {
        Vector2 force = new Vector2(0, 0);

        if (belowThreshold(point)) {
            float factor = MAX_FORCE * (getThreshold() - distance(point)) / getThreshold();
            Vector2 direction = getPosition().sub(point).nor();
            force.set(direction).scl(factor);

            if (getPolarity() == Polarity.REPULSIVE)
                force.scl(-1);
        }

        return force;
    }

    /**
     * Indica si un punto se encuentra dentro del campo de acción.
     *
     * Compara distancias al cuadrado para evitar el cálculo de raíces cuadradas. Esta función
     * actúa de primera discriminante para calcular la fuerza ejercida sobre el punto.
     *
     * @param point Punto en unidades del mundo
     * @return
     */
    private boolean belowThreshold(Vector2 point) {
        return point.dst2(getPosition()) <= getThreshold() * getThreshold();
    }

    /**
     * Calcula la distancia de un punto al centro del elemento.
     *
     * @param point Punto en unidades del mundo
     */
    private float distance(Vector2 point) {
        return Utils.distance(point, getPosition());
    }
}
