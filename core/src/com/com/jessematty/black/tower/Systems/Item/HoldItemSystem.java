package com.jessematty.black.tower.Systems.Item;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.Item.ItemAction.HoldItemComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;
/**
 *  system for holding an item
 */
public class HoldItemSystem extends GameEntitySystem {
    private ComponentMapper<HoldItemComponent> holdItemComponentMapper;
        private ComponentMapper<Holder> holderComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private  ComponentMapper<com.jessematty.black.tower.Components.AttachEntity.Holdable> holdableComponentMapper;
    private ComponentMapper<ItemComponent> itemComponentComponentMapper;
    public HoldItemSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        holdItemComponentMapper = GameComponentMapper.getHoldItemComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        holdableComponentMapper =GameComponentMapper.getHoldableComponentMapper();
        itemComponentComponentMapper =GameComponentMapper.getItemComponentMapper();
        holderComponentMapper=GameComponentMapper.getHolderComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all( HoldItemComponent.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity hand= entities.get(count);
            HoldItemComponent holdable=holdItemComponentMapper.get(hand);
            String itemId=holdable.getItemId();
            Entity item=getWorld().getEntity(itemId);
            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(item);
            if(ownedComponent!=null){
                Entity currentOwner=getWorld().getEntity(ownedComponent.getOwnerEntityID());
                EntityUtilities.detachEntity(currentOwner, item);
            }
            Holder holder=holderComponentMapper.get(hand);
            holder.setItemToHoldId(itemId);
            ItemComponent itemComponent=itemComponentComponentMapper.get(item);
            if(itemComponent!=null){
                itemComponent.setHeld(true);
                itemComponent.setOnGround(false);
                itemComponent.setInContainer(false);
            }
            EntityUtilities.attachEntity(hand, item);
            hand.remove(EquipItem.class);
            hand.remove(HoldItemComponent.class);
        }
    }
}
