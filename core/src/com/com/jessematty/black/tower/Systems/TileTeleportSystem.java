package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Teleport;
import com.jessematty.black.tower.Components.Tiles.Tile;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;

public class TileTeleportSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Tile> tileComponentMapper =ComponentMapper.getFor(Tile.class);
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
        entities=getEngine().getEntitiesFor(Family.all(MovableComponent.class, PositionComponent.class, Action.class,Teleport.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
                Tile tile = tileComponentMapper.get(entity);

                Teleport teleportComponent = teleportComponentMapper.get(entity);
                Array<Entity> entities=tile.getEntities();
            int transportTileX= teleportComponent.getTileTransportLocationX();
            int transportTileY= teleportComponent.getTileTransportLocationY();






        }
    }



}