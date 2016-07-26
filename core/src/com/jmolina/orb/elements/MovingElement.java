package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Elemento movible y rotable
 */
public class MovingElement extends Element {

    private final Interpolation INTERPOLATION = Interpolation.sine;

    private float zoomCorrectionFactor;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento movible (rotación y desplazamiento)
     */
    public MovingElement(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float x, float y, float w, float h, float angle) {
        super(am, world, ppm, geometry, flavor, x, y, w, h, angle);
        this.zoomCorrectionFactor = ppm / Var.GRID_CELL_SIZE;
    }

    /**
     * Añade rotación lineal respecto del origen.
     *
     * @param frequency Frecuencia de la rotación
     * @param clockwise A true si la rotación es en sentido horario
     */
    public void addRotation(float frequency, boolean clockwise) {
        float sign = clockwise ? -1f : 1f;
        float duration = 1 / frequency;

        this.getActor().addAction(forever(
                rotateBy(sign * 360, duration)
        ));
    }

    public void addRotation(float frequency) {
        addRotation(frequency, true);
    }

    /**
     * Añade un desplazamiento lineal armónico, empezando y terminando en la posición actual del
     * elemento.
     *
     * @param frequency Frecuencia del desplazamiento completo
     * @param x Coordenada X destino (en unidades del mundo)
     * @param y Coordenada Y destino (en unidades del mundo)
     */
    public void addDisplacement(float frequency, float x, float y) {
        float halfDuration = 1 / (2 * frequency);

        this.getActor().addAction(forever(
                sequence(
                        moveBy(toPixels(x), toPixels(y), halfDuration, INTERPOLATION),
                        moveBy(-toPixels(x), -toPixels(y), halfDuration, INTERPOLATION)
                )
        ));
    }

    public void addDisplacement(float frequency, float x) {
        addDisplacement(frequency, x, 0);
    }

    /**
     * Convierte de unidades del mundo a píxeles, teniendo en cuenta el valor de zoom.
     *
     * @param cells Valor en unidades del mundo
     * @return Valor en píxeles
     */
    protected float toPixels(float cells) {
        return this.zoomCorrectionFactor * Utils.cell(cells);
    }

}
