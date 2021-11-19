package com.jessematty.black.tower.GameBaseClasses.Rendering;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Disposable;
import static com.badlogic.gdx.graphics.GL20.GL_BLEND;
import static com.badlogic.gdx.graphics.GL20.GL_DST_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_DST_ALPHA;

/**
 * class that renders the light objects to the frame buffers 
 * and then blends them to make the final image displayed on the screen
 */
public class FrameBufferRenderer  implements Disposable {
    /**
     * the sprite batch to draw the objects
     */
    private Batch batch;
    /**
     * the frame buffer used to draw light objects
     */
    private FrameBuffer lightFrameBuffer;
    /**
     * the frame buffer used to draw non light objects , the tile map, game entities etc. 
     */
    private FrameBuffer mapFrameBuffer;
    /**
     * width and height of the frame buffer
     */
    private int width;
    private int height;
    public FrameBuffer getLightFrameBuffer() {
        return lightFrameBuffer;
    }
  
    public FrameBuffer getMapFrameBuffer() {
        return mapFrameBuffer;
    }
   
    public FrameBufferRenderer( Batch batch, int width, int height) {
        this.width=width;
        this.height=width;
        this.batch=batch;
        this.batch= new BrightnessBatch();
       // mapFrameBuffer= new FrameBuffer(Format.RGBA8888, width, height, false);
       // lightFrameBuffer= new FrameBuffer(Format.RGBA8888, width, height, false);
    }
    public Batch getBatch() {
        return batch;
    }
    public void setBatch(Batch batch) {
        this.batch = batch;
    }
    public void renderFrame(FrameBuffer frameBuffer){
    }
    /**
     *  called each loop tick to render the current light and  non light frame buffers
     */
    public void update(){
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_DST_ALPHA, GL_ONE_MINUS_DST_ALPHA);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    @Override
    public void dispose() {
        lightFrameBuffer.dispose();;
        mapFrameBuffer.dispose();
    }
}
