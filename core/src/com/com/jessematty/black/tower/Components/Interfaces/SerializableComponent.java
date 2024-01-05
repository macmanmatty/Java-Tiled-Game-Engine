package com.jessematty.black.tower.Components.Interfaces;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

/**
 * interface for a component that needs to implement  additional logic
 * on being saved and loaded from the game
 */
public  interface SerializableComponent extends Component {
    /**
        entity component deserialization method called when game reloading happens
     @see com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.EntityKryoSerializer
      * @param entity the entity object to deserialize
     * @param assets the game assets object
     */
     public abstract  void  deSerialize( Entity entity, GameAssets assets);
    /**
     entity component deserialization method called when game saving happens
      @see com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.EntityKryoSerializer

     * @param entity the entity object to serialize
     */
    public abstract  void  serialize(Entity entity );




}
