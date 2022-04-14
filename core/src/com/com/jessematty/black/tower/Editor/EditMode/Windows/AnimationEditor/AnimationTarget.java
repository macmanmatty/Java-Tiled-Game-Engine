package com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimationTarget extends Target {
    private  final AnimationEditor animationEditor;
    private AnimationFrame animationFrame;

    public AnimationTarget(AnimationEditor animationEditor, AnimationFrame animationFrame) {
        super(animationFrame);
        this.animationEditor = animationEditor;
        this.animationFrame = animationFrame;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        Object object=payload.getObject();
        if(  object instanceof TextureRegion ||  object instanceof Sprite|| object instanceof TextureRegionDrawable || object instanceof Texture) {
            return true;
        }
        return false;

    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
            int index=animationFrame.getFrameNumber();
            if(x>animationEditor.getWidth()){
                index++;
            }

            Object object=payload.getObject();

            AnimationFrame frame=null;

            if(object instanceof AnimationFrame){
                frame= (AnimationFrame) object;
            }




        animationEditor.getAnimationFrames().removeValue(frame, true);

       animationEditor.getAnimationFrames().insert(index, frame);


            animationEditor.forceRemakeTable();


    }




}
