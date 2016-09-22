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

package com.jmolina.orb.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jmolina.orb.data.UserData;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.interfaces.LevelManager;

/**
 * Listener de contacto entre los cuerpos del mundo fisico
 */
public class ContactHandler implements ContactListener {

    private LevelManager levelManager;

    /** Cuerpo del orbe*/
    private Body orbBody;

    /**
     * Constructor
     *
     * @param levelManager LevelManager
     * @param orb Orbe
     */
    public ContactHandler(LevelManager levelManager, Orb orb) {
        this.levelManager = levelManager;
        orbBody = orb.getBody();
    }

    /**
     * Cuando se inicia el contacto entre 2 cuerpos, se comprueba si alguno de ellos es el orbe.
     * En base a la {@link UserData} almacenada en el otro cuerpo, se decide el efecto de inicio.
     *
     * @param contact Contacto de Box2D
     */
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        UserData userDataA = (UserData) bodyA.getUserData();
        UserData userDataB = (UserData) bodyB.getUserData();

        if (bodyA.equals(orbBody))
            decideBeginning(userDataB);
        else if (bodyB.equals(orbBody))
            decideBeginning(userDataA);
    }

    /**
     * Cuando se termina el contacto entre 2 cuerpos, se comprueba si alguno de ellos es el orbe.
     * En base a la {@link UserData} almacenada en el otro cuerpo, se decide el efecto de fin.
     *
     * @param contact Contacto de Box2D
     */
    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        UserData userDataA = (UserData) bodyA.getUserData();
        UserData userDataB = (UserData) bodyB.getUserData();

        if (bodyA.equals(orbBody))
            decideEnding(userDataB);
        else if (bodyB.equals(orbBody))
            decideEnding(userDataA);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // System.out.println("presolve");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // System.out.println("postsolve");
    }

    /**
     * Decide la consecuencia de la colision en base al efecto almacenado en la UserData del cuerpo.
     *
     * @param userData UserData
     */
    private void decideBeginning(UserData userData) {
        switch (userData.effect) {
            case EXIT: levelManager.successGame(); return;
            case DESTROY: levelManager.destroy(); return;
            case HEAT: levelManager.enableTicking(userData.tick); return;
            default: decideBeginningOnFlavor(userData.flavor);
        }
    }

    /**
     * Decide la consecuencia de la colision en base al sabor almacenado en la UserData del cuerpo.
     *
     * @param flavor Sabor
     */
    private void decideBeginningOnFlavor(WorldElement.Flavor flavor) {
        switch (flavor) {
            case BLACK: levelManager.collide(true); return;
            case GREY: levelManager.collide(false); return;
            case VIOLET: levelManager.collide(false); return;
            default:
        }
    }

    /**
     * Decide la consecuencia de la finalizacion de la colision en base al efecto almacenado en la
     * UserData del cuerpo.
     *
     * @param userData UserData
     */
    private void decideEnding(UserData userData) {
        switch (userData.effect) {
            case HEAT: levelManager.disableTicking(); return;
            default:
        }
    }

}
