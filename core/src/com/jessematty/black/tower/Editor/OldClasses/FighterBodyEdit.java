package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class FighterBodyEdit extends ObjectEdit {


    CheckBox dieDownOnly;
    CheckBox dieForDrop;
    CheckBox dieForPickup;
    CheckBox slashForThrow;
    CheckBox usingLPCSprites;
    CheckBox editAnimations;
    CheckBox findBodyByName;
    

    TextField name;
    TextField atlasName;



    






    public FighterBodyEdit(GameAssets assetts, Skin skin) {


     



    }



    /*
    protected void setFrames(){
      body.setDropDownFrames(Integer.valueOf(dropDownAnimation.getxSize()));
        body.setDieDownFrames(Integer.valueOf(dieDownAnimation.getxSize()));
        body.setThrowDownFrames(Integer.valueOf(throwDownAnimation.getxSize()));
        body.setEatDownFrames(Integer.valueOf(eatDownAnimation.getxSize()));
        body.setSlashDownFrames(Integer.valueOf(slashDownAnimation.getxSize()));
        body.setThrustDownFrames(Integer.valueOf(thrustDownAnimation.getxSize()));
        body.setShootDownFrames(Integer.valueOf(shootDownAnimation.getxSize()));
        body.setGrabDownFrames(Integer.valueOf(grabDownAnimation.getxSize()));
        body.setWalkDownFrames(Integer.valueOf(walkDownAnimation.getxSize()));
        body.setSpellCastDownFrames(Integer.valueOf(spellCastDownAnimation.getxSize()));
        body.setPickUpDownFrames(Integer.valueOf(pickUpDownAnimation.getxSize()));

        body.setDropUpFrames(Integer.valueOf(dropUpAnimation.getxSize()));
        body.setDieUpFrames(Integer.valueOf(dieUpAnimation.getxSize()));
        body.setThrowUpFrames(Integer.valueOf(throwUpAnimation.getxSize()));
        body.setEatUpFrames(Integer.valueOf(eatUpAnimation.getxSize()));
        body.setSlashUpFrames(Integer.valueOf(slashUpAnimation.getxSize()));
        body.setThrustUpFrames(Integer.valueOf(thrustUpAnimation.getxSize()));
        body.setShootUpFrames(Integer.valueOf(shootUpAnimation.getxSize()));
        body.setGrabUpFrames(Integer.valueOf(grabUpAnimation.getxSize()));
        body.setWalkUpFrames(Integer.valueOf(walkUpAnimation.getxSize()));
        body.setSpellCastUpFrames(Integer.valueOf(spellCastUpAnimation.getxSize()));
        body.setPickUpUpFrames(Integer.valueOf(pickUpUpAnimation.getxSize()));

        body.setDropLeftFrames(Integer.valueOf(dropLeftAnimation.getxSize()));
        body.setDieLeftFrames(Integer.valueOf(dieLeftAnimation.getxSize()));
        body.setThrowLeftFrames(Integer.valueOf(throwLeftAnimation.getxSize()));
        body.setEatLeftFrames(Integer.valueOf(eatLeftAnimation.getxSize()));
        body.setSlashLeftFrames(Integer.valueOf(slashLeftAnimation.getxSize()));
        body.setThrustLeftFrames(Integer.valueOf(thrustLeftAnimation.getxSize()));
        body.setShootLeftFrames(Integer.valueOf(shootLeftAnimation.getxSize()));
        body.setGrabLeftFrames(Integer.valueOf(grabLeftAnimation.getxSize()));
        body.setWalkLeftFrames(Integer.valueOf(walkLeftAnimation.getxSize()));
        body.setSpellCastLeftFrames(Integer.valueOf(spellCastLeftAnimation.getxSize()));
        body.setPickUpLeftFrames(Integer.valueOf(pickUpLeftAnimation.getxSize()));

        body.setDropRightFrames(Integer.valueOf(dropRightAnimation.getxSize()));
        body.setDieRightFrames(Integer.valueOf(dieRightAnimation.getxSize()));
        body.setThrowRightFrames(Integer.valueOf(throwRightAnimation.getxSize()));
        body.setEatRightFrames(Integer.valueOf(eatRightAnimation.getxSize()));
        body.setSlashRightFrames(Integer.valueOf(slashRightAnimation.getxSize()));
        body.setThrustRightFrames(Integer.valueOf(thrustRightAnimation.getxSize()));
        body.setShootRightFrames(Integer.valueOf(shootRightAnimation.getxSize()));
        body.setGrabRightFrames(Integer.valueOf(grabRightAnimation.getxSize()));
        body.setWalkRightFrames(Integer.valueOf(walkRightAnimation.getxSize()));
        body.setSpellCastRightFrames(Integer.valueOf(spellCastRightAnimation.getxSize()));
        body.setPickUpRightFrames(Integer.valueOf(pickUpRightAnimation.getxSize()));

        body.setBodyName(name.getMessageText());


        walkUpAnimation= new TextureRegionArrayModify(1, body.getWalkUpFrames(), assetts,skin,  objects);
        dropUpAnimation= new TextureRegionArrayModify(1, body.getDropUpFrames(), assetts,skin,  objects);
        spellCastUpAnimation= new TextureRegionArrayModify(1, body.getSpellCastUpFrames(), assetts,skin,  objects);
        thrustUpAnimation= new TextureRegionArrayModify(1, body.getThrustUpFrames(), assetts,skin,  objects);
        shootUpAnimation= new TextureRegionArrayModify(1, body.getShootUpFrames(), assetts,skin,  objects);
        grabUpAnimation= new TextureRegionArrayModify(1, body.getGrabUpFrames(), assetts,skin,  objects);
        pickUpUpAnimation= new TextureRegionArrayModify(1, body.getPickUpUpFrames(), assetts,skin,  objects);
        eatUpAnimation= new TextureRegionArrayModify(1, body.getEatUpFrames(), assetts,skin,  objects);
        throwUpAnimation= new TextureRegionArrayModify(1, body.getThrowUpFrames(), assetts,skin,  objects);
        dieUpAnimation= new TextureRegionArrayModify(1, body.getDieUpFrames(), assetts,skin,  objects);
        slashUpAnimation= new TextureRegionArrayModify(1, body.getSlashUpFrames(), assetts,skin,  objects);


        walkRightAnimation= new TextureRegionArrayModify(1, body.getWalkRightFrames(), assetts,skin,  objects);
        dropRightAnimation= new TextureRegionArrayModify(1, body.getDropRightFrames(), assetts,skin,  objects);
        spellCastRightAnimation= new TextureRegionArrayModify(1, body.getSpellCastRightFrames(), assetts,skin,  objects);
        thrustRightAnimation= new TextureRegionArrayModify(1, body.getThrustRightFrames(), assetts,skin,  objects);
        shootRightAnimation= new TextureRegionArrayModify(1, body.getShootRightFrames(), assetts,skin,  objects);
        grabRightAnimation= new TextureRegionArrayModify(1, body.getGrabRightFrames(), assetts,skin,  objects);
        pickUpRightAnimation= new TextureRegionArrayModify(1, body.getPickUpRightFrames(), assetts,skin,  objects);
        eatRightAnimation= new TextureRegionArrayModify(1, body.getEatRightFrames(), assetts,skin,  objects);
        throwRightAnimation= new TextureRegionArrayModify(1, body.getThrowRightFrames(), assetts,skin,  objects);
        dieRightAnimation= new TextureRegionArrayModify(1, body.getDieRightFrames(), assetts,skin,  objects);
        slashRightAnimation= new TextureRegionArrayModify(1, body.getSlashRightFrames(), assetts,skin,  objects);




        walkLeftAnimation= new TextureRegionArrayModify(1, body.getWalkLeftFrames(), assetts,skin,  objects);
        dropLeftAnimation= new TextureRegionArrayModify(1, body.getDropLeftFrames(), assetts,skin,  objects);
        spellCastLeftAnimation= new TextureRegionArrayModify(1, body.getSpellCastLeftFrames(), assetts,skin,  objects);
        thrustLeftAnimation= new TextureRegionArrayModify(1, body.getThrustLeftFrames(), assetts,skin,  objects);
        shootLeftAnimation= new TextureRegionArrayModify(1, body.getShootLeftFrames(), assetts,skin,  objects);
        grabLeftAnimation= new TextureRegionArrayModify(1, body.getGrabLeftFrames(), assetts,skin,  objects);
        pickUpLeftAnimation= new TextureRegionArrayModify(1, body.getPickUpLeftFrames(), assetts,skin,  objects);
        eatLeftAnimation= new TextureRegionArrayModify(1, body.getEatLeftFrames(), assetts,skin,  objects);
        throwLeftAnimation= new TextureRegionArrayModify(1, body.getThrowLeftFrames(), assetts,skin,  objects);
        dieLeftAnimation= new TextureRegionArrayModify(1, body.getDieLeftFrames(), assetts,skin,  objects);
        slashLeftAnimation= new TextureRegionArrayModify(1, body.getSlashLeftFrames(), assetts,skin,  objects);




        walkDownAnimation= new TextureRegionArrayModify(1, body.getWalkDownFrames(), assetts,skin,  objects);
        dropDownAnimation= new TextureRegionArrayModify(1, body.getDropDownFrames(), assetts,skin,  objects);
        spellCastDownAnimation= new TextureRegionArrayModify(1, body.getSpellCastDownFrames(), assetts,skin,  objects);
        thrustDownAnimation= new TextureRegionArrayModify(1, body.getThrustDownFrames(), assetts,skin,  objects);
        shootDownAnimation= new TextureRegionArrayModify(1, body.getShootDownFrames(), assetts,skin,  objects);
        grabDownAnimation= new TextureRegionArrayModify(1, body.getGrabDownFrames(), assetts,skin,  objects);
        pickUpDownAnimation= new TextureRegionArrayModify(1, body.getPickUpDownFrames(), assetts,skin,  objects);
        eatDownAnimation= new TextureRegionArrayModify(1, body.getEatDownFrames(), assetts,skin,  objects);
        throwDownAnimation= new TextureRegionArrayModify(1, body.getThrowDownFrames(), assetts,skin,  objects);
        dieDownAnimation= new TextureRegionArrayModify(1, body.getDieDownFrames(), assetts,skin,  objects);
        slashDownAnimation= new TextureRegionArrayModify(1, body.getSlashDownFrames(), assetts,skin,  objects);




        walkDownAnimation.getHGroup().removeEntity();
        throwDownAnimation.getHGroup().removeEntity();
        eatDownAnimation.getHGroup().removeEntity();
        pickUpDownAnimation.getHGroup().removeEntity();
        thrustDownAnimation.getHGroup().removeEntity();
        slashDownAnimation.getHGroup().removeEntity();
        shootDownAnimation.getHGroup().removeEntity();
        spellCastDownAnimation.getHGroup().removeEntity();
        grabDownAnimation.getHGroup().removeEntity();
        dropDownAnimation.getHGroup().removeEntity();
        dieDownAnimation.getHGroup().removeEntity();


        walkUpAnimation.getHGroup().removeEntity();
        throwUpAnimation.getHGroup().removeEntity();
        eatUpAnimation.getHGroup().removeEntity();
        pickUpUpAnimation.getHGroup().removeEntity();
        thrustUpAnimation.getHGroup().removeEntity();
        slashUpAnimation.getHGroup().removeEntity();
        shootUpAnimation.getHGroup().removeEntity();
        spellCastUpAnimation.getHGroup().removeEntity();
        grabUpAnimation.getHGroup().removeEntity();
        dropUpAnimation.getHGroup().removeEntity();
        dieUpAnimation.getHGroup().removeEntity();



        walkLeftAnimation.getHGroup().removeEntity();
        throwLeftAnimation.getHGroup().removeEntity();
        eatLeftAnimation.getHGroup().removeEntity();
        pickUpLeftAnimation.getHGroup().removeEntity();
        thrustLeftAnimation.getHGroup().removeEntity();
        slashLeftAnimation.getHGroup().removeEntity();
        shootLeftAnimation.getHGroup().removeEntity();
        spellCastLeftAnimation.getHGroup().removeEntity();
        grabLeftAnimation.getHGroup().removeEntity();
        dropLeftAnimation.getHGroup().removeEntity();
        dieLeftAnimation.getHGroup().removeEntity();


        walkRightAnimation.getHGroup().removeEntity();
        throwRightAnimation.getHGroup().removeEntity();
        eatRightAnimation.getHGroup().removeEntity();
        pickUpRightAnimation.getHGroup().removeEntity();
        thrustRightAnimation.getHGroup().removeEntity();
        slashRightAnimation.getHGroup().removeEntity();
        shootRightAnimation.getHGroup().removeEntity();
        spellCastRightAnimation.getHGroup().removeEntity();
        grabRightAnimation.getHGroup().removeEntity();
        dropRightAnimation.getHGroup().removeEntity();
        dieRightAnimation.getHGroup().removeEntity();







        walkDownAnimationGroup.addActor(walkDownAnimationPreview);
        throwDownAnimationGroup.addActor(throwDownAnimationPreview);
        eatDownAnimationGroup.addActor(eatDownAnimationPreview);
        pickUpDownAnimationGroup.addActor(pickUpDownAnimationPreview);
        thrustDownAnimationGroup.addActor(thrustDownAnimationPreview);
        slashDownAnimationGroup.addActor(slashDownAnimationPreview);
        shootDownAnimationGroup.addActor(shootDownAnimationPreview);
        spellCastDownAnimationGroup.addActor(spellCastDownAnimationPreview);
        grabDownAnimationGroup.addActor(grabDownAnimationPreview);
        dropDownAnimationGroup.addActor(dropDownAnimationPreview);
        dieDownAnimationGroup.addActor(dieDownAnimationPreview);


        walkUpAnimationGroup.addActor(walkUpAnimationPreview);
        throwUpAnimationGroup.addActor(throwUpAnimationPreview);
        eatUpAnimationGroup.addActor(eatUpAnimationPreview);
        pickUpUpAnimationGroup.addActor(pickUpUpAnimationPreview);
        thrustUpAnimationGroup.addActor(thrustUpAnimationPreview);
        slashUpAnimationGroup.addActor(slashUpAnimationPreview);
        shootUpAnimationGroup.addActor(shootUpAnimationPreview);
        spellCastUpAnimationGroup.addActor(spellCastUpAnimationPreview);
        grabUpAnimationGroup.addActor(grabUpAnimationPreview);
        dropUpAnimationGroup.addActor(dropUpAnimationPreview);
        dieUpAnimationGroup.addActor(dieUpAnimationPreview);


        walkRightAnimationGroup.addActor(walkRightAnimationPreview);
        throwRightAnimationGroup.addActor(throwRightAnimationPreview);
        eatRightAnimationGroup.addActor(eatRightAnimationPreview);
        pickUpRightAnimationGroup.addActor(pickUpRightAnimationPreview);
        thrustRightAnimationGroup.addActor(thrustRightAnimationPreview);
        slashRightAnimationGroup.addActor(slashRightAnimationPreview);
        shootRightAnimationGroup.addActor(shootRightAnimationPreview);
        spellCastRightAnimationGroup.addActor(spellCastRightAnimationPreview);
        grabRightAnimationGroup.addActor(grabRightAnimationPreview);
        dropRightAnimationGroup.addActor(dropRightAnimationPreview);
        dieRightAnimationGroup.addActor(dieRightAnimationPreview);


        walkLeftAnimationGroup.addActor(walkLeftAnimationPreview);
        throwLeftAnimationGroup.addActor(throwLeftAnimationPreview);
        eatLeftAnimationGroup.addActor(eatLeftAnimationPreview);
        pickUpLeftAnimationGroup.addActor(pickUpLeftAnimationPreview);
        thrustLeftAnimationGroup.addActor(thrustLeftAnimationPreview);
        slashLeftAnimationGroup.addActor(slashLeftAnimationPreview);
        shootLeftAnimationGroup.addActor(shootLeftAnimationPreview);
        spellCastLeftAnimationGroup.addActor(spellCastLeftAnimationPreview);
        grabLeftAnimationGroup.addActor(grabLeftAnimationPreview);
        dropLeftAnimationGroup.addActor(dropLeftAnimationPreview);
        dieLeftAnimationGroup.addActor(dieLeftAnimationPreview);
        







    }



    private void setAtlasRegions() {


        walkUpAnimationPreview.setFrames(body.getWalkUpAnamation());

        thrustUpAnimationPreview.setFrames(body.getThrustAnamationUp());
        dropUpAnimationPreview.setFrames(body.getDropAnamationUp());

        slashUpAnimationPreview.setFrames(body.getSlashAnamationUp());
        shootUpAnimationPreview.setFrames(body.getShootAnamationUp());
        grabUpAnimationPreview.setFrames(body.getGrabAnamationUp());
        pickUpUpAnimationPreview.setFrames(body.getPickUpAnamationUp());
        eatUpAnimationPreview.setFrames(body.getEatAnamationUp());
        throwUpAnimationPreview.setFrames(body.getThrowAnamationUp());
        dieUpAnimationPreview.setFrames(body.getDieAnamationUp());
        spellCastUpAnimationPreview.setFrames(body.getSpellCastAnamationUp());

        walkDownAnimationPreview.setFrames(body.getWalkDownAnamation());

        thrustDownAnimationPreview.setFrames(body.getThrustAnamationDown());
        dropDownAnimationPreview.setFrames(body.getDropAnamationDown());

        slashDownAnimationPreview.setFrames(body.getSlashAnamationDown());
        shootDownAnimationPreview.setFrames(body.getShootAnamationDown());
        grabDownAnimationPreview.setFrames(body.getGrabAnamationDown());
        pickUpDownAnimationPreview.setFrames(body.getPickUpAnamationDown());
        eatDownAnimationPreview.setFrames(body.getEatAnamationDown());
        throwDownAnimationPreview.setFrames(body.getThrowAnamationDown());
        dieDownAnimationPreview.setFrames(body.getDieAnamationDown());
        spellCastDownAnimationPreview.setFrames(body.getSpellCastAnamationDown());

        walkLeftAnimationPreview.setFrames(body.getWalkLeftAnamation());

        thrustLeftAnimationPreview.setFrames(body.getThrustAnamationLeft());
        dropLeftAnimationPreview.setFrames(body.getDropAnamationLeft());

        slashLeftAnimationPreview.setFrames(body.getSlashAnamationLeft());
        shootLeftAnimationPreview.setFrames(body.getShootAnamationLeft());
        grabLeftAnimationPreview.setFrames(body.getGrabAnamationLeft());
        pickUpLeftAnimationPreview.setFrames(body.getPickUpAnamationLeft());
        eatLeftAnimationPreview.setFrames(body.getEatAnamationLeft());
        throwLeftAnimationPreview.setFrames(body.getThrowAnamationLeft());
        dieLeftAnimationPreview.setFrames(body.getDieAnamationLeft());
        spellCastLeftAnimationPreview.setFrames(body.getSpellCastAnamationLeft());

        walkRightAnimationPreview.setFrames(body.getWalkRightAnamation());

        thrustRightAnimationPreview.setFrames(body.getThrustAnamationRight());
        dropRightAnimationPreview.setFrames(body.getDropAnamationRight());

        slashRightAnimationPreview.setFrames(body.getSlashAnamationRight());
        shootRightAnimationPreview.setFrames(body.getShootAnamationRight());
        grabRightAnimationPreview.setFrames(body.getGrabAnamationRight());
        pickUpRightAnimationPreview.setFrames(body.getPickUpAnamationRight());
        eatRightAnimationPreview.setFrames(body.getEatAnamationRight());
        throwRightAnimationPreview.setFrames(body.getThrowAnamationRight());
        dieRightAnimationPreview.setFrames(body.getDieAnamationRight());
        spellCastRightAnimationPreview.setFrames(body.getSpellCastAnamationRight());




    }


    
    public void animationsToBody(final HumanBody4Direction body){
        this.body=body;
        Label nameLabel= new Label("Input The Fighters Name", skin);

      


        name= new TextField("The Name of The Fighter Body", skin);
        name.setText(body.getBodyName());
        HorizontalGroup nameGroup= new HorizontalGroup();
        nameGroup.addActor(nameLabel);
        nameGroup.addActor(name);


        setAnimationsToName= new TextButton("Make Body", skin);


        setAnimationsToName.addListener( new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
               

            makeBody();




            }
        });




        editAnimationGroup= new VerticalGroup();

        dieDownOnly= new CheckBox("is die animation only the down direction" ,skin);
        dieDownOnly.setChecked(body.isDieDownOnly());

        dieForDrop=     dieForDrop= new CheckBox("is die animation used for dropping bodys " ,skin);
        dieForDrop.setChecked(body.isDieForDrop());

        dieForPickup=     dieForPickup= new CheckBox("is die animation used for picking up bodys " ,skin);
        dieForPickup.setChecked(body.isDieForPickup());


        slashForThrow=     slashForThrow= new CheckBox("is the slash animation used for throwimng bodys animation" ,skin);
        lpcCheckBoxGroup=new HorizontalGroup();
        lpcCheckBoxGroup.addActor(dieDownOnly);
        lpcCheckBoxGroup.addActor(dieForPickup);
        lpcCheckBoxGroup.addActor(dieForDrop);
        lpcCheckBoxGroup.addActor(slashForThrow);
        slashForThrow.setChecked(body.isSlashForThrow());

        editAnimations=   new CheckBox("edit the animation Frames" ,skin);

        editAnimations.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    editAnimationGroup.setVisible(true);
                }

                else {
                    editAnimationGroup.setVisible(false);

                }

            }
        });

        usingLPCSprites=   new CheckBox("Using Assetts From The LPC" ,skin);

        usingLPCSprites.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    lpcCheckBoxGroup.setVisible(true);
                }

                else {
                    lpcCheckBoxGroup.setVisible(false);

                }

            }
        });
        findBodyByName= new CheckBox("Find  Animations Using Name", skin);



        window.addEntity(usingLPCSprites);
        window.addEntity(lpcCheckBoxGroup);
        window.addEntity(editAnimations);
        window.addEntity(editAnimationGroup);




        dieDownOnly= new CheckBox("is die animation only the die direction" ,skin);
        dieDownOnly.setChecked(body.isDieDownOnly());

        dieForDrop=     dieForDrop= new CheckBox("is die animation used for dropping bodys " ,skin);
        dieForDrop.setChecked(body.isDieForDrop());

        dieForPickup=     dieForPickup= new CheckBox("is die animation used for picking up bodys " ,skin);
        dieForPickup.setChecked(body.isDieForPickup());


        slashForThrow=     slashForThrow= new CheckBox("is the slash animation used for throwimng bodys animation" ,skin);
        window.addEntity(usingLPCSprites);
        window.addEntity(editAnimations);
        window.addEntity(name);
        slashForThrow.setChecked(body.isSlashForThrow());


        if(usingLPCSprites.isChecked()==true) {
            window.addEntity(slashForThrow);
            window.addEntity(dieDownOnly);
            window.addEntity(dieForDrop);
            window.addEntity(dieForPickup);

        }

        if(editAnimations.isChecked()) {

            walkUpAnimation = new TextureRegionArrayModify(1, body.getWalkUpFrames(), assetts, skin, objects);
            dropUpAnimation = new TextureRegionArrayModify(1, body.getDropUpFrames(), assetts, skin, objects);
            spellCastUpAnimation = new TextureRegionArrayModify(1, body.getSpellCastUpFrames(), assetts, skin, objects);
            thrustUpAnimation = new TextureRegionArrayModify(1, body.getThrustUpFrames(), assetts, skin, objects);
            shootUpAnimation = new TextureRegionArrayModify(1, body.getShootUpFrames(), assetts, skin, objects);
            grabUpAnimation = new TextureRegionArrayModify(1, body.getGrabUpFrames(), assetts, skin, objects);
            pickUpUpAnimation = new TextureRegionArrayModify(1, body.getPickUpUpFrames(), assetts, skin, objects);
            eatUpAnimation = new TextureRegionArrayModify(1, body.getEatUpFrames(), assetts, skin, objects);
            throwUpAnimation = new TextureRegionArrayModify(1, body.getThrowUpFrames(), assetts, skin, objects);
            dieUpAnimation = new TextureRegionArrayModify(1, body.getDieUpFrames(), assetts, skin, objects);
            slashUpAnimation = new TextureRegionArrayModify(1, body.getSlashUpFrames(), assetts, skin, objects);


            walkRightAnimation = new TextureRegionArrayModify(1, body.getWalkRightFrames(), assetts, skin, objects);
            dropRightAnimation = new TextureRegionArrayModify(1, body.getDropRightFrames(), assetts, skin, objects);
            spellCastRightAnimation = new TextureRegionArrayModify(1, body.getSpellCastRightFrames(), assetts, skin, objects);
            thrustRightAnimation = new TextureRegionArrayModify(1, body.getThrustRightFrames(), assetts, skin, objects);
            shootRightAnimation = new TextureRegionArrayModify(1, body.getShootRightFrames(), assetts, skin, objects);
            grabRightAnimation = new TextureRegionArrayModify(1, body.getGrabRightFrames(), assetts, skin, objects);
            pickUpRightAnimation = new TextureRegionArrayModify(1, body.getPickUpRightFrames(), assetts, skin, objects);
            eatRightAnimation = new TextureRegionArrayModify(1, body.getEatRightFrames(), assetts, skin, objects);
            throwRightAnimation = new TextureRegionArrayModify(1, body.getThrowRightFrames(), assetts, skin, objects);
            dieRightAnimation = new TextureRegionArrayModify(1, body.getDieRightFrames(), assetts, skin, objects);
            slashRightAnimation = new TextureRegionArrayModify(1, body.getSlashRightFrames(), assetts, skin, objects);


            walkLeftAnimation = new TextureRegionArrayModify(1, body.getWalkLeftFrames(), assetts, skin, objects);
            dropLeftAnimation = new TextureRegionArrayModify(1, body.getDropLeftFrames(), assetts, skin, objects);
            spellCastLeftAnimation = new TextureRegionArrayModify(1, body.getSpellCastLeftFrames(), assetts, skin, objects);
            thrustLeftAnimation = new TextureRegionArrayModify(1, body.getThrustLeftFrames(), assetts, skin, objects);
            shootLeftAnimation = new TextureRegionArrayModify(1, body.getShootLeftFrames(), assetts, skin, objects);
            grabLeftAnimation = new TextureRegionArrayModify(1, body.getGrabLeftFrames(), assetts, skin, objects);
            pickUpLeftAnimation = new TextureRegionArrayModify(1, body.getPickUpLeftFrames(), assetts, skin, objects);
            eatLeftAnimation = new TextureRegionArrayModify(1, body.getEatLeftFrames(), assetts, skin, objects);
            throwLeftAnimation = new TextureRegionArrayModify(1, body.getThrowLeftFrames(), assetts, skin, objects);
            dieLeftAnimation = new TextureRegionArrayModify(1, body.getDieLeftFrames(), assetts, skin, objects);
            slashLeftAnimation = new TextureRegionArrayModify(1, body.getSlashLeftFrames(), assetts, skin, objects);


            walkDownAnimation = new TextureRegionArrayModify(1, body.getWalkDownFrames(), assetts, skin, objects);
            dropDownAnimation = new TextureRegionArrayModify(1, body.getDropDownFrames(), assetts, skin, objects);
            spellCastDownAnimation = new TextureRegionArrayModify(1, body.getSpellCastDownFrames(), assetts, skin, objects);
            thrustDownAnimation = new TextureRegionArrayModify(1, body.getThrustDownFrames(), assetts, skin, objects);
            shootDownAnimation = new TextureRegionArrayModify(1, body.getShootDownFrames(), assetts, skin, objects);
            grabDownAnimation = new TextureRegionArrayModify(1, body.getGrabDownFrames(), assetts, skin, objects);
            pickUpDownAnimation = new TextureRegionArrayModify(1, body.getPickUpDownFrames(), assetts, skin, objects);
            eatDownAnimation = new TextureRegionArrayModify(1, body.getEatDownFrames(), assetts, skin, objects);
            throwDownAnimation = new TextureRegionArrayModify(1, body.getThrowDownFrames(), assetts, skin, objects);
            dieDownAnimation = new TextureRegionArrayModify(1, body.getDieDownFrames(), assetts, skin, objects);
            slashDownAnimation = new TextureRegionArrayModify(1, body.getSlashDownFrames(), assetts, skin, objects);


            walkDownAnimationGroup.addActor(walkDownAnimation.getHGroup());
            throwDownAnimationGroup.addActor(throwDownAnimation.getHGroup());
            eatDownAnimationGroup.addActor(eatDownAnimation.getHGroup());
            pickUpDownAnimationGroup.addActor(pickUpDownAnimation.getHGroup());
            thrustDownAnimationGroup.addActor(thrustDownAnimation.getHGroup());
            slashDownAnimationGroup.addActor(slashDownAnimation.getHGroup());
            shootDownAnimationGroup.addActor(shootDownAnimation.getHGroup());
            spellCastDownAnimationGroup.addActor(spellCastDownAnimation.getHGroup());
            grabDownAnimationGroup.addActor(grabDownAnimation.getHGroup());
            dropDownAnimationGroup.addActor(dropDownAnimation.getHGroup());
            dieDownAnimationGroup.addActor(dieDownAnimation.getHGroup());


            walkUpAnimationGroup.addActor(walkUpAnimation.getHGroup());
            throwUpAnimationGroup.addActor(throwUpAnimation.getHGroup());
            eatUpAnimationGroup.addActor(eatUpAnimation.getHGroup());
            pickUpUpAnimationGroup.addActor(pickUpUpAnimation.getHGroup());
            thrustUpAnimationGroup.addActor(thrustUpAnimation.getHGroup());
            slashUpAnimationGroup.addActor(slashUpAnimation.getHGroup());
            shootUpAnimationGroup.addActor(shootUpAnimation.getHGroup());
            spellCastUpAnimationGroup.addActor(spellCastUpAnimation.getHGroup());
            grabUpAnimationGroup.addActor(grabUpAnimation.getHGroup());
            dropUpAnimationGroup.addActor(dropUpAnimation.getHGroup());
            dieUpAnimationGroup.addActor(dieUpAnimation.getHGroup());


            walkRightAnimationGroup.addActor(walkRightAnimation.getHGroup());
            throwRightAnimationGroup.addActor(throwRightAnimation.getHGroup());
            eatRightAnimationGroup.addActor(eatRightAnimation.getHGroup());
            pickUpRightAnimationGroup.addActor(pickUpRightAnimation.getHGroup());
            thrustRightAnimationGroup.addActor(thrustRightAnimation.getHGroup());
            slashRightAnimationGroup.addActor(slashRightAnimation.getHGroup());
            shootRightAnimationGroup.addActor(shootRightAnimation.getHGroup());
            spellCastRightAnimationGroup.addActor(spellCastRightAnimation.getHGroup());
            grabRightAnimationGroup.addActor(grabRightAnimation.getHGroup());
            dropRightAnimationGroup.addActor(dropRightAnimation.getHGroup());
            dieRightAnimationGroup.addActor(dieRightAnimation.getHGroup());


            walkLeftAnimationGroup.addActor(walkLeftAnimation.getHGroup());
            throwLeftAnimationGroup.addActor(throwLeftAnimation.getHGroup());
            eatLeftAnimationGroup.addActor(eatLeftAnimation.getHGroup());
            pickUpLeftAnimationGroup.addActor(pickUpLeftAnimation.getHGroup());
            thrustLeftAnimationGroup.addActor(thrustLeftAnimation.getHGroup());
            slashLeftAnimationGroup.addActor(slashLeftAnimation.getHGroup());
            shootLeftAnimationGroup.addActor(shootLeftAnimation.getHGroup());
            spellCastLeftAnimationGroup.addActor(spellCastLeftAnimation.getHGroup());
            grabLeftAnimationGroup.addActor(grabLeftAnimation.getHGroup());
            dropLeftAnimationGroup.addActor(dropLeftAnimation.getHGroup());
            dieLeftAnimationGroup.addActor(dieLeftAnimation.getHGroup());


            walkUpAnimationPreview = new AnimationPreview(body.getWalkUpFrames() * 32 + 1, 600);

            thrustUpAnimationPreview = new AnimationPreview(body.getThrustUpFrames() * 32 + 1, 600 + 32 + 10);
            dropUpAnimationPreview = new AnimationPreview(body.getDropUpFrames() * 32 + 1, 600 + 32 + 10);

            slashUpAnimationPreview = new AnimationPreview(body.getSlashUpFrames() * 32 + 1, 600 + 32 * 2 + 10);
            shootUpAnimationPreview = new AnimationPreview(body.getShootUpFrames() * 32 + 1, 600 + 32 * 3 + 10);
            grabUpAnimationPreview = new AnimationPreview(body.getGrabUpFrames() * 32 + 1, 600 + 32 * 4 + 10);
            pickUpUpAnimationPreview = new AnimationPreview(body.getPickUpUpFrames() * 32 + 1, 600 + 32 * 5 + 10);
            eatUpAnimationPreview = new AnimationPreview(body.getEatUpFrames() * 32 + 1, 600 + 32 * 6 + 10);
            throwUpAnimationPreview = new AnimationPreview(body.getThrowUpFrames() * 32 + 1, 600 + 32 * 7 + 10);
            dieUpAnimationPreview = new AnimationPreview(body.getDieUpFrames() * 32 + 1, 600 + 32 * 8 + 10);
            spellCastUpAnimationPreview = new AnimationPreview(body.getSpellCastUpFrames() * 32 + 1, 600 + 32 * 9 + 10);


            walkDownAnimationPreview = new AnimationPreview(body.getWalkDownFrames() * 32 + 1, 600);

            thrustDownAnimationPreview = new AnimationPreview(body.getThrustDownFrames() * 32 + 1, 600 + 32 + 10);
            dropDownAnimationPreview = new AnimationPreview(body.getDropDownFrames() * 32 + 1, 600 + 32 + 10);

            slashDownAnimationPreview = new AnimationPreview(body.getSlashDownFrames() * 32 + 1, 600 + 32 * 2 + 10);
            shootDownAnimationPreview = new AnimationPreview(body.getShootDownFrames() * 32 + 1, 600 + 32 * 3 + 10);
            grabDownAnimationPreview = new AnimationPreview(body.getGrabDownFrames() * 32 + 1, 600 + 32 * 4 + 10);
            pickUpDownAnimationPreview = new AnimationPreview(body.getPickUpDownFrames() * 32 + 1, 600 + 32 * 5 + 10);
            eatDownAnimationPreview = new AnimationPreview(body.getEatDownFrames() * 32 + 1, 600 + 32 * 6 + 10);
            throwDownAnimationPreview = new AnimationPreview(body.getThrowDownFrames() * 32 + 1, 600 + 32 * 7 + 10);
            dieDownAnimationPreview = new AnimationPreview(body.getDieDownFrames() * 32 + 1, 600 + 32 * 8 + 10);
            spellCastDownAnimationPreview = new AnimationPreview(body.getSpellCastDownFrames() * 32 + 1, 600 + 32 * 9 + 10);


            walkLeftAnimationPreview = new AnimationPreview(body.getWalkLeftFrames() * 32 + 1, 600);

            thrustLeftAnimationPreview = new AnimationPreview(body.getThrustLeftFrames() * 32 + 1, 600 + 32 + 10);
            dropLeftAnimationPreview = new AnimationPreview(body.getDropLeftFrames() * 32 + 1, 600 + 32 + 10);

            slashLeftAnimationPreview = new AnimationPreview(body.getSlashLeftFrames() * 32 + 1, 600 + 32 * 2 + 10);
            shootLeftAnimationPreview = new AnimationPreview(body.getShootLeftFrames() * 32 + 1, 600 + 32 * 3 + 10);
            grabLeftAnimationPreview = new AnimationPreview(body.getGrabLeftFrames() * 32 + 1, 600 + 32 * 4 + 10);
            pickUpLeftAnimationPreview = new AnimationPreview(body.getPickUpLeftFrames() * 32 + 1, 600 + 32 * 5 + 10);
            eatLeftAnimationPreview = new AnimationPreview(body.getEatLeftFrames() * 32 + 1, 600 + 32 * 6 + 10);
            throwLeftAnimationPreview = new AnimationPreview(body.getThrowLeftFrames() * 32 + 1, 600 + 32 * 7 + 10);
            dieLeftAnimationPreview = new AnimationPreview(body.getDieLeftFrames() * 32 + 1, 600 + 32 * 8 + 10);
            spellCastLeftAnimationPreview = new AnimationPreview(body.getSpellCastLeftFrames() * 32 + 1, 600 + 32 * 9 + 10);


            walkRightAnimationPreview = new AnimationPreview(body.getWalkRightFrames() * 32 + 1, 600);

            thrustRightAnimationPreview = new AnimationPreview(body.getThrustRightFrames() * 32 + 1, 600 + 32 + 10);
            dropRightAnimationPreview = new AnimationPreview(body.getDropRightFrames() * 32 + 1, 600 + 32 + 10);

            slashRightAnimationPreview = new AnimationPreview(body.getSlashRightFrames() * 32 + 1, 600 + 32 * 2 + 10);
            shootRightAnimationPreview = new AnimationPreview(body.getShootRightFrames() * 32 + 1, 600 + 32 * 3 + 10);
            grabRightAnimationPreview = new AnimationPreview(body.getGrabRightFrames() * 32 + 1, 600 + 32 * 4 + 10);
            pickUpRightAnimationPreview = new AnimationPreview(body.getPickUpRightFrames() * 32 + 1, 600 + 32 * 5 + 10);
            eatRightAnimationPreview = new AnimationPreview(body.getEatRightFrames() * 32 + 1, 600 + 32 * 6 + 10);
            throwRightAnimationPreview = new AnimationPreview(body.getThrowRightFrames() * 32 + 1, 600 + 32 * 7 + 10);
            dieRightAnimationPreview = new AnimationPreview(body.getDieRightFrames() * 32 + 1, 600 + 32 * 8 + 10);
            spellCastRightAnimationPreview = new AnimationPreview(body.getSpellCastRightFrames() * 32 + 1, 600 + 32 * 9 + 10);


            walkDownAnimationGroup.addActor(walkDownAnimationPreview);
            throwDownAnimationGroup.addActor(throwDownAnimationPreview);
            eatDownAnimationGroup.addActor(eatDownAnimationPreview);
            pickUpDownAnimationGroup.addActor(pickUpDownAnimationPreview);
            thrustDownAnimationGroup.addActor(thrustDownAnimationPreview);
            slashDownAnimationGroup.addActor(slashDownAnimationPreview);
            shootDownAnimationGroup.addActor(shootDownAnimationPreview);
            spellCastDownAnimationGroup.addActor(spellCastDownAnimationPreview);
            grabDownAnimationGroup.addActor(grabDownAnimationPreview);
            dropDownAnimationGroup.addActor(dropDownAnimationPreview);
            dieDownAnimationGroup.addActor(dieDownAnimationPreview);


            walkUpAnimationGroup.addActor(walkUpAnimationPreview);
            throwUpAnimationGroup.addActor(throwUpAnimationPreview);
            eatUpAnimationGroup.addActor(eatUpAnimationPreview);
            pickUpUpAnimationGroup.addActor(pickUpUpAnimationPreview);
            thrustUpAnimationGroup.addActor(thrustUpAnimationPreview);
            slashUpAnimationGroup.addActor(slashUpAnimationPreview);
            shootUpAnimationGroup.addActor(shootUpAnimationPreview);
            spellCastUpAnimationGroup.addActor(spellCastUpAnimationPreview);
            grabUpAnimationGroup.addActor(grabUpAnimationPreview);
            dropUpAnimationGroup.addActor(dropUpAnimationPreview);
            dieUpAnimationGroup.addActor(dieUpAnimationPreview);


            walkRightAnimationGroup.addActor(walkRightAnimationPreview);
            throwRightAnimationGroup.addActor(throwRightAnimationPreview);
            eatRightAnimationGroup.addActor(eatRightAnimationPreview);
            pickUpRightAnimationGroup.addActor(pickUpRightAnimationPreview);
            thrustRightAnimationGroup.addActor(thrustRightAnimationPreview);
            slashRightAnimationGroup.addActor(slashRightAnimationPreview);
            shootRightAnimationGroup.addActor(shootRightAnimationPreview);
            spellCastRightAnimationGroup.addActor(spellCastRightAnimationPreview);
            grabRightAnimationGroup.addActor(grabRightAnimationPreview);
            dropRightAnimationGroup.addActor(dropRightAnimationPreview);
            dieRightAnimationGroup.addActor(dieRightAnimationPreview);


            walkLeftAnimationGroup.addActor(walkLeftAnimationPreview);
            throwLeftAnimationGroup.addActor(throwLeftAnimationPreview);
            eatLeftAnimationGroup.addActor(eatLeftAnimationPreview);
            pickUpLeftAnimationGroup.addActor(pickUpLeftAnimationPreview);
            thrustLeftAnimationGroup.addActor(thrustLeftAnimationPreview);
            slashLeftAnimationGroup.addActor(slashLeftAnimationPreview);
            shootLeftAnimationGroup.addActor(shootLeftAnimationPreview);
            spellCastLeftAnimationGroup.addActor(spellCastLeftAnimationPreview);
            grabLeftAnimationGroup.addActor(grabLeftAnimationPreview);
            dropLeftAnimationGroup.addActor(dropLeftAnimationPreview);
            dieLeftAnimationGroup.addActor(dieLeftAnimationPreview);


            editAnimationGroup.addActor(walkDownAnimationGroup);
            editAnimationGroup.addActor(throwDownAnimationGroup);
            editAnimationGroup.addActor(eatDownAnimationGroup);
            editAnimationGroup.addActor(pickUpDownAnimationGroup);
            editAnimationGroup.addActor(thrustDownAnimationGroup);
            editAnimationGroup.addActor(slashDownAnimationGroup);
            editAnimationGroup.addActor(shootDownAnimationGroup);
            editAnimationGroup.addActor(spellCastDownAnimationGroup);
            editAnimationGroup.addActor(grabDownAnimationGroup);
            editAnimationGroup.addActor(dropDownAnimationGroup);
            editAnimationGroup.addActor(dieDownAnimationGroup);


            editAnimationGroup.addActor(walkUpAnimationGroup);
            editAnimationGroup.addActor(throwUpAnimationGroup);
            editAnimationGroup.addActor(eatUpAnimationGroup);
            editAnimationGroup.addActor(pickUpUpAnimationGroup);
            editAnimationGroup.addActor(thrustUpAnimationGroup);
            editAnimationGroup.addActor(slashUpAnimationGroup);
            editAnimationGroup.addActor(shootUpAnimationGroup);
            editAnimationGroup.addActor(spellCastUpAnimationGroup);
            editAnimationGroup.addActor(grabUpAnimationGroup);
            editAnimationGroup.addActor(dropUpAnimationGroup);
            editAnimationGroup.addActor(dieUpAnimationGroup);


            editAnimationGroup.addActor(walkLeftAnimationGroup);
            editAnimationGroup.addActor(throwLeftAnimationGroup);
            editAnimationGroup.addActor(eatLeftAnimationGroup);
            editAnimationGroup.addActor(pickUpLeftAnimationGroup);
            editAnimationGroup.addActor(thrustLeftAnimationGroup);
            editAnimationGroup.addActor(slashLeftAnimationGroup);
            editAnimationGroup.addActor(shootLeftAnimationGroup);
            editAnimationGroup.addActor(spellCastLeftAnimationGroup);
            editAnimationGroup.addActor(grabLeftAnimationGroup);
            editAnimationGroup.addActor(dropLeftAnimationGroup);
            editAnimationGroup.addActor(dieLeftAnimationGroup);


            editAnimationGroup.addActor(walkRightAnimationGroup);
            editAnimationGroup.addActor(throwRightAnimationGroup);
            editAnimationGroup.addActor(eatRightAnimationGroup);
            editAnimationGroup.addActor(pickUpRightAnimationGroup);
            editAnimationGroup.addActor(thrustRightAnimationGroup);
            editAnimationGroup.addActor(slashRightAnimationGroup);
            editAnimationGroup.addActor(shootRightAnimationGroup);
            editAnimationGroup.addActor(spellCastRightAnimationGroup);
            editAnimationGroup.addActor(grabRightAnimationGroup);
            editAnimationGroup.addActor(dropRightAnimationGroup);
            editAnimationGroup.addActor(dieRightAnimationGroup);


            walkDownAnimation.setAtlasRegions(body.getWalkDownAnamation());
            dropDownAnimation.setAtlasRegions(body.getDropAnamationDown());
            spellCastDownAnimation.setAtlasRegions(body.getSpellCastAnamationDown());
            thrustDownAnimation.setAtlasRegions(body.getThrustAnamationDown());
            shootDownAnimation.setAtlasRegions(body.getShootAnamationDown());
            grabDownAnimation.setAtlasRegions(body.getGrabAnamationDown());
            pickUpDownAnimation.setAtlasRegions(body.getPickUpAnamationDown());
            eatDownAnimation.setAtlasRegions(body.getEatAnamationDown());
            throwDownAnimation.setAtlasRegions(body.getThrowAnamationDown());
            dieDownAnimation.setAtlasRegions(body.getDieAnamationDown());
            slashDownAnimation.setAtlasRegions(body.getSlashAnamationDown());


            walkUpAnimation.setAtlasRegions(body.getWalkUpAnamation());
            dropUpAnimation.setAtlasRegions(body.getDropAnamationUp());
            spellCastUpAnimation.setAtlasRegions(body.getSpellCastAnamationUp());
            thrustUpAnimation.setAtlasRegions(body.getThrustAnamationUp());
            shootUpAnimation.setAtlasRegions(body.getShootAnamationUp());
            grabUpAnimation.setAtlasRegions(body.getGrabAnamationUp());
            pickUpUpAnimation.setAtlasRegions(body.getPickUpAnamationUp());
            eatUpAnimation.setAtlasRegions(body.getEatAnamationUp());
            throwUpAnimation.setAtlasRegions(body.getThrowAnamationUp());
            dieUpAnimation.setAtlasRegions(body.getDieAnamationUp());
            slashUpAnimation.setAtlasRegions(body.getSlashAnamationUp());


            walkLeftAnimation.setAtlasRegions(body.getWalkLeftAnamation());
            dropLeftAnimation.setAtlasRegions(body.getDropAnamationLeft());
            spellCastLeftAnimation.setAtlasRegions(body.getSpellCastAnamationLeft());
            thrustLeftAnimation.setAtlasRegions(body.getThrustAnamationLeft());
            shootLeftAnimation.setAtlasRegions(body.getShootAnamationLeft());
            grabLeftAnimation.setAtlasRegions(body.getGrabAnamationLeft());
            pickUpLeftAnimation.setAtlasRegions(body.getPickUpAnamationLeft());
            eatLeftAnimation.setAtlasRegions(body.getEatAnamationLeft());
            throwLeftAnimation.setAtlasRegions(body.getThrowAnamationLeft());
            dieLeftAnimation.setAtlasRegions(body.getDieAnamationLeft());
            slashLeftAnimation.setAtlasRegions(body.getSlashAnamationLeft());


            walkRightAnimation.setAtlasRegions(body.getWalkRightAnamation());
            dropRightAnimation.setAtlasRegions(body.getDropAnamationRight());
            spellCastRightAnimation.setAtlasRegions(body.getSpellCastAnamationRight());
            thrustRightAnimation.setAtlasRegions(body.getThrustAnamationRight());
            shootRightAnimation.setAtlasRegions(body.getShootAnamationRight());
            grabRightAnimation.setAtlasRegions(body.getGrabAnamationRight());
            pickUpRightAnimation.setAtlasRegions(body.getPickUpAnamationRight());
            eatRightAnimation.setAtlasRegions(body.getEatAnamationRight());
            throwRightAnimation.setAtlasRegions(body.getThrowAnamationRight());
            dieRightAnimation.setAtlasRegions(body.getDieAnamationRight());
            slashRightAnimation.setAtlasRegions(body.getSlashAnamationRight());

        }
        
    }



    public HumanBody4Direction makeBody(){
        String name=this.name.getText();
        body.setBodyName(name);
        body.setSlashForThrow(slashForThrow.isChecked());
        body.setDieForPickup(dieForPickup.isChecked());
        body.setDieForDrop(dieForDrop.isChecked());
        body.setDieDownOnly(dieDownOnly.isChecked());
        if (findBodyByName.isChecked()) {
            AtlasRegion region = assetts.getAtlasRegionByName(name+"walkUp0", "assetts.atlas");
        
        

            
            
        if(region!=null) {  // if textureRegion is not null name is valid
            body.makeBody(assetts, body.getBodyName(), new Color(1,1,1,1), atlasName.getText());

        }
        

        }
        
        else{

            body.setWalkDownAnamation(walkDownAnimation.getFrames());
            body.setDropAnamationDown(dropDownAnimation.getFrames());  
            body.setThrustAnamationDown(thrustDownAnimation.getFrames());
            body.setSpellCastAnamationDown(spellCastDownAnimation.getFrames());
            body.setShootAnamationDown(shootDownAnimation.getFrames());
            body.setGrabAnamationDown(grabDownAnimation.getFrames());
            body.setPickUpAnamationDown(pickUpDownAnimation.getFrames());
            body.setEatAnamationDown(eatDownAnimation.getFrames());
            body.setThrowAnamationDown(throwDownAnimation.getFrames());
            body.setDieAnamationDown(dieDownAnimation.getFrames());
            body.setSlashAnamationDown(slashDownAnimation.getFrames());


            body.setWalkUpAnamation(walkUpAnimation.getFrames());
            body.setDropAnamationUp(dropUpAnimation.getFrames());
            body.setThrustAnamationUp(thrustUpAnimation.getFrames());
            body.setSpellCastAnamationUp(spellCastUpAnimation.getFrames());
            body.setShootAnamationUp(shootUpAnimation.getFrames());
            body.setGrabAnamationUp(grabUpAnimation.getFrames());
            body.setPickUpAnamationUp(pickUpUpAnimation.getFrames());
            body.setEatAnamationUp(eatUpAnimation.getFrames());
            body.setThrowAnamationUp(throwUpAnimation.getFrames());
            body.setDieAnamationUp(dieUpAnimation.getFrames());
            body.setSlashAnamationUp(slashUpAnimation.getFrames());


            body.setWalkLeftAnamation(walkLeftAnimation.getFrames());
            body.setDropAnamationLeft(dropLeftAnimation.getFrames());
            body.setThrustAnamationLeft(thrustLeftAnimation.getFrames());
            body.setSpellCastAnamationLeft(spellCastLeftAnimation.getFrames());
            body.setShootAnamationLeft(shootLeftAnimation.getFrames());
            body.setGrabAnamationLeft(grabLeftAnimation.getFrames());
            body.setPickUpAnamationLeft(pickUpLeftAnimation.getFrames());
            body.setEatAnamationLeft(eatLeftAnimation.getFrames());
            body.setThrowAnamationLeft(throwLeftAnimation.getFrames());
            body.setDieAnamationLeft(dieLeftAnimation.getFrames());
            body.setSlashAnamationLeft(slashLeftAnimation.getFrames());


            body.setWalkRightAnamation(walkRightAnimation.getFrames());
            body.setDropAnamationRight(dropRightAnimation.getFrames());
            body.setThrustAnamationRight(thrustRightAnimation.getFrames());
            body.setSpellCastAnamationRight(spellCastRightAnimation.getFrames());
            body.setShootAnamationRight(shootRightAnimation.getFrames());
            body.setGrabAnamationRight(grabRightAnimation.getFrames());
            body.setPickUpAnamationRight(pickUpRightAnimation.getFrames());
            body.setEatAnamationRight(eatRightAnimation.getFrames());
            body.setThrowAnamationRight(throwRightAnimation.getFrames());
            body.setDieAnamationRight(dieRightAnimation.getFrames());
            body.setSlashAnamationRight(slashRightAnimation.getFrames());



        }
        objects.addEntity(body);
        return body;
        
        
    }
    
    
    */
    
}
