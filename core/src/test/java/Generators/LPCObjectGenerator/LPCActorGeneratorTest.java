package Generators.LPCObjectGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import libGDX.HeadlesslibGDX;

public class LPCActorGeneratorTest {
    GameAssets gameAssets;
    @Before
    public void setup(){
        new HeadlesslibGDX();
       gameAssets = new GameAssets("game", Mockito.any());
    }
    @Test
    public void  testLoadEntitiesFromFile(){
        LPCObjectGenerator lpcObjectGenerator= new LPCObjectGenerator(gameAssets);
     ObjectMap<String, EntityBag> entityBagArray= lpcObjectGenerator.loadEntities("/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/core/src/test/java/Generators/LPCObjectGenerator/testEntities.json", false, FileUtilities.FileHandleType.ABSOLUTE);
        assertEquals(11, entityBagArray.size);
        assertEquals(13, entityBagArray.get("lizard").getEntities().size);
        assertEquals(0, entityBagArray.get("tree").getEntities().size);
        assertNotNull(entityBagArray.get("lizard").getOwner());
        assertNotNull(entityBagArray.get("tree").getOwner());
    }

    @Test
    public void  testLoadEntitiesFromDirectory(){
        LPCObjectGenerator lpcObjectGenerator= new LPCObjectGenerator(gameAssets);
        ObjectMap<String, EntityBag> entityBagArray= lpcObjectGenerator.loadEntities("/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/core/src/test/java/Generators/LPCObjectGenerator/Entities", false, FileUtilities.FileHandleType.ABSOLUTE);
        assertEquals(2, entityBagArray.size);
        assertNotNull(entityBagArray.get("sword").getOwner());
        assertNotNull(entityBagArray.get("bow").getOwner());

    }
}
