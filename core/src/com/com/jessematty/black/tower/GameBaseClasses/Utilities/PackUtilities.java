package com.jessematty.black.tower.GameBaseClasses.Utilities;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;

/**
 * Utilities class that  contains  helper methods for the pack component
 */
public class PackUtilities {
    /**
     * method that returns  all pack components an entity can be added to
     * based on the entities volume weight and groups
     * @param packComponents an libGDX array of all pack components available
     * @param entity the entity to add the pack(s)
     * @return the packs the entity can currently be added to.
     */
    public static  Array<PackComponent> getAvailablePacks(World world, Array<PackComponent> packComponents, Entity entity){
        PhysicalObjectComponent physicalObjectComponent= GameComponentMapper.getPhysicalObjectComponentMapper().get(entity);
        GroupsComponent groupsComponent=GameComponentMapper.getGroupsComponentMapper().get(entity);
        PositionComponent positionComponent=GameComponentMapper.getPositionComponentMapper().get(entity);
         double gravity=world.getMap(positionComponent.getMapId()).getGravity();
        Array<PackComponent> availablePacks=new Array<>();
        for(PackComponent pack: packComponents){
            double availableWeight=pack.getMaxHoldWeight()-pack.getCurrentWeight();
            double availableVolume=pack.getMaxVolume()-pack.getCurrentFilledVolume();
            double [] volumeAndMass=EntityUtilities.getEntityMassAndVolume(world, entity);
            double weight=volumeAndMass[0]*gravity;
            if(availableWeight<weight || availableVolume<volumeAndMass[1]){
                continue;
            }
            if( groupsComponent!=null&& InList.isInList(groupsComponent.getGroups(), pack.getGroupsAddable())){
                availablePacks.add(pack);
            }
        }
        return  availablePacks;
    }
}
