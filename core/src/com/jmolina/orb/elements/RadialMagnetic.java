package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.widgets.RadialField;


/**
 * Elemento magnético radial
 */
public class RadialMagnetic extends Magnetic {

    public RadialMagnetic(AssetManager am, World world, float ppm, float x, float y, float diameter, float threshold, float strength, Polarity polarity) {
        super(am, world, ppm,
                Geometry.CIRCLE,
                x, y, diameter, diameter, 0
        );

        setStrength(strength);
        setThreshold(threshold);
        setPolarity(polarity);

        RadialField radialField = new RadialField(am, getPPM(), diameter, getThreshold(), getStrength());
        setActor(radialField);
    }

    @Override
    public void reset() {
        super.reset();
        ((RadialField)getActor()).reset();
    }
}
