package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.CreateEntity;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntitiesOnTime;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntityOnTime;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class CreateEntityOnTimeSystem extends GameEntitySystem {
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<CreateEntitiesOnTime> createEntitiesOnTimeComponentMapper;
    private  ComponentMapper<CreateEntity> createEntityComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ImmutableArray<Entity> entities;
    public CreateEntityOnTimeSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        createEntityComponentMapper=getGameComponentMapper().getCreateEntityComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();


        
        
    }
    @Override
    public void update(float deltaTime) {
        double gameSeconds=getDraw().getGameTime().getGameSeconds();
        entities= getEngine().getEntitiesFor(Family.all(CreateEntitiesOnTime.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            CreateEntitiesOnTime createEntitiesOnTime=createEntitiesOnTimeComponentMapper.get(entity);
            Array<CreateEntityOnTime> createEntityOnTimes=createEntitiesOnTime.getEntitiesToCreate();
            int entitiesToCreate=createEntityOnTimes.size;
            for(int count2=0; count2<entitiesToCreate; count2++){
                CreateEntityOnTime createEntityOnTime=createEntityOnTimes.get(count2);
                if(gameSeconds%createEntityOnTime.getInterval()==0){

                    PositionComponent position=createEntityOnTime.getPosition();
                    if(position!=null) {
                        String entityToCreateID = createEntityOnTime.getEntityToCreateID();
                        Entity entityToCreate = getWorld().getEntity(entityToCreateID);
                        Entity entityCopy = getDraw().getAssetts().getJsonLoader().copyObject(entityToCreate, Entity.class);

                        entityCopy.add(position);
                        getWorld().addEntityToWorld(entityCopy);
                    }

                }


            }




        }
        super.update(deltaTime);
    }

    private void setLocationAndAddToMap(Entity entityToCreate) {

        getWorld().addEntityToWorld(entityToCreate);
        PositionComponent position=positionComponentMapper.get(entityToCreate);




    }
}
