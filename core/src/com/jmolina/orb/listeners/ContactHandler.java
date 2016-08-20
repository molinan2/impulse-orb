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

public class ContactHandler implements ContactListener {

    private enum Instant { BEGINNING, ENDING }

    private LevelManager levelManager;
    private Body orbBody;

    public ContactHandler(LevelManager levelManager, Orb orb) {
        this.levelManager = levelManager;
        orbBody = orb.getBody();
    }

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

    private void decideBeginning(UserData userData) {
        switch (userData.effect) {
            case EXIT: levelManager.successGame(); return;
            case DESTROY: levelManager.destroy(); return;
            case HEAT: levelManager.enableTicking(userData.tick); return;
            default: decideBeginningOnFlavor(userData.flavor);
        }
    }

    private void decideBeginningOnFlavor(WorldElement.Flavor flavor) {
        switch (flavor) {
            case BLACK: levelManager.collide(true); return;
            case GREY: levelManager.collide(false); return;
            case VIOLET: levelManager.collide(false); return;
            default:
        }
    }

    private void decideEnding(UserData userData) {
        switch (userData.effect) {
            case HEAT: levelManager.disableTicking(); return;
            default:
        }
    }

}
