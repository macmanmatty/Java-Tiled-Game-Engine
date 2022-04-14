package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;

public class ProceduralTreeEdit extends PlantEdit {

    TextButton makeTree;
    PositiveFloatField floatXModify;
    PositiveFloatField floatYModify;
    ImageButton trunkTexture;
    PositiveIntegerField maxLeaves;
    PositiveIntegerField minLeaves;
    VerticalGroup proceduralTreeGroup;
    CheckBox showProceduralTreeGroup;
    RandomNumbers value= new RandomNumbers();
    TextField minFruit;
    TextField maxFruit;













    public ProceduralTreeEdit() {

        showProceduralTreeGroup=new CheckBox("Show Procedural Tree Fields To Edit", skin);

       showProceduralTreeGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    proceduralTreeGroup.setVisible(true);
                }

                else {
                    proceduralTreeGroup.setVisible(false);

                }

            }
        });

        proceduralTreeGroup= new VerticalGroup();

        trunkTexture = new ImageButton((Drawable) null);
        trunkTexture.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ImageButton imageButton = (ImageButton) event.getTarget();



                return true;

            }
        });



        makeTree=new TextButton("Make Tree", skin);
        makeTree.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                float fruit=value.getRandomNumber(Integer.valueOf(minFruit.getText()), Integer.valueOf(maxFruit.getText()));
                float numberOfLeaves=value.getRandomNumber(Integer.valueOf(minLeaves.getText()), Integer.valueOf(maxLeaves.getText()));


            }
        });


        minLeaves= new PositiveIntegerField("", skin);
        maxLeaves= new PositiveIntegerField("", skin);
        floatXModify= new PositiveFloatField("", skin);
        floatYModify= new PositiveFloatField("", skin);
        Label minLeavesLabel= new Label("The Minimum cluster of leavesToMake on the Tree", skin);
        Label maxLeavesLabel= new Label("The Maximum cluster of leavesToMake on the Tree", skin);
        Label floatXLabel= new Label("Tree Draw X Modify", skin);
        Label floatYLabel= new Label("Tree Draw Y Modify", skin);
        HorizontalGroup minLeavesGroup = new HorizontalGroup();
        minLeavesGroup.addActor(minLeavesLabel);
        minLeavesGroup.addActor(minLeaves);

        HorizontalGroup maxLeavesGroup= new HorizontalGroup();
        maxLeavesGroup.addActor(maxLeavesLabel);
        maxLeavesGroup.addActor(maxLeaves);



        HorizontalGroup treeModXGroup = new HorizontalGroup();
        treeModXGroup.addActor(floatXLabel);
        treeModXGroup.addActor(floatXModify);

        HorizontalGroup treeModYGroup= new HorizontalGroup();
        treeModYGroup.addActor(floatYLabel);
        treeModYGroup.addActor(floatYModify);
        proceduralTreeGroup.addActor(maxLeavesGroup);
        proceduralTreeGroup.addActor(minLeavesGroup);
        proceduralTreeGroup.addActor(treeModXGroup);
        proceduralTreeGroup.addActor(treeModYGroup);
        proceduralTreeGroup.addActor(makeTree);
        window.addActor(showProceduralTreeGroup);
        window.addActor(proceduralTreeGroup);








    }

}
