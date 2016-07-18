package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.interfaces.Reseteable;
import com.jmolina.orb.utils.Grid;

public class BaseGroup extends Group implements Reseteable, Disposable {

    public static final int COLOR_WHITE = 0xffffffff;
    public static final int COLOR_BLUE = 0x2E3192ff;
    public static final int COLOR_BLACK = 0x4D4D4Dff;

    private ShaderProgram shader;
    private float brightness;

    private AssetManager assetManager;

    public BaseGroup(AssetManager am) {
        setAssetManager(am);

        // Disabling Transform (scale, rotate) improves drawing performance
        // setTransform(false);

        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);

        brightness = 0.0f;
        shader = new ShaderProgram(Gdx.files.internal("shader/brightness.vert"), Gdx.files.internal("shader/brightness.frag"));
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        // TODO
        // Soporte preliminar click feedback con shader.
        // Solo se renderiza el shader cuando brightness > 0
        if (brightness > 0) {
            float variance = 0.03f;
            brightness = MathUtils.clamp(brightness - variance, 0f, 1f);
            batch.setShader(shader);
            shader.begin();
            shader.setUniformf("brightness", brightness);
            super.draw(batch, parentAlpha);
            shader.end();
            batch.setShader(null);
        }
        else {
            super.draw(batch, parentAlpha);
        }
    }

    @Override
    public void reset() {

    }

    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    public void setAssetManager(AssetManager am) {
        this.assetManager = am;
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    @Override
    public void dispose() {
        shader.dispose();
    }

    public void clickEffect() {
        // TODO
        // Soporte preliminar click feedback con shader
        this.brightness = 0.5f;
    }

    public void setPositionGrid (float x, float y) {
        setPosition(Grid.unit(x), Grid.unit(y));
    }

}
