package com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.Tools.EntityTools.EntityTools;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.DragLists.TwoWayDragList;

public class EditEntityWindow  extends MapEditWindow implements ClipBoardChangeListener {


   private  Entity entity;
   private TwoWayDragList<NamedComponent> componentList;
   private Button editComponents;
   private Button goToEntity;
   private Image entityImage;
   private Image noEntityImage;
   private Label name;
   private Label position;










    public EditEntityWindow(final MapEditScreen mapEditScreen, Skin skin) {
        super(mapEditScreen, "Entity Edit", skin, "default");
        componentList=new TwoWayDragList<>(skin, mapEditScreen.getWorldObjects().getComponents(), null, "Components", "Components in Entity");
            makeWindow();





    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        entityImage = EntityTools.getEntityImage(entity);
        if(entityImage==null ){

            entityImage= noEntityImage;
        }
        makeWindow();
    }

    @Override
    public void makeWindow() {



        noEntityImage=  new Image( new TextureRegionDrawable(getMapEditScreen().getGameAssets().getAtlasRegionByName("questionMark", "editorAssets")));
        entityImage= noEntityImage;
        goToEntity=  new ImageButton( new TextureRegionDrawable(getMapEditScreen().getGameAssets().getAtlasRegionByName("search", "editorAssets")));
        goToEntity.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                EntityTools.goToEntity(getMapEditScreen(), entity);
                return  true;

            }
        });

        editComponents= new TextButton("Edit Components", skin);
        editComponents.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;

            }
        });

        name= new Label("lebel", skin);





        add(goToEntity);
        add(entityImage);
        add(editComponents);






    }

    public void  noInformation(){

        clearWindow();



    }



    @Override
    public void objectChanged(Object object) {

        if(object instanceof  Entity){

            setEntity((Entity) object);
        }

        else{

            noInformation();
        }

    }
}
