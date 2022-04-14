package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntityOnAction;
import com.jessematty.black.tower.Components.CreateEntity.ParticleEntity;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class CreateEntityOnActionSystem extends GameEntitySystem {
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<CreateEntityOnAction> entityCreateableComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ImmutableArray<Entity> entities;
    public CreateEntityOnActionSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        actionComponentMapper= GameComponentMapper.getActionComponentMapper();
        entityCreateableComponentMapper =GameComponentMapper.getEntityCreateableComponentMapper();
        positionComponentMapper=GameComponentMapper.getPositionComponentMapper();


        
        
    }
    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(CreateEntityOnAction.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
             CreateEntityOnAction createEntityOnAction =entityCreateableComponentMapper.get(entity);
             String action=actionComponentMapper.get(entity).getStat();
             ParticleEntity particleEntity= createEntityOnAction.getEntitiesToCreate().get(action);
             if(particleEntity==null){
                 continue;
             }
             String entityToCreateID=particleEntity.getEntityId();


             if(entityToCreateID!=null){
                 Entity entityToCreate=getWorld().getEntity(entityToCreateID);
                 PositionComponent positionComponent=positionComponentMapper.get(entityToCreate);
                 if(positionComponent!=null){
                     Vector3 startPositionOffset=particleEntity.getCreateOffsets();
                     positionComponent.movePosition(startPositionOffset.x, startPositionOffset.y, startPositionOffset.z);

                 }
                 EntityUtilities.copyEntity(getAssets(), entityToCreate);

                 setLocationAndAddToMap(entity);
             }



        }
        super.update(deltaTime);
    }

    private void setLocationAndAddToMap(Entity entityToCreate) {

        getWorld().addEntityToWorld(entityToCreate);
        PositionComponent position=positionComponentMapper.get(entityToCreate);




    }
}
