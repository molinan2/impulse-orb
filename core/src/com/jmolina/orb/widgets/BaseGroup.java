package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.utils.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class BaseGroup extends Group {

    private AssetManager assetManager;
    private Image frame;

    public BaseGroup(AssetManager am) {
        setAssetManager(am);
        setTransform(true); // Transform=false improves drawing performance
        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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

    /**
     * Activa el efecto post-click.
     */
    public void clickEffect() {
        if (frame != null) {
            frame.addAction(sequence(
                    alpha(1),
                    alpha(0, 0.2f)
            ));
        }
    }

    /**
     * Actor con alpha = 0 sobre el que se ejecutará una animación al hacer click en el objeto
     *
     * @param frame
     */
    public void setFrame(Image frame) {
        this.frame = frame;
    }

    public void setPositionGrid (float x, float y) {
        setPosition(Utils.cell(x), Utils.cell(y));
    }

}
