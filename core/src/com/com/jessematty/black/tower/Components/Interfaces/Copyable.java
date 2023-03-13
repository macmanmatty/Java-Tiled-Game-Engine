package com.jessematty.black.tower.Components.Interfaces;

import com.badlogic.ashley.core.Component;

public interface Copyable<T> extends Component {

    T makeCopy();

}
