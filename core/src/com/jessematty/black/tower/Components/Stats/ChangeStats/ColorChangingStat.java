package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;

/**
 * replace with better system
 */
public   class ColorChangingStat extends NumericStat {
   private float colorMultiplier;
   private boolean affectsRed;
   private boolean affectsGreen;
   private boolean affectsBlue;
   private boolean affectsAlpha;
   private float greenValue;
   private float redValue;
   private float blueValue;
   private float alphaValue;
   private float brightnessValue;
    public ColorChangingStat(boolean displayable, String name, double value) {
        super(displayable, name, value);
    }
    public ColorChangingStat(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }
    public ColorChangingStat(boolean displayable, String name, double value, double minValue, double maxValue, float colorMultiplier) {
        super(displayable, name, value, minValue, maxValue);
        this.colorMultiplier=colorMultiplier;
    }
    public ColorChangingStat() {
    }
    public ColorChangingStat(NumericStat other) {
        super(other);
    }

    public ColorChangingStat(ColorChangingStat other) {
        super(other);
        this.colorMultiplier = other.colorMultiplier;
        this.affectsRed = other.affectsRed;
        this.affectsGreen = other.affectsGreen;
        this.affectsBlue = other.affectsBlue;
        this.affectsAlpha = other.affectsAlpha;
        this.greenValue = other.greenValue;
        this.redValue = other.redValue;
        this.blueValue = other.blueValue;
        this.alphaValue = other.alphaValue;
        this.brightnessValue = other.brightnessValue;
    }

    @Override
    public Stat makeCopy() {
        return new ColorChangingStat(this);
    }
    public float getColorMultiplier() {
        return colorMultiplier;
    }
    public void setColorMultiplier(float colorMultiplier) {
        this.colorMultiplier = colorMultiplier;
        calculateColor();
    }
    public void setValue(double value) {
        if(value>maxValue && maxValue>0){
            value=maxValue;
        }
        else if(value<minValue){
            value=minValue;
        }
        this.value = value;
        calculateColor();
    }
    public void addValues( double value, double min, double max){
        this.value=this.value+value;
        minValue=minValue+min;
        maxValue=maxValue+max;
        if(statBar!=null){
            statBar.update();
        }
        calculateColor();
    }
    public void addValues( ColorChangingStat stat){
        super.addValues(stat);
        if(statBar!=null){
            statBar.update();
        }
        calculateColor();
    }
    public  void addValuesRandom(NumericStat numericStat, float multiplier){
        double value = RandomNumbers.getRandomNumber(getMinIntValue(),getMaxIntValue() )*multiplier;
        numericStat.addValues(value, 0, 0);
        calculateColor();
    }
    public  void subtractValuesRandom(NumericStat numericStat, float multiplier){
        double value =RandomNumbers.getRandomNumber(getMinIntValue(),getMaxIntValue() )*multiplier;
        numericStat.addValues(-value, 0, 0);
        calculateColor();
    }
    @Override
    public String toString() {
        if(displayMinAndMax =true) {
            return name + ": " + value + " Min: " + minValue + "Max: " + maxValue;
        }
        else{
            return name + ": " + value;
        }
    }
    private void calculateColor(){
        redValue= (float) (value*colorMultiplier);
        blueValue= (float) (value*colorMultiplier);
        greenValue= (float) (value*colorMultiplier);
        alphaValue= (float) (value*colorMultiplier);
        brightnessValue= (float) (value*colorMultiplier);
    }
    public boolean isAffectsRed() {
        return affectsRed;
    }
    public boolean isAffectsGreen() {
        return affectsGreen;
    }
    public boolean isAffectsBlue() {
        return affectsBlue;
    }
    public boolean isAffectsAlpha() {
        return affectsAlpha;
    }
    public void setAffectsRed(boolean affectsRed) {
        this.affectsRed = affectsRed;
    }
    public void setAffectsGreen(boolean affectsGreen) {
        this.affectsGreen = affectsGreen;
    }
    public void setAffectsBlue(boolean affectsBlue) {
        this.affectsBlue = affectsBlue;
    }
    public void setAffectsAlpha(boolean affectsAlpha) {
        this.affectsAlpha = affectsAlpha;
    }
    public float getGreenValue() {
        return greenValue;
    }
    public float getRedValue() {
        return redValue;
    }
    public float getBlueValue() {
        return blueValue;
    }
    public float getAlphaValue() {
        return alphaValue;
    }
    public float getBrightnessValue() {
        return brightnessValue;
    }
}
