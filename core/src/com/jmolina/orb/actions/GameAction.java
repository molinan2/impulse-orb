package com.jmolina.orb.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.jmolina.orb.elements.Orb;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

public class GameAction {

    public static final float DURATION = 0.3f;
    public static final float INTRO_SEQUENCE_TIME = 0.5f;
    public static final Interpolation interpolation = Interpolation.pow2In;

    static public Action orbIntro(Orb orb) {
        return new SequenceAction(
                parallel(
                        scaleBy(4 * orb.getNaturalScale(), 4 * orb.getNaturalScale(), 0),
                        rotateTo(0, 0),
                        alpha(0)
                ),
                parallel(
                        rotateTo(360, INTRO_SEQUENCE_TIME, Interpolation.exp5),
                        scaleTo(orb.getNaturalScale(), orb.getNaturalScale(), INTRO_SEQUENCE_TIME, Interpolation.pow2),
                        fadeIn(INTRO_SEQUENCE_TIME, Interpolation.pow2)
                )
        );
    }

}
