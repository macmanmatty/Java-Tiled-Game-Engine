package com.jessematty.black.tower.Systems.Sound;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.audio.Sound;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.PlaySound;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.SoundComponent;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;


public  class PlaySoundSystem extends GameEntitySystem {
    private ComponentMapper<SoundComponent> soundComponentComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
   private  ImmutableArray<Entity> entities;
    private ZRPGCharacter player;
    private PositionComponent playerPosition;




    public PlaySoundSystem(MapDraw draw, int priorty) {
        super(priorty, draw);
        player=draw.getPlayer();



    }

    @Override
    public void addedToEngine(Engine engine) {

        soundComponentComponentMapper = GameComponentMapper.getSoundComponentComponentMapper();
        positionComponentMapper =GameComponentMapper.getPositionComponentMapper();




    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        entities=getEngine().getEntitiesFor(Family.all( PlaySound.class ,  SoundComponent.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {

            Entity entity = entities.get(count);
            SoundComponent soundComponent = soundComponentComponentMapper.get(entity);
            PositionComponent noisePosition = positionComponentMapper.get(entity);
            float soundMinFrequency = soundComponent.getCurrentSoundMinFrequency();
            float soundMaxFrequency = soundComponent.getCurrentSoundMaxFrequency();
            NumericStats numericStats=player.getNumericStats();
            float minHearingFrequency =numericStats.getNumericStat("minHearingFrequency").getFloatValue();
            float maxHearingFrequency = numericStats.getNumericStat("maxHearingFrequency").getFloatValue();


            if(MathUtilities.overLaps(soundMinFrequency, soundMaxFrequency, minHearingFrequency, maxHearingFrequency)){

                playSound(soundComponent, noisePosition);
            }

            }

        }



        public void playSound(SoundComponent soundComponent, PositionComponent noisePosition) {

             float maxSoundDistance=player.getNumericStats().getNumericStat("hearingDistance").getFloatValue();


            float noiseLocationX = noisePosition.getLocationX();
            float noiseLocationY = noisePosition.getLocationY();
            float playerLocationX = playerPosition.getLocationX();
            float playerLocationY = playerPosition.getLocationY();

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


    public ZRPGCharacter getPlayer() {
        return player;
    }

    public void setPlayer(ZRPGCharacter player) {
        this.player = player;
        playerPosition=player.getPosition();

    }
}