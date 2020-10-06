package com.jessematty.black.tower.GameBaseClasses.Loaders.World;

import com.badlogic.ashley.core.ComponentMapper;
import com.esotericsoftware.kryo.io.Input;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Loaders.EntityLoaders.EntityLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.LoadingException;
import com.jessematty.black.tower.GameBaseClasses.Loaders.MapLoaders.MapLoader;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.World;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class WorldReader {
    private final GameAssets assetts;
    private String fileSeperator;
    private final World world;
    private final MapLoader mapLoader;
    private final EntityLoader entityLoader;
    private final GameAssets gameAssets;

    ComponentMapper<PositionComponent> positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);

    public WorldReader(GameAssets assetts) {
        this.assetts = assetts;
        fileSeperator = FileUtilities.getFileSeparator();
        world = new World();
        mapLoader=new MapLoader(assetts);
        entityLoader=new EntityLoader(assetts);
        this.gameAssets=assetts;


    }

    public World loadWorld(String path) throws com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception, LoadingException { // loads  game  from the maps json and tmx files ina given path


        Input input= null;
        try {
            input = new Input( new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        World world= (World) gameAssets.getKryo().readClassAndObject(input);

        return  world;




    }


}

