package com.jessematty.black.tower.GameBaseClasses.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import static com.badlogic.gdx.graphics.GL20.GL_BLEND;
import static com.badlogic.gdx.graphics.GL20.GL_DST_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_DST_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_TRUE;

public class FrameBufferRenderer {

    private Batch batch;


    public FrameBufferRenderer(Batch batch) {
        this.batch = batch;
    }

    public FrameBufferRenderer() {
        this.batch= new BrightnessBatch();
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }


    public void renderFrame(FrameBuffer frameBuffer){


    }

    public void renderFrames( FrameBuffer... frameBuffers){
            batch.enableBlending();
            if(!batch.isDrawing()){
                batch.begin();
            }
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_DST_ALPHA, GL_ONE_MINUS_DST_ALPHA);






    }


}
