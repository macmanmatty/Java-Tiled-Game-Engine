package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Maps.World;

/**
 * class that holds utility functions  for  a Zelda Like RPG Character
 */
public class ZRPGCharacterUtilities {

    private ZRPGCharacterUtilities() {
    }

    /**
     * adds an entity to a zrpg character
     *
     * @param zrpgCharacter the character to add the entity to.
     * @param entity   the entity to add to the character
     */
    public static void equipItem(ZRPGCharacter zrpgCharacter, Entity entity) {
        String entityToAddId = GameComponentMapper.getIdComponentMapper().get(entity).getId();
        if (GameComponentMapper.getContainerComponentMapper().get(entity) != null) {
            zrpgCharacter.getPacks().getPackEntityIds().add(entityToAddId);
        }
        if(GameComponentMapper.getPartComponentComponentMapper().get(entity)!=null){
            String name=GameComponentMapper.getNameComponentMapper().get(entity).getStat();
            zrpgCharacter.getBody().getBodyParts().put(name, entityToAddId);

        }
        EntityUtilities.attachEntity(zrpgCharacter.getPlayerEntity(), entity);
    }

    /**
     * removes an entity from  a zrpg character
     *
     * @param zrpgCharacter the character to add the entity to.
     * @param entity   the entity to add to the character
     */
    public static void unequipItem(ZRPGCharacter zrpgCharacter, Entity entity) {
        String entityToAddId = GameComponentMapper.getIdComponentMapper().get(entity).getId();
        if (GameComponentMapper.getContainerComponentMapper().get(entity) != null) {
            zrpgCharacter.getPacks().getPackEntityIds().removeValue(entityToAddId, false);
        }
        if(GameComponentMapper.getPartComponentComponentMapper().get(entity)!=null){
            String name=GameComponentMapper.getNameComponentMapper().get(entity).getStat();
            zrpgCharacter.getBody().getBodyParts().remove(name);
        }
        EntityUtilities.detachEntity(zrpgCharacter.getPlayerEntity(), entity);
    }

    /**
     * @param bodyPartName  the name of the body part
     * @param zrpgCharacter th
     * @param world         the game world
     * @return
     */
    public static Entity getBodyPart(String bodyPartName, ZRPGCharacter zrpgCharacter, World world) {
        Entity character = zrpgCharacter.getPlayerEntity();
        BodyComponent bodyComponent = GameComponentMapper.getBodyComponentComponentMapper().get(character);
        if (bodyComponent != null) {
            String bodyPartId = bodyComponent.getBodyParts().get(bodyPartName);
            if (bodyPartId != null) {
                return world.getEntity(bodyPartId);
            }
        }
        return  null;
    }
}