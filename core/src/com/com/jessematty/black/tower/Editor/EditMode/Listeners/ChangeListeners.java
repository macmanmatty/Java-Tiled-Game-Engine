package com.jessematty.black.tower.Editor.EditMode.Listeners;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.WorldSettable;

/**
 *  class that hold array objects that need to be notified  when something changes.
 */
public class ChangeListeners {
    private final  Array<WorldSettable> worldSettables= new Array<>();
    private final  Array<MapSettable> mapSettables= new Array<>();
    private final    Array<ClipBoardChangeListener> clipBoardChangeListeners= new Array();

    public void  setMap(GameMap map){
        for(MapSettable mapSettable:mapSettables){
            mapSettable.setMap(map);
        }
    }
    public void  setWorld(World world){
        for(WorldSettable worldSettable:worldSettables){
            worldSettable.setWorld(world);
        }
    }

    public void  setClipBoardObject(Object object){
        for(ClipBoardChangeListener clipBoardChangeListener:clipBoardChangeListeners){
            clipBoardChangeListener.objectChanged(object);
        }
    }

    public Array<WorldSettable> getWorldSettables() {
        return worldSettables;
    }

    public Array<MapSettable> getMapSettables() {
        return mapSettables;
    }

    public Array<ClipBoardChangeListener> getClipBoardChangeListeners() {
        return clipBoardChangeListeners;
    }
}
