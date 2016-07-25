package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class RotatingElement extends Element {

    public RotatingElement(AssetManager am, World world, float x, float y, float w, float h, float angle, Flavor flavor, Geometry geometry, BodyDef.BodyType bodyType, float pixelsPerMeter) {
        super(am, world, x, y, w, h, angle, flavor, geometry, bodyType, pixelsPerMeter);
    }
}
