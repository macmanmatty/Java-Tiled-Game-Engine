package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class EquipItemInHand extends EventSystem {

    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<Holder> hands;
    private ComponentMapper<NameComponent> names;
    private ComponentMapper<ID> idComponentMapper;


    Entity holder;
   Entity itemToHold;
    public EquipItemInHand(MapDraw draw, Entity holder, Entity itemToHold) {
        super(draw);
        this.holder = holder;
        this.itemToHold = itemToHold;
        hands= GameComponentMapper.getHolderComponentMapper();
        names=GameComponentMapper.getNameComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        positions=GameComponentMapper.getPositionComponentMapper();
    }



    @Override
    public void act(float deltaTime) {
        Holder itemHolder=hands.get(holder);
        Entity currentItemHoldingItem=getWorld().getEntity(itemHolder.getItemToHoldId());
        if(currentItemHoldingItem==null) {
            itemHolder.setItemToHoldId(idComponentMapper.get(itemToHold).getId());
            NameComponent holderNameComponent = names.get(holder);
            NameComponent itemToHoldNameComponent = names.get(itemToHold);
            holderNameComponent.setStat(holderNameComponent.getStat() + "Holding " + itemToHoldNameComponent);

        }


    }

}