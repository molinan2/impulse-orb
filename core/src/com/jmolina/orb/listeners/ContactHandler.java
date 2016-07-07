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
        UserData userDataA = (UserData) contact.getFixtureA().getUserData();
        UserData userDataB = (UserData) contact.getFixtureB().getUserData();
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.equals(orbFixture)) {
            if (userDataB.type == Element.Type.RED) {
                level.destroyOrb();
            }
            else if (userDataB.effect == Element.Effect.EXIT) {
                level.successGame();
            }
        }
        else if (fixtureB.equals(orbFixture)) {
            if (userDataA.type == Element.Type.RED) {
                level.destroyOrb();
            }
            else if (userDataA.effect == Element.Effect.EXIT) {
                level.successGame();
            }
        }
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

}
