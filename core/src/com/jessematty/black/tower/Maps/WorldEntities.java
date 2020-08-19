package com.jessematty.black.tower.Maps;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.IEntityGenerator;

public class WorldEntities {


    private IEntityGenerator entityGenerator;

    private OrderedMap<String, OrderedMap<String, Component>> componentsInWorld= new OrderedMap<>();// components in world

    public void addComponent( String name , Component component){
        componentsInWorld.get(component.getClass().getName()).put(name, component);

    }

    public Component getComponent(String name,  Class<? extends Component> componentClass){

        return componentsInWorld.get(componentClass.getName()).get(name);

    }

}
