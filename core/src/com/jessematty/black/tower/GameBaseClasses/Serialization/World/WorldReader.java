package com.jessematty.black.tower.GameBaseClasses.Serialization.World;

import com.esotericsoftware.kryo.io.Input;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.World;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class WorldReader {

    private final GameAssets gameAssets;


    public WorldReader(GameAssets assets) {
        this.gameAssets=assets;
    }

    public World loadWorld(String path) { // loads  game  from the maps json and tmx files ina given path

        Input input= null;
        try {
            input = new Input( new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        World world= (World) gameAssets.getKryo().readClassAndObject(input);

        return  world;
    }


}

