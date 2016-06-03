package com.jmolina.orb.screens;

import com.jmolina.orb.Orb;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.Credit;

public class Credits extends Menu {

    private Credit application;
    private Credit authors;
    private Credit license;
    private Credit libraries;
    private Credit resources;
    private Credit thanks;

    public Credits(OrbAssetManager am) {
        super(am);

        setReturningScreen(Orb.Name.MAIN);
        setTitle("CREDITS");

        application = new Credit(getAssetManager(), "Application", Var.BACON_IPSUM);
        authors = new Credit(getAssetManager(), "Authors", Var.BACON_IPSUM);
        license = new Credit(getAssetManager(), "License", Var.BACON_IPSUM);
        libraries = new Credit(getAssetManager(), "Libraries", Var.BACON_IPSUM);
        resources = new Credit(getAssetManager(), "Resources", Var.BACON_IPSUM);
        thanks = new Credit(getAssetManager(), "Thanks", Var.BACON_IPSUM);

        addRow(application);
        addRow(authors);
        addRow(license);
        addRow(libraries);
        addRow(resources);
        addRow(thanks);
    }

    @Override
    public void dispose() {
        application.dispose();
        authors.dispose();
        license.dispose();
        libraries.dispose();
        resources.dispose();
        thanks.dispose();
        super.dispose();
    }

}
