package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.PlaySound;
import com.jessematty.black.tower.Components.Ears;
import com.jessematty.black.tower.Components.SoundComponent;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;


public  class PlaySoundSystem extends GameEntitySystem{
    private ComponentMapper<SoundComponent> soundComponentComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
   private  ImmutableArray<Entity> entities;
    private ZRPGPlayer player;
    private Position playerPosition;



    Batch batch;

    public PlaySoundSystem(MapDraw draw, int priorty) {
        super(priorty, draw);
        player=draw.getPlayer();



    }

    @Override
    public void addedToEngine(Engine engine) {

        soundComponentComponentMapper =getGameComponentMapper().getSoundComponentComponentMapper();
        positionComponentMapper =getGameComponentMapper().getPositionComponentMapper();




    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        entities=getEngine().getEntitiesFor(Family.all( Action.class, SoundComponent.class, Position.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {

            Entity entity = entities.get(count);
            SoundComponent soundComponent = soundComponentComponentMapper.get(entity);
            Position noisePosition = positionComponentMapper.get(entity);
            float soundMinFrequency = soundComponent.getCurrentSoundMinFrequency();
            float soundMaxFrequency = soundComponent.getCurrentSoundMaxFrequency();
            Ears ears = player.getEars();
            float minHearingFrequency = ears.getMinFrequency();
            float maxHearingFrequency = ears.getMaxFrequency();


            if(MathUtilities.overLaps(soundMinFrequency, soundMaxFrequency, minHearingFrequency, maxHearingFrequency)){

                playSound(soundComponent, noisePosition);
            }

            }

        }



        public void playSound(SoundComponent soundComponent, Position noisePosition) {

             float maxSoundDistance=player.getEars().getHearingDistance();


            float noiseLocationX = noisePosition.getScreenLocationX();
            float noiseLocationY = noisePosition.getScreenLocationY();
            float playerLocationX = playerPosition.getScreenLocationX();
            float playerLocationY = playerPosition.getScreenLocationY();

            float xDistance = Math.abs(noiseLocationX - playerLocationX);
            float yDistance = Math.abs(noiseLocationY - playerLocationY);
            if (yDistance <= maxSoundDistance || xDistance <= maxSoundDistance) {

                    float volume = soundComponent.getCurrentSoundDecibels() * (1 / Math.max(xDistance, yDistance));
                Sound sound=soundComponent.getCurrentSound();

                if(sound!=null) {



                    sound.play(volume);


                }


                }


        }


    public ZRPGPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ZRPGPlayer player) {
        this.player = player;
        playerPosition=player.getPosition();

    }
}