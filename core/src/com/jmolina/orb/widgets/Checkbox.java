package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Checkbox extends Base {

    private Image checkedImage;
    private Image uncheckedImage;
    private boolean checked;

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

        // Necesario para que solo el Checkbox responda a eventos
        // Asi se puede identificar su clase en MultiOption
        checkedImage.setTouchable(Touchable.disabled);
        uncheckedImage.setTouchable(Touchable.disabled);
        setTouchable(Touchable.enabled);
    }

    public void check() {
        checkedImage.clearActions();
        checkedImage.addAction(fadeIn(0));
        uncheckedImage.clearActions();
        uncheckedImage.addAction(fadeOut(0));
        checked = true;
    }

    public void uncheck() {
        uncheckedImage.clearActions();
        uncheckedImage.addAction(fadeIn(0));
        checkedImage.clearActions();
        checkedImage.addAction(fadeOut(0));
        checked = false;
    }

    public void toggle() {
        if (isChecked()) uncheck();
        else check();
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
