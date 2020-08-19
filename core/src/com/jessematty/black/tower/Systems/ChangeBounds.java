package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
@Transient

public class ChangeBounds extends GameEntitySystem {

    private ComponentMapper<Position> positions;


    public ChangeBounds(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {


        positions=getGameComponentMapper().getPositionComponentMapper();
    }



    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(Movable.class, Position.class).get());


        int size=entities.size();
        for(int count=0;  count<size; count++ ) {
            Entity entity=entities.get(count);
            Position position=positions.get(entity);


            Polygon bounds= position.getDirectionalBounds().get(position.getDirection());
            if(bounds!=null) {
                position.setBounds(bounds);

            }



        }

    }


}
