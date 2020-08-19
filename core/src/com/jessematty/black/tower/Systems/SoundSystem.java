package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.SoundComponent;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

@Transient
public  class SoundSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<SoundComponent> soundComponentComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    public SoundSystem(MapDraw draw,  int priority) {
        super(priority, draw );
    }
    @Override
    public void addedToEngine(Engine engine) {
        soundComponentComponentMapper =getGameComponentMapper().getSoundComponentComponentMapper();
        positionComponentMapper =getGameComponentMapper().getPositionComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( SoundComponent.class, Position.class, Action.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Position position= positionComponentMapper.get(entity);
            if(getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY())!=getDraw().getCurrentMap()) {
                // not on current map  don't draw or calculate anything realated to drawing
                continue;
            }
            SoundComponent soundComponent=soundComponentComponentMapper.get(entity);
            Action action=actionComponentMapper.get(entity);
            String newAction=action.getText();
            String currentAction=soundComponent.getCurrentAction();
            // action changed play Sound;
            if(!currentAction.equalsIgnoreCase(newAction)){

                soundComponent.setPlaySound(true);
            }
            soundComponent.setCurrentAction(action.getText());


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