package com.jessematty.black.tower.Components.Tiles;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
public class TileComponent implements Component {
    public TileComponent() {
    }
    private  boolean enterable=true;
    protected boolean checked;
    protected transient Array<Entity> entities= new Array<Entity>(); // list of entities on ths tile
    protected  boolean entered;
    public boolean isEnterable() {
        return enterable;
    }
    public void setEnterable(boolean enterable) {
        this.enterable = enterable;
    }
    public Entity getLastEntity(){
        return  entities.get(entities.size-1);
    }
    public Array<Entity> getEntities() {
        return entities;
    }
    public void setEntities(Array<Entity> entities) {
        this.entities = entities;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public void setEntered(boolean entered) {
        this.entered = entered;
    }
}
