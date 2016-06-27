package com.jmolina.orb.listeners;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.screens.LevelBaseScreen;
import com.jmolina.orb.stages.GestureStage;

public class GestureHandler implements GestureDetector.GestureListener {

    private final float IMPULSE_RATIO_X = LevelBaseScreen.RATIO_METER_PIXEL;
    private final float IMPULSE_RATIO_Y = -LevelBaseScreen.RATIO_METER_PIXEL;
    private final float MAX_VELOCITY = 40.0f;

    private Element orb;
    private GestureStage stage;

    public GestureHandler(GestureStage stage) {
        this.stage = stage;
    }

    public Element getOrb() {
        return this.orb;
    }

    public void setOrb(Element orb) {
        this.orb = orb;
    }


    /**
     * GestureListener methods
     */

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("tap");

        // TODO: Que cuando se detenga, permanezca detenido un tiempo

        getOrb().getBody().setLinearVelocity(0,0);
        getOrb().getBody().setAngularVelocity(0);
        getOrb().getBody().setLinearDamping(0);
        getOrb().getBody().setAngularDamping(0);

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        System.out.println("fling X: " + velocityX + ", Y: " + velocityY);

        getOrb().getBody().applyLinearImpulse(
                velocityX * IMPULSE_RATIO_X,
                velocityY * IMPULSE_RATIO_Y,
                getOrb().getBody().getPosition().x,
                getOrb().getBody().getPosition().y,
                true
        );

        stage.drawGesture();

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
    }

}
