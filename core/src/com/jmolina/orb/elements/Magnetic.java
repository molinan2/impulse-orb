package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.utils.Utils;


/**
 * Elemento magnético
 */
public class Magnetic extends Movable {

    public enum Polarity { ATTRACTIVE, REPULSIVE }

    private final Polarity DEFAULT_POLARITY = Polarity.ATTRACTIVE;
    private final float MAX_FORCE = 5f;

    private float threshold;
    private Polarity polarity;

    public Magnetic(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float x, float y, float w, float h, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm,
                geometry, flavor,
                x, y, w, h, angle
        );

        setThreshold(threshold);
        setPolarity(polarity);
    }

    public void setThreshold(float threshold) {
        this.threshold = MathUtils.clamp(threshold, 0, threshold);
    }

    public float getThreshold() {
        return threshold;
    }

    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    public Polarity getPolarity() {
        return polarity;
    }

    /**
     * Devuelve la distancia de un punto dado a este Elemento (que puede ser circular o rectangular).
     *
     * @param point Punto expresado en unidades del mundo
     */
    private float distance(Vector2 point) {
        switch (getGeometry()) {
            case CIRCLE: return distanceCircle(point);
            default: return distanceCircle(point);
        }
    }

    private float distanceCircle(Vector2 point) {
        return Utils.distance(point, getPosition());
    }

    /**
     * Devuelve la fuerza que ejercería este elemento sobre el punto dado.
     * <p>
     * La fórmula de la atracción está simplificada:
     * - Sólo depende de la inversa de la distancia, siempre que el punto haya sobrepasado el umbral.
     * - Sólo se computan los centroides (i.e. no se atrae el Orb si su centroide no cae dentro del
     * campo de actuación).
     *
     * @param point Punto expresado en unidades del mundo
     */
    public Vector2 force(Vector2 point) {
        Vector2 force = new Vector2(0,0);
        float distance = distance(point);

        if (distance < threshold) {
            Vector2 direction = getPosition().sub(point).nor();
            float factor = MAX_FORCE * (threshold - distance) / threshold;
            force.set(direction).scl(factor);

            if (polarity == Polarity.REPULSIVE)
                force.scl(-1);
        }

        return force;
    }

}
