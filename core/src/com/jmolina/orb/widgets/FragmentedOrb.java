package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;


/**
 * Fragments:
 *
 * 1: Top left
 * 2: Top right
 * 3: Bottom left
 * 4: Bottom right
 */
public class FragmentedOrb extends BaseGroup {

    private Image fragment1;
    private Image fragment2;
    private Image fragment3;
    private Image fragment4;

    public FragmentedOrb(AssetManager am) {
        super(am);

        fragment1 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_1, Texture.class));
        fragment2 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_2, Texture.class));
        fragment3 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_3, Texture.class));
        fragment4 = new Image(getAsset(Asset.GAME_ORB_FRAGMENT_4, Texture.class));

        reset();

        addActor(fragment1);
        addActor(fragment2);
        addActor(fragment3);
        addActor(fragment4);

        setTransform(true);
        setSize(Grid.unit(1), Grid.unit(1));
        setOrigin(getWidth() * 0.5f, getHeight() * 0.5f);
        setRotation(0);
        setScale(1, 1);
    }

    public float randomDistance() {
        return Grid.unit(4) * (float) (Math.random() - 0.5f);
    }

    public float randomAngle() {
        return 4 * 360 * (float) (2 * Math.random() - 1);
    }

    public void destroy() {
        addAction(parallel(
                run(new Runnable() {
                    @Override
                    public void run() {
                        fragment1.addAction(parallel(
                                Actions.rotateBy(randomAngle(), 2),
                                Actions.moveBy(randomDistance(), randomDistance(), 2)
                        ));

                        fragment2.addAction(parallel(
                                Actions.rotateBy(randomAngle(), 2),
                                Actions.moveBy(randomDistance(), randomDistance(), 2)
                        ));

                        fragment3.addAction(parallel(
                                Actions.rotateBy(randomAngle(), 2),
                                Actions.moveBy(randomDistance(), randomDistance(), 2)
                        ));

                        fragment4.addAction(parallel(
                                Actions.rotateBy(randomAngle(), 2),
                                Actions.moveBy(randomDistance(), randomDistance(), 2)
                        ));
                    }
                }),
                sequence(
                        delay(0.3f),
                        fadeOut(1f, Interpolation.pow2In)
                )
        ));
    }

    public void reset() {
        clearActions();
        addAction(alpha(1));

        fragment1.clearActions();
        fragment2.clearActions();
        fragment3.clearActions();
        fragment4.clearActions();

        fragment1.setPosition(Grid.unit(0), Grid.unit(0.5f));
        fragment2.setPosition(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment3.setPosition(Grid.unit(0f), Grid.unit(0f));
        fragment4.setPosition(Grid.unit(0.5f), Grid.unit(0));
        fragment1.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment2.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment3.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment4.setSize(Grid.unit(0.5f), Grid.unit(0.5f));
        fragment1.addAction(alpha(1));
        fragment2.addAction(alpha(1));
        fragment3.addAction(alpha(1));
        fragment4.addAction(alpha(1));
        fragment1.setRotation(0);
        fragment2.setRotation(0);
        fragment3.setRotation(0);
        fragment4.setRotation(0);
        fragment1.setOrigin(fragment1.getWidth()/2, fragment1.getHeight()/2);
        fragment2.setOrigin(fragment2.getWidth()/2, fragment2.getHeight()/2);
        fragment3.setOrigin(fragment3.getWidth()/2, fragment3.getHeight()/2);
        fragment4.setOrigin(fragment4.getWidth()/2, fragment4.getHeight()/2);

        // fragment1.setScale(scale);
        // fragment2.setScale(scale);
        // fragment3.setScale(scale);
        // fragment4.setScale(scale);
    };

}
