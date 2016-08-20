package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Rating extends BaseGroup {

    public final static int MIN = 0;
    public final static int MAX = 4;

    private final String NICE_TRY = "Nice try";
    private final String BRONZE = "Bronze!";
    private final String SILVER = "Silver!";
    private final String GOLD = "Gold!";
    private final String DEVELOPER = "You have beaten the developer!";

    private ArrayList<Image> medals;
    private Group rating;
    private Heading heading;

    public Rating(AssetManager am, int numericRating) {
        super(am);

        rating = new Group();
        heading = new Heading(getAssetManager(), getText(numericRating), Align.center, Heading.Weight.Regular, Var.COLOR_LILAC_DARK);
        medals = new ArrayList<Image>();

        for (int i=MIN; i<MAX; i++) {
            if (i < numericRating) {
                medals.add(new Image(getAsset(Asset.UI_RATING_YES, Texture.class)));
                medals.get(i).addAction(forever(sequence(
                        alpha(0.5f, 0.25f),
                        alpha(1f, 0.25f),
                        delay(1f)
                )));
            }
            else {
                medals.add(new Image(getAsset(Asset.UI_RATING_NO, Texture.class)));
            }

            medals.get(i).setPosition(i * Utils.cell(1.25f), Utils.cell(0));
            rating.addActor(medals.get(i));
        }

        rating.setPosition(Utils.cell(2.75f), Utils.cell(0));
        heading.setPosition(Utils.cell(0), Utils.cell(1));

        addActor(rating);
        addActor(heading);
        setSize(Utils.cell(10), Utils.cell(2));
    }

    private String getText(int rating) {
        rating = MathUtils.clamp(rating, 0, 4);

        if (rating == 0) return NICE_TRY;
        else if (rating == 1) return BRONZE;
        else if (rating == 2) return SILVER;
        else if (rating == 3) return GOLD;
        else if (rating == 4) return DEVELOPER;
        else return "";
    }

    public void setHeadingVisibility(boolean visibility) {
        heading.setVisible(visibility);
    }

}
