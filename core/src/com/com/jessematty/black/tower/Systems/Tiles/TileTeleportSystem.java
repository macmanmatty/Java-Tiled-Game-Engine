package com.jessematty.black.tower.Systems.Tiles;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Attacks.Teleport;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Tiles.TileComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class TileTeleportSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<TileComponent> tileComponentMapper =ComponentMapper.getFor(TileComponent.class);
    private ComponentMapper<Teleport> teleportComponentMapper =ComponentMapper.getFor(Teleport.class);
    private ComponentMapper<PositionComponent> positionComponentComponentMapper =ComponentMapper.getFor(PositionComponent.class);

    protected  boolean eightDirections=true;
    int counter=0;

    public TileTeleportSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(MovableComponent.class, PositionComponent.class, ActionComponent.class,Teleport.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
                TileComponent tileComponent = tileComponentMapper.get(entity);

                Teleport teleportComponent = teleportComponentMapper.get(entity);
                Array<Entity> entities= tileComponent.getEntities();
            int transportTileX= teleportComponent.getTileTransportLocationX();
            int transportTileY= teleportComponent.getTileTransportLocationY();






        }
    }



}