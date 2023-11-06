package Generators.LPCObjectGenerator;

import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;

import org.junit.Test;
import org.mockito.Mockito;

import Maps.TestMap;

public class LPCActorGeneratorTest {
    GameAssets gameAssets= new GameAssets("game", Mockito.any());

    @Test
    public void  testLoadAssets(){
        LPCObjectGenerator lpcObjectGenerator= new LPCObjectGenerator(gameAssets, new TestMap().testWorld);

    }

}
