package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class ChangeItemLocationSystem extends GameEntitySystem {

       private PackComponent packComponent;
       private  Entity entity;
       private PhysicalObjectComponent object;
       private PositionComponent position;
       private ItemComponent item;
       private ComponentMapper<EntityId> idComponentMapper;
       private ComponentMapper<ItemComponent> itemComponentMapper;


    public ChangeItemLocationSystem(MapDraw draw, PackComponent packComponent, Entity entity) {
        super(draw);
        this.packComponent = packComponent;
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
