package Generators.LPCObjectGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Maps.TestMap;
import libGDX.HeadlesslibGDX;

public class LPCActorGeneratorTest {
    GameAssets gameAssets;
    @Before
    public void setup(){
        new HeadlesslibGDX();
       gameAssets = new GameAssets("game", Mockito.any());
    }
    @Test
    public void  testLoadAssets(){
        LPCObjectGenerator lpcObjectGenerator= new LPCObjectGenerator(gameAssets, new TestMap().testWorld);
     ObjectMap<String, EntityBag> entityBagArray= lpcObjectGenerator.loadEntities("/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/core/src/test/java/Generators/LPCObjectGenerator/testEntities.json", false, false);
        assertEquals(11, entityBagArray.size);
        assertEquals(6, entityBagArray.get("lizard").getEntities().size);
        assertEquals(0, entityBagArray.get("tree").getEntities().size);
        assertNotNull(entityBagArray.get("lizard").getOwner());
        assertNotNull(entityBagArray.get("tree").getOwner());
    }


}
