package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.RandomlyCreateAndPlaceEntity;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Copy.CopyObject;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class RandomlyCreateAndPlaceEntitySystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<RandomlyCreateAndPlaceEntity> randomlyCreateAndPlaceEntityComponentMapper;
    private ImmutableArray<Entity> entities;
    public RandomlyCreateAndPlaceEntitySystem(int priority, MapDraw draw) {
        super(priority, draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
        randomlyCreateAndPlaceEntityComponentMapper =GameComponentMapper.getRandomlyCreateAndPlaceEntityComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(PositionComponent.class, RandomlyCreateAndPlaceEntity.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            Entity entityToPlace= GameAssets.getJsonLoader().copyObject(entity, Entity.class);
            PositionComponent entityPosition = positionComponentMapper.get(entityToPlace);
            RandomlyCreateAndPlaceEntity randomlyCreateAndPlaceEntitySystem = randomlyCreateAndPlaceEntityComponentMapper.get(entity);
            GameTime gameTime=getDraw().getGameTime();
            float createInterval=randomlyCreateAndPlaceEntitySystem.getCreateInterval();
            if(createInterval%gameTime.getTotalGameTimeLapsedInSeconds()!=0){
                continue;
            }
            entityToPlace.remove(RandomlyCreateAndPlaceEntity.class);
        boolean placed=false;
            if(randomlyCreateAndPlaceEntitySystem.isRandomLocation() && randomlyCreateAndPlaceEntitySystem.isRandomMap()){
               placed=  randomPlacement(entityToPlace, entityPosition);
            }
            else if(randomlyCreateAndPlaceEntitySystem.isRandomLocation()){
            placed=randomPlacementOnSelectedMap(entityToPlace, randomlyCreateAndPlaceEntitySystem.getMapIds(), entityPosition);
            }
            else{
                placed=placeEntity(entityToPlace, randomlyCreateAndPlaceEntitySystem.getPositionsToCreateAt(), entityPosition);
            }
            if(placed==true){
                getWorld().addEntityToWorld(entityToPlace);
                randomlyCreateAndPlaceEntitySystem.placeEntity();
                if(randomlyCreateAndPlaceEntitySystem.getEntitiesPlaced()>=randomlyCreateAndPlaceEntitySystem.getEntitiesToCreate()){
                    getWorld().removeEntityFromWorld(entity);
                }
            }
            // add entity to map  add the tiles
        }
    }
// places a entity  at random tile selected from a random map  in the random placement component
    private boolean randomPlacement(Entity entity, PositionComponent positionComponent){
        Array<GameMap> maps=getWorld().getWorldMaps().values().toArray();
        int randomNumberMap=RandomNumbers.getRandomNumber(0, maps.size-1);
         GameMap map=maps.get(randomNumberMap);
            LandSquareTile landSquareTile=MapUtilities.getRandomEnterableTile(map);
            if(landSquareTile==null){
                return  false;
            }
            MapUtilities.setPositionToTile(landSquareTile, positionComponent);
        return  true;
    }
    // places a entity  at random tile selected from the list of allowed map ids  in the random placement component
    private boolean randomPlacementOnSelectedMap(Entity entity, Array<String> mapIds, PositionComponent positionComponent){
        int max=mapIds.size;
        int random=RandomNumbers.getRandomNumber(0, max);
       String mapId=mapIds.get(random);
        LandMap map= (LandMap) getWorld().getMap(mapId);
            LandSquareTile landSquareTile=MapUtilities.getRandomEnterableTile(map);
            if(landSquareTile==null){
                return false;
            }
            MapUtilities.setPositionToTile(landSquareTile, positionComponent);
            return  true;
    }
// places a entity  at random tile selected from the list of allowed tiles  in the random placement component
    private boolean placeEntity(Entity entity, Array<PositionComponent> positionComponents, PositionComponent positionComponent){
        boolean enterable=false;
        int counter=0;
            int max = positionComponents.size;
            int random = RandomNumbers.getRandomNumber(0, max);
            PositionComponent positionComponent1 = positionComponents.get(random);
            entity.remove(PositionComponent.class);
            entity.add(positionComponent);
        return true;
    }
}
