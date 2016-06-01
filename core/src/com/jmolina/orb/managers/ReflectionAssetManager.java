package com.jmolina.orb.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class ReflectionAssetManager extends AssetManager {

    public ReflectionAssetManager() {
        super();
    }

    /**
     * Dynamic assets load using reflection
     * @param c Class
     */
    public void autoload(Class c) {
        for (Field field : ClassReflection.getFields(c)) {
            try {
                System.out.println("Loaded: " + field.get(c).toString());

                String name = field.get(c).toString();
                String[] splits = name.split("\\.");
                String extension = splits[splits.length-1];

                if (extension.equals("fnt")) {
                    this.load(field.get(c).toString(), BitmapFont.class);
                }
                else if (extension.equals("png")) {
                    this.load(field.get(c).toString(), Texture.class);
                }

            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

}
