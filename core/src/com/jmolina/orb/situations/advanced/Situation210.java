package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation210 extends Walls {

    public Situation210(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                (float) Math.sqrt(2), (float) Math.sqrt(2), 6, 9, 0
        );

        destroyer.addRotation(0.25f);

        Movable inter = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2, 1, 6, 0, 0
        );

        Movable platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                5, 1, 9.5f, 0, 0
        );

        Movable platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                5, 1, 2.5f, 18, 0
        );

        platform1.addDisplacement(0.125f, 0, 18);
        platform2.addDisplacement(0.125f, 0, -18);
        inter.addDisplacement(0.25f, 0, 18);

        Movable wallLeft1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 0, 0
        );

        Movable wallLeft2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 4.5f, 0
        );

        Movable wallLeft3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 9, 0
        );

        Movable wallLeft4 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 13.5f, 0
        );

        Movable wallLeft5 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 18, 0
        );

        Movable wallRight1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 0, 0
        );

        Movable wallRight2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 4.5f, 0
        );

        Movable wallRight3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 9, 0
        );

        Movable wallRight4 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 13.5f, 0
        );

        Movable wallRight5 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 18, 0
        );

        addElement(platform1);
        addElement(platform2);
        addElement(inter);
        addElement(wallLeft1);
        addElement(wallLeft2);
        addElement(wallLeft3);
        addElement(wallLeft4);
        addElement(wallLeft5);
        addElement(wallRight1);
        addElement(wallRight2);
        addElement(wallRight3);
        addElement(wallRight4);
        addElement(wallRight5);
        addElement(destroyer);
    }

}
