package com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Editor.World.WorldObjects;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSelectImageButton;

public class EntitySelect extends Table {// finds all textureRegions that start with  a given name and makes a  UI table of  them
    private GameAssets assets;
    private Skin skin;
    private WorldObjects objects;
    private GameComponentMapper gameComponentMapper;
    private ComponentMapper<Name> nameComponentMapper;
    private ComponentMapper<EditorImageComponent> editorImageComponentComponentMapper;
    private Array<Entity> entities;
    private TextureRegion noImage;
    private ClipBoard clipBoard;


    
    public EntitySelect(MapEditScreen mapEditScreen, Skin skin) {
        this.assets = assets;
        this.skin=skin;
        this.objects=mapEditScreen.getWorldObjects();
        this.gameComponentMapper=mapEditScreen.getGameComponentMapper();
        this.nameComponentMapper=gameComponentMapper.getNameComponentMapper();
        this.editorImageComponentComponentMapper=gameComponentMapper.getEditorImageComponentComponentMapper();
        this.noImage=assets.getAtlasRegionByName("noImage", "assetts.atlas");
        entities= objects.getEntitiesInWorld();
        noImage=assets.getAtlasRegionByName("empty", "editorAssets");
        clipBoard=mapEditScreen.getClipBoard();



    }
    public void makeTable(){
      int numberOfEntities=entities.size;



            for(int count=0; count<numberOfEntities; count++) {
                Entity entity=entities.get(count);
                 EditorImageComponent editorImageComponent=editorImageComponentComponentMapper.get(entity);
                 TextureRegion region=noImage;
                 if(editorImageComponent!=null){
                     TextureRegion  textureRegion=editorImageComponent.getAtlasRegion();
                     if(textureRegion!=null){

                         region=textureRegion;
                     }
                 }
                ItemSelectImageButton<Entity> itemSelectImageButton= new ItemSelectImageButton<>(entity,region, clipBoard );

                 add(itemSelectImageButton);






            }

        }


    public ScrollPane getTable() {
       return  new ScrollPane(this);
    }
}
