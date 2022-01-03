package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenuWorld;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Generators.Entity.EntityGenerator;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.CreateWorldOptionPane;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.Maps.WorldSettable;

public    class WorldEditScreen implements NamedScreen, InputProcessor, EditScreen, WorldSettable {
        private   World world;
        private final  GameAssets gameAssets;
        private WorldObjects worldObjects;
        private EntityGenerator entityGenerator;
        private MapEditScreen mapEditScreen;
        private Stage uiStage;
        private Stage mapStage;
        private OrthographicCamera camera;
        private  InputMultiplexer inputMultiplexer;
        private GameMap lastMapEdited;
        private Viewport viewPort;
        private TopMenuWorld topMenu;
        private final String  name="World Edit Screen";
        private final Skin skin;
        private ImageButton[] [] mapButtons;
        private  Table mapButtonsTable= new Table();
        private  final DragAndDrop dragAndDrop = new DragAndDrop();
        private  final KeyListener keyListener= new KeyListener();
        private final  ClipBoard clipBoard= new ClipBoard();
        public WorldEditScreen(Skin skin, GameAssets gameAssets, World world) {
        this.gameAssets = gameAssets;
        if(world==null){
            world= new World();
        }
        this.world=world;
        this.skin=skin;
        worldObjects= new WorldObjects();
        mapEditScreen =new MapEditScreen(gameAssets, clipBoard,   dragAndDrop,  skin, world, worldObjects);

        }
    public void editMap(GameMap currentMap){
        mapEditScreen.setMap(currentMap);
        gameAssets.setScreen(mapEditScreen);
    }
    @Override
    public void show() {
        this.uiStage =new Stage();
        topMenu=new TopMenuWorld(this);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960*(w/h),960);
        camera.update();
        viewPort=new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        mapStage= new Stage( viewPort);
        mapStage.getBatch().setProjectionMatrix(camera.combined);
        inputMultiplexer= new InputMultiplexer();
        inputMultiplexer.addProcessor(keyListener);
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(mapStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        entityGenerator= new EntityGenerator(world, gameAssets);
        if(world.isNewWorld()){
            CreateWorldOptionPane createWorldOptionPane = new CreateWorldOptionPane(this, "Create World", skin, "default", world);
            createWorldOptionPane.makeWindow();
            Window window= createWorldOptionPane;
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


    @Override
    public World getWorld() {
        return world;
    }


    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String getName() {
        return name;
    }
    public GameAssets getGameAssets() {
        return gameAssets;
    }
    @Override
    public WorldObjects getWorldObjects() {
        return worldObjects;
    }
    public void setWorldObjects(WorldObjects worldObjects) {
        this.worldObjects = worldObjects;
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

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }
    @Override
    public GameMap getMap() {
        return lastMapEdited;
    }

    public void changeMap(GameMap lastMapEdited) {
        this.lastMapEdited = lastMapEdited;
    }

    public Skin getSkin() {
        return skin;
    }
}
