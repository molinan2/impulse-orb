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
    public void preloadAll(Class c) {
        autoload(c);
    }

    /**
     * Dynamic assets load using reflection
     * @param c Class
     */
    public void autoload(Class c) {
        for (Field field : ClassReflection.getFields(c)) {
            try {
                // Android Studio 2 añade un campo sintetico para soportar su "Instant run",
                // provocando una NullPointerException al ejecutar la app en Android.

                // Salta la iteracion si el campo no es de tipo String (deben ser String)
                if (!field.getType().toString().equals("class java.lang.String")) {
                    continue;
                }

                // Alternativa: Salta la iteracion si el campo es sintetico (mas arriesgado)
                // if (field.isSynthetic()) {
                //     continue;
                // }

                // NullPointerException al ejecutarse sobre un campo sintetico
                Object o = field.get(c);
                String name = o.toString();

                if (name.endsWith(".fnt"))
                    this.load(field.get(c).toString(), BitmapFont.class);
                else if (name.endsWith(".png"))
                    this.load(field.get(c).toString(), Texture.class);
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

}
