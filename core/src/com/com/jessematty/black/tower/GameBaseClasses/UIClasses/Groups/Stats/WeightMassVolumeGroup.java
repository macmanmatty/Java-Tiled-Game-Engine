package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.Stats;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.World;



public class WeightMassVolumeGroup extends VerticalGroup {

    Entity entity;
    Skin skin;

    World world;

    public WeightMassVolumeGroup(Entity entity, Skin skin, World world) {
        this.entity = entity;
        this.skin = skin;
        this.world = world;
        makeUI();
    }

    public void makeUI() {
        double[] weightMassVolume = EntityUtilities.getMassVolumeWeight(world, entity);
        Label mass = new Label("Mass: " + weightMassVolume[0], skin);
        Label weight = new Label("Weight: " + weightMassVolume[1], skin);
        Label volume = new Label("Volume: " + weightMassVolume[2], skin);
        addActor(weight);
       addActor(mass);
        addActor(volume);
        double[] totalWeightMassVolume = EntityUtilities.getEntityMassAndVolume(world, entity);
        HorizontalGroup totalWeightMassVolumeGroup = new HorizontalGroup();
        Label total = new Label("Weight:", skin);
        Label totalMass = new Label("  Mass: " + totalWeightMassVolume[0], skin);
        Label totalVolume = new Label(" Volume: " + totalWeightMassVolume[1], skin);
        totalWeightMassVolumeGroup.addActor(total);
        totalWeightMassVolumeGroup.addActor(totalMass);
        totalWeightMassVolumeGroup.addActor(totalVolume);
        totalWeightMassVolumeGroup.space(10);
    }
}
