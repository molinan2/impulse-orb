package com.jmolina.orb.widgets.game;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.actors.BaseActor;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

public class Arrow extends BaseGroup {

    private final float LINE_WIDTH_RATIO = 0.125f;

    private BaseActor base;
    private BaseActor line;
    private BaseActor arrowhead;

    public Arrow(AssetManager am, float pixelsPerMeter) {
        super(am);

        base = new BaseActor(findRegion(Atlas.GAME_GESTURE_BASE));
        line = new BaseActor(findRegion(Atlas.GAME_GESTURE_LINE));
        arrowhead = new BaseActor(findRegion(Atlas.GAME_GESTURE_ARROWHEAD));

        base.setScale(pixelsPerMeter / base.getWidth());

        line.setScale(
                pixelsPerMeter / line.getWidth(),
                LINE_WIDTH_RATIO * pixelsPerMeter / line.getHeight()
        );

        arrowhead.setScale(pixelsPerMeter / arrowhead.getWidth());

        addActor(base);
        addActor(line);
        addActor(arrowhead);
        setTransform(false);
    }

    public void set(Vector2 start, Vector2 end) {
        Vector2 direction;
        float angle, distance;

        base.setPosition(
                start.x - 0.5f * base.getWidth(),
                start.y - 0.5f * base.getHeight()
        );

        arrowhead.setPosition(
                end.x - 0.5f * arrowhead.getWidth(),
                end.y - 0.5f * arrowhead.getHeight()
        );

        direction = new Vector2(end.x - start.x, end.y - start.y);
        angle = MathUtils.radiansToDegrees * MathUtils.atan2(direction.y, direction.x);
        distance = (float) Math.sqrt(direction.x * direction.x + direction.y * direction.y);

        arrowhead.setRotation(angle);
        line.setRotation(angle);
        line.setScaleX(distance / line.getWidth());

        line.setPosition(
                0.5f * (base.getX() + arrowhead.getX()),
                0.5f * (base.getY() + arrowhead.getY())
        );
    }

}
