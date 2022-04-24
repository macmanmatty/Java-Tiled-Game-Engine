package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class RemoveItemFromPack extends GameEntitySystem {


    private Pack pack;
    private Entity itemToGet;
    private PhysicalObjectComponent object;
    private PositionComponent position;
    private Item item;
    private  int  counter;
    private Holder holder;
    private ComponentMapper<EntityId> idComponentMapper;


    public RemoveItemFromPack(MapDraw draw, Pack pack, Entity itemToGet) {
        super(draw);
        this.pack = pack;
        this.itemToGet = itemToGet;
        idComponentMapper= GameComponentMapper.getIdComponentMapper();
    }



    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
    }

    @Override
    public void update(float deltaTime) {

       Entity item= getWorld().getEntity(pack.getItem(counter));
       Entity currentlyHeldItem=getWorld().getEntity(holder.getItemToHoldId());
       if(currentlyHeldItem!=null){
           getEngine().addSystem(new ChangeItemLocationSystem( getDraw(),pack, currentlyHeldItem));
       }

       if(itemToGet.equals(item)){
           holder.setItemToHoldId(idComponentMapper.get(item).getId());
           getEngine().removeSystem(this);

       }









    }
}
