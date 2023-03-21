package Utilities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.World;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class EntityUtilitiesTest {

    Entity owner= new Entity();
    Entity owned= new Entity();
    Entity owned2 = new Entity();
    Entity subOwned= new Entity();
    Maps.TestMap testMap= new TestMap();
    World testWorld;
    String ownedId;
    String ownerId;
    String subOwnedId;
    @Before
    public void createEntities(){

       owned.add(new OwnedComponent());
        owner.add(new OwnerComponent());
        owned.add(new OwnerComponent());
        testWorld=testMap.testWorld;
        testWorld.addEntityToWorld(owner);
        testWorld.addEntityToWorld(owned);
        testWorld.addEntityToWorld(subOwned);
        testWorld.addEntityToWorld(owned2);
         ownedId= owned.getComponent(EntityId.class).getId();
        ownerId= owner.getComponent(EntityId.class).getId();
        subOwnedId= owner.getComponent(EntityId.class).getId();
    }

    @Test
    public void testAttachEntityWithOwnedComponent(){
        EntityUtilities.attachEntity(testWorld, owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNotNull(ownedComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(ownedId, ownerComponent.getOwnedEntityIDs().get(0));
        assertEquals(ownedComponent.getOwnerEntityID(), ownerId);
    }
    @Test
    public void testGetAllOwnedEntities(){
        EntityUtilities.attachEntity(testWorld, owner, owned);
        Array<Entity> entities= EntityUtilities.getAllOwnedEntities(owner, testWorld);
        assertEquals(1,entities.size);
        assertEquals(owned, entities.get(0));
    }
    @Test
    public void testGetAllOwnedEntitiesLevel3(){
        EntityUtilities.attachEntity(testWorld, owner, owned);
     EntityUtilities.attachEntity(testWorld, owner, owned2);
      EntityUtilities.attachEntity(testWorld, owned, subOwned);
       Array<Entity> entities= EntityUtilities.getAllOwnedEntities(owner, testWorld);
        assertEquals(3,entities.size);
        assertEquals(owned, entities.get(0));
        assertEquals(owned2, entities.get(2));
        assertEquals(subOwned, entities.get(1));

    }
    @Test
    public void testAttachEntityWithOwnedComponentWithGetAllOwnedEntitiesIDs(){
        EntityUtilities.attachEntity(testWorld, owner, owned);
        Array<String> entities= EntityUtilities.getAllOwnedEntitiesIDs(owner, testWorld);
        assertEquals(1,entities.size);
        assertEquals(ownedId, entities.get(0));
    }
    @Test
    public void testAttachEntityWithoutOwnedComponent(){
        owned.remove(OwnedComponent.class);
        EntityUtilities.attachEntity(testWorld, owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        assertNotNull(ownerComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownedComponent);
        assertEquals(ownedComponent.getOwnerEntityID(), ownerId);

    }

    @Test
    public void testDetachEntity(){
        EntityUtilities.detachEntity(testWorld, owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertEquals( 0, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(null, ownedComponent);
    }

    @Test
    public void testGetTotalMassAndVolume() {
        PhysicalObjectComponent physcialObject = new PhysicalObjectComponent(100, 100);
        PhysicalObjectComponent physcialObject2 = new PhysicalObjectComponent(100, 100);
        owner.add(physcialObject);
        owned.add(physcialObject2);
        EntityUtilities.attachEntity(testWorld, owner, owned);
        double[] massAndVolume = EntityUtilities.getEntityMassAndVolume(testWorld, owner);
        assertEquals(200,massAndVolume[1] ,1);
        assertEquals(200, massAndVolume[0] ,1);

    }
    @Test
    public void testGetTotalMassAndVolumeL3() {
        PhysicalObjectComponent physcialObject = new PhysicalObjectComponent(100, 100);
        PhysicalObjectComponent physcialObject2 = new PhysicalObjectComponent(100, 100);
        PhysicalObjectComponent physcialObject3 = new PhysicalObjectComponent(50, 50);

        owner.add(physcialObject);
        owned.add(physcialObject2);
        subOwned.add(physcialObject3);

        EntityUtilities.attachEntity(testWorld, owner, owned);
        EntityUtilities.attachEntity(testWorld, owner, subOwned);
        double[] massAndVolume = EntityUtilities.getEntityMassAndVolume(testWorld, owner);
        assertEquals(250,massAndVolume[1] ,1);
        assertEquals(250, massAndVolume[0] ,1);

    }

    }
