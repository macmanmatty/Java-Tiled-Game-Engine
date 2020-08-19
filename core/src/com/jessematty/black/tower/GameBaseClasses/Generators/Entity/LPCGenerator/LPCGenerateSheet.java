package com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.SelfChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableBooleanStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public class LPCGenerateSheet {

 private String atlasName="assets.atlas";
 private String bodyName="entity";
 private String name="entity";
 private String info="info:" ;
 private float brightness;
 private  NamedColor color;
 private float mass;
 private float volume;
 private float boundsX;
 private float boundsY;
 private NumericStat health= new NumericStat("health");
 private NumericStat strength= new NumericStat("strength");
 private NumericStat seeingDistance= new NumericStat("seeingDistance");
 private NumericStat hearingDistance= new NumericStat("hearingDistance");
 private NumericStat iq= new NumericStat("iq");
 private NumericStat experience= new NumericStat("experience");
 private NumericStat speed= new NumericStat("speed");
 private BooleanStat files= new BooleanStat("flies");
 private BooleanStat swims= new BooleanStat("swims");
 private Array<StringStat> stringStats=new Array<>();
 private Array<BooleanStat> booleanStats=new Array<>();
 private Array<NumericStat> numericStats=new Array<>();
 private Array<ChangableStringStat> changeableStringStats = new Array<>();
 private Array<ChangableBooleanStat> changeableBooleanStats = new Array<>();
 private Array<ChangableNumericStat> changeableNumericStats = new Array<>();
 private Array<SelfChangableNumericStat> selfChangableNumericStats= new Array<>();
 private Array<ColorChangingStat> colorChangingStats= new Array<>();

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public NamedColor getColor() {
        return color;
    }

    public void setColor(NamedColor color) {
        this.color = color;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getBoundsX() {
        return boundsX;
    }

    public void setBoundsX(float boundsX) {
        this.boundsX = boundsX;
    }

    public float getBoundsY() {
        return boundsY;
    }

    public void setBoundsY(float boundsY) {
        this.boundsY = boundsY;
    }

    public NumericStat getHealth() {
        return health;
    }

    public void setHealth(NumericStat health) {
        this.health = health;
    }

    public NumericStat getStrength() {
        return strength;
    }

    public void setStrength(NumericStat strength) {
        this.strength = strength;
    }

    public NumericStat getSeeingDistance() {
        return seeingDistance;
    }

    public void setSeeingDistance(NumericStat seeingDistance) {
        this.seeingDistance = seeingDistance;
    }

    public NumericStat getHearingDistance() {
        return hearingDistance;
    }

    public void setHearingDistance(NumericStat hearingDistance) {
        this.hearingDistance = hearingDistance;
    }

    public NumericStat getIq() {
        return iq;
    }

    public void setIq(NumericStat iq) {
        this.iq = iq;
    }

    public NumericStat getExperience() {
        return experience;
    }

    public void setExperience(NumericStat experience) {
        this.experience = experience;
    }

    public NumericStat getSpeed() {
        return speed;
    }

    public void setSpeed(NumericStat speed) {
        this.speed = speed;
    }

    public BooleanStat getFiles() {
        return files;
    }

    public void setFiles(BooleanStat files) {
        this.files = files;
    }

    public BooleanStat getPrivateswims() {
        return swims;
    }

    public void setPrivateswims(BooleanStat privateswims) {
        this.swims = privateswims;
    }

    public Array<StringStat> getStringStats() {
        return stringStats;
    }

    public void setStringStats(Array<StringStat> stringStats) {
        this.stringStats = stringStats;
    }

    public Array<BooleanStat> getBooleanStats() {
        return booleanStats;
    }

    public void setBooleanStats(Array<BooleanStat> booleanStats) {
        this.booleanStats = booleanStats;
    }

    public Array<NumericStat> getNumericStats() {
        return numericStats;
    }

    public void setNumericStats(Array<NumericStat> numericStats) {
        this.numericStats = numericStats;
    }

    public Array<ChangableStringStat> getChangeableStringStats() {
        return changeableStringStats;
    }

    public void setChangeableStringStats(Array<ChangableStringStat> changeableStringStats) {
        this.changeableStringStats = changeableStringStats;
    }

    public Array<ChangableBooleanStat> getChangeableBooleanStats() {
        return changeableBooleanStats;
    }

    public void setChangeableBooleanStats(Array<ChangableBooleanStat> changeableBooleanStats) {
        this.changeableBooleanStats = changeableBooleanStats;
    }

    public Array<ChangableNumericStat> getChangeableNumericStats() {
        return changeableNumericStats;
    }

    public void setChangeableNumericStats(Array<ChangableNumericStat> changeableNumericStats) {
        this.changeableNumericStats = changeableNumericStats;
    }

    public Array<SelfChangableNumericStat> getSelfChangableNumericStats() {
        return selfChangableNumericStats;
    }

    public void setSelfChangableNumericStats(Array<SelfChangableNumericStat> selfChangableNumericStats) {
        this.selfChangableNumericStats = selfChangableNumericStats;
    }

    public Array<ColorChangingStat> getColorChangingStats() {
        return colorChangingStats;
    }

    public void setColorChangingStats(Array<ColorChangingStat> colorChangingStats) {
        this.colorChangingStats = colorChangingStats;
    }
}
