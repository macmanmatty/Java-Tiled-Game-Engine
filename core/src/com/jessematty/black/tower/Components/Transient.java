package com.jessematty.black.tower.Components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  marker annotation for libGDX Ashley ECS components that should not be serialized on game saving
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}
