package com.jmolina.orb.managers;

// import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jmolina.orb.assets.Asset;

public class AssetManager extends com.badlogic.gdx.assets.AssetManager {



    public static final Class ASSET_CLASS = Asset.class;


    /**
     * Constructor
     */
    public AssetManager() {
        super();
    }

    /**
     * Preload assets needed for Load screen
     *
     * load: preload
     * finishLoading: actual load
     */
    public void loadLoadScreenAssets() {
        load(Asset.UI_BACKGROUND, Texture.class);
        load(Asset.UI_SPLASH, Texture.class);
        load(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);
        load(Asset.UI_PROGRESS_BASE, Texture.class);
        load(Asset.UI_PROGRESS_FILL, Texture.class);
        finishLoading();
    }

    /**
     * Precarga todos los assets en una clase usando reflection
     * @param c Class
     */
    public void preloadAll(Class c) {
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

                if (name.endsWith(".fnt")) {
                    load(field.get(c).toString(), BitmapFont.class);
                }
                else if (name.endsWith(".music.mp3")) {
                    load(field.get(c).toString(), Music.class);
                }
                else if (name.endsWith(".mp3")) {
                    load(field.get(c).toString(), Sound.class);
                }
                else if (name.endsWith(".png")) {
                    TextureLoader.TextureParameter parameter = new TextureLoader.TextureParameter();
                    parameter.genMipMaps = true;
                    parameter.minFilter = Texture.TextureFilter.Linear;
                    parameter.magFilter = Texture.TextureFilter.Linear;
                    load(field.get(c).toString(), Texture.class, parameter);
                }

            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }




}
