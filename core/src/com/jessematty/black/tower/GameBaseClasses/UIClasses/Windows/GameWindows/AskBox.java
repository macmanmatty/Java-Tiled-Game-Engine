package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;

public class AskBox extends ClosableWindow {

    TextField textField;
    Button askQuestion;

    public AskBox(String title, Skin skin, String styleName) {
        super(title, skin, styleName, true, false, true);
        makeAskBox(skin, styleName);
    }

    public void makeAskBox(Skin skin, String style){
        if( style==null|| style.isEmpty()){
            style="default";

        }


        Label label= new Label("Enter The Question", skin, style);
        textField= new TextField("", skin, style);
        askQuestion= new TextButton("Ask Question", skin, style);
        askQuestion.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {


            }
        });

     add(label);
        add(textField);
        row();
        add(askQuestion);
        pack();
        validate();


    }
    public String getText(){



        return textField.getText();

    }




}
