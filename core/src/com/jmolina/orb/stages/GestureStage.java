package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jmolina.orb.elements.BaseActor;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Level;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * TODO
 * Hacer de fling un objeto
 */
public class GestureStage extends Stage {

    private final float VIEWPORT_WIDTH = BaseScreen.VIEWPORT_WIDTH;
    private final float VIEWPORT_HEIGHT = BaseScreen.VIEWPORT_HEIGHT;

    private float pixelsPerMeter;
    private float zoomRatio;
    private BaseActor base;
    private BaseActor line;
    private BaseActor arrow;
    private BaseActor tap;
    private Group fling;
    private Vector2 start;
    private Vector2 end;
    // private FrameBuffer buffer;

    public GestureStage(Viewport vp, AssetManager am, float rmp, float zr) {
        super(vp);

        pixelsPerMeter = 1 / rmp;
        zoomRatio = zr;

        start = new Vector2();
        end = new Vector2();
        fling = new Group();
        base = new BaseActor(am.get(Asset.GAME_GESTURE_BASE, Texture.class));
        line = new BaseActor(am.get(Asset.GAME_GESTURE_LINE, Texture.class));
        arrow = new BaseActor(am.get(Asset.GAME_GESTURE_ARROW, Texture.class));

        tap = new BaseActor(am.get(Asset.GAME_GESTURE_BASE, Texture.class));
        tap.setPosition(
                VIEWPORT_WIDTH/2 - tap.getWidth()/2,
                VIEWPORT_HEIGHT/2 - tap.getHeight()/2);

        // buffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT, false);

        base.setScale(pixelsPerMeter / base.getWidth(), pixelsPerMeter / base.getHeight());
        line.setScale(pixelsPerMeter / line.getWidth(), pixelsPerMeter / line.getHeight() / 8f);
        arrow.setScale(pixelsPerMeter / arrow.getWidth(), pixelsPerMeter / arrow.getHeight());

        tap.addAction(fadeOut(0));
        addActor(tap);

        fling.addActor(base);
        fling.addActor(line);
        fling.addActor(arrow);
        fling.setTransform(false);
        addActor(fling);
        fling.addAction(fadeOut(0));
    }

    public void drawFling() {
        tap.clearActions();
        tap.addAction(fadeOut(0));
        fling.clearActions();
        fling.addAction(fadeOut(0));

        // Hay que limpiar el buffer cada vez que viene un gesto nuevo
        // buffer.dispose();
        // buffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT, false);

        // Centra la flecha
        float offsetX = start.x - getViewport().getWorldWidth() * 0.5f;
        float offsetY = start.y - getViewport().getWorldHeight() * 0.5f;

        offsetX = offsetY = 0; // Desactivado

        // Actualiza posiciones
        base.setPosition(
                start.x - 0.5f * base.getWidth() - offsetX,
                start.y - 0.5f * base.getHeight() - offsetY
        );

        arrow.setPosition(
                end.x - 0.5f * arrow.getWidth() - offsetX,
                end.y - 0.5f * arrow.getHeight() - offsetY
        );
        Vector2 direction = new Vector2(end.x - start.x, end.y - start.y);
        float angle = MathUtils.atan2(direction.y, direction.x);
        arrow.setRotation(angle * MathUtils.radiansToDegrees);

        line.setRotation(angle * MathUtils.radiansToDegrees);
        float distance = (float) Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
        line.setScaleX(distance / line.getWidth());
        line.setPosition(
                0.5f * (base.getX() + arrow.getX()),
                0.5f * (base.getY() + arrow.getY())
        );

        fling.clearActions();
        fling.addAction(sequence(
                fadeIn(0),
                fadeOut(0.75f)
        ));
    }

    public void drawTap() {
        fling.clearActions();
        fling.addAction(fadeOut(0));
        tap.clearActions();
        tap.addAction(fadeOut(0));

        // buffer.dispose();
        // buffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT, false);

        tap.addAction(sequence(
                parallel(fadeIn(0), scaleTo(zoomRatio * 0.25f, zoomRatio * 0.25f)),
                parallel(fadeOut(0.25f), scaleTo(zoomRatio * 0.5f, zoomRatio * 0.5f, 0.25f))
        ));
    }


    @Override
    public void draw() {
        // Se dibuja la stage en el buffer
        // buffer.begin();
        super.draw();
        // buffer.end();

        // Se dibuja el buffer en la pantalla
        // getBatch().begin();
        // getBatch().draw(buffer.getColorBufferTexture(), 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, 0, 0, 1, 1);
        // getBatch().end();
    }


    /**
     * InputProcessor methods
     */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 vector = new Vector3(screenX, screenY, 0);
        getCamera().unproject(vector);
        start.set(vector.x, vector.y);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 vector = new Vector3(screenX, screenY, 0);
        getCamera().unproject(vector);
        end.set(vector.x, vector.y);

        return false;
    }

}
