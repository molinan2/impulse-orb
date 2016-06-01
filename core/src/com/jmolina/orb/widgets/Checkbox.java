package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.jmolina.orb.var.Asset;

/**
 * TODO
 * Reformar
 * Que sea un widget (BaseWidget) compuesto de 2 imagenes: la base y la "marca" superpuesta
 */
public class Checkbox extends Image implements Disposable {

    private Texture checkedTexture;
    private Texture uncheckedTexture;
    private TextureRegionDrawable checkedDrawable;
    private TextureRegionDrawable uncheckedDrawable;
    private boolean checked;

    public Checkbox(boolean checked) {
        checkedTexture = new Texture(Gdx.files.internal(Asset.UI_CHECKBOX_CHECKED));
        uncheckedTexture = new Texture(Gdx.files.internal(Asset.UI_CHECKBOX_UNCHECKED));
        checkedDrawable = new TextureRegionDrawable(new TextureRegion(checkedTexture));
        uncheckedDrawable = new TextureRegionDrawable(new TextureRegion(uncheckedTexture));

        this.checked = checked;
        setDrawable(checkedDrawable);
        setScaling(Scaling.stretch);
        setAlign(Align.center);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void check() {
        setDrawable(checkedDrawable);
        checked = true;
    }

    public void uncheck() {
        setDrawable(uncheckedDrawable);
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
        checkedTexture.dispose();
        uncheckedTexture.dispose();
    }

}
