package com.jessematty.black.tower.Editor.Tools.MapTools;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.NamedTiledMapTileLayer;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.NumberedTile;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.ColorUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Loaders.Copy.CopyObject;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Generators.MapGenerators.NumberMapGenerator;
import com.jessematty.black.tower.Generators.Sets.MaskMode;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.File;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MapTools {
    private final CopyObject copyObject;
    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;
    private final GameAssets gameAssets;
    private  static final BitMask bitMask= new BitMask();
    private static AtlasNamedAtlasRegion emptyGridTile;
    private static AtlasNamedAtlasRegion emptyTile;
    public MapTools(MapEditScreen edit) {
        this.mapEditScreen = edit;
        this.gameAssets = edit.getGameAssets();
        this.copyObject = new CopyObject(gameAssets);
        emptyGridTile = gameAssets.getAtlasRegionByName("emptyGridTile", "editorAssets");
        emptyTile = gameAssets.getAtlasRegionByName("empty", "editorAssets");
    }
    public static void changeMapSize(GameMap map, int xSize, int ySize) { // used to increase or decrease map  size in the editor
        LandSquareTile[][] tiles = new LandSquareTile[xSize][ySize];
        int currentXSize = map.getXTiles();
        int currentYSize = map.getYTiles();
        // copy landsquare tile data
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                if (countx < currentXSize && county < currentYSize) {
                    tiles[countx][county] = map.getMapSquare(countx, county);
                }
            }
        }
        map.setMap(tiles);
        //copy tiled map data
        int tileSizeX = map.getTileWidth();
        int tileSizeY = map.getTileHeight();
        TiledMap currentTiledMap = map.getTiledMap();
        int layers = currentTiledMap.getLayers().size();
        TiledMap newTiledMap = new TiledMap();
        MapLayers currentLayers = currentTiledMap.getLayers();
        for (int count = 0; count < layers; count++) {
            TiledMapTileLayer oldTiledMapTileLayer = (TiledMapTileLayer) currentLayers.get(count);
            TiledMapTileLayer newTiledMapTileLayer = new TiledMapTileLayer(xSize, ySize, tileSizeX, tileSizeY);
            for (int countx = 0; countx < xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    newTiledMapTileLayer.setCell(countx, county, oldTiledMapTileLayer.getCell(countx, county));
                }
            }
        }
    }
    public static void changeWorldSize(World world, int xMaps, int yMaps) {// used to increase or decrease world size in the editor
        LandMap[][] worldMap = new LandMap[xMaps][yMaps];
        int currentXMaps = world.getXMaps();
        int currentYMaps = world.getYMaps();
        for (int countx = 0; countx < xMaps; countx++) {
            for (int county = 0; county < yMaps; county++) {
                if (countx < currentXMaps && county < currentYMaps) {
                    worldMap[countx][county] = world.getMap(countx, county);
                }
            }
        }
    }

    public  static TiledMap loadTMXMapFromFile(GameAssets assets) { // loads WoodWand tiled landSquareTileMap from WoodWand file
        File image = null;
        Texture texture = null;
        JFrame frame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        frame.add(chooser);
        frame.toFront();
        frame.setVisible(true);
        File file = chooser.getSelectedFile();
        String path = file.getPath();
        String extension = FileUtilities.getExtensionOfFile(file);
        if (extension.equalsIgnoreCase("tmx")) {
            TiledMap map = new TmxMapLoader().load(path);
            assets.getAssetManager().load(path, TiledMap.class);
            assets.getAssetManager().finishLoading();
            return map;
        }
        return null;
    }
    public LandSquareTile[][] copyTiles(LandSquareTile[][] tiles) {
        LandSquareTile[][] copiedTiles = copyObject.copyObject(tiles, LandSquareTile[][].class);
        return copiedTiles;
    }
    public static World newWorld(String name, int xMaps, int yMaps) {
        World world = new World(xMaps, yMaps, name);
        LandMap[][] maps = new LandMap[xMaps][yMaps];
        for (int countx = 0; countx < xMaps; countx++) {
            for (int county = 0; county < yMaps; county++) {
                maps[countx][county] = new LandMap();
            }
        }
        return world;
    }
    public static LandMap newLandMap(double gravity, String name, int xSize, int ySize, int tileSizeX, int tileSizeY) {
        LandMap map = new LandMap();
        LandSquareTile[][] tiles = new LandSquareTile[xSize][ySize];
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                tiles[countx][county] = new LandSquareTile(countx, county, ySize);
            }
        }
        TiledMap tiledMap = new TiledMap();
        tiledMap.getProperties().put("width", (float) xSize);
        tiledMap.getProperties().put("height", (float) ySize);
      NamedTiledMapTileLayer tiledMapTileLayer = new NamedTiledMapTileLayer(xSize, ySize, tileSizeX, tileSizeY);
        tiledMapTileLayer.setDisplayName("layer 0");
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                Cell cell = new Cell();
                cell.setTile(new AtlasStaticTiledMapTile((emptyGridTile)));
                tiledMapTileLayer.setCell(countx, county, cell);
            }
        }
        tiledMap.getLayers().add(tiledMapTileLayer);
        //  create the preview tile layer
        NamedTiledMapTileLayer previewLayer = new NamedTiledMapTileLayer(xSize, ySize, tileSizeX, tileSizeY);
        previewLayer.setDisplayName("newRegion");
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                Cell cell = new Cell();
                cell.setTile(new AtlasStaticTiledMapTile((emptyGridTile)));
                previewLayer.setCell(countx, county, cell);
            }
        }
        tiledMap.getLayers().add(previewLayer);

        map.setMap(tiles);
        map.getGameMapSettings().getSettings().put("name", name);
        map.getGameMapSettings().getSettings().put("gravity", gravity);
        map.setTiledMap(tiledMap);
        map.setTileSize(tileSizeX, tileSizeY);
        return map;
    }
    public  LandSquareTile [] [] newTileMap (int xSize, int ySize){
        LandSquareTile [] [] landSquareTiles= new LandSquareTile[xSize][ySize];
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {

                landSquareTiles[countx][county]= new LandSquareTile(countx, county, ySize);


            }

            }

 return  landSquareTiles;
    }



    public static Building newBuilding(Building map, double gravity, String name, int xSize, int ySize, int tileSize) {
        if (map == null) {
            map = new Building();
        }
        LandSquareTile[][] tiles = new LandSquareTile[xSize][ySize];
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                tiles[countx][county] = new LandSquareTile(countx, county, ySize);
            }
        }
        TiledMap tiledMap = new TiledMap();
        TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer(tileSize, tileSize, xSize, ySize);
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                Cell cell = new Cell();
                cell.setTile(new AtlasStaticTiledMapTile((emptyGridTile)));
                tiledMapTileLayer.setCell(countx, county, cell);
            }
        }
        tiledMap.getLayers().add(tiledMapTileLayer);
        map.setMap(tiles);
        map.getGameMapSettings().getSettings().put("name", name);
        map.getGameMapSettings().getSettings().put("gravity", gravity);
        map.setTiledMap(tiledMap);
        return map;
    }
    public static NamedTiledMapTileLayer newMapLayer(String name, int xTiles, int yTiles, int tileSizeX, int tileSizeY) {
        NamedTiledMapTileLayer layer = new NamedTiledMapTileLayer(xTiles, yTiles, tileSizeX, tileSizeY);
        layer.setDisplayName(name);
        for (int countx = 0; countx < xTiles; countx++) {
            for (int county = 0; county < yTiles; county++) {
                Cell cell=new Cell();
                layer.setCell(countx, county, cell);
            }
        }
        return  layer;
    }
    // checks to see if map name already exists  in a given world  map names must be unique
    public static boolean  mapNameCheck(String name , World world){
        GameMap [] []  maps=world.getWorldMap();
        
       int  xSize=maps.length;
       int ySize=maps[0].length;
       
       Array<String> mapNames= new Array<String>();
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                GameMap map=maps[countx][county];
                if(map!=null) {
                    String mapName=map.getGameMapSettings().getSimpleSetting("name", String.class);

                    if(mapName!=null && !mapName.isEmpty()) {
                        mapNames.add(mapName);
                    }
                }
            }
        }
        
            
        return InList.isInList(mapNames, name);
      
    }
   // loads a  new tmx landSquareTileMap made with tiled TileMap program
        public   TiledMap loadTmxMap( GameMap gameMap,   GameAssets gameAssets, String path, boolean expandMapToFit, boolean clipMapToFit) throws MapLoadingExeception {
            TiledMap tiledMap = gameAssets.loadExternalTMXMap(path);
            MapProperties mapProperties=tiledMap.getProperties();
            int width= mapProperties.get("width", java.lang.Integer.class);
            int  height= mapProperties.get("height", Integer.class);
            int  tileSizeX=mapProperties.get("tilewidth",Integer.class);
            int tileSizeY=mapProperties.get("tileheight", Integer.class);
            if(width==gameMap.getXTiles() && height==gameMap.getYTiles()){
                gameMap.setTiledMap(tiledMap);
                    return  tiledMap;
        }
            int xSize=gameMap.getXTiles();
            int ySize=gameMap.getYTiles();
            if(xSize<=width && ySize<=height){
                changeGameMapSize(gameMap.getMap(),   width, height );
                gameMap.setTiledMap(tiledMap);
                return  tiledMap;
            }
           if(clipMapToFit==true){
               
                changeTiledMapSize(tiledMap,tileSizeX, tileSizeY, gameMap.getXTiles() , gameMap.getYTiles());
                return  tiledMap;
            }
           // if tiled map is bigger than current mpa size increase the map size;
            if(expandMapToFit==true){
                    int expendX=width-gameMap.getXTiles();
                   int expendY=width-gameMap.getXTiles();
                    if(clipMapToFit==false) {
                        if (expendX < 0) {
                            expendX = 0;
                        }
                        if (expendY < 0) {
                            expendY = 0;
                        }
                    }
                   LandSquareTile [] [] tiles= changeGameMapSize(gameMap.getMap(), expendX, expendY);
                    gameMap.setMap(tiles);
                    return  tiledMap;
                }
            throw new MapLoadingExeception("Unable to TMX map to GameMap Check Size?");
    }

    // creates  new  named  texture atlas  with a given  name texture atlas extracts  all the texture regions  from a tiled map and adds them
    public static TextureAtlas getAtlasFromTiledMap(TiledMap map, String atlasName, String tileNamePrefix){

        MapLayers mapLayers=map.getLayers();
        int size=mapLayers.size();
        NamedTextureAtlas tiledMapTextureAtlas=new NamedTextureAtlas(atlasName);
        for(int count=0; count<size; count++) {
            TiledMapTileLayer layer= (TiledMapTileLayer) mapLayers.get(count);
            int width=layer.getWidth();
            int height=layer.getHeight();
            for(int countx=0; countx<width; countx++) {
                for (int county = 0; county < height; county++) {
                    Cell cell = layer.getCell(countx, county);
                    if (cell != null) {

                        TiledMapTile tile = cell.getTile();
                        if (tile != null) {

                            TextureRegion region = tile.getTextureRegion();
                            if(!InList.isInList(tiledMapTextureAtlas, region)){
                                String name=tileNamePrefix+UUID.randomUUID().toString();
                                AtlasRegion atlasRegion= new AtlasRegion(region);
                                atlasRegion.name=name;
                                AtlasNamedAtlasRegion namedRegion = new AtlasNamedAtlasRegion(atlasRegion, atlasName);
                                tiledMapTextureAtlas.addRegion( name, namedRegion);

                            }

                        }
                    }

                }
            }

        }

        return  tiledMapTextureAtlas;

    }


    // cadd the all of the texture regions from a tiled map to a named texture atlas
    public static TextureAtlas addTiledMapRegionsToAtlas(TiledMap map, NamedTextureAtlas atlas,  String tileNamePrefix){

        MapLayers mapLayers=map.getLayers();
        int size=mapLayers.size();
        for(int count=0; count<size; count++) {
            TiledMapTileLayer layer= (TiledMapTileLayer) mapLayers.get(count);
            int width=layer.getWidth();
            int height=layer.getHeight();
            for(int countx=0; countx<width; countx++) {
                for (int county = 0; county < height; county++) {
                    Cell cell = layer.getCell(countx, county);
                    if (cell != null) {

                        TiledMapTile tile = cell.getTile();
                        if (tile != null) {

                            TextureRegion region = tile.getTextureRegion();
                            if(!InList.isInList(atlas, region)){
                                String name=tileNamePrefix+UUID.randomUUID().toString();
                                AtlasRegion atlasRegion= new AtlasRegion(region);
                                atlasRegion.name=name;
                                AtlasNamedAtlasRegion namedRegion = new AtlasNamedAtlasRegion(atlasRegion);
                                atlas.addRegion( name, namedRegion);

                            }

                        }
                    }

                }
            }

        }

        return  atlas;

    }


    private   LandSquareTile [] [] changeGameMapSize(LandSquareTile [] []  tilesToCopy, int width, int height ){
        LandSquareTile[ ] [] expandedMap= new LandSquareTile[width][height];
        newLandSquareTiles(expandedMap, 0, 0, width, height, height);
        copyMapTiles(tilesToCopy, expandedMap, 0 , 0 , width, height);
        return  expandedMap;
    }
    public void newLandSquareTiles(LandSquareTile [] [] map, int xStart, int yStart, int xEnd, int yEnd,  int yTiles) {
        for (int countx = xStart; countx < xEnd; countx++) {
            for (int county = yStart; county < yEnd; county++) {
                map[countx][county]= new LandSquareTile(countx, county, yTiles);
            }
        }
    }
    
//creates a new expended tiled map from the old tiled map
    private   TiledMap changeTiledMapSize(TiledMap tiledMap, int tileSizeX, int tileSizeY, int width, int height){
        TiledMap expendedMap= new TiledMap();
        copyLayersToMap(tiledMap, expendedMap, tileSizeX, tileSizeY, 0, 0, width, height);
        return  expendedMap;
    }
    // copies the layers of one tiles map to another
    public   void copyLayersToMap(TiledMap tiledMapToCopy, TiledMap tiledMapToCopyTo, int tileSizeX, int tileSizeY,  int xStart, int yStart, int xEnd, int yEnd){
        if(tiledMapToCopy==null || tiledMapToCopyTo==null || tiledMapToCopy==tiledMapToCopyTo) {
            return;
        }
        MapLayers mapLayersToCopy=tiledMapToCopy.getLayers();
        MapLayers mapLayersToCopyTo=tiledMapToCopyTo.getLayers();
        int numberOfLayersToCopy=mapLayersToCopy.size();
        int numberOfLayersToCopyTo=mapLayersToCopyTo.size();
      MapProperties mapProperties=  tiledMapToCopyTo.getProperties();
        int width=mapProperties.get("width", Integer.class);
        int height=mapProperties.get("height", Integer.class);
        // if not enough layers to copy to create them
        if(numberOfLayersToCopy>numberOfLayersToCopyTo){
            int difference=numberOfLayersToCopy-numberOfLayersToCopyTo;
            for(int count=0; count<difference; count++){
                newMapLayer("layer "+mapLayersToCopyTo.size(), width, height, tileSizeX, tileSizeY );
            }
        }
        for (int countLayers =0;countLayers < numberOfLayersToCopyTo; countLayers++) {
            NamedTiledMapTileLayer namedTiledMapTileLayerToCopy= (NamedTiledMapTileLayer) tiledMapToCopy.getLayers().get(countLayers);
            NamedTiledMapTileLayer namedTiledMapTileLayerToCopyTo= (NamedTiledMapTileLayer) tiledMapToCopyTo.getLayers().get(countLayers);
            for (int countx = xStart; countx < xEnd; countx++) {
                for (int county = yStart; county < yEnd; county++) {
                    Cell cellToCopy= namedTiledMapTileLayerToCopy.getCell(countx, county);
                   if(cellToCopy!=null) {
                       Cell cellToCopyTo = namedTiledMapTileLayerToCopyTo.getCell(countx, county);
                       if(cellToCopyTo==null){
                           namedTiledMapTileLayerToCopyTo.setCell(countx, county, new Cell());
                           cellToCopyTo = namedTiledMapTileLayerToCopyTo.getCell(countx, county);
                       }
                       TiledMapTile tiledMapTile=cellToCopy.getTile();
                       if(tiledMapTile instanceof  AtlasStaticTiledMapTile){
                           cellToCopyTo.setTile(new AtlasStaticTiledMapTile((AtlasStaticTiledMapTile) tiledMapTile));
                       }
                       else   if(tiledMapTile instanceof AtlasAnimatedTiledMapTile){
                           cellToCopyTo.setTile(new AtlasAnimatedTiledMapTile((AtlasAnimatedTiledMapTile) tiledMapTile));
                       }
                   }
                }
            }
        }
    }
    public   void copyMapTiles(LandSquareTile [] [] tilesToCopy, LandSquareTile [] [] tilesToCopyTo,  int xStart, int yStart, int xEnd, int yEnd){
       if(tilesToCopy==null || tilesToCopyTo==null || tilesToCopy==tilesToCopyTo) {
           return;
       }
            for (int countx = xStart; countx < xEnd; countx++) {
                for (int county = yStart; county < yEnd; county++) {
                    LandSquareTile tileToCopy=tilesToCopy[countx][county];
                    if(tileToCopy!=null) {
                        tilesToCopyTo[countx][county]= new CopyObject(gameAssets).copyObject(tileToCopy, LandSquareTile.class)     ;
                    }
                }
            }
        }
    public static  Cell [] [] [] copyCells(Cell [] [] [] cellsToPlace){
        int xCells=cellsToPlace[0].length;
        int yCells=cellsToPlace[0][0].length;
        int layers= cellsToPlace.length;
        Cell [] [] [] copiedCells= new Cell[layers][xCells][yCells];
        for (int countLayers = 0; countLayers < layers; countLayers++) {
            for (int countx = 0; countx < xCells; countx++) {
                for (int county = 0; county < yCells; county++) {
                    Cell oldCell=cellsToPlace[countLayers][countx][county];
                    copiedCells[countLayers][countx][county]=copyCell(oldCell);
                }
            }
        }
        return copiedCells;
    }
    public static   Cell  copyCell( Cell oldCell ){
        Cell copiedCell= new Cell();
        copiedCell.setRotation(oldCell.getRotation());
        copiedCell.setFlipHorizontally(oldCell.getFlipHorizontally());
        copiedCell.setFlipVertically(oldCell.getFlipVertically());
        if(oldCell.getTile() instanceof  AtlasStaticTiledMapTile) {
            copiedCell.setTile(new AtlasStaticTiledMapTile((AtlasStaticTiledMapTile) oldCell.getTile()));
        }
        else  if(oldCell.getTile() instanceof AtlasAnimatedTiledMapTile){
            copiedCell.setTile(new AtlasAnimatedTiledMapTile((AtlasAnimatedTiledMapTile) oldCell.getTile()));
        }
        return  copiedCell;
    }
    public static  Cell  [] [] copyCells2D(Cell  [] []  cellsToPlace){
        int xCells=cellsToPlace.length;
        int yCells=cellsToPlace[0].length;
        Cell  [] [] copiedCells= new Cell[xCells][yCells];
        for (int countx = 0; countx < xCells; countx++) {
            for (int county = 0; county < yCells; county++) {
                Cell oldCell=cellsToPlace[countx][county];
                copiedCells[countx][county]=copyCell(oldCell);
            }
        }
        return copiedCells;
    }


    public void addEntity(Entity entity, World world){


       world.addEntityToWorld(entity);

    }

    public static void addMaskedLayers(GameAssets assets, GameMap map,  Array<TileSet> tileSets, boolean addStat){


    }






    // create a tile set from a folder of tile images with a given tile name  and matching bit numbers
    // in the format  of tileName.bitNumber.setNumber.extension ie water.10.0.png or sand.255.3.jpg setNumber is the number of random tiles  a given image has and it starts at 0
    // supported image  formats png and jpg

    public static  TileSet createTileSet(NamedTextureAtlas atlas, String path , String name){
        int []  bitNumbers=bitMask.getEightBitMaskWangValues();
        File [] files= new File(path).listFiles();
        TileSet tileSet= new TileSet(name);

        int size=files.length;
        for(int count=0; count<size; count++){


            File file=files[count];

            String fileName=file.getName();
            String []  parts = fileName.split("\\.");
            if(isTileFile(name, bitNumbers, parts)){


                // create texture region
                Texture texture= new Texture(path+FileUtilities.getFileSeparator()+file.getName());
                TextureRegion textureRegion=new TextureRegion(texture);
                String textureName=parts[0]+"."+parts[1]+"."+parts[2];


                // add region to atlas
                atlas.addRegion(textureName, textureRegion);


                // add region to tile set
                int bitNumber=Integer.valueOf(parts[1]);

                tileSet.addNumberedTile(bitNumber,new NumberedTile(bitNumber, textureName,atlas.getAtlasFileName()));



            }



        }





        return tileSet;

    }


    private static  boolean isTileFile( String name, int [] bitNumbers,  String []  parts){
        if(!parts[0].equalsIgnoreCase(name)){
            return  false;

        }

        // if name  does not contain  a valid bit number return false
        for(int count=0; count<bitNumbers.length; count++) {
            String stringBitNumber=String.valueOf(bitNumbers[count]);
            if (!parts[1].equalsIgnoreCase(stringBitNumber)){
                return  false;

            }


        }

        // if the file is a png or jpg image file return true
        if(parts[4].equalsIgnoreCase("png") || parts[4].equalsIgnoreCase("jpg")){
            return true;
        }
         // is not supported image file return false

        return  false;



    }


    //  makes a tileMap from a bit mask map;
public   static  void addRandomMaskedLayers(int minValue, int maxValue, int smoothness,  GameAssets assets,  GameMap map, Array<TileSet> tileSets, boolean addStatToTile, MaskMode maskMode) {
    int xSize=map.getXTiles();
    int ySize=map.getYTiles();

    if(minValue>=maxValue){
        throw new IllegalArgumentException("Min Value is Greater  Than Max Value");

    }

    NumberMapGenerator numberMapGenerator= new NumberMapGenerator(xSize,ySize);
    int [] [] tileNumberMap=numberMapGenerator.makeNumberMap(maxValue, minValue , smoothness);
    Array<Integer> tileNumbers= numberMapGenerator.getMapNumbers();
    tileNumbers.sort();


        int numberOfSoilLayers= tileNumbers.size;

        TiledMap tiledMap=map.getTiledMap();
        MapLayers mapLayers=tiledMap.getLayers();
        LandSquareTile [] [] landSquareTileMap=map.getMap();
        ComponentMapper<NumericStats> numericStatComponentMapper= GameComponentMapper.getNumericStatsComponentMapper();


        for (int count = 0; count < numberOfSoilLayers; count++) {

            TiledMapTileLayer layer2 = new TiledMapTileLayer(xSize, ySize, 32, 32);
            layer2.setName("layer" + mapLayers.size());
            mapLayers.add(layer2);
            TiledMapTileLayer layer = new TiledMapTileLayer(xSize, ySize, 32, 32);
            layer.setName("layer" + mapLayers.size());
            mapLayers.add(layer);

        }
// make bit mask map
        int [] [] bitNumberMap=bitMask.makeTrimmedHeightTileBitMap(tileNumbers, tileNumberMap, maskMode); // make trimmed bit map

        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                for (int count = 0; count < numberOfSoilLayers; count++) {
                    TileSet tileSet=tileSets.get(count);
                    String atlasName=tileSet.getAtlasName();


                    if (tileNumberMap[countx][county] == tileNumbers.get(count)) {
                        Cell cell = new Cell();
                        AtlasNamedAtlasRegion region = assets.getAtlasRegionByName(getTileImage(tileSet, bitNumberMap[countx][county], count), atlasName);
                        cell.setTile(  new AtlasStaticTiledMapTile(region));
                        TiledMapTileLayer  layer = (TiledMapTileLayer) tiledMap.getLayers().get(count );
                        layer.setCell(countx, ySize-county, cell);
                        if(bitNumberMap[countx][county]!=255  && count>0) { // make the layer under the same as layer above
                            cornerCheck( assets, ySize, mapLayers, tileSet, tileNumberMap, countx, county, count, 1);
                        }
                    }






                }
            }
        }
    }




    private static Array<TileSet> makeTileSets( int numberOfNeededTileSets, boolean canReColor,  Array<TileSet> tileSets, MaskMode maskMode){
        if(numberOfNeededTileSets<tileSets.size){
            return  tileSets;
        }

        int numberOriginalTileSets=tileSets.size;
        Array<TileSet> usedTileSets= new Array<>();
        if((canReColor==false && tileSets.size<2) || tileSets.size<1){

            throw new IllegalArgumentException(" Not Enough Tile  Sets to Mask");
        }



        while(tileSets.size<numberOfNeededTileSets) {
            if (canReColor) {
                Color tileColor = ColorUtilities.randomColor(1, 1, 1, 1, true, true, true, true);
                TileSet tileSet=tileSets.get(RandomNumbers.getRandomNumber(0, tileSets.size));
                usedTileSets.add(tileSet);
                if(!InList.isInList(usedTileSets, tileSet)) {
                    TileSet newTileSet = new TileSet(tileSet);
                    tileSet.setColor(new NamedColor(tileColor));
                    tileSets.add(newTileSet);

                }

            }
            else{

                TileSet tileSet=tileSets.get(RandomNumbers.getRandomNumber(0, numberOriginalTileSets));
                usedTileSets.add(tileSet);
                if(!InList.isInList(usedTileSets, tileSet)) {
                    TileSet newTileSet = new TileSet(tileSet);
                    tileSets.add(newTileSet);

                }


            }




        }



        return  tileSets;



    }


    private static  boolean checkMask(TileSet tileSet){



        return  true;

    }

    // returns a tile image for bit mask number and a set  number  from a tile set
    private static String getTileImage(TileSet tileSet, int bitMaskNumber, int setNumber) {
        ObjectMap<Integer, Array<NumberedTile>> regionNames=tileSet.getSetRegionNames();
        AtlasNamedAtlasRegion atlasNamedAtlasRegion=null;
        Array<NumberedTile>  bitRegions= regionNames.get(bitMaskNumber);
        int random= RandomNumbers.getRandomNumber(0, bitRegions.size);
        return  bitRegions.get(random).getRegionName();





    }

    private static void cornerCheck( GameAssets assetts,  int ySize, MapLayers layers, TileSet tileSet, int[][] tileNumberMap, int countx, int county, int count, int number){
        TiledMapTileLayer  layer = (TiledMapTileLayer) layers.get(count - number);
        String atlasName=tileSet.getAtlasName();
        Cell cell = new Cell();
        int bitValue= bitMask.eightSideBitMapCalculator(tileNumberMap[countx][county]-number, countx, county, tileNumberMap);
        AtlasNamedAtlasRegion region;
        if(number>=count){
            region = assetts.getAtlasRegionByName(getTileImage(tileSet,bitValue, count ), atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if (bitValue!=255 ) {
            region = assetts.getAtlasRegionByName(getTileImage(tileSet,bitValue, count ), atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
        }
        else {
            region = assetts.getAtlasRegionByName(getTileImage(tileSet,255, count ), atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if(number<count) {
            number++;
            cornerCheck(assetts, ySize, layers, tileSet,tileNumberMap, countx, county, count, number);
        }
    }



}

    
    
    

