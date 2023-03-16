package com.jessematty.black.tower.Systems.Sound;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Other.SoundComponent;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

@Transient
public  class SoundSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<SoundComponent> soundComponentComponentMapper;
    private ComponentMapper<ActionComponent> actionComponentMapper;
    public SoundSystem(MapDraw draw,  int priority) {
        super(priority, draw );
    }
    @Override
    public void addedToEngine(Engine engine) {
        soundComponentComponentMapper =GameComponentMapper.getSoundComponentComponentMapper();
        actionComponentMapper= GameComponentMapper.getActionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(OnCurrentMap.class,  SoundComponent.class,  ActionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);

            SoundComponent soundComponent=soundComponentComponentMapper.get(entity);
            ActionComponent actionComponent =actionComponentMapper.get(entity);
            String newAction= actionComponent.getStat();
            String currentAction=soundComponent.getCurrentAction();
            // action changed play Sound;
            if(!currentAction.equalsIgnoreCase(newAction)){

                soundComponent.setPlaySound(true);
            }
            soundComponent.setCurrentAction(actionComponent.getStat());


            int timesToPlay = soundComponent.getCurrentSoundTimesToPlay();
            if (timesToPlay > 0) {
                if (soundComponent.getTimesPlayed()>=timesToPlay) {

                    soundComponent.resetCounter();
                    soundComponent.setPlaySound(false);


                }

            }
            soundComponent.tick();





        }
    }
}