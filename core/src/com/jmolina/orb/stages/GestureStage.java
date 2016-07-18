package com.jmolina.orb.stages;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.widgets.Fling;
import com.jmolina.orb.widgets.Tap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GestureStage extends Stage {

    private Tap tap;
    private Fling fling;
    private Vector2 start, end;

    public GestureStage(AssetManager am, Viewport vp, float pixelsPerMeter) {
        super(vp);

        start = new Vector2();
        end = new Vector2();
        fling = new Fling(am, pixelsPerMeter);
        tap = new Tap(am, pixelsPerMeter);

        tap.setPosition(
                0.5f * vp.getWorldWidth() - 0.5f * tap.getWidth(),
                0.5f * vp.getWorldHeight() - 0.5f * tap.getHeight()
        );

        tap.reset();
        fling.reset();

        addActor(tap);
        addActor(fling);
    }

    public void drawFling() {
        tap.reset();
        fling.set(start, end);
        fling.start();
    }

    public void drawTap() {
        fling.reset();
        tap.start();
    }


    /**
     * InputProcessor methods
     */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 vector = new Vector3(screenX, screenY, 0);
        getCamera().unproject(vector);
        start.set(vector.x, vector.y);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 vector = new Vector3(screenX, screenY, 0);
        getCamera().unproject(vector);
        end.set(vector.x, vector.y);

        return false;
    }

}
