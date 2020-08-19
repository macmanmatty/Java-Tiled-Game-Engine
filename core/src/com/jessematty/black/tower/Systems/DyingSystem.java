package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Dying;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.RemoveFromEngine;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

@Transient
public class DyingSystem extends GameEntitySystem {
    private ComponentMapper<Position> positions;
    private ComponentMapper<Item> itemComponentMapper;
    private ComponentMapper<Dying> dyingComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentMapper;
    public DyingSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        positions=getGameComponentMapper().getPositionComponentMapper();
        itemComponentMapper=getGameComponentMapper().getItemComponentMapper();
        dyingComponentMapper=getGameComponentMapper().getDyingComponentMapper();
        animatableComponentMapper=getGameComponentMapper().getAnimatableComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(Dying.class, Position.class).get());
        int size=entities.size();
        for(int count=0;  count<size; count++ ) {
            Entity entity = entities.get(count);
            Position position = positions.get(entity);
            Dying dying = dyingComponentMapper.get(entity);
            if(!dying.isDying()){
                dying.setDying(true);
                AnimatableComponent animatable=animatableComponentMapper.get(entity);
                if(animatable!=null){
                    Animation animation=animatable.getAnamation(position.getDirection(), "die");
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
                Polygon bounds = position.getDirectionalBounds().get(position.getDirection());
                if (bounds != null) {
                    position.setBounds(bounds);
                }
                //add remove from engine component  as entity is now dead
                entity.add(new RemoveFromEngine());
                entity.remove(Dying.class);
                // remove all owned entities
                Array<Entity> ownedEntities = EntityUtilities.getAllOwnedEntities(entity, getWorld());
                int items = ownedEntities.size;
                for (int count2 = 0; count2 < items; count2++) {
                    Entity ownedEntity = entities.get(count2);
                    Item item = itemComponentMapper.get(ownedEntity);
                    // unless owned entity is an item and is drop able remove it from engine
                    if (item != null && item.isDropOnoDie()) {
                        Drop drop = new Drop();
                        entity.add(drop);
                    } else {
                        ownedEntity.add(new RemoveFromEngine());
                    }
                }
                entity.remove(Dying.class);
            }
        }
    }
}
