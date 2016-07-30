package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.data.Displacement;
import com.jmolina.orb.data.Rotation;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Elemento movible y rotable
 */
public class Movable extends Element {

    private final Interpolation INTERPOLATION = Interpolation.sine;

    private float zoomCorrectionFactor;
    private float x, y, angle;
    private boolean skipBodySync;
    private Rotation rotation;
    private Displacement displacement;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento movible (rotación y desplazamiento)
     */
    public Movable(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float x, float y, float w, float h, float angle) {
        super(am, world, ppm, geometry, flavor, x, y, w, h, angle);
        this.zoomCorrectionFactor = ppm / Var.GRID_CELL_SIZE;
        this.x = x;
        this.y = y;
        this.angle = angle;

        rotation = new Rotation();
        displacement = new Displacement();
        allowBodySync();
    }

    @Override
    public void syncBody(Viewport viewport) {
        syncBody(viewport, true, true);
    }

    /**
     * {@inheritDoc}
     *
     * Ignora la sincronización de Actor a Body si {@link #isSkippingBodySync()} es true.
     * Si la ignora, desactiva {@link #skipBodySync}, de modo que sólo estará a true durante 1 frame.
     * <p>
     * Es necesario para poder resetear la posición del actor en base a las coordenadas originales
     * del Element dentro del mundo.
     */
    @Override
    public void syncBody(Viewport viewport, boolean position, boolean angle) {
        if (isSkippingBodySync()) allowBodySync();
        else super.syncBody(viewport, position, angle);
    }

    /**
     * Añade rotación lineal respecto del origen.
     *
     * @param frequency Frecuencia de la rotación
     * @param clockwise A true si la rotación es en sentido horario
     */
    public void addRotation(float frequency, boolean clockwise) {
        rotation.frequency = MathUtils.clamp(frequency, 0, frequency);
        rotation.clockwise = clockwise;

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
        displacement.frequency = MathUtils.clamp(frequency, 0, frequency);
        displacement.x = x;
        displacement.y = y;

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
    private float toPixels(float cells) {
        return this.zoomCorrectionFactor * Utils.cell(cells);
    }

    /**
     * Devuelve el Element a su posición y rotación iniciales.
     * Es necesario desactivar temporalmente (1 frame) la sincronización del Body con el Actor
     * para poder posicionar el Element según sus coordenadas del mundo.
     */
    public void reset() {
        getActor().clearActions();
        getBody().setTransform(x, y, MathUtils.degreesToRadians * angle);
        addRotation(rotation.frequency, rotation.clockwise);
        addDisplacement(displacement.frequency, displacement.x, displacement.y);
        skipBodySync();
    }

    /**
     * Skip bodySync on the next frame, allowing positioning the element in World units instead
     * of scene units and then converting.
     */
    private void skipBodySync() {
        skipBodySync = true;
    }

    private void allowBodySync() {
        skipBodySync = false;
    }

    private boolean isSkippingBodySync() {
        return skipBodySync;
    }

}
