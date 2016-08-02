package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.jmolina.orb.interfaces.Reseteable;
import com.jmolina.orb.var.Asset;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Elemento calentador
 *
 * TODO
 * Extender de Movable
 * Cambiar textura: que sea lisa
 * Que las proporciones sea variables (w, h)
 */
public class Heater extends Element implements Reseteable {

    private final float TICK_AMOUNT = 0.2f;
    private final float TICK_PERIOD = 0.8f;
    private final float BLINK_TIME = 0.66f;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento etéreo calentador de dimensiones fijas: 10x10.
     */
    public Heater(AssetManager am, World world, float ppm, float w, float h, float x, float y) {
        super(am, world, am.get(Asset.GAME_HEAT, Texture.class), ppm,
                Geometry.SQUARE, Flavor.BLUE,
                w, h, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.HEAT);
        getUserData().tick.amount = TICK_AMOUNT;
        getUserData().tick.period = TICK_PERIOD;
        getActor().addAction(blinkAction());
    }

    @Override
    public void reset() {
        getActor().clearActions();
        getActor().addAction(alpha(1));
        getActor().act(0);
        getActor().addAction(blinkAction());
    }

    private Action blinkAction() {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                alpha(1, BLINK_TIME, Interpolation.pow2),
                alpha(0.4f, BLINK_TIME, Interpolation.pow2)
        ));

        return forever;
    }

}
