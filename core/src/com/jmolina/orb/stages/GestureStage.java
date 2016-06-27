package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jmolina.orb.elements.BaseActor;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.LevelBaseScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GestureStage extends Stage {

    private final float PIXELS_PER_METER = 1 / LevelBaseScreen.RATIO_METER_PIXEL;
    private final float VIEWPORT_WIDTH = BaseScreen.VIEWPORT_WIDTH;
    private final float VIEWPORT_HEIGHT = BaseScreen.VIEWPORT_HEIGHT;

    private BaseActor base;
    private BaseActor line;
    private BaseActor arrow;
    private Vector2 start;
    private Vector2 end;
    private FrameBuffer buffer;

    public GestureStage(AssetManager am) {
        super();

        buffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT, false);

        start = new Vector2();
        end = new Vector2();

        base = new BaseActor(am.get(Asset.GAME_GESTURE_BASE, Texture.class));
        base.setScale(PIXELS_PER_METER / base.getWidth(), PIXELS_PER_METER / base.getHeight());

        line = new BaseActor(am.get(Asset.GAME_GESTURE_LINE, Texture.class));
        line.setScale(PIXELS_PER_METER / line.getWidth(), PIXELS_PER_METER / line.getHeight() / 8f);

        arrow = new BaseActor(am.get(Asset.GAME_GESTURE_ARROW, Texture.class));
        arrow.setScale(PIXELS_PER_METER / arrow.getWidth(), PIXELS_PER_METER / arrow.getHeight());

        addActor(base);
        addActor(line);
        addActor(arrow);

        getRoot().addAction(alpha(0));
    }

    public void newGesture() {
        // Hay que limpiar el buffer cada vez que viene un gesto nuevo
        buffer.dispose();
        buffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT, false);

        // Inicializar posiciones
        base.setPosition(start.x - 0.5f * base.getWidth(), start.y - 0.5f * base.getHeight());

        arrow.setPosition(end.x - 0.5f * arrow.getWidth(), end.y - 0.5f * arrow.getHeight());
        Vector2 direction = new Vector2(end.x - start.x, end.y - start.y);
        float angle = MathUtils.atan2(direction.y, direction.x);
        arrow.setRotation(angle * MathUtils.radiansToDegrees);

        line.setRotation(angle * MathUtils.radiansToDegrees);
        float distance = (float) Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
        line.setScaleX(distance / line.getWidth());
        line.setPosition(0.5f * (base.getX() + arrow.getX()), 0.5f * (base.getY() + arrow.getY()));

        getRoot().clearActions();
        getRoot().addAction(sequence(
                alpha(1),
                fadeOut(0.75f)
        ));
    }

    /**
     * Modificacion de la version de super para permitir el renderizado en FrameBuffer
     */
    @Override
    public void draw() {
        Camera camera = getViewport().getCamera();
        camera.update();

        if (!getRoot().isVisible()) return;

        Batch batch = this.getBatch();
        if (batch != null) {
            batch.setProjectionMatrix(camera.combined);

            // Se dibuja la stage en el buffer
            // buffer.bind(); // Esto viene en el libro pero no parece necesario
            buffer.begin();

            batch.begin();
            getRoot().draw(batch, 1);
            batch.end();

            buffer.end();
            // FrameBuffer.unbind(); // Esto viene en el libro pero no parece necesario

            // Se dibuja el buffer en la pantalla
            batch.begin();
            batch.draw(buffer.getColorBufferTexture(), 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, 0, 0, 1, 1);
            batch.end();
        }
    }


    /**
     * InputProcessor methods
     */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        start.set(screenX, VIEWPORT_HEIGHT - screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        end.set(screenX, VIEWPORT_HEIGHT - screenY);
        return false;
    }

}
