package com.jessematty.black.tower.Editor.EditMode.Brushes;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.AnimatedTiledMapTileActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.CellActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.ClipBoardActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.StaticTiledMapTileActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.TextureRegionActor;
import com.jessematty.black.tower.Editor.EditMode.MapTools.MapTools;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSettable;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class ClipBoard  extends Actor implements ItemSettable {
    private LandSquareTile [] [] tiles;
    private final MapEditScreen mapEditScreen;
    private float  screenLocationX;
    private float  screenLocationY;
    private boolean snapToGrid;
    private int tileSizeX;
    private int tileSizeY;
    private Rectangle selectedArea;
    private Array<ClipBoardChangeListener> clipBoardChangeListeners= new Array<>();
    private Object currentObject;
    private ClipBoardActor clipBoardActor;
    private TextureRegion pointer;
    private String pointerTextureRegionName;
    
    public ClipBoard(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
        this.tileSizeX=mapEditScreen.getTileWidth();
        this.tileSizeY=mapEditScreen.getTileHeight();
        pointer=mapEditScreen.getGameAssets().getAtlasRegionByName("pointer", "editorAssets");

    }
    public Cell getCurrentCell() {
        return MapTools.copyCell((Cell) currentObject);
        
    }
    public void setCurrentCell(Cell currentCell) {
        this.currentObject = currentCell;
    }
    public  void rotateCellLeft(){
        if(currentObject instanceof  Cell) {
            Cell currentCell= (Cell) currentObject;
            currentCell.setRotation(currentCell.getRotation() - 90);
        }
    }
    public void rotateCellRight(){
        if(currentObject instanceof  Cell) {
            Cell currentCell= (Cell) currentObject;
            currentCell.setRotation(currentCell.getRotation() + 90);
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(clipBoardActor!=null) {
            clipBoardActor.setLocations(screenLocationX, screenLocationY);
            clipBoardActor.draw(batch, parentAlpha);
        }

        batch.setColor(1,1,1,1);
    }
    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
    }
    public void setScreenLocations(float x , float y){
        this.screenLocationX=x;
        this.screenLocationY=y;
        clipBoardActor.setLocations(screenLocationX, screenLocationY);

    }
    public float getScreenLocationX() {
        return screenLocationX;
    }
    public float getScreenLocationY() {
        return screenLocationY;
    }
    public boolean isSnapToGrid() {
        return snapToGrid;
    }
    public void setSnapToGrid(boolean snapToGrid) {
        this.snapToGrid = snapToGrid;
    }
    public void setTileSize(int x, int y){
        this.tileSizeX=x;
        this.tileSizeX=y;
    }
    public Rectangle getSelectedArea() {
        return selectedArea;
    }
    public void setSelectedArea(Rectangle selectedArea) {
        this.selectedArea = selectedArea;
    }
    public Array<ClipBoardChangeListener> getClipBoardChangeListeners() {
        return clipBoardChangeListeners;
    }
    public Object getCurrentObject() {
        return currentObject;
    }
    public void setCurrentObject(Object currentObject) {
        this.currentObject = currentObject;
        setClipBoardActor();
    }
    private void setClipBoardActor() {
        if(currentObject instanceof  Cell ){
            TiledMapTile tiledMapTile=((Cell) currentObject).getTile();
            if(tiledMapTile!=null){
                if(tiledMapTile instanceof AtlasAnimatedTiledMapTile){
                    
                    clipBoardActor=new AnimatedTiledMapTileActor((AtlasAnimatedTiledMapTile) tiledMapTile);
                    clipBoardActor.setColor(((AtlasAnimatedTiledMapTile) tiledMapTile).getColor());
                    
                    
                    
                }
                
                else{
                    clipBoardActor=new StaticTiledMapTileActor((AtlasStaticTiledMapTile) tiledMapTile);
                    clipBoardActor.setColor(((AtlasStaticTiledMapTile) tiledMapTile).getColor());
                    
                    
                }
                
            }
            
        }
        
       else  if(currentObject instanceof TextureRegion){
            clipBoardActor= new TextureRegionActor((TextureRegion)currentObject);
            
        }
        else  if(currentObject instanceof Entity){
            EditorImageComponent drawable=((Entity) currentObject).getComponent(EditorImageComponent.class);
            if(drawable!=null) {
                clipBoardActor = new TextureRegionActor(drawable.getAtlasRegion());
            }
            clipBoardActor.setColor(drawable.getColor());
            
        }
        
        else if(currentObject instanceof Cell [] [] []){
            
            clipBoardActor= new CellActor((Cell[][][]) currentObject);
            
        }
        else if(currentObject instanceof Building){
            
            
        }

        else{


            setPointer();
                if(pointer!=null) {
                    clipBoardActor = new TextureRegionActor(pointer);
                }



        }
        
        
        
    }

    private  void  setPointer(){



    }





    @Override
    public void setItem(Object item) {

        setCurrentObject(item);
    }

    public String getPointerTextureRegionName() {
        return pointerTextureRegionName;
    }

    public void setPointerTextureRegionName(String pointerTextureRegionName) {
        this.pointerTextureRegionName = pointerTextureRegionName;
    }
}
