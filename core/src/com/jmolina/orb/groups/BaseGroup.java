package com.jmolina.orb.groups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.interfaces.Reseteable;
import com.jmolina.orb.utils.Grid;

public class BaseGroup extends Group implements Reseteable {

    public BaseGroup () {
        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void setGridPosition (int xGrid, int yGrid) {
        this.setPosition(Grid.cellX(xGrid), Grid.cellY(yGrid));
    }

    public void setGridPosition (float xGrid, float yGrid) {
        this.setPosition(Grid.cellX(xGrid), Grid.cellY(yGrid));
    }

    @Override
    public void reset() {

    }
}
