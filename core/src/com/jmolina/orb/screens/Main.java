/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.widgets.ui.GameTitle;
import com.jmolina.orb.widgets.ui.MainButton;
import com.jmolina.orb.widgets.ui.Notice;

import static com.jmolina.orb.managers.ScreenManager.Key.CREDITS;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_SELECT;
import static com.jmolina.orb.managers.ScreenManager.Key.OPTIONS;
import static com.jmolina.orb.managers.ScreenManager.Key.STATS;

/**
 * Pantalla de menu principal de la aplicacion
 */
public class Main extends BaseScreen {

    /** Titulo de la aplicacion */
    private GameTitle gameTitle;

    /** Botones del menu */
    private MainButton play, options, stats, credits, exit;

    /** Nota a pie de pantalla*/
    private Notice notice;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public Main(SuperManager superManager) {
        super(superManager);

        gameTitle = new GameTitle(getAssetManager(), getGameManager());
        play = new MainButton(getAssetManager(), "PLAY", MainButton.Type.SUCCESS);
        options = new MainButton(getAssetManager(), "OPTIONS", MainButton.Type.DEFAULT);
        stats = new MainButton(getAssetManager(), "STATS", MainButton.Type.DEFAULT);
        credits = new MainButton(getAssetManager(), "CREDITS", MainButton.Type.DEFAULT);
        exit = new MainButton(getAssetManager(), "EXIT", MainButton.Type.DANGER);
        notice = new Notice(getAssetManager());

        gameTitle.setPositionGrid(1, 14.5f);
        play.setPositionGrid(2, 11);
        options.setPositionGrid(2, 9);
        stats.setPositionGrid(2, 7);
        credits.setPositionGrid(2, 5);
        exit.setPositionGrid(2, 3);
        notice.setPositionGrid(1, 0.5f);

        play.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                play.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(LEVEL_SELECT, Hierarchy.LOWER);
            }
        });

        options.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                options.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(OPTIONS, Hierarchy.LOWER);
            }
        });

        stats.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                stats.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(STATS, Hierarchy.LOWER);
            }
        });

        credits.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                credits.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(CREDITS, Hierarchy.LOWER);
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exit.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
                exitApplication();
            }
        });

        addMainActor(gameTitle);
        addMainActor(play);
        addMainActor(options);
        addMainActor(stats);
        addMainActor(credits);
        addMainActor(exit);
        addMainActor(notice);
    }

    @Override
    public void back() {
        exitApplication();
    }

    @Override
    public void show() {
        super.show();
        getGameManager().play(GameManager.Track.Menu);
    }
}
