package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemLabel;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListAdapter;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListLabelStyle;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListSource;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListTarget;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListTitle;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListTitleStyle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TiledMapLayerList2 extends Table  implements ItemListAdapter<NamedTiledMapTileLayer> {
    private int size;
    private Array<NamedTiledMapTileLayer> items;
    private Array<ItemLabel<NamedTiledMapTileLayer>> itemLabels= new  Array<>();
    private Array<ImageButton> hideButtons= new Array<>();
    private Skin skin;
    private NamedTiledMapTileLayer selectedItem;
    private int selectedIndex;
    private boolean editable;
    private boolean dragToChangeOrder;
    private DragAndDrop dragAndDrop;
    private ItemLabel<NamedTiledMapTileLayer> selectedLabel;
    private boolean remakeTable;
    private String  methodName="";
    private Class itemDataClass=String.class;
    private ItemListTitle<NamedTiledMapTileLayer> itemListTitle;
    private float columnWidth=100f;
    private String titleName;
   private ItemListLabelStyle itemListLabelStyle;
   private ItemListTitleStyle itemListTitleStyle;
   private boolean displayTitle;
   private TextureRegionDrawable hideUp;
   private TextureRegionDrawable hideDown;
   private MapEditScreen mapEditScreen;
   private float rowHeight=15;

    public TiledMapLayerList2( MapEditScreen mapEditScreen, Skin skin, String titleName, String methodName, Class itemDataClass, boolean displayTitle) {
       this(mapEditScreen,skin,"default", titleName, methodName, itemDataClass,  0, displayTitle);
    }
    public TiledMapLayerList2( MapEditScreen mapEditScreen, Skin skin, String styleName, String titleName, String methodName, Class itemDataClass,  int tableColumnIndex, boolean displayTitle) {
        this.skin = skin;
        this.displayTitle=displayTitle;
        this.itemListLabelStyle=skin.get(styleName, ItemListLabelStyle.class);
        this.itemListTitleStyle=skin.get(styleName, ItemListTitleStyle.class);
        this.methodName=methodName;
        this.mapEditScreen=mapEditScreen;

        this.itemDataClass=itemDataClass;


        this.titleName=titleName;

        hideUp= new TextureRegionDrawable(mapEditScreen.getGameAssets().getAtlasRegionByName("hideUp"));
        hideDown= new TextureRegionDrawable(mapEditScreen.getGameAssets().getAtlasRegionByName("hideDown"));



    }
    @Override
    public void act(float delta) {
        TiledMap tiledMap=mapEditScreen.getTiledMapEdit().getCurrentTiledMap();
        if(tiledMap !=null) {
            setItems(tiledMap.getLayers().getByType(NamedTiledMapTileLayer.class), false);
        }
        super.act(delta);



        if(items!=null){
        if((size!=items.size) || remakeTable==true) {
                size = items.size;


            remakeTable=false;
            makeItems();
        }
    }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }
    private void makeItems(){
        int size=items.size;
        itemLabels.clear();
        clear();
        if(displayTitle==true) {
            add(itemListTitle).width(columnWidth);
        }

        row();
        for(int count=0; count<size; count++){
            NamedTiledMapTileLayer tiledMapTileLayer=items.get(count);
           ItemLabel<NamedTiledMapTileLayer> label= new ItemLabel<NamedTiledMapTileLayer>(this,  count,   itemListLabelStyle, tiledMapTileLayer,editable, methodName, itemDataClass);
           label.setSize(getMaxWidth(), getMaxHeight());
            itemLabels.add(label);
            if(dragToChangeOrder==true && dragAndDrop!=null){
                if(label!=null) {
                    dragAndDrop.addTarget(new ItemListTarget<NamedTiledMapTileLayer>(this, label));
                    dragAndDrop.addSource(new ItemListSource<NamedTiledMapTileLayer>(this, label, skin));
                }
            }
            HorizontalGroup horizontalGroup= new HorizontalGroup();
            ImageButton hideButton= new MapLayerHideButton(tiledMapTileLayer, hideUp, hideDown);
            horizontalGroup.addActor(label);
            horizontalGroup.addActor(hideButton);
            horizontalGroup.space(2);
            add(horizontalGroup).width(columnWidth).height(rowHeight);
            add();
            row();
        }
        invalidateHierarchy();
        setBounds(getX(), getY(), columnWidth, getPrefHeight());

    }
    public Array<NamedTiledMapTileLayer> getItems() {
        return items;
    }
    public void setItems(Array<NamedTiledMapTileLayer> items, boolean trackItems) {
        if(trackItems==true) {
            this.items = items;
        }
        else{
            this.items= new Array<>(items.size);
            this.items.addAll(items);
            
        }
        remakeTable=true;
    }
    public   void setSelected(NamedTiledMapTileLayer item, boolean changeTable){
        this.selectedItem=item;
        for( int count=0; count<size; count++){
            ItemLabel label= itemLabels.get(count);
            if(label.getItem()==item){
                label.setSelected(true);
                selectedLabel=label;
                count=selectedIndex;
            }
            else{
                label.setSelected(false);
            }
        }


        fire(new ChangeEvent());
        mapEditScreen.getTiledMapEdit().setCurrentLayer(item);


    }
    public NamedTiledMapTileLayer getSelectedItem() {
        return selectedItem;
    }
    public ItemLabel<NamedTiledMapTileLayer> getSelectedLabel() {
        return selectedLabel;
    }
    public int getSelectedIndex() {
        return selectedIndex;
    }
    public void setSelected(int index, boolean changeTable) {
        this.selectedIndex=index;

        for( int count=0; count<size; count++){
           ItemLabel label= itemLabels.get(count);
            if(label.getIndex()==index){
                label.setSelected(true);
                selectedLabel=label;
                selectedItem= (NamedTiledMapTileLayer) label.getItem();
            }
            else{
                label.setSelected(false);
            }
        }

        mapEditScreen.getTiledMapEdit().setCurrentLayer(getItems().get(index));

        fire(new ChangeEvent());

    }
    public void setEditableLabel(ItemLabel setLabel) {
        for (int count = 0; count < size; count++) {
            ItemLabel label = itemLabels.get(count);
            if (setLabel == label) {
                label.displayTextField();
            } else {
                label.displayLabel();
            }
        }
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
        remakeTable=true;
    }
    public boolean isEditable() {
        return editable;
    }
    public void forceRemakeTable() {
        this.remakeTable = true;

    }
   public void  enableDragOrderChanging(DragAndDrop dragAndDrop){
        this.dragToChangeOrder=true;
        this.dragAndDrop=dragAndDrop;
    }
    public void  enableDragOrderChanging(){
        this.dragToChangeOrder=true;
        this.dragAndDrop=new DragAndDrop();
    }
    public void disableDragOrderChanged(){
        dragAndDrop=null;
        this.dragToChangeOrder=false;
    }


    public ItemListTitle<NamedTiledMapTileLayer> getItemListTitle() {
        return itemListTitle;
    }
    public  String getSelectedData(){
        return String.valueOf(selectedLabel.getText());
    }
    public String  getItemValue(NamedTiledMapTileLayer item)  {
        String value="";
        if(item!=null){
            Class<NamedTiledMapTileLayer> cls= (Class<NamedTiledMapTileLayer>) item.getClass();
            Method method = null;
            try {
                method = cls.getDeclaredMethod("get"+methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Method Not Found");
            }
            try {
                value= String.valueOf(method.invoke(item));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Method Not Found");
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("Method Not Found");
            }
        }
        return value;
    }
    public float getColumnWidth() {
        return columnWidth;
    }
    public void setColumnWidth(float columnWidth) {
        this.columnWidth = columnWidth;

    }





    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTitleName() {
        return titleName;
    }

    public Class getItemDataClass() {
        return itemDataClass;
    }

    @Override
    public Skin getSkin() {
        return skin;
    }

    public Array<ItemLabel<NamedTiledMapTileLayer>> getItemLabels() {
        return itemLabels;
    }

    public boolean isDisplayTitle() {
        return displayTitle;
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    public float getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(float rowHeight) {
        this.rowHeight = rowHeight;
    }
}
