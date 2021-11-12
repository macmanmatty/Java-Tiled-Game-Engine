package com.jessematty.black.tower.Components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  marker annotation for components that  should be newly created  on saving
 */
@Retention(RetentionPolicy.RUNTIME)

public @interface NewComponent {

}
