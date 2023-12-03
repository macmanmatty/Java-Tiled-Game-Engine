package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.Entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntitySettable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

/**
 * ui class that extends the libGDX HorizontalGroup
 * that contains and entities name, image ( if it has one) and button to pick one
 */
public   class EntityActionHorizontalGroup extends HorizontalGroup {
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
     * the button to select and entity
     */
    private Button select;
    /**
     * the entity  settable object to set
     */
    private EntitySettable entitySettable;
    public EntityActionHorizontalGroup(EntitySettable entitySettable, GameAssets gameAssets, Skin skin, Entity entity) {
        this.skin = skin;
        this.entity = entity;
        this.gameAssets=gameAssets;
        this.entitySettable=entitySettable;
        select=new TextButton("Select", skin, "Brick");
        select.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
                entitySettable.setEntity(entity);}
        });
        makeGroup();
    }

    /**
     *  makes the horizontal group
     */
    private void makeGroup(){
        NameComponent nameComponent = GameComponentMapper.getNameComponentMapper().get(entity);
        String name="*** Name Missing ***";
        if(nameComponent!=null) {
        name=nameComponent.getStat();
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
        addActor(select);
        space(10);
    }
    public Skin getSkin() {
        return skin;
    }
    public Entity getEntity() {
        return entity;
    }
    public Button getSelect() {
        return select;
    }
}
