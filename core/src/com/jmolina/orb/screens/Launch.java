package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.Ladder;
import com.jmolina.orb.widgets.ui.LaunchCover;
import com.jmolina.orb.widgets.ui.MainButton;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class Launch extends Menu {

    private LaunchCover cover;
    private MainButton button;
    private Ladder ladder;

    /**
     * Constructor
     *
     * @param superManager
     * @param level
     * @param title
     */
    public Launch(SuperManager superManager, final ScreenManager.Key level, String title) {
        super(superManager);

        setPreviousScreen(LEVEL_SELECT);
        setTitle(title);

        cover = new LaunchCover(getAssetManager(), getTexture(level));
        button = new MainButton(getAssetManager(), "GO!", MainButton.Type.SUCCESS);
        ladder = new Ladder(getAssetManager(), getPrefsManager(), getGameManager(), level, "Top times");

        add(cover);
        add(ladder);
        add(button, 1f, 8f);

        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(level, Hierarchy.LOWER);
            }
        });
    }

    private Texture getTexture(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: return getAsset(Asset.UI_LAUNCH_COVER_1, Texture.class);
            case LEVEL_2: return getAsset(Asset.UI_LAUNCH_COVER_2, Texture.class);
            case LEVEL_3: return getAsset(Asset.UI_LAUNCH_COVER_3, Texture.class);
            case LEVEL_4: return getAsset(Asset.UI_LAUNCH_COVER_4, Texture.class);
            case LEVEL_5: return getAsset(Asset.UI_LAUNCH_COVER_5, Texture.class);
            default: return getAsset(Asset.UI_LAUNCH_COVER_1, Texture.class);
        }
    }

}
