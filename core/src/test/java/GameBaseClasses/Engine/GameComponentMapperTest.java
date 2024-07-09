package GameBaseClasses.Engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jessematty.black.tower.AI.Item.PickUpItem;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import java.io.File;

import Maps.TestMap;
import TestEntities.TestEntity;

public class GameComponentMapperTest {
    private Entity movable1;
    private Entity item;
    private Array<Entity> entities=new Array<>();


    @Before
    public void setup() {
        TestEntity testEntity= new TestEntity();
        movable1=  testEntity.movable;
        item =testEntity.item;
        PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
        positionComponent.setLocationX(222);
        positionComponent.setLocationY(222);
        positionComponent.setBounds(32,32);
        item.add(positionComponent);
        item.add(new ItemComponent());
        StringStat text= new StringStat("text");
        item.add(new StringStats());
        item.getComponent(StringStats.class).addStat(text);
        NumericStat price= new NumericStat("price");
        NumericStats numericStats= new NumericStats();
        numericStats.addStat(price);
        item.add(numericStats);
        BooleanStat on= new BooleanStat("on");
        BooleanStats booleanStats= new BooleanStats();
        booleanStats.addStat(on);
        item.add(booleanStats);
        item.getComponent(StringStats.class).addStat(text);
        movable1.add(new MovingOnGroundComponent());
        movable1.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
        positionComponent.setBounds(32, 32);
        entities.add(item);
        entities.add(movable1);

    }

    @Test
    public void getEntitiesWithComponents(){
     Array<Entity>  entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, null, null, ItemComponent.class);
       assertEquals(1, entities.size);


    }

    @Test
    public void getEntitiesWithStringStats(){
        Array<String> stats= new Array<>();
        stats.add("text");
        Array<Entity>  entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, null, stats);
        assertEquals(1, entities.size);


    }
    @Test
    public void getEntitiesWithNumericStats(){
        Array<String> stats= new Array<>();
        stats.add("price");
        Array<Entity>  entities = GameComponentMapper.getEntitiesContainingStats(this.entities, stats, null, null);
        assertEquals(1, entities.size);

        stats.add("cost");
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, stats, null, null);
        assertEquals(0, entities.size);
    }
    @Test
    public void getEntitiesWithBooleanStats(){
        Array<String> stats= new Array<>();
        stats.add("on");
        Array<Entity>  entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null);
        assertEquals(1, entities.size);
        stats.add("on");
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null , ItemComponent.class);
        assertEquals(1, entities.size);
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null , PackComponent.class);
        assertEquals(0, entities.size);

        stats.add("cost");
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null);
        assertEquals(0, entities.size);
    }
    @Test
    public void getEntitiesWithAllStats(){
        Array<String> stats= new Array<>();
        stats.add("on");
        Array<String> stats2= new Array<>();
        stats2.add("price");
        Array<String> stats3= new Array<>();
        stats3.add("text");
        Array<Entity>  entities = GameComponentMapper.getEntitiesContainingStats(this.entities, stats2, stats, stats3, ItemComponent.class);
        assertEquals(1, entities.size);
        stats.add("on");
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null , ItemComponent.class);
        assertEquals(1, entities.size);
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null , PackComponent.class);
        assertEquals(0, entities.size);

        stats.add("cost");
        entities = GameComponentMapper.getEntitiesContainingStats(this.entities, null, stats, null);
        assertEquals(0, entities.size);
    }

}
