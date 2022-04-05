package com.jessematty.black.tower.GameBaseClasses.Serialization.World;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureAtlasPacker;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorldWriter {
private boolean packAssets;
private int packWidth;
private int packHeight;
private com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureAtlasPacker textureAtlasPacker= new TextureAtlasPacker();
    private final  GameAssets gameAssets;

    public WorldWriter(GameAssets assets) {
        this.gameAssets=assets;

    }

    public void saveWorld(World world, String path) throws IOException {

        File file=FileUtilities.createFile(path, world.getName()+".bin");
            Output output= new Output(new FileOutputStream(file));
            gameAssets.getKryo().writeClassAndObject(output, world);
            output.close();
    }

    /**
     * saves a world object using kryo and it's associated texture atlas using the texture atlas packer class
     * @param world the world to save
     * @param path the directory to save the game to
     * @param packWidth the max width  to use for the images with texture atlas should be  a power of two
     * @param packHeight the max height  to use for the images with texture atlas should be  a power of two
     * @throws IOException file loading exception
     */
    public void saveWorldWithAssets(World world,  String path, int packWidth,  int packHeight) throws IOException {
        String atlasName=world.getName()+"Assets";
        String fullAssetsPath=path+FileUtilities.getFileSeparator()+atlasName+".atlas";
        world.getWorldSettings().getSettings().put("textureAtlasPath", fullAssetsPath);
        packAssets( path,  atlasName,fullAssetsPath, world, packWidth, packHeight);
           saveWorld(world, path);
    }

    /**
     * packs the  assets using the texture atlas packer class
     * @param path the path to save the atlas to
     * @param atlasName the name of the atlas
     *
     * @param world the world object to save
     *   * @param packWidth the max width  to use for the images with texture atlas should be  a power of two
     *   * @param packHeight the max height  to use for the images with texture atlas should be  a power of two
     * @throws IOException
     */
    private void packAssets(String path, String atlasName, String fullAtlasPath, World world, int packWidth, int packHeight) throws IOException {
        textureAtlasPacker.packAtlas(path, atlasName, world.getWorldTextureAtlas(), packWidth  ,packHeight, 2);
        setMapAtlasName(fullAtlasPath,world.getWorldMap() );
    }

    /**
     * sets  worlds maps the tiled map
     * @param atlasName the name of the texture atlas
     * @param maps the name of the map
     */
    private  void setMapAtlasName(String atlasName,  ObjectMap< String , LandMap> maps){
        Values<LandMap> mapValues =maps.values();
            while(mapValues.hasNext){
                LandMap map=mapValues.next();
                map.getTiledMap().getProperties().put("atlasName", atlasName);
            }
    }
    /**
     * sets  land maps the tiled map
     * @param atlasName the name of the texture atlas
     * @param buildings the array of buildings on the map
     */
    private void setBuildingAtlasName(String atlasName, Array<Building> buildings ){

        int size=buildings.size;
        for(int count=0; count<size; count++){
            buildings.get(count).getTiledMap().getProperties().put("atlasName", atlasName);
        }

    }

    public boolean isPackAssets() {
        return packAssets;
    }

    public void setPackAssets(boolean packAssets) {
        this.packAssets = packAssets;
    }

    public int getPackWidth() {
        return packWidth;
    }

    public void setPackWidth(int packWidth) {
        this.packWidth = packWidth;
    }

    public int getPackHeight() {
        return packHeight;
    }

    public void setPackHeight(int packHeight) {
        this.packHeight = packHeight;
    }
}
