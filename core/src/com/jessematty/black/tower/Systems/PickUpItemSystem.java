package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.PickUp;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class PickUpItemSystem extends GameEntitySystem {
            private ComponentMapper<ID> idComponentMapper;
            private  ComponentMapper<Holder> holderComponentMapper;
            private ComponentMapper<Action> actionComponentMapper;
            private ComponentMapper<Position> positionComponentMapper;
            private ComponentMapper<PickUp> pickUpComponentMapper;
            private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    public PickUpItemSystem(MapDraw draw) {
        super(draw);
       idComponentMapper= getGameComponentMapper().getIdComponentMapper();
        holderComponentMapper=getGameComponentMapper().getHolderComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        pickUpComponentMapper=getGameComponentMapper().getPickUpComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(Position.class, PickUp.class).get());
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
    private Entity getItem(Position position, Array<Entity> entities) {
        int size = entities.size;
        for (int count = 0; count < size; count++) {
            Entity entity=entities.get(count);
            Position entityPosition =entity.getComponent(Position.class);
            boolean overLaps = Intersector.overlapConvexPolygons(entityPosition.getBounds(), position.getBounds());
            if (overLaps == true) {
                return entity;
            }
        }
        return null;
    }
}
