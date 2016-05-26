package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.jmolina.orb.screens.BackgroundScreen;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.CreditsScreen;
import com.jmolina.orb.screens.LevelLaunchScreen;
import com.jmolina.orb.screens.LevelSelectScreen;
import com.jmolina.orb.screens.LoadScreen;
import com.jmolina.orb.screens.MainScreen;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.screens.StatsScreen;
import com.jmolina.orb.var.Vars;


/**
 * TODO
 * Algun tipo de ScreenManager, que decida el flujo de pantallas y las disposee e instancie
 * Sería una especie de ScreenFactory, o incluiría una ScreenFactory
 * Info: http://www.tutorialspoint.com/design_pattern/factory_pattern.htm
 */

/**
 * TODO
 * Background que no esté implementado en ninguna Screen
 * Que se renderize por debajo de todas las Screens
 * Y sacar el background de las Screens
 * Esto permitiría que no haya "salto aparente" cuando se disposeen y creen las pantallas
 * Y daría sensación de continuidad cuando aplique efectos a las Stages de pantalla
 * Idealmente, móvil
 */

/**
 * TODO
 * En GameScreen
 * Cuando has muerto o entra en pausa, la musica baja de pitch y vuelve a 1x cuando se reanuda el juego
 */
public class OrbGame extends Game {

	private LoadScreen gameLoadScreen;
	private MainScreen mainScreen;
	private OptionsScreen optionsScreen;
	private StatsScreen statsScreen;
	private CreditsScreen creditsScreen;
	private LevelSelectScreen levelSelectScreen;
	private LevelLaunchScreen levelLaunchScreen;

	// private BackgroundScreen backgroundScreen;

	private Logger logger;
	private ArrayMap<Vars.ScreenNames, BaseScreen> screens;

	private Texture splashTexture;

	@Override
	public void create () {
		logger = new Logger("Game", Logger.INFO);
		Gdx.input.setCatchBackKey(true); // Android

		splashTexture = new Texture(Gdx.files.internal("splash.png"));

		// backgroundScreen = new BackgroundScreen();

		/**
		 * Todos los parámetros de pantallas se setean a través de métodos. No se pasan al constructor
		 *
		 * Implementar (por ahora) en Game la API necesaria para que una pantalla pueda apuntar a otra
		 * Opcionalmente, que se mantengan creadas las pantallas o se limpien
		 *
		 * Idealmente: implementar un Screen Factory/Manager y pasarselo a todas las pantallas, y que se disposeen al cambiar
 		 */

		gameLoadScreen = new LoadScreen(); // Parametrica
		gameLoadScreen.setSplash(splashTexture);
		mainScreen = new MainScreen();
		optionsScreen = new OptionsScreen();
		statsScreen = new StatsScreen();
		creditsScreen = new CreditsScreen();
		levelSelectScreen = new LevelSelectScreen();
		levelLaunchScreen = new LevelLaunchScreen(); // Parametrica

		gameLoadScreen.setScreenManager(this);
		mainScreen.setScreenManager(this);
		optionsScreen.setScreenManager(this);
		statsScreen.setScreenManager(this);
		creditsScreen.setScreenManager(this);
		levelSelectScreen.setScreenManager(this);
		levelLaunchScreen.setScreenManager(this);

		// TODO: 23/05/2016 AchievementsScreen
		// TODO: 23/05/2016 LadderScreen

		/**
		 * TODO: INFO
		 *
		 * Informacion de nivel y lista de niveles
		 *  -Estatica (decidida en diseño: id, order (implicit), title, cover )
		 *    -Cargar desde fichero JSON definido
		 *  -Dinamica (modificable en ejecucion: opciones, stats)
		 *    -Cargar internamente desde preferencias al inicio, en objetos intermedios
		 *    -Modificar en tiempo real los objetos intermedios
		 *    -Guardar los objetos intermedios a preferencias al finalizar
		 */

		screens = new ArrayMap<Vars.ScreenNames, BaseScreen>();
		screens.put(Vars.ScreenNames.SCREEN_LOAD, gameLoadScreen);
		screens.put(Vars.ScreenNames.SCREEN_MAIN, mainScreen);
		screens.put(Vars.ScreenNames.SCREEN_OPTIONS, optionsScreen);
		screens.put(Vars.ScreenNames.SCREEN_STATS, statsScreen);
		screens.put(Vars.ScreenNames.SCREEN_CREDITS, creditsScreen);
		screens.put(Vars.ScreenNames.SCREEN_LEVEL_SELECT, levelSelectScreen);
		screens.put(Vars.ScreenNames.SCREEN_LEVEL_LAUNCH, levelLaunchScreen);

		setScreen(gameLoadScreen);
	}

	@Override
	public void render () {
		if ((Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && Gdx.input.justTouched()) || Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			// logger.info("Mensaje");
		}

		if (screen != null) {
			// backgroundScreen.render(Gdx.graphics.getDeltaTime());
			screen.render(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void dispose () {
		if (screen != null) screen.hide();

		splashTexture.dispose();

		mainScreen.dispose();
		gameLoadScreen.dispose();
		optionsScreen.dispose();
		statsScreen.dispose();
		creditsScreen.dispose();
		levelSelectScreen.dispose();
		levelLaunchScreen.dispose();
		// backgroundScreen.dispose();
	}

	public void setScreenByKey(Vars.ScreenNames key) {
		if (this.screen != null) this.screen.hide();

		// TODO: 24/05/2016 Sustituir por una Screen Factory

		// Disposar screen antigua
		// Remove de ArrayMap. Igual ya no es útil el ArrayMap...
		// Instanciar nueva screen del tipo solicitado
		// Añadir al ArrayMap

		screens.get(key).setAsInputProcessor();
		this.screen = screens.get(key);

		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

}
