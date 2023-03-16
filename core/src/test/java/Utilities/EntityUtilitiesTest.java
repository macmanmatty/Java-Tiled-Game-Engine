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
    @Before
    public void createEntities(){

        owned.add(new OwnedComponent());
        owner.add(new OwnerComponent());
        testWorld=testMap.testWorld;
        testWorld.addEntityToWorld(owner);
        testWorld.addEntityToWorld(owned);

    }

    @Test
    public void testAttachEntity(){
        EntityUtilities.attachEntity(testWorld, owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNotNull(ownedComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(owned.getComponent(EntityId.class).getId(), ownerComponent.getOwnedEntityIDs().get(0));
    }
    @Test
    public void testAttachEntityWithoutOwnedComponent(){
        EntityUtilities.attachEntity(testWorld, owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        owned.remove(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownedComponent);


    }
}
