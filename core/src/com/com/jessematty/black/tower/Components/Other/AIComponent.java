package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.AI.ZRPGBrainComponent;

public class AIComponent implements Component {

  private ZRPGBrainComponent ZRPGBrainComponen = new ZRPGBrainComponent();

  public ZRPGBrainComponent getZRPGBrainComponen() {
    return ZRPGBrainComponen;
  }

  public void setZRPGBrainComponen(ZRPGBrainComponent ZRPGBrainComponen) {
    this.ZRPGBrainComponen = ZRPGBrainComponen;
  }
}

