package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.Editor.EditMode.Listeners.ChangeListeners;
import com.jessematty.black.tower.GameBaseClasses.Camera.GameCamera;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.ZRPGPlayerFunctions;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.GameBaseClasses.Rendering.FrameBufferRenderer;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Stages.GameStage;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.Drawing.BoundingBoxRenderer;
import com.jessematty.black.tower.Systems.Drawing.LightRenderSystem;
import com.jessematty.black.tower.Systems.Player.ZRPGPlayerSystem;
import com.jessematty.black.tower.Systems.Sound.PlaySoundSystem;
import com.jessematty.black.tower.Systems.Drawing.RenderSystem;
import com.jessematty.black.tower.Systems.UI.UIBarSystem;

import java.io.IOException;
public class MapDraw implements NamedScreen{// class for drawing the currentGameMap and actors plants, fighters, item ECT on to the screen
    protected GameMap currentMap;
    private GameStage uiStage;
    private GameCamera gameCamera; // the camera for rendering the currentGameMap and actors
    private TiledMapRenderer tiledMapRenderer;
    private  SpriteBatch batch;
    private GameAssets gameAssets;
    private   ShapeRenderer shapeRenderer;
    private  Engine engine;
    private ZRPGCharacter player;
    private Viewport mapViewport;
    private FrameBufferRenderer frameBufferRenderer;
    private boolean drawEntityDebugBounds;
    protected World world;
    private BoundingBoxRenderer boundingBoxRenderer;
    private UIBarSystem uiBarSystem;
    private boolean showBars;
    private final String  name="Map Draw Screen";
    private GameTime gameTime;
    private final ChangeListeners changeListeners;
    public MapDraw(GameAssets gameAssets){
        this(gameAssets,false);
    }
    public MapDraw(GameAssets gameAssets, boolean drawEntityDebugBounds){
        changeListeners=new ChangeListeners();
        this.gameAssets =gameAssets;
        this.drawEntityDebugBounds =drawEntityDebugBounds;
    }
    
    public void setWorld(World world){
        this.world=world;
        engine=world.getEngine();
        changeListeners.setWorld(world);
    }
    public void showCurrentWorld(){
        batch=new BrightnessBatch();
        Gdx.input.setInputProcessor(GameAssets.getGameInput().getLockableInputMultiplexer());
        gameCamera = new GameCamera(960, 960);
        if(world.getWorldMaps().size>0) {
            this.currentMap = world.getStartMap();
            this.currentMap.setCurrentMap(true);
            setMap(world.getMap(world.getWorldSettings().getSimpleSetting("startMapId", String.class)), true);
        }
        else {
            throw new IllegalArgumentException("World Must Have Maps!");
        }
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        mapViewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCamera);
        float viewPortWidth=500*(w/h);
        float viewPortHeight=960f;
        viewPortHeight=500;
        gameCamera.setToOrtho(false,viewPortWidth,viewPortHeight);
        gameCamera.update();
        gameTime=world.getGameTime();
        frameBufferRenderer= new FrameBufferRenderer(batch, (int)viewPortWidth, (int)viewPortHeight);
        uiStage = new GameStage(gameAssets.getGameLogger());
        GameAssets.getGameInput().addProcessor(uiStage);
        shapeRenderer =new ShapeRenderer();
        boundingBoxRenderer= new BoundingBoxRenderer(shapeRenderer);
        EngineSetup.addBaseSystemsToEngine(engine, this, shapeRenderer, drawEntityDebugBounds, frameBufferRenderer.getMapFrameBuffer(), frameBufferRenderer.getLightFrameBuffer());
    }
    /**
     * begin libGDX screen draw methods
     */
    @Override
        public void show () {
        float w = Gdx.graphics.getWidth();
            float h = Gdx.graphics.getHeight();
            if(player !=null){
                gameCamera.centerCameraToPosition(player.getPositionComponent());
            }
        }
    /**
     * // the main game loop
     * @param time libGDX delta time
     */
    @Override
        public void render (float time) {
            Gdx.gl.glClearColor(1, 1, 0, 0); // clear the screen
           Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            // move camera to player position
            // draw game components  on   the screen
             tiledMapRenderer.setView(gameCamera);
            tiledMapRenderer.render();
            batch.setProjectionMatrix(gameCamera.combined);
            shapeRenderer.setProjectionMatrix(gameCamera.combined);
            gameCamera.update();
            float  deltaTime=Gdx.graphics.getDeltaTime();
            currentMap.mapTurnActions(deltaTime, gameTime);
            engine.update(deltaTime);
            uiStage.update(deltaTime, gameTime);
            GameAssets.getGameInput().getKeyListener().update(deltaTime);
            gameTime.countTime();
    }
    @Override
    public void resize(int width, int height) {
        if(gameCamera !=null) {
            if(mapViewport!=null) {
                mapViewport.update(width, height, false);
            }
               uiStage.resize(width, height);
            }
            if(engine.getSystem(LightRenderSystem.class)!=null){
                frameBufferRenderer= new FrameBufferRenderer(batch, (int)width, (int)height);
                engine.getSystem(RenderSystem.class).setFrameBuffer(frameBufferRenderer.getMapFrameBuffer());
                engine.getSystem(LightRenderSystem.class).setFrameBuffer(frameBufferRenderer.getLightFrameBuffer());
            }
            gameCamera.update();
        }
    @Override
    public void pause() {
        //assetts.saveGame(world, world.getLoadPath());
    }
    @Override
    public void resume() {
         showCurrentWorld();
    }
    @Override
    public void hide() {
      //  assetts.saveGame(world, assetts.getSettings().getPreferences().getString("savePath"));
    }
    @Override
    public void dispose() { // libgdx dispose method
        try {
            gameAssets.saveGame(world, gameAssets.getSettings().getPreferences().getString("savePath"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameAssets.dispose();
        batch.dispose();
        uiStage.dispose();
        frameBufferRenderer.dispose();
    }
    /**
     *
     * end libGDX screen methods
     */
    /**
     *   sets/ changes  the current gameMap
     *   This  MUST  be called BEFORE  this screen is displayed to set the map to  render
     * @param newMap the  map to change to
     */
    public void setMap(GameMap newMap, boolean showMap){
        if(currentMap!=null) {
            this.currentMap.setCurrentMap(false);
        }
        newMap.setCurrentMap(true);
        this.currentMap =newMap;
        changeListeners.setMap(currentMap);
        if(showMap==true){
            showMap(currentMap);
        }
    }
    public void showMap(GameMap newMap){
       gameCamera.calculateScreenMaxes(newMap);
      tiledMapRenderer = new OrthogonalTiledMapRenderer(currentMap.getTiledMap(), 1);
      Skin skin= newMap.getSkin();
        if(skin!=null){
            gameAssets.setCurrentSkin(skin);
        }
    }
    public ZRPGCharacter getPlayer() {
        return player;
    }
    /**
     *   sets the rpg player object and adds the players UI Bars
     * @param player  the player to set to
     */
    public void setPlayer(ZRPGCharacter player) {
        this.player = player;
        engine.getSystem(PlaySoundSystem.class).setPlayer(player);
        engine.getSystem(ZRPGPlayerSystem.class).setPlayer(player);
      // DefaultZRPGBottomWindow defaultZRPGBottomWindow= new DefaultZRPGBottomWindow(getCurrentMap().getSkin(),  "windowCompass", this );
       // defaultZRPGBottomWindow.setZrpgPlayer(player);
      // addUIBarWindow(defaultZRPGBottomWindow, Direction.DOWN);
       gameCamera.setEntityToFollow(player.getPlayerEntity());
        gameCamera.centerCameraToPosition(player.getPositionComponent());
        GameAssets.getGameInput().getKeyListener().addInputKeyCombos( new ZRPGPlayerFunctions(this, player).getPlayerControlFunctions());
    }


    /**
     *  Method to convert a  x, y screen location to  a land square tile  on the map at map position x, y in tile units
     * @param x the screen x location in pixels
     * @param y the screen y location  in pixels
     * @return LandSquareTile  the tile at given screen location
     */
    public LandSquareTile screenToTile(float x, float y){
        Vector3 vec3= gameCamera.unproject(new Vector3(x,y,0));
        LandSquareTile tile= currentMap.getTileFromWorldUnitCoordinates(vec3.x, vec3.y);
        return tile;
    }
    public GameMap getCurrentMap(){
        return currentMap;
    }
    public float getViewPortHeight(){
        return gameCamera.viewportHeight;
    }
    public float getViewPortWidth(){
        return gameCamera.viewportWidth;
    }
    public GameTime getGameTime() {
        return gameTime;
    }
    public void setGameTime(GameTime gameTime) {
        this.gameTime = gameTime;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public Engine getEngine() {
        return engine;
    }
    public OrthographicCamera getGameCamera() {
        return gameCamera;
    }
    public boolean isDrawEntityDebugBounds() {
        return drawEntityDebugBounds;
    }
    /**
     *  set  whether or not show  the collision detection   bounding rectangles
     *  for ALL the entities in the system and adds the system for doing so
     * @param drawEntityDebugBounds whether or not show  the collision detection   bounding rectangles  for ALL the entities
     */
    public void setDrawEntityDebugBounds(boolean drawEntityDebugBounds) {
        this.drawEntityDebugBounds = drawEntityDebugBounds;
        if(drawEntityDebugBounds ==true) {
            engine.addSystem(boundingBoxRenderer);
        }
        else{
            engine.removeSystem(boundingBoxRenderer);
        }
    }
    public World getWorld() {
        return world;
    }
    public boolean isShowBars() {
        return showBars;
    }
    public void setShowBars(boolean showBars) {
        this.showBars = showBars;
    }
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
    public GameAssets getGameAssets() {
        return gameAssets;
    }
    public GameStage getUiStage() {
        return uiStage;
    }
    @Override
    public String getName() {
        return name;
    }
    public Viewport getMapViewport() {
        return mapViewport;
    }
    public ChangeListeners getChangeListeners() {
        return changeListeners;
    }
}
