package com.jmolina.orb.situations;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jmolina.orb.managers.AssetManager;

public class SituationFactory {

    private AssetManager assetManager;
    private World world;
    private float pixelsPerMeter;

    public SituationFactory(AssetManager assetManager, World world, float pixelsPerMeter) {
        this.assetManager = assetManager;
        this.world = world;
        this.pixelsPerMeter = pixelsPerMeter;
    }

    public Situation newSituation(Class c) {
        Object object = null;

        try {
            Constructor constructor = ClassReflection.getConstructor(c, AssetManager.class, World.class, float.class);
            object = constructor.newInstance(assetManager, world, pixelsPerMeter);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return (Situation) object;
    }

}
