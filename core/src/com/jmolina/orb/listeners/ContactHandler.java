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
