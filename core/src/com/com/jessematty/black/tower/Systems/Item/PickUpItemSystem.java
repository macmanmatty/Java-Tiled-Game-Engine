package com.jessematty.black.tower.Systems.Item;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Item.ItemAction.PickUpItemComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.GameEntitySystem;
/**
 * s
 */
public class PickUpItemSystem extends GameEntitySystem {
            private ComponentMapper<AnimatableComponent> animatableComponentComponentMapper;
    public PickUpItemSystem(MapDraw draw) {
        super(draw);
        animatableComponentComponentMapper=GameComponentMapper.getAnimatableComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(PickUpItemComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entityToPickUp=entities.get(count);
            PositionComponent positionComponent=GameComponentMapper.getPositionComponentMapper().get(entityToPickUp);
            positionComponent.removeBounds();
            positionComponent.setOnGround(false);
            GameMap map= getWorld().getMap(positionComponent.getMapId());
            map.removeEntity(positionComponent.getTiles(), entityToPickUp);
            entityToPickUp.remove(PickUpItemComponent.class);
            AnimatableComponent animatableComponent=animatableComponentComponentMapper.get(entityToPickUp);
            if(animatableComponent!=null){
                animatableComponent.setSingleImage(false);
            }

            }
    }
}
