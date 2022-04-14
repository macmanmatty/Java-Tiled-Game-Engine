package com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
public class AnimationPreview extends Actor {
    private int frameRate=10;
    private Array<AnimationFrame> frames= new Array<>();
    private int frameCounter;
    private int currentFrame;
    private  float height;
    private float width;
    public int getFrameRate() {
        return frameRate;
    }
    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
    public Array<AnimationFrame> getFrames() {
        return frames;
    }
    public void setFrames(Array<AnimationFrame> frames) {
        this.frames = frames;
    }
    @Override
    public void setWidth(float width) {
        this.width = width;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        int numberOfFrames = frames.size;
        if (numberOfFrames > 0) {
            batch.setColor(getColor());
            TextureRegion frame = frames.get(currentFrame).getTextureRegion();
            if (frame != null) {
                batch.draw(frame, getX(), getY());
                if (frameCounter % frameRate == 0) {
                    currentFrame++;
                    if (currentFrame >= numberOfFrames) {
                        currentFrame = 0;
                    }
                }
                frameCounter++;
            }
        }
    }
}
