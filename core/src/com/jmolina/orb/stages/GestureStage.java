package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.game.Arrow;
import com.jmolina.orb.widgets.game.Pulse;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GestureStage extends Stage {

    private Pulse pulse;
    private Arrow arrow;
    private Vector2 start, end;
    private FrameBuffer buffer;
    private Image solidArrow;

    public GestureStage(AssetManager am, Viewport vp, float pixelsPerMeter) {
        super(vp);

        solidArrow = new Image();
        buffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) Var.SCREEN_WIDTH, (int) Var.SCREEN_HEIGHT, false);
        start = new Vector2();
        end = new Vector2();
        arrow = new Arrow(am, pixelsPerMeter);
        pulse = new Pulse(am, pixelsPerMeter);

        pulse.setPosition(
                0.5f * vp.getWorldWidth() - 0.5f * pulse.getWidth(),
                0.5f * vp.getWorldHeight() - 0.5f * pulse.getHeight()
        );

        solidArrow.setSize(Var.SCREEN_WIDTH, Var.SCREEN_HEIGHT);
        solidArrow.setPosition(0, 0);
        arrow.setVisible(false);
        pulse.reset();

        addActor(pulse);
        addActor(arrow);
        addActor(solidArrow);
    }

    /**
     * Dibuja la flecha del gesto "fling" a través de un {@link FrameBuffer}.
     */
    public void drawFling() {
        pulse.reset();
        arrow.set(start, end);
        renderBuffer();
        updateSolidArrow();
        startSolidArrow();
    }

    /**
     * Dibuja el pulso del gesto "tap".
     */
    public void drawTap() {
        resetSolidArrow();
        pulse.start();
    }

    /**
     * Genera un nuevo buffer.
     */
    private void renderBuffer() {
        buffer.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        arrow.setVisible(true);
        solidArrow.setVisible(false);
        draw();
        arrow.setVisible(false);
        solidArrow.setVisible(true);
        buffer.end();
    }

    /**
     * Asigna la textura del buffer a la imagen de la flecha.
     */
    private void updateSolidArrow() {
        TextureRegion arrowRegion = new TextureRegion(buffer.getColorBufferTexture());
        arrowRegion.flip(false, true);
        solidArrow.setDrawable(new TextureRegionDrawable(arrowRegion));
    }

    /**
     * Inicia la animación de la flecha.
     */
    private void startSolidArrow() {
        solidArrow.clearActions();
        solidArrow.addAction(sequence(alpha(1), fadeOut(0.75f)));
    }

    /**
     * Resetea la animación de la flecha y la oculta.
     */
    private void resetSolidArrow() {
        solidArrow.clearActions();
        solidArrow.addAction(alpha(0));
    }

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
