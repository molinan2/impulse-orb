package com.jmolina.orb.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public abstract class UIAction {

    private static final float ANIMATION_DURATION = 0.2f;
    private static final float ANIMATION_SCALE_FACTOR = 1.35f;

    static public final Action toOutside() {
        return new SequenceAction(
                parallel(
                        fadeOut(ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(ANIMATION_SCALE_FACTOR, ANIMATION_SCALE_FACTOR, ANIMATION_DURATION)
                )
        );
    }

    static public final Action toInside() {
        return new SequenceAction(
                parallel(
                        fadeOut(ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(1 / ANIMATION_SCALE_FACTOR, 1 / ANIMATION_SCALE_FACTOR, ANIMATION_DURATION)
                )
        );
    }

    static public final Action fromInside() {
        return new SequenceAction(
                fadeOut(0f),
                scaleTo(1 / ANIMATION_SCALE_FACTOR, 1 / ANIMATION_SCALE_FACTOR, 0f),
                parallel(
                        fadeIn(ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(1f, 1f, ANIMATION_DURATION)
                )
        );
    }

    static public final Action fromOutside() {
        return new SequenceAction(
                fadeOut(0f),
                scaleTo(ANIMATION_SCALE_FACTOR, ANIMATION_SCALE_FACTOR, 0f),
                parallel(
                        fadeIn(ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(1f, 1f, ANIMATION_DURATION)
                )
        );
    }

    static public final Action appear() {
        return new SequenceAction(
                fadeOut(0f),
                scaleTo(ANIMATION_SCALE_FACTOR, ANIMATION_SCALE_FACTOR, 0f),
                fadeIn(ANIMATION_DURATION, Interpolation.pow2)
        );
    }

    static public final Action disappear() {
        return new SequenceAction(
                fadeIn(0f),
                scaleTo(1f, 1f, 0f),
                fadeOut(ANIMATION_DURATION, Interpolation.pow2)
        );
    }

    static public final Action dummy() {
        return new SequenceAction(
                fadeIn(1f),
                scaleTo(1f, 1f, 0f)
        );
    }

    static public final Action blink() {
        return new SequenceAction(
                alpha(1f),
                fadeOut(0),
                fadeIn(0.5f),
                fadeOut(0.5f),
                fadeIn(0.5f)
        );
    }


}
