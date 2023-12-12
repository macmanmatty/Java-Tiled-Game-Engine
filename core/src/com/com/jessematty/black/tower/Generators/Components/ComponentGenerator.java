package com.jessematty.black.tower.Generators.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;

/**
 * interface for component generator classes that generate
 * a libGDX Ashley  component from a specified file
 * @param <T> the  libGDX Ashley component  type
 */
public interface ComponentGenerator <T extends Component> {

    T generateComponent(String filePath, FileUtilities.FileHandleType type);
}
