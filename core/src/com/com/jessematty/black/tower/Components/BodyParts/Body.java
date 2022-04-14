package com.jessematty.black.tower.Components.BodyParts;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.HashMap;

public class Body implements Component {

   private  HashMap<String, String> bodyParts = new HashMap<String, String>();

    public Body() {
    }


    public HashMap<String, String> getBodyParts() {
        return bodyParts;
    }




}