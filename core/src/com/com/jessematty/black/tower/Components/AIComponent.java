package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.AI.Brain;

public class AIComponent implements Component {

  private Brain brain= new Brain();

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain brain) {
    this.brain = brain;
  }
}

