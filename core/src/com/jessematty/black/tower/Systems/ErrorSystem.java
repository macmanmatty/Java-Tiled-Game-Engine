package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;

@Transient
// system for displaying entity error messages
public class ErrorSystem extends GameEntitySystem {


    private ComponentMapper<ErrorComponent> errorComponentComponentMapper;

    ImmutableArray<Entity> entities;

    public ErrorSystem(MapDraw draw) {
        super(draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        errorComponentComponentMapper=getGameComponentMapper().getErrorComponentComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( ErrorComponent.class).get());
        int size=entities.size();


                for(int count=0; count<size; count++){

                    Entity entity=entities.get(count);
                    ErrorComponent errorComponent= errorComponentComponentMapper.get(entity);
                    //create error option pane
                    OptionPane optionPane= new OptionPane(getDraw().getCurrentMap().getSkin(), errorComponent.getTitle(), errorComponent.getErrorMessage(), errorComponent.getButtonText());
                   // add window to stage
                    getDraw().addWindow(optionPane, ScreenPosition.CENTER);
                    // remove error
                    entity.remove(ErrorComponent.class);

                }

    }


}
