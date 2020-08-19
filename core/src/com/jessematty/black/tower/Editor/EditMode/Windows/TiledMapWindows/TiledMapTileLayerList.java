package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemLabel;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListSource;

public class TiledMapTileLayerList extends ItemList<NamedTiledMapTileLayer> { // list that tracks a tiled map and it's layers



    private MapEditScreen mapEditScreen;
    private int mapLayers;
    public TiledMapTileLayerList(final MapEditScreen mapEditScreen, Skin skin, String titleName, String methodName, Class itemDataClass) {
        super(skin, titleName, methodName, itemDataClass, false);

        this.mapEditScreen = mapEditScreen;


    }

    @Override
    public void act(float delta) {

        TiledMap tiledMap=mapEditScreen.getTiledMapEdit().getCurrentTiledMap();
        if(tiledMap !=null && mapLayers!=tiledMap.getLayers().size()) {
            mapLayers=tiledMap.getLayers().size();
            // don't show the top map layer as it used for drawing preview images;
           Array<NamedTiledMapTileLayer> layers= tiledMap.getLayers().getByType(NamedTiledMapTileLayer.class);
            layers.removeIndex(layers.size-1);

          setItems(layers, false);


        }

        super.act(delta);

    }

    @Override
    public void setSelected(NamedTiledMapTileLayer item, boolean changeTable) {
        super.setSelected(item, changeTable);
        mapEditScreen.getTiledMapEdit().setCurrentLayer(item);
    }

    @Override
    public void setSelected(int index, boolean changeTable) {
        super.setSelected(index, changeTable);
        mapEditScreen.getTiledMapEdit().setCurrentLayer(getItems().get(index));
    }


}
