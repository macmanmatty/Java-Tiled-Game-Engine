package com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.UIElements.TextureRegionSettableImage;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskableTileSet;

public class OuterCornerTileSet {

    private Table table=new Table();
    private TileSet bitMaskableTileSet;
    private int tileWidth;
    private int tileHeight;
    private GameAssets gameAssets;
    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;


    public OuterCornerTileSet(TileSet bitMaskableTileSet, MapEditScreen mapEditScreen) {
        this.bitMaskableTileSet = bitMaskableTileSet;
        this.tileWidth = mapEditScreen.getTileWidth();
        this.gameAssets = mapEditScreen.getGameAssets();
        this.tileHeight = mapEditScreen.getTileHeight();
        this.mapEditScreen = mapEditScreen;
    }

    Table makeTable(){
        AtlasRegion textureRegion208=gameAssets.getAtlasRegionByName("208", "assetts.atlas");
        Image image208= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion208);
        image208.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare208 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 208,bitMaskableTileSet, image208);
        table.add(tileSquare208.getActor());

        AtlasRegion textureRegion248=gameAssets.getAtlasRegionByName("248", "assetts.atlas");
        Image image248= new TextureRegionSettableImage(mapEditScreen.getClipBoard(), textureRegion248);
        image248.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare248 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 248,bitMaskableTileSet, image248);
        table.add(tileSquare248.getActor());

        AtlasRegion textureRegion104=gameAssets.getAtlasRegionByName("104", "assetts.atlas");
        Image image104= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion104);
        image104.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare104 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 104,bitMaskableTileSet, image104);
        table.add(tileSquare104.getActor());
        table.row();

        AtlasRegion textureRegion214=gameAssets.getAtlasRegionByName("214", "assetts.atlas");
        Image image214= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion214);
        image214.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare214 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 214,bitMaskableTileSet, image214);
        table.add(tileSquare214.getActor());
        
        TextureRegion empty= gameAssets.getAtlasRegionByName("empty", "assetts.atlas");
        Image emptyImage= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), empty);
        emptyImage.setSize(tileWidth, tileHeight);
        table.add(emptyImage);
        AtlasRegion textureRegion107=gameAssets.getAtlasRegionByName("107", "assetts.atlas");
        Image image107= new Image(textureRegion107);
        image107.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare107 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 107,bitMaskableTileSet, image107);
        table.add(tileSquare107.getActor());
        table.row();

        AtlasRegion textureRegion22=gameAssets.getAtlasRegionByName("22", "assetts.atlas");
        Image image22= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion22);
        image22.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare22 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 22,bitMaskableTileSet, image22);
        table.add(tileSquare22.getActor());

        AtlasRegion textureRegion31=gameAssets.getAtlasRegionByName("31", "assetts.atlas");
        Image image31= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion31);
        image31.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare31 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 31,bitMaskableTileSet, image31);
        table.add(tileSquare31.getActor());
        
        AtlasRegion textureRegion11=gameAssets.getAtlasRegionByName("11", "assetts.atlas");
        Image image11= new TextureRegionSettableImage( mapEditScreen.getClipBoard(), textureRegion11);
        image11.setSize(tileWidth, tileHeight);
        TileSetTextureRegionDraggableTarget tileSquare11 = new TileSetTextureRegionDraggableTarget(mapEditScreen.getClipBoard(), 11,bitMaskableTileSet, image11);
        table.add(tileSquare11.getActor());



        table.pack();
        table.validate();
        return table;




    }
}
