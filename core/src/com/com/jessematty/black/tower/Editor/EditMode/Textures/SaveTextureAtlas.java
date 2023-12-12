package com.jessematty.black.tower.Editor.EditMode.Textures;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO.ImageFormat;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO.SaveParameters;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.Utilities.AnimationUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.Maps.World;

import java.io.IOException;
public class SaveTextureAtlas {
    private TextureAtlas textureAtlas;
    private final  String fileSeparator =System.getProperty("file.separator");
    private ComponentMapper<DrawableComponent> drawableComponentMapper=ComponentMapper.getFor(DrawableComponent.class);
    private ComponentMapper<AnimatableComponent> animatableComponentMapper=ComponentMapper.getFor(AnimatableComponent.class);
    public void createWorldTextureAtlas(World world){
        textureAtlas= new TextureAtlas();
    }
    private void getEntityTextures(Values<Entity> entities){
        while(entities.hasNext()){
             Entity entity=entities.next();
             AnimatableComponent animatable=animatableComponentMapper.get(entity);
             if(animatable!=null) {
                 addFrames(AnimationUtilities.getAnimationRegions(animatable));
             }
             else{
                 DrawableComponent drawableComponent =drawableComponentMapper.get(entity);
                 if(drawableComponent !=null){
                     AtlasRegion region= drawableComponent.getTextureRegion();
                     addTextureRegion(region.name, region);
                 }
             }
        }
    }

    private void addFrames(Array<AtlasNamedAtlasRegion> frames) {
        int size=frames.size;
        for(int count=0; count<size; count++){
            AtlasRegion atlasRegion=frames.get(count);
            addTextureRegion(atlasRegion.name, atlasRegion);
        }
    }
    private void addFrames(AtlasStaticTiledMapTile[] frames) {
        int size=frames.length;
        for(int count=0; count<size; count++){
            TextureRegion textureRegion=frames[count].getTextureRegion();
            String name=frames[count].getNames()[0];
            addTextureRegion(name, textureRegion);
        }
    }
    private void getTileTextures( int x, int y, TiledMap tiledMap) {
        int size=tiledMap.getLayers().size();
        for(int count=0; count<size; count++)
        for (int countx = 0; countx < x; countx++) {
            for (int county = 0; county < y; county++) {
                 TiledMapTileLayer layer= (TiledMapTileLayer) tiledMap.getLayers().get(count);
                 Cell cell=layer.getCell(countx, county);
                 if(cell.getTile() instanceof  AtlasStaticTiledMapTile ) {
                     AtlasStaticTiledMapTile tiledMapTile = (AtlasStaticTiledMapTile) cell.getTile();
                     TextureRegion region = tiledMapTile.getTextureRegion();
                     addTextureRegion(tiledMapTile.getNames()[0], region);
                 }
                 else  if(cell.getTile() instanceof AtlasAnimatedTiledMapTile) {
                     AtlasAnimatedTiledMapTile tiledMapTile = (AtlasAnimatedTiledMapTile) cell.getTile();
                     AtlasStaticTiledMapTile []  tiles = tiledMapTile.getFrameTiles();
                     addFrames(tiles);
                 }
                 }
        }
    }
    public void saveTextureAtlas(TextureAtlas atlas, String directory,  int width, int height) {
        PixmapPacker pixmapPacker= new PixmapPacker( width, height, Format.RGBA8888, 2, true);
        Array<AtlasRegion> regions=atlas.getRegions();
        int size=regions.size;
        for(int count=0; count<size; count++){
            AtlasRegion region=regions.get(count);
            Pixmap pixmap=getPixmap(region);
            pixmapPacker.pack(region.name, pixmap);
        }
        SaveParameters saveParameters=  new PixmapPackerIO.SaveParameters();
        saveParameters.format= ImageFormat.PNG;
        saveParameters.useIndexes=true;
        try {
            new PixmapPackerIO().save(Gdx.files.external(directory),pixmapPacker,  saveParameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Pixmap getPixmap(TextureRegion textureRegion){
    TextureData textureData = textureRegion.getTexture().getTextureData();
    if (!textureData.isPrepared()) {
        textureData.prepare();
    }
    Pixmap pixmap = new Pixmap(
            textureRegion.getRegionWidth(),
            textureRegion.getRegionHeight(),
            textureData.getFormat()
    );
    pixmap.drawPixmap(
            textureData.consumePixmap(), // The other Pixmap
            0, // The target x-coordinate (top left corner)
            0, // The target y-coordinate (top left corner)
            textureRegion.getRegionX(), // The source x-coordinate (top left corner)
            textureRegion.getRegionY(), // The source y-coordinate (top left corner)
            textureRegion.getRegionWidth(), // The width of the area from the other Pixmap in pixels
            textureRegion.getRegionHeight() // The height of the area from the other Pixmap in pixels
    );
           return pixmap;
    }
    private  void addTextureRegion( String name,TextureRegion region){
        if(!InList.isInList(textureAtlas, region)) {
            textureAtlas.addRegion(name, region);
        }
}
}