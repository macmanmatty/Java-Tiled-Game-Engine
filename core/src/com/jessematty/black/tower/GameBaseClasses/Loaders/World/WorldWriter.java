package com.jessematty.black.tower.GameBaseClasses.Loaders.World;

import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.Loaders.EntityLoaders.EntityLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.MapLoaders.MapLoader;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class WorldWriter {
private String fileSeperator;
private boolean packAssetts;
private int packWidth;
private int packHeight;
    private final MapLoader mapLoader;
    private final EntityLoader entityLoader;
    private final  GameAssets gameAssets;

    public WorldWriter(GameAssets assets) {
        mapLoader= new MapLoader(assets);
        entityLoader= new EntityLoader(assets);
        this.gameAssets=assets;

    }

    public void saveWorld(World world, String path) {

        File file=FileUtilities.createFile(path, "game.bin");

        try {
            Output output= new Output(new FileOutputStream(file));
            gameAssets.getKryo().writeClassAndObject(output, world);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public boolean isPackAssetts() {
        return packAssetts;
    }

    public void setPackAssetts(boolean packAssetts) {
        this.packAssetts = packAssetts;
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
