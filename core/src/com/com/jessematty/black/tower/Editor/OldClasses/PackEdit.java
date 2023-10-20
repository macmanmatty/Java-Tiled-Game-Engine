package com.jessematty.black.tower.Editor.OldClasses;

import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;

public class PackEdit extends ItemEdit {

    PackComponent packComponent;
    FloatField volume;




    public PackComponent makeThing(PackComponent item){

        super.makeThing(item);


        return item;
    }


    public void setTextFieldsToThing(PackComponent packComponent){
        this.packComponent = packComponent;



    }
}
