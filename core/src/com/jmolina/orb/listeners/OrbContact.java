package com.jmolina.orb.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class OrbContact implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        System.out.println("begin");
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("end");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        System.out.println("presolve");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        System.out.println("postsolve");
    }

}
