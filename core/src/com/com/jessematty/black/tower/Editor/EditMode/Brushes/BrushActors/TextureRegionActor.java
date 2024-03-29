package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionActor extends ClipBoardActor{
   private TextureRegion textureRegion;


    public TextureRegionActor(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
            if (textureRegion != null) {
                batch.draw(textureRegion, locationX, locationY);
            }
        }
        
        
        

    
}
