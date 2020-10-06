package com.jessematty.black.tower.Generators;

import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;

import java.util.ArrayList;

public class MessageGenerator {


    GameAssets assetts;
    GameMap map;
    ItemGenerator itemGenerator;

    ArrayList<String> string = new ArrayList<String>();


    public MessageGenerator(GameAssets assetts, GameMap map, ItemGenerator itemGenerator) {
        this.assetts = assetts;
        this.map = map;
        this.itemGenerator = itemGenerator;
    }

    public String makeRandomName() {

        String name="";
        return name;


    }
}
