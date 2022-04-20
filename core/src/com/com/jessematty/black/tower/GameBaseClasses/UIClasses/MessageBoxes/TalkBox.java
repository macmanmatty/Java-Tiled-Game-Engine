package com.jessematty.black.tower.GameBaseClasses.UIClasses.MessageBoxes;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.Components.TalkComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;

public class TalkBox extends ClosableWindow {
    private SelectBox<String> participants;
    private String styleName;
    private TextField questionField;
    private TextButton askButton;
    private HorizontalGroup askQuestionGroup= new HorizontalGroup();
    private Array<Entity> talkers= new Array<>();
    private ScrollPane messages;
    private Table messagesTable= new  Table();


    private Array<Label> labels = new Array<>();
    private ComponentMapper<TalkComponent> talkComponentComponentMapper;
    private ComponentMapper<ImageComponent> imageComponentComponentMapper;
    private ComponentMapper<NameComponent> nameComponentMapper;

    public TalkBox(String title, Skin skin) {
      this(title, skin, "default");
    }

    public TalkBox(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
        this.imageComponentComponentMapper=GameComponentMapper.getImageComponentMapper();
        this.nameComponentMapper=GameComponentMapper.getNameComponentMapper();


    }


    public  void showWindow(){


    }


    public void setStyleName(String styleName){
        this.styleName=styleName;
        SelectBoxStyle selectBoxStyle=getSkin().get(styleName, SelectBoxStyle.class);
        if(selectBoxStyle!=null) {
            participants.setStyle(selectBoxStyle);
        }
        TextFieldStyle textFieldStyle=getSkin().get(styleName,TextFieldStyle.class);
        if(selectBoxStyle!=null) {
            questionField.setStyle(textFieldStyle);
        }
        ScrollPaneStyle scrollPaneStyle=getSkin().get(styleName, ScrollPaneStyle.class);

        if(scrollPaneStyle!=null) {
            messages.setStyle(scrollPaneStyle);
        }





    }

    public Array<Entity> getTalkers() {
        return talkers;
    }

    public void setTalkers(Array<Entity> talkers) {
        this.talkers = talkers;
    }

    public  void clear(){
        messages.clear();
        talkers.clear();
        clear();
        invalidateHierarchy();
    }

    public Array<Label> getLabels() {
        return labels;
    }
}
