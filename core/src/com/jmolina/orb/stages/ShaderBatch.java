package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderBatch extends SpriteBatch {

    public ShaderProgram shader;
    public final boolean isCompiled;

    //ideally use getters/setters here...
    public float brightness=0.1f;
    public float contrast=1.0f;

    public ShaderBatch() {
        super();

        ShaderProgram.pedantic = false;

        shader = new ShaderProgram(
                Gdx.files.internal("shader/brightness.vert"),
                Gdx.files.internal("shader/brightness.frag")
        );

        isCompiled = shader.isCompiled();

        if (isCompiled) {
            //setShader(shader);
            //shader.begin();
            //shader.end();
        }
    }

    public void begin() {
        super.begin();
        shader.setUniformf("brightness", brightness);
        shader.setUniformf("contrast", contrast);
    }

}
