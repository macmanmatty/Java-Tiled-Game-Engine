package com.jessematty.black.tower.Generators.MapGenerators;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Interfaces.SerializableComponet;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Lights.LightSource;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.ColoredTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public  abstract class MapGenerator {
    com.jessematty.black.tower.Generators.MapGenerators.NumberMapGen heightMapMaker = new NumberMapGen();
    BitMask bitMask = new BitMask();
    GameMap map;
    GameAssets assetts;
    int xSize;
    int ySize;
    String atlasName="assetts";
    LandSquareTile[][] landSquareTileMap;
    TiledMap tiledMap;
    MapLayers mapLayers;
    TmxMapLoader loader= new TmxMapLoader();
    RandomNumbers value= new RandomNumbers();
    HashMap<String, Entity> loadedEntity= new HashMap<String, Entity>();
    protected ArrayList<LandSquareTile> enterGates = new ArrayList<LandSquareTile>();
    protected ArrayList<LandSquareTile> exitGates = new ArrayList<LandSquareTile>();
    protected transient Skin skin; // UI skin  for landSquareTileMap
    protected ArrayList<LightSource> lightSources = new ArrayList<LightSource>();
    public MapGenerator(GameAssets assetts, int xSize, int ySize) {
        this.assetts = assetts;
        this.xSize = xSize;
        this.ySize = ySize;
        tiledMap = new TiledMap();
        mapLayers = tiledMap.getLayers();
    }
    public GameMap makeMap() {
        return map;
    }
    protected void loadEntity(String path) {
        Entity[] things = (Entity[]) assetts.loadObject(path, Entity[].class);
        int size = things.length;
        for (int count = 0; count < size; count++) {
             ImmutableArray<Component> components= things[count].getComponents();
             int size2=components.size();
             for(int count2=0; count2<size2; count2++){
                 Component component=components.get(count2);
                 if(component instanceof SerializableComponet){
                     ((SerializableComponet) component).deSerialize(assetts);
                 }
             }
        }
    }
protected void eraseTiles (ArrayList<LandSquareTile> tiles, TiledMapTileLayer layer){ // sets all of the tiled map  tiles
        // that corrosponed to the landsquare tiles for a given layer in the array to null so they aren't rawn;
    Cell cell=null;
        int size=tiles.size();
        for(int count=0; count<size; count++){
            layer.setCell(tiles.get(count).getLocationX(), tiles.get(count).getLocationY(), cell);
        }
}
    public ArrayList< ArrayList<LandSquareTile>> getAreas(int [] [] map ,  int number, boolean diagnols){
        int xSize=map.length;
        int ySize=map[0].length;
        ArrayList<ArrayList<LandSquareTile>> areas= new ArrayList<ArrayList<LandSquareTile>>((int)((xSize*ySize)/9));
        for(int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                if (map[countx][county]==number){
                    boolean inArea=landSquareTileMap[countx][county].isInArea();
                    if(inArea==false){
                        areas.add(findArea(map, number, countx, county, diagnols));
                    }
                }
            }
        }
        uncheckTiles();
        return areas;
        }
    private  boolean loadPremadeSections(String path, String name){
        boolean loadedAllMaps=true;
        int counter=0;
        String mapPath=path+name+counter+".tmx";
        File file= new File(mapPath);
        boolean loaded = loadPremadeSection(mapPath);
        while(file.exists()==true) {
            mapPath=path+name+counter+".tmx";
            file= new File(mapPath);
            loaded= loadPremadeSection(mapPath);
            if(loaded==false){
                loadedAllMaps=false;
            }
        }
        return loadedAllMaps;
    }
    private boolean loadPremadeSection( String path){ // loads  the premade parts of the map in tmx format
        TiledMap mapSection=loader.load(path);
        if(tiledMap==null){
            return false;
        }
        MapLayers partialMapLayers=mapSection.getLayers();
        int numberOflayers=mapLayers.size();
        int locationX=value.getRandomNumber(0, xSize-1);
        int locationY=value.getRandomNumber(0, ySize-1);
        MapProperties properties=tiledMap.getProperties();
        int width=properties.get("width", Integer.class);
        int height=properties.get("height", Integer.class);
        int sizeX=width+locationX;
        int sizeY=height+locationY;
        boolean canadd= areaCheck(locationX, locationY, sizeX, sizeY);
        if(canadd==true) {
            int currentlayers = this.mapLayers.size();
            int layersDifference = numberOflayers - currentlayers;
            if (layersDifference > 0) { // map scetion has more layers then is current in the tiledmap addEntity more layers
                for (int count = 0; count < layersDifference; count++) {
                    partialMapLayers.add(new TiledMapTileLayer(xSize, ySize, 32, 32));
                }
            }
            for(int count=0; count<numberOflayers; count++) {
                TiledMapTileLayer mainMapLayer= (TiledMapTileLayer) mapLayers.get(count);// the games tiles map layer
                TiledMapTileLayer partialMapLayer= (TiledMapTileLayer) partialMapLayers.get(count); // loaded tiled map layer
                MapObjects objects= partialMapLayer.getObjects(); // the loaded map objects
                for (int countx = locationX; countx < sizeX; countx++) {
                    for (int county = locationX; county < sizeY; county++) {
                        mainMapLayer.setCell(countx, county, partialMapLayer.getCell(countx, county));
                        landSquareTileMap[countx][county].setUnchangeable(true);
                        AtlasNamedAtlasRegion region= (AtlasNamedAtlasRegion) partialMapLayer.getCell(countx, county).getTile().getTextureRegion();
                       String fullImageName= ( partialMapLayer).getCell(countx, county).getTile().getProperties().get("image", String.class); // get the image name
                        float r=  ( partialMapLayer).getCell(countx, county).getTile().getProperties().get("colorR", Float.class); // get the image name
                        float g=  ( partialMapLayer).getCell(countx, county).getTile().getProperties().get("colorG", Float.class); // get the image name
                        float b=  ( partialMapLayer).getCell(countx, county).getTile().getProperties().get("colorB", Float.class); // get the image name
                        float a=  ( partialMapLayer).getCell(countx, county).getTile().getProperties().get("colorA", Float.class); // get the image name
                        float brightness=  ( partialMapLayer).getCell(countx, county).getTile().getProperties().get("brightness", Float.class); // get the image name
                        boolean animated=( partialMapLayer).getCell(countx, county).getTile().getProperties().get("animated", Boolean.class);
                        int frames=( partialMapLayer).getCell(countx, county).getTile().getProperties().get("frames", Integer.class);
                        float duration=( partialMapLayer).getCell(countx, county).getTile().getProperties().get("duration", Float.class);
                        String[]  imageName=fullImageName.split("\\."); // get image name texture atlas uses
                        ColoredTiledMapTile tile=new AtlasStaticTiledMapTile( region, imageName[0]);
                         if(r!=0 && g!=0 && b!=0 && a!=0 ){
                             
                             tile.setColor(new NamedColor(new Color(r, g, b, a)));
                         }
                         

                         if(animated==true){
                             AtlasStaticTiledMapTile [] regions= new AtlasStaticTiledMapTile[frames];
                             String []  name=imageName[0].split("(?=\\d)(?<!\\d)");
                             for(int count2=0; count2<frames; count2++ ){
                                 AtlasNamedAtlasRegion region2=assetts.getAtlasRegionByName(name[0]+count, atlasName);
                                 AtlasStaticTiledMapTile tile2= new AtlasStaticTiledMapTile(region2, name[0]);
                                 if(r!=0 && g!=0 && b!=0 && a!=0 ){
                                     tile.setColor(new NamedColor(new Color(r, g, b, a)));
                                 }

                                 regions[count]=tile2;
                             }
                            Array<AtlasStaticTiledMapTile> tiles= new Array<AtlasStaticTiledMapTile>();
                             tiles.addAll(regions);
                             tile= new AtlasAnimatedTiledMapTile(duration, tiles);
                         }
                         
                        Cell cell = new Cell();
                         cell.setTile(tile);
                         
                        mainMapLayer.setCell(locationX, locationY, cell);
                    }
                }
                int numberOfObjects=objects.getCount();
                for(int count2=0;  count2<numberOfObjects; count2++){
                  TiledMapTileMapObject mapObject= (TiledMapTileMapObject) objects.get(count);
                  placeObject(mapObject,  locationX, locationY);
                }
            }
            return true;
        }
        return false;
    }
    
    // gets a set texture From a set
    protected AtlasNamedAtlasRegion getTexture(String name , String atlasName){
       int  setNumber=value.getRandomNumber(0,21);
       AtlasNamedAtlasRegion region=assetts.getAtlasRegionByName(name+"."+setNumber, atlasName+".atlas");
       
       if(region==null){
           region=assetts.getAtlasRegionByName(name+"."+0, atlasName+".atlas");
       }
       
       region.setAtlasName(atlasName);
        return region;
    }
    private void placeObject( TiledMapTileMapObject mapObject, int areaX, int areaY) {
        MapProperties properities=mapObject.getProperties();
        float locationX=mapObject.getX()+areaX*32f;
        float locationY=mapObject.getY()+areaY*32f;
        String id= properities.get("id", String.class);
        String objectClassName=properities.get("class", String.class);
        Entity thing=loadedEntity.get(id);
            LandSquareTile location=map.getTileFromWorldUnitCoordinates(locationX, locationY);
            location.addEntity(thing);
        Color color=mapObject.getColor();
        mapObject.setTile(null);
    }
    public boolean areaCheck(int locationX, int locationY, int sizeX, int sizeY) { // check to see something  of map can be placed at a given loaction
        if(sizeX>=this.xSize || sizeY>=this.ySize){ // if map requires more space the map has return false
            return false;
        }
        for(int countx=locationX; countx<sizeX; countx++){
            for(int county=locationY; county<sizeY; county++){
                if(landSquareTileMap[countx][county].isUnchangeble()==true){
                    return false;
                }
            }
        }
        return true;
    }
    protected  ArrayList<LandSquareTile> findArea(int [] [] map, int number, int locationX, int locationY, boolean diagonals){
        int xSize=map.length;
        int ySize=map[0].length;
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>(xSize*ySize);
        tiles.add(landSquareTileMap[locationX][locationY]);
        for(int count=0; count<tiles.size(); count++) {
            LandSquareTile tile=tiles.get(count);
            if(tile.isChecked()==false){
                tiles.addAll( findTiles(tile.getLocationX(), tile.getLocationY(), number, map, diagonals));
                tile.setChecked(true);
                tile.setInArea(true);
            }
        }
        for(int count=0; count<tiles.size(); count++){
            if(tiles.get(count).isChecked()==false){
                System.exit(0);
            }
        }
        return  tiles;
    }
    protected  ArrayList<LandSquareTile> findTiles(int x,  int y, int number, int [] [] map, boolean diagnols){ // function for finding all landsquare tiles
// that share number on number map.
        int xSize=map.length;
        int ySize=map[0].length;
        ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
        if(diagnols==true) {
            for (int countx = -1; countx < 2; countx++) {
                for (int county = -1; county < 2; county++) {
                    int xx = x + countx;
                    int yy = y + county;
                    if(countx==0 && county==0){
                        continue;
                    }
                    if (xx > 0 && yy > 0 && xx < xSize && yy < ySize && map[countx][county] == number) {
                        tiles.add(landSquareTileMap[xx][yy]);
                    }
                }
            }
        }
        else {
            for (int countx = -1; countx < 2; countx++) {
                for (int county = -1; county < 2; county++) {
                    int xx = x + countx;
                    int yy = y + county;
                    if(countx==-1 && county==-1){
                        continue;
                    }
                    if(countx==1 && county==1){
                        continue;
                    }
                    if(countx==0 && county==0){
                        continue;
                    }
                    if(countx==-1 && county==1){
                        continue;
                    }
                    if(countx==1 && county==-1){
                        continue;
                    }
                    if (xx > 0 && yy > 0 && xx < xSize && yy < ySize && map[xx][yy] == number) {
                        tiles.add(landSquareTileMap[xx][yy]);
                    }
                }
            }
        }
        return tiles;
    }
    protected void  uncheckTiles(){ // function to set all of the landsquare tile check booleran to false
                for (int countx = 0; countx < xSize; countx++) {
                    for (int county = 0; county < ySize; county++) {
                        landSquareTileMap[countx][county].setChecked(false);
                        landSquareTileMap[countx][county].setInArea(false);
                    }
                }
            }
    public ArrayList<LandSquareTile> findArea(Entity thing,  LandSquareTile location, boolean diagonals){
            ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
            tiles.add(location);
            boolean foundNumber=true;
            while(foundNumber==true){
                for(int count=0; count<tiles.size(); count++) {
                    LandSquareTile tile=tiles.get(count);
                   Array<Entity> things =tile.getEntities();
                    if(tile.isChecked()==false){
                        tiles.addAll( findTilesMatchingThing(tile.getLocationX(), tile.getLocationY(), thing, diagonals));
                        tile.setChecked(true);
                    }
                }
            }
            return  tiles;
        }
    protected  Collection< LandSquareTile> findTilesMatchingThing(int locationx, int locationy, Entity thing, boolean diagnols) { // fins all adjcent tiles  that have  the same object on them
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
        if(diagnols==true) {
            for (int countx = -1; countx < 2; countx++) {
                for (int county = -1; county < 2; county++) {
                    int xx = locationx + countx;
                    int yy = locationy + county;
                    if (countx == 0 && county == 0) {
                        continue;
                    }
                    if (xx > 0 && yy > 0 && xx < xSize && yy < ySize) {
                        boolean tileHas = thingCheck(thing, landSquareTileMap[xx][yy]);
                        if (tileHas == true) {
                            tiles.add(landSquareTileMap[xx][yy]);
                        }
                    }
                }
            }
        }
        else {
            for (int countx = -1; countx < 2; countx++) {
                for (int county = -1; county < 2; county++) {
                    int xx = locationx + countx;
                    int yy = locationy + county;
                    if (countx == 0 && county == 0) {
                        continue;
                    }
                    if(countx==-1 && county==-1){
                        continue;
                    }
                    if(countx==1 && county==1){
                        continue;
                    }
                    if(countx==-1 && county==1){
                        continue;
                    }
                    if(countx==1 && county==-1){
                        continue;
                    }
                    if (xx > 0 && yy > 0 && xx < xSize && yy < ySize) {
                        boolean tileHas = thingCheck(thing, landSquareTileMap[xx][yy]);
                        if (tileHas == true) {
                            tiles.add(landSquareTileMap[xx][yy]);
                        }
                    }
                }
            }
        }
        return tiles;
    }
    protected boolean thingCheck(Entity thing, LandSquareTile location){ // chedck wether or  not tile  has a given object based on name
         String name=thing.toString();
        Array<Entity> things=location.getEntities();
         int size=things.size;
         for(int count=0; count<size; count++){
             if(things.get(count).toString().equals(name)){
                 return true;
             }
         }
        return false;
    }
    protected boolean placeRegion(AtlasNamedAtlasRegion region, LandSquareTile tile, Entity thing, TiledMapTileLayer layer, boolean unchangable ) {
        int x = tile.getLocationX();
        int y = tile.getLocationY();
        int xTiles = (int) Math.ceil( (region.getRegionWidth() / 32));
        int yTiles = (int) Math.ceil( (region.getRegionHeight() / 32));
        int xTotal = x + xTiles;
        int yTotal = y + yTiles;
        TextureRegion[][] regions = region.split(32, 32);
        //check if tiles can be changed if not return
        for (int countx = x; countx < xTotal; countx++) {
            for (int county = y; county < yTotal; county++) {
               if( landSquareTileMap[countx][county].isUnchangeble()==true){
                   return false;
               }
            }
        }
        for (int countx = x; countx < xTotal; countx++) {
            for (int county = y; county < yTotal; county++) {
                Cell cell = new Cell();
               AtlasNamedAtlasRegion region2=assetts.addAtlasRegionToAtlas(regions[countx-x][county-y], region.name+"x"+countx+y+county,atlasName );
                cell.setTile(new AtlasStaticTiledMapTile(region2));
                layer.setCell(countx, ySize-county, cell);
                if(unchangable==true){
                    landSquareTileMap[countx][county].setUnchangeable(unchangable);
                }
                if(thing!=null){
                    landSquareTileMap[countx][county].addEntity(thing);
                }
            }
        }
        return true;
    }
    
    protected  void makeTiledMaps(){
    }
    protected void makeEntity(String path){
    }
    protected void makeTiles(){
        landSquareTileMap =new LandSquareTile[xSize][ySize];
        for (int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                landSquareTileMap[countx][county] = new LandSquareTile(countx, county, ySize);

            }
        }
    }
    public TiledMap getTiledMap() {
        return tiledMap;
    }
    public int getxSize() {
        return xSize;
    }
    public int getySize() {
        return ySize;
    }
}
