package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

public class QuestEdit extends InteractionEdit {

    boolean keepParts;



    TextField startQuestMessage; // the starting mission message
    TextField endQuestCompletedMessage; // the ending mission message
    TextField endQuestFailedMessage; // the failed mission  mission message

    ArrayList<HorizontalGroup> questMessages = new ArrayList<HorizontalGroup>();
    TextButton addQuest;
    HorizontalGroup messageGroup;
    TextField messageField;
    Label messageLabel;
    VerticalGroup questGroup;
    CheckBox showQuestGroup;
    





    public QuestEdit() {

        showQuestGroup=new CheckBox("Show Quest Fields To Edit", skin);

        showQuestGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    questGroup.setVisible(true);
                }

                else {
                    questGroup.setVisible(false);

                }

            }
        });
        startQuestMessage= new TextField("",skin);
        endQuestCompletedMessage= new TextField("",skin);
        endQuestFailedMessage= new TextField("",skin);
       HorizontalGroup startQuestGroup= new HorizontalGroup();
        Label startQuestLabel = new Label("start Quest Message",  skin);
        startQuestGroup.addActor(startQuestLabel);
        startQuestGroup.addActor(startQuestMessage);
        
        HorizontalGroup endQuestFailedGroup= new HorizontalGroup();
        Label endQuestFailedLabel = new Label("start Quest Message",  skin);
        endQuestFailedGroup.addActor(endQuestFailedLabel);
        endQuestFailedGroup.addActor(endQuestFailedMessage);

        HorizontalGroup endQuestCompletedGroup= new HorizontalGroup();
        Label endQuestCompletedLabel = new Label("start Quest Message",  skin);
        endQuestCompletedGroup.addActor(endQuestCompletedLabel);
        endQuestCompletedGroup.addActor(endQuestCompletedMessage);
        addQuest= new TextButton("Add QuestMessage", skin);
        addQuest.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                addMessage();



            }
        });



         messageGroup= new HorizontalGroup();
         messageLabel = new Label("start Quest Message #"+0,  skin);
        messageGroup.addActor(messageLabel);
        messageGroup.addActor(messageField);









    }

    protected  void addMessage(){

        messageGroup= new HorizontalGroup();
        messageLabel = new Label("start Quest Message #"+questMessages.size(),  skin);
        messageGroup.addActor(messageLabel);
        messageGroup.addActor(messageField);



    }







    }
