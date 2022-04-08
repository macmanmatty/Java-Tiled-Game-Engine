package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapLayerWindow;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapEdit;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;

public class TiledMapTileLayerList extends ItemList<NamedTiledMapTileLayer>  { // list that tracks a tiled map and it's layers
    private int mapLayers;
    private TiledMapEdit tiledMapEdit;
    public TiledMapTileLayerList(final TiledMapEdit tiledMapEdit, Skin skin, String titleName, String methodName, Class itemDataClass) {
        super(skin, titleName, methodName, itemDataClass, false);
        this.tiledMapEdit=tiledMapEdit;

       
    }
    @Override
    public void act(float delta) {
        TiledMap tiledMap=tiledMapEdit.getCurrentTiledMap();
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
        tiledMapEdit.setCurrentLayer(item);
    }
    @Override
    public void setSelected(int index, boolean changeTable) {
        super.setSelected(index, changeTable);
        tiledMapEdit.setCurrentLayer(getItems().get(index));
    }
}
