package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Engine.EngineSetup;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch;
import com.jessematty.black.tower.GameBaseClasses.Rendering.DayAndNightTiledMapRenderer;

import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars.DefaultRPGBottomWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.TopBars.DefaultRPGTopWindow;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.UIWindow;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.BoundingBoxRenderer;
import com.jessematty.black.tower.Systems.PlaySoundSystem;
import com.jessematty.black.tower.Systems.ZRPGPlayerSystem;

public class MapDraw implements NamedScreen {// class for drawing the currentGameMap and actors plants, fighters, item ECT on to the screen
     protected TiledMap currentTiledMap;
    protected GameMap currentMap;
    private Stage uiStage;
    private   OrthographicCamera camera; // the camera for rendering the currentGameMap and actors
      private TiledMapRenderer tiledMapRenderer;
      private InputMultiplexer input= new InputMultiplexer();
    private  SpriteBatch batch;
    private GameAssets assetts;
    private   ShapeRenderer shapeRenderer;
    private GameComponentMapper gameComponentMapper;
    private  Engine engine;
    private ZRPGPlayer player;
    private Viewport mapViewport;
    private Viewport stageViewport;
    private int xSize; // the x size of the landSquareTileMap
    private int ySize;// the y size of the landSquareTileMap
    private GameTime gameTime= new GameTime();
    private  FrameBuffer mapAndEntityBuffer;
    private FrameBuffer lightSourceBuffer;
    private  float minWorldWidth=960;
    private float minWorldWHeight=960;
    private boolean drawEntityDebugBounds;
    private  float viewPortWidth;
    private float viewPortHeight;
    protected World world;
    private  String currentGamePath;
    private BoundingBoxRenderer boundingBoxRenderer;
    private boolean showBars;
    private final KeyListener keyListener= new KeyListener();
    private final String  name="Map Draw Screen";

    public MapDraw(GameAssets assetts, World world, boolean drawEntityDebugBounds){
        this.assetts=assetts;
        engine=world.getEngine();
        this.world=world;
        gameComponentMapper=world.getGameComponentMapper();
        if(world.getWorldMap()!=null) {
            this.currentMap = world.getStartMap();
            this.currentMap.setCurrentMap(true);
            this.ySize = currentMap.getYSize();
            this.xSize = currentMap.getXSize();
            changeMap(world.getMap(world.getStartMapX(), world.getStartMapY()));
        }
        else {
            throw new IllegalArgumentException("World Must Have Maps!");
        }
        this.currentGamePath=world.getLoadPath();
        float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    camera = new OrthographicCamera();
        mapViewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewPortWidth=500*(w/h);
    viewPortHeight=500;
    camera.setToOrtho(false,viewPortWidth,viewPortHeight);
    camera.update();
    batch=new com.jessematty.black.tower.GameBaseClasses.Rendering.BrightnessBatch();
    //mapAndEntityBuffer= new FrameBuffer(Format.RGBA8888, 960, 960, false);
   // lightSourceBuffer= new FrameBuffer(Format.RGBA8888, 960, 960, false);
        stageViewport= new FillViewport(minWorldWidth, minWorldWHeight);
    uiStage = new Stage(stageViewport);
    input.addProcessor(uiStage);
    //input.addProcessor(keyListener);
    shapeRenderer =new ShapeRenderer();
    boundingBoxRenderer= new BoundingBoxRenderer(shapeRenderer);
           EngineSetup.addBaseSystemsToEngine(engine, this, shapeRenderer, drawEntityDebugBounds, mapAndEntityBuffer, lightSourceBuffer);
        this.drawEntityDebugBounds =drawEntityDebugBounds;
        Gdx.input.setInputProcessor(input);
    }
    @Override
        public void show () {
        float w = Gdx.graphics.getWidth();
            float h = Gdx.graphics.getHeight();


        //stage.addActor(bottomBar.getBottomBar());
        //bottomBarHeight=bottomBar.getBottomBar().getHeight();
            if(player !=null){
                centerCameraToPlayer();
            }
        }
        @Override
        public void render (float time) {
            Gdx.gl.glClearColor(1, 1, 1, 1); // clear the screen
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // move camera to player position
            if(player!=null) {
                Movable movable = player.getMovable();
                if(movable!=null) {
                    if ( movable.isMoved()) {
                        moveCameraWithFollowFighter(movable.getDistanceMoved());
                    }
                }
            }
            // draw game components  on   the screen
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            shapeRenderer.setProjectionMatrix(camera.combined);
                tiledMapRenderer.setView(camera);
                tiledMapRenderer.render();
            float  deltaTime=Gdx.graphics.getDeltaTime();
            currentMap.mapTurnActions(deltaTime, gameTime);
            gameTime.countTime();
            engine.update(deltaTime);
            //bottomBar.update();
        }
       public void moveCameraWithFollowFighter(Vector3 distance){ // translates the camera position  to  the player the camera   is following
        // distance is how far the fighter moved
           camera.translate(distance);
        cameraBoundsCheck();
       }
       public void cameraBoundsCheck(){
           float cameraHalfWidth=camera.viewportWidth*.5f;
           float cameraHalfHeight=camera.viewportHeight*.5f;
           float cameraLeft = camera.position.x - cameraHalfWidth;
           float cameraRight = camera.position.x + cameraHalfWidth;
           float cameraBottom = camera.position.y - cameraHalfHeight;
           float cameraTop = camera.position.y + cameraHalfHeight;
           float screenMaxX=xSize*32;
           float screenMaxY=(ySize*32);
           if(cameraLeft <= 0)
           {
               camera.position.x =cameraHalfWidth;
           }
           else if(cameraRight >= screenMaxX)
           {
               camera.position.x = screenMaxX- cameraHalfWidth;
           }
           else{
               centerCameraToPlayerWidth();
           }
// Vertical axis
         if(cameraTop >= screenMaxY)
           {
               camera.position.y = screenMaxY - cameraHalfHeight;
           }
           else if(cameraBottom <=0)
           {
               camera.position.y = cameraHalfHeight;
           }
           else{
               centerCameraToPlayerHeight();
           }
       }
    private void centerCameraToPlayerWidth(){
        Position position=player.getPosition();
        float positionX= position.getScreenLocationX();
        float cameraHalfWidth=camera.viewportWidth*.5f;
        camera.position.x=positionX;
        float screenMaxX=xSize*32;
        if(positionX<cameraHalfWidth ){
            camera.position.x=cameraHalfWidth;
        }
        else if(positionX>screenMaxX-cameraHalfWidth){
            camera.position.x=screenMaxX-cameraHalfWidth;
        }
    }
    private void centerCameraToPlayerHeight(){ // same as below but only  for height
        Position position=player.getPosition();
        float positionY= position.getScreenLocationY();
        float cameraHalfHeight=camera.viewportHeight*.5f;
        camera.position.y=positionY;
        float screenMaxY=ySize*32;
        if(positionY<cameraHalfHeight ){
            camera.position.y=cameraHalfHeight;
        }
        else if(positionY>screenMaxY-cameraHalfHeight){
            camera.position.y=screenMaxY-cameraHalfHeight;
        }
    }
    private void centerCameraToPlayer(){ // centers the camera to the positions position
        Position position=player.getPosition();
        float playerX=position.getScreenLocationX();;
        float playerY=position.getScreenLocationY();;
        float positionX= playerX;
        float positionY= playerY;
        float cameraHalfWidth=camera.viewportWidth*.5f;
        float cameraHalfHeight=camera.viewportHeight*.5f;
        camera.position.y=positionY;
        camera.position.x=positionX;
        float screenMaxX=xSize*32;
        float screenMaxY=ySize*32;
        // make sure the camera doesn't leave the currentGameMap bounds
        if(positionX<cameraHalfWidth ){
            camera.position.x=cameraHalfWidth;
        }
        else if(playerX>screenMaxX-cameraHalfWidth){
            camera.position.x=screenMaxX-cameraHalfWidth;
        }
        if(playerY<cameraHalfHeight ){
            camera.position.y=cameraHalfHeight;
        }
        else if(playerY>screenMaxY-cameraHalfHeight){
            camera.position.x=screenMaxY-cameraHalfHeight;
        }
    }
  public void   changeMap(GameMap newMap){
        // sets/ changes  the current gameMap  This  MUST  be called BEFORE  this screen is displayed to set the map to  render
      if(currentMap!=null) {
          this.currentMap.setCurrentMap(false);
      }
        newMap.setCurrentMap(true);
      EngineSetup.removeSystemsFromEngine(engine, currentMap.getMapGameEntitySystems());
      EngineSetup.addSystemsToEngine(engine, newMap.getMapGameEntitySystems());
        this.ySize=newMap.getYSize();
        this.xSize=newMap.getXSize();
      currentTiledMap = currentMap.getTiledMap();
      tiledMapRenderer = new DayAndNightTiledMapRenderer(currentTiledMap, 1);
      if(player!=null){
          centerCameraToPlayer();
          this.currentMap =newMap;
      }
    }
    @Override
    public void resize(int width, int height) {
        if(camera!=null) {

            if(mapViewport!=null) {
            mapViewport.update(width, height, false);
        }
        if(stageViewport!=null) {
            stageViewport.update(width, height, false);
        }
            camera.update();
        }

    }
    @Override
    public void pause() {
        //assetts.saveGame(world, world.getLoadPath());
    }
    @Override
    public void resume() {
        if(false) {
            try {
                World world = assetts.loadGame(currentGamePath);
                currentMap = world.getCurrentMap();
            } catch (MapLoadingExeception mapLoadingExeception) {
                mapLoadingExeception.printStackTrace();
            }
            int graphicsWidth = Gdx.graphics.getWidth();
            int  graphicsHeight = Gdx.graphics.getHeight();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 500 * (graphicsWidth / graphicsHeight), 500);
            camera.update();
            mapViewport = new ExtendViewport(minWorldWidth, minWorldWHeight, camera);
            batch = new BrightnessBatch();
            mapAndEntityBuffer = new FrameBuffer(Format.RGBA8888, graphicsWidth, graphicsHeight, false);
            lightSourceBuffer = new FrameBuffer(Format.RGBA8888, graphicsWidth, graphicsHeight, false);
            batch.setProjectionMatrix(camera.combined);
            stageViewport= new FitViewport(graphicsWidth, graphicsHeight);
            uiStage = new Stage(new FitViewport(graphicsWidth, graphicsHeight));
            input.addProcessor(uiStage);
            shapeRenderer = new ShapeRenderer();
            EngineSetup.addBaseSystemsToEngine(engine, this, shapeRenderer, drawEntityDebugBounds, mapAndEntityBuffer, lightSourceBuffer);
            currentMap = world.getCurrentMap();
            currentTiledMap = currentMap.getTiledMap();
            tiledMapRenderer = new DayAndNightTiledMapRenderer(currentTiledMap, 1);
//        stage.addActor(bottomBar.getBottomBar());
            //bottomBarHeight=bottomBar.getBottomBar().getHeight();
            if (player != null) {
                centerCameraToPlayer();
            }
            Gdx.input.setInputProcessor(input);
        }
    }
    @Override
    public void hide() {
        this.currentGamePath=world.getLoadPath();
        assetts.saveGame(world, currentGamePath);
    }
    @Override
    public void dispose() { // libgdx dispose method
        assetts.saveGame(world, currentGamePath);
        assetts.dispose();
        batch.dispose();
        uiStage.dispose();
        mapAndEntityBuffer.dispose();
        lightSourceBuffer.dispose();
    }
   
    public void setKeyboardToFighter(ZRPGPlayer fighter){
    }

   
    public void addInfoToConsole(String string){
   }
    public ZRPGPlayer getPlayer() {
        return player;
    }
    public void setPlayer(ZRPGPlayer player) {
        this.player = player;
        engine.getSystem(ZRPGPlayerSystem.class).setPlayer(player);
        engine.getSystem(PlaySoundSystem.class).setPlayer(player);
        centerCameraToPlayer();
    }
    public void addWindow(Actor window, float positionX, float positionY) {
        Vector3 vec= camera.project(new Vector3(positionX,positionY,0));
        window.setPosition(vec.x, vec.y);
        uiStage.addActor(window);
    }
    public void addWindow(Actor window, ScreenPosition screenPosition) {
        Vector3 vec= camera.project(new Vector3(Gdx.graphics.getWidth()/2, 0,0));
        window.setPosition(vec.x, vec.y);
        uiStage.addActor(window);
    }
    public void addSpeechBubble(Label label, float positionX, float positionY){
        label.setPosition(positionX, positionY);
        uiStage.addActor(label);
    }
    public void moveCameraX(float distance){
        camera.translate(distance, 0);
    }
    public void moveCameraY(float distance){
        camera.translate(0, distance);
    }
    public void moveCamera(float distanceX, float distanceY){
        camera.translate(distanceX, distanceY);
    }
    public LandSquareTile screenToTile(float x, float y){
        Vector3 vec3= camera.unproject(new Vector3(x,y,0));
        LandSquareTile tile= currentMap.screenToTile(vec3.x, vec3.y);
        return tile;
    }
    public GameMap getCurrentMap(){
        return currentMap;
    }
    public float getViewPortHeight(){
        return camera.viewportHeight;
    }
    public float getViewPortWidth(){
        return camera.viewportWidth;
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
    public InputMultiplexer getInput() {
        return input;
    }
    public Engine getEngine() {
        return engine;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public TiledMap getCurrentTiledMap() {
        return currentTiledMap;
    }
    public GameComponentMapper getGameComponentMapper() {
        return gameComponentMapper;
    }
    public boolean isDrawEntityDebugBounds() {
        return drawEntityDebugBounds;
    }
    public void setDrawEntityDebugBounds(boolean drawEntityDebugBounds) {
        this.drawEntityDebugBounds = drawEntityDebugBounds;
        if(drawEntityDebugBounds ==true) {
            engine.addSystem(boundingBoxRenderer);
        }
        else{
            engine.removeSystem(boundingBoxRenderer);
        }
    }
    public void setViewPortWidth(float viewPortWidth) {
        this.viewPortWidth = viewPortWidth;
    }
    public void setViewPortHeight(float viewPortHeight) {
        this.viewPortHeight = viewPortHeight;
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

    public KeyListener getKeyListener() {
        return keyListener;
    }
    @Override
    public String getName() {
        return name;
    }
    public Viewport getMapViewport() {
        return mapViewport;
    }


    public float getMinWorldWidth() {
        return minWorldWidth;
    }
    public void setMinWorldWidth(float minWorldWidth) {
        this.minWorldWidth = minWorldWidth;
    }
    public float getMinWorldWHeight() {
        return minWorldWHeight;
    }
    public void setMinWorldWHeight(float minWorldWHeight) {
        this.minWorldWHeight = minWorldWHeight;
    }
}
