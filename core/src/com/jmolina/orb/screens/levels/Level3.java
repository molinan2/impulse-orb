package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.expert.*;


public class Level3 extends Level {

    public Level3(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_3);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_3);

        addSituation(new Situation300(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation301(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation301(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation301(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation301(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation302(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation302(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation302(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation302(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation303(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation303(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation303(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation303(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation304(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation305(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation306(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation306(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation306(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation306(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation306(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation306(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation307(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation307(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation307(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation307(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation305(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation308L(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation308L(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation308L(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation308L(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation308R(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation308R(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation308R(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation308R(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation303(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation309(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation309(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation309(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation309(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation310(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation310(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation310(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation310(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation305(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation311(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation311(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation311(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation311(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation303(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation399(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
