package com.jessematty.black.tower.Editor.EditMode.Brushes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors.ClipBoardActor;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSettable;

/** contains the current object to be copied or placed.
 *
 */
public class ClipBoard   implements ItemSettable {
    /**
     * the locations of the current object on the screen
     */
    private float  screenLocationX;
    private float  screenLocationY;
    private boolean snapToGrid;
    /**
     * the selected area fo the screen
     */
    private Rectangle selectedArea;
    /**
     * objects that listen for in change in the current clipboard object
     */
    private Array<ClipBoardChangeListener> clipBoardChangeListeners= new Array<>();
    /**
     * the current object on the clipboard
     */
    private Object currentObject; // the current object the clip board holds
    /**
     *  the current actor for the clipboard object
     */
    private ClipBoardActor clipBoardActor; // the current actor to draw for the clipboard icon
    private TextureRegion pointer;
    private String pointerTextureRegionName;
    /**
     * the link to the system clipboard
     */
    private Clipboard systemClipboard= Gdx.app.getClipboard();
    /**
    
    whether or not to draw the clipboard actor
     **/

    
    public ClipBoard() {
    }
    // return a copy of the current cell
    public Cell getCurrentCell() {
        return MapTools.copyCell((Cell) currentObject);
        
    }
    public void setCurrentCell(Cell currentCell) {
        this.currentObject = currentCell;
    }
    /**  if the clipboard contains a tiled cell rotates it  left
     * 
     */
    public  void rotateCellLeft(){
        if(currentObject instanceof  Cell) {
            Cell currentCell= (Cell) currentObject;
            currentCell.setRotation(currentCell.getRotation() - 90);
        }
    }
    /**
     *     if the clipboard contains a tiled cell rotates it  right
      */
    public void rotateCellRight(){
        if(currentObject instanceof  Cell) {
            Cell currentCell= (Cell) currentObject;
            currentCell.setRotation(currentCell.getRotation() + 90);
        }
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
