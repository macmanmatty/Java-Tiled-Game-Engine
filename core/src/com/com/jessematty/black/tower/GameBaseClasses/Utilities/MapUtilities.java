package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.Map;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;
import java.util.List;

public class MapUtilities {

    private final static  RandomNumbers value= new RandomNumbers();
    public static void addEntityToTile(GameMap map, LandSquareTile tile, Entity entity, PositionComponent position){

        float screenLocationX=tile.getScreenLocationx();
       float screenLocationY= tile.getScreenLocationy();

        position.setLocationX(screenLocationX);
        position.setLocationY(screenLocationY);
        map.removeEntity(position.getTiles(), entity);
        Array<LandSquareTile> newTiles=map.getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
        position.setTiles(newTiles);
    }

    // gets all
    public static  Array<Entity> getAllEntities (GameMap map , float screenLocationX, float screenLocationY, int xTiles, int yTiles){

        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
       Array<Entity> entities= new Array<>();
        for (int countx = -xTiles; countx < xTiles; countx++ ){
            for (int county = -yTiles; county < yTiles; county++) {
                LandSquareTile tile = map.screenToTile(screenLocationX + (countx * tileSizeX), screenLocationY + (county * tileSizeY));
                // get tile occupants
                if (tile != null) {

                    Array<Entity> occupants = tile.getEntities();
                    int size = occupants.size;
                    for (int count = 0; count < size; count++) {
                        Entity entity = occupants.get(count);
                        if (!InList.isInList(entities, entity)) {
                            entities.add(entity);
                        }

                    }


                }

            }

        }


        return  entities;


    }
    public static  Array<Entity> getAllEntitiesAndTiles (GameMap map , float screenLocationX, float screenLocationY, int xTiles, int yTiles){

        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
        Array<Entity> entities= new Array<>();
        for (int countx = -xTiles; countx < xTiles; countx++ ){
            for (int county = -yTiles; county < yTiles; county++) {
                LandSquareTile tile = map.screenToTile(screenLocationX + (countx * tileSizeX), screenLocationY + (county * tileSizeY));
                // get tile occupants
                if (tile != null) {
                    entities.add(tile);

                    Array<Entity> occupants = tile.getEntities();
                    int size = occupants.size;
                    for (int count = 0; count < size; count++) {
                        Entity entity = occupants.get(count);
                        if (!InList.isInList(entities, entity)) {
                            entities.add(entity);
                        }

                    }


                }

            }

        }


        return  entities;


    }

    public static  Array<Entity> getAllEntities(GameMap map , float screenLocationX, float screenLocationY, int xTiles, int yTiles, Array<String> numericStats, Array<String> stringStats , Array<String> booleanStats,  Class<? extends Component> ... components){




        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
        Array<Entity> entities= new Array<>();
        for (int countx = -xTiles; countx < xTiles; countx++ ){
            for (int county = -yTiles; county < yTiles; county++) {
                LandSquareTile tile = map.screenToTile(screenLocationX + (countx * tileSizeX), screenLocationY + (county * tileSizeY));
                // get tile occupants
                if (tile != null) {

                    Array<Entity> occupants = tile.getEntities(numericStats, stringStats, booleanStats, components);
                    int size = occupants.size;
                    for (int count = 0; count < size; count++) {
                        Entity entity = occupants.get(count);
                        if (!InList.isInList(entities, entity)) {
                            entities.add(entity);
                        }

                    }


                }

            }

        }


        return  entities;


    }


    public  static Array<Entity>  getAllEntities( World world, GameMap map,  float screenX, float screenY, Circle circle){
       float diameter=circle.radius*2f;
       int tilesX=(int)(diameter/map.getTileWidth())+1;
       int tilesY=(int)(diameter/map.getTileHeight())+1;
       Array<Entity> entitiesInCircle= new Array<>();
        ComponentMapper<PositionComponent> positionComponentMapper=world.getGameComponentMapper().getPositionComponentMapper();

        Array<Entity> entities=getAllEntities(map, screenX, screenY, tilesX, tilesY);

       int size=entities.size;
       for(int count=0; count<size; count++){

           Entity entity=entities.get(count);
           PositionComponent position=positionComponentMapper.get(entity);

           if(Intersector.overlaps(circle, position.getBoundsBoundingRectangle())){
               entitiesInCircle.add(entity);
           }
       }




        return  entitiesInCircle;

    }


    public  static  Array<Entity>  getAllEntities( World world, GameMap map,  float screenX, float screenY, Rectangle rectangle){
        float width=rectangle.width;
        float height=rectangle.height;
        int tilesX=(int)(width/map.getTileWidth())+1;
        int tilesY=(int)(height/map.getTileHeight())+1;
        Array<Entity> entitiesInRectangle= new Array<>();
        ComponentMapper<PositionComponent> positionComponentMapper=world.getGameComponentMapper().getPositionComponentMapper();

        Array<Entity> entities=getAllEntities(map, screenX, screenY, tilesX, tilesY);

        int size=entities.size;
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            PositionComponent position=positionComponentMapper.get(entity);

            if(Intersector.overlaps(rectangle, position.getBoundsBoundingRectangle())){
                entitiesInRectangle.add(entity);
            }
        }




        return  entitiesInRectangle;

    }

    public static   Array<Entity>  getAllEntities( World world, GameMap map,  float screenX, float screenY, Polygon polygon){
        Rectangle rectangle=polygon.getBoundingRectangle();
        float width=rectangle.width;
        float height=rectangle.height;
        int tilesX=(int)(width/map.getTileWidth())+1;
        int tilesY=(int)(height/map.getTileHeight())+1;
        Array<Entity> entitiesInPolygon= new Array<>();
        ComponentMapper<PositionComponent> positionComponentMapper=world.getGameComponentMapper().getPositionComponentMapper();
        Array<Entity> entities=getAllEntities(map, screenX, screenY, tilesX, tilesY);

        int size=entities.size;
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            PositionComponent position=positionComponentMapper.get(entity);

            if(Intersector.overlapConvexPolygons(polygon, position.getBounds())){
                entitiesInPolygon.add(entity);
            }
        }




        return  entitiesInPolygon;

    }






    public static ArrayList<LandSquareTile> getFourDirectionsAdjoiningTiles(Map map, LandSquareTile tile) {
        int x = tile.getLocationX();
        int y = tile.getLocationY();
        ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
        LandSquareTile tile2 = map.getMapSquareOrNull(x - 1, y);
        if (tile2 != null) {
            tiles.add(tile2);
        }
        tile2 = map.getMapSquareOrNull(x + 1, y);
        if (tile2 != null) {
            tiles.add(tile2);
        }
        tile2 = map.getMapSquareOrNull(x, y - 1);
        if (tile2 != null) {
            tiles.add(tile2);
        }
        tile2 = map.getMapSquareOrNull(x, y + 1);
        if (tile2 != null) {
            tiles.add(tile2);
        }
        return tiles;
    }
    public static LandSquareTile getClosestEnterableTile( Map map , LandSquareTile tile) { // returns the closest isEnterable tile to  WoodWand given tile
        int locationx = tile.getLocationX();
        int locationy = tile.getLocationY();
        LandSquareTile newTile = null;
        int counter = 1;
        int xSize=map.getXTiles();
        int ySize=map.getYTiles();
        if (locationx == xSize) {
            locationx = xSize - 1;
        }
        if (locationy == ySize) {
            locationy = ySize - 1;
        }
        if (locationy < 0) {
            locationy = 0;
        }
        if (locationx < 0) {
            locationx = 0;
        }
        return newTile;
    }
    
    
    public static Entity getClosestEntity(Map map, LandSquareTile location, Direction direction, int distance, Class<? extends Component>... components) {
        Array<LandSquareTile> tiles = getAdjacentTiles( map, location, direction, distance);
        int size = tiles.size;
        for (int count = 0; count < size; count++) {
            Array<Entity> entities = tiles.get(count).getEntities(components);
            if (entities.size > 0) {
                return entities.get(0);
            }
        }
        return null;
    }


    public static Array<Entity> getClosestEntities (Map map, LandSquareTile location, Direction direction, int tileDistance, Class<? extends Component>... components) {
        Array<Entity> occupants = new Array<Entity>();
        if (direction != null) {
            Array<LandSquareTile> tiles = getAdjacentTiles(map, location, direction, tileDistance);
            int size = tiles.size;
            for (int count = 0; count < size; count++) {
                occupants.addAll(tiles.get(count).getEntities(components));
            }
        }
        return occupants;
    }
    public static Array<Entity> getClosestEntities(Map map, PositionComponent position, float distance, Class<? extends Component>... components) {
        Array<LandSquareTile> tiles = getSurroundingTiles(map, position, distance);
        int size = tiles.size;
        Array<Entity> occupants = new Array<Entity>();
        for (int count = 0; count < size; count++) {
            occupants.addAll(tiles.get(count).getEntities(components));
        }
        return occupants;
    }
    
    
    public static Vector2 getScreenCoordinatesFromTileCoordinates( Map map,  int x, int y){
        
        int ySize=map.getYTiles();
        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
        float screenLocationX = (x + 1) * tileSizeX;
        float screenLocationY = (ySize - y - 1) * tileSizeY;
        return new Vector2(screenLocationX, screenLocationY);
    }


    // returns a random enterable tile  from a  map returns  null if no tiles are enterable
    public static LandSquareTile getRandomEnterableTile(Map map) {
        
        int xSize=map.getXTiles();
        int ySize=map.getYTiles();
        LandSquareTile tile = null;
        while (tile == null) {
            LandSquareTile tile2 = map.getMapSquare(value.getRandomNumber(3, xSize - 3), value.getRandomNumber(3, ySize - 3));
            if (tile2.isEnterable() == true) {
                tile = tile2;
            }
        }
        return tile;
    }
   
   
    public static List<LandSquareTile> getSurroundingTiles( Map map , PositionComponent position) {  //returns the surronding tiles for a given object based on the texture width and height.
        float xMin = position.getLocationX() - 2;
        float yMin = position.getLocationY() - 2;
        float xMax = position.getLocationX() + position.getBoundsX();
        float yMax = position.getLocationY() + position.getBoundsY();
        ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
        int minTileX = (int) (xMin / tileSizeX);
        int minTileY = (int) (yMin / tileSizeY);
        int maxTileY = (int) (yMax / tileSizeY);
        int maxTileX = (int) (xMax / tileSizeX);
        int xTiles = maxTileX - minTileX;
        int yTiles = maxTileY - minTileY;
        for (int countx = 0; countx < xTiles; countx++) {
            for (int county = 0; county < yTiles; county++) {
                if (countx == minTileX || countx == maxTileX || county == maxTileY || county == minTileY) {
                    LandSquareTile tile = map.getMapSquare(minTileX, minTileY);
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }
    public static Array<LandSquareTile> getSurroundingTiles(Map map, PositionComponent position, float distance) {  //returns the surronding tiles for a given object based on the texture width and height.

        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
    float xMin = position.getLocationX() - tileSizeX;
        float yMin = position.getLocationY() - tileSizeY;
        float xMax = position.getLocationX() + position.getBoundsX() + (distance) + tileSizeX;
        float yMax = position.getLocationY() + position.getBoundsY() + (distance) + tileSizeY;
        Array<LandSquareTile> tiles = new Array<LandSquareTile>();
        int minTileX = (int) (xMin / tileSizeX);
        int minTileY = (int) (yMin / tileSizeY);
        int maxTileY = (int) (yMax / tileSizeY);
        int maxTileX = (int) (xMax / tileSizeX);
        int xTiles = maxTileX - minTileX;
        int yTiles = maxTileY - minTileY;
        for (int countx = 0; countx < xTiles; countx++) {
            for (int county = 0; county < yTiles; county++) {
                if (countx == minTileX || countx == maxTileX || county == maxTileY || county == minTileY) {
                    LandSquareTile tile = map.getMapSquare(minTileX, minTileY);
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }
    public static List<LandSquareTile> getSurroundingTiles(Map map,LandSquareTile tile, int distance) { // returns all tiles surronding a given tile as an arraylist with the give tile in the list aswell
        ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
        int locationx = tile.getLocationX();
        int locationy = tile.getLocationY();
        for (int countx = -distance; countx < distance; countx++) {
            for (int county = -distance; county < distance; county++) {
                LandSquareTile tile2 = map.getMapSquare(locationx + distance, locationy + distance);
                tiles.add(tile2);
            }
        }
        return tiles;
    }

    public static LandSquareTile getNextTile( World world, PositionComponent position){

       GameMap map =world.getMap(position.getMapId());
       return getNextTile(map, position.getTiles().get(0), position.getDirection());



    }
    public static LandSquareTile getNextTile( Map map , LandSquareTile tile, Direction direction) { // returns  the next tile in the squence based on the direction you are headed
        int xLocation = tile.getLocationX();
        int yLocation = tile.getLocationY();
        switch (direction) {
            case UP:
                return map.getMapSquare(xLocation, yLocation - 1);
            case DOWN:
                return map.getMapSquare(xLocation, yLocation + 1);
            case LEFT:
                return map.getMapSquare(xLocation - 1, yLocation);
            case RIGHT:
                return map.getMapSquare(xLocation + 1, yLocation);
            case LEFTUP:
                return map.getMapSquare(xLocation - 1, yLocation - 1);
            case LEFTDOWN:
                return map.getMapSquare(xLocation - 1, yLocation + 1);
            case RIGHTUP:
                return map.getMapSquare(xLocation + 1, yLocation - 1);
            case RIGHTDOWN:
                return map.getMapSquare(xLocation + 1, yLocation + 1);
            case SAME:
                return tile;
        }
        return tile;
    }
    public static LandSquareTile getPreviousTile( Map map, LandSquareTile tile, Direction direction, int distance) { // returns  the next tile in the squence based on the direction you are headed
        int xLocation = tile.getLocationX();
        int yLocation = tile.getLocationY();
        switch (direction) {
            case UP:
                return map.getMapSquare(xLocation, yLocation + distance);
            case DOWN:
                return map.getMapSquare(xLocation, yLocation - distance);
            case LEFT:
                return map.getMapSquare(xLocation + distance, yLocation);
            case RIGHT:
                return map.getMapSquare(xLocation - distance, yLocation);
            case LEFTUP:
                return map.getMapSquare(xLocation + distance, yLocation + distance);
            case LEFTDOWN:
                return map.getMapSquare(xLocation + distance, yLocation - distance);
            case RIGHTUP:
                return map.getMapSquare(xLocation - distance, yLocation + distance);
            case RIGHTDOWN:
                return map.getMapSquare(xLocation - distance, yLocation - distance);
            case SAME:
                return tile;
        }
        return tile;
    }
    public static ArrayList<LandSquareTile> getNextTile( Map map, LandSquareTile tile, Direction direction, int distance) { // returns  the next line of  tiles in the squence based on the direction you are headed
        int xLocation = tile.getLocationX();
        int yLocation = tile.getLocationY();
        ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
        switch (direction) {
            case UP:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation, yLocation - count));
                }
            case DOWN:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation, yLocation + count));
                }
            case LEFT:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation - count, yLocation));
                }
            case RIGHT:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation + count, yLocation));
                }
            case LEFTUP:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation - count, yLocation - count));
                }
            case LEFTDOWN:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation - count, yLocation + count));
                }
            case RIGHTUP:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation + count, yLocation - count));
                }
            case RIGHTDOWN:
                for (int count = 0; count < distance; count++) {
                    tiles.add(map.getMapSquare(xLocation + count, yLocation + count));
                }
            case SAME:
                return tiles;
        }
        return tiles;
    }
    public static List<LandSquareTile> getAllTilesAndAddEntity( Map map, Rectangle rectangle, Entity entity) {
        return getAllTilesAndAddEntity(map, rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y, entity);
    }
    public static List<LandSquareTile> getAllTilesAndAddEntity( Map map, float xMin, float yMin, float xMax, float yMax, Entity entity){ // finds all tiles for a given  rectangle bounds  and adds them to a list and returns them .
        ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
        for (float countx=xMin-10; countx<xMax; countx=countx+tileSizeX) {
            for (float county = yMin-10; county < yMax; county = county + tileSizeY) {
                LandSquareTile tile=screenToTile( map, countx, county);
                boolean  canAdd=tileCheck(tile, tiles);
                if(canAdd==true){
                    tiles.add(tile);
                    tile.addEntity(entity);
                }
            }
        }
        return tiles;
    }
    
    
    public static LandSquareTile screenToTile( Map map, float  screenLocationX, float screenLocationY){
        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
        int mapYSize=map.getYTiles();
        return map.getMapSquare((int) Math.ceil(screenLocationX / tileSizeX) - 1, mapYSize - (int) Math.ceil(screenLocationY / tileSizeY));


    }
   
    
    
    public static List<LandSquareTile> getAllTiles( Map map, Rectangle rectangle) {
        return getAllTiles(  map, rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y);
    }
    public static List<LandSquareTile> getAllTiles( Map map, float xMin, float yMin, float xMax, float yMax){ // finds all tiles for a given  rectangle bounds  and adds them to a list and returns them .
        int tileSizeX=map.getTileWidth();
        int tileSizeY=map.getTileHeight();
    ArrayList<LandSquareTile> tiles= new ArrayList<LandSquareTile>();
        for (float countx=xMin-10; countx<xMax; countx=countx+tileSizeX) {
            for (float county = yMin-10; county < yMax; county = county + tileSizeY) {
                LandSquareTile tile=map.screenToTile(countx, county);
                boolean  canAdd=tileCheck(tile, tiles);
                if(canAdd==true){
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }
    public static boolean tileCheck(LandSquareTile tile, ArrayList<LandSquareTile> tiles){ // checks to make sure a tile is not being added twice
        if(tile==null){
            return false;
        }
        int size=tiles.size();
        for(int count=0; count<size; count++){
            if(tiles.get(count)==tile){
                return false;
            }
        }
        return true;
    }
    public static LandSquareTile getAdjoiningRandomTile( Map map, LandSquareTile tile) { // returns  a random adjoining tile
        int xLocation = tile.getLocationX();
        int yLocation = tile.getLocationY();
        int random=value.getRandomNumber(1,13);
        switch (random) {
            case 1:
                return map.getMapSquare(xLocation, yLocation - 1);
            case 2:
                return map.getMapSquare(xLocation, yLocation + 1);
            case 3:
                return map.getMapSquare(xLocation - 1, yLocation);
            case 4:
                return map.getMapSquare(xLocation + 1, yLocation);
            case 5:
                return map.getMapSquare(xLocation - 1, yLocation - 1);
            case 6:
                return map.getMapSquare(xLocation - 1, yLocation + 1);
            case 7:
                return map.getMapSquare(xLocation + 1, yLocation - 1);
            case 8:
                return map.getMapSquare(xLocation + 1, yLocation + 1);
            case 9:
                return map.getMapSquare(xLocation + 1, yLocation + 1);
            case 10:
                return map.getMapSquare(xLocation, yLocation + 1);
            default:
                return map.getMapSquare(xLocation, yLocation - 1);
        }
    }
    public static Array<LandSquareTile> getAdjacentTiles( Map map, LandSquareTile tile, Direction direction, int distance) { // returns  an array tiles  of  tiles for specified distance going plis one tile to left and right of you in the squence based on the direction you are headed
        int xLocation = tile.getLocationX();
        int yLocation = tile.getLocationY();
        Array<LandSquareTile> tiles= new Array<LandSquareTile>();
        switch (direction) {
            case UP:
                for (int count=0; count<distance; count++){
                    tiles.add(map.getMapSquare(xLocation, yLocation - count)); // addEntity the tiles right int front you x distance ahead
                    tiles.add(map.getMapSquare(xLocation + count, yLocation - count)); // addEntity the tiles to the left of you x distance ahead
                    tiles.add(map.getMapSquare(xLocation - count, yLocation-count )); // addthe tiles to the righjt of you x distance ahead
                }
                break;
            case DOWN:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation + count, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation - count, yLocation + count));
                }
                break;
            case LEFT:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation - count, yLocation));
                    tiles.add(map.getMapSquare(xLocation - count, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation - count, yLocation - count));
                }
                break;
            case RIGHT:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation + count, yLocation));
                    tiles.add(map.getMapSquare(xLocation + count, yLocation - count));
                    tiles.add(map.getMapSquare(xLocation + count, yLocation + count));
                }
                break;
            case LEFTUP:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation - count, yLocation - count));
                    tiles.add(map.getMapSquare(xLocation, yLocation - count));
                    tiles.add(map.getMapSquare(xLocation - count, yLocation));
                }
                break;
            case LEFTDOWN:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation - count, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation - count, yLocation));
                }
                break;
            case RIGHTUP:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation + count, yLocation - count));
                    tiles.add(map.getMapSquare(xLocation, yLocation - count));
                    tiles.add(map.getMapSquare(xLocation + count, yLocation - count));
                }
                break;
            case RIGHTDOWN:
                for (int count=0; count<distance; count++) {
                    tiles.add(map.getMapSquare(xLocation + count, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation, yLocation + count));
                    tiles.add(map.getMapSquare(xLocation + count, yLocation));
                }
                break;
            case SAME:
                return tiles;
        }
        return tiles;
    }
    public static LandSquareTile [] getAdjacentTiles( Map map, LandSquareTile tile, Direction direction) { // returns  the next tile in the squence based on the direction you are headed
        int xLocation = tile.getLocationX();
        int yLocation = tile.getLocationY();
        LandSquareTile [] tiles = new LandSquareTile [3];
        switch (direction) {
            case UP:
                tiles [0]=map.getMapSquare(xLocation, yLocation - 1);
                tiles[1]=map.getMapSquare(xLocation+1, yLocation - 1);
                tiles[2]=map.getMapSquare(xLocation-1, yLocation - 1);
                break;
            case DOWN:
                tiles [0]=map.getMapSquare(xLocation, yLocation + 1);
                tiles[1]=map.getMapSquare(xLocation+1, yLocation + 1);
                tiles[2]=map.getMapSquare(xLocation-1, yLocation + 1);
                break;
            case LEFT:
                tiles [0]=map.getMapSquare(xLocation-1, yLocation );
                tiles[1]=map.getMapSquare(xLocation-1, yLocation+1  );
                tiles[2]=map.getMapSquare(xLocation-1, yLocation -1);
                break;
            case RIGHT:
                tiles [0]=map.getMapSquare(xLocation+1, yLocation  );
                tiles[1]=map.getMapSquare(xLocation+1, yLocation - 1);
                tiles[2]=map.getMapSquare(xLocation+1, yLocation + 1);
                break;
            case LEFTUP:
                tiles [0]=map.getMapSquare(xLocation-1, yLocation -1 );
                tiles[1]=map.getMapSquare(xLocation, yLocation - 1);
                tiles[2]=map.getMapSquare(xLocation-1, yLocation);
                break;
            case LEFTDOWN:
                tiles [0]=map.getMapSquare(xLocation-1, yLocation + 1);
                tiles[1]=map.getMapSquare(xLocation, yLocation + 1);
                tiles[2]=map.getMapSquare(xLocation-1, yLocation);
                break;
            case RIGHTUP:
                tiles [0]=map.getMapSquare(xLocation+1, yLocation - 1);
                tiles[1]=map.getMapSquare(xLocation, yLocation -1);
                tiles[2]=map.getMapSquare(xLocation+1, yLocation - 1);
                break;
            case RIGHTDOWN:
                tiles [0]=map.getMapSquare(xLocation+1, yLocation + 1);
                tiles[1]=map.getMapSquare(xLocation, yLocation + 1);
                tiles[2]=map.getMapSquare(xLocation+1, yLocation);
                break;
            case SAME:
                return tiles;
        }
        return tiles;
    }
    public static  double findDistance(LandSquareTile location1,  LandSquareTile location2) { // calculates the distance bewteen to tiles using the x and y coordinates
        int x2=location2.getLocationX();
        int y2=location2.getLocationY();
        int y1=location1.getLocationY();
        int x1=location1.getLocationX();
        return Math.sqrt(((x2-x1)^2+((y2-y1)^2)));
    }
    public static Vector2 getDistance(PositionComponent thing1, PositionComponent thing2) {
        float xVector=thing1.getLocationX()-thing2.getLocationX();
        float yVector=thing1.getLocationY()-thing2.getLocationY();
        return new Vector2(xVector, yVector);
    }


    public static void setPositionToTile(LandSquareTile tile , PositionComponent positionComponent){

        float loactionX=tile.getScreenLocationx();
        float locationY=tile.getScreenLocationy();
        positionComponent.setLocationX(loactionX);
        positionComponent.setLocationY(locationY);
        positionComponent.setMapID(tile.getMapId());
        positionComponent.getTiles().clear();
        positionComponent.getTiles().add(tile);
    }



}
