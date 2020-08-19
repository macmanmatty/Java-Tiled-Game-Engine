package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Teleport;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;

public class TileTeleportSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Tile> tiles=ComponentMapper.getFor(Tile.class);
    private ComponentMapper<Teleport> transports=ComponentMapper.getFor(Teleport.class);
    private ComponentMapper<Position> positions=ComponentMapper.getFor(Position.class);

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
        entities=getEngine().getEntitiesFor(Family.all(Movable.class, Position.class, Action.class,Teleport.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
                Tile tile = tiles.get(entity);

                Teleport teleportCompoonent = transports.get(entity);

                Array<Entity> entities=tile.getEntities();
                int size2=entities.size;
            int transportTileX= teleportCompoonent.getTileTransportLocationX();
            int transportTileY= teleportCompoonent.getTileTransportLocationY();
            int transportMapX= teleportCompoonent.getMapTransportLocationX();
            int transportMapY= teleportCompoonent.getMapTransportLocationY();
            GameMap map=getDraw().getWorld().getMap(transportMapX, transportMapY);
            Vector2  screenLocations=map.getScreenCoordinatesFromTileCoordinates(transportTileX, transportTileY);




            for(int count2=0; count2<size2; count2++){
                    Entity tileEntity=entities.get(count2);
                    Position position=positions.get(tileEntity);
                    position.setScreenLocationX(screenLocations.x);
                    position.setScreenLocationY(screenLocations.y);
                map.removeEntity(tileEntity);
                    map.addEntity(tileEntity);

                }




        }
    }



}