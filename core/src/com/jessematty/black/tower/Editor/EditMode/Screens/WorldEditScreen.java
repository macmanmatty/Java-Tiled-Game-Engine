package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.EntityGenerator;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedScreen;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Editor.EditMode.MapTools.MapTools;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.EditMode.Windows.CreateWindows.CreateWorldWindow;
import com.jessematty.black.tower.Editor.World.WorldObjects;

public    class WorldEditScreen implements NamedScreen, InputProcessor, EditScreen {
        private final  World world;
        private final  GameAssets assetts;
        private  GameComponentMapper gameComponentMapper;
        private final WorldObjects worldObjects;
        private EntityGenerator entityGenerator;
        private com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;
        private Stage uiStage;
        private Stage mapStage;
        private Batch batch;
        private OrthographicCamera camera;
        private  InputMultiplexer inputMultiplexer;
        private int tileSize=32;
        private Viewport viewPort;
        private int mapSquareSize=160;
        private TopMenu topMenu;
        private final String  name="World Edit Screen";
        private final Skin skin;
        private ImageButton[] [] mapButtons;
        private  Table mapButtonsTable= new Table();
        private  final DragAndDrop dragAndDrop = new DragAndDrop();
        private  final KeyListener keyListener= new KeyListener();
    public WorldEditScreen(Skin skin, GameAssets assetts, World world) {
        this.assetts=assetts;
        this.tileSize=tileSize;
        if(world==null){
            world= new World();
        }
        this.world=world;
        this.skin=skin;
        worldObjects= new WorldObjects();
        topMenu=new TopMenu();
        mapEditScreen =new com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen( assetts, topMenu,  dragAndDrop,  keyListener,  skin, world, worldObjects);
    }
    public void editMap(GameMap currentMap){
        mapEditScreen.changeMap(currentMap);
        assetts.setScreen(mapEditScreen);
    }
    @Override
    public void show() {
        this.uiStage =new Stage();
        batch= new SpriteBatch();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960*(w/h),960);
        camera.update();
        viewPort=new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        mapStage= new Stage( viewPort);
        mapStage.getBatch().setProjectionMatrix(camera.combined);
        batch=new SpriteBatch();
        inputMultiplexer= new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(mapStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        entityGenerator= new EntityGenerator(world, assetts);
        if(world.isNewWorld()){
            CreateWorldWindow createWorldWindow= new CreateWorldWindow(this, "Create World", skin, "default", world);
            createWorldWindow.makeWindow();
            Window window=createWorldWindow;
            window.setVisible(true);
            window.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
             uiStage.addActor(window);
        }
        else {
            createWorldMapsOverview(world.getXMaps(), world.getYMaps());
        }

     makeWindows();

    }

    private void makeWindows() {
        Table topMenuTable=topMenu.getMenuBar().getTable();
        float height=Gdx.graphics.getHeight();

        topMenuTable.setSize(Gdx.graphics.getWidth(), topMenuTable.getPrefHeight());
        height=height-topMenuTable.getPrefHeight();
        topMenuTable.setPosition(0, height);
        uiStage.addActor(topMenuTable);
        mapButtonsTable.setPosition(mapStage.getWidth()/2, mapStage.getHeight()/2f);

    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Keys.UP){
            camera.translate(new Vector2(0, tileSize));
        }
        if(keycode== Keys.DOWN){
            camera.translate(new Vector2(0, -tileSize));
        }
        if(keycode== Keys.LEFT){
            camera.translate(new Vector2(-tileSize, 0));
        }
        if(keycode== Keys.RIGHT){
            camera.translate(new Vector2(tileSize, 0));
        }
        if(keycode== Keys.ALT_LEFT ){
        }
        if(keycode== Keys.DOWN){
        }
        if(keycode== Keys.LEFT){
        }
        if(keycode== Keys.RIGHT){
        }
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
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.FOREST.r, Color.FOREST.g, Color.FOREST.b, Color.FOREST.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        uiStage.draw();
        uiStage.act();
        mapStage.act();
        mapStage.draw();
    }
    @Override
    public void resize(int width, int height) {
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
    public void createWorldMapsOverview(int xSize, int ySize){
        mapButtonsTable.clear();
        mapButtons= new MapButton[xSize][ySize];
        world.createWorld(xSize, ySize);
        for(int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                MapButton mapButton=new MapButton(skin, world.getMap(countx, county), this);
                mapButton.setSize(160, 160);
                mapButtons[countx][county]=mapButton;
                mapButtonsTable.add(mapButton);
            }
            mapButtonsTable.row();
        }
        mapStage.addActor(mapButtonsTable);
    }
    public void changeWordSize(int xSize, int ySize){
        MapTools.changeWorldSize(world, xSize, ySize);
        createWorldMapsOverview(xSize, ySize);
    }
    public World getWorld() {
        return world;
    }
    @Override
    public String getName() {
        return name;
    }
    public GameAssets getAssetts() {
        return assetts;
    }
    public WorldObjects getWorldObjects() {
        return worldObjects;
    }
    public MapEditScreen getMapEditScreen() {
        return mapEditScreen;
    }
    public Stage getUiStage() {
        return uiStage;
    }
    public Stage getMapStage() {
        return mapStage;
    }
    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }
    public KeyListener getKeyListener() {
        return keyListener;
    }
}
