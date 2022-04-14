package com.jessematty.black.tower.GameBaseClasses.Player.RPGPlayer;

import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Player;
import com.jessematty.black.tower.GameBaseClasses.Player.PlayerFunction.PlayerFunction;

public class PlayerFunctions {

   private  ObjectMap<Integer, PlayerFunction> playerFunctionObjectMap= new ObjectMap<>();

   private Player player;



    public void function(int key){
        playerFunctionObjectMap.get(key).act(player);

    }

}
