package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.utils.Grid;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Fragments:
 *
 * 1: Top left
 * 2: Top right
 * 3: Bottom left
 * 4: Bottom right
 */
public class OrbFragments extends BaseGroup {

    private final float ANIMATION_TIME = 2.0f;

    private Image explosion;
    private Image fragment1;
    private Image fragment2;
    private Image fragment3;
    private Image fragment4;

    public OrbFragments(AssetManager am) {
        super(am);

        explosion = new Image(getAsset(Asset.GAME_CIRCLE_RED, Texture.class));
        fragment1 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_1, Texture.class));
        fragment2 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_2, Texture.class));
        fragment3 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_3, Texture.class));
        fragment4 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_4, Texture.class));

        addActor(explosion);
        addActor(fragment1);
        addActor(fragment2);
        addActor(fragment3);
        addActor(fragment4);

        explosion.addAction(alpha(0));

        setTransform(true);
        setSize(Grid.unit(1), Grid.unit(1));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setRotation(0);
        setScale(1);

        reset();
    }

    public float randomDistance() {
        return Grid.unit(4) * (float) (Math.random() - 0.5f);
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

        explosion.setPosition(Grid.unit(0), Grid.unit(0));
        fragment1.setPosition(Grid.unit(0), Grid.unit(0.5f));
        fragment2.setPosition(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment3.setPosition(Grid.unit(0f), Grid.unit(0f));
        fragment4.setPosition(Grid.unit(0.5f), Grid.unit(0));
        explosion.setSize(Grid.unit(1), Grid.unit(1));
        fragment1.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment2.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment3.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment4.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
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
