package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class CreateEntitiesOnTime implements Component {

   private  Array<CreateEntityOnTime> entitiesToCreate = new Array<>();


    public Array<CreateEntityOnTime> getEntitiesToCreate() {
        return entitiesToCreate;
    }


}
