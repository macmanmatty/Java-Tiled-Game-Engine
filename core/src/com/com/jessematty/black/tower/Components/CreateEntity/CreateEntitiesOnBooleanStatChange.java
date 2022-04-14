package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class CreateEntitiesOnBooleanStatChange implements Component {

   private  Array<CreateEntityOnBooleanStatChange> entitiesToCreate = new Array<>();

    public Array<CreateEntityOnBooleanStatChange> getEntitiesToCreate() {
        return entitiesToCreate;
    }


}
