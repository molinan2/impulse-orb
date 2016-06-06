package com.jmolina.orb.stages;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BackableStage extends Stage {

    private Runnable operation;

    public BackableStage(Viewport viewport, Runnable operation) {
        super(viewport);
        this.operation = operation;
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == Input.Keys.BACK) {
            operation.run();
        }

        return super.keyUp(keyCode);
    }

}
