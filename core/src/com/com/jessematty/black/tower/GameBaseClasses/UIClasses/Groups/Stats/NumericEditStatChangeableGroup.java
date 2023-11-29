package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.Stats;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;

public class NumericEditStatChangeableGroup extends NumericEditStatEditGroup {
    protected   int amountOfTimeToChangeFor; // how long the change stays for 0 or less means perminante  change
    protected Array<String> actionsToChangeOn = new Array<String>(); // what actions will case this change to applied it touching, ingesting, penetrating
    protected boolean randomChange; // if this flag is the change will occur ramdomly between the min and min and max values for this stat
    private NumericStatChangeable numericStatChangeable;
    public NumericEditStatChangeableGroup(Skin skin, NumericStatChangeable numericStat, boolean editable) {
        super(skin, numericStat, editable);
        this.numericStatChangeable=numericStat;
    }


    @Override
    public void makeGroup() {

        super.makeGroup();

        if(editable==false) {
            statLabel .setText(" " +numericStatChangeable.getName() + ": " + numericStatChangeable.getDoubleValue() + "/" +"Time: "+numericStatChangeable.getAmountOfTimeToChangeFor());

        }
        if(editable==true){


        }


    }
}
