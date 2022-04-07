package com.jessematty.black.tower.Editor.Tools.MapTools.Tools;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import java.util.Stack;
public class BucketFill {
    public static void fillCells( int xMin, int yMin, int xMax, int yMax, TiledMapTileLayer layer, int locationX, int locationY, AtlasNamedAtlasRegion fillRegion, TextureRegion regionToReplace, boolean fillDiagnols) {
        if(fillRegion==regionToReplace ){
            return;
        }
        Cell cell=layer.getCell(locationX, locationY);
        cell.setTile(new StaticTiledMapTile(fillRegion));
        Stack<BucketFillNode> queue= new Stack<>();
        addTiles(xMin, yMin, xMax, yMax, queue, layer, locationX, locationY, fillDiagnols);
        while(!queue.isEmpty()) {
            BucketFillNode bucketFillNode=queue.pop();
            if(bucketFillNode.textureRegion==regionToReplace){
                int x=bucketFillNode.x;
                int y=bucketFillNode.y;
                addTiles(xMin, yMin, xMax, yMax, queue, layer, x, y, fillDiagnols);
                cell=layer.getCell(x, y);
                cell.setTile(new StaticTiledMapTile(fillRegion));
            }
        }
    }
    private static  void addTiles( int xMin, int yMin, int  xMax, int yMax, Stack<BucketFillNode> queue, TiledMapTileLayer layer, int locationX, int locationY, boolean fillDiagnols){
        if (locationY + 1 < yMax) {
        addNode(locationX, locationY+1, layer, queue );
        }
        if (locationY - 1 >= yMin) {
            addNode(locationX, locationY-1, layer, queue );
        }
        if (locationX - 1 >= xMin) {
            addNode(locationX-1, locationY, layer, queue );
        }
        if (locationX + 1 < xMax) {
            addNode(locationX+1, locationY, layer, queue );
        }
        if (fillDiagnols == true) {
            if (locationY + 1 < yMax && locationX + 1 < xMax) {
                addNode(locationX+1, locationY+1, layer, queue );
            }
            if (locationY - 1 >= 0 && locationX - 1 >= yMin) {
                addNode(locationX-1, locationY-1, layer, queue );
            }
            if (locationX - 1 >= 0 && locationY + 1 < yMax) {
                addNode(locationX-1, locationY+1, layer, queue );
            }
            if (locationX + 1 < xMax && locationY - 1 >= xMin) {
                addNode(locationX+1, locationY-1, layer, queue );
            }
        }
    }
    private static  void addNode( int x, int y, TiledMapTileLayer layer, Stack<BucketFillNode> queue ){
        Cell tiledCell = layer.getCell(x, y);
        if(tiledCell!=null) {
            TiledMapTile tiledMapTile = tiledCell.getTile();
            if(tiledMapTile!=null) {
                queue.add(new BucketFillNode(x, y, tiledMapTile.getTextureRegion()));
            }
            else{
                queue.add(new BucketFillNode(x,  y, null));
            }
        }
    }
    private static class BucketFillNode {
        int x;
        int y;
        TextureRegion textureRegion;
        public BucketFillNode(int x, int y, TextureRegion atlasNamedAtlasRegion) {
            this.x = x;
            this.y = y;
            this.textureRegion = atlasNamedAtlasRegion;
        }
    }
}
