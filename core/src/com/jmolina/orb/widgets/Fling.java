package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.actors.BaseActor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Fling extends BaseGroup {

    private final float LINE_WIDTH_RATIO = 0.125f;

    private BaseActor base;
    private BaseActor line;
    private BaseActor arrow;

    public Fling(AssetManager am, float pixelsPerMeter) {
        super(am);

        base = new BaseActor(getAsset(Asset.GAME_GESTURE_BASE, Texture.class));
        line = new BaseActor(getAsset(Asset.GAME_GESTURE_LINE, Texture.class));
        arrow = new BaseActor(getAsset(Asset.GAME_GESTURE_ARROW, Texture.class));

        base.setScale(pixelsPerMeter / base.getWidth());

        line.setScale(
                pixelsPerMeter / line.getWidth(),
                LINE_WIDTH_RATIO * pixelsPerMeter / line.getHeight()
        );

        arrow.setScale(pixelsPerMeter / arrow.getWidth());

        addActor(base);
        addActor(line);
        addActor(arrow);
        setTransform(false);
    }

    public void set(Vector2 start, Vector2 end) {
        Vector2 direction;
        float angle, distance;

        base.setPosition(
                start.x - 0.5f * base.getWidth(),
                start.y - 0.5f * base.getHeight()
        );

        arrow.setPosition(
                end.x - 0.5f * arrow.getWidth(),
                end.y - 0.5f * arrow.getHeight()
        );

        direction = new Vector2(end.x - start.x, end.y - start.y);
        angle = MathUtils.radiansToDegrees * MathUtils.atan2(direction.y, direction.x);
        distance = (float) Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));

        arrow.setRotation(angle);
        line.setRotation(angle);
        line.setScaleX(distance / line.getWidth());

        line.setPosition(
                0.5f * (base.getX() + arrow.getX()),
                0.5f * (base.getY() + arrow.getY())
        );
    }

    public void start() {
        reset();
        addAction(alpha(0));
        addAction(sequence(
                fadeIn(0),
                fadeOut(0.75f)
        ));
    }

    public void reset() {
        clearActions();
        addAction(alpha(0));
    }

}
