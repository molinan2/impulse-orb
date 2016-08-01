package com.jmolina.orb.elements;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.widgets.game.LinearField;


/**
 * TODO
 * Elemento magnético lineal
 */
public class LinearMagnetic extends Magnetic {

    private final float NEAR_ZERO = 0.0001f;

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

    @Override
    public void reset() {
        super.reset();
        ((LinearField)getActor()).reset();
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
    public Vector2 force(Vector2 point) {
        Vector2 force = new Vector2(0, 0);

        if (insideField(point)) {
            float factor = MAX_FORCE * (getThreshold() - distanceLine(point)) / getThreshold();
            force.set(Utils.normal(getSegment()));
            force.scl(-1).scl(factor);

            if (getPolarity() == Polarity.ATTRACTIVE)
                force.scl(-1);
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

}
