/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

/**
 * Manager de assets. Extiende del {@link com.badlogic.gdx.assets.AssetManager} de libGDX.
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {

    private enum AssetType { FONT, MUSIC, SOUND, TEXTURE, TEXTURE_MIP, UNDEFINED }

    /** Clase que almacena los assets para precarga */
    public static final Class ASSET_CLASS = Asset.class;

    /** Fichero que almacena los assets del atlas */
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
        load(Asset.UI_SPLASH_BODY, Texture.class);
        load(Asset.UI_SPLASH_REFLECTIONS, Texture.class);
        load(Asset.UI_PROGRESS_BASE, Texture.class);
        load(Asset.UI_PROGRESS_FILL, Texture.class);
        load(AssetManager.GAME_ATLAS, TextureAtlas.class);
        finishLoading();
    }

    /**
     * Devuelve el atlas
     */
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

    /**
     * Detecta el tipo de asset en funcion de su nombre de fichero
     *
     * @param name Nombre de fichero (descriptor)
     */
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
