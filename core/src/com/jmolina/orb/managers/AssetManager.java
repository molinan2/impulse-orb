package com.jmolina.orb.managers;

import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jmolina.orb.var.Asset;

public class AssetManager extends com.badlogic.gdx.assets.AssetManager {

    private enum AssetType { FONT, MUSIC, SOUND, TEXTURE, TEXTURE_MIP, UNDEFINED }

    public static final Class ASSET_CLASS = Asset.class;
    public static final String GAME_ATLAS = "atlas/game.atlas";

    /**
     * Constructor
     */
    public AssetManager() {
        super();
    }

    /**
     * Preload assets needed for Load screen.
     *
     * {@link com.badlogic.gdx.assets.AssetManager#load(String, Class)}: does the preload.
     * {@link com.badlogic.gdx.assets.AssetManager#finishLoading()}: does the actual load.
     */
    public void loadLoadScreenAssets() {
        load(Asset.UI_BACKGROUND, Texture.class);
        load(Asset.UI_SPLASH_BODY, Texture.class);
        load(Asset.UI_SPLASH_REFLECTIONS, Texture.class);
        load(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);
        load(Asset.UI_PROGRESS_BASE, Texture.class);
        load(Asset.UI_PROGRESS_FILL, Texture.class);
        load(AssetManager.GAME_ATLAS, TextureAtlas.class);
        finishLoading();
    }

    public TextureAtlas getGameAtlas() {
        return get(GAME_ATLAS, TextureAtlas.class);
    }

    /**
     * Precarga todos los assets en una clase usando reflection
     *
     * @param clazz Class
     */
    public void preloadAll(Class clazz) {
        for (Field field : ClassReflection.getFields(clazz)) {
            try {
                // Fix: Salta la iteracion si el campo no es de tipo String (deben ser String).
                // Android Studio 2 añade un campo sintético para soportar su "Instant run",
                // provocando una NullPointerException al ejecutar la app en Android.
                if (!field.getType().toString().equals("class java.lang.String")) {
                    continue;
                }

                // Alternate fix: Salta la iteración si el campo es sintético (más arriesgado)
                // if (field.isSynthetic()) {
                //     continue;
                // }

                Object object = field.get(clazz);
                String name = object.toString();
                AssetType assetType = detectAssetType(name);

                switch (assetType) {
                    case FONT:
                        load(name, BitmapFont.class);
                        break;
                    case MUSIC:
                        load(name, Music.class);
                        break;
                    case SOUND:
                        load(name, Sound.class);
                        break;
                    case TEXTURE_MIP:
                        TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
                        param.genMipMaps = true;
                        param.minFilter = Texture.TextureFilter.Linear;
                        param.magFilter = Texture.TextureFilter.Linear;
                        load(name, Texture.class, param);
                        break;
                    case TEXTURE:
                        load(name, Texture.class);
                        break;
                    default:
                }
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

    private AssetType detectAssetType(String name) {
        boolean font = name.endsWith(".fnt");
        boolean music = name.endsWith(".music.mp3") || name.endsWith(".music.ogg");
        boolean sound = name.endsWith(".mp3");
        boolean textureMip = name.endsWith(".mip.png");
        boolean texture = name.endsWith(".png");

        if (font) return AssetType.FONT;
        else if (music) return AssetType.MUSIC;
        else if (sound) return AssetType.SOUND;
        else if (textureMip) return AssetType.TEXTURE_MIP;
        else if (texture) return AssetType.TEXTURE;
        else return AssetType.UNDEFINED;
    }

}
