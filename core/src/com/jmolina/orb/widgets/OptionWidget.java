package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.actors.Checkbox;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.var.Var;

public class OptionWidget extends BaseGroup implements Disposable {

    private Image text;
    private Checkbox checkbox;

    public OptionWidget(Texture textTexture) {
        this(textTexture, true);
    }

    public OptionWidget(Texture textTexture, boolean checked) {
        text = new Image(textTexture);
        text.setPosition(0f, 0f);
        text.setTouchable(Touchable.disabled);

        checkbox = new Checkbox();
        checkbox.setPosition(8.5f * Var.GRID_UNIT, 0f);

        addActor(text);
        addActor(checkbox);
        setHeight(1.5f * Var.GRID_UNIT);

        setChecked(checked);
    }

    public void toggleCheckbox() {
        checkbox.toggle();
    }

    public boolean isChecked() {
        return checkbox.isChecked();
    }

    public void setChecked(boolean checked) {
        if (checked) checkbox.check();
        else checkbox.uncheck();
    }

    @Override
    public void dispose() {
        checkbox.dispose();
    }
}
