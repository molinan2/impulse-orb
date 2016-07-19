package com.jmolina.orb.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.data.UserData;
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
            decide(userDataB);
        else if (fixtureB.equals(orbFixture))
            decide(userDataA);
    }

    @Override
    public void endContact(Contact contact) {
        // System.out.println("end");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // System.out.println("presolve");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // System.out.println("postsolve");
    }

    private void decide(UserData userData) {
        if (userData.flavor == Element.Flavor.RED) {
            level.destroy();
        }
        else if (userData.effect == Element.Effect.EXIT) {
            level.successGame();
        }
        else if (userData.flavor == Element.Flavor.BLUE) {
            // do nothing
        }
        else {
            level.collision();
        }
    }

}
