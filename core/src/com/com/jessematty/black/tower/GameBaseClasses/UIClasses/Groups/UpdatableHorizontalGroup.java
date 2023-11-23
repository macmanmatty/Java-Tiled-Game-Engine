package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
public  abstract  class UpdatableHorizontalGroup extends HorizontalGroup  {
    protected String groupName="";
    protected Skin skin;
    public UpdatableHorizontalGroup(Skin skin) {
        this.skin = skin;
    }
    public UpdatableHorizontalGroup(String groupName, Skin skin) {
        this.groupName = groupName;
        this.skin = skin;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
