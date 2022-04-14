package com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class AnimationSource extends Source {
    private final AnimationEditor animationEditor;
    private final AnimationFrame animationFrame;



    private int selectedIndex;
    final Payload payload= new Payload();

    public AnimationSource(AnimationEditor animationEditor, AnimationFrame animationFrame) {
        super(animationFrame);
        this.animationFrame=animationFrame;
        this.animationEditor=animationEditor;



    }
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {


        Image image= new Image(animationFrame.getDrawable());
        Image invalidImage=new Image(animationFrame.getDrawable());
        invalidImage.setColor(1, 1, 1, .5f);
        payload.setObject(image);
        payload.setDragActor(image);
        payload.setInvalidDragActor(invalidImage);

        return payload;
    }
    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        super.dragStop(event, x, y, pointer, payload, target);






    }
}
