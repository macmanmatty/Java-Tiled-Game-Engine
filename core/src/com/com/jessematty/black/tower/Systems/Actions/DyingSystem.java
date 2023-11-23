package com.jessematty.black.tower.Systems.Actions;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Dying;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.Components.Item.ItemAction.DropItemComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

@Transient
public class DyingSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<ItemComponent> itemComponentMapper;
    private ComponentMapper<Dying> dyingComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentMapper;
    public DyingSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        positions= GameComponentMapper.getPositionComponentMapper();
        itemComponentMapper=GameComponentMapper.getItemComponentMapper();
        dyingComponentMapper=GameComponentMapper.getDyingComponentMapper();
        animatableComponentMapper=GameComponentMapper.getAnimatableComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(Dying.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0;  count<size; count++ ) {
            Entity entity = entities.get(count);
            PositionComponent position = positions.get(entity);
            Dying dying = dyingComponentMapper.get(entity);
            if(!dying.isDying()){
                dying.setDying(true);
                AnimatableComponent animatable=animatableComponentMapper.get(entity);
                if(animatable!=null){
                    Animation animation=animatable.getAnimation(position.getDirection(), "die");
                    if(animation!=null){
                        dying.setTimeToDie(animation.getFrames().length*animation.getFrameRate());
                    }
                    else{
                        dying.setTimeToDie(1);
                    }
                }
            }
            int dieCounter=dying.getDieCounter();
            System.out.println("Die counter "+dieCounter+ "time to die "+dying.getTimeToDie() );
            dying.tick();
            if (dieCounter>=dying.getTimeToDie()){
                System.out.println("Die");
                position.removeBounds();

                //add remove from engine component  as entity is now dead
                entity.add(new RemoveFromEngine());
                entity.remove(Dying.class);
                // remove all owned entities
                Array<Entity> ownedEntities = EntityUtilities.getAllOwnedEntities(entity, getWorld());
                int items = ownedEntities.size;
                for (int count2 = 0; count2 < items; count2++) {
                    Entity ownedEntity = entities.get(count2);
                    ItemComponent item = itemComponentMapper.get(ownedEntity);
                    // unless owned entity is an item and is drop able remove it from engine
                    if (item != null && item.isDropOnoDie()) {
                        DropItemComponent dropItemClass = new DropItemComponent();
                        entity.add(dropItemClass);
                    } else {
                        ownedEntity.add(new RemoveFromEngine());
                    }
                }
                entity.remove(Dying.class);
            }
        }
    }
}
