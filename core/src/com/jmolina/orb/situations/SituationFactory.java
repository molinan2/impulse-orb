package com.jmolina.orb.situations;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jmolina.orb.managers.AssetManager;

/**
 * Factoria simplificada de {@link Situation}. Devuelve una nueva instancia de una situacion dada
 * por su nombre de clase.
 */
public class SituationFactory {

    private AssetManager assetManager;
    private World world;
    private float pixelsPerMeter;

    /**
     * Constructor
     *
     * @param assetManager AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correccion pixeles/metros
     */
    public SituationFactory(AssetManager assetManager, World world, float pixelsPerMeter) {
        this.assetManager = assetManager;
        this.world = world;
        this.pixelsPerMeter = pixelsPerMeter;
    }

    /**
     * Instancia una nueva situacion.
     *
     * @param clazz Clase de la situacion
     * @return Instancia
     */
    public Situation newSituation(Class clazz) {
        Object object = null;

        try {
            Constructor constructor = ClassReflection.getConstructor(clazz, AssetManager.class, World.class, float.class);
            object = constructor.newInstance(assetManager, world, pixelsPerMeter);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return (Situation) object;
    }

}
