package com.jmolina.orb.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public abstract class UIAction {

    private static final float SCALE_FACTOR = 1.35f;
    private static final float LARGE = SCALE_FACTOR;
    private static final float SMALL = 1 / SCALE_FACTOR;

    public static final float DURATION = 0.3f;
    public static final Interpolation IN = Interpolation.pow2In;
    public static final Interpolation OUT = Interpolation.pow2Out;

    static public final Action toOutside() {
        return new SequenceAction(
                parallel(
                        fadeOut(DURATION, IN),
                        scaleTo(LARGE, LARGE, DURATION, IN)
                )
        );
    }

    static public final Action toInside() {
        return new SequenceAction(
                parallel(
                        fadeOut(DURATION, IN),
                        scaleTo(SMALL, SMALL, DURATION, IN)
                )
        );
    }

    static public final Action fromInside() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(SMALL, SMALL),
                parallel(
                        fadeIn(DURATION, OUT),
                        scaleTo(1f, 1f, DURATION, OUT)
                )
        );
    }

    static public final Action fromOutside() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(LARGE, LARGE),
                parallel(
                        fadeIn(DURATION, OUT),
                        scaleTo(1f, 1f, DURATION, OUT)
                )
        );
    }

    static public final Action appear() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(LARGE, LARGE),
                fadeIn(DURATION, IN)
        );
    }

    static public final Action disappear() {
        return new SequenceAction(
                alpha(1f),
                scaleTo(1f, 1f),
                fadeOut(DURATION, IN)
        );
    }

    static public final Action reset() {
        return new SequenceAction(
                alpha(1f),
                scaleTo(1f, 1f)
        );
    }

    static public final Action blink() {
        return new SequenceAction(
                alpha(0f),
                fadeIn(0.5f),
                fadeOut(0.5f),
                fadeIn(0.5f)
        );
    }


}
