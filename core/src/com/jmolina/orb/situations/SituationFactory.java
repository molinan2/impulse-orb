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

package com.jmolina.orb.situations;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jmolina.orb.managers.AssetManager;

/**
 * Factoria simplificada de {@link Situation}. Devuelve una nueva instancia de una situacion dada
 * por su nombre de clase.
 */
public class SituationFactory {

    private AssetManager assetManager;
    private World world;
    private float pixelsPerMeter;

    /**
     * Constructor
     *
     * @param assetManager AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correccion pixeles/metros
     */
    public SituationFactory(AssetManager assetManager, World world, float pixelsPerMeter) {
        this.assetManager = assetManager;
        this.world = world;
        this.pixelsPerMeter = pixelsPerMeter;
    }

    /**
     * Instancia una nueva situacion.
     *
     * @param clazz Clase de la situacion
     * @return Instancia
     */
    public Situation newSituation(Class clazz) {
        Object object = null;

        try {
            Constructor constructor = ClassReflection.getConstructor(clazz, AssetManager.class, World.class, float.class);
            object = constructor.newInstance(assetManager, world, pixelsPerMeter);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return (Situation) object;
    }

}
