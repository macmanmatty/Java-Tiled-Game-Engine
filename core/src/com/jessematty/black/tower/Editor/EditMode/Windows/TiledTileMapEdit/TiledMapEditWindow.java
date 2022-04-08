package com.jessematty.black.tower.Editor.EditMode.Windows.TiledTileMapEdit;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Buttons.MapEditButtons;
import com.jessematty.black.tower.Editor.EditMode.Input.MapEditKeys;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindows;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapLayerWindow.NamedTiledMapTileLayer;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.Editor.TiledMapStage.TiledMapStage;
import com.jessematty.black.tower.Editor.Tools.EntityTools.EntityTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.GameMapEdit;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.PlaceMode;
import com.jessematty.black.tower.Editor.Tools.MapTools.SelectMode;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapEdit;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Camera.GameCamera;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class TiledMapEditWindow {
    /*
    private PlaceMode placeMode = PlaceMode.PLACE;
    private SelectMode selectMode=SelectMode.SELECT;
    private Skin skin; // the UI skin
    private GameMapEdit gameMapEdit;// map editor class
    private GameMap currentMap; // the currentMap to Edit
    private TiledMapEdit tiledMapEdit;// the current tiled map to edit
    private Stage mapStage; // the map stage
    private BrightnessBatch batch; // the batch for rendering
    private GameCamera camera; // the camera
    private Stage uiStage;
    private Engine engine;
    private ShapeRenderer shapeRenderer;
    private FrameBuffer mapFrameBuffer;
    private FrameBuffer lightFrameBuffer;
    private Viewport mapViewport;
    private TiledMapRenderer tiledMapRenderer;
    private World world;
    private final DragAndDrop dragAndDrop;
    private final KeyListener keyListener;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private int currentLayerNumber;
    private Rectangle selectedArea = new Rectangle();
    private final WorldObjects worldObjects;
    private ClipBoard clipBoard;
    private MapTools mapTools;
    private MapEditWindows mapEditWindows;
    private MapEditButtons mapEditButtons;
    private TopMenu topMenu;
    private MapEditScreen mapEditScreen;
    private boolean renderToBuffer=true;
    private MapEditKeys editInputKeys;
    private boolean screenLocked;
    private boolean keyLocked;
    public TiledMapEditWindow(MapEditScreen mapEditScreen, ClipBoard clipBoard, TopMenu topMenu, DragAndDrop dragAndDrop, KeyListener keyListener, Skin skin, World world, WorldObjects worldObjects) {
       // super(mapEditScreen,  "Tiled Map",skin,  "default");
        this.world = world;
        this.mapEditScreen=mapEditScreen;
        this.dragAndDrop = dragAndDrop;
        this.keyListener = keyListener;
        batch = new BrightnessBatch();
        this.skin = skin;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        positionComponentMapper = GameComponentMapper.getPositionComponentMapper();
        this.worldObjects = worldObjects;
        this.topMenu = topMenu;
        this.clipBoard=clipBoard;
    }
    public void makeWindow() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new GameCamera(960, 960);
        float viewPortWidth = 960;
        float viewPortHeight = 960;
        mapViewport = new ExtendViewport(viewPortWidth, viewPortHeight, camera);
        editInputKeys.addKeys();
        mapStage = new Stage(mapViewport, batch);
        mapStage.getBatch().setProjectionMatrix(camera.combined);
        uiStage = new Stage();
        keyListener.getStages().add(uiStage);
        keyListener.getStages().add(mapStage);
        //inputMultiplexer.addProcessor(mapStage);
    //    tiledMapEdit= new TiledMapEdit( getGameAssets(), clipBoard);
     //   gameMapEdit= new GameMapEdit(getGameAssets(), clipBoard);
        mapTools = new MapTools(mapEditScreen);
        engine = world.getEngine();
        shapeRenderer = new ShapeRenderer();
        EngineSetup.addRenderSystemsToEngine(  engine, batch, shapeRenderer, true, mapFrameBuffer, lightFrameBuffer);
        makeWindows();
    }
    public void hide() {
        renderToBuffer = true;
    }
    public void resize(int width, int height) {
        uiStage.getViewport().setScreenSize(width, height);
        mapStage.getViewport().setScreenSize(width, height);
        camera.update();
        mapFrameBuffer= new FrameBuffer(Format.RGBA8888, width, height, false);
        lightFrameBuffer= new FrameBuffer(Format.RGBA8888, width, height, false);
    }
    public void pause() {
    }
    public void resume() {
        renderToBuffer=false;
    }
    public void dispose() {
    }
    private void makeWindows() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        Table menuTable = topMenu.getMenuBar().getTable();
        menuTable.setSize(Gdx.graphics.getWidth(), menuTable.getPrefHeight());
        height = height - menuTable.getPrefHeight();
        menuTable.setPosition(0, height);
        uiStage.addActor(menuTable);
        Window mapButtonsWindow = mapEditButtons;
        mapEditButtons.setWindowSize(width - 320, 64);
        mapButtonsWindow.setPosition(0, height - 64);
        uiStage.addActor(mapButtonsWindow);
        MapEditWindow textureDisplayWindow = null;
        Window textureDisplay2DWindow = textureDisplayWindow;
        textureDisplayWindow.setWindowSize(320, 500);
        height = height - textureDisplay2DWindow.getHeight();
        textureDisplayWindow.setPosition(width - 320, height);
        uiStage.addActor(textureDisplayWindow);
        MapEditWindow tiledMapLayerWindow=null;
        Window tiledMapLayer2DWindow = tiledMapLayerWindow;
        tiledMapLayerWindow.setWindowSize(320, 200);
        height = height - tiledMapLayer2DWindow.getHeight();
        tiledMapLayer2DWindow.setPosition(width - 320, height);
        uiStage.addActor(tiledMapLayer2DWindow);
    }
    public void setWidowsToMap() {
    }
    protected void placeTextureRegionInCell(AtlasRegion region) {
        clipBoard.getCurrentCell().getTile().getTextureRegion().setRegion(region);
    }
    public void runGame(World world, Entity player){
        world.setPlayer(player);
      //  getGameAssets().getMapDraw().setMap(currentMap);
        //gameAssets.showGame(world, player );
    }
    public int getXSize() {
        return currentMap.getXTiles();
    }
    public int getYSize() {
        return currentMap.getYTiles();
    }
    // changes or set the current map to be edited to the given map
    public void changeMap(GameMap map) {
        this.currentMap = map;
        TiledMap tiledMap=map.getTiledMap();
        tiledMapEdit.setCurrentTiledMap(tiledMap);
        tiledMapEdit.setCurrentLayer((NamedTiledMapTileLayer) tiledMap.getLayers().get(0));
        this.mapFrameBuffer= new FrameBuffer(Format.RGBA8888,map.getXTiles()*map.getTileWidth(), map.getYTiles()*map.getYTiles(),  false );
        // set the tiled of the current map to the editor
        tiledMapEdit.setTileSizeX(map.getTileWidth());
        tiledMapEdit.setTileSizeY(map.getTileHeight());
        tiledMapEdit.setxSize(map.getXTiles());
        tiledMapEdit.setySize(map.getYTiles());
        // set the game amp in the current editor
        gameMapEdit.setTileSizeX(map.getTileWidth());
        gameMapEdit.setTileSizeY(map.getTileHeight());
        gameMapEdit.setxSize(map.getXTiles());
        gameMapEdit.setySize(map.getYTiles());
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f);
        mapStage= new TiledMapStage(batch, mapViewport, currentMap, dragAndDrop);
        setWindowsToCurrentMap();
    }
    private void setWindowsToCurrentMap() {
    }
    public void render(float delta) {
        renderToBuffer=false;
        Gdx.gl.glClearColor(Color.FOREST.r, Color.FOREST.g, Color.FOREST.b, Color.FOREST.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        if(mapFrameBuffer!=null && renderToBuffer) {
            mapFrameBuffer.begin();
        }
        if(tiledMapRenderer!=null) {
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
        }
        mapStage.act();
        mapStage.draw();
        if(mapFrameBuffer!=null && renderToBuffer) {
            mapFrameBuffer.end();
        }
        uiStage.act();
        uiStage.draw();
        if(currentMap!=null && mapFrameBuffer!=null && renderToBuffer) {
            Texture mapTexture=mapFrameBuffer.getColorBufferTexture();
            currentMap.setMapImage(mapTexture);
            //  batch.setTiledMapView(camera);
            batch.begin();
            batch.draw(mapTexture, 0, 0 );
            batch.end();
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        float  deltaTime=Gdx.graphics.getDeltaTime();
        engine.update(deltaTime);
        batch.end();
    }
    private void updateScreen() {
    }
    public LandMap createMap(){
        LandMap map= null;
        return map;
    }
    @Override
    public boolean keyDown(int keycode) {
        System.out.println("key down!!");
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Object object=clipBoard.getCurrentObject();
        if(object==null){
            return  false;
        }
        if(screenX>mapViewport.getWorldWidth() || screenY>mapViewport.getWorldWidth()){
            return false;
        }
        Vector3 unprojectedScreenCoordinates= camera.unproject(new Vector3(screenX,screenY,0));
        float x=unprojectedScreenCoordinates.x;
        float y=unprojectedScreenCoordinates.y;
        if (currentMap != null) {
            place(object, x, y);
        }
        if(placeMode == PlaceMode.FILL_BUCKET){
            if(currentMap!=null) {
                int tiledMapX=(int)(x/ currentMap.getTileWidth());
                int tiledMapY=(int)(((y/ currentMap.getTileHeight())));
                tiledMapEdit.bucketFill(tiledMapX, tiledMapY, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());
            }
        }
        return false;
    }
    void place(Object object, float x, float y ){
        if (object instanceof Entity) {
            Entity entityToPlace = (Entity) object;
            EntityTools.placeEntity(currentMap, world, entityToPlace, x, y);
        }
        if (object instanceof LandSquareTile[][]) {
            LandSquareTile tile = currentMap.screenToTile(x, y);
            gameMapEdit.placeTiles(tile.getLocationX(), tile.getLocationY(), (LandSquareTile[][]) object);
        }
        if (object instanceof Cell[] [] [] ) {
            LandSquareTile tile = currentMap.screenToTile(x, y);
            tiledMapEdit.placeCells(tile.getLocationX(), tile.getLocationY(), (Cell[][][]) object);
        }
        if (object instanceof Cell [] []  ) {
            LandSquareTile tile = currentMap.screenToTile(x, y);
            tiledMapEdit.placeCell(tile.getLocationX(), tile.getLocationY(), (Cell[][]) object);
        }
        if (object instanceof  AtlasRegion) {
            tiledMapEdit.createStaticTiledMapTile(x, y, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());
        }
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(keyListener.anyKeysPressed(Keys.SHIFT_LEFT, Keys.SHIFT_RIGHT)) {
            if (screenX > mapViewport.getWorldWidth() || screenY > mapViewport.getWorldWidth()) {
                return true;
            }
            Vector3 unprojectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
            if (selectMode == SelectMode.SELECT) {
                if (currentMap != null) {
                    selectedArea.height = unprojectedScreenCoordinates.x - selectedArea.x;
                    selectedArea.width = unprojectedScreenCoordinates.y - selectedArea.y;
                }
                gameMapEdit.selectTiles(selectedArea);
            }
            if (selectMode == SelectMode.SELECT) {
                if (currentMap != null) {
                    selectedArea.height = unprojectedScreenCoordinates.x - selectedArea.x;
                    selectedArea.width = unprojectedScreenCoordinates.y - selectedArea.y;
                }
                tiledMapEdit.selectCells(selectedArea);
            }
        }
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        uiStage.setKeyboardFocus(null);
        mapStage.setKeyboardFocus(null);
        if(screenX>mapViewport.getWorldWidth() || screenY>mapViewport.getWorldWidth()){
            return true;
        }
        Vector3 unprojectedScreenCoordinates= camera.unproject(new Vector3(screenX,screenY,0));
        if(pointer== Buttons.LEFT)
            if(selectMode == SelectMode.SELECT){
                if(currentMap!=null) {
                    selectedArea.height=unprojectedScreenCoordinates.x-selectedArea.x;
                    selectedArea.width=unprojectedScreenCoordinates.y-selectedArea.y;
                }
                shapeRenderer.begin(ShapeType.Line);
                shapeRenderer.setColor(1, 0, 0, 1);
                shapeRenderer.rect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
                shapeRenderer.end();
            }
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Object object=clipBoard.getCurrentObject();
        // no object exit
        if(object==null){
            return false;
        }
        // object out of screen exit and true mouse in not in  application bounds
        if(screenX>mapViewport.getWorldWidth() || screenY>mapViewport.getWorldWidth()){
            return true;
        }
        //  actual  get game location coordinates
        Vector3 unprotectedScreenCoordinates=camera.unproject(new Vector3(screenX, screenY, 0));
        clipBoard.setScreenLocations(screenX, mapViewport.getTopGutterY()-screenY);
        float x=unprotectedScreenCoordinates.x;
        float y=unprotectedScreenCoordinates.y;
        if(placeMode == PlaceMode.PLACE) {
            placeObjectPreview(object, x , y);
        }
        if(placeMode == PlaceMode.FILL_BUCKET){
            if(currentMap!=null) {
                int tiledMapX=(int)(x/ currentMap.getTileWidth());
                int tiledMapY=(int)(((y/ currentMap.getTileHeight())));
                tiledMapEdit.clearMouseOver();
                tiledMapEdit.fillMouseOver(tiledMapX, tiledMapY, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());
            }
        }
        return false;
    }
    private void placeObjectPreview(Object object, float x, float y){
        if(object instanceof  Entity) {
            Entity entityToPlace = (Entity) clipBoard.getCurrentObject();
            if (currentMap != null && entityToPlace != null) {
                PositionComponent position = positionComponentMapper.get(entityToPlace);
                position.setMapID(currentMap.getId());
                position.setLocationX(x);
                position.setLocationY(y);
            }
        }
        if (object instanceof  Cell) {
            int tiledMapX = (int) (x / currentMap.getTileWidth());
            int tiledMapY = (int) (((y / currentMap.getTileHeight())));
            tiledMapEdit.clearMouseOver();
            tiledMapEdit.placeCellMouseOver(tiledMapX, tiledMapY, (Cell[][]) clipBoard.getCurrentObject());
        }
        if (object instanceof Cell [] [] [] ) {
            LandSquareTile tile = currentMap.screenToTile(x, y);
            tiledMapEdit.clearMouseOver();
            tiledMapEdit.placeCells(tile.getLocationX(), tile.getLocationY(), (Cell[][][]) clipBoard.getCurrentObject());
        }
    }
    // sets the screen to  a  x, y world position
    public void setCameraToMapCoordinates(float  mapX, float mapY ){
        Vector3 screenCoordinates= camera.project(new Vector3(mapX, mapY, 0));
        camera.translate(new Vector2(screenCoordinates.x, screenCoordinates.y));
    }
    private Vector2 setCoordinatesToTile(float screenX, float screenY){
        int tilesX= (int) (screenX/ currentMap.getTileWidth());
        int tilesY= (int) (screenY/ currentMap.getTileHeight());
        float newScreenX=tilesX* currentMap.getTileWidth();
        float newScreenY=tilesY* currentMap.getTileHeight();
        return  new Vector2(tilesX, tilesY);
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public World getWorld() {
        return world;
    }
    public void changeWorld(World World) {
        this.world=world;
    }
    //@Override
    public void setMap(GameMap gameMap) {
        this.tiledMapEdit.setCurrentTiledMap(gameMap.getTiledMap());

        
    }
    public GameMap getMap() {
        return currentMap;
    }
    public PlaceMode getPlaceMode() {
        return placeMode;
    }
    public void setPlaceMode(PlaceMode placeMode) {
        this.placeMode = placeMode;
    }
    public int getCurrentLayerNumber() {
        return currentLayerNumber;
    }
    public int getTileWidth() {
        return currentMap.getTileWidth();
    }
    public int getTileHeight() {
        return currentMap.getTileHeight();
    }
    public WorldObjects getWorldObjects() {
        return worldObjects;
    }
    public void setWorldObjects(WorldObjects worldObjects) {
    }
    public ClipBoard getClipBoard() {
        return clipBoard;
    }
    public void setCurrentLayerNumber(int currentLayerNumber) {
        this.currentLayerNumber = currentLayerNumber;
    }
    public MapTools getMapTools() {
        return mapTools;
    }
    public Stage getUiStage() {
        return uiStage;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }
    public KeyListener getKeyListener() {
        return keyListener;
    }
    public TiledMapEdit getTiledMapEdit() {
        return tiledMapEdit;
    }
    public void setTiledMapEdit(TiledMapEdit tiledMapEdit) {
        this.tiledMapEdit = tiledMapEdit;
    }
    public GameMapEdit getGameMapEdit() {
        return gameMapEdit;
    }
    public void setGameMapEdit(GameMapEdit gameMapEdit) {
        this.gameMapEdit = gameMapEdit;
    }
    public GameMap getCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }
    public SelectMode getSelectMode() {
        return selectMode;
    }
    public void setSelectMode(SelectMode selectMode) {
        this.selectMode = selectMode;
    }
    public Skin getSkin() {
        return skin;
    }
    public boolean isRenderToBuffer() {
        return renderToBuffer;
    }
    public void setRenderToBuffer(boolean renderToBuffer) {
        this.renderToBuffer = renderToBuffer;
    }
    public void backToWorldEdit() {
        // capture  map image
        renderToBuffer=true;
    }
    @Override
    public boolean isMouseInputLocked() {
        return screenLocked;
    }
    @Override
    public void setMouseInputLocked(boolean screenLocked) {
        this.screenLocked = screenLocked;
    }
    @Override
    public boolean isKeyInputLocked() {
        return keyLocked;
    }
    @Override
    public void setKeyInputLocked(boolean keyLocked) {
        this.keyLocked = keyLocked;
    }

     */
}
