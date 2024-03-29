package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;

public class StaticTiledMapTileActor extends ClipBoardActor{
   private AtlasStaticTiledMapTile tile;
   private TextureRegion textureRegion;
   
  
   
    public StaticTiledMapTileActor(AtlasStaticTiledMapTile tile) {
        this.tile = tile;
        this.textureRegion=tile.getTextureRegion();
        
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        if(tile!=null) {
            if (textureRegion != null) {
                batch.draw(textureRegion, locationX, locationY);
            }
        }
        
        
        
    }
    
}
