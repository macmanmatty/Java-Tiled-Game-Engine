package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import java.util.ArrayList;
import java.util.List;
public class ChangeImageOnStat {
    private  boolean changeAnimatable;
    private boolean setToMin;
    private  String imageName="";
    private String animatableName="";
    private String atlasName="";
    private boolean permanent;
    private List<NumericStat> numericStats = new ArrayList<>();
    private List<com.jessematty.black.tower.Components.Stats.BooleanStat> booleanStats = new ArrayList<>();
    private List<StringStat> stringStats = new ArrayList<>();
    public boolean isChangeAnimatable() {
        return changeAnimatable;
    }
    public boolean isSetToMin() {
        return setToMin;
    }
    public String getImageName() {
        return imageName;
    }
    public String getAnimatableName() {
        return animatableName;
    }
    public List<NumericStat> getNumericStats() {
        return numericStats;
    }
    public List<BooleanStat> getBooleanStats() {
        return booleanStats;
    }
    public List<StringStat> getStringStats() {
        return stringStats;
    }
    public void setChangeAnimatable(boolean changeAnimatable) {
        this.changeAnimatable = changeAnimatable;
    }
    public void setSetToMin(boolean setToMin) {
        this.setToMin = setToMin;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public void setAnimatableName(String animatableName) {
        this.animatableName = animatableName;
    }
    public boolean isPermanent() {
        return permanent;
    }
    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }
    public String getAtlasName() {
        return atlasName;
    }
    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }
}
