package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class AskBox {

    Window window;
    TextField textField;
    Button askQuestion;
    public Window makeAskBox(Skin skin, String style){
        if( style==null|| style.isEmpty()){
            style="default";

        }


        window= new Window("Ask a Question", skin ,style );
        Label label= new Label("Enter The Question", skin, style);
        textField= new TextField("", skin, style);
        askQuestion= new TextButton("Ask Question", skin, style);
        askQuestion.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                window.remove();

            }
        });

        window.add(label);
        window.add(textField);
        window.row();
        window.add(askQuestion);
        window.pack();
        window.validate();









        return window;

    }
    public String getText(){



        return textField.getText();

    }




}
