package com.jessematty.black.tower.Components.EventComponents;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Interfaces.Transient;


/**
 * interface for event action components like collision ,
 * eating , using a potion, or adding an item to container
 */
@Transient
public interface EventComponent  extends Component {
}
