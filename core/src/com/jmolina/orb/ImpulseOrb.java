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

package com.jmolina.orb;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.jmolina.orb.interfaces.PlayServices;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;


/**
 * Punto de entrada de la aplicación. Implementa {@link ApplicationListener} para escuchar los
 * eventos de Android. Implementa {@link SuperManager} para gestionar el resto de managers de nivel
 * de aplicación.
 */
public class ImpulseOrb implements ApplicationListener, SuperManager {

	private PrefsManager prefsManager;
	private AssetManager assetManager;
	private ScreenManager screenManager;
	private GameManager gameManager;
	private PlayServices serviceManager;


	/**
	 * Constructor
	 */
	public ImpulseOrb() {
		this.serviceManager = null;
	}

	/**
	 * Constructor
	 */
	public ImpulseOrb(PlayServices serviceManager) {
		this.serviceManager = serviceManager;
	}


	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		prefsManager = new PrefsManager();
		assetManager = new AssetManager();
		assetManager.loadLoadScreenAssets();
		screenManager = new ScreenManager(this);
	}

	@Override
	public void render () {
		screenManager.render();
	}

	@Override
	public void resize (int width, int height) {
		screenManager.resize(width, height);
	}

	@Override
	public void resume () {
		screenManager.resume();
	}

	@Override
	public void pause () {
		screenManager.pause();
	}

	@Override
	public void dispose () {
		screenManager.dispose();
		if (gameManager != null) gameManager.dispose();
		assetManager.clear();
		assetManager.dispose();
	}

	@Override
	public void createGameManager() {
		gameManager = new GameManager(this);
	}

	@Override
	public PrefsManager getPrefsManager() {
		return prefsManager;
	}

	@Override
	public AssetManager getAssetManager() {
		return this.assetManager;
	}

	@Override
	public ScreenManager getScreenManager() {
		return this.screenManager;
	}

	@Override
	public GameManager getGameManager() {
		return this.gameManager;
	}

	@Override
	public PlayServices getServiceManager() {
		return serviceManager;
	}

}
