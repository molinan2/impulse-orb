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

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.game.LinearField;


/**
 * Elemento magnético lineal
 */
public class LinearMagnetic extends Magnetic {

    private final float NEAR_ZERO = 0.0001f;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param flavor Sabor
     * @param w Anchura en unidades del mundo
     * @param h Altura en unidades del mundo
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     * @param angle Angulo
     * @param threshold Umbral en unidades del mundo
     * @param polarity Polaridad
     */
    public LinearMagnetic(AssetManager am, World world, float ppm, Flavor flavor, float w, float h, float x, float y, float angle, float threshold, Polarity polarity) {
        super(
                am, world, ppm,
                Geometry.SQUARE, flavor,
                w, h, x, y, angle,
                threshold, polarity
        );

        LinearField linearField = new LinearField(am, getPPM(), flavor, w, h, threshold, polarity);
        setActor(linearField);
        setThreshold(threshold);
        setPolarity(polarity);
    }

    /**
     * Calcula la distancia de un punto al eje principal del elemento (eje 'x').
     *
     * @param point Punto expresado en unidades del mundo
     */
    private float distanceLine(Vector2 point) {
        return Intersector.distanceLinePoint(getStart().x, getStart().y, getEnd().x, getEnd().y, point.x, point.y);
    }

    /**
     * Calcula la distancia de un punto al segmento del eje principal del elemento (de longitud 'w').
     *
     * @param point Punto expresado en unidades del mundo
     */
    private float distanceSegment(Vector2 point) {
        return Intersector.distanceSegmentPoint(getStart(), getEnd(), point);
    }

    /**
     * Punto de inicio del segmento.
     */
    private Vector2 getStart() {
        float startX = getPosition().x + 0.5f * getWidth() * MathUtils.cosDeg(180 + getRotation());
        float startY = getPosition().y + 0.5f * getWidth() * MathUtils.sinDeg(180 + getRotation());

        return new Vector2(startX, startY);
    }

    /**
     * Punto de fin del segmento.
     */
    private Vector2 getEnd() {
        float endX = getPosition().x + 0.5f * getWidth() * MathUtils.cosDeg(getRotation());
        float endY = getPosition().y + 0.5f * getWidth() * MathUtils.sinDeg(getRotation());

        return new Vector2(endX, endY);
    }

    /**
     * Segmento del elemento. Es el segmento del eje principal (x), desde el punto de inicio hasta el
     * de fin. Tiene una longitud de {@link #width}.
     */
    private Vector2 getSegment() {
        return getEnd().sub(getStart());
    }

    /**
     * Devuelve la fuerza que ejerce este elemento sobre un punto dado.
     *
     * La fuerza es cero si la proyección del punto sobre el eje del elemento cae fuera del segmento
     * del elemento.
     *
     * @param point Punto expresado en unidades del mundo
     */
    @Override
    public Vector2 getForce(Vector2 point) {
        Vector2 force = new Vector2(0, 0);

        if (closeRange(point)) {
            if (insideField(point)) {
                float factor = MAX_FORCE * (getThreshold() - distanceLine(point)) / getThreshold();
                force.set(Utils.normal(getSegment()));
                force.scl(factor);

                if (getPolarity() == Polarity.REPULSIVE)
                    force.scl(-1);
            }
        }

        return force;
    }

    /**
     * Indica si el punto se encuentra dentro del campo magnético
     *
     * @param point Punto en coordenadas del mundo
     */
    private boolean insideField(Vector2 point) {
        return atFrontSide(point) && projectedOnSegment(point) && belowThreshold(point);
    }

    /**
     * Indica si el punto se encuentra en la cara frontal del elemento. La cara frontal es la que
     * queda a la izquierda del vector segmento (Start-End).
     *
     * @param point Punto en coordenadas del mundo
     */
    private boolean atFrontSide(Vector2 point) {
        Vector2 vectorStartPoint = new Vector2(point);
        vectorStartPoint.sub(getStart());

        return getSegment().angle(vectorStartPoint) >= 0;
    }

    /**
     * Indica si la proyección del punto sobre el eje del elemento está dentro del segmento.
     *
     * @param point Punto en coordenadas del mundo
     */
    private boolean projectedOnSegment(Vector2 point) {
        return Math.abs(distanceSegment(point) - distanceLine(point)) < NEAR_ZERO;
    }

    /**
     * Indica la distancia del punto al elemento es más cercana que el umbral
     *
     * @param point Punto en coordenadas del mundo
     */
    private boolean belowThreshold(Vector2 point) {
        return distanceLine(point) <= getThreshold();
    }

    /**
     * Indica si un punto se encuentra en "rango cercano" del elemento. "Rango cercano" es la distancia entre
     * el centro del elemento y el pico más alejado del campo magnético. Cualquier punto más alejado
     * no se verá afectado por el campo. Se usa para evitar el cálculo de fuerza en los elementos
     * que no estén suficientemente cerca del punto.
     *
     * Compara las distancias al cuadrado para evitar el cálculo de las raíces cuadradas. Esta función
     * actúa de primera discriminante para calcular la fuerza ejercida sobre el punto.
     *
     * @param point Un punto en coordenadas del mundo
     * @return
     */
    private boolean closeRange(Vector2 point) {
        float maximumFieldDistance2 = 0.25f * getWidth() * getWidth() + getThreshold() * getThreshold();
        float pointCenterDistance2 = point.dst2(getPosition());

        return pointCenterDistance2 <= maximumFieldDistance2;

    }

}
