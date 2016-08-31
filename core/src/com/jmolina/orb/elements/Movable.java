package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.data.Displacement;
import com.jmolina.orb.data.Rotation;
import com.jmolina.orb.interfaces.Reseteable;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Elemento movible (desplazamiento armonico y rotacion)
 */
public class Movable extends Element implements Reseteable {

    private final Interpolation INTERPOLATION = Interpolation.sine;

    /** Factor de correccion del zoom */
    private float zoomCorrectionFactor;

    /** Bandera de salto de sincronizacion */
    private boolean skipBodySync;

    /** Datos de rotacion */
    private Rotation rotation;

    /** Datos de desplazamiento */
    private Displacement displacement;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param geometry Geometria
     * @param flavor Sabor
     * @param w Anchura en unidades del mundo
     * @param h Altura en unidades del mundo
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada X de la posicion en unidades del mundo
     * @param angle Angulo en grados
     */
    public Movable(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float w, float h, float x, float y, float angle) {
        super(am, world, ppm, geometry, flavor, w, h, x, y, angle);
        zoomCorrectionFactor = ppm / Var.GRID_CELL_SIZE;
        setInitialX(x);
        setInitialY(y);
        setInitialAngle(angle);

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

    /**
     * Añade rotacion lineal en sentido de las agujas del reloj
     *
     * @param frequency Frecuencia
     */
    public void addRotation(float frequency) {
        addRotation(frequency, true);
    }

    /**
     * Añade un desplazamiento lineal armónico, empezando y terminando en la posición actual del
     * elemento.
     *
     * @param frequency Frecuencia del desplazamiento completo
     * @param x Amplitud del desplazamiento en el eje XX' (en unidades del mundo)
     * @param y Amplitud del desplazamiento en el eje YY' (en unidades del mundo)
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

    /**
     * Añade un desplazamiento lineal armonico en el eje XX'
     *
     * @param frequency Frecuencia
     * @param x Amplitud del desplazamiento
     */
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
    @Override
    public void reset() {
        getActor().clearActions();
        getBody().setTransform(getInitialX(), getInitialY(), MathUtils.degreesToRadians * getInitialAngle());
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

    /**
     * Desactiva el salto de sincronizacion del cuerpo
     */
    private void allowBodySync() {
        skipBodySync = false;
    }

    /**
     * Indica si se esta saltando la sincronizacion del cuerpo
     */
    private boolean isSkippingBodySync() {
        return skipBodySync;
    }

}
