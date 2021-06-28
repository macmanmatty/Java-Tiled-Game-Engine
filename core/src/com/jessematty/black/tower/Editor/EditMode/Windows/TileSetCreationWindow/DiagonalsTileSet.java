package com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.UIElements.TextureRegionSettableImage;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class DiagonalsTileSet {

    private Table table=new Table();
    private TileSet bitMaskableTileSet;
    private int tileWidth;
    private int tileHeight;

    private GameAssets gameAssets;
    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;


    public DiagonalsTileSet(TileSet bitMaskableTileSet,  MapEditScreen mapEditScreen) {
        this.bitMaskableTileSet = bitMaskableTileSet;
        this.tileWidth = mapEditScreen.getTileWidth();
        this.gameAssets = mapEditScreen.getGameAssets();
        this.tileHeight = mapEditScreen.getTileHeight();
        this.mapEditScreen = mapEditScreen;
    }

    Table makeTable(){
        AtlasRegion textureRegion126=gameAssets.getAtlasRegionByName("126", "assetts.atlas");
        Image image126= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion126);
        image126.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare126 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 126,bitMaskableTileSet, image126);
        table.add(tileSquare126.getActor());

        AtlasNamedAtlasRegion textureRegion219=gameAssets.getAtlasRegionByName("219", "assetts.atlas");
        Image image219= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion219);
        image219.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare219 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 219,bitMaskableTileSet, image219);
        table.add(tileSquare219.getActor());

        table.pack();
        table.validate();
        return table;




    }
}
