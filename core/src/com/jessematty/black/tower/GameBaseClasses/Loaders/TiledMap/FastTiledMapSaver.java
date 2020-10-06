package com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;

public class FastTiledMapSaver implements TiledMapSaver {
   protected  CellSaver[] [] [] cells; // the saved tiled map cells
    protected int layers; // number of map layers
   protected String [] layerNames; // the names of the layers
    protected MapProperties mapProperties = new  MapProperties();



    public FastTiledMapSaver() {
    }
    public FastTiledMapSaver(GameAssets assetts) {

    }
    public TiledMap loadMap(GameAssets assetts){
        int xSize=mapProperties.get("width", Integer.class);
        int ySize=mapProperties.get("height", Integer.class);
        int tileSizeX=mapProperties.get("tileWidth", Integer.class);
        int tileSizeY=mapProperties.get("tileHeight", Integer.class);
        String atlasName=mapProperties.get("atlasName", String.class);

        TiledMap tiledMap= new TiledMap();
        for(int count=0; count<layers; count++){
            TiledMapTileLayer layer= new TiledMapTileLayer(xSize, ySize, tileSizeX, tileSizeY);
            layer.setName(layerNames[count]);
            tiledMap.getLayers().add(layer);
            for(int countx=0; countx<xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    CellSaver saver=cells[count][countx][county];
                    if(saver==null){ // if cell is null do nothing
                        continue;
                    }
                    Cell cell= new Cell();
                    cell.setFlipVertically(saver.isFlipVertical());
                    cell.setFlipHorizontally(saver.isFlipHorizontal());
                    boolean animated=saver.isAnimated();
                    if(animated==true){ // if cell is animated make animated tile
                     String [] regionNames=saver.getRegionNames();
                        Array<AtlasStaticTiledMapTile> regions= new Array<AtlasStaticTiledMapTile>();
                        for(int count2=0; count2<regionNames.length; count2++){
                            AtlasNamedAtlasRegion region=assetts.getAtlasRegionByName(regionNames[count], atlasName);
                            if(region!=null){
                                regions.add(new AtlasStaticTiledMapTile(region));
                            }
                        }
                        cell.setTile(new AtlasAnimatedTiledMapTile(saver.getInterval(), regions));
                        layer.setCell(countx, ySize-county,cell );
                    }
                    else{
                        String [] name= saver.getRegionNames();
                        AtlasNamedAtlasRegion region=assetts.getAtlasRegionByName(name[0], atlasName);
                        System.out.println("Region "+region);
                        if(region!=null){
                            cell.setTile(new AtlasStaticTiledMapTile(region));
                            layer.setCell(countx, ySize-county,cell );
                        }
                    }
                }
            }
            }
            return tiledMap;
    }
    public void saveMap(TiledMap tiledMap, String atlasName){
        this. mapProperties=tiledMap.getProperties();
        this.mapProperties.put("atlasName", atlasName);
        int xSize=mapProperties.get("width", Integer.class);
        int ySize=mapProperties.get("height", Integer.class);
        MapLayers tileLayers=tiledMap.getLayers();
        this.layers=tileLayers.size();
        cells= new CellSaver[layers][xSize][ySize];
        layerNames= new String[layers];
        for(int count=0; count<layers; count++) {
        TiledMapTileLayer layer= (TiledMapTileLayer) tileLayers.get(count);
        layerNames[count]=layer.getName();
            for (int countx = 0; countx < xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    CellSaver saver;
                    Cell cell = layer.getCell(countx, county);
                    if (cell != null) { // cell has a tile associated with it save the cells information
                        saver=new CellSaver();
                        saver.setFlipHorizontal(cell.getFlipHorizontally());
                        saver.setFlipVertical(cell.getFlipVertically());
                        Class tileClass = cell.getTile().getClass();
                        TiledMapTile tile = cell.getTile();
                        if (tileClass.equals(AtlasStaticTiledMapTile.class)) {
                            AtlasStaticTiledMapTile tile2 = (AtlasStaticTiledMapTile) tile;
                            saver.setRegionNames(tile2.getNames());
                            saver.setColor(tile2.getColor());
                            saver.setTileClass(AtlasStaticTiledMapTile.class);
                        } else if (tileClass.equals(AtlasAnimatedTiledMapTile.class)) {
                            AtlasAnimatedTiledMapTile tile2 = (AtlasAnimatedTiledMapTile) tile;
                            saver.setAnimated(true);
                            saver.setRegionNames((tile2.getNames()));
                            saver.setColor(tile2.getColor());
                            saver.setTileClass(AtlasAnimatedTiledMapTile.class);
                        }


                        cells[count][countx][ySize-county]=saver;


                    }
                }
            }
        }
    }





}
