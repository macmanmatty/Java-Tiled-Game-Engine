package TestEntities;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStats;

public  final class TestEntity {

   public  Entity movable= new Entity();
   public    Entity staticWall= new Entity();
  {

       movable.add(new PhysicalObjectComponent());
       movable.add(new PositionComponent());
       movable.add(new NameComponent());
       NumericStats numericStats= new NumericStats();
       movable.add(numericStats );
       movable.add(new ActionComponent());
       movable.add(new MovableComponent());

   }

    public TestEntity() {

    }
}
