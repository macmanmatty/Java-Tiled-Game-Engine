package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Teleport;
import com.jessematty.black.tower.Components.Tiles.OnEnterTileComponent;
import com.jessematty.black.tower.Components.Tiles.Tile;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;

public class OnTileEnterSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Tile> tileComponentMapper;
    private ComponentMapper<OnEnterTileComponent> onEnterTileComponentComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentComponentMapper;


    protected  boolean eightDirections=true;
    int counter=0;

    public OnTileEnterSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        tileComponentMapper=getGameComponentMapper().getTileComponentMapper();
        positionComponentComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        onEnterTileComponentComponentMapper=getGameComponentMapper().getOnEnterTileComponentComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( Tile.class, Movable.class, PositionComponent.class, Action.class, OnEnterTileComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity tile=entities.get(count);
                Tile tileComponent = tileComponentMapper.get(tile);
                if(tileComponent.isEntered()){
                    OnEnterTileComponent onEnterTileComponent=onEnterTileComponentComponentMapper.get(tile);
                    onEnterTileComponent.getOnEnterTile().onEnterTile(tile, tileComponent.getLastEntity(), getDraw());

                }






        }
    }



}