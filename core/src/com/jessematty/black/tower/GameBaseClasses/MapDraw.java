package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.Camera.GameCamera;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Input.GameInput;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.GameBaseClasses.Rendering.DayAndNightTiledMapRenderer;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Table.UITable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars.DefaultZRPGBottomWindow;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.BoundingBoxRenderer;
import com.jessematty.black.tower.Systems.PlaySoundSystem;
import com.jessematty.black.tower.Systems.UIBarSystem;
import com.jessematty.black.tower.Systems.ZRPGPlayerSystem;
public class MapDraw implements NamedScreen {// class for drawing the currentGameMap and actors plants, fighters, item ECT on to the screen
     protected TiledMap currentTiledMap;
    protected GameMap currentMap;
    private Stage uiStage;
    private GameCamera gameCamera; // the camera for rendering the currentGameMap and actors
      private TiledMapRenderer tiledMapRenderer;
    private  SpriteBatch batch;
    private GameAssets assetts;
    private   ShapeRenderer shapeRenderer;
    private  Engine engine;
    private ZRPGPlayer player;
    private Viewport mapViewport;
    private Viewport stageViewport;
    private GameTime gameTime= new GameTime();
    private  FrameBuffer mapAndEntityBuffer;
    private FrameBuffer lightSourceBuffer;
    private boolean drawEntityDebugBounds;
    protected World world;
    private BoundingBoxRenderer boundingBoxRenderer;
    private UIBarSystem uiBarSystem;
    private boolean showBars;
    private final GameInput gameInput;
    private final String  name="Map Draw Screen";



    public MapDraw(GameAssets gameAssets, World world, boolean drawEntityDebugBounds){
        this.assetts=gameAssets;
        engine=world.getEngine();
        this.world=world;
        gameCamera = new GameCamera(960, 960);
        this.gameInput=gameAssets.getGameInput();

        if(world.getWorldMap()!=null) {
            this.currentMap = world.getStartMap();
            this.currentMap.setCurrentMap(true);

            setMap(world.getMap(world.getWorldSettings().getSimpleSetting("startMapX", Integer.class), world.getWorldSettings().getSimpleSetting("startMapY", Integer.class)));
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
    batch=new BrightnessBatch();



    //mapAndEntityBuffer= new FrameBuffer(Format.RGBA8888, 960, 960, false);
   // lightSourceBuffer= new FrameBuffer(Format.RGBA8888, 960, 960, false);
        stageViewport= new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    uiStage = new Stage(stageViewport);
    gameInput.addProcessor(uiStage);
    shapeRenderer =new ShapeRenderer();
    boundingBoxRenderer= new BoundingBoxRenderer(shapeRenderer);

    EngineSetup.addBaseSystemsToEngine(engine, this, shapeRenderer, drawEntityDebugBounds, mapAndEntityBuffer, lightSourceBuffer);
        this.drawEntityDebugBounds =drawEntityDebugBounds;
        Gdx.input.setInputProcessor(gameInput.getLockableInputMultiplexer());
    }

    /**
     * begin libGDX screen draw methods
     */
    @Override
        public void show () {
        float w = Gdx.graphics.getWidth();
            float h = Gdx.graphics.getHeight();
            if(player !=null){
                gameCamera.centerCameraToPosition(player.getPosition());
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
            gameTime.countTime();
            engine.update(deltaTime);



            uiStage.act();
          //  uiStage.draw();

        }

    @Override
    public void resize(int width, int height) {
        if(gameCamera !=null) {
            if(mapViewport!=null) {
                mapViewport.update(width, height, false);
            }
            if(stageViewport!=null) {
                stageViewport.update(width, height, false);
            }
            gameCamera.update();
        }
    }
    @Override
    public void pause() {
        //assetts.saveGame(world, world.getLoadPath());
    }
    @Override
    public void resume() {
        if(false) {

            World world = null;

            world = assetts.loadGame(assetts.getSettings().getPreferences().getString("savePath"));

            currentMap = world.getCurrentMap();

            int graphicsWidth = Gdx.graphics.getWidth();
            int  graphicsHeight = Gdx.graphics.getHeight();
            gameCamera = new GameCamera(960, 960);
            gameCamera.setToOrtho(false, 500 * (graphicsWidth / graphicsHeight), 500);
            gameCamera.update();
            mapViewport = new ExtendViewport(960, 960, gameCamera);
            batch = new BrightnessBatch();
            // mapAndEntityBuffer = new FrameBuffer(Format.RGBA8888, graphicsWidth, graphicsHeight, false);
            // lightSourceBuffer = new FrameBuffer(Format.RGBA8888, graphicsWidth, graphicsHeight, false);
            batch.setProjectionMatrix(gameCamera.combined);
            stageViewport= new FitViewport(graphicsWidth, graphicsHeight);
            uiStage = new Stage(new FitViewport(graphicsWidth, graphicsHeight));
            gameInput.addProcessor(uiStage);
            shapeRenderer = new ShapeRenderer();
            EngineSetup.addBaseSystemsToEngine(engine, this, shapeRenderer, drawEntityDebugBounds, mapAndEntityBuffer, lightSourceBuffer);
            currentMap = world.getCurrentMap();
            currentTiledMap = currentMap.getTiledMap();
            tiledMapRenderer = new DayAndNightTiledMapRenderer(currentTiledMap, 1);
//        stage.addActor(bottomBar.getBottomBar());
            //bottomBarHeight=bottomBar.getBottomBar().getHeight();
            if (player != null) {
                gameCamera.centerCameraToPosition(player.getPosition());
            }
            Gdx.input.setInputProcessor(gameInput.getLockableInputMultiplexer());
        }
    }
    @Override
    public void hide() {
        assetts.saveGame(world, assetts.getSettings().getPreferences().getString("savePath"));

    }
    @Override
    public void dispose() { // libgdx dispose method
        assetts.saveGame(world, assetts.getSettings().getPreferences().getString("savePath"));
        assetts.dispose();
        batch.dispose();
        uiStage.dispose();
        mapAndEntityBuffer.dispose();
        lightSourceBuffer.dispose();
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

    public void setMap(GameMap newMap){
      if(currentMap!=null) {
          this.currentMap.setCurrentMap(false);
      }
        newMap.setCurrentMap(true);
      EngineSetup.removeSystemsFromEngine(engine, currentMap.getMapGameEntitySystemsClasses());
      EngineSetup.addSystemsToEngine(engine, newMap.getMapGameEntitySystemsClasses());
       gameCamera.calculateScreenMaxes(newMap);
      currentTiledMap = currentMap.getTiledMap();
      tiledMapRenderer = new OrthogonalTiledMapRenderer(currentTiledMap, 1);
      if(player!=null){
          gameCamera.centerCameraToPosition(player.getPosition());
          this.currentMap =newMap;
      }
    }

    public ZRPGPlayer getPlayer() {
        return player;
    }

    /**
     *   sets the rpg player object and adds the players UI Bars
     * @param player  the player to set to
     */
    public void setPlayer(ZRPGPlayer player) {
        this.player = player;
        engine.getSystem(ZRPGPlayerSystem.class).setPlayer(player);
        engine.getSystem(PlaySoundSystem.class).setPlayer(player);
       DefaultZRPGBottomWindow defaultZRPGBottomWindow= new DefaultZRPGBottomWindow(getCurrentMap().getSkin(),  "windowCompass", this );
        defaultZRPGBottomWindow.setZrpgPlayer(player);
       addUIBarWindow(defaultZRPGBottomWindow, Direction.DOWN);
       gameCamera.setEntityToFollow(player.getPlayerEntity());
        gameCamera.centerCameraToPosition(player.getPosition());
    }

    /**
     *  adds  a libGDX scene 2d UI component to the  screen to be displayed
     * @param window
     * @param positionX
     * @param positionY
     */
    public void addWindow(Actor window, float positionX, float positionY) {
        window.setPosition(positionX, positionY);
        uiStage.addActor(window);
    }


    /**
     *  adds  a libGDX scene 2d UI component  to the  screen to be displayed
     * @param window
     * @param screenPosition

     */
    public void addWindow(Actor window, ScreenPosition screenPosition) {
        window.setPosition(screenPosition.getX(), screenPosition.getY());
        uiStage.addActor(window);
    }
    public void addSpeechBubble(Label label, float positionX, float positionY){
        label.setPosition(positionX, positionY);
        uiStage.addActor(label);
    }

    /**
     *  Method to convert a  x, y screen location to  a land square tile  on the map at map position x, y
     *
     *
     * @param x the screen x location in pixels
     * @param y the screen y location  in pixels
     *
     * @return LandSquareTile  the tile at given screen location
     */

    public LandSquareTile screenToTile(float x, float y){
        Vector3 vec3= gameCamera.unproject(new Vector3(x,y,0));
        LandSquareTile tile= currentMap.screenToTile(vec3.x, vec3.y);
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
    public TiledMap getCurrentTiledMap() {
        return currentTiledMap;
    }
    public boolean isDrawEntityDebugBounds() {
        return drawEntityDebugBounds;
    }

    /**
     *  set  whether or not show  the collision detection   bounding rectangles
     *  for ALL the entities in the system and adds the system for donig so
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
    public GameAssets getAssetts() {
        return assetts;
    }
    public Stage getUiStage() {
        return uiStage;
    }
    @Override
    public String getName() {
        return name;
    }
    public Viewport getMapViewport() {
        return mapViewport;
    }


    public  void addUIBarWindow(UITable uiWindow, Direction direction){
        if(uiBarSystem==null){
            uiBarSystem=new UIBarSystem(this);
            engine.addSystem(uiBarSystem);
        }
        switch (direction){
            case UP:
                uiBarSystem.setTopBar(uiWindow);
                addWindow(uiWindow, ScreenPosition.TOP);
                uiWindow.setSize(uiStage.getWidth(), uiWindow.getPrefHeight());
                gameCamera.setScreenMaxY(gameCamera.getScreenMaxY()+uiWindow.getHeight());
                break;
            case RIGHT:
                uiBarSystem.setRightBar(uiWindow);
                addWindow(uiWindow, ScreenPosition.RIGHT);
                uiWindow.setSize(uiWindow.getPrefWidth(),  uiStage.getHeight());
                gameCamera.setScreenMaxX(gameCamera.getScreenMaxX()+uiWindow.getWidth());
                break;
            case DOWN:
                uiBarSystem.setBottomBar(uiWindow);
                addWindow(uiWindow, ScreenPosition.BOTTOM);
                uiWindow.setSize(uiStage.getWidth(), uiWindow.getPrefHeight());
                gameCamera.setScreenMinY(gameCamera.getScreenMinY()-uiWindow.getHeight()+currentMap.getTileHeight());
                break;
            case LEFT:
                uiBarSystem.setLeftBar(uiWindow);
                addWindow(uiWindow, ScreenPosition.LEFT);
                uiWindow.setSize(uiWindow.getPrefWidth(),  uiStage.getHeight());
                gameCamera.setScreenMinX(gameCamera.getScreenMinX()-uiWindow.getWidth()+currentMap.getTileHeight());
                break;
        }
    }
}
