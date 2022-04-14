package com.jessematty.black.tower.AI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionMarkerComponent;
import com.jessematty.black.tower.Maps.GameMap;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Brain {

    private Queue<ActionMarkerComponent> actionMarkerComponents=new LinkedList<ActionMarkerComponent>();

    public ActionMarkerComponent getNextAction(){
        return actionMarkerComponents.remove();


    }
    public void  addNewAction( ActionMarkerComponent actionMarkerComponent){
        actionMarkerComponents.add(actionMarkerComponent);



    }


    public Queue<ActionMarkerComponent> getActionMarkerComponents() {
        return actionMarkerComponents;
    }



    public abstract  void act(GameMap map);
    public abstract  void see(Array<Entity> entities);
    public abstract  void hear(Array<Entity> entities);






}
