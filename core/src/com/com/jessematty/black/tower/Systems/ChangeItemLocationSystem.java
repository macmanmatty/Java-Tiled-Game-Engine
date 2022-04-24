package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Container;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class ChangeItemLocationSystem extends GameEntitySystem {

       private  Pack pack;
       private  Entity entity;
       private PhysicalObjectComponent object;
       private PositionComponent position;
       private Item item;
       private ComponentMapper<EntityId> idComponentMapper;
       private ComponentMapper<Item> itemComponentMapper;
       private ComponentMapper<Container> containerComponentMapper;


    public ChangeItemLocationSystem(MapDraw draw, Pack pack, Entity entity) {
        super(draw);
        this.pack = pack;
        this.entity = entity;
        idComponentMapper= GameComponentMapper.getIdComponentMapper();
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
    }

    @Override
    public void update(float deltaTime) {



        getEngine().removeSystem(this);


    }
}
