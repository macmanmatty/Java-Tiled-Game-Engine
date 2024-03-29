package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.ColoredTiledMapTile;

public class CellActor extends ClipBoardActor {
   private float screenLocationX;
   private float screenLocationY;
   private float rotation;
   private int tileSizeX;
    private int tileSizeY;
   private  boolean  flipx;
   private boolean flipy;
   Cell [] [] [] cells;

    public CellActor(Cell[][][] cells) {
        this.cells = cells;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(cells!=null){
            int layers=cells.length;
            int xSize=cells[0].length;
            int ySize=cells[0][0].length;
            for(int count=0; count<layers; count++) {
                for (int countx = 0; countx < xSize; countx++) {
                    for (int county = 0; county < ySize; county++) {
                        ColoredTiledMapTile tile = (ColoredTiledMapTile) cells[count][countx][county].getTile();
                        TextureRegion region = tile.getTextureRegion();
                        region.flip(flipx, flipy);
                        batch.setColor(tile.getColor());
                        batch.draw(region, screenLocationX + (countx * tileSizeX), screenLocationY + (county * tileSizeY));
                    }

                }
            }

        }
        batch.setColor(1,1,1,1);
    }


    public boolean isFlipx() {
        return flipx;
    }
    public void setFlipx(boolean flipx) {
        this.flipx = flipx;
    }
    public boolean isFlipy() {
        return flipy;
    }
    public void setFlipy(boolean flipy) {
        this.flipy = flipy;
    }
    public float getScreenLocationX(){
        return screenLocationX;
    }
    public void setScreenLocationX(float screenLocationX) {
        this.screenLocationX = screenLocationX;
    }
    public float getScreenLocationY() {
        return screenLocationY;
    }
    public void setScreenLocationY(float screenLocationY) {
        this.screenLocationY = screenLocationY;
    }
}
