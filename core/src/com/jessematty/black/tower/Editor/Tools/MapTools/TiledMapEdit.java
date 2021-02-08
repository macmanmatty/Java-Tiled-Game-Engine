package com.jessematty.black.tower.Editor.EditMode.MapTools;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.MapTools.Tools.BitMaskTiledMapCells;
import com.jessematty.black.tower.Editor.EditMode.MapTools.Tools.BucketFill;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.NamedTiledMapTileLayer;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.Generators.MapGenerators.HeightMapGen;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.ColoredTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskException;
import com.jessematty.black.tower.Generators.Sets.MaskMode;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskableTileSet;

public class TiledMapEdit {
   private  TiledMap currentTiledMap;
    private NamedTiledMapTileLayer currentLayer;
    private  int xSize;
    private int  ySize;
    private int tileSizeX=32;
    private int tileSizeY=32;
    private int currentLayerNumber;
    private ClipBoard clipBoard;
    private float mapXPixels;
    private  float mapYPixels;
    private GameAssets gameAssets;
    private BitMask bitMask= new BitMask();
    private boolean fillDiagnols;
    public TiledMapEdit(GameAssets gameAssets, ClipBoard clipBoard) {
        this.clipBoard = clipBoard;
        this.gameAssets = gameAssets;
    }
    // fills a grid of tiles based on whether or not there textures regions  match
    // like a paint bucket function ina drawing program
    public void bucketFill(int locationX, int locationY, AtlasNamedAtlasRegion fillRegion) {
        Cell cell=currentLayer.getCell(locationX, locationY);
        if(cell!=null) {
            TextureRegion regionToReplace = cell.getTile().getTextureRegion();
            BucketFill.fillCells(0, 0, xSize, ySize, currentLayer, locationX, locationY, fillRegion, regionToReplace, fillDiagnols);
        }

    }

    public void bitMask(int locationX, int locationY, AtlasNamedAtlasRegion fillRegion, TileSet set) {
        Cell cell=currentLayer.getCell(locationX, locationY);
        if(cell!=null) {
            TextureRegion regionToReplace = cell.getTile().getTextureRegion();
            if(regionToReplace!=null) {
                BitMaskTiledMapCells.bitMaskCells(0, 0, xSize, ySize, currentLayer, locationX, locationY,gameAssets,  set, fillRegion);
            }

        }

    }
    public void bitMaskMouseOver(int locationX, int locationY, AtlasNamedAtlasRegion fillRegion, TileSet set) {
        Cell cell=currentLayer.getCell(locationX, locationY);
        if(cell!=null) {
            TextureRegion regionToReplace = cell.getTile().getTextureRegion();
            if(regionToReplace!=null) {
                BitMaskTiledMapCells.bitMaskCells(0, 0, xSize, ySize, getPreviewLayer(), locationX, locationY,gameAssets,  set, fillRegion);
            }

        }

    }
   
    public void fillMouseOver(int locationX, int locationY, AtlasNamedAtlasRegion fillRegion) {
        Cell cell=currentLayer.getCell(locationX, locationY);
        if(cell!=null) {
            TextureRegion regionToReplace = cell.getTile().getTextureRegion();
            BucketFill.fillCells(0, 0, xSize, ySize, getPreviewLayer(), locationX, locationY, fillRegion, regionToReplace, fillDiagnols);
        }
    }
    public void clearMouseOver() {
        clearLayer("newRegion");
    }
    public void clearLayer( String name) {
        MapLayers mapLayers=currentTiledMap.getLayers();
        NamedTiledMapTileLayer layer= (NamedTiledMapTileLayer) mapLayers.get(name);
        for (int countx =0; countx < ySize; countx++) {
            for (int county = 0; county < xSize; county++) {
                layer.getCell(countx, county).setTile(null);
            }
        }
    }
    // turns the current tiled map tile  if it is static into an animated tiled map tile
    public void newAnimatedTileCell(float screenX, float screenY){
        Cell cell=screenToTiledMapCell(screenX, screenY);
        if(cell.getTile() instanceof AtlasStaticTiledMapTile){
        AtlasStaticTiledMapTile tiledMapTile= (AtlasStaticTiledMapTile) cell.getTile();
        Array<AtlasStaticTiledMapTile> frameTiles= new Array<>();
        frameTiles.add(tiledMapTile);
        cell.setTile(new AtlasAnimatedTiledMapTile(1, frameTiles));
        }
    }
    // adds a new layer to the tiled map with given name
    public void addLayer(String name){
        MapLayers layers=currentTiledMap.getLayers();
        TiledMapTileLayer  layer= MapTools.newMapLayer("newRegion", xSize, ySize, tileSizeX, tileSizeY);
        if(currentTiledMap!=null && layers!=null) {
            layers.add(layer);
            int previousMapLayerNumber=layers.size()-2;
            layers.get(previousMapLayerNumber).setName(name);
        }
    }
    // a new layer to the tiled  map  with the default name layer + number of layers
    public void addLayer(){
        MapLayers layers=currentTiledMap.getLayers();
        NamedTiledMapTileLayer layer=  MapTools.newMapLayer("newRegion", xSize, ySize, tileSizeX, tileSizeY);
        if(currentTiledMap!=null && layers!=null) {
            layers.add(layer);
            int previousMapLayerNumber=layers.size()-2;
           layers.get(previousMapLayerNumber).setName("layer "+previousMapLayerNumber);
        }
    }
    // gets a given layer based on layer name
    public TiledMapTileLayer getLayer(String name){
        MapLayers layers=currentTiledMap.getLayers();
        return (TiledMapTileLayer) layers.get(name);
    }
    // places a 3d array of cells on the map at given x and y screen location
    public void placeCells(int locationX, int locationY, Cell[][][] cellsToPlace) {
        int layers=cellsToPlace.length;
        int xMax = cellsToPlace[0].length;
        int yMax = cellsToPlace[0][0].length;
        for (int countLayers = currentLayerNumber; countLayers < layers; countLayers++) {
            if(countLayers>=currentTiledMap.getLayers().size()){
                TiledMapTileLayer tiledMapTileLayer= new TiledMapTileLayer(xSize, ySize, tileSizeX, tileSizeY);
                tiledMapTileLayer.setName("layer "+currentTiledMap.getLayers().size());
                currentTiledMap.getLayers().add(tiledMapTileLayer);
            }
            TiledMapTileLayer tiledMapTileLayer= (TiledMapTileLayer) currentTiledMap.getLayers().get(countLayers);
            for (int countx = locationX; countx < xMax; countx++) {
                for (int county = locationY; county < yMax; county++) {
                    tiledMapTileLayer.setCell(countx, county, cellsToPlace[countLayers][countx][county]);
                }
            }
        }
    }
    // places  a 2d array of cells on the tiled map at given x, y screen location
    public void placeCell(int locationX, int locationY, Cell[][] cellsToPlace) {
        placeCellsInternal(currentLayer,locationX, locationY, cellsToPlace);
    }
    // places  a 2d array of cells on the the preview layer  tiled map at given x, y screen location
    public void placeCellMouseOver(int locationX, int locationY, Cell[][] cellsToPlace) {
       NamedTiledMapTileLayer drawLayer= getPreviewLayer();
        placeCellsInternal(drawLayer,locationX, locationY, cellsToPlace);
    }
    private NamedTiledMapTileLayer getPreviewLayer(){
       MapLayers layers= currentTiledMap.getLayers();
       return (NamedTiledMapTileLayer) layers.get(layers.size()-1);
    }
    // places  a 2d array of cells on the tiled map at given x, y screen location
    private void  placeCellsInternal( NamedTiledMapTileLayer currentLayer, int locationX, int locationY, Cell[][] cellsToPlace){
        int xMax = cellsToPlace.length;
        int yMax = cellsToPlace[0].length;
        for (int countx = locationX; countx < xMax; countx++) {
            for (int county = locationY; county < yMax; county++) {
                currentLayer.setCell(countx, county, cellsToPlace[countx][county]);
            }
        }
    }
    // set a cell to a new static tai8led map tile with a  given texture region
    public void createStaticTiledMapTile(float x, float y, AtlasNamedAtlasRegion region){
        Cell cell =screenToTiledMapCell( x, y);
        if(cell!=null) {
            cell.setTile(new AtlasStaticTiledMapTile(region, region.name));
            System.out.println("texture was set ");
        }
    }
    // selects a given area of tiled map cells
    public void selectCells(Rectangle selectedArea) {
        int x = (int) (selectedArea.x / tileSizeX);
        int y = (int) (selectedArea.y / tileSizeY);
        int xMax = (int) (selectedArea.width / tileSizeX) + 1;
        int yMax = (int) (selectedArea.height / tileSizeY) + 1;
        if (xMax > xSize) {
            xMax = xSize;
        }
        if (yMax > ySize) {
            yMax = ySize;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if(xMax>0 && yMax>0) {
            Cell[][][] cellsToPlace = new Cell[1][xMax][yMax];
            for (int countx = x; countx < xMax; countx++) {
                for (int county = y; county < yMax; county++) {
                    TiledMapTileLayer layer = (TiledMapTileLayer) currentTiledMap.getLayers().get(currentLayerNumber);
                    Cell cell = layer.getCell(countx, county);
                    if (cell != null) {
                        cellsToPlace[1][countx - x][county - y] = cell;
                    }
                }
            }
            clipBoard.setCurrentObject(cellsToPlace);
        }
    }
    
    public TiledMap getCurrentTiledMap() {
        return currentTiledMap;
    }
    public void setCurrentTiledMap(TiledMap currentTiledMap) {
        this.currentTiledMap = currentTiledMap;
    }
    public NamedTiledMapTileLayer getCurrentLayer() {
        return currentLayer;
    }
    public void setCurrentLayer(NamedTiledMapTileLayer currentLayer) {
        this.currentLayer = currentLayer;
        System.out.println("layer  was set " +currentLayer);
    }
    // darkens a given area of tiled map tiles.
    public void darkenTiles(int x, int y, LandSquareTile[][] tiles){
        if(tiles!=null) {
            int xMax = tiles.length;
            int yMax = tiles[0].length;
            for (int countx = x; countx < xMax; countx++) {
                for (int county = y; county < yMax; county++) {
                    TiledMapTileLayer layer = (TiledMapTileLayer) currentTiledMap.getLayers().get(currentTiledMap.getLayers().size() - 1);
                    Cell cell = layer.getCell(countx, county);
                    ColoredTiledMapTile tile = (ColoredTiledMapTile) cell.getTile();
                    tile.setColor(new NamedColor(1, 1, 1, .5f));
                }
            }
        }
    }
    public void setTileSizeY(int tileSizeY) {
        this.tileSizeY = tileSizeY;
    }
    public Cell screenToTiledMapCell(float xScreen, float yScreen){
        int tiledMapX=(int)(xScreen/tileSizeX);
        int tiledMapY=(int)(((yScreen/tileSizeY)));
        return currentLayer.getCell(tiledMapX, tiledMapY);
    }
    // hides or displays a given layer
    private void showOrHideLayer(int layer){
        MapLayer mapLayer=currentTiledMap.getLayers().get(layer);
        mapLayer.setVisible(!mapLayer.isVisible());
    }
    // hides or displays a given layer
    private void showOrHideLayer(String name){
        MapLayer mapLayer=currentTiledMap.getLayers().get(name);
        mapLayer.setVisible(!mapLayer.isVisible());
    }
    protected void loadTiledMap() { // loads a new tmx TileMap made with tiled tileMap program
        TiledMap map=MapTools.loadTMXMapFromFile(gameAssets);
        if(map!=null) {
            currentTiledMap= map;
            MapProperties properties= currentTiledMap.getProperties();
            tileSizeX       = properties.get("tilewidth", Integer.class);
            tileSizeY       = properties.get("tileheight", Integer.class);
            xSize = properties.get("width", Integer.class);
            ySize  = properties.get("height", Integer.class);
            mapXPixels = xSize  * tileSizeX;
            mapYPixels = ySize * tileSizeY;
        }
    }


    // creates a bitmasked height map of tiles based given sets of bitmasking tiles.
    public void createMaskedSet(  String atlasName, String name, int max, int min, int smooth, MaskMode maskMode) {
        int [] [] soilBitNumberMap;
        int [] [] soilMap;
                HeightMapGen heightMapMaker= new HeightMapGen(xSize, ySize);
        soilMap=heightMapMaker.makeHeightMap(min,max,smooth);
      Array< Integer> soilMapNumbers= new Array<Integer>();
        int numberOfSoilLayers=soilMapNumbers.size;
        for (int count = 0; count < numberOfSoilLayers; count++) {
            TiledMapTileLayer layer2 = new TiledMapTileLayer(xSize, ySize, 32, 32);
            layer2.setName("layer" + currentTiledMap.getLayers().size());
            currentTiledMap.getLayers().add(layer2);
            TiledMapTileLayer layer = new TiledMapTileLayer(xSize, ySize, 32, 32);
            layer.setName("layer" + currentTiledMap.getLayers().size());
            currentTiledMap.getLayers().add(layer);
        }
        soilBitNumberMap=bitMask.makeTrimmedHeightTileBitMap(soilMapNumbers, soilMap, maskMode); // make trimmed bit map
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                for (int count = 0; count < numberOfSoilLayers; count++) {
                    if (soilMap[countx][county] ==soilMapNumbers.get(count)) {
                        Cell cell = new Cell();
                        AtlasNamedAtlasRegion region = gameAssets.getAtlasRegionByName(name+"." + (count) + "." + soilBitNumberMap[countx][county] + ".0", atlasName);
                        cell.setTile(  new AtlasStaticTiledMapTile(region));
                        TiledMapTileLayer  layer = (TiledMapTileLayer) currentTiledMap.getLayers().get(count );
                        layer.setCell(countx, ySize-county, cell);
                        if(soilBitNumberMap[countx][county]!=255  && count>0) { // make the layer under the same as layer above
                            cornerCheck( atlasName,name,  soilMap, countx, county, count, 1);
                        }
                    }
                }
            }
        }
    }
    private void cornerCheck( String atlasName,  String name, int [] [] soilMap, int countx, int county, int count, int number){
        TiledMapTileLayer  layer = (TiledMapTileLayer) currentTiledMap.getLayers().get(count - number);
        Cell cell = new Cell();
        int bitValue= bitMask.eightSideBitMapCalculator(soilMap[countx][county]-number, countx, county, soilMap);
        AtlasNamedAtlasRegion region;
        if(number>=count){
            region = gameAssets.getAtlasRegionByName(name+"." + (count - number) + "." + 255 + ".0", atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if (bitValue!=255 ) {
            region = gameAssets.getAtlasRegionByName(name+"." + (count - number) + "." + bitValue + ".0", atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
        }
        else {
            region = gameAssets.getAtlasRegionByName(name+"." + (count - number) + "." + 255 + ".0", atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if(number<count) {
            number++;
            cornerCheck(atlasName, name, soilMap, countx, county, count, number);
        }
    }
    // bit maskes given tile that has bit masking  tile set to go with it.
  public void   bitMask( TextureRegion region, BitMaskableTileSet bitMaskableTileSet) throws BitMaskException {
      for (int countx = 0; countx < xSize; countx++) {
          for (int county = 0; county < ySize; county++) {
             int number= bitMask.eightSideBitMapCalculator(countx, county, region, currentLayer, xSize, ySize);
             Cell cell=currentLayer.getCell(countx, county);
            String name =bitMaskableTileSet.getTileNumbers().get(number);
            if(name.isEmpty()){
                throw new BitMaskException( "Incomplete  Tile Set No Tile  Exists for Bit Number "+number);
            }
                      AtlasNamedAtlasRegion atlasRegion=gameAssets.getAtlasRegionByName(name);
            if(atlasRegion==null){
                throw new BitMaskException( "Incomplete  Tile Set No Tile  Exists for Bit Number "+number);
            }
             cell.setTile(new AtlasStaticTiledMapTile(atlasRegion));
          }
          }
  }
    public int getxSize() {
        return xSize;
    }
    public void setxSize(int xSize) {
        this.xSize = xSize;
    }
    public int getySize() {
        return ySize;
    }
    public void setySize(int ySize) {
        this.ySize = ySize;
    }
    public int getTileSizeX() {
        return tileSizeX;
    }
    public void setTileSizeX(int tileSizeX) {
        this.tileSizeX = tileSizeX;
    }
    public int getTileSizeY() {
        return tileSizeY;
    }
    public boolean isFillDiagnols() {
        return fillDiagnols;
    }
    public void setFillDiagnols(boolean fillDiagnols) {
        this.fillDiagnols = fillDiagnols;
    }
}
