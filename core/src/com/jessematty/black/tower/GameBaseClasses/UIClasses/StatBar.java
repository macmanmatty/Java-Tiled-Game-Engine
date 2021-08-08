package com.jessematty.black.tower.GameBaseClasses.UIClasses;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import java.io.Serializable;
public class StatBar extends Widget implements Serializable { 
  private transient  AtlasRegion[] regions;
  private  int [] numbers;
    /** the stat to used fo the bar to generate the images */
  private NumericStat stat;
  private   int parts=7;
    /** the games assets class used  to get the texture regions */

    private transient GameAssets assets;
    /** the current stat bar image */
    private TextureRegion currentTexture;
    /** the min max  and  current stat bar values */
    private double statValue;
  private double statMinValue;
  private double statMaxValue;
    /** whether or not to display the state bar */
    boolean display=true;
    public StatBar(StatBar other) {
        this.regions = other.regions;
        this.numbers = other.numbers;
        this.stat = other.stat;
        this.parts = other.parts;
        this.assets = other.assets;
        statValue=other.statValue;
    }
    public StatBar(NumericStat stat, GameAssets assets) {
    this.assets = assets;
        numbers= new int [parts];
        regions= new AtlasRegion[parts];
        this.stat=stat;
        this.statValue=stat.getDoubleValue();
        this.statMaxValue =stat.getMaxValue();
        this.statMinValue =stat.getMinValue();
        calculateParts();
        currentTexture=getRegion(statValue);
    }
    private void calculateParts(){
        int numberOfParts=(int)(stat.getMaxValue()/parts);
        for(int count=1; count<=parts; count++)        {
            numbers[count-1]=parts*numberOfParts;
            AtlasRegion region= assets.getAtlasRegionByName("statBar"+count, "assets");
            if(region!=null) {
                regions[count - 1] = region;
            }
            else{
                display=false;
                return;
            }
        }
    }

    /**
     *  updates  the state bar image based on the  numeric stat
     */
    public void update(){
        setMax(stat.getMaxIntValue());
        double currentStatValue=stat.getDoubleValue();
        double currentStatMaxValue=stat.getMaxValue();
    double currentStatMinValue=stat.getMinValue();
    if(currentStatMaxValue!=statMaxValue || statMinValue!=currentStatMinValue){
            calculateParts();
            statMinValue=currentStatMinValue;
            statMaxValue=currentStatMaxValue;
        }
        if(currentStatValue!=statValue) {
            statValue=currentStatValue;
            currentTexture = getRegion(statValue);
        }
}

    /**
     *  return the approiate image based on teh value of the passed in stat
     * @param stat the stat to check
     * @return
     */



    public AtlasRegion getRegion(double stat){
        if (stat >=numbers[6]){
            return regions[6];
        }
        if (stat <=numbers[5]&& stat >numbers[4]){
            return regions[5];
        }
        if (stat <=numbers[4]&& stat >numbers[3]){
            return regions[4];
        }
        if (stat <=numbers[3]&& stat >numbers[2]){
            return regions[3];
        }
        if (stat <=numbers[2]&& stat >numbers[1]){
            return regions[2];
        }
        if (stat <=numbers[1]&& stat >numbers[0]){
            return regions[1];
        }
        return regions[0];
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentTexture, getX(), getY());
    }
    public void setMax(int maxStat) {
        int numberOfParts=(int)(maxStat/parts);
        for(int count=1; count<=parts; count++)        {
            numbers[count-1]=numberOfParts*count;
        }

    }
    public void setAssets(GameAssets assets) {
        this.assets = assets;
    }
    public NumericStat getStat() {
        return stat;
    }
    public void setStat(NumericStat stat) {
        this.stat = stat;
    }
    public int getParts() {
        return parts;
    }
    public void setParts(int parts) {
        this.parts = parts;
    }
}
