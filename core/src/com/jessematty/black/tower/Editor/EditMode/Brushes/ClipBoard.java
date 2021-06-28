package com.jessematty.black.tower.Editor.EditMode.Brushes;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.AnimatedTiledMapTileActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.CellActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.ClipBoardActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.StaticTiledMapTileActor;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.TextureRegionActor;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSettable;
import com.jessematty.black.tower.Maps.Buildings.Building;

// contains the current object to be copied or placed.
public class ClipBoard  extends Actor implements ItemSettable {
    private float  screenLocationX;
    private float  screenLocationY;
    private boolean snapToGrid;
    private Rectangle selectedArea;
    private Array<ClipBoardChangeListener> clipBoardChangeListeners= new Array<>();
    private Object currentObject; // the current object the clip board holds
    private ClipBoardActor clipBoardActor; // the current actor to draw for the clipboard icon
    private TextureRegion pointer;
    private String pointerTextureRegionName;
    private Clipboard systemClipboard= Gdx.app.getClipboard();


    public ClipBoard() {

    }

    // return a copy of the current cell
    public Cell getCurrentCell() {
        return MapTools.copyCell((Cell) currentObject);
        
    }
    public void setCurrentCell(Cell currentCell) {
        this.currentObject = currentCell;
    }
    //  if the clipboard contains a tiled cell rotates it  left
    public  void rotateCellLeft(){
        if(currentObject instanceof  Cell) {
            Cell currentCell= (Cell) currentObject;
            currentCell.setRotation(currentCell.getRotation() - 90);
        }

    }
    //  if the clipboard contains a tiled cell rotates it  right

    public void rotateCellRight(){
        if(currentObject instanceof  Cell) {
            Cell currentCell= (Cell) currentObject;
            currentCell.setRotation(currentCell.getRotation() + 90);
        }
    }

    // draws the currents actor
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
    //sets the screen locations for the clipboard object and actor
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

    // returns the selected area  that mouse has selected
    public Rectangle getSelectedArea() {
        return selectedArea;
    }
    public void setSelectedArea(Rectangle selectedArea) {
        this.selectedArea = selectedArea;
    }
    public Array<ClipBoardChangeListener> getClipBoardChangeListeners() {
        return clipBoardChangeListeners;
    }
    // returns the object the clipboard currently holds
    public Object getCurrentObject() {
        return currentObject;
    }
    // sets the clip board object and the actor image  for the current object
    public void setCurrentObject(Object currentObject) {
        this.currentObject = currentObject;
        setClipBoardActor();
    }
    // sets the actor to be drawn for the clipboard icon
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


    // sets the current pointer based of the current action
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
