package com.jmolina.orb.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jmolina.orb.data.UserData;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.screens.Level;

public class ContactHandler implements ContactListener {

    private Level level;
    private Fixture orbFixture;

    public ContactHandler(Level level) {
        this.level = level;
        this.orbFixture = level.getOrb().getBody().getFixtureList().first();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        UserData userDataA = (UserData) fixtureA.getUserData();
        UserData userDataB = (UserData) fixtureB.getUserData();

        if (fixtureA.equals(orbFixture))
            decideStart(userDataB);
        else if (fixtureB.equals(orbFixture))
            decideStart(userDataA);
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        UserData userDataA = (UserData) fixtureA.getUserData();
        UserData userDataB = (UserData) fixtureB.getUserData();

        if (fixtureA.equals(orbFixture))
            decideEnd(userDataB);
        else if (fixtureB.equals(orbFixture))
            decideEnd(userDataA);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // System.out.println("presolve");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // System.out.println("postsolve");
    }

    private void decideStart(UserData userData) {
        switch (userData.effect) {
            case EXIT: level.successGame(); return;
            case DESTROY: level.destroy(); return;
            case HEAT: level.setTicking(true); return;
            default: decideOnFlavor(userData.flavor);
        }
    }

    private void decideOnFlavor(WorldElement.Flavor flavor) {
        switch (flavor) {
            case BLACK: level.collide(true); return;
            case GREY: level.collide(false); return;
            default:
        }
    }

    private void decideEnd(UserData userData) {
        switch (userData.effect) {
            case HEAT: level.setTicking(false); return;
            default:
        }
    }

}
