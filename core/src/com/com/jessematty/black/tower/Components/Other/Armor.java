package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;

import javax.swing.text.html.parser.Entity;

public class Armor implements Component {


    private HashMap<Entity, Entity> armor= new HashMap<Entity, Entity>();



    public HashMap<Entity, Entity> getArmor() {
        return armor;
    }

    public void setArmor(HashMap<Entity, Entity> armor) {
        this.armor = armor;
    }
}

