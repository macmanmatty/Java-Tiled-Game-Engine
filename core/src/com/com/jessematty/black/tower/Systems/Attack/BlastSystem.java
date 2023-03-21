package com.jessematty.black.tower.Systems.Attack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Explode;
import com.jessematty.black.tower.Components.Other.Explodable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Stats.ChangeStats;

public class BlastSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private  ComponentMapper<Explodable> explodableComponentMapper;
    private ComponentMapper<NumericStats> numericStatComponentMapper;



    public BlastSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        positionComponentMapper =GameComponentMapper.getPositionComponentMapper();
        explodableComponentMapper = GameComponentMapper.getBlastableComponentMapper();
        numericStatComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();


    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Explode.class, Explodable.class, PositionComponent.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity explosion= entities.get(count);

            PositionComponent position = positionComponentMapper.get(explosion);
            Explodable explodable = explodableComponentMapper.get(explosion);
            GameMap map=getWorld().getMap(position.getMapId());

            Circle circle= new Circle();
            circle.radius= explodable.getRadius();
            Array<Entity>  blastedEntities= MapUtilities.getAllEntities(getWorld(), map, position.getLocationX(), position.getLocationY(), circle);

            int numberOfEntities=blastedEntities.size;
            for (int count2=0; count2<numberOfEntities; count2++){

                ChangeStats.changeStats( blastedEntities.get(count), explosion, explodable.getBlastActionName(), false, true, true);


            }
            explosion.add(new RemoveFromEngine());






        }

    }

}
