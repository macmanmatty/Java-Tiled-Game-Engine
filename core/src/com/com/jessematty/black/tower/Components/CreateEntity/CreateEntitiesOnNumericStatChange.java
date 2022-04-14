package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class CreateEntitiesOnNumericStatChange implements Component {

   private  Array<CreateEntityOnNumericStatChange> entitiesToCreate = new Array<>();

    public Array<CreateEntityOnNumericStatChange> getEntitiesToCreate() {
        return entitiesToCreate;
    }
}
