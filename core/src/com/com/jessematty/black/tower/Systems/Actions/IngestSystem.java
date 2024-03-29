package com.jessematty.black.tower.Systems.Actions;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Ingest;
import com.jessematty.black.tower.Components.Other.Ingestable;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Stats.ChangeStats;

public class IngestSystem extends GameEntitySystem {

    private ComponentMapper<Ingestable> ingestableComponentMapper;
    private ComponentMapper<com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Ingest> ingestingComponentMapper;

    ImmutableArray<Entity> entities;


    public IngestSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {

        ingestableComponentMapper=GameComponentMapper.getIngestableComponentMapper();
        ingestingComponentMapper= GameComponentMapper.getIngestingComponentMapper();


    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( Ingest.class, Ingestable.class).get());

        int size=entities.size();


                for(int count=0; count<size; count++){

                    Entity entity=entities.get(count);
                    Ingestable ingestable=ingestableComponentMapper.get(entity);
                    Ingest ingest=ingestingComponentMapper.get(entity);
                    String ingestorId="";
                    if(ingestorId!=null){
                        Entity ingestor=getWorld().getEntity(ingestorId);

                        ChangeStats.changeStats(ingestor ,  entity,   "ingest",  false,  true, true);

                    }



                    entity.remove(Ingest.class);
                    entity.add(new RemoveFromEngine());
                }


    }


}
