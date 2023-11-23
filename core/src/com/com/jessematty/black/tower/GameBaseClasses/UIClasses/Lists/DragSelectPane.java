package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.utils.Array;
public class DragSelectPane <T> extends ScrollPane {
   private  Array<Array<T>> items= new Array<Array<T>>();
   private  boolean [] []  selectMap;
    private  T[][] selectedItems;
    private int xSize;
    private int ySize;
    private  int itemSize;
    private float startX;
    private float  startY;
    
    public DragSelectPane(Actor widget) {
        super(widget);
    }
    public DragSelectPane(Actor widget, Skin skin) {
        super(widget, skin);
    }
    public DragSelectPane(Actor widget, Skin skin, String styleName) {
        super(widget, skin, styleName);
    }
    public DragSelectPane(Actor widget, ScrollPaneStyle style) {
        super(widget, style);
    }
    
    private void addListener() {
       addListener(new DragScrollListener(this) {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
            }
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
                startX = x;
                startY = y;
            }
            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                
                
                Rectangle rectangle= new Rectangle(startX, startY, x-startX, y-startY);
                selectItems(rectangle);
               
            }
        });
    }
    
    
    
    private void selectItems(Rectangle selectedArea) {
        int x= (int) (selectedArea.x/itemSize);
        int y= (int) (selectedArea.y/itemSize);
        int xMax= (int) (selectedArea.width/itemSize)+1;
        int yMax= (int) (selectedArea.height/itemSize)+1;
        if(xMax>xSize){
            xMax=xSize;
        }
        if(yMax>ySize){
            yMax=ySize;
        }
        if(x<0){
            x=0;
        }
        if(y<0){
            y=0;
        }
        for (int countx=x; countx<xMax; countx++) {
            for (int county = y; county < yMax; county++) {
                selectMap[countx][county]=true;
                
            }
        }

        selectItems();

    }


    private void selectItems(){
        int widthx=0;
        int widthy=0;
        for (int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {


            }
        }


    }

    public Array<Array<T>> getItems() {
        return items;
    }

    public void setItems( Array<Array<T>> items) {
        this.items = items;
        xSize=items.size*itemSize;
        ySize=items.get(0).size*itemSize;
        this.selectMap=new boolean[items.size][items.get(0).size];

    }
}
