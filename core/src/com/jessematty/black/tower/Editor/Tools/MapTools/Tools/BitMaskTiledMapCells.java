package com.jessematty.black.tower.Editor.Tools.MapTools.Tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskException;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

import java.util.Stack;

public class BitMaskTiledMapCells {

   private static  final  BitMask bitMask= new BitMask();

   private  static  int [] [] bitMap ;
   private static GameAssets gameAssets;
   private static String atlasName;



    public static void bitMaskCells(int xMin, int yMin, int xMax, int yMax, TiledMapTileLayer layer, int locationX, int locationY, GameAssets assets, TileSet tileSet,  TextureRegion regionToMask) {
        bitMap= new int [xMax-xMin] [yMax-yMin];
       gameAssets=assets;
       atlasName=tileSet.getAtlasName();

       bitMaskCellsInternal(xMin, yMin, xMax, yMax, layer, locationX, locationY, tileSet, regionToMask);



    }

        private  static void bitMaskCellsInternal(int xMin, int yMin, int xMax, int yMax, TiledMapTileLayer layer, int locationX, int locationY, TileSet tileSet,    TextureRegion regionToMask) throws BitMaskException {

        Cell cell=layer.getCell(locationX, locationY);
            int bitNumber=bitMask.eightSideBitMapCalculator( locationX, locationY, cell.getTile(), regionToMask, layer);;
            String  atlasNamedAtlasRegionName=tileSet.getRandomRegion(bitNumber);
            AtlasNamedAtlasRegion atlasNamedAtlasRegion=gameAssets.getAtlasRegionByName(atlasNamedAtlasRegionName, atlasName);
        cell.setTile(new StaticTiledMapTile(atlasNamedAtlasRegion));


        Stack<BitMaskNode> queue= new Stack<>();

        addTiles(xMin, yMin, xMax, yMax, queue, layer, locationX, locationY);

        while(!queue.isEmpty()) {
            BitMaskNode bitMaskNode =queue.pop();
            if(bitMaskNode.textureRegion==regionToMask){
                int x= bitMaskNode.x;
                int y= bitMaskNode.y;
                addTiles(xMin, yMin, xMax, yMax, queue, layer, x, y);
                cell=layer.getCell(x, y);
               bitNumber=bitMask.eightSideBitMapCalculator( locationX, locationY, cell.getTile(), regionToMask, layer);;
                 atlasNamedAtlasRegionName=tileSet.getRandomRegion(bitNumber);
              atlasNamedAtlasRegion=gameAssets.getAtlasRegionByName(atlasNamedAtlasRegionName, atlasName);
              if(atlasNamedAtlasRegion==null){
                  throw   new BitMaskException();

              }

                cell.setTile(new StaticTiledMapTile(atlasNamedAtlasRegion));

            }



        }


    }


    private static  void addTiles(int xMin, int yMin, int  xMax, int yMax, Stack<BitMaskNode> queue, TiledMapTileLayer layer, int locationX, int locationY){

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

    private static  void addNode( int x, int y, TiledMapTileLayer layer, Stack<BitMaskNode> queue ){


        Cell tiledCell = layer.getCell(x, y);
        if(tiledCell!=null) {
            TiledMapTile tiledMapTile = tiledCell.getTile();
            if(tiledMapTile!=null) {
                queue.add(new BitMaskNode(x, y, tiledMapTile.getTextureRegion()));
            }
            else{
                queue.add(new BitMaskNode(x,  y, null));


            }
        }
    }


    private static class BitMaskNode {

        int x;
        int y;
        TextureRegion textureRegion;


        public BitMaskNode(int x, int y, TextureRegion atlasNamedAtlasRegion) {
            this.x = x;
            this.y = y;
            this.textureRegion = atlasNamedAtlasRegion;
        }
    }
}
