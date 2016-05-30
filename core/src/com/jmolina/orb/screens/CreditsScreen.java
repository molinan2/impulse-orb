package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.Orb;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.Credit;

public class CreditsScreen extends MenuScreen {

    private Credit application;
    private Credit authors;
    private Credit license;
    private Credit libraries;
    private Credit resources;
    private Credit thanks;

    public CreditsScreen() {
        super();

        setReturningScreen(Orb.Name.MAIN);
        setTitle("CREDITS");

        application = new Credit("Application", Var.BACON_IPSUM);
        authors = new Credit("Authors", Var.BACON_IPSUM);
        license = new Credit("License", Var.BACON_IPSUM);
        libraries = new Credit("Libraries", Var.BACON_IPSUM);
        resources = new Credit("Resources", Var.BACON_IPSUM);
        thanks = new Credit("Thanks", Var.BACON_IPSUM);

        addRow(application);
        addRow(authors);
        addRow(license);
        addRow(libraries);
        addRow(resources);
        addRow(thanks);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
