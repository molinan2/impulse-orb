package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.game.RadialField;


/**
 * Elemento magnético radial
 */
public class RadialMagnetic extends Magnetic {

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
