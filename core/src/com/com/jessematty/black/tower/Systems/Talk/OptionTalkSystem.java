package com.jessematty.black.tower.Systems.Talk;


import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

import java.util.ArrayList;

public class OptionTalkSystem extends TalkSystem { // a class that display a  message with clickable options that perform thingsToActions



    ArrayList<String> options; // the options to choose from
    int optionNumber; // the selected option if no is slected it equals -1;


    public OptionTalkSystem(MapDraw draw, ZRPGCharacter player) {
        super(draw, player);
    }

    /*
    public boolean  unact(float gameTime){

        optionNumber=messageDisplay.getOptionNumber();
        if(optionNumber!=-1){
            speechBubble.removeEntity();
            actions.get(optionNumber).act();
            return true;




        }

        return false;

    }


    public void unact(){

        speechBubble.removeEntity();

    }


    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }


    public void addAction(Action action){

        this.actions.addEntity(action);

    }
    public void addOption(String option){


        options.addEntity(option);

    }


*/


}
