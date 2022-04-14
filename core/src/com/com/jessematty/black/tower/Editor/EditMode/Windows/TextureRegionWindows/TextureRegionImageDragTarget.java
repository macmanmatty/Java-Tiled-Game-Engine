package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureRegionImageDragTarget extends Target  {
    private Image image;
    private TextureRegion region;
    public TextureRegionImageDragTarget(   Image image) {
        super(image);
        this.image=image;
    }
    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        Object object= payload.getObject();

        if(! (object  instanceof TextureRegion) ){
            return false;
        }
        return true;
    }
    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        TextureRegion region= (TextureRegion) payload.getObject();
        image.setDrawable(new TextureRegionDrawable(region));
        this.region=region;
    }

    public TextureRegion getRegion() {
        return region;
    }
}
