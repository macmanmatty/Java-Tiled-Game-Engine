package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.Stat;

public interface StatModifiableComponent extends Component {


    public Array<Stat> getStats();




}
