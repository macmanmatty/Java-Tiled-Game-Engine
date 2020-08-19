package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;


import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.TalkComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.MessageBoxes.TalkBox;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapUtilities;

public class TalkSystem extends EventSystem { // talking fighter action that displays a speech bubble
  private ComponentMapper<TalkComponent> talkComponentMapper;

  private ZRPGPlayer player;

    public TalkSystem(MapDraw draw, ZRPGPlayer player) {
        super(draw);
        this.player = player;
    }


    @Override
    public void act(float deltaTime) {

        int mapX=  player.getPosition().getMapWorldLocationX();
        int mapY=player.getPosition().getMapWorldLocationY();
        GameMap map=getWorld().getMap(mapX, mapY);
        float voiceDistance=player.getNumericStats().getNumericStats().get("voiceProjection").getFloatValue();
         Array<Entity> entities= MapUtilities.getClosestEntities(map, player.getPosition(),  voiceDistance, TalkComponent.class );
        TalkBox talkBox= new TalkBox("Conversation", getDraw().getCurrentMap().getSkin(), getGameComponentMapper());
        talkBox.setTalkers(entities);


         getDraw().addWindow(talkBox, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);




    }


    @Override
    public void addedToEngine(Engine engine) {
        talkComponentMapper=getGameComponentMapper().getTalkComponentComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }


}
