package com.jessematty.black.tower.Generators.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationGenerator;
/**
 * class that contains a map of all component generators
 * and loaded components
 */
public class ComponentGenerators {
    /**
     *  the map of component generators
     *  key = the component class to generate
     *  value= the component generator object that generates the components
     *
     */
  private final   ObjectMap<String, ComponentGenerator<? extends Component>> componentGenerators=new ObjectMap<>();
    /**
     * the map of loaded components
     * key= the file to the component
     * value= the component
     */
  private ObjectMap<String, Component> loadedComponents= new ObjectMap<>();
    public ComponentGenerators(GameAssets gameAssets) {
        componentGenerators.put(AnimatableComponent.class.toString(),  new AnimationGenerator(gameAssets));
    }
    public  ObjectMap<String,  ComponentGenerator<? extends Component>> getComponentGenerators() {
        return componentGenerators;
    }
    public ObjectMap<String, Component> getLoadedComponents() {
        return loadedComponents;
    }
}
