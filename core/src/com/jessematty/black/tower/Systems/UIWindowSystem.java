package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Container;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class UIWindowSystem extends GameEntitySystem {

       private  ComponentMapper<Pack> pack;
       private ComponentMapper<ID> idComponentMapper;
       private ComponentMapper<Item> itemComponentMapper;
       private ComponentMapper<Container> containerComponentMapper;
       private ComponentMapper<OwnerComponent> ownerComponent;
       private ZRPGPlayer player;
       private UIWindowSystem window;


    public UIWindowSystem(MapDraw draw, ZRPGPlayer player) {
        super(draw);
        this.player=player;

    }



    @Override
    public void addedToEngine(Engine engine) {
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
        pack=getGameComponentMapper().getPackComponentMapper();


    }

    @Override
    public void update(float deltaTime) {

        if(player.isShowBottomBar()) {
            Array<Entity> packs = EntityUtilities.getOwnedEntitiesWithComponents(getWorld(), player.getPlayerEntity(), Pack.class);



        }






    }
}
