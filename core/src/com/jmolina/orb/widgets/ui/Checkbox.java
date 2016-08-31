package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Una checkbox interactiva
 */
public class Checkbox extends BaseGroup {

    /** Imagenes marcada y desmarcada */
    private Image checkedImage, uncheckedImage;

    /** Estado de la checkbox */
    private boolean checked;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param checked Estado
     */
    public Checkbox(AssetManager am, boolean checked) {
        super(am);

        checkedImage = new Image(getAsset(Asset.UI_CHECKBOX_CHECKED, Texture.class));
        checkedImage.setPosition(0f, 0f);

        uncheckedImage = new Image(getAsset(Asset.UI_CHECKBOX_UNCHECKED, Texture.class));
        uncheckedImage.setPosition(0f, 0f);

        this.checked = checked;

        addActor(uncheckedImage);
        addActor(checkedImage);

        setHeight(checkedImage.getHeight());
        setWidth(checkedImage.getWidth());

        // Solo el Grupo responde a eventos. Asi se identifica su clase en MultiOption.
        checkedImage.setTouchable(Touchable.disabled);
        uncheckedImage.setTouchable(Touchable.disabled);
        setTouchable(Touchable.enabled);
    }

    /**
     * Marca la checkbox
     */
    public void check() {
        checkedImage.clearActions();
        checkedImage.addAction(fadeIn(0));
        uncheckedImage.clearActions();
        uncheckedImage.addAction(fadeOut(0));
        checked = true;
    }

    /**
     * Desmarca la checkbox
     */
    public void uncheck() {
        uncheckedImage.clearActions();
        uncheckedImage.addAction(fadeIn(0));
        checkedImage.clearActions();
        checkedImage.addAction(fadeOut(0));
        checked = false;
    }

    /**
     * Cambia el estado de la checkbox
     */
    public void toggle() {
        if (isChecked()) uncheck();
        else check();
    }

    /**
     * Indica si esta marcada o no
     */
    public boolean isChecked() {
        return checked;
    }

}
