package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ShowInfo;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.InfoWindow;

public class InfoSystem extends GameEntitySystem {


    private ImmutableArray<Entity> entities;
    private ComponentMapper<Name> nameComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;


    public InfoSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {

        nameComponentMapper= getGameComponentMapper().getNameComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();




    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(ShowInfo.class).get());

        int size=entities.size();


                for(int count=0; count<size; count++){

                    Entity entity=entities.get(count);
                    entity.remove(ShowInfo.class);
                    String name= nameComponentMapper.get(entity).getStat();
                    PositionComponent position=positionComponentMapper.get(entity);
                    getDraw().addWindow(new InfoWindow("Info For "+name, getDraw(), entity),position.getLocationX(), position.getLocationY()  );

                }


    }


}
