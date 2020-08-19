package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AddOwnerComponent;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class AddOwnerSystem extends GameEntitySystem { // checks  the die when zero  stats  for all entities  if all stats are zero
    // marks the entities  as dying.

    private ComponentMapper<AddOwnerComponent> addOwnerComponentComponentMapper;
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<ID> idComponentMapper;

    public AddOwnerSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        addOwnerComponentComponentMapper=getGameComponentMapper().getAddOwnerComponentComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        attachableComponentMapper=getGameComponentMapper().getAttachableComponentMapper();
        ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(AddOwnerComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            AddOwnerComponent addOwnerComponent=addOwnerComponentComponentMapper.get(entity);
            entity.remove(AddOwnerComponent.class);
            String ownerID=addOwnerComponent.getOwnerId();
            OwnerComponent ownerComponent=ownerComponentComponentMapper.get(getWorld().getEntity(ownerID));
            if(ownerComponent!=null){

                Array<String> ownedEntities=ownerComponent.getOwnedEntityIDs();
                if(ownerComponent.getMaxOwnedEntities()<ownedEntities.size) {
                    ownerComponent.getOwnedEntityIDs().add(idComponentMapper.get(entity).getId());

                }
                else{


                }
            }

            else{


            }
            OwnedComponent ownedComponent=  new OwnedComponent();
            ownedComponent.setOwnerEntityID(ownerID);
            entity.add(ownedComponent);



        }



        super.update(deltaTime);
    }


}



