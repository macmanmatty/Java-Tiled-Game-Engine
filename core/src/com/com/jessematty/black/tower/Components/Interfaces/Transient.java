package com.jessematty.black.tower.Components.Interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  marker annotation for libGDX Ashley ECS components that should not be serialized on game saving
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}
