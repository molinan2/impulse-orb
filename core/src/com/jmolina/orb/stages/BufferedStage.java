package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.var.Var;

/**
 * No funciona así.
 * La idea es que se aplique la transición al FrameBuffer, no a los actores
 */
public class BufferedStage extends Stage {

    private FrameBuffer buffer;

    public BufferedStage () {
        super();
    }

    public BufferedStage (Viewport viewport) {
        super(viewport);
    }

    public void newTransition() {
        buffer.dispose();
        buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Var.WIDTH, Var.HEIGHT, false);
    }

    @Override
    public void draw() {
        // Se dibuja la stage en el buffer
        buffer.begin();
        super.draw();
        buffer.end();

        // Se dibuja el buffer en la pantalla
        getBatch().begin();
        getBatch().draw(buffer.getColorBufferTexture(), 0, 0, Var.WIDTH, Var.HEIGHT, 0, 0, 1, 1);
        getBatch().end();

        buffer.dispose();
    }

}
