package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntitySettable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.EntityHorizontalGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;

/**(
 * window for selecting a single entity from a list of entities
 */
public class EntitySelectWindow extends ClosableWindow implements EntitySettable {
    /**
     * the array of entities to be displayed in the window
     */
   private Array<Entity> entities;
    /**
     * game assets object
     */
   private GameAssets gameAssets;
    /**
     * the selected entity
     */
   private Entity selectedEntity;

    /**
     * call back function to be preformed on the entity after it is selected.
     */
   private OnSelected<Entity> onSelected;

    /**
     * whether or not to close the window once an entity ha been picked;
     */
   boolean closeOnSelect=true;
    public EntitySelectWindow(Skin skin, Array<Entity> entities, GameAssets gameAssets, OnSelected<Entity> onSelected){
        super("Pick An Entity", skin, true, false, false);
        this.entities = entities;
       this.gameAssets=gameAssets;
        this.skin=skin;
        this.onSelected=onSelected;
        createWindow();
    }
    public EntitySelectWindow(Skin skin, Array<Entity> entities, GameAssets gameAssets){
        this(skin, entities, gameAssets, null);
    }
    public void createWindow(){
        clear();

        Table table = new Table();
        for(final  Entity entity: entities){
            EntityHorizontalGroup entityHorizontalGroup= new EntityHorizontalGroup(this, gameAssets, skin, entity);
            table.add(entityHorizontalGroup);
            table.row();
            
        }
        ScrollPane scrollPane=  new ScrollPane(table);
        scrollPane.setTransform(true);
        add(scrollPane).size(200, 400);
        pack();

    }
    @Override
    public void setEntity(Entity entity) {
        this.selectedEntity=entity;
        if(onSelected!=null){
            onSelected.onSelected(selectedEntity);
        }
        if(closeOnSelect){
            close();
        }
    }
    @Override
    public Entity getEntity() {
        return selectedEntity;
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Array<Entity> entities) {
        this.entities = entities;
        createWindow();
    }

    public boolean isCloseOnSelect() {
        return closeOnSelect;
    }

    public void setCloseOnSelect(boolean closeOnSelect) {
        this.closeOnSelect = closeOnSelect;
    }
}
