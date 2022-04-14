package com.jessematty.black.tower.Editor.OldClasses;

import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;

public class PackEdit extends ItemEdit {

    Pack pack;
    FloatField volume;




    public Pack makeThing(Pack item){

        super.makeThing(item);


        return item;
    }


    public void setTextFieldsToThing(Pack pack){
        this.pack=pack;



    }
}
