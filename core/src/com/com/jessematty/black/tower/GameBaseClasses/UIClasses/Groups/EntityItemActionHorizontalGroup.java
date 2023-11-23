package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponents;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntitySettable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

/**
 * ui class that extends the libGDX HorizontalGroup
 * that contains and entities name, image ( if it has one) and button to pick one
 */
public   class EntityItemActionHorizontalGroup extends HorizontalGroup {
    /**
     * libGDX skin
     */
    private Skin skin;
    /**
     * the ashely  Entity object
     */
    private Entity entity;

    /**
     * game assets object used to retrieve an entities  image
     */
    private GameAssets gameAssets;
    /**
     * the entity  settable object to set
     */
    private EntitySettable entitySettable;
    public EntityItemActionHorizontalGroup(EntitySettable entitySettable, GameAssets gameAssets, Skin skin, Entity entity) {
        this.skin = skin;
        this.entity = entity;
        this.gameAssets=gameAssets;
        this.entitySettable=entitySettable;
        makeGroup();
    }

    /**
     *  makes the horizontal group
     */
    private void makeGroup(){
        NameComponent nameComponent = GameComponentMapper.getNameComponentMapper().get(entity);
        String name="*** Name Missing ***";
        if(nameComponent!=null) {
        name=nameComponent.getName();
        }
        Label label= new Label(name, skin);
        addActor(label);
        Image image= new Image(gameAssets.getAtlasRegionByName("noImage", "textureAtlases/testAssets/testAssets.atlas"));
        ImageComponent imageComponent=GameComponentMapper.getImageComponentMapper().get(entity);
        if(imageComponent!=null && imageComponent.getImage()!=null){
           image=imageComponent.getImage();
        }
        addActor(image);
        addActor(label);
        ItemActionComponents itemActionComponents= GameComponentMapper.getActionComponentsComponentMapper().get(entity);
        Array<ItemActionComponent> itemActionComponentArray=itemActionComponents.getActionComponents();
        for(ItemActionComponent itemActionComponent: itemActionComponentArray){

        }
        space(10);
    }
    public Skin getSkin() {
        return skin;
    }
    public Entity getEntity() {
        return entity;
    }
}
