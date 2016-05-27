package com.jmolina.orb.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * No tiene sentido. Una accion no es una clase, es una instancia concreta
 * ¿Cómo hago entonces para guardar "presets" de acciones compuestas?
 * ¿No puedo crear una clase que almacene mis presets?
 * Sí: con una clase abstracta
 */
public abstract class UIAction {

    static public final Action bounce() {
        return sequence(
                scaleTo(Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_DURATION * 0.5f),
                scaleTo(1f, 1f, Var.ANIMATION_DURATION * 0.5f)
        );
    }


    static public final Action toOutside() {
        return new SequenceAction(sequence(
                parallel(
                        fadeOut(Var.ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_DURATION)
                )
        ));
    }

    static public final Action toInside() {
        return new SequenceAction(sequence(
                parallel(
                        fadeOut(Var.ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(1 / Var.ANIMATION_SCALE_FACTOR, 1 / Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_DURATION)
                )
        ));
    }

    static public final Action fromInside() {
        return new SequenceAction(sequence(
                fadeOut(0f),
                scaleTo(1 / Var.ANIMATION_SCALE_FACTOR, 1 / Var.ANIMATION_SCALE_FACTOR, 0f),
                parallel(
                        fadeIn(Var.ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(1f, 1f, Var.ANIMATION_DURATION)
                )
        ));
    }

    static public final Action fromOutside() {
        return new SequenceAction(sequence(
                fadeOut(0f),
                scaleTo(Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_SCALE_FACTOR, 0f),
                parallel(
                        fadeIn(Var.ANIMATION_DURATION, Interpolation.pow2),
                        scaleTo(1f, 1f, Var.ANIMATION_DURATION)
                )
        ));
    }

    static public final Action appear() {
        return new SequenceAction(sequence(
                fadeOut(0f),
                scaleTo(Var.ANIMATION_SCALE_FACTOR, Var.ANIMATION_SCALE_FACTOR, 0f),
                fadeIn(Var.ANIMATION_DURATION, Interpolation.pow2)
        ));
    }

    static public final Action disappear() {
        return new SequenceAction(sequence(
                fadeIn(0f),
                scaleTo(1f, 1f, 0f),
                fadeOut(Var.ANIMATION_DURATION, Interpolation.pow2)
        ));
    }

    static public final Action dummy() {
        return new SequenceAction(sequence(
                fadeIn(1f),
                scaleTo(1f, 1f, 0f))
        );
    }


}