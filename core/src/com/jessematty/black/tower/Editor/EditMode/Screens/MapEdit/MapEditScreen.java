package com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.EditMode.Input.MapEditKeys;
import com.jessematty.black.tower.Editor.EditMode.Listeners.ChangeListeners;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenuMap;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.CreateMapOptionPane;
import com.jessematty.black.tower.Editor.Tools.EntityTools.EntityTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.SelectMode;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindows;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Rendering.FrameBufferRenderer;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Camera.GameCamera;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Input.GameInput;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.WorldSettable;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.Tools.MapTools.PlaceMode;
import com.jessematty.black.tower.Editor.Tools.MapTools.GameMapEdit;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapEdit;
import com.jessematty.black.tower.Editor.EditMode.Buttons.MapEditButtons;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapLayerWindow.NamedTiledMapTileLayer;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;

public    class MapEditScreen implements NamedScreen, LockableInputProcessor, EditScreen, MapSettable {
    private PlaceMode placeMode = PlaceMode.PLACE;
    private SelectMode selectMode=SelectMode.SELECT;
    private Skin skin; // the UI skin
    private GameMapEdit gameMapEdit;// map editor class
    private GameMap currentMap; // the currentMap to Edit
    private TiledMapEdit tiledMapEdit;// the current tiled map to edit
    private BrightnessBatch batch; // the batch for rendering
    private GameCamera camera; // the camera
    private Stage uiStage;
    private Engine engine;
    private ShapeRenderer shapeRenderer;
    private FrameBufferRenderer frameBufferRenderer;
    private final GameAssets gameAssets;
    private TiledMapRenderer tiledMapRenderer;
    private  World world;
    private int currentLayerNumber;
    private Rectangle selectedArea = new Rectangle();
    private final WorldObjects worldObjects;
    private ClipBoard clipBoard;
    private final String name = "Map Edit Screen";
    private MapTools mapTools;
    private MapEditWindows mapEditWindows;
    private MapEditButtons mapEditButtons;
    private TopMenu topMenu;
    private boolean renderToBuffer=false;
    private MapEditKeys editInputKeys;
    private boolean screenLocked;
    private boolean keyLocked;
    private final MapDraw mapDraw;

    public MapEditScreen(GameAssets assets, Skin skin, World world) {
        this.gameAssets = assets;
        this.world = world;
        batch = new BrightnessBatch();
        this.skin = skin;
        this.worldObjects = new WorldObjects();
        this.clipBoard=new ClipBoard();
        uiStage = new Stage();
        mapDraw= new MapDraw(getGameAssets(), world);

    }
    @Override
    public void show() {

        GameInput gameInput=GameAssets.getGameInput();
        camera = new GameCamera(960, 960);
        topMenu= new TopMenu(this);
        float viewPortWidth = 960;
        float viewPortHeight = 960;
        tiledMapEdit= new TiledMapEdit( gameAssets, clipBoard);
        gameMapEdit= new GameMapEdit(gameAssets, clipBoard);
        mapEditWindows= new MapEditWindows(this);
       editInputKeys= new MapEditKeys(this, gameInput.getKeyListener());
        editInputKeys.addKeys();
        gameInput.addProcessor(this);
        camera.enableControlledMovement();
        gameInput.addProcessor(uiStage);
        Gdx.input.setInputProcessor(gameInput.getLockableInputMultiplexer());
        mapTools = new MapTools(this);
        engine = world.getEngine();
        shapeRenderer = new ShapeRenderer();
        frameBufferRenderer= new FrameBufferRenderer(batch, 960, 960);
        EngineSetup.addRenderSystemsToEngine(  engine, batch, shapeRenderer, true, frameBufferRenderer.getMapFrameBuffer(), frameBufferRenderer.getLightFrameBuffer());
        makeWindows();
    }
    @Override
    public void hide() {
        renderToBuffer=true;
        GameAssets.getGameInput().removeProcessor(uiStage);
        GameAssets.getGameInput().removeProcessor(this);
    }
    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().setScreenSize(width, height);
        camera.update();
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
        renderToBuffer=false;
    }
    @Override
    public void dispose() {
    }
    /**
     * creates the map editing UI windows and adds them to the UIStage
     */
    private void makeWindows() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        Table menuTable = topMenu.getMenuBar().getTable();
        menuTable.setSize(Gdx.graphics.getWidth(), menuTable.getPrefHeight());
        height = height - menuTable.getPrefHeight();
        menuTable.setPosition(0, height);
        uiStage.addActor(menuTable);
        mapEditButtons = new MapEditButtons(this, skin);
        mapEditButtons.makeWindow();
        Window mapButtonsWindow = mapEditButtons;
        mapEditButtons.setWindowSize(width - 320, 64);
        mapButtonsWindow.setPosition(0, height - 64);
        uiStage.addActor(mapButtonsWindow);
        EditWindow textureDisplayWindow=mapEditWindows.getEditWindows().get("Texture Window");
        Window textureDisplay2DWindow = textureDisplayWindow;
        textureDisplayWindow.setWindowSize(320, 500);
        height = height - textureDisplay2DWindow.getHeight();
        textureDisplayWindow.setPosition(width - 320, height);
        uiStage.addActor(textureDisplayWindow);
        EditWindow tiledMapLayerWindow=mapEditWindows.getEditWindows().get("Layers Window");
        Window tiledMapLayer2DWindow = tiledMapLayerWindow;
        tiledMapLayerWindow.setWindowSize(320, 200);
        height = height - tiledMapLayer2DWindow.getHeight();
        tiledMapLayer2DWindow.setPosition(width - 320, height);
        uiStage.addActor(tiledMapLayerWindow);
        EditWindow landMapSelectorWindow=mapEditWindows.getEditWindows().get("Land Map Selector Window");
        landMapSelectorWindow.setWindowSize(320, 200);
        height = height - landMapSelectorWindow.getHeight();
        landMapSelectorWindow.setPosition(width - 320, height);
        landMapSelectorWindow.setEditor(this);
        mapDraw.getChangeListeners().getWorldSettables().add((WorldSettable) landMapSelectorWindow);
        mapDraw.getChangeListeners().getMapSettables().add(tiledMapEdit);
        mapDraw.setWorld(world);
        uiStage.addActor(landMapSelectorWindow);
        uiStage.addActor(clipBoard);
    }
   
    public int getXSize() {
        return currentMap.getXTiles();
    }
    public int getYSize() {
        return currentMap.getYTiles();
    }
    @Override
   
       /**
        *  change the current map
         * @param map the map to change to
         */
    public void setMap(GameMap map) {
            this.currentMap = map;


        gameMapEdit.setTileSizeX(map.getTileWidth());
        gameMapEdit.setTileSizeY(map.getTileHeight());
        gameMapEdit.setxSize(map.getXTiles());
        gameMapEdit.setySize(map.getYTiles());
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap(), 1f);
        mapDraw.setMap(map, false);

    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.FOREST.r, Color.FOREST.g, Color.FOREST.b, Color.FOREST.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        if(frameBufferRenderer!=null && renderToBuffer) {
            frameBufferRenderer.getMapFrameBuffer().begin();
        }
        if(tiledMapRenderer!=null) {
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
        }
        if(frameBufferRenderer!=null && renderToBuffer) {
            frameBufferRenderer.getMapFrameBuffer().end();
        }
        uiStage.act();
        uiStage.draw();

        if(currentMap!=null && frameBufferRenderer!=null && renderToBuffer) {
            Texture mapTexture=frameBufferRenderer.getMapFrameBuffer().getColorBufferTexture();
            currentMap.setMapImage(mapTexture);
          //  batch.setTiledMapView(camera);
                batch.begin();
            batch.draw(mapTexture, 0, 0 );
            batch.end();
        }
             batch.begin();
            engine.update(delta);
            batch.end();
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
        if (object instanceof Cell [] [] [] ) {
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
        if(GameAssets.getGameInput().getKeyListener().anyKeysPressed(Keys.SHIFT_LEFT, Keys.SHIFT_RIGHT)) {
            Viewport viewport=uiStage.getViewport();
            if (screenX > viewport.getWorldWidth() || screenY > viewport.getWorldWidth()) {
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

        Viewport mapViewport=uiStage.getViewport();
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
        Viewport mapViewport=uiStage.getViewport();
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
                ComponentMapper<PositionComponent> positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
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

    @Override
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

    public ClipBoard getClipBoard() {
        return clipBoard;
    }
    public String getName() {
        return name;
    }
    public void setCurrentLayerNumber(int currentLayerNumber) {
        this.currentLayerNumber = currentLayerNumber;
    }
    public MapTools getMapTools() {
        return mapTools;
    }
    @Override
    public Stage getUiStage() {
        return uiStage;
    }
    public GameAssets getGameAssets() {
        return gameAssets;
    }
    public OrthographicCamera getCamera() {
        return camera;
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

    public void editMap(LandMap map) {
    }
}
