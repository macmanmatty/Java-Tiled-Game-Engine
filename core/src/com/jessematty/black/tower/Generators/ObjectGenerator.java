package com.jessematty.black.tower.Generators;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Generators.MapGenerators.MapGenerator;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;

public class ObjectGenerator {



    PlantGenerator plantGenerator;
    ArmorGenerator armorGenerator;
    ItemGenerator itemGenerator;
    WeaponGenerator weaponGenerator;
    MessageGenerator messageGenerator;
    MapGenerator  mapGenerator;
    GameAssets assetts;
    GameMap map;






    public ObjectGenerator( GameMap map, GameAssets assetts) {

        this.map = map;
        this.assetts = assetts;
        this.mapGenerator=mapGenerator;






    }

    public Entity makeGrass(int number){
        return null;


    }






}
