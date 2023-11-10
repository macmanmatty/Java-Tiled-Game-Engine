package com.jessematty.black.tower.Generators.Entity.LPCGenerator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatChangeable;
import com.jessematty.black.tower.Components.Stats.Stat;

import java.util.UUID;

public class LPCObjectGeneratorDTO {
    /**
     * whether or not to load the entity
     * into the game
     */
    boolean load=true;

    String atlasName;
    /**
     * the base name of the animation
     */
    String animatableBodyName ="humanMale";
    String sex="Male";
    /**
     * the name of the entity
     */
    String name="name";
    String info="info";
    /**
     * drawable color values
     */
    float brightness=1;
    float colorR=1;
    float colorG=1;
    float colorB=1;
    float colorA=1;
    /**
     * mass and volume
     */
    float mass;
    float volume;
    /**
     * the entities bounds if the bounding box is  a rectangle
     */
    float boundsX;
    float boundsY;
    /**
     * if using basic health these are the stats
     */
    float health;
    float maxHealth;
    
    boolean killWhenZeroHealth =true;
    float hardness;
    /**
     * what LPC animation frames the object has
     */
    
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
    /**
     * is the object animated
     */
    boolean animated;
    /**
     * is the object animated using the LPC
     * Actor sets
     */
    boolean lpcActorAnimated;
    /**
     * is object the drawable
     * aka has an image or is animated
     */
    boolean drawable;
    /**
     * does object have an image
     */
    boolean hasImage;
    /**
     * if the object is an Animated LPC asset
     * but has no corresponding image
     * whether or not to use the first
     * Image of the Down animation sequence
     * for the Items image
     */
    boolean useDownFrame1AsImage;
    /**
     * the name of the image in the texture atlas
     */
    String imageName;
    /**
     * whether or not to draw the item when the game starts
     */
    boolean drawOnStart=true;
    /**
     * what z layer to draw the item on
     */
    int drawLayer=1;
    /**
     * the entities max speed
     */
    float maxSpeed=100f;
    int hands=2;
    /**
     * used for body parts attached to an LPC human like Character
     */
    int shirtSize;
    int shoeSize;
    int pantSize;
    int gloveSize;
    /**
     * whether  the entity has the ability to move from the start
     * aka human, water, wind, dragon, ent
     */
    boolean moveable;
    Array<NumericStatChangeable> numericStatsChangeableList= new Array<>();
    Array<BooleanStatChangeable> booleanStatChangeableList = new Array<>();
    Array<StringStatChangeable> stringStatChangeableList = new Array<>();
    /**
     *Array  of all  the entities stats  String Numeric and Boolean
     */
    Array<Stat> stats= new Array();
    /**
     * the items price
     */
    float price;
    /**
     * the items condition
     */
    float condition;
    /**
     * if the item is a slotted pack 
     * the number of slots it has
     */
    int slots;
    /**
     * the max weight an Entity can have attached to it
     */
    float maxAtachedWeight;
    /**
     * if the Entity is hollow ie pack , hollow tree etc.
     * the internal volume of the entity
     */
    float internalVolume;
    /**
     * the max price an item will ever be worth
     */
    float maxPrice;
    /**
     *  the min price for an item
     */
    float minPrice;
    /**
     * is the Entity an item
     */
    boolean item;
    /**
     * is the entity readable ie Book , Trail Sign, Scroll, Wall painting etc.
     *
     */
    boolean readable;
    /**
     * is the item ingestable
     */
    boolean ingestable;
    /**
     * is the item drinkable
     */
    boolean drinkable;
    
    boolean slashable;
    boolean thrustable;
    boolean shootable;
    boolean holder;
    /**
     * part entity that will added to a
     * body component of an owner entity
     *
     */
    boolean part;
    /**
     * if this is part entity the class  (not JAVA Class) of the part
     */
    String partClass;
    /**
     *  the array of attachable  parts if the entity has a body
     */
    Array<String> attachableParts= new Array<>();

    /**
     * does this entity have body component;
     */
    boolean body;

    boolean wearable;
    /**
     * is the entity  a pack?
     */
    boolean pack;
    /**
     * is the entity a plant
     */
    boolean plant;
    /**
     * is the entity a human like character
     * ie 2 hands, 2 feet, noise, eyes , ears etc.
     * 
     */
    boolean humanLikeCharacter;
    /**
     * the id of the entity if placed in the object layer of a TMX map
     * not the same as entity id. But still must be unique.
     */
    String tmxObjectId=UUID.randomUUID().toString();
    /**
     * if not using a rectangular bounds
     * the polygon bounds of the entity
     * if rectangular bounds are entered 
     * setting this to a non null value will override them
     */
    Polygon bounds;
    /**
     * the groups the entity belongs to Ie weapon, food, goblin made item, iuyggy
     * pretty much any valild string
     * 
     * 
     */
    Array<String> groups= new Array<>();
  
    Array<String> groupsAddable= new Array<>();

    Array<String> attachedEntities= new Array();
    Array<LPCObjectGeneratorDTO> attachedEntityDTOs= new Array();

    private int upLayerOffset;
    private int downLayerOffset;

    private int leftLayerOffset;

    private int rightLayerOffset;


    public String getAtlasName() {
        return atlasName;
    }
    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }
    public String getAnimatableBodyName() {
        return animatableBodyName;
    }
    public void setAnimatableBodyName(String animatableBodyName) {
        this.animatableBodyName = animatableBodyName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
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
        return animated;
    }
    public void setAnimated(boolean animated) {
        this.animated = animated;
        this.drawable=true;
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
    
    
    public float getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
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
    
    public Array<String> getGroupsAddable() {
        return groupsAddable;
    }
    public void setGroupsAddable(Array<String> groupsAddable) {
        this.groupsAddable = groupsAddable;
    }
    public float getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
    public float getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }
    public boolean isItem() {
        return item;
    }
    public void setItem(boolean item) {
        this.item = item;
    }
    public boolean isReadable() {
        return readable;
    }
    public void setReadable(boolean readable) {
        this.readable = readable;
    }
    public boolean isIngestable() {
        return ingestable;
    }
    public void setIngestable(boolean ingestable) {
        this.ingestable = ingestable;
    }
    public boolean isDrinkable() {
        return drinkable;
    }
    public void setDrinkable(boolean drinkable) {
        this.drinkable = drinkable;
    }
    public boolean isSlashable() {
        return slashable;
    }
    public void setSlashable(boolean slashable) {
        this.slashable = slashable;
    }
    public boolean isThrustable() {
        return thrustable;
    }
    public void setThrustable(boolean thrustable) {
        this.thrustable = thrustable;
    }
    public boolean isShootable() {
        return shootable;
    }
    public void setShootable(boolean shootable) {
        this.shootable = shootable;
    }
    public boolean isPack() {
        return pack;
    }
    public void setPack(boolean pack) {
        this.pack = pack;
    }
    public boolean isWearable() {
        return wearable;
    }
    public void setWearable(boolean wearable) {
        this.wearable = wearable;
    }
    public boolean isPlant() {
        return plant;
    }
    public void setPlant(boolean plant) {
        this.plant = plant;
    }
    public boolean isHolder() {
        return holder;
    }
    public void setHolder(boolean holder) {
        this.holder = holder;
    }
    public boolean isPart() {
        return part;
    }
    public void setPart(boolean part) {
        this.part = part;
    }
    public boolean isLoad() {
        return load;
    }
    public void setLoad(boolean load) {
        this.load = load;
    }
    public boolean isHumanLikeCharacter() {
        return humanLikeCharacter;
    }
    public void setHumanLikeCharacter(boolean humanLikeCharacter) {
        this.humanLikeCharacter = humanLikeCharacter;
    }
    public String getTmxObjectId() {
        return tmxObjectId;
    }
    public void setTmxObjectId(String tmxObjectId) {
        this.tmxObjectId = tmxObjectId;
    }

    public Array<String> getAttachedEntities() {
        return attachedEntities;
    }

    public void setAttachedEntities(Array<String> attachedEntities) {
        this.attachedEntities = attachedEntities;
    }

    public Array<LPCObjectGeneratorDTO> getAttachedEntityDTOs() {
        return attachedEntityDTOs;
    }

    public void setAttachedEntityDTOs(Array<LPCObjectGeneratorDTO> attachedEntityDTOs) {
        this.attachedEntityDTOs = attachedEntityDTOs;
    }

    public boolean isLpcActorAnimated() {
        return lpcActorAnimated;
    }

    public void setLpcActorAnimated(boolean lpcActorAnimated) {
        this.lpcActorAnimated = lpcActorAnimated;
        this.animated =true;
        this.drawable=true;
    }

    public String getPartClass() {
        return partClass;
    }

    public void setPartClass(String partClass) {
        this.partClass = partClass;
    }

    public Array<String> getAttachableParts() {
        return attachableParts;
    }

    public void setAttachableParts(Array<String> attachableParts) {
        this.attachableParts = attachableParts;
    }

    public boolean isBody() {
        return body;
    }

    public void setBody(boolean body) {
        this.body = body;
    }

    public int getUpLayerOffset() {
        return upLayerOffset;
    }

    public void setUpLayerOffset(int upLayerOffset) {
        this.upLayerOffset = upLayerOffset;
    }

    public int getDownLayerOffset() {
        return downLayerOffset;
    }

    public void setDownLayerOffset(int downLayerOffset) {
        this.downLayerOffset = downLayerOffset;
    }

    public int getLeftLayerOffset() {
        return leftLayerOffset;
    }

    public void setLeftLayerOffset(int leftLayerOffset) {
        this.leftLayerOffset = leftLayerOffset;
    }

    public int getRightLayerOffset() {
        return rightLayerOffset;
    }

    public void setRightLayerOffset(int rightLayerOffset) {
        this.rightLayerOffset = rightLayerOffset;
    }

}
