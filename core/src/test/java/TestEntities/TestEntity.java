package TestEntities;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;

public  final class TestEntity {

   public  Entity movable= new Entity();
   public Entity item= new Entity();
    public Entity item2= new Entity();

    public    Entity staticWall= new Entity();
  {

       movable.add(new PhysicalObjectComponent());
       movable.add(new PositionComponent());
       movable.add(new NameComponent());
       NumericStats numericStats= new NumericStats();
       movable.add(numericStats );
       movable.add(new ActionComponent());
       movable.add(new MovableComponent());
      item.add(new PhysicalObjectComponent());
      item.add(new PositionComponent());
      item.add(new NameComponent());
      NumericStats numericStats2= new NumericStats();
      item.add(numericStats2 );
      item.add(new ActionComponent());
      item.add(new ItemComponent());
      item2.add(new PhysicalObjectComponent());
      item2.add(new PositionComponent());
      item2.add(new NameComponent());
      NumericStats numericStats3= new NumericStats();
      numericStats3.addStat(new NumericStat(true, "love", 1));
      item2.add(numericStats2 );
      item2.add(new ActionComponent());
      item2.add(new ItemComponent());

   }

    public TestEntity() {

    }
}
