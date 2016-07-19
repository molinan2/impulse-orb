package com.jmolina.orb.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.jmolina.orb.elements.Orb;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

public class GameAction {

    public static final float DURATION = 0.3f;
    public static final float ORB_INTRO_TIME = 1f;
    public static final float ORB_OUTRO_TIME = 1.2f;
    public static final Interpolation interpolation = Interpolation.pow2In;

    static public Action orbIntro(Orb orb) {
        return new SequenceAction(
                parallel(
                        scaleBy(4 * orb.getNaturalScale(), 4 * orb.getNaturalScale(), 0),
                        rotateTo(0, 0),
                        alpha(0)
                ),
                parallel(
                        rotateTo(360, ORB_INTRO_TIME, Interpolation.exp5),
                        scaleTo(orb.getNaturalScale(), orb.getNaturalScale(), ORB_INTRO_TIME, Interpolation.pow2),
                        fadeIn(ORB_INTRO_TIME, Interpolation.pow2)
                )
        );
    }

    static public Action orbOutro(Orb orb, Runnable switchToSuccess) {
        return new SequenceAction(
                parallel(
                        rotateBy(1080, ORB_OUTRO_TIME, Interpolation.pow2Out),
                        scaleTo(4 * orb.getNaturalScale(), 4 * orb.getNaturalScale(), ORB_OUTRO_TIME, Interpolation.pow2),
                        fadeOut(ORB_OUTRO_TIME, Interpolation.pow2)
                ),
                run(switchToSuccess)
        );
    }

}
