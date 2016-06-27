package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jmolina.orb.elements.BaseActor;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.LevelBaseScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GestureStage extends Stage {

    private final float PIXELS_PER_METER = 1 / LevelBaseScreen.RATIO_METER_PIXEL;
    private final float VIEWPORT_HEIGHT = BaseScreen.VIEWPORT_HEIGHT;

    private BaseActor base;
    private BaseActor line;
    private BaseActor arrow;
    private Vector2 start;
    private Vector2 end;
    private float time;

    public GestureStage(AssetManager am) {
        super();

        time = 0f;
        start = new Vector2();
        end = new Vector2();

        base = new BaseActor(am.get(Asset.GAME_GESTURE_BASE, Texture.class));
        base.setScale(PIXELS_PER_METER / base.getWidth(), PIXELS_PER_METER / base.getHeight());
        base.setVisible(false);

        line = new BaseActor(am.get(Asset.GAME_GESTURE_LINE, Texture.class));
        line.setScale(PIXELS_PER_METER / line.getWidth(), PIXELS_PER_METER / line.getHeight() / 8f);
        line.setVisible(false);

        arrow = new BaseActor(am.get(Asset.GAME_GESTURE_ARROW, Texture.class));
        arrow.setScale(PIXELS_PER_METER / arrow.getWidth(), PIXELS_PER_METER / arrow.getHeight());
        arrow.setVisible(false);

        addActor(base);
        addActor(line);
        addActor(arrow);
    }

    public void drawGesture() {
        // time += Gdx.graphics.getDeltaTime();

        base.setPosition(start.x - 0.5f * base.getWidth(), start.y - 0.5f * base.getHeight());

        arrow.setPosition(end.x - 0.5f * arrow.getWidth(), end.y - 0.5f * arrow.getHeight());
        Vector2 direction = new Vector2(end.x - start.x, end.y - start.y);
        float angle = MathUtils.atan2(direction.y, direction.x);
        arrow.setRotation(angle * MathUtils.radiansToDegrees);

        line.setRotation(angle * MathUtils.radiansToDegrees);
        float distance = (float) Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
        line.setScaleX(distance / line.getWidth());
        line.setPosition(0.5f * (base.getX() + arrow.getX()), 0.5f * (base.getY() + arrow.getY()));

        base.setVisible(true);
        line.setVisible(true);
        arrow.setVisible(true);

        getRoot().clearActions();
        getRoot().addAction(sequence(
                alpha(1),
                fadeOut(0.75f)
        ));
    }

    /**
     * InputProcessor methods
     */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        start.set(screenX, VIEWPORT_HEIGHT - screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        end.set(screenX, VIEWPORT_HEIGHT - screenY);
        return false;
    }

}
