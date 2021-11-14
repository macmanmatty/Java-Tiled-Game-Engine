package com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.UIElements.TextureRegionSettableImage;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class SingleTilesTileSet {

    private Table table=new Table();
    private TileSet bitMaskableTileSet;
    private int tileWidth;
    private int tileHeight;
    
    private GameAssets gameAssets;
    private final MapEditScreen mapEditScreen;


    public SingleTilesTileSet(TileSet bitMaskableTileSet,MapEditScreen mapEditScreen) {
        this.bitMaskableTileSet = bitMaskableTileSet;
        this.tileWidth = mapEditScreen.getTileWidth();
        this.gameAssets = mapEditScreen.getGameAssets();
        this.tileHeight = mapEditScreen.getTileHeight();
        this.mapEditScreen = mapEditScreen;
    }

    Table makeTable(){
        TextureRegion empty= gameAssets.getAtlasRegionByName("empty", "assetts.atlas");
        Image emptyImage= new Image(empty);
        emptyImage.setSize(tileWidth, tileHeight);
        table.add(emptyImage);
        
        AtlasRegion textureRegion2=gameAssets.getAtlasRegionByName("2", "assetts.atlas");
        Image image2= new TextureRegionSettableImage(mapEditScreen.getClipBoard(),empty);
        image2.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare2 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(),2,bitMaskableTileSet, image2);
        table.add(tileSquare2.getActor());
        
        TextureRegion empty2= gameAssets.getAtlasRegionByName("empty2", "assetts.atlas");
        Image empty2Image= new Image(empty);
        empty2Image.setSize(tileWidth, tileHeight);
        table.add(empty2Image);
        table.row();

        AtlasRegion textureRegion8=gameAssets.getAtlasRegionByName("8", "assetts.atlas");
        Image image8=  new TextureRegionSettableImage(mapEditScreen.getClipBoard(),empty);
        image8.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare8 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(),8,bitMaskableTileSet, image8);
        table.add(tileSquare8.getActor());

        TextureRegion empty3= gameAssets.getAtlasRegionByName("empty3", "assetts.atlas");
        Image empty3Image= new Image(empty);
        empty3Image.setSize(tileWidth, tileHeight);
        table.add(empty3Image);

        AtlasRegion textureRegion16=gameAssets.getAtlasRegionByName("16", "assetts.atlas");
        Image image16=new TextureRegionSettableImage(mapEditScreen.getClipBoard(),empty);
        image16.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare16 = new com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(),16,bitMaskableTileSet, image16);
        table.add(tileSquare16.getActor());
        table.row();

        TextureRegion empty4= gameAssets.getAtlasRegionByName("empty4", "assetts.atlas");
        Image empty4Image= new Image(empty);
        empty4Image.setSize(tileWidth, tileHeight);
        table.add(empty4Image);

        AtlasRegion textureRegion64=gameAssets.getAtlasRegionByName("64", "assetts.atlas");
        Image image64=new TextureRegionSettableImage(mapEditScreen.getClipBoard(),empty);
        image64.setSize(tileWidth, tileHeight);
        com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.TileSetTextureRegionDraggableTarget tileSquare64 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(),64,bitMaskableTileSet, image64);
        table.add(tileSquare64.getActor());

        TextureRegion empty5= gameAssets.getAtlasRegionByName("empty5", "assetts.atlas");
        Image empty5Image= new Image(empty);
        empty5Image.setSize(tileWidth, tileHeight);
        table.add(empty5Image);
        table.pack();
        table.validate();
        return table;




    }
}
