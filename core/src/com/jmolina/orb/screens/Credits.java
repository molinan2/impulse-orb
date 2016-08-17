package com.jmolina.orb.screens;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.Credit;

public class Credits extends Menu {

    private final String AUTHORS = "Game design, programming, interface design and sound effects design by Juan M. Molina [1].";
    private final String LICENSE = "Source code licensed under GPLv3 [1]. Original content comprised of sound effects and graphic art, and licensed under CC BY-SA 4.0 [2].";
    private final String RESOURCES = "All music themes composed by Benjamin Tissot [1] and licensed under CC BY-ND 3.0 [2]. Music themes are \"Funky element\" [3], \"The lounge\" [4] and \"Pop dance\" [5]. \"Padlock icon\" designed by Dave Gandy [6] for FLATICON [7] and licensed under CC BY 3.0 [8]. Roboto font designed by Christian Robertson for Google [9] and licensed under Apache License 2.0 [10].";
    private final String LIBRARIES = "This application makes extensive use of libGDX [1], a game-development framework created by Mario Zechner and Nathan Sweet and maintained by the community [2]. libGDX is licensed under the Apache License 2.0 [3].";
    private final String TOOLS = "Fonts rendered with Hiero [1], a Bitmap Font converter/creator by Kevin Glass and adapted by Nathan Sweet [2].\n" +
            "\n" +
            "UI and ingame sound effects designed with Sfxr [3], a retro sound effects synthesizer by Tomas Pettersson.\n" +
            "\n" +
            "Textures packed with GDX Texture Packer [4], an efficient image packing tool by Aurelien Ribon [5].";
    private final String THANKS = "To Manuel Jesús Marín Jiménez for his guidance and help with the planning.\n" +
            "\n" +
            "To the Free Software community.";

    private Credit tools;
    private Credit authors;
    private Credit license;
    private Credit libraries;
    private Credit resources;
    private Credit thanks;

    public Credits(SuperManager superManager) {
        super(superManager);

        setPreviousScreen(ScreenManager.Key.MAIN);
        setTitle("CREDITS");

        authors = new Credit(getAssetManager(), "AUTHORS", AUTHORS);
        authors.addLink("Juan M. Molina's blog", "https://jmolinan.wordpress.com/");

        license = new Credit(getAssetManager(), "LICENSE", LICENSE);
        license.addLink("GPLv3 license", "https://www.gnu.org/licenses/gpl-3.0.en.html");
        license.addLink("CC BY-SA 4.0 license", "https://creativecommons.org/licenses/by-sa/4.0/");

        resources = new Credit(getAssetManager(), "3RD PARTY RESOURCES", RESOURCES);
        resources.addLink("Bensound.com", "http://www.bensound.com/");
        resources.addLink("CC BY-ND 3.0 license", "https://creativecommons.org/licenses/by-nd/3.0/legalcode");
        resources.addLink("Funky element theme", "http://www.bensound.com/royalty-free-music/track/funky-element");
        resources.addLink("The lounge theme", "http://www.bensound.com/royalty-free-music/track/the-lounge");
        resources.addLink("Pop dance theme", "http://www.bensound.com/royalty-free-music/track/pop-dance");
        resources.addLink("Padlock icon", "http://www.flaticon.com/free-icon/padlock_25239#term=lock&page=1&position=51");
        resources.addLink("www.flaticon.com", "http://www.flaticon.com");
        resources.addLink("CC BY 3.0 license", "https://creativecommons.org/licenses/by/3.0/");
        resources.addLink("Roboto font", "https://www.google.com/fonts/specimen/Roboto");
        resources.addLink("Apache License 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

        libraries = new Credit(getAssetManager(), "LIBRARIES", LIBRARIES);
        libraries.addLink("libGDX", "https://libgdx.badlogicgames.com");
        libraries.addLink("libGDX on GitHub", "https://github.com/libgdx/libgdx");
        libraries.addLink("Apache License 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

        tools = new Credit(getAssetManager(), "TOOLS", TOOLS);
        tools.addLink("Hiero", "https://libgdx.badlogicgames.com/tools.html");
        tools.addLink("libGDX Fonts", "http://www.badlogicgames.com/wordpress/?p=1247");
        tools.addLink("Sfxr", "http://www.drpetter.se/project_sfxr.html");
        tools.addLink("Gdx Texture Packer", "https://code.google.com/archive/p/libgdx-texturepacker-gui/");
        tools.addLink("Aurelien Ribon's blog", "http://www.aurelienribon.com/blog/about/");

        thanks = new Credit(getAssetManager(), "THANKS", THANKS);

        add(authors);
        add(license);
        add(resources);
        add(libraries);
        add(tools);
        add(thanks);
    }

}
