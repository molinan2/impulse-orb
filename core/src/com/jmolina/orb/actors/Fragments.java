package com.jmolina.orb.actors;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Fragments:
 *
 * 1: Top left
 * 2: Top right
 * 3: Bottom left
 * 4: Bottom right
 */
public class Fragments extends BaseGroup {

    private final float ANIMATION_TIME = 2.0f;

    private Image explosion;
    private Image fragment1;
    private Image fragment2;
    private Image fragment3;
    private Image fragment4;

    public Fragments(AssetManager am) {
        super(am);

        explosion = new Image(findRegion(Atlas.GAME_CIRCLE_RED));
        fragment1 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_1));
        fragment2 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_2));
        fragment3 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_3));
        fragment4 = new Image(findRegion(Atlas.GAME_ORB_FRAGMENT_4));

        addActor(explosion);
        addActor(fragment1);
        addActor(fragment2);
        addActor(fragment3);
        addActor(fragment4);

        explosion.addAction(alpha(0));

        setTransform(true);
        setSize(Utils.cell(1), Utils.cell(1));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setRotation(0);
        setScale(1);

        reset();
    }

    public float randomDistance() {
        return Utils.cell(4) * (float) (Math.random() - 0.5f);
    }

    public float randomAngle() {
        return 4 * 360 * (float) (2 * Math.random() - 1);
    }

    public void destroy() {
        explosion.addAction(sequence(
                alpha(0.4f),
                parallel(
                        scaleTo(3, 3, 0.3f, Interpolation.exp5Out),
                        fadeOut(0.3f, Interpolation.exp5Out)
                )
        ));
        fragment1.addAction(destruction());
        fragment2.addAction(destruction());
        fragment3.addAction(destruction());
        fragment4.addAction(destruction());

        addAction(sequence(
                delay(0.25f * ANIMATION_TIME),
                fadeOut(0.5f * ANIMATION_TIME, Interpolation.pow2In)
        ));
    }

    public void reset() {
        clearActions();
        addAction(alpha(1));

        explosion.clearActions();
        fragment1.clearActions();
        fragment2.clearActions();
        fragment3.clearActions();
        fragment4.clearActions();

        explosion.setPosition(Utils.cell(0), Utils.cell(0));
        fragment1.setPosition(Utils.cell(0), Utils.cell(0.5f));
        fragment2.setPosition(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment3.setPosition(Utils.cell(0f), Utils.cell(0f));
        fragment4.setPosition(Utils.cell(0.5f), Utils.cell(0));
        explosion.setSize(Utils.cell(1), Utils.cell(1));
        fragment1.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment2.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment3.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        fragment4.setSize(Utils.cell(0.5f), Utils.cell(0.5f));
        explosion.addAction(alpha(0));
        fragment1.addAction(alpha(1));
        fragment2.addAction(alpha(1));
        fragment3.addAction(alpha(1));
        fragment4.addAction(alpha(1));
        fragment1.setRotation(0);
        fragment2.setRotation(0);
        fragment3.setRotation(0);
        fragment4.setRotation(0);
        explosion.setOrigin(0.5f * explosion.getWidth(), 0.5f * explosion.getHeight());
        fragment1.setOrigin(0.5f * fragment1.getWidth(), 0.5f * fragment1.getHeight());
        fragment2.setOrigin(0.5f * fragment2.getWidth(), 0.5f * fragment2.getHeight());
        fragment3.setOrigin(0.5f * fragment3.getWidth(), 0.5f * fragment3.getHeight());
        fragment4.setOrigin(0.5f * fragment4.getWidth(), 0.5f * fragment4.getHeight());
        explosion.setScale(1.5f);
    };

    private Action destruction() {
        return new ParallelAction(
                Actions.rotateBy(randomAngle(), ANIMATION_TIME),
                Actions.moveBy(randomDistance(), randomDistance(), ANIMATION_TIME)
        );
    }

}
