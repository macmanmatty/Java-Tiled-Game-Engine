package com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors.MapEditInputProcessors;
import com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors.MapEditProcessor;
import com.jessematty.black.tower.Editor.EditMode.Input.MapEditKeys;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindows;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Rendering.FrameBufferRenderer;
import com.jessematty.black.tower.GameBaseClasses.Camera.GameCamera;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.Input.GameInput;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.WorldSettable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.GameMapEdit.GameMapEdit;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapEdit;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;

/**
 *  screen instance for editing the map
 *  holds all of the ui tool windows
 *
 */
public    class MapEditScreen implements NamedScreen,  EditScreen, MapSettable {
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
    private final WorldObjects worldObjects;
    private ClipBoard clipBoard;
    private final String name = "Map Edit Screen";
    private MapTools mapTools;
    private MapEditWindows mapEditWindows;
    private TopMenu topMenu;
    private boolean renderToBuffer=false;
    private MapEditKeys editInputKeys;
    private final MapDraw mapDraw;
    private MapEditInputProcessors mapEditProcessors;
    public MapEditScreen(GameAssets assets, Skin skin, World world) {
        this.gameAssets = assets;
        this.world = world;
        batch = new BrightnessBatch();
        this.skin = skin;
        this.worldObjects = new WorldObjects();
        this.clipBoard=new ClipBoard();
        uiStage = new Stage();
        mapDraw= new MapDraw(getGameAssets());
        mapDraw.setWorld(world);
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
      //editInputKeys= new MapEditKeys(this, gameInput.getKeyListener());
      // editInputKeys.addKeys();
        mapEditProcessors= new MapEditInputProcessors(this);
        gameInput.addProcessor(mapEditProcessors.getInputProcessors().values().toArray());
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
        GameAssets.getGameInput().removeProcessor(mapEditProcessors.getInputProcessors().values().toArray());
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
         EditWindow mapEditButtons = mapEditWindows.getEditWindows().get("Top Buttons");
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
                batch.begin();
            batch.draw(mapTexture, 0, 0 );
            MapEditProcessor currentInputProcessor=mapEditProcessors.getCurrentInputProcessor();
            if(currentInputProcessor.isDrawMousePointer()){
                TextureRegion mousePointer=currentInputProcessor.getMousePointer();
                if(mousePointer!=null){
                    batch.draw( mousePointer, currentInputProcessor.getScreenX(), currentInputProcessor.getScreenY());
                }
            }
            batch.end();
        }
             batch.begin();
            engine.update(delta);
            batch.end();
        }
    public World getWorld() {
        return world;
    }
    @Override
    public GameMap getMap() {
        return currentMap;
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
    public void editMap(LandMap map) {
    }
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}
