package com.jessematty.black.tower.Systems.UI;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import Container;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Containers.Pack;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class UIWindowSystem extends GameEntitySystem {

       private  ComponentMapper<Pack> pack;
       private ComponentMapper<EntityId> idComponentMapper;
       private ComponentMapper<ItemComponent> itemComponentMapper;
       private ComponentMapper<Container> containerComponentMapper;
       private ComponentMapper<OwnerComponent> ownerComponent;
       private ZRPGCharacter player;
       private UIWindowSystem window;


    public UIWindowSystem(MapDraw draw, ZRPGCharacter player) {
        super(draw);
        this.player=player;

    }



    @Override
    public void addedToEngine(Engine engine) {
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        pack= GameComponentMapper.getPackComponentMapper();

    }

    @Override
    public void update(float deltaTime) {

        if(player.isShowBottomBar()) {
            Array<Entity> packs = EntityUtilities.getOwnedEntitiesWithComponents(getWorld(), player.getPlayerEntity(), Pack.class);



        }






    }
}
