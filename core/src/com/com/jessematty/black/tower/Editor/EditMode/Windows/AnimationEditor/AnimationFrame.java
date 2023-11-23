package com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Editor.EditMode.UIElements.TextureRegionSettableImage;

public class AnimationFrame extends TextureRegionSettableImage {


    private int frameNumber;
    private TextureRegion textureRegion;
    private TextureRegion noTile;
    private AnimationEditor animationEditor;



    public AnimationFrame(final AnimationEditor animationEditor, final TextureRegion region, int frameNumber) {
        super( animationEditor.getClipBoard() , region);
        this.textureRegion = region;
        this.frameNumber = frameNumber;
        this.animationEditor=animationEditor;
        if(textureRegion ==null){
            setTextureRegion(noTile);


        }

        setSize(64, 64);



    }




    public AnimationFrame( AnimationEditor animationEditor, int frameNumber) {
     this(animationEditor, null , frameNumber);


    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.setDrawable(new TextureRegionDrawable(textureRegion));
    }
}
