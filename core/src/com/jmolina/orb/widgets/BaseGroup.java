package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.jmolina.orb.var.Utils;

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

    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    public void setAssetManager(AssetManager am) {
        this.assetManager = am;
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    public TextureRegion findRegion(String name) {
        return getAssetManager().getGameAtlas().findRegion(name);
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
    protected void setFrame(Image frame) {
        this.frame = frame;
    }

    public void setPositionGrid (float x, float y) {
        setPosition(Utils.cell(x), Utils.cell(y));
    }

}
