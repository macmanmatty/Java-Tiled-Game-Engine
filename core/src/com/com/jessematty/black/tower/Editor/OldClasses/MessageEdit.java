package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.Components.Other.Message;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class MessageEdit  extends ObjectEdit {


TextField message;
    SelectBox<String> groups;
    CheckBox conditional;
    


    VerticalGroup messageGRoup;
    VerticalGroup conditionalGroup;
    VerticalGroup fighterGroup;







    TextButton showFighters;
    HorizontalGroup mGroup;

    int messageNumber;




        TextField manClass;
    GameAssets assetts;
    Skin skin;
  Message fighterMessage;

    public MessageEdit(GameAssets assetts, final Skin skin, int messageNumber) {


       fighterMessage=new Message();
        this.messageNumber=messageNumber;
        conditionalGroup= new VerticalGroup();
        Label label2= new Label("Select A Condition to Attach To The Message", skin);
        conditionalGroup.addActor(label2);


        fighterGroup= new VerticalGroup();
        Label label3= new Label("Select A Condition to Attach To The Message", skin);
        fighterGroup.addActor(label3);

        conditional= new CheckBox("Is WoodWand condition Required to Display the Message", skin);
        conditional.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                CheckBox box= (CheckBox) event.getListenerActor();

                if(box.isChecked()==true){
                    conditionalGroup.setVisible(true);



                }

                else{

                    conditionalGroup.setVisible(false);
                }

            }
        });




        mGroup=new HorizontalGroup();

       message=new TextField("Message #"+messageNumber, skin);
       manClass=new TextField("Message #"+messageNumber +"manClass recipent", skin);

mGroup.addActor(message);
mGroup.addActor(fighterGroup);
mGroup.addActor(conditionalGroup);
        mGroup.addActor(manClass);


    }









    public HorizontalGroup getmGroup() {
        return mGroup;
    }

    public String getMessageText() {
        return message.getMessageText();

    }
}
