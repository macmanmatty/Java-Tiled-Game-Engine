package com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
public class LPCSpriteGenerator {
    private AnimatableComponent animatable;
  private  GameAssets gameAssets;
public   String atlasName;
public   String bodyName;
public   boolean hasWalkFrames;
public   boolean hasSlashFrames;
public   boolean hasThrustFrames;
public   boolean hasEatFrames;
public   boolean hasSpellCastFrames;
public   boolean hasShootFrames;
public   boolean hasPickUpFrames;
public   boolean hasDropFrames;
public   boolean hasOpenFrames;
public   boolean hasDieFrames;
public   boolean hasGrabFrames;
public   boolean hasThrowFrames;
public   boolean hasTalkFrames;
public   Vector2 walkUpOffsets=new Vector2();
public   Vector2 walkDownOffsets= new Vector2();
public   Vector2 walkLeftOffsets= new Vector2();
public   Vector2 walkRightOffsets= new Vector2();
public   int walkUpLayerNumber=3;
public   int walkDownLayerNumber=3;
public   int  walkLeftLayerNumber=3;
public   int  walkRightLayerNumber=3;
public   int downLayerNumberOffset;
public   int upLayerNumberOffset;
public   int leftLayerNumberOffset;
public   int rightLayerNumberOffset;
public   Vector2 thrustUpOffsets=new Vector2();
public   Vector2 thrustDownOffsets= new Vector2();
public   Vector2 thrustLeftOffsets= new Vector2();
public   Vector2 thrustRightOffsets= new Vector2();
public   int thrustUpLayerNumber=3;
public   int thrustDownLayerNumber=3;
public   int  thrustLeftLayerNumber=3;
public   int  thrustRightLayerNumber=3;
public   Vector2 slashUpOffsets=new Vector2();
public   Vector2 slashDownOffsets= new Vector2();
public   Vector2 slashLeftOffsets= new Vector2();
public   Vector2 slashRightOffsets= new Vector2();
public   int slashUpLayerNumber=3;
public   int slashDownLayerNumber=3;
public   int  slashLeftLayerNumber=3;
public   int  slashRightLayerNumber=3;
public   Vector2 eatUpOffsets=new Vector2();
public   Vector2 eatDownOffsets= new Vector2();
public   Vector2 eatLeftOffsets= new Vector2();
public   Vector2 eatRightOffsets= new Vector2();
public   int eatUpLayerNumber=3;
public   int eatDownLayerNumber=3;
public   int  eatLeftLayerNumber=3;
public   int  eatRightLayerNumber=3;
public   Vector2 spellCastUpOffsets=new Vector2();
public   Vector2 spellCastDownOffsets= new Vector2();
public   Vector2 spellCastLeftOffsets= new Vector2();
public   Vector2 spellCastRightOffsets= new Vector2();
public   int spellCastUpLayerNumber=3;
public   int spellCastDownLayerNumber=3;
public   int  spellCastLeftLayerNumber=3;
public   int  spellCastRightLayerNumber=3;
public   Vector2 shootUpOffsets=new Vector2();
public   Vector2 shootDownOffsets= new Vector2();
public   Vector2 shootLeftOffsets= new Vector2();
public   Vector2 shootRightOffsets= new Vector2();
public   int shootUpLayerNumber=3;
public   int shootDownLayerNumber=3;
public   int  shootLeftLayerNumber=3;
public   int  shootRightLayerNumber=3;
public   Vector2 grabUpOffsets=new Vector2();
public   Vector2 grabDownOffsets= new Vector2();
public   Vector2 grabLeftOffsets= new Vector2();
public   Vector2 grabRightOffsets= new Vector2();
public   int grabUpLayerNumber=3;
public   int grabDownLayerNumber=3;
public   int  grabLeftLayerNumber=3;
public   int  grabRightLayerNumber=3;
public   Vector2 dieUpOffsets=new Vector2();
public   Vector2 dieDownOffsets= new Vector2();
public   Vector2 dieLeftOffsets= new Vector2();
public   Vector2 dieRightOffsets= new Vector2();
public   int dieUpLayerNumber=3;
public   int dieDownLayerNumber=3;
public   int  dieLeftLayerNumber=3;
public   int  dieRightLayerNumber=3;
public   Vector2 openUpOffsets=new Vector2();
public   Vector2 openDownOffsets= new Vector2();
public   Vector2 openLeftOffsets= new Vector2();
public   Vector2 openRightOffsets= new Vector2();
public   int openUpLayerNumber=3;
public   int openDownLayerNumber=3;
public   int  openLeftLayerNumber=3;
public   int  openRightLayerNumber=3;
public   Vector2 talkUpOffsets=new Vector2();
public   Vector2 talkDownOffsets= new Vector2();
public   Vector2 talkLeftOffsets= new Vector2();
public   Vector2 talkRightOffsets= new Vector2();
public   int talkUpLayerNumber=3;
public   int talkDownLayerNumber=3;
public   int  talkLeftLayerNumber=3;
public   int  talkRightLayerNumber=3;
public   Vector2 restUpOffsets=new Vector2();
public   Vector2 restDownOffsets= new Vector2();
public   Vector2 restLeftOffsets= new Vector2();
public   Vector2 restRightOffsets= new Vector2();
public   int restUpLayerNumber=3;
public   int restDownLayerNumber=3;
public   int  restLeftLayerNumber=3;
public   int  restRightLayerNumber=3;
public   Vector2 dropUpOffsets=new Vector2();
public   Vector2 dropDownOffsets= new Vector2();
public   Vector2 dropLeftOffsets= new Vector2();
public   Vector2 dropRightOffsets= new Vector2();
public   int dropUpLayerNumber=3;
public   int dropDownLayerNumber=3;
public   int  dropLeftLayerNumber=3;
public   int  dropRightLayerNumber=3;
public   Vector2 throwUpOffsets=new Vector2();
public   Vector2 throwDownOffsets= new Vector2();
public   Vector2 throwLeftOffsets= new Vector2();
public   Vector2 throwRightOffsets= new Vector2();
public   int throwUpLayerNumber=3;
public   int throwDownLayerNumber=3;
public   int  throwLeftLayerNumber=3;
public   int  throwRightLayerNumber=3;
public   Vector2 pickUpUpOffsets=new Vector2();
public   Vector2 pickUpDownOffsets= new Vector2();
public   Vector2 pickUpLeftOffsets= new Vector2();
public   Vector2 pickUpRightOffsets= new Vector2();
public   int pickUpUpLayerNumber=3;
public   int pickUpDownLayerNumber=3;
public   int  pickUpLeftLayerNumber=3;
public   int  pickUpRightLayerNumber=3;
public  int walkUpFrames=9;
public  int walkDownFrames = 9;
public  int walkLeftFrames = 9;
public  int walkRightFrames = 9;
public  int dropUpFrames = 9;
public  int dropDownFrames = 9;
public  int dropLeftFrames = 9;
public  int dropRightFrames = 9;
public  int spellCastUpFrames = 7;
public  int spellCastDownFrames = 7;
public  int spellCastLeftFrames = 7;
public  int spellCastRightFrames = 7;
public  int thrustUpFrames = 8;
public  int thrustDownFrames = 8;
public  int thrustLeftFrames = 8;
public  int thrustRightFrames = 8;
public  int slashUpFrames = 6;
public  int slashDownFrames = 6;
public  int slashLeftFrames = 6;
public  int slashRightFrames = 6;
public  int openUpFrames = 7;
public  int openDownFrames = 7;
public  int openLeftFrames = 7;
public  int openRightFrames = 7;
public  int shootUpFrames = 13;
public  int shootDownFrames = 13;
public  int shootLeftFrames = 13;
public  int shootRightFrames = 13;
public  int pickUpUpFrames = 5;
public  int pickUpDownFrames = 5;
public  int pickUpLeftFrames = 5;
public  int pickUpRightFrames = 5;
public  int eatUpFrames = 3;
public  int eatDownFrames = 3;
public  int eatLeftFrames = 3;
public  int eatRightFrames = 3;
public  int throwUpFrames = 9;
public  int throwDownFrames = 7;
public  int throwLeftFrames = 7;
public  int throwRightFrames = 7;
public  int grabUpFrames = 9;
public  int grabDownFrames = 7;
public  int grabLeftFrames = 7;
public  int grabRightFrames = 7;
public  int dieUpFrames = 6;
public  int dieDownFrames = 6;
public  int dieLeftFrames = 6;
public  int dieRightFrames = 6;
public  int walkUpLeftFrames = 8;
public  int walkDownLeftFrames = 8;
public  int walkDownRightFrames = 8;
public  int walkUpRightFrames = 8;
public  int eatUpLeftFrames = 8;
public  int eatDownLeftFrames = 8;
public  int eatDownRightFrames = 8;
public  int eatUpRightFrames = 8;
public  int drinkUpLeftFrames = 8;
public  int drinkDownLeftFrames = 8;
public  int drinkDownRightFrames = 8;
public  int drinkUpRightFrames = 8;
public  int shootUpLeftFrames = 8;
public  int shootDownLeftFrames = 8;
public  int shootDownRightFrames = 8;
public  int shootUpRightFrames = 8;
public  int slashUpLeftFrames = 8;
public  int slashDownLeftFrames = 8;
public  int slashDownRightFrames = 8;
public  int slashUpRightFrames = 8;
public  int thrustUpLeftFrames = 8;
public  int thrustDownLeftFrames = 8;
public  int thrustDownRightFrames = 8;
public  int thrustUpRightFrames = 8;
public  int spellCastUpLeftFrames = 8;
public  int spellCastDownLeftFrames = 8;
public  int spellCastDownRightFrames = 8;
public  int spellCastUpRightFrames = 8;
public  int openUpLeftFrames = 8;
public  int openDownLeftFrames = 8;
public  int openDownRightFrames = 8;
public  int openUpRightFrames = 8;
public  int grabUpLeftFrames = 8;
public  int grabDownLeftFrames = 8;
public  int grabDownRightFrames = 8;
public  int grabUpRightFrames = 8;
public  int dropUpLeftFrames = 8;
public  int dropDownLeftFrames = 8;
public  int dropDownRightFrames = 8;
public  int dropUpRightFrames = 8;
public  int pickUpUpLeftFrames = 8;
public  int pickUpDownLeftFrames = 8;
public  int pickUpDownRightFrames = 8;
public  int pickUpUpRightFrames = 8;
public  int dieUpLeftFrames = 8;
public  int dieDownLeftFrames = 8;
public  int dieDownRightFrames = 8;
public  int dieUpRightFrames = 8;
public  int rideHorseUpLeftFrames = 8;
public  int rideHorseDownLeftFrames = 8;
public  int rideHorseDownRightFrames = 8;
public  int rideHorseUpRightFrames = 8;
public  int talkUpLeftFrames = 8;
public  int talkDownLeftFrames = 8;
public  int talkDownRightFrames = 8;
public  int talkUpRightFrames = 8;
public  int talkLeftFrames = 1;
public  int talkDownrames = 1;
public  int talkRightFrames = 1;
public  int talkUpFrames = 1;
public  int addItemTPackFramesUp = 1;
public  int addItemTPackFramesDown = 1;
public  int addItemTPackFramesLeft = 1;
public  int addItemTPackFramesRight = 1;
public  int craftFramesUp = 1;
public  int craftFramesDown = 1;
public  int craftFramesLeft = 1;
public  int craftFramesRight = 1;
public  int eatFrameRate = 5;
public  int slashFrameRate = 10;
public  int slashFrameRateWand;
public  int thrustFrameRate = 5;
public  int shootFrameRate = 5;
public  int walkFrameRate = 5;
public  int drinkFrameRate = 5;
public  int spellCastFrameRate = 5;
public  int dieFrameRate = 20;
public  int grabFrameRate = 5;
public  int throwFrameRate = 5;
public  int wandFrameRate = 1;
public  int digFrameRate = 5;
public  int openFrameRate = 5;
public  int pickUpFrameRate = 5;
public  int dropFrameRate = 5;
public  int useFrameRate = 5;
public  int addItemToPackFrameRate = 5;
public  int talkFrameRate = 5;
public  int craftFrameRate = 5;
public  int unlockFrameRate = 5;
    public LPCSpriteGenerator(AnimatableComponent animatable, GameAssets gameAssets, String atlasName, String bodyName) {
        this.animatable = animatable;
        this.gameAssets = gameAssets;
        this.atlasName = atlasName;
        this.bodyName = bodyName;
    }
    public void makeBody () {
        boolean slashForThrow = true;// will the slash frames  be using fro throwing items too?
        boolean dieForPickup = true;// will the dieing frames be used for throwing too?
        boolean dieDownOnly = true; // is the dieing animatuions only in the down direction;
        boolean grabForUnlock = true;
        boolean thrustForGrab = true;
        boolean dieForDrop = true;
        AtlasNamedAtlasRegion[] walkUpAnamation;
        AtlasNamedAtlasRegion[] walkDownAnamation;
        AtlasNamedAtlasRegion[] walkRightAnamation;
        AtlasNamedAtlasRegion[] walkLeftAnamation;
        AtlasNamedAtlasRegion[] slashAnamationUp;
        AtlasNamedAtlasRegion[] slashAnamationLeft;
        AtlasNamedAtlasRegion[] slashAnamationDown;
        AtlasNamedAtlasRegion[] slashAnamationRight;
        AtlasNamedAtlasRegion[] spellCastAnamationUp;
        AtlasNamedAtlasRegion[] spellCastAnamationDown;
        AtlasNamedAtlasRegion[] spellCastAnamationLeft;
        AtlasNamedAtlasRegion[] spellCastAnamationRight;
        AtlasNamedAtlasRegion[] shootAnamationRight;
        AtlasNamedAtlasRegion[] shootAnamationLeft;
        AtlasNamedAtlasRegion[] shootAnamationDown;
        AtlasNamedAtlasRegion[] shootAnamationUp;
        AtlasNamedAtlasRegion[] eatAnamationLeft;
        AtlasNamedAtlasRegion[] eatAnamationRight;
        AtlasNamedAtlasRegion[] eatAnamationUp;
        AtlasNamedAtlasRegion[] eatAnamationDown;
        AtlasNamedAtlasRegion[] openAnamationLeft;
        AtlasNamedAtlasRegion[] openAnamationRight;
        AtlasNamedAtlasRegion[] openAnamationUp;
        AtlasNamedAtlasRegion[] openAnamationDown;
        AtlasNamedAtlasRegion[] throwAnamationUp;
        AtlasNamedAtlasRegion[] throwAnamationDown;
        AtlasNamedAtlasRegion[] throwAnamationLeft;
        AtlasNamedAtlasRegion[] throwAnamationRight;
        AtlasNamedAtlasRegion[] dieAnamationUp;
        AtlasNamedAtlasRegion[] dieAnamationDown;
        AtlasNamedAtlasRegion[] dieAnamationLeft;
        AtlasNamedAtlasRegion[] dieAnamationRight;
        AtlasNamedAtlasRegion[] pickUpAnamationUp;
        AtlasNamedAtlasRegion[] pickUpAnamationDown;
        AtlasNamedAtlasRegion[] pickUpAnamationLeft;
        AtlasNamedAtlasRegion[] pickUpAnamationRight;
        AtlasNamedAtlasRegion[] grabAnamationUp;
        AtlasNamedAtlasRegion[] grabAnamationDown;
        AtlasNamedAtlasRegion[] grabAnamationLeft;
        AtlasNamedAtlasRegion[] grabAnamationRight;
        AtlasNamedAtlasRegion[] thrustAnamationUp;
        AtlasNamedAtlasRegion[] thrustAnamationDown;
        AtlasNamedAtlasRegion[] thrustAnamationLeft;
        AtlasNamedAtlasRegion[] thrustAnamationRight;
        AtlasNamedAtlasRegion[] dropAnamationUp;
        AtlasNamedAtlasRegion[] dropAnamationDown;
        AtlasNamedAtlasRegion[] dropAnamationLeft;
        AtlasNamedAtlasRegion[] dropAnamationRight;
        AtlasNamedAtlasRegion[] talkUpAnamation;
        AtlasNamedAtlasRegion[] talkDownAnamation;
        AtlasNamedAtlasRegion[] talkRightAnamation;
        AtlasNamedAtlasRegion[] talkLeftAnamation;
        AtlasNamedAtlasRegion[] addToPackUpAnamation;
        AtlasNamedAtlasRegion[] addToPackDownAnamation;
        AtlasNamedAtlasRegion[] addToPackRightAnamation;
        AtlasNamedAtlasRegion[] addToPackLeftAnamation;
        walkUpAnamation = new AtlasNamedAtlasRegion[walkUpFrames];
        walkDownAnamation = new AtlasNamedAtlasRegion[walkDownFrames];
        walkLeftAnamation = new AtlasNamedAtlasRegion[walkLeftFrames];
        walkRightAnamation = new AtlasNamedAtlasRegion[walkRightFrames];
        
         AtlasNamedAtlasRegion[] restUpAnamation = new AtlasNamedAtlasRegion[1];
       AtlasNamedAtlasRegion[] restDownAnamation = new AtlasNamedAtlasRegion[1];
        AtlasNamedAtlasRegion [] restLeftAnamation = new AtlasNamedAtlasRegion[1];
        AtlasNamedAtlasRegion [] restRightAnamation = new AtlasNamedAtlasRegion[1];
        
        if (hasWalkFrames == true) {
                restUpAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkUp" +0, atlasName);
          
                restDownAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkDown" + 0, atlasName);
           
                restLeftAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkLeft" + 0, atlasName);
            
                restRightAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkRight" + 0, atlasName);
            animatable.addAnimation(restUpAnamation, Direction.UP, "rest", 1, restUpOffsets, restUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(restDownAnamation, Direction.DOWN, "rest", 1,restDownOffsets, restDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(restLeftAnamation, Direction.LEFT, "rest", 1, restLeftOffsets, restLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(restRightAnamation, Direction.RIGHT, "rest", 1, restRightOffsets, restRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasWalkFrames == true) {
            for (int count = 0; count < walkUpFrames; count++) {
                walkUpAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkUp" + count, atlasName);
            }
            for (int count = 0; count < walkDownFrames; count++) {
                walkDownAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkDown" + count, atlasName);
            }
            for (int count = 0; count < walkLeftFrames; count++) {
                walkLeftAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkLeft" + count, atlasName);
            }
            for (int count = 0; count < walkRightFrames; count++) {
                walkRightAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkRight" + count, atlasName);
            }
            animatable.addAnimation(walkUpAnamation, Direction.UP, "move", walkFrameRate, walkUpOffsets, walkUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(walkDownAnamation, Direction.DOWN, "move", walkFrameRate,walkDownOffsets, walkDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(walkLeftAnamation, Direction.LEFT, "move", walkFrameRate, walkLeftOffsets, walkLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(walkRightAnamation, Direction.RIGHT, "move", walkFrameRate, walkRightOffsets, walkRightLayerNumber- rightLayerNumberOffset);
        }
        grabAnamationUp = new AtlasNamedAtlasRegion[grabUpFrames];
        grabAnamationDown = new AtlasNamedAtlasRegion[grabDownFrames];
        grabAnamationLeft = new AtlasNamedAtlasRegion[grabLeftFrames];
        grabAnamationRight = new AtlasNamedAtlasRegion[grabRightFrames];
        shootAnamationUp = new AtlasNamedAtlasRegion[shootUpFrames];
        shootAnamationDown = new AtlasNamedAtlasRegion[shootDownFrames];
        shootAnamationLeft = new AtlasNamedAtlasRegion[shootLeftFrames];
        shootAnamationRight = new AtlasNamedAtlasRegion[shootRightFrames];
        slashAnamationUp = new AtlasNamedAtlasRegion[slashUpFrames];
        slashAnamationDown = new AtlasNamedAtlasRegion[slashDownFrames];
        slashAnamationLeft = new AtlasNamedAtlasRegion[slashLeftFrames];
        slashAnamationRight = new AtlasNamedAtlasRegion[slashRightFrames];
        thrustAnamationUp = new AtlasNamedAtlasRegion[thrustUpFrames];
        thrustAnamationDown = new AtlasNamedAtlasRegion[thrustDownFrames];
        thrustAnamationLeft = new AtlasNamedAtlasRegion[thrustLeftFrames];
        thrustAnamationRight = new AtlasNamedAtlasRegion[thrustRightFrames];
        spellCastAnamationUp = new AtlasNamedAtlasRegion[spellCastUpFrames];
        spellCastAnamationDown = new AtlasNamedAtlasRegion[spellCastDownFrames];
        spellCastAnamationLeft = new AtlasNamedAtlasRegion[spellCastLeftFrames];
        spellCastAnamationRight = new AtlasNamedAtlasRegion[spellCastRightFrames];
        openAnamationUp = new AtlasNamedAtlasRegion[openUpFrames];
        openAnamationDown = new AtlasNamedAtlasRegion[openDownFrames];
        openAnamationLeft = new AtlasNamedAtlasRegion[openLeftFrames];
        openAnamationRight = new AtlasNamedAtlasRegion[openRightFrames];
        eatAnamationUp = new AtlasNamedAtlasRegion[eatUpFrames];
        eatAnamationDown = new AtlasNamedAtlasRegion[eatDownFrames];
        eatAnamationLeft = new AtlasNamedAtlasRegion[eatLeftFrames];
        eatAnamationRight = new AtlasNamedAtlasRegion[eatRightFrames];
        throwAnamationUp = new AtlasNamedAtlasRegion[throwUpFrames];
        throwAnamationDown = new AtlasNamedAtlasRegion[throwDownFrames];
        throwAnamationLeft = new AtlasNamedAtlasRegion[throwLeftFrames];
        throwAnamationRight = new AtlasNamedAtlasRegion[throwRightFrames];
        dieAnamationUp = new AtlasNamedAtlasRegion[dieUpFrames];
        dieAnamationDown = new AtlasNamedAtlasRegion[dieDownFrames];
        dieAnamationLeft = new AtlasNamedAtlasRegion[dieLeftFrames];
        dieAnamationRight = new AtlasNamedAtlasRegion[dieRightFrames];
        pickUpAnamationUp = new AtlasNamedAtlasRegion[pickUpUpFrames];
        pickUpAnamationDown = new AtlasNamedAtlasRegion[pickUpDownFrames];
        pickUpAnamationLeft = new AtlasNamedAtlasRegion[pickUpLeftFrames];
        pickUpAnamationRight = new AtlasNamedAtlasRegion[pickUpRightFrames];
        dropAnamationUp = new AtlasNamedAtlasRegion[dropUpFrames];
        dropAnamationDown = new AtlasNamedAtlasRegion[dropDownFrames];
        dropAnamationLeft = new AtlasNamedAtlasRegion[dropLeftFrames];
        dropAnamationRight = new AtlasNamedAtlasRegion[dropRightFrames];
      
        if (hasDieFrames) {
            if (dieDownOnly == true) {
                for (int count = 0; count < dieUpFrames; count++) {
                    dieAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
                for (int count = 0; count < dieDownFrames; count++) {
                    dieAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
                for (int count = 0; count < dieLeftFrames; count++) {
                    dieAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
                for (int count = 0; count < dieRightFrames; count++) {
                    dieAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
            } else {
                for (int count = 0; count < dieUpFrames; count++) {
                    dieAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "dieUp" + count, atlasName);
                }
                for (int count = 0; count < dieDownFrames; count++) {
                    dieAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "dieDown" + count, atlasName);
                }
                for (int count = 0; count < dieLeftFrames; count++) {
                    dieAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "dieLeft" + count, atlasName);
                }
                for (int count = 0; count < dieRightFrames; count++) {
                    dieAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "dieRight" + count, atlasName);
                }
            }
            animatable.addAnimation(dieAnamationUp, Direction.UP, "die", dieFrameRate, dieUpOffsets, dieUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(dieAnamationDown, Direction.DOWN, "die", dieFrameRate,dieDownOffsets, dieDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(dieAnamationLeft, Direction.LEFT, "die", dieFrameRate, dieLeftOffsets, dieLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(dieAnamationRight, Direction.RIGHT, "die", dieFrameRate, dieRightOffsets, dieRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasSlashFrames) {
            for (int count = 0; count < slashUpFrames; count++) {
                slashAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "slashUp" + count, atlasName);
            }
            for (int count = 0; count < slashDownFrames; count++) {
                slashAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "slashDown" + count, atlasName);
            }
            for (int count = 0; count < slashLeftFrames; count++) {
                slashAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "slashLeft" + count, atlasName);
            }
            for (int count = 0; count < slashRightFrames; count++) {
                slashAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "slashRight" + count, atlasName);
            }
            animatable.addAnimation(slashAnamationUp, Direction.UP, "slash", slashFrameRate, slashUpOffsets, slashUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(slashAnamationDown, Direction.DOWN, "slash", slashFrameRate,slashDownOffsets, slashDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(slashAnamationLeft, Direction.LEFT, "slash", slashFrameRate, slashLeftOffsets, slashLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(slashAnamationRight, Direction.RIGHT, "slash", slashFrameRate, slashRightOffsets, slashRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasEatFrames) {
            for (int count = 0; count < eatUpFrames; count++) {
                eatAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "eatUp" + count, atlasName);
            }
            for (int count = 0; count < eatDownFrames; count++) {
                eatAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "eatDown" + count, atlasName);
            }
            for (int count = 0; count < eatLeftFrames; count++) {
                eatAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "eatLeft" + count, atlasName);
            }
            for (int count = 0; count < eatRightFrames; count++) {
                eatAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "eatRight" + count, atlasName);
            }
            animatable.addAnimation(eatAnamationUp, Direction.UP, "eat", eatFrameRate, eatUpOffsets, eatUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(eatAnamationDown, Direction.DOWN, "eat", eatFrameRate,eatDownOffsets, eatDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(eatAnamationLeft, Direction.LEFT, "eat", eatFrameRate, eatLeftOffsets, eatLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(eatAnamationRight, Direction.RIGHT, "eat", eatFrameRate, eatRightOffsets, eatRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasThrustFrames) {
            for (int count = 0; count < thrustUpFrames; count++) {
                thrustAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustUp" + count, atlasName);
            }
            for (int count = 0; count < thrustDownFrames; count++) {
                thrustAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustDown" + count, atlasName);
            }
            for (int count = 0; count < thrustLeftFrames; count++) {
                thrustAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustLeft" + count, atlasName);
            }
            for (int count = 0; count < thrustRightFrames; count++) {
                thrustAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustRight" + count, atlasName);
            }
            animatable.addAnimation(thrustAnamationUp, Direction.UP, "thrust", thrustFrameRate, thrustUpOffsets, thrustUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(thrustAnamationDown, Direction.DOWN, "thrust", thrustFrameRate,thrustDownOffsets, thrustDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(thrustAnamationLeft, Direction.LEFT, "thrust", thrustFrameRate, thrustLeftOffsets, thrustLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(thrustAnamationRight, Direction.RIGHT, "thrust", thrustFrameRate, thrustRightOffsets, thrustRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasShootFrames) {
            for (int count = 0; count < shootUpFrames; count++) {
                shootAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "shootUp" + count, atlasName);
            }
            for (int count = 0; count < shootDownFrames; count++) {
                shootAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "shootDown" + count, atlasName);
            }
            for (int count = 0; count < shootLeftFrames; count++) {
                shootAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "shootLeft" + count, atlasName);
            }
            for (int count = 0; count < shootRightFrames; count++) {
                shootAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "shootRight" + count, atlasName);
            }
            animatable.addAnimation(shootAnamationUp, Direction.UP, "shoot", shootFrameRate, shootUpOffsets, shootUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(shootAnamationDown, Direction.DOWN, "shoot", shootFrameRate,shootDownOffsets, shootDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(shootAnamationLeft, Direction.LEFT, "shoot", shootFrameRate, shootLeftOffsets, shootLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(shootAnamationRight, Direction.RIGHT, "shoot", shootFrameRate, shootRightOffsets, shootRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasSpellCastFrames) {
            for (int count = 0; count < spellCastUpFrames; count++) {
                spellCastAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastUp" + count, atlasName);
            }
            for (int count = 0; count < spellCastDownFrames; count++) {
                spellCastAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastDown" + count, atlasName);
            }
            for (int count = 0; count < spellCastLeftFrames; count++) {
                spellCastAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastLeft" + count, atlasName);
            }
            for (int count = 0; count < spellCastRightFrames; count++) {
                spellCastAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastRight" + count, atlasName);
            }
            animatable.addAnimation(spellCastAnamationUp, Direction.UP, "spellCast", spellCastFrameRate, spellCastUpOffsets, spellCastUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(spellCastAnamationDown, Direction.DOWN, "spellCast", spellCastFrameRate,spellCastDownOffsets, spellCastDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(spellCastAnamationLeft, Direction.LEFT, "spellCast", spellCastFrameRate, spellCastLeftOffsets, spellCastLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(spellCastAnamationRight, Direction.RIGHT, "spellCast", spellCastFrameRate, spellCastRightOffsets, spellCastRightLayerNumber- rightLayerNumberOffset);
        }
        
        
        if (hasDropFrames) {
            if (dieForDrop == false) {
                for (int count = 0; count < dropUpFrames; count++) {
                    dropAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "dropUp" + count, atlasName);
                }
                for (int count = 0; count < dropDownFrames; count++) {
                    dropAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "dropDown" + count, atlasName);
                }
                for (int count = 0; count < dropLeftFrames; count++) {
                    dropAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "dropLeft" + count, atlasName);
                }
                for (int count = 0; count < dropRightFrames; count++) {
                    dropAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "dropRight" + count, atlasName);
                }
            } else if (hasDieFrames) {
                dropAnamationUp[0] = dieAnamationUp[0];
                dropAnamationUp[1] = dieAnamationUp[1];
                dropAnamationUp[2] = dieAnamationUp[0];
                dropAnamationDown[0] = dieAnamationUp[0];
                dropAnamationDown[1] = dieAnamationUp[1];
                dropAnamationDown[2] = dieAnamationUp[0];
                dropAnamationLeft[0] = dieAnamationUp[0];
                dropAnamationLeft[1] = dieAnamationUp[1];
                dropAnamationLeft[2] = dieAnamationUp[0];
                dropAnamationRight[0] = dieAnamationUp[0];
                dropAnamationRight[1] = dieAnamationUp[1];
                dropAnamationRight[2] = dieAnamationUp[0];
            }
            animatable.addAnimation(dropAnamationUp, Direction.UP, "drop", dropFrameRate, dropUpOffsets, dropUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(dropAnamationDown, Direction.DOWN, "drop", dropFrameRate,dropDownOffsets, dropDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(dropAnamationLeft, Direction.LEFT, "drop", dropFrameRate, dropLeftOffsets, dropLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(dropAnamationRight, Direction.RIGHT, "drop", dropFrameRate, dropRightOffsets, dropRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasGrabFrames) {
            if (thrustForGrab == false) {
                for (int count = 0; count < grabUpFrames; count++) {
                    grabAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "grabtUp" + count, atlasName);
                }
                for (int count = 0; count < grabDownFrames; count++) {
                    grabAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "grabDown" + count, atlasName);
                }
                for (int count = 0; count < grabLeftFrames; count++) {
                    grabAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "grabLeft" + count, atlasName);
                }
                for (int count = 0; count < grabRightFrames; count++) {
                    grabAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "grabRight" + count, atlasName);
                }
            } else if (hasThrustFrames) {
                grabAnamationLeft[0] = thrustAnamationLeft[0];
                grabAnamationLeft[1] = thrustAnamationLeft[1];
                grabAnamationLeft[2] = thrustAnamationLeft[3];
                grabAnamationLeft[3] = thrustAnamationLeft[4];
                grabAnamationLeft[4] = thrustAnamationLeft[3];
                grabAnamationLeft[5] = thrustAnamationLeft[1];
                grabAnamationLeft[6] = thrustAnamationLeft[0];
                grabAnamationRight[0] = thrustAnamationRight[0];
                grabAnamationRight[1] = thrustAnamationRight[1];
                grabAnamationRight[2] = thrustAnamationRight[3];
                grabAnamationRight[3] = thrustAnamationRight[4];
                grabAnamationRight[4] = thrustAnamationRight[3];
                grabAnamationRight[5] = thrustAnamationRight[1];
                grabAnamationRight[6] = thrustAnamationRight[0];
                grabAnamationUp[0] = thrustAnamationUp[0];
                grabAnamationUp[1] = thrustAnamationUp[1];
                grabAnamationUp[2] = thrustAnamationUp[3];
                grabAnamationUp[3] = thrustAnamationUp[4];
                grabAnamationUp[4] = thrustAnamationUp[5];
                grabAnamationUp[5] = thrustAnamationUp[4];
                grabAnamationUp[6] = thrustAnamationUp[3];
                grabAnamationUp[7] = thrustAnamationUp[1];
                grabAnamationUp[8] = thrustAnamationUp[0];
                grabAnamationDown[0] = thrustAnamationDown[0];
                grabAnamationDown[1] = thrustAnamationDown[1];
                grabAnamationDown[2] = thrustAnamationDown[3];
                grabAnamationDown[3] = thrustAnamationDown[4];
                grabAnamationDown[4] = thrustAnamationDown[3];
                grabAnamationDown[5] = thrustAnamationDown[1];
                grabAnamationDown[6] = thrustAnamationDown[0];
            }
            animatable.addAnimation(grabAnamationUp, Direction.UP, "grab", grabFrameRate, grabUpOffsets, grabUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(grabAnamationDown, Direction.DOWN, "grab", grabFrameRate,grabDownOffsets, grabDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(grabAnamationLeft, Direction.LEFT, "grab", grabFrameRate, grabLeftOffsets, grabLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(grabAnamationRight, Direction.RIGHT, "grab", grabFrameRate, grabRightOffsets, grabRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasPickUpFrames) {
            if (dieForPickup == false) {
                for (int count = 0; count < pickUpUpFrames; count++) {
                    pickUpAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUptUp" + count, atlasName);
                }
                for (int count = 0; count < pickUpDownFrames; count++) {
                    pickUpAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUpDown" + count, atlasName);
                }
                for (int count = 0; count < pickUpLeftFrames; count++) {
                    pickUpAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUpLeft" + count, atlasName);
                }
                for (int count = 0; count < pickUpRightFrames; count++) {
                    pickUpAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUpRight" + count, atlasName);
                }
            } else if (hasDieFrames) {
                pickUpAnamationUp[0] = dieAnamationUp[0];
                pickUpAnamationUp[1] = dieAnamationUp[1];
                pickUpAnamationUp[2] = dieAnamationUp[2];
                pickUpAnamationUp[3] = dieAnamationUp[1];
                pickUpAnamationUp[4] = dieAnamationUp[0];
                pickUpAnamationLeft = pickUpAnamationUp;
                pickUpAnamationRight = pickUpAnamationUp;
                pickUpAnamationDown = pickUpAnamationUp;
            }
            animatable.addAnimation(pickUpAnamationUp, Direction.UP, "pickUp", pickUpFrameRate, pickUpUpOffsets, pickUpUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(pickUpAnamationDown, Direction.DOWN, "pickUp", pickUpFrameRate,pickUpDownOffsets, pickUpDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(pickUpAnamationLeft, Direction.LEFT, "pickUp", pickUpFrameRate, pickUpLeftOffsets, pickUpLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(pickUpAnamationRight, Direction.RIGHT, "pickUp", pickUpFrameRate, pickUpRightOffsets, pickUpRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasThrowFrames) {
            if (slashForThrow == false) {
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "throwUp" + count, atlasName);
                }
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "throwDown" + count, atlasName);
                }
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "throwLeft" + count, atlasName);
                }
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "throwRight" + count, atlasName);
                }
            } else if (hasSlashFrames) {
                throwAnamationLeft[0] = slashAnamationLeft[0];
                throwAnamationLeft[1] = slashAnamationLeft[1];
                throwAnamationLeft[2] = slashAnamationLeft[3];
                throwAnamationLeft[3] = slashAnamationLeft[4];
                throwAnamationLeft[4] = slashAnamationLeft[3];
                throwAnamationLeft[5] = slashAnamationLeft[1];
                throwAnamationLeft[6] = slashAnamationLeft[0];
                throwAnamationRight[0] = slashAnamationRight[0];
                throwAnamationRight[1] = slashAnamationRight[1];
                throwAnamationRight[2] = slashAnamationRight[3];
                throwAnamationRight[3] = slashAnamationRight[4];
                throwAnamationRight[4] = slashAnamationRight[3];
                throwAnamationRight[5] = slashAnamationRight[1];
                throwAnamationRight[6] = slashAnamationRight[0];
                throwAnamationUp[0] = slashAnamationUp[0];
                throwAnamationUp[1] = slashAnamationUp[1];
                throwAnamationUp[2] = slashAnamationUp[3];
                throwAnamationUp[3] = slashAnamationUp[4];
                throwAnamationUp[4] = slashAnamationUp[5];
                throwAnamationUp[5] = slashAnamationUp[4];
                throwAnamationUp[6] = slashAnamationUp[3];
                throwAnamationUp[7] = slashAnamationUp[1];
                throwAnamationUp[8] = slashAnamationUp[0];
                throwAnamationDown[0] = slashAnamationDown[0];
                throwAnamationDown[1] = slashAnamationDown[1];
                throwAnamationDown[2] = slashAnamationDown[3];
                throwAnamationDown[3] = slashAnamationDown[4];
                throwAnamationDown[4] = slashAnamationDown[3];
                throwAnamationDown[5] = slashAnamationDown[1];
                throwAnamationDown[6] = slashAnamationDown[0];
            }
            animatable.addAnimation(throwAnamationUp, Direction.UP, "throw", throwFrameRate, throwUpOffsets, throwUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(throwAnamationDown, Direction.DOWN, "throw", throwFrameRate,throwDownOffsets, throwDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(throwAnamationLeft, Direction.LEFT, "throw", throwFrameRate, throwLeftOffsets, throwLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(throwAnamationRight, Direction.RIGHT, "throw", throwFrameRate, throwRightOffsets, throwRightLayerNumber- rightLayerNumberOffset);
        }
        
        if (hasOpenFrames) {
            if (grabForUnlock == false) {
                for (int count = 0; count < openUpFrames; count++) {
                    openAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "opentUp" + count, atlasName);
                }
                for (int count = 0; count < openDownFrames; count++) {
                    openAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "openDown" + count, atlasName);
                }
                for (int count = 0; count < openLeftFrames; count++) {
                    openAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "openLeft" + count, atlasName);
                }
                for (int count = 0; count < openRightFrames; count++) {
                    openAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "openRight" + count, atlasName);
                }
            } else if (hasGrabFrames) {
                openAnamationLeft[0] = grabAnamationLeft[0];
                openAnamationLeft[1] = grabAnamationLeft[1];
                openAnamationLeft[2] = grabAnamationLeft[3];
                openAnamationLeft[3] = grabAnamationLeft[4];
                openAnamationLeft[4] = grabAnamationLeft[3];
                openAnamationLeft[5] = grabAnamationLeft[1];
                openAnamationLeft[6] = grabAnamationLeft[0];
                openAnamationRight[0] = grabAnamationRight[0];
                openAnamationRight[1] = grabAnamationRight[1];
                openAnamationRight[2] = grabAnamationRight[3];
                openAnamationRight[3] = grabAnamationRight[4];
                openAnamationRight[4] = grabAnamationRight[3];
                openAnamationRight[5] = grabAnamationRight[1];
                openAnamationRight[6] = grabAnamationRight[0];
                openAnamationUp[0] = grabAnamationUp[0];
                openAnamationUp[1] = grabAnamationUp[1];
                openAnamationUp[2] = grabAnamationUp[3];
                openAnamationUp[3] = grabAnamationUp[4];
                openAnamationUp[4] = grabAnamationUp[5];
                openAnamationUp[5] = grabAnamationUp[4];
                openAnamationUp[6] = grabAnamationUp[3];
                openAnamationDown[0] = grabAnamationDown[0];
                openAnamationDown[1] = grabAnamationDown[1];
                openAnamationDown[2] = grabAnamationDown[3];
                openAnamationDown[3] = grabAnamationDown[4];
                openAnamationDown[4] = grabAnamationDown[3];
                openAnamationDown[5] = grabAnamationDown[1];
                openAnamationDown[6] = grabAnamationDown[0];
            }
            animatable.addAnimation(openAnamationUp, Direction.UP, "open", openFrameRate, openUpOffsets, openUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(openAnamationDown, Direction.DOWN, "open", openFrameRate,openDownOffsets, openDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(openAnamationLeft, Direction.LEFT, "open", openFrameRate, openLeftOffsets, openLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(openAnamationRight, Direction.RIGHT, "open", openFrameRate, openRightOffsets, openRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasWalkFrames && hasTalkFrames) {
            talkUpAnamation = new AtlasNamedAtlasRegion[1];
            talkUpAnamation[0] = walkUpAnamation[0];
            talkDownAnamation = new AtlasNamedAtlasRegion[1];
            talkDownAnamation[0] = walkDownAnamation[0];
            talkLeftAnamation = new AtlasNamedAtlasRegion[1];
            talkLeftAnamation[0] = walkLeftAnamation[0];
            talkRightAnamation = new AtlasNamedAtlasRegion[1];
            talkRightAnamation[0] = walkRightAnamation[0];
            animatable.addAnimation(walkUpAnamation, Direction.UP, "walk", walkFrameRate, walkUpOffsets, walkUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(walkDownAnamation, Direction.DOWN, "walk", walkFrameRate,walkDownOffsets, walkDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(walkLeftAnamation, Direction.LEFT, "walk", walkFrameRate, walkLeftOffsets, walkLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(walkRightAnamation, Direction.RIGHT, "walk", walkFrameRate, slashRightOffsets, walkRightLayerNumber- rightLayerNumberOffset);
        }
    }

    public void makeBody (LPCAnimations LPCAnimation) {
        boolean slashForThrow = true;// will the slash frames  be using fro throwing items too?
        boolean dieForPickup = true;// will the dieing frames be used for throwing too?
        boolean dieDownOnly = true; // is the dieing animatuions only in the down direction;
        boolean grabForUnlock = true;
        boolean thrustForGrab = true;
        boolean dieForDrop = true;
        AtlasNamedAtlasRegion[] walkUpAnamation;
        AtlasNamedAtlasRegion[] walkDownAnamation;
        AtlasNamedAtlasRegion[] walkRightAnamation;
        AtlasNamedAtlasRegion[] walkLeftAnamation;
        AtlasNamedAtlasRegion[] slashAnamationUp;
        AtlasNamedAtlasRegion[] slashAnamationLeft;
        AtlasNamedAtlasRegion[] slashAnamationDown;
        AtlasNamedAtlasRegion[] slashAnamationRight;
        AtlasNamedAtlasRegion[] spellCastAnamationUp;
        AtlasNamedAtlasRegion[] spellCastAnamationDown;
        AtlasNamedAtlasRegion[] spellCastAnamationLeft;
        AtlasNamedAtlasRegion[] spellCastAnamationRight;
        AtlasNamedAtlasRegion[] shootAnamationRight;
        AtlasNamedAtlasRegion[] shootAnamationLeft;
        AtlasNamedAtlasRegion[] shootAnamationDown;
        AtlasNamedAtlasRegion[] shootAnamationUp;
        AtlasNamedAtlasRegion[] eatAnamationLeft;
        AtlasNamedAtlasRegion[] eatAnamationRight;
        AtlasNamedAtlasRegion[] eatAnamationUp;
        AtlasNamedAtlasRegion[] eatAnamationDown;
        AtlasNamedAtlasRegion[] openAnamationLeft;
        AtlasNamedAtlasRegion[] openAnamationRight;
        AtlasNamedAtlasRegion[] openAnamationUp;
        AtlasNamedAtlasRegion[] openAnamationDown;
        AtlasNamedAtlasRegion[] throwAnamationUp;
        AtlasNamedAtlasRegion[] throwAnamationDown;
        AtlasNamedAtlasRegion[] throwAnamationLeft;
        AtlasNamedAtlasRegion[] throwAnamationRight;
        AtlasNamedAtlasRegion[] dieAnamationUp;
        AtlasNamedAtlasRegion[] dieAnamationDown;
        AtlasNamedAtlasRegion[] dieAnamationLeft;
        AtlasNamedAtlasRegion[] dieAnamationRight;
        AtlasNamedAtlasRegion[] pickUpAnamationUp;
        AtlasNamedAtlasRegion[] pickUpAnamationDown;
        AtlasNamedAtlasRegion[] pickUpAnamationLeft;
        AtlasNamedAtlasRegion[] pickUpAnamationRight;
        AtlasNamedAtlasRegion[] grabAnamationUp;
        AtlasNamedAtlasRegion[] grabAnamationDown;
        AtlasNamedAtlasRegion[] grabAnamationLeft;
        AtlasNamedAtlasRegion[] grabAnamationRight;
        AtlasNamedAtlasRegion[] thrustAnamationUp;
        AtlasNamedAtlasRegion[] thrustAnamationDown;
        AtlasNamedAtlasRegion[] thrustAnamationLeft;
        AtlasNamedAtlasRegion[] thrustAnamationRight;
        AtlasNamedAtlasRegion[] dropAnamationUp;
        AtlasNamedAtlasRegion[] dropAnamationDown;
        AtlasNamedAtlasRegion[] dropAnamationLeft;
        AtlasNamedAtlasRegion[] dropAnamationRight;
        AtlasNamedAtlasRegion[] talkUpAnamation;
        AtlasNamedAtlasRegion[] talkDownAnamation;
        AtlasNamedAtlasRegion[] talkRightAnamation;
        AtlasNamedAtlasRegion[] talkLeftAnamation;
        AtlasNamedAtlasRegion[] addToPackUpAnamation;
        AtlasNamedAtlasRegion[] addToPackDownAnamation;
        AtlasNamedAtlasRegion[] addToPackRightAnamation;
        AtlasNamedAtlasRegion[] addToPackLeftAnamation;
        walkUpAnamation = new AtlasNamedAtlasRegion[walkUpFrames];
        walkDownAnamation = new AtlasNamedAtlasRegion[walkDownFrames];
        walkLeftAnamation = new AtlasNamedAtlasRegion[walkLeftFrames];
        walkRightAnamation = new AtlasNamedAtlasRegion[walkRightFrames];

        AtlasNamedAtlasRegion[] restUpAnamation = new AtlasNamedAtlasRegion[1];
        AtlasNamedAtlasRegion[] restDownAnamation = new AtlasNamedAtlasRegion[1];
        AtlasNamedAtlasRegion [] restLeftAnamation = new AtlasNamedAtlasRegion[1];
        AtlasNamedAtlasRegion [] restRightAnamation = new AtlasNamedAtlasRegion[1];

        if (hasWalkFrames == true) {
            restUpAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkUp" +0, atlasName);

            restDownAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkDown" + 0, atlasName);

            restLeftAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkLeft" + 0, atlasName);

            restRightAnamation[0] = gameAssets.getAtlasRegionByName(bodyName + "walkRight" + 0, atlasName);
            animatable.addAnimation(restUpAnamation, Direction.UP, "rest", 1, restUpOffsets, restUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(restDownAnamation, Direction.DOWN, "rest", 1,restDownOffsets, restDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(restLeftAnamation, Direction.LEFT, "rest", 1, restLeftOffsets, restLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(restRightAnamation, Direction.RIGHT, "rest", 1, restRightOffsets, restRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasWalkFrames == true) {
            for (int count = 0; count < walkUpFrames; count++) {
                walkUpAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkUp" + count, atlasName);
            }
            for (int count = 0; count < walkDownFrames; count++) {
                walkDownAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkDown" + count, atlasName);
            }
            for (int count = 0; count < walkLeftFrames; count++) {
                walkLeftAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkLeft" + count, atlasName);
            }
            for (int count = 0; count < walkRightFrames; count++) {
                walkRightAnamation[count] = gameAssets.getAtlasRegionByName(bodyName + "walkRight" + count, atlasName);
            }
            animatable.addAnimation(walkUpAnamation, Direction.UP, "move", walkFrameRate, walkUpOffsets, walkUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(walkDownAnamation, Direction.DOWN, "move", walkFrameRate,walkDownOffsets, walkDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(walkLeftAnamation, Direction.LEFT, "move", walkFrameRate, walkLeftOffsets, walkLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(walkRightAnamation, Direction.RIGHT, "move", walkFrameRate, walkRightOffsets, walkRightLayerNumber- rightLayerNumberOffset);
        }
        grabAnamationUp = new AtlasNamedAtlasRegion[grabUpFrames];
        grabAnamationDown = new AtlasNamedAtlasRegion[grabDownFrames];
        grabAnamationLeft = new AtlasNamedAtlasRegion[grabLeftFrames];
        grabAnamationRight = new AtlasNamedAtlasRegion[grabRightFrames];
        shootAnamationUp = new AtlasNamedAtlasRegion[shootUpFrames];
        shootAnamationDown = new AtlasNamedAtlasRegion[shootDownFrames];
        shootAnamationLeft = new AtlasNamedAtlasRegion[shootLeftFrames];
        shootAnamationRight = new AtlasNamedAtlasRegion[shootRightFrames];
        slashAnamationUp = new AtlasNamedAtlasRegion[slashUpFrames];
        slashAnamationDown = new AtlasNamedAtlasRegion[slashDownFrames];
        slashAnamationLeft = new AtlasNamedAtlasRegion[slashLeftFrames];
        slashAnamationRight = new AtlasNamedAtlasRegion[slashRightFrames];
        thrustAnamationUp = new AtlasNamedAtlasRegion[thrustUpFrames];
        thrustAnamationDown = new AtlasNamedAtlasRegion[thrustDownFrames];
        thrustAnamationLeft = new AtlasNamedAtlasRegion[thrustLeftFrames];
        thrustAnamationRight = new AtlasNamedAtlasRegion[thrustRightFrames];
        spellCastAnamationUp = new AtlasNamedAtlasRegion[spellCastUpFrames];
        spellCastAnamationDown = new AtlasNamedAtlasRegion[spellCastDownFrames];
        spellCastAnamationLeft = new AtlasNamedAtlasRegion[spellCastLeftFrames];
        spellCastAnamationRight = new AtlasNamedAtlasRegion[spellCastRightFrames];
        openAnamationUp = new AtlasNamedAtlasRegion[openUpFrames];
        openAnamationDown = new AtlasNamedAtlasRegion[openDownFrames];
        openAnamationLeft = new AtlasNamedAtlasRegion[openLeftFrames];
        openAnamationRight = new AtlasNamedAtlasRegion[openRightFrames];
        eatAnamationUp = new AtlasNamedAtlasRegion[eatUpFrames];
        eatAnamationDown = new AtlasNamedAtlasRegion[eatDownFrames];
        eatAnamationLeft = new AtlasNamedAtlasRegion[eatLeftFrames];
        eatAnamationRight = new AtlasNamedAtlasRegion[eatRightFrames];
        throwAnamationUp = new AtlasNamedAtlasRegion[throwUpFrames];
        throwAnamationDown = new AtlasNamedAtlasRegion[throwDownFrames];
        throwAnamationLeft = new AtlasNamedAtlasRegion[throwLeftFrames];
        throwAnamationRight = new AtlasNamedAtlasRegion[throwRightFrames];
        dieAnamationUp = new AtlasNamedAtlasRegion[dieUpFrames];
        dieAnamationDown = new AtlasNamedAtlasRegion[dieDownFrames];
        dieAnamationLeft = new AtlasNamedAtlasRegion[dieLeftFrames];
        dieAnamationRight = new AtlasNamedAtlasRegion[dieRightFrames];
        pickUpAnamationUp = new AtlasNamedAtlasRegion[pickUpUpFrames];
        pickUpAnamationDown = new AtlasNamedAtlasRegion[pickUpDownFrames];
        pickUpAnamationLeft = new AtlasNamedAtlasRegion[pickUpLeftFrames];
        pickUpAnamationRight = new AtlasNamedAtlasRegion[pickUpRightFrames];
        dropAnamationUp = new AtlasNamedAtlasRegion[dropUpFrames];
        dropAnamationDown = new AtlasNamedAtlasRegion[dropDownFrames];
        dropAnamationLeft = new AtlasNamedAtlasRegion[dropLeftFrames];
        dropAnamationRight = new AtlasNamedAtlasRegion[dropRightFrames];

        if (hasDieFrames) {
            if (dieDownOnly == true) {
                for (int count = 0; count < dieUpFrames; count++) {
                    dieAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
                for (int count = 0; count < dieDownFrames; count++) {
                    dieAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
                for (int count = 0; count < dieLeftFrames; count++) {
                    dieAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
                for (int count = 0; count < dieRightFrames; count++) {
                    dieAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "die" + count, atlasName);
                }
            } else {
                for (int count = 0; count < dieUpFrames; count++) {
                    dieAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "dieUp" + count, atlasName);
                }
                for (int count = 0; count < dieDownFrames; count++) {
                    dieAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "dieDown" + count, atlasName);
                }
                for (int count = 0; count < dieLeftFrames; count++) {
                    dieAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "dieLeft" + count, atlasName);
                }
                for (int count = 0; count < dieRightFrames; count++) {
                    dieAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "dieRight" + count, atlasName);
                }
            }
            animatable.addAnimation(dieAnamationUp, Direction.UP, "die", dieFrameRate, dieUpOffsets, dieUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(dieAnamationDown, Direction.DOWN, "die", dieFrameRate,dieDownOffsets, dieDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(dieAnamationLeft, Direction.LEFT, "die", dieFrameRate, dieLeftOffsets, dieLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(dieAnamationRight, Direction.RIGHT, "die", dieFrameRate, dieRightOffsets, dieRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasSlashFrames) {
            for (int count = 0; count < slashUpFrames; count++) {
                slashAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "slashUp" + count, atlasName);
            }
            for (int count = 0; count < slashDownFrames; count++) {
                slashAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "slashDown" + count, atlasName);
            }
            for (int count = 0; count < slashLeftFrames; count++) {
                slashAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "slashLeft" + count, atlasName);
            }
            for (int count = 0; count < slashRightFrames; count++) {
                slashAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "slashRight" + count, atlasName);
            }
            animatable.addAnimation(slashAnamationUp, Direction.UP, "slash", slashFrameRate, slashUpOffsets, slashUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(slashAnamationDown, Direction.DOWN, "slash", slashFrameRate,slashDownOffsets, slashDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(slashAnamationLeft, Direction.LEFT, "slash", slashFrameRate, slashLeftOffsets, slashLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(slashAnamationRight, Direction.RIGHT, "slash", slashFrameRate, slashRightOffsets, slashRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasEatFrames) {
            for (int count = 0; count < eatUpFrames; count++) {
                eatAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "eatUp" + count, atlasName);
            }
            for (int count = 0; count < eatDownFrames; count++) {
                eatAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "eatDown" + count, atlasName);
            }
            for (int count = 0; count < eatLeftFrames; count++) {
                eatAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "eatLeft" + count, atlasName);
            }
            for (int count = 0; count < eatRightFrames; count++) {
                eatAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "eatRight" + count, atlasName);
            }
            animatable.addAnimation(eatAnamationUp, Direction.UP, "eat", eatFrameRate, eatUpOffsets, eatUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(eatAnamationDown, Direction.DOWN, "eat", eatFrameRate,eatDownOffsets, eatDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(eatAnamationLeft, Direction.LEFT, "eat", eatFrameRate, eatLeftOffsets, eatLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(eatAnamationRight, Direction.RIGHT, "eat", eatFrameRate, eatRightOffsets, eatRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasThrustFrames) {
            for (int count = 0; count < thrustUpFrames; count++) {
                thrustAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustUp" + count, atlasName);
            }
            for (int count = 0; count < thrustDownFrames; count++) {
                thrustAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustDown" + count, atlasName);
            }
            for (int count = 0; count < thrustLeftFrames; count++) {
                thrustAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustLeft" + count, atlasName);
            }
            for (int count = 0; count < thrustRightFrames; count++) {
                thrustAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "thrustRight" + count, atlasName);
            }
            animatable.addAnimation(thrustAnamationUp, Direction.UP, "thrust", thrustFrameRate, thrustUpOffsets, thrustUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(thrustAnamationDown, Direction.DOWN, "thrust", thrustFrameRate,thrustDownOffsets, thrustDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(thrustAnamationLeft, Direction.LEFT, "thrust", thrustFrameRate, thrustLeftOffsets, thrustLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(thrustAnamationRight, Direction.RIGHT, "thrust", thrustFrameRate, thrustRightOffsets, thrustRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasShootFrames) {
            for (int count = 0; count < shootUpFrames; count++) {
                shootAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "shootUp" + count, atlasName);
            }
            for (int count = 0; count < shootDownFrames; count++) {
                shootAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "shootDown" + count, atlasName);
            }
            for (int count = 0; count < shootLeftFrames; count++) {
                shootAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "shootLeft" + count, atlasName);
            }
            for (int count = 0; count < shootRightFrames; count++) {
                shootAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "shootRight" + count, atlasName);
            }
            animatable.addAnimation(shootAnamationUp, Direction.UP, "shoot", shootFrameRate, shootUpOffsets, shootUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(shootAnamationDown, Direction.DOWN, "shoot", shootFrameRate,shootDownOffsets, shootDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(shootAnamationLeft, Direction.LEFT, "shoot", shootFrameRate, shootLeftOffsets, shootLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(shootAnamationRight, Direction.RIGHT, "shoot", shootFrameRate, shootRightOffsets, shootRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasSpellCastFrames) {
            for (int count = 0; count < spellCastUpFrames; count++) {
                spellCastAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastUp" + count, atlasName);
            }
            for (int count = 0; count < spellCastDownFrames; count++) {
                spellCastAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastDown" + count, atlasName);
            }
            for (int count = 0; count < spellCastLeftFrames; count++) {
                spellCastAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastLeft" + count, atlasName);
            }
            for (int count = 0; count < spellCastRightFrames; count++) {
                spellCastAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "spellCastRight" + count, atlasName);
            }
            animatable.addAnimation(spellCastAnamationUp, Direction.UP, "spellCast", spellCastFrameRate, spellCastUpOffsets, spellCastUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(spellCastAnamationDown, Direction.DOWN, "spellCast", spellCastFrameRate,spellCastDownOffsets, spellCastDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(spellCastAnamationLeft, Direction.LEFT, "spellCast", spellCastFrameRate, spellCastLeftOffsets, spellCastLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(spellCastAnamationRight, Direction.RIGHT, "spellCast", spellCastFrameRate, spellCastRightOffsets, spellCastRightLayerNumber- rightLayerNumberOffset);
        }


        if (hasDropFrames) {
            if (dieForDrop == false) {
                for (int count = 0; count < dropUpFrames; count++) {
                    dropAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "dropUp" + count, atlasName);
                }
                for (int count = 0; count < dropDownFrames; count++) {
                    dropAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "dropDown" + count, atlasName);
                }
                for (int count = 0; count < dropLeftFrames; count++) {
                    dropAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "dropLeft" + count, atlasName);
                }
                for (int count = 0; count < dropRightFrames; count++) {
                    dropAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "dropRight" + count, atlasName);
                }
            } else if (hasDieFrames) {
                dropAnamationUp[0] = dieAnamationUp[0];
                dropAnamationUp[1] = dieAnamationUp[1];
                dropAnamationUp[2] = dieAnamationUp[0];
                dropAnamationDown[0] = dieAnamationUp[0];
                dropAnamationDown[1] = dieAnamationUp[1];
                dropAnamationDown[2] = dieAnamationUp[0];
                dropAnamationLeft[0] = dieAnamationUp[0];
                dropAnamationLeft[1] = dieAnamationUp[1];
                dropAnamationLeft[2] = dieAnamationUp[0];
                dropAnamationRight[0] = dieAnamationUp[0];
                dropAnamationRight[1] = dieAnamationUp[1];
                dropAnamationRight[2] = dieAnamationUp[0];
            }
            animatable.addAnimation(dropAnamationUp, Direction.UP, "drop", dropFrameRate, dropUpOffsets, dropUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(dropAnamationDown, Direction.DOWN, "drop", dropFrameRate,dropDownOffsets, dropDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(dropAnamationLeft, Direction.LEFT, "drop", dropFrameRate, dropLeftOffsets, dropLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(dropAnamationRight, Direction.RIGHT, "drop", dropFrameRate, dropRightOffsets, dropRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasGrabFrames) {
            if (thrustForGrab == false) {
                for (int count = 0; count < grabUpFrames; count++) {
                    grabAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "grabtUp" + count, atlasName);
                }
                for (int count = 0; count < grabDownFrames; count++) {
                    grabAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "grabDown" + count, atlasName);
                }
                for (int count = 0; count < grabLeftFrames; count++) {
                    grabAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "grabLeft" + count, atlasName);
                }
                for (int count = 0; count < grabRightFrames; count++) {
                    grabAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "grabRight" + count, atlasName);
                }
            } else if (hasThrustFrames) {
                grabAnamationLeft[0] = thrustAnamationLeft[0];
                grabAnamationLeft[1] = thrustAnamationLeft[1];
                grabAnamationLeft[2] = thrustAnamationLeft[3];
                grabAnamationLeft[3] = thrustAnamationLeft[4];
                grabAnamationLeft[4] = thrustAnamationLeft[3];
                grabAnamationLeft[5] = thrustAnamationLeft[1];
                grabAnamationLeft[6] = thrustAnamationLeft[0];
                grabAnamationRight[0] = thrustAnamationRight[0];
                grabAnamationRight[1] = thrustAnamationRight[1];
                grabAnamationRight[2] = thrustAnamationRight[3];
                grabAnamationRight[3] = thrustAnamationRight[4];
                grabAnamationRight[4] = thrustAnamationRight[3];
                grabAnamationRight[5] = thrustAnamationRight[1];
                grabAnamationRight[6] = thrustAnamationRight[0];
                grabAnamationUp[0] = thrustAnamationUp[0];
                grabAnamationUp[1] = thrustAnamationUp[1];
                grabAnamationUp[2] = thrustAnamationUp[3];
                grabAnamationUp[3] = thrustAnamationUp[4];
                grabAnamationUp[4] = thrustAnamationUp[5];
                grabAnamationUp[5] = thrustAnamationUp[4];
                grabAnamationUp[6] = thrustAnamationUp[3];
                grabAnamationUp[7] = thrustAnamationUp[1];
                grabAnamationUp[8] = thrustAnamationUp[0];
                grabAnamationDown[0] = thrustAnamationDown[0];
                grabAnamationDown[1] = thrustAnamationDown[1];
                grabAnamationDown[2] = thrustAnamationDown[3];
                grabAnamationDown[3] = thrustAnamationDown[4];
                grabAnamationDown[4] = thrustAnamationDown[3];
                grabAnamationDown[5] = thrustAnamationDown[1];
                grabAnamationDown[6] = thrustAnamationDown[0];
            }
            animatable.addAnimation(grabAnamationUp, Direction.UP, "grab", grabFrameRate, grabUpOffsets, grabUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(grabAnamationDown, Direction.DOWN, "grab", grabFrameRate,grabDownOffsets, grabDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(grabAnamationLeft, Direction.LEFT, "grab", grabFrameRate, grabLeftOffsets, grabLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(grabAnamationRight, Direction.RIGHT, "grab", grabFrameRate, grabRightOffsets, grabRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasPickUpFrames) {
            if (dieForPickup == false) {
                for (int count = 0; count < pickUpUpFrames; count++) {
                    pickUpAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUptUp" + count, atlasName);
                }
                for (int count = 0; count < pickUpDownFrames; count++) {
                    pickUpAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUpDown" + count, atlasName);
                }
                for (int count = 0; count < pickUpLeftFrames; count++) {
                    pickUpAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUpLeft" + count, atlasName);
                }
                for (int count = 0; count < pickUpRightFrames; count++) {
                    pickUpAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "pickUpRight" + count, atlasName);
                }
            } else if (hasDieFrames) {
                pickUpAnamationUp[0] = dieAnamationUp[0];
                pickUpAnamationUp[1] = dieAnamationUp[1];
                pickUpAnamationUp[2] = dieAnamationUp[2];
                pickUpAnamationUp[3] = dieAnamationUp[1];
                pickUpAnamationUp[4] = dieAnamationUp[0];
                pickUpAnamationLeft = pickUpAnamationUp;
                pickUpAnamationRight = pickUpAnamationUp;
                pickUpAnamationDown = pickUpAnamationUp;
            }
            animatable.addAnimation(pickUpAnamationUp, Direction.UP, "pickUp", pickUpFrameRate, pickUpUpOffsets, pickUpUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(pickUpAnamationDown, Direction.DOWN, "pickUp", pickUpFrameRate,pickUpDownOffsets, pickUpDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(pickUpAnamationLeft, Direction.LEFT, "pickUp", pickUpFrameRate, pickUpLeftOffsets, pickUpLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(pickUpAnamationRight, Direction.RIGHT, "pickUp", pickUpFrameRate, pickUpRightOffsets, pickUpRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasThrowFrames) {
            if (slashForThrow == false) {
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationUp[count] = gameAssets.getAtlasRegionByName(bodyName + "throwUp" + count, atlasName);
                }
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "throwDown" + count, atlasName);
                }
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "throwLeft" + count, atlasName);
                }
                for (int count = 0; count < throwUpFrames; count++) {
                    throwAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "throwRight" + count, atlasName);
                }
            } else if (hasSlashFrames) {
                throwAnamationLeft[0] = slashAnamationLeft[0];
                throwAnamationLeft[1] = slashAnamationLeft[1];
                throwAnamationLeft[2] = slashAnamationLeft[3];
                throwAnamationLeft[3] = slashAnamationLeft[4];
                throwAnamationLeft[4] = slashAnamationLeft[3];
                throwAnamationLeft[5] = slashAnamationLeft[1];
                throwAnamationLeft[6] = slashAnamationLeft[0];
                throwAnamationRight[0] = slashAnamationRight[0];
                throwAnamationRight[1] = slashAnamationRight[1];
                throwAnamationRight[2] = slashAnamationRight[3];
                throwAnamationRight[3] = slashAnamationRight[4];
                throwAnamationRight[4] = slashAnamationRight[3];
                throwAnamationRight[5] = slashAnamationRight[1];
                throwAnamationRight[6] = slashAnamationRight[0];
                throwAnamationUp[0] = slashAnamationUp[0];
                throwAnamationUp[1] = slashAnamationUp[1];
                throwAnamationUp[2] = slashAnamationUp[3];
                throwAnamationUp[3] = slashAnamationUp[4];
                throwAnamationUp[4] = slashAnamationUp[5];
                throwAnamationUp[5] = slashAnamationUp[4];
                throwAnamationUp[6] = slashAnamationUp[3];
                throwAnamationUp[7] = slashAnamationUp[1];
                throwAnamationUp[8] = slashAnamationUp[0];
                throwAnamationDown[0] = slashAnamationDown[0];
                throwAnamationDown[1] = slashAnamationDown[1];
                throwAnamationDown[2] = slashAnamationDown[3];
                throwAnamationDown[3] = slashAnamationDown[4];
                throwAnamationDown[4] = slashAnamationDown[3];
                throwAnamationDown[5] = slashAnamationDown[1];
                throwAnamationDown[6] = slashAnamationDown[0];
            }
            animatable.addAnimation(throwAnamationUp, Direction.UP, "throw", throwFrameRate, throwUpOffsets, throwUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(throwAnamationDown, Direction.DOWN, "throw", throwFrameRate,throwDownOffsets, throwDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(throwAnamationLeft, Direction.LEFT, "throw", throwFrameRate, throwLeftOffsets, throwLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(throwAnamationRight, Direction.RIGHT, "throw", throwFrameRate, throwRightOffsets, throwRightLayerNumber- rightLayerNumberOffset);
        }

        if (hasOpenFrames) {
            if (grabForUnlock == false) {
                for (int count = 0; count < openUpFrames; count++) {
                    openAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "opentUp" + count, atlasName);
                }
                for (int count = 0; count < openDownFrames; count++) {
                    openAnamationDown[count] = gameAssets.getAtlasRegionByName(bodyName + "openDown" + count, atlasName);
                }
                for (int count = 0; count < openLeftFrames; count++) {
                    openAnamationLeft[count] = gameAssets.getAtlasRegionByName(bodyName + "openLeft" + count, atlasName);
                }
                for (int count = 0; count < openRightFrames; count++) {
                    openAnamationRight[count] = gameAssets.getAtlasRegionByName(bodyName + "openRight" + count, atlasName);
                }
            } else if (hasGrabFrames) {
                openAnamationLeft[0] = grabAnamationLeft[0];
                openAnamationLeft[1] = grabAnamationLeft[1];
                openAnamationLeft[2] = grabAnamationLeft[3];
                openAnamationLeft[3] = grabAnamationLeft[4];
                openAnamationLeft[4] = grabAnamationLeft[3];
                openAnamationLeft[5] = grabAnamationLeft[1];
                openAnamationLeft[6] = grabAnamationLeft[0];
                openAnamationRight[0] = grabAnamationRight[0];
                openAnamationRight[1] = grabAnamationRight[1];
                openAnamationRight[2] = grabAnamationRight[3];
                openAnamationRight[3] = grabAnamationRight[4];
                openAnamationRight[4] = grabAnamationRight[3];
                openAnamationRight[5] = grabAnamationRight[1];
                openAnamationRight[6] = grabAnamationRight[0];
                openAnamationUp[0] = grabAnamationUp[0];
                openAnamationUp[1] = grabAnamationUp[1];
                openAnamationUp[2] = grabAnamationUp[3];
                openAnamationUp[3] = grabAnamationUp[4];
                openAnamationUp[4] = grabAnamationUp[5];
                openAnamationUp[5] = grabAnamationUp[4];
                openAnamationUp[6] = grabAnamationUp[3];
                openAnamationDown[0] = grabAnamationDown[0];
                openAnamationDown[1] = grabAnamationDown[1];
                openAnamationDown[2] = grabAnamationDown[3];
                openAnamationDown[3] = grabAnamationDown[4];
                openAnamationDown[4] = grabAnamationDown[3];
                openAnamationDown[5] = grabAnamationDown[1];
                openAnamationDown[6] = grabAnamationDown[0];
            }
            animatable.addAnimation(openAnamationUp, Direction.UP, "open", openFrameRate, openUpOffsets, openUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(openAnamationDown, Direction.DOWN, "open", openFrameRate,openDownOffsets, openDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(openAnamationLeft, Direction.LEFT, "open", openFrameRate, openLeftOffsets, openLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(openAnamationRight, Direction.RIGHT, "open", openFrameRate, openRightOffsets, openRightLayerNumber- rightLayerNumberOffset);
        }
        if (hasWalkFrames && hasTalkFrames) {
            talkUpAnamation = new AtlasNamedAtlasRegion[1];
            talkUpAnamation[0] = walkUpAnamation[0];
            talkDownAnamation = new AtlasNamedAtlasRegion[1];
            talkDownAnamation[0] = walkDownAnamation[0];
            talkLeftAnamation = new AtlasNamedAtlasRegion[1];
            talkLeftAnamation[0] = walkLeftAnamation[0];
            talkRightAnamation = new AtlasNamedAtlasRegion[1];
            talkRightAnamation[0] = walkRightAnamation[0];
            animatable.addAnimation(walkUpAnamation, Direction.UP, "walk", walkFrameRate, walkUpOffsets, walkUpLayerNumber- upLayerNumberOffset);
            animatable.addAnimation(walkDownAnamation, Direction.DOWN, "walk", walkFrameRate,walkDownOffsets, walkDownLayerNumber- downLayerNumberOffset);
            animatable.addAnimation(walkLeftAnamation, Direction.LEFT, "walk", walkFrameRate, walkLeftOffsets, walkLeftLayerNumber- leftLayerNumberOffset);
            animatable.addAnimation(walkRightAnamation, Direction.RIGHT, "walk", walkFrameRate, slashRightOffsets, walkRightLayerNumber- rightLayerNumberOffset);
        }
    }
    public void setAnimatable(AnimatableComponent animatable) {
        this.animatable = animatable;
    }
    public void setGameAssets(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }
    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }
    public void setHasWalkFrames(boolean hasWalkFrames) {
        this.hasWalkFrames = hasWalkFrames;
    }
    public void setHasSlashFrames(boolean hasSlashFrames) {
        this.hasSlashFrames = hasSlashFrames;
    }
    public void setHasThrustFrames(boolean hasThrustFrames) {
        this.hasThrustFrames = hasThrustFrames;
    }
    public void setHasEatFrames(boolean hasEatFrames) {
        this.hasEatFrames = hasEatFrames;
    }
    public void setHasSpellCastFrames(boolean hasSpellCastFrames) {
        this.hasSpellCastFrames = hasSpellCastFrames;
    }
    public void setHasShootFrames(boolean hasShootFrames) {
        this.hasShootFrames = hasShootFrames;
    }
    public void setHasPickUpFrames(boolean hasPickUpFrames) {
        this.hasPickUpFrames = hasPickUpFrames;
    }
    public void setHasDropFrames(boolean hasDropFrames) {
        this.hasDropFrames = hasDropFrames;
    }
    public void setHasOpenFrames(boolean hasOpenFrames) {
        this.hasOpenFrames = hasOpenFrames;
    }
    public void setHasDieFrames(boolean hasDieFrames) {
        this.hasDieFrames = hasDieFrames;
    }
    public void setHasGrabFrames(boolean hasGrabFrames) {
        this.hasGrabFrames = hasGrabFrames;
    }
    public void setHasThrowFrames(boolean hasThrowFrames) {
        this.hasThrowFrames = hasThrowFrames;
    }
    public void setHasTalkFrames(boolean hasTalkFrames) {
        this.hasTalkFrames = hasTalkFrames;
    }

    public String getAtlasName() {
        return atlasName;
    }
}
