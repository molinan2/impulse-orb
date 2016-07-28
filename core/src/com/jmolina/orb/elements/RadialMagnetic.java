package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.widgets.RadialField;


/**
 * Elemento magnético radial
 */
public class RadialMagnetic extends Magnetic {

    public RadialMagnetic(AssetManager am, World world, float ppm, float x, float y, float diameter, float threshold, Polarity polarity) {
        this(am, world, ppm, Flavor.VIOLET, x, y, diameter, threshold, polarity);
    }

    public RadialMagnetic(AssetManager am, World world, float ppm, Flavor flavor, float x, float y, float diameter, float threshold, Polarity polarity) {
        super(am, world, ppm,
                Geometry.CIRCLE, flavor,
                x, y, diameter, diameter, 0,
                threshold, polarity
        );

        setThreshold(threshold);
        setPolarity(polarity);

        RadialField radialField = new RadialField(am, getPPM(), flavor, diameter, getThreshold(), polarity);
        setActor(radialField);
    }

    @Override
    public void reset() {
        super.reset();
        ((RadialField)getActor()).reset();
    }

}
