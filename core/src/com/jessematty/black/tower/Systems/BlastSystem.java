package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Explode;
import com.jessematty.black.tower.Components.Explodable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.RemoveFromEngine;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapUtilities;

public class BlastSystem extends GameEntitySystem{
    private ComponentMapper<Position> positionComponentMapper;
    private  ComponentMapper<Explodable> blastableComponentMapper;



    public BlastSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        positionComponentMapper =getGameComponentMapper().getPositionComponentMapper();
        blastableComponentMapper=getGameComponentMapper().getBlastableComponentMapper();


    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Explode.class, Explodable.class, Position.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity explosion= entities.get(count);

            Position position = positionComponentMapper.get(explosion);
            Explodable explodable =blastableComponentMapper.get(explosion);
            GameMap map=getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());

            Circle circle= new Circle();
            circle.radius= explodable.getRadius();
            Array<Entity>  blastedEntities= MapUtilities.getAllEntities(getWorld(), map, position.getScreenLocationX(), position.getScreenLocationY(), circle);

            int numberOfEntities=blastedEntities.size;
            for (int count2=0; count2<numberOfEntities; count2++){

                ChangeStats.changeStats( blastedEntities.get(count), explosion, explodable.getBlastActionName(), false, true, true);


            }
            explosion.add(new RemoveFromEngine());






        }

    }

}
