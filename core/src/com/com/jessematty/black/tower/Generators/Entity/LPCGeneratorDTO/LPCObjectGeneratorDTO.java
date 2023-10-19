package com.jessematty.black.tower.Generators.Entity.LPCGeneratorDTO;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.Stat;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LPCObjectGeneratorDTO {
    String atlasName;
    String bodyName="humanMale";
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
    List<Stat> stats= new ArrayList<>();
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
    boolean drawOnStart;
    int drawLayer;
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
    float price;
    float condition;
    int slots;
    float maxAtachedWeight;
    float internalVolume;




}
