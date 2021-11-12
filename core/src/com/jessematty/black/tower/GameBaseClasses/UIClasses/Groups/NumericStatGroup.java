package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.ActionTextField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.TextFieldOnEnterAction;

public class NumericStatGroup extends StatGroup  {
          private   NumericStat numericStat;
            protected  Label statLabel;
            private StatBar statBar;
            private FloatField statValue;
            private FloatField maxStat;
            private FloatField minStat;
            private boolean  intOnly;
            private boolean positiveOnly;
            private CheckBox displayMinAndMax;
            private CheckBox killWhenZero;
            
            
    public NumericStatGroup(Skin skin,  NumericStat numericStat, boolean editable) {
        super(numericStat, skin);
            this.numericStat= (NumericStat) super.stat;
       makeGroup();
    }
    
    public void makeGroup(){
        super.makeGroup();
        if(editable==false) {
            statLabel = new Label(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue(), skin);
            addActor(statLabel);
            statBar = numericStat.getStatBar();
            if (statBar != null) {
                addActor(statBar);
            }
        }
        
        else{
            statLabel = new Label(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue(), skin);
            maxStat=new FloatField(String.valueOf(numericStat.getMaxValue()), skin);
            maxStat.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
                @Override
                public void action() {
                    numericStat.setMaxValue(maxStat.getDouble());
                }
            });
            minStat=new FloatField(String.valueOf(numericStat.getMinValue()), skin);
            minStat.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
                @Override
                public void action() {
                    numericStat.setMinValue(minStat.getDouble());
                }
            });
            statValue =new FloatField(String.valueOf(numericStat.getDoubleValue()), skin);
            statValue.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
                @Override
                public void action() {
                    numericStat.setValue(statValue.getDouble());
                }
            });

            setTextFieldInputParams();
            NamedField maxField= new NamedField(maxStat, new Label("Max Value", skin));
            NamedField minField= new NamedField(maxStat, new Label("Min Value", skin));
            NamedField valueField= new NamedField(statValue, new Label("Value", skin));


            addActor(valueField);
            addActor(minField);
            addActor(maxField);
            statBar = numericStat.getStatBar();
            if (statBar != null) {
                addActor(statBar);
            }
            
        }
        
        
    }
    private void setTextFieldInputParams() {
        
        if(intOnly){
            if(positiveOnly){
                maxStat.setPositiveIntFilter();
                minStat.setPositiveIntFilter();
                statValue.setPositiveIntFilter();
                
            }
            else {
                maxStat.setIntFilter();
                minStat.setIntFilter();
                statValue.setIntFilter();
            }
            
           
            
        }
        
        else if(positiveOnly){
            maxStat.setPositiveFloatFilter();
            minStat.setPositiveFloatFilter();
            statValue.setPositiveFloatFilter();
            
            
        }
    }
    @Override
    public void act(float delta){
        super.act(delta);
        statLabel.setText(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue());
        if(statBar!=null) {
            statBar.update();
        }
            }
    public Label getStatLabel() {
        return statLabel;
    }
    public StatBar getStatBar() {
        return statBar;
    }

    public boolean isIntOnly() {
        return intOnly;
    }
    public void setIntOnly(boolean intOnly) {
        this.intOnly = intOnly;
        makeGroup();
    }
    public boolean isPositiveOnly() {
        return positiveOnly;
    }
    public void setPositiveOnly(boolean positiveOnly) {
        this.positiveOnly = positiveOnly;
        makeGroup();
    }
    
    public void setDefault(){
        intOnly=false;
        positiveOnly=false;
        makeGroup();
    }
}
