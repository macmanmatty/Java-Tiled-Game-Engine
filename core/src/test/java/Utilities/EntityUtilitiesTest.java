package Utilities;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
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
    Maps.TestMap testMap= new TestMap();
    World testWorld;
    String ownedId;
    String ownerId;
    @Before
    public void createEntities(){

       owned.add(new OwnedComponent());
        owner.add(new OwnerComponent());
        testWorld=testMap.testWorld;
        testWorld.addEntityToWorld(owner);
        testWorld.addEntityToWorld(owned);
      ownedId= owned.getComponent(EntityId.class).getId();
      ownerId= owner.getComponent(EntityId.class).getId();


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

}
