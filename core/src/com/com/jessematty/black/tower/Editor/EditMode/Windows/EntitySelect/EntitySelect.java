package com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Other.EditorImageComponent;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSelectImageButton;

public class EntitySelect extends Table {// finds all textureRegions that start with  a given name and makes a  UI table of  them
    private GameAssets assets;
    private Skin skin;
    private WorldObjects objects;
    private ComponentMapper<NameComponent> nameComponentMapper;
    private ComponentMapper<EditorImageComponent> editorImageComponentComponentMapper;
    private Array<Entity> entities;
    private TextureRegion noImage;
    private ClipBoard clipBoard;



    
    public EntitySelect(MapEditScreen mapEditScreen, Skin skin) {
        this.assets = assets;
        this.skin=skin;
        this.objects=mapEditScreen.getWorldObjects();
        this.nameComponentMapper=GameComponentMapper.getNameComponentMapper();
        this.editorImageComponentComponentMapper=GameComponentMapper.getEditorImageComponentComponentMapper();
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
