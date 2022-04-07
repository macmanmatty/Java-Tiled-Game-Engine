package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StatMode;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.BasicLists.AddRemoveList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.ActionTextField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;

public class StatGroup  extends UpdatableHorizontalGroup{

    boolean editable;
    private ActionTextField nameField;
    protected Stat stat;
    protected List<String> groups;
    protected StatMode statMode=StatMode.ENTITY;


    public StatGroup(  Stat stat, Skin skin) {
        super(skin);
        this.stat=stat;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    protected void makeGroup() {
        if(editable==true){
            nameField= new ActionTextField(stat.getName(), skin);
            NamedField namedField= new NamedField(new Label(" Stat Name: ", skin), nameField);
            AddRemoveList changeGroupsList= new AddRemoveList(skin, "Change Groups " , stat.getChangeGroups());
            AddRemoveList displayGroupsList= new AddRemoveList(skin,"Display Groups" , stat.getDisplayGroups());

            addActor(namedField);
            if(statMode==StatMode.ENTITY){
                addActor(changeGroupsList);
                addActor(displayGroupsList);
            }







        }


    }


}
