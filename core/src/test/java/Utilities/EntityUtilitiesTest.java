package Utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Attacks.Shootable;
import com.jessematty.black.tower.Components.Attacks.Slashable;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.Weapon;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.World;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
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
        PositionComponent positionComponent=new PositionComponent();
        positionComponent.setMapID(testMap.testMap1.getId());
        owned.add(positionComponent);
        owner.add(positionComponent);
        owned2.add(positionComponent);
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
    public void testAttachSameEntityTwice(){
       boolean attached= EntityUtilities.attachEntity( owner, owned);
     boolean attachedAgain=   EntityUtilities.attachEntity( owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertEquals(true, attached);
        assertEquals(false, attachedAgain);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(ownedId, ownerComponent.getOwnedEntityIDs().get(0));
        assertEquals(ownedComponent.getOwnerEntityID(), ownerId);
    }
    @Test
    public void testAttachEntityWithOwnedComponent(){
        EntityUtilities.attachEntity( owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertNotNull(ownedComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(ownedId, ownerComponent.getOwnedEntityIDs().get(0));
        assertEquals(ownedComponent.getOwnerEntityID(), ownerId);
    }
    @Test
    public void testAttachEntityWithOutOwnerComponent(){
        owner.remove(OwnerComponent.class);
        EntityUtilities.attachEntity( owner, owned);
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
        EntityUtilities.attachEntity( owner, owned);
        Array<Entity> entities= EntityUtilities.getAllOwnedEntities(owner, testWorld);
        assertEquals(1,entities.size);
        assertEquals(owned, entities.get(0));
    }
    @Test
    public void testGetAllOwnedEntitiesLevel3(){
        EntityUtilities.attachEntity( owner, owned);
     EntityUtilities.attachEntity( owner, owned2);
      EntityUtilities.attachEntity(owned, subOwned);
       Array<Entity> entities= EntityUtilities.getAllOwnedEntities(owner, testWorld);
        assertEquals(3,entities.size);
        assertEquals(owned, entities.get(0));
        assertEquals(owned2, entities.get(2));
        assertEquals(subOwned, entities.get(1));
    }

    @Test
    public void testGetAllOwnedEntitiesLevel3WithItem(){
        subOwned.add(new ItemComponent());
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.attachEntity( owner, owned2);
        EntityUtilities.attachEntity(owned, subOwned);
        Array<Entity> entities= EntityUtilities.getAllOwnedEntitiesWithComponents( testWorld, owner, ItemComponent.class);
        assertEquals(1,entities.size);
    }
    @Test
    public void testGetAllOwnedEntitiesLevel3WithItems(){
        subOwned.add(new ItemComponent());
        owned.add(new ItemComponent());
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.attachEntity( owner, owned2);
        EntityUtilities.attachEntity(owned, subOwned);
        Array<Entity> entities= EntityUtilities.getAllOwnedEntitiesWithComponents( testWorld, owner, ItemComponent.class);
        assertEquals(2,entities.size);
    }
    @Test
    public void testGetAllOwnedEntitiesLevel3WithNoComponents(){
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.attachEntity( owner, owned2);
        EntityUtilities.attachEntity(owned, subOwned);
        Array<Entity> entities= EntityUtilities.getAllOwnedEntitiesWithComponents( testWorld, owner, Shootable.class);
        assertEquals(0,entities.size);
    }
    @Test
    public void testGetAllOwnedEntitiesLevel3With2Components(){
        subOwned.add(new ItemComponent());
        owned.add(new ItemComponent());
        owned.add( new Weapon());
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.attachEntity( owner, owned2);
        EntityUtilities.attachEntity(owned, subOwned);
        Array<Entity> entities= EntityUtilities.getAllOwnedEntitiesWithComponents( testWorld, owner, ItemComponent.class, Weapon.class);
        assertEquals(1,entities.size);
    }
    @Test
    public void testAttachEntityWithOwnedComponentWithGetAllOwnedEntitiesIDs(){
        EntityUtilities.attachEntity( owner, owned);
        Array<String> entities= EntityUtilities.getAllOwnedEntitiesIDs(owner, testWorld);
        assertEquals(1,entities.size);
        assertEquals(ownedId, entities.get(0));
    }
    @Test
    public void testAttachEntityWithoutOwnedComponent(){
        owned.remove(OwnedComponent.class);
        EntityUtilities.attachEntity( owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        assertNotNull(ownerComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownedComponent);
        assertEquals(ownedComponent.getOwnerEntityID(), ownerId);
    }
    @Test
    public void testDetachEntity(){
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.detachEntity(owner, owned);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertEquals( 0, ownerComponent.getOwnedEntityIDs().size);
        assertEquals(null, ownedComponent);
    }

    @Test
    public void testDetachEntity2(){
        Entity entity=new Entity();
        entity.add(new EntityId());
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.detachEntity(owner, entity);
        OwnerComponent ownerComponent=owner.getComponent(OwnerComponent.class);
        OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
        assertNotNull(ownerComponent);
        assertEquals( 1, ownerComponent.getOwnedEntityIDs().size);
    }
    @Test
    public void testDetachSameEntityTwice(){
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.detachEntity(owner, owned);
        EntityUtilities.detachEntity(owner, owned);
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
        EntityUtilities.attachEntity( owner, owned);
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
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.attachEntity(owner, subOwned);
        double[] massAndVolume = EntityUtilities.getEntityMassAndVolume(testWorld, owner);
        assertEquals(250,massAndVolume[1] ,1);
        assertEquals(250, massAndVolume[0] ,1);
    }
    @Test
    public void testGetTotalMassAndVolumeL3NonAttachedEntity() {
        PhysicalObjectComponent physcialObject = new PhysicalObjectComponent(100, 100);
        PhysicalObjectComponent physcialObject2 = new PhysicalObjectComponent(100, 100);
        PhysicalObjectComponent physcialObject3 = new PhysicalObjectComponent(50, 50);
        owner.add(physcialObject);
        owned.add(physcialObject2);
        subOwned.add(physcialObject3);
        EntityUtilities.attachEntity(owner, owned);
        EntityUtilities.attachEntity( owned, subOwned);
        double[] massAndVolume = EntityUtilities.getEntityMassAndVolume(testWorld, owner);
        assertEquals(250,massAndVolume[1] ,1);
        assertEquals(250, massAndVolume[0] ,1);
        assertEquals(250*9.8, massAndVolume[2], 1);
    }
    @Test
    public void testGetEntityPosition() {
        PositionComponent positionComponent = new PositionComponent();
        PositionComponent positionComponent2 = new PositionComponent();
        positionComponent2.setMapID("map");
        positionComponent2.setLocationX(1);
        positionComponent2.setTileLocationY(10);
        owner.add(positionComponent2);
        owned.add(positionComponent);
        EntityUtilities.attachEntity( owner, owned);
        EntityUtilities.attachEntity(owner, subOwned);
        PositionComponent positionComponent1=EntityUtilities.getEntityPosition(testWorld, owned);
        assertEquals(1, positionComponent1.getLocationX(), 0);
        assertEquals(10, positionComponent1.getTileLocationY());
        assertEquals("map", positionComponent1.getMapId());
    }
    @Test
    public void createEntityTest(){
    Entity entity= EntityUtilities.createEntity(true, true);
        assertNotNull(entity.getComponent(EntityId.class));
        assertNotNull(entity.getComponent(NumericStats.class));
        assertNotNull(entity.getComponent(BooleanStats.class));
        assertNotNull(entity.getComponent(StringStats.class));
        assertNotNull(entity.getComponent(NumericStatsChangeable.class));
        assertNotNull(entity.getComponent(BooleanStatsChangeable.class));
        assertNotNull(entity.getComponent(StringStatsChangeable.class));
    }
    }
