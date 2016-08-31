package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.jmolina.orb.var.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Grupo de actores que sirve como base para la creacion de widgets.
 */
public class BaseGroup extends Group {

    private AssetManager assetManager;

    /** Marco exterior opcional para el efecto de click */
    private Image frame;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public BaseGroup(AssetManager am) {
        assetManager = am;
        setTransform(true); // Transform=false improves drawing performance
        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);
    }

    /**
     * Devuelve el AssetManager
     */
    protected AssetManager getAssetManager() {
        return this.assetManager;
    }

    /**
     * Devuelve un asset determinado
     *
     * @param fileName Descriptor del asset
     * @param type Tipo de asset
     */
    protected synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    /**
     * Devuelve la region de textura solicitada
     *
     * @param name Descriptor de la region de textura
     */
    protected TextureRegion findRegion(String name) {
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

    /**
     * Fija la posicion del grupo en unidades del grid
     *
     * @param x Coordenada X de la posicion en unidades del grid
     * @param y Coordenada Y de la posicion en unidades del grid
     */
    public void setPositionGrid (float x, float y) {
        setPosition(Utils.cell(x), Utils.cell(y));
    }

}
