package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.EntitySystem;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public abstract class GameEntitySystem extends EntitySystem   {
     private final  MapDraw draw;
     private final World world;
     private final GameAssets assets;
     private final KeyListener keyListener;

    public GameEntitySystem(MapDraw draw) {
        this.draw = draw;
        this.world=draw.getWorld();
        this.assets =draw.getGameAssets();
        this.keyListener=draw.getGameAssets().getGameInput().getKeyListener();


    }

    public GameEntitySystem(int priority, MapDraw draw) {
        super(priority);
        this.draw = draw;
        this.world=draw.getWorld();
        this.assets =draw.getGameAssets();
        this.keyListener=draw.getGameAssets().getGameInput().getKeyListener();


    }

    public final  MapDraw getDraw() {
        return draw;
    }



    public final  GameMap getMap(int x , int y){

        return draw.getWorld().getMap(x, y);
    }

    public LandSquareTile getTile(int mapX, int mapY, int tileX, int tileY){

        GameMap map=getMap(mapX, mapY);
        if(map!=null) {


            return map.getMapSquareOrNull(tileX, tileY);

        }
        return null;

    }


    public final  World getWorld() {
        return world;
    }

    public final  GameAssets getAssets() {
        return assets;
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }
}
