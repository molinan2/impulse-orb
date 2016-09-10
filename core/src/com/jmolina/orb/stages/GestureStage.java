package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
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

/**
 * Stage donde se dibujan los feedback de los gestos
 */
public class GestureStage extends Stage {

    /**
     * Workaround para encajar el framebuffer en distintas proporciones de pantalla.
     * Se crea un framebuffer mayor, se calcula el offset segun las proporciones de pantalla y se
     * recoloca la imagen correspondiente al buffer.
     */
    private final float OVERSIZE_RATIO = 0.8f;

    /** Pulso del gesto de paralizacion */
    private Pulse pulse;

    /** Flecha del gesto de impulso */
    private Arrow arrow;

    /** Puntos de inicio y fin del gesto de impulso */
    private Vector2 start, end;

    /** Buffer para mezclar transparencias del gesto de impulso */
    private FrameBuffer buffer;

    /** Imagen solida del gesto de impulso generada en el buffer */
    private Image bufferedArrow;

    private Viewport vp;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param vp Viewport
     * @param pixelsPerMeter Factor de conversion de pixeles a metros
     */
    public GestureStage(AssetManager am, Viewport vp, float pixelsPerMeter) {
        super(vp);

        this.vp = vp;
        bufferedArrow = new Image();
        buffer = new FrameBuffer(
                Pixmap.Format.RGBA8888,
                vp.getScreenWidth() + (int) (OVERSIZE_RATIO * vp.getScreenWidth()),
                vp.getScreenHeight() + (int) (OVERSIZE_RATIO * vp.getScreenHeight()),
                false
        );

        start = new Vector2();
        end = new Vector2();
        arrow = new Arrow(am, pixelsPerMeter);
        pulse = new Pulse(am, pixelsPerMeter);

        pulse.setPosition(
                0.5f * vp.getWorldWidth() - 0.5f * pulse.getWidth(),
                0.5f * vp.getWorldHeight() - 0.5f * pulse.getHeight()
        );

        bufferedArrow.setSize(
                Var.SCREEN_WIDTH+ OVERSIZE_RATIO * Var.SCREEN_WIDTH,
                Var.SCREEN_HEIGHT + OVERSIZE_RATIO * Var.SCREEN_HEIGHT
        );

        float ratioX = Var.SCREEN_WIDTH / vp.getScreenWidth();
        float ratioY = Var.SCREEN_HEIGHT / vp.getScreenHeight();
        float offsetX = 0.5f * ratioX * (Gdx.graphics.getWidth() - vp.getScreenWidth());
        float offsetY = 0.5f * ratioY * (Gdx.graphics.getHeight() - vp.getScreenHeight());

        bufferedArrow.setPosition(-offsetX, -offsetY);
        arrow.setVisible(false);
        pulse.reset();

        addActor(pulse);
        addActor(arrow);
        addActor(bufferedArrow);
    }

    /**
     * Dibuja la flecha del gesto "fling" a través de un {@link FrameBuffer}.
     */
    public void drawFling() {
        pulse.reset();
        arrow.set(start, end);
        renderBuffer();
        updateBufferedArrow();
        startBufferedArrowAction();
    }

    /**
     * Dibuja el pulso del gesto "tap".
     */
    public void drawTap() {
        resetBufferedArrowAction();
        pulse.start();
    }

    /**
     * Genera un nuevo buffer.
     *
     * Si se utiliza buffer.begin() y buffer.end() se modifican incorrectamente las dimensiones del
     * viewport. Hay que utilizar buffer.bind() y FrameBuffer.unbind() (estático).
     */
    private void renderBuffer() {
        buffer.bind();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        arrow.setVisible(true);
        bufferedArrow.setVisible(false);
        draw();
        arrow.setVisible(false);
        bufferedArrow.setVisible(true);
        FrameBuffer.unbind();
    }

    /**
     * Asigna la textura del buffer a la imagen de la flecha.
     */
    private void updateBufferedArrow() {
        TextureRegion arrowRegion = new TextureRegion(buffer.getColorBufferTexture());
        arrowRegion.flip(false, true);
        bufferedArrow.setDrawable(new TextureRegionDrawable(arrowRegion));
    }

    /**
     * Inicia la animación de la flecha.
     */
    private void startBufferedArrowAction() {
        bufferedArrow.clearActions();
        bufferedArrow.addAction(sequence(alpha(1), fadeOut(0.75f)));
    }

    /**
     * Resetea la animación de la flecha y la oculta.
     */
    private void resetBufferedArrowAction() {
        bufferedArrow.clearActions();
        bufferedArrow.addAction(alpha(0));
    }

    /**
     * Captura la posicion de inicio del gesto
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 position = translate(screenX, screenY);
        start.set(position.x, position.y);
        return false;
    }

    /**
     * Captura la posicion de fin del gesto
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 position = translate(screenX, screenY);
        end.set(position.x, position.y);
        return false;
    }

    @Override
    public void dispose() {
        buffer.dispose();
        super.dispose();
    }

    /**
     * Convierte de unidades en pantalla a unidades en el viewport. Esto normalmente se hace
     * mediante Camera#unproject(), pero no estaba funcionando correctamente por algun motivo.
     *
     * Si el punto recae dentro del area usable pero fuera del viewport (que es de proporcion
     * fija), se considera que la posicion es limite.
     *
     * @param screenX Coordenada X de un punto en pantalla
     * @param screenY Coordenada Y de un punto en pantalla
     */
    private Vector2 translate(int screenX, int screenY) {
        float offsetX = 0.5f * ((float) Gdx.graphics.getWidth() - (float) vp.getScreenWidth());
        float offsetY = 0.5f * ((float) Gdx.graphics.getHeight() - (float) vp.getScreenHeight());

        int x = (int) (screenX - offsetX);
        int y = (int) ((Gdx.graphics.getHeight() - screenY) - offsetY);

        x = MathUtils.clamp(x, 0, vp.getScreenWidth());
        y = MathUtils.clamp(y, 0, vp.getScreenHeight());

        float ratioX = (vp.getWorldWidth() / (float) vp.getScreenWidth());
        float ratioY = (vp.getWorldHeight() / (float) vp.getScreenHeight());

        x = (int) (ratioX * x);
        y = (int) (ratioY * y);

        return new Vector2(x, y);
    }

}
