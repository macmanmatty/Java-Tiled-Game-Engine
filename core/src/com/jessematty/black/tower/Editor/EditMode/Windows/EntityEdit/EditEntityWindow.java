package com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;

public class EditEntityWindow  extends MapEditWindow implements ClipBoardChangeListener {


   private  Entity entity;
   private List<Component> componentList;
   private Button editComponent;
   private SelectBox<Stat> statsList;
   private SelectBox<Animation> animationSelectBox;
   private SelectBox<ImageComponent>  componentSelectBox;
   private SelectBox<Drawable> drawableSelectBox;





    public EditEntityWindow(MapEditScreen mapEditScreen,  Skin skin) {
        super(mapEditScreen, "Entity Edit", skin, "default");
        statsList= new SelectBox<Stat>(skin);
        animationSelectBox= new SelectBox<Animation>(skin);
        componentSelectBox= new SelectBox<ImageComponent>(skin);
        drawableSelectBox= new SelectBox<Drawable>(skin);





    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        makeWindow();
    }

    @Override
    public void makeWindow() {

    }

    public void  noInformation(){


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
