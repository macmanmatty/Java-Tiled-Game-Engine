package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.CreateEntity;
import com.jessematty.black.tower.Components.EntityCreateable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class CreateEntitySystem extends GameEntitySystem {
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<EntityCreateable> entityCreateableComponentMapper;
    private  ComponentMapper<CreateEntity> createEntityComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ImmutableArray<Entity> entities;
    public CreateEntitySystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        entityCreateableComponentMapper =getGameComponentMapper().getEntityCreateableComponentMapper();
        createEntityComponentMapper=getGameComponentMapper().getCreateEntityComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();


        
        
    }
    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(CreateEntity.class, EntityCreateable.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
             CreateEntity createEntity= createEntityComponentMapper.get(entity);
             EntityCreateable entityCreateable=entityCreateableComponentMapper.get(entity);
             Entity entityToCreate=entityCreateable.getEntitiesToCreate().get(createEntity.getEntityName());
             if(entityToCreate!=null){
                 EntityUtilities.copyEntity(getAsssets(), entity);
                 setLocationAndAddToMap(entity);
             }



        }
        super.update(deltaTime);
    }

    private void setLocationAndAddToMap(Entity entityToCreate) {

        getWorld().addEntity(entityToCreate);
        Position position=positionComponentMapper.get(entityToCreate);




    }
}
