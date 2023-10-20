package com.jessematty.black.tower.Generators.Entity.LPCGenerator;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LPCObjectGeneratorDTO {
    String atlasName;
    /**
     * the base name of the animation
     */
    String bodyName="humanMale";
    /**
     * the name of the entity
     */
    String name="name";
    String info="info";
    float brightness;
    float colorR=1;
    float colorG=1;
    float colorB=1;
    float colorA=1;
    float mass;
    float volume;
    float boundsX;
    float boundsY;
    float health;
    float maxHealth;
    float hardness;
    boolean killWhenZeroHealth =true;
    boolean hashSlashFrames=true;
    boolean overSizeSlash=true;
    boolean hasThrustFrames=true;
    boolean hasWalkFrames=true;
    boolean hasShootFrames=true;
    boolean hasDieFrames=true;
    boolean hasSpellCastFrames=true;
    boolean hasEatFrames=true;
    boolean hasPickupFrames=true;
    boolean hasThrowFrames=true;
    boolean isAnimated;
    boolean drawable;
    boolean hasImage;
    boolean useDownFrame1AsImage;
    String imageName;
    boolean drawOnStart=true;
    int drawLayer=1;
    float strength;
    float hearingDistance;
    float seeingDistance;
    float iq;
    float experience;
    float maxSpeed=100f;
    boolean flies;
    boolean swims;
    int hands=2;
    int shirtSize;
    int shoeSize;
    int pantSize;
    int gloveSize;
    boolean moveable;
    Array<NumericStatChangeable> numericStatsChangeableList= new Array<>();
    Array<BooleanStatChangeable> booleanStatChangeableList = new Array<>();
    Array<StringStatChangeable> stringStatChangeableList = new Array<>();
    Array<Stat> stats= new Array();
    float price;
    float condition;
    int slots;
    float maxAtachedWeight;
    float internalVolume;
    Polygon bounds;
    Array<String> groups= new Array<>();
    Array<String> groupsAddable= new Array<>();

    int packSlots;



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

    public float getColorR() {
        return colorR;
    }

    public void setColorR(float colorR) {
        this.colorR = colorR;
    }

    public float getColorG() {
        return colorG;
    }

    public void setColorG(float colorG) {
        this.colorG = colorG;
    }

    public float getColorB() {
        return colorB;
    }

    public void setColorB(float colorB) {
        this.colorB = colorB;
    }

    public float getColorA() {
        return colorA;
    }

    public void setColorA(float colorA) {
        this.colorA = colorA;
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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getHardness() {
        return hardness;
    }

    public void setHardness(float hardness) {
        this.hardness = hardness;
    }

    public boolean isKillWhenZeroHealth() {
        return killWhenZeroHealth;
    }

    public void setKillWhenZeroHealth(boolean killWhenZeroHealth) {
        this.killWhenZeroHealth = killWhenZeroHealth;
    }

    public boolean isHashSlashFrames() {
        return hashSlashFrames;
    }

    public void setHashSlashFrames(boolean hashSlashFrames) {
        this.hashSlashFrames = hashSlashFrames;
    }

    public boolean isOverSizeSlash() {
        return overSizeSlash;
    }

    public void setOverSizeSlash(boolean overSizeSlash) {
        this.overSizeSlash = overSizeSlash;
    }

    public boolean isHasThrustFrames() {
        return hasThrustFrames;
    }

    public void setHasThrustFrames(boolean hasThrustFrames) {
        this.hasThrustFrames = hasThrustFrames;
    }

    public boolean isHasWalkFrames() {
        return hasWalkFrames;
    }

    public void setHasWalkFrames(boolean hasWalkFrames) {
        this.hasWalkFrames = hasWalkFrames;
    }

    public boolean isHasShootFrames() {
        return hasShootFrames;
    }

    public void setHasShootFrames(boolean hasShootFrames) {
        this.hasShootFrames = hasShootFrames;
    }

    public boolean isHasDieFrames() {
        return hasDieFrames;
    }

    public void setHasDieFrames(boolean hasDieFrames) {
        this.hasDieFrames = hasDieFrames;
    }

    public boolean isHasSpellCastFrames() {
        return hasSpellCastFrames;
    }

    public void setHasSpellCastFrames(boolean hasSpellCastFrames) {
        this.hasSpellCastFrames = hasSpellCastFrames;
    }

    public boolean isHasEatFrames() {
        return hasEatFrames;
    }

    public void setHasEatFrames(boolean hasEatFrames) {
        this.hasEatFrames = hasEatFrames;
    }

    public boolean isHasPickupFrames() {
        return hasPickupFrames;
    }

    public void setHasPickupFrames(boolean hasPickupFrames) {
        this.hasPickupFrames = hasPickupFrames;
    }

    public boolean isHasThrowFrames() {
        return hasThrowFrames;
    }

    public void setHasThrowFrames(boolean hasThrowFrames) {
        this.hasThrowFrames = hasThrowFrames;
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public void setAnimated(boolean animated) {
        isAnimated = animated;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public boolean isUseDownFrame1AsImage() {
        return useDownFrame1AsImage;
    }

    public void setUseDownFrame1AsImage(boolean useDownFrame1AsImage) {
        this.useDownFrame1AsImage = useDownFrame1AsImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isDrawOnStart() {
        return drawOnStart;
    }

    public void setDrawOnStart(boolean drawOnStart) {
        this.drawOnStart = drawOnStart;
    }

    public int getDrawLayer() {
        return drawLayer;
    }

    public void setDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getHearingDistance() {
        return hearingDistance;
    }

    public void setHearingDistance(float hearingDistance) {
        this.hearingDistance = hearingDistance;
    }

    public float getSeeingDistance() {
        return seeingDistance;
    }

    public void setSeeingDistance(float seeingDistance) {
        this.seeingDistance = seeingDistance;
    }

    public float getIq() {
        return iq;
    }

    public void setIq(float iq) {
        this.iq = iq;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean isFlies() {
        return flies;
    }

    public void setFlies(boolean flies) {
        this.flies = flies;
    }

    public boolean isSwims() {
        return swims;
    }

    public void setSwims(boolean swims) {
        this.swims = swims;
    }

    public int getHands() {
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }

    public int getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(int shirtSize) {
        this.shirtSize = shirtSize;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public int getPantSize() {
        return pantSize;
    }

    public void setPantSize(int pantSize) {
        this.pantSize = pantSize;
    }

    public int getGloveSize() {
        return gloveSize;
    }

    public void setGloveSize(int gloveSize) {
        this.gloveSize = gloveSize;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public Array<NumericStatChangeable> getNumericStatsChangeableList() {
        return numericStatsChangeableList;
    }

    public void setNumericStatsChangeableList(Array<NumericStatChangeable> numericStatsChangeableList) {
        this.numericStatsChangeableList = numericStatsChangeableList;
    }

    public Array<BooleanStatChangeable> getBooleanStatChangeableList() {
        return booleanStatChangeableList;
    }

    public void setBooleanStatChangeableList(Array<BooleanStatChangeable> booleanStatChangeableList) {
        this.booleanStatChangeableList = booleanStatChangeableList;
    }

    public Array<StringStatChangeable> getStringStatChangeableList() {
        return stringStatChangeableList;
    }

    public void setStringStatChangeableList(Array<StringStatChangeable> stringStatChangeableList) {
        this.stringStatChangeableList = stringStatChangeableList;
    }

    public Array<Stat> getStats() {
        return stats;
    }

    public void setStats(Array<Stat> stats) {
        this.stats = stats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCondition() {
        return condition;
    }

    public void setCondition(float condition) {
        this.condition = condition;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public float getMaxAtachedWeight() {
        return maxAtachedWeight;
    }

    public void setMaxAtachedWeight(float maxAtachedWeight) {
        this.maxAtachedWeight = maxAtachedWeight;
    }

    public float getInternalVolume() {
        return internalVolume;
    }

    public void setInternalVolume(float internalVolume) {
        this.internalVolume = internalVolume;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

    public Array<String> getGroups() {
        return groups;
    }

    public void setGroups(Array<String> groups) {
        this.groups = groups;
    }

    public int getPackSlots() {
        return packSlots;
    }

    public void setPackSlots(int packSlots) {
        this.packSlots = packSlots;
    }

    public Array<String> getGroupsAddable() {
        return groupsAddable;
    }

    public void setGroupsAddable(Array<String> groupsAddable) {
        this.groupsAddable = groupsAddable;
    }
}
