package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.PickUp;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class PickUpItemSystem extends GameEntitySystem {
            private ComponentMapper<ID> idComponentMapper;
            private  ComponentMapper<Holder> holderComponentMapper;
            private ComponentMapper<ActionComponent> actionComponentMapper;
            private ComponentMapper<PositionComponent> positionComponentMapper;
            private ComponentMapper<PickUp> pickUpComponentMapper;
            private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    public PickUpItemSystem(MapDraw draw) {
        super(draw);
       idComponentMapper= GameComponentMapper.getIdComponentMapper();
        holderComponentMapper=GameComponentMapper.getHolderComponentMapper();
        actionComponentMapper=GameComponentMapper.getActionComponentMapper();
        positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
        pickUpComponentMapper=GameComponentMapper.getPickUpComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(PositionComponent.class, PickUp.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {

            Entity entityToPickUp=entities.get(count);
            PickUp pickUp=pickUpComponentMapper.get(entityToPickUp);
            String entityToPickUpId=idComponentMapper.get(entityToPickUp).getId();
            String pickerUpperEntityId=pickUp.getEntityToPickUpId();
            Entity pickerUpperEntity=getWorld().getEntity(pickerUpperEntityId);
            Holder holder=holderComponentMapper.get(pickerUpperEntity);
            holder.setItemToHoldId(entityToPickUpId);
            OwnedComponent ownedComponent= new OwnedComponent();
            ownedComponent.setOwnerEntityID(pickerUpperEntityId);
            ownedComponent.setSetEntityActionToOwner(pickUp.isSetEntityActionToOwner());
            ownedComponent.setSetEntityPositionToOwner(pickUp.isSetEntityPositionToOwner());
            entityToPickUp.add(ownedComponent );
            OwnerComponent ownerComponent=ownerComponentComponentMapper.get(pickerUpperEntity);
            if(ownerComponent==null){
                ownerComponent= new OwnerComponent();
                ownerComponent.getOwnedEntityIDs().add(entityToPickUpId);

            }

            else{

                ownerComponent.getOwnedEntityIDs().add(entityToPickUpId);
            }

            entityToPickUp.remove(PickUp.class);





            }




    }
    private Entity getItem(PositionComponent position, Array<Entity> entities) {
        int size = entities.size;
        for (int count = 0; count < size; count++) {
            Entity entity=entities.get(count);
            PositionComponent entityPosition =entity.getComponent(PositionComponent.class);
            boolean overLaps = Intersector.overlapConvexPolygons(entityPosition.getBounds(), position.getBounds());
            if (overLaps == true) {
                return entity;
            }
        }
        return null;
    }
}
