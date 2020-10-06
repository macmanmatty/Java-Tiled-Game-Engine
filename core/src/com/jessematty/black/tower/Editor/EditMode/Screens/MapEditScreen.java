package com.jessematty.black.tower.Editor.EditMode.Screens;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.EditMode.MapTools.SelectMode;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindows;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.MapTools.PlaceMode;
import com.jessematty.black.tower.Editor.EditMode.MapTools.GameMapEdit;
import com.jessematty.black.tower.Editor.EditMode.MapTools.MapTools;
import com.jessematty.black.tower.Editor.EditMode.MapTools.TiledMapEdit;
import com.jessematty.black.tower.Editor.EditMode.TopButtons.MapEditButtons;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.NamedTiledMapTileLayer;
import com.jessematty.black.tower.Editor.TiledMapStage.TiledMapStage;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.World.WorldObjects;

import java.util.ArrayList;


public    class MapEditScreen   implements NamedScreen,  InputProcessor, EditScreen {
    private PlaceMode placeMode = PlaceMode.PLACE;
    private SelectMode selectMode=SelectMode.SELECT;
    private Skin skin; // the UI skin
    private GameMapEdit gameMapEdit;
    private GameMap currentMap;
    private TiledMapEdit tiledMapEdit;
    private Actor entityToPlaceImage;
    private int ySize; // landSquareTileMap x size
    private int xSize; // may y size
    private float mapXPixils;
    private float mapYPixils;
    private Stage mapStage;
    private boolean running;
    private int tileWidth = 32;
    private int tileHeight = 32;
    private BrightnessBatch batch;
    private OrthographicCamera camera;
    private Stage uiStage;
    private Engine engine;
    private ShapeRenderer shapeRenderer;
    private FrameBuffer mapFrameBuffer;
    private FrameBuffer lightFrameBuffer;
    private ExtendViewport mapViewport;
    private final GameAssets gameAssets;
    private final GameComponentMapper gameComponentMapper;
    private TiledMapRenderer tiledMapRenderer;
    private final World world;
    private final DragAndDrop dragAndDrop;
    private final KeyListener keyListener;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private int currentLayerNumber;
    private Rectangle selectedArea = new Rectangle();
    private final WorldObjects worldObjects;
    private ClipBoard clipBoard;
    private final String name = "Map Edit Screen";
    private MapTools mapTools;
    protected PlaceMode mode;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    protected float minWorldWidth = 960;
    protected float minWorldWHeight = 960;
    private MapEditWindows mapEditWindows;
    private MapEditButtons mapEditButtons;
    private TopMenu topMenu;




    public MapEditScreen(GameAssets assets, ClipBoard clipBoard,  TopMenu topMenu, DragAndDrop dragAndDrop, KeyListener keyListener, Skin skin, World world, WorldObjects worldObjects) {
        this.gameAssets = assets;
        this.world = world;
        this.dragAndDrop = dragAndDrop;
        this.keyListener = keyListener;
        batch = new BrightnessBatch();
        this.skin = skin;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        this.gameComponentMapper = world.getGameComponentMapper();
        positionComponentMapper = gameComponentMapper.getPositionComponentMapper();
        this.worldObjects = worldObjects;
        this.topMenu = topMenu;
        this.clipBoard=clipBoard;


    }

    @Override
    public void show() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.translate(420, 420);
        float viewPortWidth = 960;
        float viewPortHeight = 960;
        mapViewport = new ExtendViewport(viewPortWidth, viewPortHeight, camera);


        mapEditWindows= new MapEditWindows(this);

        mapStage = new Stage(mapViewport, batch);
        mapStage.getBatch().setProjectionMatrix(camera.combined);
        uiStage = new Stage();
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(mapStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        tiledMapEdit= new TiledMapEdit( gameAssets, clipBoard);
        gameMapEdit= new GameMapEdit(gameAssets, clipBoard);
        mapTools = new MapTools(this);
        engine = world.getEngine();
        shapeRenderer = new ShapeRenderer();
        EngineSetup.addRenderSystemsToEngine( gameComponentMapper,  engine, batch, shapeRenderer, true, mapFrameBuffer, lightFrameBuffer);
        makeWindows();

    }

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

        MapEditWindow textureDisplayWindow=mapEditWindows.getTextureDisplayWindow();
        Window textureDisplay2DWindow = textureDisplayWindow;
        textureDisplayWindow.setWindowSize(320, 500);
        height = height - textureDisplay2DWindow.getHeight();
        textureDisplayWindow.setPosition(width - 320, height);
        uiStage.addActor(textureDisplayWindow);
        MapEditWindow tiledMapLayerWindow=mapEditWindows.getTiledMapLayerWindow();


        Window tiledMapLayer2DWindow = tiledMapLayerWindow;
        tiledMapLayerWindow.setWindowSize(320, 200);
        height = height - tiledMapLayer2DWindow.getHeight();
        tiledMapLayer2DWindow.setPosition(width - 320, height);
        uiStage.addActor(tiledMapLayer2DWindow);
        uiStage.addActor(clipBoard);

    }

    public void setWidowsToMap() {
    }

    public void loadTextureNames(String path) {
        ArrayList<TextureAtlasRegionNames> names = gameAssets.loadNames(path);
    }



    protected void placeTextureRegionInCell(AtlasRegion region) {
        clipBoard.getCurrentCell().getTile().getTextureRegion().setRegion(region);
    }


            public void runGame(World world, Entity player){
        world.setPlayer(player);
       gameAssets.getMapDraw().changeMap(currentMap);
        //gameAssets.showGame(world, player );
            }
    public int getxSize() {
        return xSize;
    }
    public void setxSize(int xSize) {
        this.xSize = xSize;
        this.mapXPixils= tileWidth *xSize;
    }
    public int getySize() {
        return ySize;
    }
    public void setySize(int ySize) {
        this.ySize = ySize;
        this.mapYPixils= tileHeight *ySize;
    }
    public void changeMap(GameMap map) {
            this.currentMap = map;
            TiledMap tiledMap=map.getTiledMap();
            tiledMapEdit.setCurrentTiledMap(tiledMap);
            tiledMapEdit.setCurrentLayer((NamedTiledMapTileLayer) tiledMap.getLayers().get(0));
            this.tileHeight =map.getTileSizeY();
            this.tileWidth =map.getTileSizeY();
            this.xSize=map.getXSize();
            this.ySize=map.getYSize();
        tiledMapEdit.setTileSizeX(tileWidth);
        tiledMapEdit.setTileSizeY(tileHeight);
        tiledMapEdit.setxSize(xSize);
        tiledMapEdit.setySize(ySize);

        gameMapEdit.setTileSizeX(tileWidth);
        gameMapEdit.setTileSizeY(tileHeight);
        gameMapEdit.setxSize(xSize);
        gameMapEdit.setySize(ySize);
        
            this.xSize=map.getXSize();
            this.ySize=map.getYSize();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        mapStage= new TiledMapStage(batch, mapViewport, currentMap, dragAndDrop);

        setWindowsToCurrentMap();

    }

    private void setWindowsToCurrentMap() {

    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.FOREST.r, Color.FOREST.g, Color.FOREST.b, Color.FOREST.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        if(tiledMapRenderer!=null) {
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
        }

        mapStage.act();
        mapStage.draw();
            uiStage.act();
            uiStage.draw();


            float  deltaTime=Gdx.graphics.getDeltaTime();
            engine.update(deltaTime);
        }
    private void updateScreen() {
    }
    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().setScreenSize(width, height);
        mapStage.getViewport().setScreenSize(width, height);
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
    }






   public LandMap createMap(){
        LandMap map= null;
     return map;
   }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT) {
            camera.translate(-tileWidth, 0);
            System.out.println("left was pressed");
        }

        else  if(keycode == Input.Keys.RIGHT) {
            camera.translate(tileWidth, 0);
        }

        else  if(keycode == Input.Keys.UP) {
            camera.translate(0, -tileHeight);
        }

        else  if(keycode == Input.Keys.DOWN) {
            camera.translate(0, tileHeight);
        }

        else  if(keycode == Keys.EQUALS) {
            camera.zoom = camera.zoom + .01f;
        }

        else  if(keycode == Keys.MINUS) {
            camera.zoom = camera.zoom - .01f;
        }

        return true;
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
            return  true;

        }
        
        if(screenX>mapViewport.getWorldWidth() || screenY>mapViewport.getWorldWidth()){

            return true;
        }

        Vector3 unprojectedScreenCoordinates= camera.unproject(new Vector3(screenX,screenY,0));
        float x=unprojectedScreenCoordinates.x;
        float y=unprojectedScreenCoordinates.y;
        
            if (currentMap != null) {

              place(object, x, y);
            }

        if(placeMode == PlaceMode.FILL_BUCKET){
            if(currentMap!=null) {
                int tiledMapX=(int)(x/ tileWidth);
                int tiledMapY=(int)(((y/ tileHeight)));
              tiledMapEdit.bucketFill(tiledMapX, tiledMapY, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());
            }
        }
        return true;
    }
    
    
    void place(Object object, float x, float y ){


        if (object instanceof Entity) {
            Entity entityToPlace = (Entity) object;

            PositionComponent position = positionComponentMapper.get(entityToPlace);
            position.setMapWorldLocationX(currentMap.getWorldX());
            position.setMapWorldLocationY(currentMap.getWorldX());
            position.setLocationX(x);
            position.setLocationY(y);
            world.addEntityToWorld(entityToPlace);
            entityToPlaceImage.addAction(Actions.removeActor());
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

        return true;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
        return true;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Object object=clipBoard.getCurrentObject();
        if(object==null){
            return true;
        }
        if(screenX>mapViewport.getWorldWidth() || screenY>mapViewport.getWorldWidth()){

            return true;
        }
        Vector3 unprojectedScreenCoordinates=camera.unproject(new Vector3(screenX, screenY, 0));
        clipBoard.setScreenLocations(screenX, Gdx.graphics.getHeight()-screenY);
         float x=unprojectedScreenCoordinates.x;
         float y=unprojectedScreenCoordinates.y;





        if(placeMode == PlaceMode.PLACE) {
            placeObjectPreview(object, x , y);
            
        }
        
        
        if(placeMode == PlaceMode.FILL_BUCKET){
            if(currentMap!=null) {
                int tiledMapX=(int)(x/ tileWidth);
                int tiledMapY=(int)(((y/ tileHeight)));
                tiledMapEdit.clearMouseOver();
                tiledMapEdit.fillMouseOver(tiledMapX, tiledMapY, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());
            }
        }
        return true;
    }
    
    
    private void placeObjectPreview(Object object, float x, float y){

        if(object instanceof  Entity) {
            Entity entityToPlace = (Entity) clipBoard.getCurrentObject();
            if (currentMap != null && entityToPlace != null) {
                if (entityToPlaceImage != null) {
                    getUiStage().addActor(entityToPlaceImage);
                }
                PositionComponent position = positionComponentMapper.get(entityToPlace);
                position.setMapWorldLocationX(currentMap.getWorldX());
                position.setMapWorldLocationY(currentMap.getWorldX());
                position.setLocationX(x);
                position.setLocationY(y);
            }
        }




        if (object instanceof  Cell) {
            int tiledMapX = (int) (x / tileWidth);
            int tiledMapY = (int) (((y / tileHeight)));
            tiledMapEdit.clearMouseOver();
            tiledMapEdit.placeCellMouseOver(tiledMapX, tiledMapY, (Cell[][]) clipBoard.getCurrentObject());
        }


        if (object instanceof Cell [] [] [] ) {
            LandSquareTile tile = currentMap.screenToTile(x, y);
            tiledMapEdit.clearMouseOver();
            tiledMapEdit.placeCells(tile.getLocationX(), tile.getLocationY(), (Cell[][][]) clipBoard.getCurrentObject());

        }
        
        
    }
    private Vector2 setCoordinatesToTile(float screenX, float screenY){
        int tilesX= (int) (screenX/ tileWidth);
        int tilesY= (int) (screenY/ tileHeight);
        float newScreenX=tilesX* tileWidth;
        float newScreenY=tilesY* tileHeight;
       return  new Vector2(tilesX, tilesY);
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }



    public World getWorld() {
        return world;
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
        return tileWidth;
    }
    public int getTileHeight() {
        return tileHeight;
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
    public GameComponentMapper getGameComponentMapper() {
        return gameComponentMapper;
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


    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
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
}
