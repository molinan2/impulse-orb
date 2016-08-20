package com.jmolina.orb.interfaces;

import com.jmolina.orb.data.Tick;

public interface LevelManager {

    /**
     * Primer inicio del juego (primer intento)
     */
    public void firstGame();

    /**
     * Inicia el menú de pausa del juego
     */
    public void pauseGame();

    /**
     * Reanuda el juego desde el menú de pausa
     */
    public void resumeGame();

    /**
     * Reinicia el juego desde el menú de pausa
     */
    public void restartGame();

    /**
     * Abandona el juego desde el menú de pausa
     */
    public void leaveGame();

    /**
     * Completa el juego, guardando las estadísticas y lanzando la pantalla de Success.
     * También sube el tiempo a Google Play Games.
     */
    public void successGame();

    /**
     * Bloquea el juego.
     *
     * Mientras el juego está bloqueado, no se pueden actualizar datos ni estados, ni activar o
     * desactivar el menú de pausa.
     */
    public void lock();

    /**
     * Desbloquea el juego
     */
    public void unlock();

    /**
     * Devuelve true si el juego está bloqueado
     */
    public boolean isLocked();

    /**
     * Reproduce el sonido y la vibración correspondientes a una colisión
     *
     * @param wall Indica si se colisiona contra un muro
     */
    public void collide(boolean wall);

    /**
     * Ejecuta un freeze sobre el {@link com.jmolina.orb.elements.Orb} y dibuja su animación
     */
    public void freeze();

    /**
     * Ejecuta un impulso sobre el {@link com.jmolina.orb.elements.Orb} y dibuja su animación
     *
     * @param velocityX Velocidad recogida por el {@link com.jmolina.orb.listeners.GestureHandler} para la coordenada x
     * @param velocityY Velocidad recogida por el {@link com.jmolina.orb.listeners.GestureHandler} para la coordenada y
     */
    public void impulse(float velocityX, float velocityY);

    /**
     * Destruye el {@link com.jmolina.orb.elements.Orb}, dibuja su animación y reinicia el juego
     */
    public void destroy();

    /**
     * Activa el incremento continuo de calor del {@link com.jmolina.orb.elements.Orb} (ticking). Al empezar (entrar en una
     * zona caliente), siempre ocurre un tick.
     */
    public void enableTicking(Tick tick);

    /**
     * Desactiva el ticking.
     */
    public void disableTicking();

    /**
     * Devuelve true si el {@link com.jmolina.orb.elements.Orb} está en una zona caliente.
     */
    public boolean isTicking();

    /**
     * Ejecuta un tick de calentamiento en el {@link com.jmolina.orb.elements.Orb}. Este tick puede implicar overload y
     * destroy.
     */
    public void tick();

    /**
     * Activa la sobrecarga (overload) del Orb y actualizando la visualización del HUD.
     */
    public void overload();

}
