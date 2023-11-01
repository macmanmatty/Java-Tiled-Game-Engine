package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;
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
import com.jessematty.black.tower.Components.Other.InfoComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntitySettable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

public   class EntityHorizontalGroup extends HorizontalGroup {
    private Skin skin;
    private Entity entity;

    private GameAssets gameAssets;

    private Button select;

    private EntitySettable entitySettable;

    public EntityHorizontalGroup(EntitySettable entitySettable, GameAssets gameAssets, Skin skin, Entity entity) {
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
        addActor(select);
        space(10);



    }


    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Entity getEntity() {
        return entity;
    }

    public Button getSelect() {
        return select;
    }

}

