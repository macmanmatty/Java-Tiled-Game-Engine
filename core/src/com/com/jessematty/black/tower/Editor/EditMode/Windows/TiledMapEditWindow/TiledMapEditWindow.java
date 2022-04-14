package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapEditWindow;

public class TiledMapEditWindow   {
/*
    private PlaceMode placeMode = PlaceMode.PLACE;
    private SelectMode selectMode=SelectMode.SELECT;
    private Skin skin; // the UI skin
    private GameMapEdit gameMapEdit;// map editor class
    private GameMap currentMap; // the currentMap to Edit
    private TiledMapEdit tiledMapEdit;// the current tiled map to edit
    private BrightnessBatch batch; // the batch for rendering
    private GameCamera camera; // the camera
    private Engine engine;
    private ShapeRenderer shapeRenderer;
    private FrameBuffer mapFrameBuffer;
    private FrameBuffer lightFrameBuffer;
    private Viewport mapViewport;
    private TiledMapRenderer tiledMapRenderer;
    private World world;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private int currentLayerNumber;
    private Rectangle selectedArea = new Rectangle();
    private ClipBoard clipBoard;
    private final String name = "Map Edit Screen";
    private MapTools mapTools;
    private MapEditWindows mapEditWindows;
    private MapEditButtons mapEditButtons;
    private TopMenu topMenu;
    private boolean renderToBuffer=true;

    public TiledMapEditWindow(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
       // super(mapEditScreen, title, skin, style);
        float h = Gdx.graphics.getHeight();
        camera = new GameCamera(960, 960);
        float viewPortWidth = 960;
        float viewPortHeight = 960;
        mapViewport = new ExtendViewport(viewPortWidth, viewPortHeight, camera);
        tiledMapEdit= new TiledMapEdit( mapEditScreen.getGameAssets(), clipBoard);
        gameMapEdit= new GameMapEdit(mapEditScreen.getGameAssets(), clipBoard);
        mapTools = new MapTools(mapEditScreen);
        engine = world.getEngine();
        shapeRenderer = new ShapeRenderer();
    }

   // @Override
    public void act(float delta) {
        //super.act(delta);
    }


  //  @Override
    public void draw(Batch batch, float parentAlpha) {
        renderToBuffer=false;
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



        if(mapFrameBuffer!=null && renderToBuffer) {
            mapFrameBuffer.end();
        }





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

        if (object instanceof AtlasRegion) {
            tiledMapEdit.createStaticTiledMapTile(x, y, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());

        }


    }




    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        KeyListener keyListener=new KeyListener();
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
      //  getStage().setKeyboardFocus(null);
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

    @Override
    public boolean scrolled(int amount) {
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

 */

}
