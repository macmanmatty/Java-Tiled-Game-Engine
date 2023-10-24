package Utilities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PackUtilities;
import com.jessematty.black.tower.Maps.World;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
import TestEntities.TestEntity;
import sun.jvm.hotspot.gc.shared.G1HeapRegionType;

import static org.junit.Assert.assertEquals;

public
class PackUtilitiesTest {
    private Entity owner;
    private MapDraw mapDraw;
    private World world;
    private TestMap testMap= new TestMap();
    private Entity owned;
    private Entity wand= new Entity();
    private PackComponent packComponent2;

    private Array<PackComponent> packs= new Array<>();

    @Before
    public void before(){
        mapDraw = testMap.mapDraw;
        mapDraw.setWorld(testMap.testWorld);
        owner = new TestEntity().movable;
        owned = new Entity();
        PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
        physicalObjectComponent.setMass(10);
        physicalObjectComponent.setVolume(10);
        PhysicalObjectComponent physicalObjectComponent2= new PhysicalObjectComponent();
        physicalObjectComponent2.setMass(10);
        physicalObjectComponent2.setVolume(10);
        PhysicalObjectComponent physicalObjectComponent3= new PhysicalObjectComponent();
        physicalObjectComponent3.setMass(1);
        physicalObjectComponent3.setVolume(1);
        GroupsComponent groupsComponent1= new GroupsComponent();
        GroupsComponent groupsComponent2= new GroupsComponent();
        groupsComponent1.getGroups().add("food");
        groupsComponent2.getGroups().add("brick");
        GroupsComponent groupsComponent3= new GroupsComponent();
        groupsComponent3.getGroups().add("wand", "wood");
        PositionComponent positionComponent=  owner.getComponent(PositionComponent.class);
        positionComponent.setMapID(mapDraw.getCurrentMap().getId());
        positionComponent.setLocationX(128);
        positionComponent.setLocationY(128);
        PositionComponent positionComponent2=  owner.getComponent(PositionComponent.class);
        positionComponent2.setMapID(mapDraw.getCurrentMap().getId());
        positionComponent2.setLocationX(128);
        positionComponent2.setLocationY(128);
        wand.add(positionComponent2);
        wand.add(physicalObjectComponent3);
        wand.add(groupsComponent3);
        owner.add(groupsComponent1);
        owned.add(groupsComponent2);
        owner.add(physicalObjectComponent);
        owned.add(physicalObjectComponent2);
        owned.add(new ActionComponent());
        world = testMap.testWorld;
        owner.add(new MovingOnGroundComponent());
        owner.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
        world.addEntityToWorld(owner);
        world.addEntityToWorld(owned);

        Boolean attach = EntityUtilities.attachEntity(owner, owned);
        PackComponent packComponent1= new PackComponent();
        packComponent1.setMaxVolume(10);
        packComponent1.setMaxHoldWeight(10);
        packComponent1.getGroupsAddable().add("food");
        packComponent1.getGroupsAddable().add("wand");
        packComponent2=new PackComponent();
        packComponent2.setMaxVolume(201);
        packComponent2.setMaxHoldWeight(201);
        packComponent2.getGroupsAddable().add("food");
        packComponent2.getGroupsAddable().add("wood");
        packs.add(packComponent1);
        packs.add(packComponent2);

    }

    @Test
    public void testAddItemToHeavy(){
        Array<PackComponent> packs= PackUtilities.getAvailablePacks(world, this.packs, owner);
        assertEquals( 1, packs.size);

    }
    @Test
    public void testAddItemToHeavy2(){
        packComponent2.setCurrentWeight(100);
        Array<PackComponent> packs= PackUtilities.getAvailablePacks(world, this.packs, owner);
        assertEquals( 0, packs.size);

    }
    @Test
    public void testAddAll(){
        Array<PackComponent> packs= PackUtilities.getAvailablePacks(world, this.packs, wand);
        assertEquals( 2, packs.size);

    }
    @Test
    public void testNoMatchingGroups(){
       GroupsComponent groupsComponent= owner.getComponent(GroupsComponent.class);
       groupsComponent.getGroups().clear();
       groupsComponent.getGroups().add("wand");
        Array<PackComponent> packs= PackUtilities.getAvailablePacks(world, this.packs, owner);
        assertEquals( 0, packs.size);

    }

    @Test
    public void testNoGroups(){
        owner.remove(GroupsComponent.class);
        Array<PackComponent> packs= PackUtilities.getAvailablePacks(world, this.packs, owner);
        assertEquals( 0, packs.size);

    }

}
