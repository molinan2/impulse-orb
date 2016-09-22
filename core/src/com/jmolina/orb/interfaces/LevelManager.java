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

package com.jmolina.orb.interfaces;

import com.jmolina.orb.data.Tick;

/**
 * Interfaz de control de un nivel de juego
 */
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
