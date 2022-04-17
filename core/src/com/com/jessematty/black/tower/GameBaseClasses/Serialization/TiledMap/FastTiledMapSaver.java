package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;

public class FastTiledMapSaver implements TiledMapSaver {
   protected  CellSaver[] [] [] cells; // the saved tiled map cells
    protected int layers; // number of map layers
   protected String [] layerNames; // the names of the layers
    protected MapProperties mapProperties = new  MapProperties();



    public FastTiledMapSaver() {
    }

    public TiledMap loadMap(GameAssets assets){
        int xSize=mapProperties.get("width", Integer.class);
        int ySize=mapProperties.get("height", Integer.class);
        int tileSizeX=mapProperties.get("tilewidth", Integer.class);
        int tileSizeY=mapProperties.get("tileheight", Integer.class);
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
                            AtlasNamedAtlasRegion region=assets.getAtlasRegionByName(regionNames[count], atlasName);
                            if(region!=null){
                                regions.add(new AtlasStaticTiledMapTile(region));
                            }
                        }
                        cell.setTile(new AtlasAnimatedTiledMapTile(saver.getInterval(), regions));
                        layer.setCell(countx, ySize-county-1,cell );
                    }
                    else{
                        String [] name= saver.getRegionNames();
                        AtlasNamedAtlasRegion region=assets.getAtlasRegionByName(name[0], atlasName);
                        if(region!=null){
                            AtlasStaticTiledMapTile tiledMapTile= new AtlasStaticTiledMapTile(region);
                            tiledMapTile.setColor(saver.getColor());
                            cell.setTile(new AtlasStaticTiledMapTile(region));
                            layer.setCell(countx, ySize-county-1,cell );
                        }
                    }
                }
            }
            }
            return tiledMap;
    }
    public void saveMap(TiledMap tiledMap) throws MapLoadingException {
        this. mapProperties=tiledMap.getProperties();

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
                        if (tileClass.equals(AtlasStaticTiledMapTile.class )) {
                            AtlasStaticTiledMapTile atlasStaticTiledMapTile = (AtlasStaticTiledMapTile) tile;
                            saver.setRegionNames(atlasStaticTiledMapTile.getNames());
                            saver.setColor(atlasStaticTiledMapTile.getColor());
                            saver.setTileClass(AtlasStaticTiledMapTile.class);
                        } else if (tileClass.equals(AtlasAnimatedTiledMapTile.class)) {
                            AtlasAnimatedTiledMapTile tile2 = (AtlasAnimatedTiledMapTile) tile;
                            saver.setAnimated(true);
                            saver.setRegionNames((tile2.getNames()));
                            saver.setColor(tile2.getColor());
                            saver.setTileClass(AtlasAnimatedTiledMapTile.class);
                        }

                        else{

                            throw new MapLoadingException("Invalid Tile Class  at Square  X "+countx +",  Y "+county );
                        }


                        cells[count][countx][ySize-county-1]=saver;


                    }
                }
            }
        }
    }

    @Override
    public MapProperties getMapProperties() {
        return mapProperties;
    }


}





