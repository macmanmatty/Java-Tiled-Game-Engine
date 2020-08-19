package com.jessematty.black.tower.Systems.ItemChangeMovements;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.ChangeItemLocationSystem;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class RemoveItemFromPack extends GameEntitySystem {


    private Pack pack;
    private Entity itemToGet;
    private PhysicalObject object;
    private Position position;
    private Item item;
    private  int  counter;
    private Holder holder;
    private ComponentMapper<ID> idComponentMapper;


    public RemoveItemFromPack(MapDraw draw, Pack pack, Entity itemToGet) {
        super(draw);
        this.pack = pack;
        this.itemToGet = itemToGet;
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
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
