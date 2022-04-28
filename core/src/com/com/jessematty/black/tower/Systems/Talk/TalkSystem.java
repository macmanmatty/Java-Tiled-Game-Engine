package com.jessematty.black.tower.Systems.Talk;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;


import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.Components.TalkComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.MessageBoxes.TalkBox;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Systems.Engine.EventSystem;

public class TalkSystem extends EventSystem { // talking fighter action that displays a speech bubble
  private ComponentMapper<TalkComponent> talkComponentMapper;

  private ZRPGCharacter player;

    public TalkSystem(MapDraw draw, ZRPGCharacter player) {
        super(draw);
        this.player = player;
    }


    @Override
    public void act(float deltaTime) {

        String mapId= player.getPosition().getMapId();

        GameMap map=getWorld().getMap( mapId);
        float voiceDistance=player.getNumericStats().getNumericStats().get("voiceProjection").getFloatValue();
         Array<Entity> entities= MapUtilities.getClosestEntities(map, player.getPosition(),  voiceDistance, TalkComponent.class );
        TalkBox talkBox= new TalkBox("Conversation", getDraw().getCurrentMap().getSkin());
        talkBox.setTalkers(entities);
         getDraw().getUiStage().addWindow(talkBox, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);




    }


    @Override
    public void addedToEngine(Engine engine) {
        talkComponentMapper= GameComponentMapper.getTalkComponentComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }


}
