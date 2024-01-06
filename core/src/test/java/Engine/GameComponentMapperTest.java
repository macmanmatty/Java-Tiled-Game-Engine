package Engine;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

import org.junit.Test;

public class GameComponentMapperTest {
    @Test
  public void   getAllEntitiesWithComponents(){
        Entity entity=new Entity();
        entity.add( new ContainerComponent());
        entity.add( new ItemComponent());
        Entity entity2= new Entity();
        entity2.add(new ItemComponent());
        Array<Entity> entityArray= new Array<>();
        entityArray.add(entity2);
        entityArray.add(entity);
       Array<Entity> items= GameComponentMapper.getEntitiesWithComponents(entityArray, ItemComponent.class);
       assertEquals(2, items.size);

    }


}
