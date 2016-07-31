package com.jmolina.orb.managers;


import com.badlogic.gdx.physics.box2d.World;

public class SituationFactory {

    private AssetManager assetManager;
    private World world;
    private float pixelsPerMeter;

    public SituationFactory(AssetManager assetManager, World world, float pixelsPerMeter) {
        this.assetManager = assetManager;
        this.world = world;
        this.pixelsPerMeter = pixelsPerMeter;
    }

}
