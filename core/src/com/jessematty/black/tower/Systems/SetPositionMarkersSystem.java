package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Markers.OnCurrentMap;
import com.jessematty.black.tower.Components.Markers.VisibleOnScreen;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapUtilities;

public class SetPositionMarkersSystem extends GameEntitySystem {// sets the a flag component for visible onscreen  and adds or removes it based on the players position.
    ImmutableArray<Entity>  entities;
    private ComponentMapper<Position> positions;
    private ComponentMapper<VisibleOnScreen> visibleOnScreenComponentMapper;
    public SetPositionMarkersSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        positions=getGameComponentMapper().getPositionComponentMapper();
        visibleOnScreenComponentMapper=getGameComponentMapper().getVisibleOnScreenComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(Position.class).get());
        int size=entities.size();
        if(getDraw().getPlayer().getMovable().isMoved()==false){// player didn't move no need to calculate anything
            return;

        }


        for(int count=0;  count<size; count++ ) {
            Entity entity=entities.get(count);
            Position position=positions.get(entity);


            if(MapUtilities.getEntityMap(getWorld(), position).equals(getDraw().getCurrentMap())){
                entity.add(new OnCurrentMap());
            }
            else{
                entity.remove(OnCurrentMap.class);

            }

            Position playerPosition=getDraw().getPlayer().getPosition();
            double distance= MathUtilities.calculateMinScreenDistance(playerPosition, position);
            float maxScreenSize=Math.max(getDraw().getViewPortWidth(), getDraw().getViewPortHeight())+32;


            if(distance>maxScreenSize){
                continue;
            }


            GameMap currentMap=getDraw().getCurrentMap();
            if(getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY())!=currentMap){
                continue;
            }
            Rectangle rectangle=position.getBoundsBoundingRectangle();
            boolean onScreen=onScreen(rectangle);
            if(onScreen){
                    entity.add(new VisibleOnScreen());
                
            }
            
            else{
                
                entity.remove(VisibleOnScreen.class);
            }
        }
    }
    public boolean onScreen(Rectangle rectangle){ // checks if the bounds of a rectangle are on screen by checking each corner point
        Camera camera=getDraw().getCamera();
        float rectangleX=rectangle.x;
        float rectangleY=rectangle.y;
        float height=rectangle.height;
        float width=rectangle.width;
         boolean  pointOneOnScreen=camera.frustum.pointInFrustum(rectangleX, rectangleY, 0); // check if point one of bounds is on screen
         if(pointOneOnScreen==true){
             return true;
         }
        boolean  pointTwoOnScreen=camera.frustum.pointInFrustum(rectangleX+width, rectangleY, 0);
         if(pointTwoOnScreen==true){
             return  true;
         }
        boolean  pointThreeOnScreen=camera.frustum.pointInFrustum(rectangleX, rectangleY+height, 0);
        if(pointThreeOnScreen==true){
            return true;
        }
        boolean  pointFourOnScreen=camera.frustum.pointInFrustum(rectangleX+width, rectangleY+height, 0);
        if(pointFourOnScreen==true){
            return true;
        }
        return false;
    }



    // checks to see if  an entity is on the current map



}
