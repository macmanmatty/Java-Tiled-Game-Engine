package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.EntitySystem;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
/**
 * class that extends EntitySystem that holds a reference to the GameAssets
 * MapDraw  World  and KeyListener  for Easy access 
 * 
 * */
public abstract class GameEntitySystem extends EntitySystem   {
     private final  MapDraw draw;
     private final World world;
     private final GameAssets assets;
    public GameEntitySystem(MapDraw draw) {
        this.draw = draw;
        this.world=draw.getWorld();
        this.assets =draw.getGameAssets();
    }
    public GameEntitySystem(int priority, MapDraw draw) {
        super(priority);
        this.draw = draw;
        this.world=draw.getWorld();
        this.assets =draw.getGameAssets();
    }
    public final  MapDraw getDraw() {
        return draw;
    }
    public final  World getWorld() {
        return world;
    }
    public final  GameAssets getAssets() {
        return assets;
    }
}
