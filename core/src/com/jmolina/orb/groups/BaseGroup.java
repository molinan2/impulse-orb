package com.jmolina.orb.groups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.var.Util;

public class BaseGroup extends Group {

    public BaseGroup() {
        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void setGridPosition(int xGrid, int yGrid) {
        this.setPosition(Util.xGrid(xGrid), Util.yGrid(yGrid));
    }

    public void setGridPosition(float xGrid, float yGrid) {
        this.setPosition(Util.xGrid(xGrid), Util.yGrid(yGrid));
    }

    public void animateOutside() {
        clearActions();
        addAction(UIAction.toOutside());
    }

    public void animateBounce() {
        clearActions();
        addAction(UIAction.bounce());
    }
}
