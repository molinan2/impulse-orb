package com.jmolina.orb.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jmolina.orb.data.UserData;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.screens.Level;

public class ContactHandler implements ContactListener {

    private enum Instant { BEGINNING, ENDING }

    private Level level;
    private Body bodyOrb;

    public ContactHandler(Level level) {
        this.level = level;
        this.bodyOrb = level.getOrb().getBody();
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        UserData userDataA = (UserData) bodyA.getUserData();
        UserData userDataB = (UserData) bodyB.getUserData();

        if (bodyA.equals(bodyOrb))
            decideBeginning(userDataB);
        else if (bodyB.equals(bodyOrb))
            decideBeginning(userDataA);
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        UserData userDataA = (UserData) bodyA.getUserData();
        UserData userDataB = (UserData) bodyB.getUserData();

        if (bodyA.equals(bodyOrb))
            decideEnding(userDataB);
        else if (bodyB.equals(bodyOrb))
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
            case EXIT: level.successGame(); return;
            case DESTROY: level.destroy(); return;
            case HEAT: level.enableTicking(userData.tick); return;
            default: decideBeginningOnFlavor(userData.flavor);
        }
    }

    private void decideBeginningOnFlavor(WorldElement.Flavor flavor) {
        switch (flavor) {
            case BLACK: level.collide(true); return;
            case GREY: level.collide(false); return;
            case VIOLET: level.collide(false); return;
            default:
        }
    }

    private void decideEnding(UserData userData) {
        switch (userData.effect) {
            case HEAT: level.disableTicking(); return;
            default:
        }
    }

}
