package com.jessematty.black.tower.Generators.Entity.LPCGenerator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.World;

public class LPCObjectGenerator {



    private final  GameAssets assets;
    private final  World world;
    private GameComponentMapper gameComponentMapper;
    protected  int layerNumber=1;
    private int objectlayerNumber=2;
    private ComponentMapper<ID> idComponentMapper;

    public LPCObjectGenerator(GameAssets assetts, World world) {
        this.assets = assetts;
        this.world = world;
    }

    public Entity generatePhysicalObject(Entity entity, float  mass, float volume, float  boundsX, float boundsY) {
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        entity.add(physicalObject);
        PositionComponent position= new PositionComponent();
        position.setBounds(boundsX, boundsY);
        entity.add(position);
        entity.add(physicalObject);

        return  entity;

    }

    public GameAssets getAssets() {
        return assets;
    }

    public World getWorld() {
        return world;
    }
}
