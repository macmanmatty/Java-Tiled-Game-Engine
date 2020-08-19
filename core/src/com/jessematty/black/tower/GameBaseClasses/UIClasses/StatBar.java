package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

import java.io.Serializable;

public class StatBar extends Actor implements Serializable {
   private transient  AtlasRegion[] regions;
   private  int [] numbers;

    private NumericStat stat;
  private   int parts=7;
  private transient GameAssets assetts;

    public StatBar() {
    }

    public StatBar(StatBar other) {
        this.regions = other.regions;
        this.numbers = other.numbers;
        this.stat = other.stat;
        this.parts = other.parts;
        this.assetts = other.assetts;
    }

    public StatBar(NumericStat stat, GameAssets assetts) {

    this.assetts=assetts;
        numbers= new int [parts];
        regions= new AtlasRegion[parts];
        this.stat=stat;

        int numberOfParts=(int)(stat.getMaxValue()/parts);


        for(int count=1; count<=parts; count++)        {
           numbers[count-1]=parts*numberOfParts;
           regions[count-1]=assetts.getAtlasRegionByName("health"+count, "assetts.atlas");





        }




    }



public void update(){


        setMax(stat.getMaxIntValue());

}


    public AtlasRegion getRegion(){


            double stat=this.stat.getDoubleValue();




        if (stat >=numbers[6]&& stat >numbers[5]){

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


    public void setMax(int maxStat) {
        int numberOfParts=(int)(maxStat/parts);


        for(int count=1; count<=parts; count++)        {
            numbers[count-1]=numberOfParts*count;





        }



    }



    public void setAssetts(GameAssets assetts) {
        this.assetts = assetts;
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
