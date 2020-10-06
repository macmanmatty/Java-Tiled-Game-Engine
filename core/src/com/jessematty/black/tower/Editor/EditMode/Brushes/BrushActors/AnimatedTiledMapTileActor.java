package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
public class AnimatedTiledMapTileActor  extends ClipBoardActor {
   private AtlasAnimatedTiledMapTile tile;
   private int currentFrame;
   private TextureRegion textureRegion;
   
   private AtlasStaticTiledMapTile[] frameTiles;
   private int frameRate=5;
   private int counter;
    public AnimatedTiledMapTileActor(AtlasAnimatedTiledMapTile tile) {
        this.tile = tile;
        this.frameTiles=tile.getFrameTiles();
        this.textureRegion=frameTiles[0].getTextureRegion();
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
       AtlasStaticTiledMapTile tile=frameTiles[currentFrame];
        if(tile!=null) {
            textureRegion =tile.getTextureRegion();
            if (textureRegion != null) {
                batch.draw(textureRegion, locationX, locationY);
            }
        }
        counter++;
        if(counter%frameRate==0) {
            currentFrame++;
        }
            if (currentFrame > frameTiles.length) {
                currentFrame = 0;
            }
    }
 
}
