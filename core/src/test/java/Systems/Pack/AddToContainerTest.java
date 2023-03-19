package Systems.Pack;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.Pack.AddToContainerSystem;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AddToContainerTest {
        Entity owner= new Entity();
        Entity owned= new Entity();
        Maps.TestMap testMap= new TestMap();
        World testWorld;
        String ownedId;
        String ownerId;
        Engine engine= new Engine();
        MapDraw mapDraw;
        Entity addItemToContainer= new Entity();
        @Before
        public void createEntities() {

            owner.add(new OwnerComponent());
            testWorld = testMap.testWorld;
            testWorld.addEntityToWorld(owner);
            testWorld.addEntityToWorld(owned);
            ownedId = owned.getComponent(EntityId.class).getId();
            ownerId = owner.getComponent(EntityId.class).getId();
            mapDraw = testMap.mapDraw;
            mapDraw.setWorld(testMap.testWorld);
            engine.addSystem(new AddToContainerSystem(mapDraw));
            PositionComponent positionComponent = new PositionComponent();
            positionComponent.setMapID(testMap.testMap1.getId());
            PositionComponent positionComponent2 = new PositionComponent();
            positionComponent2.setMapID(testMap.testMap1.getId());
            owned.add(positionComponent);
            owner.add(positionComponent2);
        }

        @Test
        public void addToContainer(){
            addItemToContainer.add(new AddItemToContainer(ownedId, ownerId, true));
            engine.addEntity(addItemToContainer);
            ContainerComponent containerComponent= new ContainerComponent();
            containerComponent.setMaxHoldWeight(1000);
            containerComponent.setMaxVolume(100);
            PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
            physicalObjectComponent.setMass(1);
            physicalObjectComponent.setVolume(1);
            owned.add(physicalObjectComponent);
            owner.add(containerComponent);
            engine.update(1);
            OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
            OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
            assertNotNull(ownerComponent);
            assertNotNull(ownedComponent);
            assertEquals( 1, containerComponent.getEntitiesInContainerIds().size);
            assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
            assertEquals(ownedId, ownerComponent.getOwnedEntityIDs().get(0));
            assertEquals(ownedComponent.getOwnerEntityID(), ownerId);
        }
    @Test
    public void addToContainerItemTooHeavy(){
        engine.addEntity(addItemToContainer);
        ContainerComponent containerComponent= new ContainerComponent();
        containerComponent.setMaxHoldWeight(1000);
        containerComponent.setMaxVolume(100);
        PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
        physicalObjectComponent.setMass(10000);
        physicalObjectComponent.setVolume(1);
        owned.add(physicalObjectComponent);
        owner.add(containerComponent);
        engine.update(1);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNull(ownedComponent);
        assertEquals( 0, containerComponent.getEntitiesInContainerIds().size);
        assertEquals( 0, ownerComponent.getOwnedEntityIDs().size);
    }
    @Test
    public void addToContainerItemTooLarge(){
        engine.addEntity(addItemToContainer);
        ContainerComponent containerComponent= new ContainerComponent();
        containerComponent.setMaxHoldWeight(1000);
        containerComponent.setMaxVolume(100);
        PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
        physicalObjectComponent.setMass(1);
        physicalObjectComponent.setVolume(1000);
        owned.add(physicalObjectComponent);
        owner.add(containerComponent);
        engine.update(1);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNull(ownedComponent);
        assertEquals( 0, containerComponent.getEntitiesInContainerIds().size);
        assertEquals( 0, ownerComponent.getOwnedEntityIDs().size);
    }

    @Test
    public void addToContainerWithoutAddingOwnership(){
        addItemToContainer.add(new AddItemToContainer(ownedId, ownerId, false));
        engine.addEntity(addItemToContainer);
        ContainerComponent containerComponent= new ContainerComponent();
        containerComponent.setMaxHoldWeight(1000);
        containerComponent.setMaxVolume(100);
        PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
        physicalObjectComponent.setMass(1);
        physicalObjectComponent.setVolume(10);
        owned.add(physicalObjectComponent);
        owner.add(containerComponent);
        engine.update(1);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNull(ownedComponent);
        assertEquals( 1, containerComponent.getEntitiesInContainerIds().size);
        assertEquals( 0, ownerComponent.getOwnedEntityIDs().size);
    }

    @Test
    public void addToContainerNotInGroupsNoContainerGroupsSet(){
        addItemToContainer.add(new AddItemToContainer(ownedId, ownerId));
        engine.addEntity(addItemToContainer);
        ContainerComponent containerComponent= new ContainerComponent();
        containerComponent.setMaxHoldWeight(1000);
        containerComponent.setMaxVolume(100);
        GroupsComponent groupsComponent = new GroupsComponent();
        groupsComponent.getGroups().add("weapon");
        owned.add(groupsComponent);
        PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
        physicalObjectComponent.setMass(1);
        physicalObjectComponent.setVolume(10);
        owned.add(physicalObjectComponent);
        owner.add(containerComponent);
        engine.update(1);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNotNull(ownedComponent);
        assertEquals( 1, containerComponent.getEntitiesInContainerIds().size);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(ownedId, ownerComponent.getOwnedEntityIDs().get(0));
        assertEquals(ownedComponent.getOwnerEntityID(), ownerId);
    }
    @Test
    public void addToContainerNotInGroupsWithNonMatchingContainerGroupsSet(){
        addItemToContainer.add(new AddItemToContainer(ownedId, ownerId));
        engine.addEntity(addItemToContainer);
        ContainerComponent containerComponent= new ContainerComponent();
        containerComponent.setMaxHoldWeight(1000);
        containerComponent.setMaxVolume(100);
        containerComponent.getGroupsAddable().add("food");
        GroupsComponent groupsComponent = new GroupsComponent();
        groupsComponent.getGroups().add("weapon");
        owned.add(groupsComponent);
        PhysicalObjectComponent physicalObjectComponent= new PhysicalObjectComponent();
        physicalObjectComponent.setMass(1);
        physicalObjectComponent.setVolume(10);
        owned.add(physicalObjectComponent);
        owner.add(containerComponent);
        engine.update(1);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNull(ownedComponent);
        assertEquals( 0, containerComponent.getEntitiesInContainerIds().size);
        assertEquals( 0, ownerComponent.getOwnedEntityIDs().size);

    }
}
