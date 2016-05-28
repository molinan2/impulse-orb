package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;

public class Checkbox extends Image implements Disposable {

    private Texture checkedTexture;
    private Texture uncheckedTexture;
    private TextureRegionDrawable checkedDrawable;
    private TextureRegionDrawable uncheckedDrawable;
    private boolean checked;

    public Checkbox () {
        this(true);
    }

    public Checkbox(boolean checked) {
        checkedTexture = new Texture(Gdx.files.internal("checked.png"));
        uncheckedTexture = new Texture(Gdx.files.internal("unchecked.png"));
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
