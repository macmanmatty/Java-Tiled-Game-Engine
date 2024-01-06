package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.ZRPGCharacterUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class EquipItemSystem extends GameEntitySystem {
    private  ComponentMapper<EquipItem> equipItemComponentMapper;
    private ComponentMapper<ZRPGCharacter> zrpgCharacterComponentMapper;
    private ComponentMapper<ContainerComponent> containerComponentComponentMapper;
    private ComponentMapper<BodyComponent> bodyComponentComponentMapper;

    public EquipItemSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        equipItemComponentMapper=GameComponentMapper.getEquipItemComponentMapper();
        zrpgCharacterComponentMapper=GameComponentMapper.getZrpgCharacterComponentMapper();
        containerComponentComponentMapper=GameComponentMapper.getContainerComponentMapper();
        bodyComponentComponentMapper=GameComponentMapper.getBodyComponentComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(ZRPGCharacter.class, BodyComponent.class, EquipItem.class).get());
      for(Entity character : entities){
          ZRPGCharacter zcharacter=zrpgCharacterComponentMapper.get(character);
          EquipItem equipItem=equipItemComponentMapper.get(character);
          Entity item=getWorld().getEntity(equipItem.getItemId());
          Entity bodyPart=getWorld().getEntity(equipItem.getBodyPartId());
          equipItem(zcharacter, item, bodyPart);
      }

    }

    /**
     * adds an entity to a zrpg character
     *
     * @param zrpgCharacter the character to add the entity to.
     * @param item   the entity to add to the character
     */
    public  void equipItem(ZRPGCharacter zrpgCharacter, Entity item, Entity bodyPart) {
        DrawableComponent drawableComponent=GameComponentMapper.getDrawableComponentMapper().get(item);
        drawableComponent.setDraw(true);
        addToComponents(item, zrpgCharacter);
        if(bodyPart!=null){
        EntityUtilities.attachEntity(zrpgCharacter.getPlayerEntity(), bodyPart);

        }
        else {
            EntityUtilities.attachEntity(zrpgCharacter.getPlayerEntity(), item);
        }
    }

    /**
     *
     * @param entity
     * @param zrpgCharacter
     */
    public void addToComponents(Entity entity, ZRPGCharacter zrpgCharacter){
        String entityToAddId = GameComponentMapper.getIdComponentMapper().get(entity).getId();
        if (containerComponentComponentMapper.get(entity) != null) {
            zrpgCharacter.getPacks().getPackEntityIds().add(entityToAddId);
        }

    }

}
