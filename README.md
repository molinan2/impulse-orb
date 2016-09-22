# Impulse Orb #

What's this?
-

Impulse Orb is a simple platformer game with a touch of puzzle game, controlled by touch gestures. The goal is to guide a small ball called "Orb" to the top of each level as soon as possible. You will find a variety of obstacles: platforms, magnets, hot zones, moving doors and destroyers. To avoid them, accelerate using the 'fling' gesture and make the "Orb" bounce through the walls and platforms. If you feel like losing control of the Orb, freeze it with a 'tap' gesture.

How to install
-

You can install the original game from [Google Play](https://play.google.com/store/apps/details?id=com.jmolina.orb). The experience is specifically tuned for Nexus 4, although it should work on most Android 4.2+ devices.

How to compile
-

This project uses Gradle. You should be able to import the project in Android Studio and then let the Gradle plugin work out the dependencies. The project includes Android and Desktop modules.

Online play is not mandatory and is disabled in the desktop version. If you want to compile your own APK, you'll need to provide a valid App ID and Google Play Games keys in order to integrate the game with online services. That includes creating your own Google Developer and Play Games account with all the achievements and leaderboards. Or just deleting them from the code...

About author
-

Impulse Orb is the result of a personal hobby-project and is my first libGDX game as a lone programmer. I hope you can find the code useful, but keep in mind that it's far from perfect ;). You can contact me through [GitHub](https://github.com/molinan2) or through [my blog](https://drefactor.wordpress.com/).

Licenses
-

This software is Free Software licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html).

Original resources are free to use, modify and share, and are licensed under [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/).

3rd party resources licenses
-

All music themes composed by [Benjamin Tissot](http://www.bensound.com/) and licensed under [CC BY-ND 3.0](https://creativecommons.org/licenses/by-nd/3.0/legalcode). Music themes are "[Funky element](http://www.bensound.com/royalty-free-music/track/funky-element)", "[The lounge](http://www.bensound.com/royalty-free-music/track/the-lounge)" and "[Pop dance](http://www.bensound.com/royalty-free-music/track/pop-dance)". [Padlock icon](http://www.flaticon.com/free-icon/padlock_25239#term=lock&page=1&position=51) designed by Dave Gandy for [FLATICON](http://www.flaticon.com/) and licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/). [Roboto font](https://www.google.com/fonts/specimen/Roboto) designed by Christian Robertson for Google and licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Libraries
-

This application makes use of [libGDX](https://libgdx.badlogicgames.com), a game-development framework created by Mario Zechner and Nathan Sweet and maintained by [the community](https://github.com/libgdx/libgdx). libGDX is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Connection to Google Play Services with the help of [BaseGameUtils](https://github.com/playgameservices/android-basic-samples/tree/master/BasicSamples/libraries/BaseGameUtils), a set of utils created by Google to help manage connection process and exception handling, licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Tools
-

Fonts rendered with [Hiero](https://libgdx.badlogicgames.com/tools.html), a Bitmap Font converter/creator by Kevin Glass and adapted by [Nathan Sweet](http://www.badlogicgames.com/wordpress/?p=1247).

UI and ingame sound effects designed with [Sfxr](http://www.drpetter.se/project_sfxr.html), a retro sound effects synthesizer by Tomas Pettersson.

Texture atlas packed with [GDX Texture Packer](https://code.google.com/archive/p/libgdx-texturepacker-gui/), an efficient image packing tool by [Aurelien Ribon](http://www.aurelienribon.com/blog/about/).