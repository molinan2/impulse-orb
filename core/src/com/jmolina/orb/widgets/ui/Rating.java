package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

public class Rating extends BaseGroup {

    private final String BRONZE = "Bronze!";
    private final String SILVER = "Silver!";
    private final String GOLD = "Gold!";
    private final String DEVELOPER = "You beat the developer!";

    private ArrayList<Image> points;
    private Group rating;
    private Heading heading;

    public Rating(AssetManager am, int numericRating) {
        super(am);

        numericRating = MathUtils.clamp(numericRating, 1, 4);

        rating = new Group();
        heading = new Heading(getAssetManager(), getText(numericRating), Align.center, Heading.Weight.Regular, Var.COLOR_BLACK);
        points = new ArrayList<Image>();

        for (int i=0; i<4; i++) {
            if (i < numericRating) {
                points.add(new Image(getAsset(Asset.UI_RATING_YES, Texture.class)));
            }
            else {
                points.add(new Image(getAsset(Asset.UI_RATING_NO, Texture.class)));
            }
        }

        for (int i=0; i<4; i++) {
            points.get(i).setPosition(i * Utils.cell(1.25f), 0);
            rating.addActor(points.get(i));
        }

        rating.setPosition(Utils.cell(2.75f), Utils.cell(0));
        heading.setPosition(Utils.cell(0), Utils.cell(1));

        addActor(rating);
        addActor(heading);
        setSize(Utils.cell(10), Utils.cell(2));
    }

    private String getText(int rating) {
        if (rating == 1) return BRONZE;
        else if (rating == 2) return SILVER;
        else if (rating == 3) return GOLD;
        else if (rating == 4) return DEVELOPER;
        else return "";
    }

}
