package com.jessematty.black.tower.GameBaseClasses.Loaders.World;

import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.Loaders.EntityLoaders.EntityLoader;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.World;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorldWriter {
private boolean packAssets;
private int packWidth;
private int packHeight;
    private final EntityLoader entityLoader;
    private final  GameAssets gameAssets;

    public WorldWriter(GameAssets assets) {
        entityLoader= new EntityLoader(assets);
        this.gameAssets=assets;

    }

    public void saveWorld(World world, String path) {
        try {
        File file=FileUtilities.createFile(path, "game.bin");
            Output output= new Output(new FileOutputStream(file));
            gameAssets.getKryo().writeClassAndObject(output, world);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
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
