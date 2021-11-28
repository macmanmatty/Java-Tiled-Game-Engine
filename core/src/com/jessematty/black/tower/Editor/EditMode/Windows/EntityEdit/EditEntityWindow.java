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
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjectSettable;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.Editor.Tools.EntityTools.EntityTools;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntitySettable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.DragLists.TwoWayDragList;

public class EditEntityWindow  extends EditWindow implements ClipBoardChangeListener, WorldObjectSettable, EntitySettable {
   private  Entity entity;
   private TwoWayDragList<NamedComponent> componentList;
   private Button editComponents;
   private Button goToEntity;
   private Image entityImage;
   private Image noEntityImage;
   private Label name;
   private Label position;
   private WorldObjects worldObjects;
    public EditEntityWindow(WorldObjects worldObjects, GameAssets gameAssets, Skin skin) {
        super(gameAssets, "Entity Edit", skin, "default");
        this.worldObjects=worldObjects;
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
        componentList=new TwoWayDragList<>(skin, worldObjects.getComponents(), null, "Components", "Components in Entity");
        noEntityImage=  new Image( new TextureRegionDrawable(getGameAssets().getInternalAtlasRegionByName("questionMark", "editorAssets")));
        entityImage= noEntityImage;
        goToEntity=  new ImageButton( new TextureRegionDrawable(getGameAssets().getInternalAtlasRegionByName("search", "editorAssets")));
        goToEntity.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               // EntityTools.goToEntity( entity);
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
        name= new Label("label", skin);
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

    @Override
    public WorldObjects getWorldObjects() {
        return worldObjects;
    }

    @Override
    public void setWorldObjects(WorldObjects worldObjects) {
        this.worldObjects = worldObjects;
    }
}
