package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.ID;

import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapUtilities;

public class CollisionSystem extends GameEntitySystem { // system that detects for collision
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Movable> movables;
    private ComponentMapper<Position> positions;
    private ComponentMapper<PhysicalObject> objects;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<ID> idComponentMapper;
    public CollisionSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        movables=getGameComponentMapper().getMovableComponentMapper();
        positions=getGameComponentMapper().getPositionComponentMapper();
        objects=getGameComponentMapper().getPhysicalObjectComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
    }
    public void update(float deltaTime) { // collision detection  for a move object
        entities=getEngine().getEntitiesFor(Family.all(Movable.class, MovingOnGround.class,    Position.class, PhysicalObject.class).get());
        int size=entities.size();
        for(int counter=0; counter<size; counter++){
        Entity entity=entities.get(counter);
        Movable movable = movables.get(entity);
        if(movable.isMoved()==false){ // didn't move nothing to check
            continue;
        }
            Position position=positions.get(entity);
        PhysicalObject physicalObject= objects.get(entity);
        float screenLocationX=position.getScreenLocationX();
        float screenLocationY=position.getScreenLocationY();
        Vector3 speed= movable.getVelocity();
        int mapX=position.getMapWorldLocationX();
        int mayY=position.getMapWorldLocationY();
        int tileSizeX=getWorld().getMap(mapX, mayY).getTileSizeX();
            int tileSizeY=getWorld().getMap(mapX, mayY).getTileSizeY();

            int checkDistanceX = (int)(speed.x/tileSizeX)+tileSizeX; // how far to check in the xAxis;
             int checkDistanceY = (int)( speed.y/tileSizeY)+tileSizeY; // how far to check in the yAxis;

            // get all owned entities of the movable
            Array<String> ownedEntityIds=EntityUtilities.getAllOwnedEntitiesIDs(entity, getWorld());
        GameMap map=getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());
        Array<Entity> occupants= MapUtilities.getAllEntities(map, screenLocationX, screenLocationY, checkDistanceX, checkDistanceY);
                int size2 = occupants.size;
                for (int count = 0; count < size2; count++) {
                    Entity occupant = occupants.get(count);
                    String id=idComponentMapper.get(occupant).getId();

                    if(InList.isInList(id, ownedEntityIds)){
                        continue;
                    }
                    Movable occupantMovable = movables.get(occupant);
                    Position occupantPosition = positions.get(occupant);
                    PhysicalObject occupantBody = objects.get(occupant);
                    if ((entity.equals(occupant))) { // check to make sure the object isn't colliding into itself
                        continue;
                    }
                    if ( occupantPosition != null  && physicalObject!=null ) { // collide with a object
                        Polygon occupantBounds1 = occupantPosition.getBounds();
                        Polygon occupantBounds2 = position.getBounds();
                       boolean collide=heightCheck( position,  occupantPosition); // check if the two entities  will collide in the z axis
                        if(collide==true) {
                            collide = Intersector.overlapConvexPolygons(occupantBounds1, occupantBounds2);
                    }
                     if (collide == true) {
                            getEngine().addSystem(new ElasticCollision(   getDraw(), map, entity, movable, physicalObject, position,  occupant, occupantMovable, occupantBody, occupantPosition));
                         ChangeStats.changeStats(entity, occupant, "touch",  true, true, true);
                         return;
                        }
                    }


        }
        }
        return;
    }
    public  boolean heightCheck(Position occupantBounds, Position occupant2Bounds) {
         float maxOccupantHeight=occupantBounds.getHeight()+occupantBounds.getHeightFromGround();
        float minOccupantHeight=occupantBounds.getHeightFromGround();
        float minHeight=occupant2Bounds.getHeightFromGround();
        float maxHeight=occupantBounds.getHeight()+occupant2Bounds.getHeightFromGround();
       if(minHeight>=minOccupantHeight && minHeight<maxHeight){
           return true;
       }
       if(maxHeight<=maxOccupantHeight && maxHeight>minOccupantHeight){
           return true;
       }
       return false;
    }
/*
    static   private boolean collisionCheck(Mover occupant1, Collidable occupant2) {// takes one movable  and one unmovable object and collides them
        Vector2 velocityO1=occupant1.getVelocity();
        Polygon occupantBounds1 = occupant1.getCollisionBounds();
        Polygon occupantBounds2 = occupant2.getCollisionBounds();
        boolean collide = Intersector.overlapConvexPolygons(occupantBounds2, occupantBounds1);
        if (collide == true) {
            occupant2.collide(occupant1);
            occupant1.collide(occupant2);
            if(occupant2.isRigidBody()){
                double mass2=occupant2.getMass();
                double mass1=occupant1.getMass();
                double finalVelocityX1=(-velocityO1.x);
                double finalVelocityY1=(-velocityO1.y);
                occupant1.move((float)finalVelocityX1, (float)finalVelocityY1);
            }
        }
        return collide;
    }
*/
}
