package com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.UIElements.TextureRegionSettableImage;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class InnerCornerTileSet {

    private Table table=new Table();
    private TileSet bitMaskableTileSet;
    private int tileWidth;
    private int tileHeight;
    private GameAssets gameAssets;
    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;

    public InnerCornerTileSet(TileSet bitMaskableTileSet,  MapEditScreen mapEditScreen) {
        this.bitMaskableTileSet = bitMaskableTileSet;
        this.tileWidth = mapEditScreen.getTileWidth();
        this.gameAssets = mapEditScreen.getGameAssets();
        this.tileHeight = mapEditScreen.getTileHeight();
        this.mapEditScreen = mapEditScreen;
    }

    Table makeTable(){
        AtlasRegion textureRegion127=gameAssets.getAtlasRegionByName("127", "assetts.atlas");
        Image image127= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion127);
        image127.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare127 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 127,bitMaskableTileSet, image127);
        table.add(tileSquare127.getActor());

        AtlasRegion textureRegion223=gameAssets.getAtlasRegionByName("223", "assetts.atlas");
        Image image223= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion223);
        image223.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare223 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 223,bitMaskableTileSet, image223);
        table.add(tileSquare223.getActor());
        
        table.row();

        AtlasRegion textureRegion251=gameAssets.getAtlasRegionByName("251", "assetts.atlas");
        Image image251= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion251);
        image251.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare251 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 251,bitMaskableTileSet, image251);
        table.add(tileSquare251.getActor());

        AtlasRegion textureRegion254=gameAssets.getAtlasRegionByName("254", "assetts.atlas");
        Image image254= new TextureRegionSettableImage(mapEditScreen.getClipBoard(), textureRegion254);
        image254.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare254 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 254,bitMaskableTileSet, image254);
        table.add(tileSquare254.getActor());

        table.pack();
        table.validate();
        return table;




    }
}
