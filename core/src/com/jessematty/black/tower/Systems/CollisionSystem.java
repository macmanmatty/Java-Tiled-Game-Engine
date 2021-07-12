package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.ID;

import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;

public class CollisionSystem extends GameEntitySystem { // system that detects for collision
    private ImmutableArray<Entity> entities;
    private ComponentMapper<MovableComponent> movables;
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<PhysicalObjectComponent> objects;
    private ComponentMapper<Name> nameComponentMapper;
    private ComponentMapper<ID> idComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    public CollisionSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        movables= GameComponentMapper.getMovableComponentMapper();
        positions=GameComponentMapper.getPositionComponentMapper();
        objects=GameComponentMapper.getPhysicalObjectComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        actionComponentMapper=GameComponentMapper.getActionComponentMapper();
        nameComponentMapper=GameComponentMapper.getNameComponentMapper();

    }
    public void update(float deltaTime) { // collision detection  for a move object
        entities=getEngine().getEntitiesFor(Family.all(MovableComponent.class,   PositionComponent.class, PhysicalObjectComponent.class).get());
        int size=entities.size();
        for(int counter=0; counter<size; counter++){
        Entity entity=entities.get(counter);
        MovableComponent movableComponent = movables.get(entity);
        if(movableComponent.isMoved()==false){ // didn't move nothing to check
            continue;
        }
            PositionComponent position=positions.get(entity);
        PhysicalObjectComponent physicalObject= objects.get(entity);
        float screenLocationX=position.getLocationX();
        float screenLocationY=position.getLocationY();
        Vector3 speed= movableComponent.getVelocity();
        int mapX=position.getMapWorldLocationX();
        int mayY=position.getMapWorldLocationY();

            Rectangle rectangle=position.getBoundsBoundingRectangle();
            if(rectangle.x==0 || rectangle.y==0){
                continue;
            }
            GameMap map=getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());

            int tileSizeX=map.getTileWidth();
            int tileSizeY=map.getTileHeight();

            int checkDistanceX = (int)((Math.abs(speed.x)+rectangle.width)/tileSizeX)+1; // how far to check in the xAxis;
             int checkDistanceY = (int)(( Math.abs(speed.y)+rectangle.height)/tileSizeY)+1; // how far to check in the yAxis;

            // get all owned entities of the movable
        Array<Entity> occupants= MapUtilities.getAllEntitiesAndTiles(map, screenLocationX, screenLocationY, checkDistanceX, checkDistanceY);
                int size2 = occupants.size;
                for (int count = 0; count < size2; count++) {
                    Entity occupant = occupants.get(count);


                    MovableComponent occupantMovableComponent = movables.get(occupant);
                    PositionComponent occupantPosition = positions.get(occupant);
                    if(occupantPosition.isHasBounds()==false){
                        continue;
                    }
                    PhysicalObjectComponent occupantBody = objects.get(occupant);
                   boolean connected= EntityUtilities.isEntityConnected(entity, occupant, getWorld());

                    if (connected==true) { // check to make sure the object isn't colliding into itself

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
                         if(occupantBody.getEntitySolidity()==1) {
                             getEngine().addSystem(new ElasticCollision(getDraw(), map, entity, movableComponent, physicalObject, position, occupant, occupantMovableComponent, occupantBody, occupantPosition));
                         }
                         else{

                             getEngine().addSystem(new InelasticCollision(getDraw(), map, entity, movableComponent, physicalObject, position, occupant, occupantMovableComponent, occupantBody, occupantPosition));

                         }

                           ChangeStats.changeStats(entity, occupant, "collide",  true, true, true);
                           Action entityAction=actionComponentMapper.get(entity);
                         Action occupantAction=actionComponentMapper.get(occupant);
                         entityAction.setStat("collide into "+nameComponentMapper.get(occupant).getStat());
                         occupantAction.setStat("collide with "+nameComponentMapper.get(entity).getStat());
                         return;
                        }
                    }


        }
        }
        return;
    }
    public  boolean heightCheck(PositionComponent occupantBounds, PositionComponent occupant2Bounds) {
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
