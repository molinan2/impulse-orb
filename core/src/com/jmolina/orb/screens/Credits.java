package com.jmolina.orb.screens;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.Credit;

public class Credits extends Menu {

    public final String BACON_IPSUM = "Bacon ipsum dolor amet rump landjaeger beef pig fatback t-bone tri-tip ham hock ribeye. Chuck boudin hamburger, t-bone biltong ham swine bresaola prosciutto pork chop. Spare ribs short ribs leberkas venison. Ham hock hamburger shank t-bone porchetta cow brisket jowl jerky alcatra landjaeger pork chop. Ribeye chuck jerky beef ribs pastrami, tenderloin short loin boudin pork belly ham hock.";

    private Credit application;
    private Credit authors;
    private Credit license;
    private Credit libraries;
    private Credit resources;
    private Credit thanks;

    public Credits(SuperManager superManager) {
        super(superManager);

        setReturningScreen(ScreenManager.Key.MAIN);
        setTitle("CREDITS");

        application = new Credit(getAssetManager(), "Application", BACON_IPSUM);
        authors = new Credit(getAssetManager(), "Authors", BACON_IPSUM);
        license = new Credit(getAssetManager(), "License", BACON_IPSUM);
        libraries = new Credit(getAssetManager(), "Libraries", BACON_IPSUM);
        resources = new Credit(getAssetManager(), "Resources", BACON_IPSUM);
        thanks = new Credit(getAssetManager(), "Thanks", BACON_IPSUM);

        add(application);
        add(authors);
        add(license);
        add(libraries);
        add(resources);
        add(thanks);
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
