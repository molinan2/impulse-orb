package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jmolina.orb.var.Asset;

public class OrbAssetManager extends AssetManager {

    public OrbAssetManager() {
        super();
    }

    /**
     * Preload assets needed for Loading screen
     */
    public void loadLoadingScreenAssets() {
        load(Asset.UI_BACKGROUND, Texture.class);
        load(Asset.UI_SPLASH, Texture.class);
        load(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);
        load(Asset.UI_PROGRESS_BASE, Texture.class);
        load(Asset.UI_PROGRESS_FILL, Texture.class);
        finishLoading();
    }

    /**
     * Preload assets needed for Loading screen
     */
    public void preloadAllAssets() {
        autoload(Asset.class);
    }

    /**
     * Dynamic assets load using reflection
     * @param c Class
     */
    public void autoload(Class c) {
        for (Field field : ClassReflection.getFields(c)) {
            try {
                String name = field.get(c).toString();

                if (name.endsWith(".fnt"))
                    this.load(field.get(c).toString(), BitmapFont.class);
                else if (name.endsWith(".png"))
                    this.load(field.get(c).toString(), Texture.class);
            } catch (ReflectionException e) {
                Gdx.app.log(this.getClass().toString(), e.toString());
                e.printStackTrace();
            }
        }
    }

}
