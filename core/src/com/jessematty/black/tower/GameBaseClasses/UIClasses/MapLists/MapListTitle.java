package com.jessematty.black.tower.GameBaseClasses.UIClasses.MapLists;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListLabelStyle;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListTitleStyle;

public class MapListTitle<T> extends Label {
    private boolean changed;
    private int clicks;
    private boolean selected;
    private  LabelStyle titleCellLabelStyle;
    private  LabelStyle titleCellLabelStyleSortedUp;
    private  LabelStyle titleCellLabelStyleSortedDown;
    private StringMapList<T> itemList;
    private  int index;
    private int clickResetCounter;
    private String name="";
    private boolean sortDescending;
    private ItemListLabelStyle itemListLabelStyle;



    public MapListTitle(final StringMapList<T> itemList, final String name, Skin skin) {
        super("", skin, "itemListTitle");
        this.itemList = itemList;
        this.name = name;
        makeTitle();
    }

    public MapListTitle(final StringMapList<T> itemList, final String name, ItemListTitleStyle itemListStyle) {
        super(name,  itemListStyle.titleLabelStyle);
        this.itemList = itemList;
        this.name = name;
        makeTitle();
    }

    public void makeTitle(){
        setText(name);
        setLayoutEnabled(true);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               if(clicks==2 ) {
                  if(itemList.isSortable()){
                      itemList.sort(sortDescending);
                      sortDescending=!sortDescending;
                  }
                   clicks=0;
               }
               clicks++;
            }
        });

        addListener(new DragListener(){



            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                float columnWidth=x-getX();
                if(columnWidth<0){
                    columnWidth=0;
                }


                itemList.setLabelWidth(columnWidth);
                itemList.forceRemakeTable();

            }
        });



    }
    public void changeBackground(){
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
   private MapListTitle<T> getLabel(){
        return this;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        clickResetCounter++;
        if(clickResetCounter%120==0){
            clicks=0;
        }
    }
}
