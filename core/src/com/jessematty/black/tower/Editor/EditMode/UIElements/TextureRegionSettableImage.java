package com.jessematty.black.tower.Editor.EditMode.UIElements;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor.AnimationEditor;
public class TextureRegionSettableImage extends Image {
    
    
    private  TextureRegion textureRegion;
    private TextureRegion noImage;
    private ClipBoard clipBoard;

    public TextureRegionSettableImage(final ClipBoard clipBoard, final TextureRegion region) {
        super(region);
        this.textureRegion = region;
        this.clipBoard=clipBoard;

        if(textureRegion ==null){
            this.textureRegion =new TextureRegion(noImage);
        }
        setSize(64, 64);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                TextureRegion copyRegion= (TextureRegion) clipBoard.getCurrentObject();
                if(copyRegion!=null){
                   setTextureRegion(copyRegion);
                }
                return true;
            }
        });
    }
    public TextureRegionSettableImage(ClipBoard clipBoard) {
        this(clipBoard, null);
    }
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.setDrawable(new TextureRegionDrawable(textureRegion));
    }
    
    
}
