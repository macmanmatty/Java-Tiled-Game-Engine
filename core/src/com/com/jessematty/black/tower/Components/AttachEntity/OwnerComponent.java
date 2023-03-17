package com.jessematty.black.tower.Components.AttachEntity;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
public class OwnerComponent implements Component {
    private Array<String> ownedEntityIDs= new Array<>();
    private int maxOwnedEntities=-1;
    private String maxEntitiesError="Max Number Of Entities Already Attached";
    private String maxEntitiesErrorTitle="Error!";
    public Array<String> getOwnedEntityIDs() {
        return ownedEntityIDs;
    }
    public boolean addEntity(String id){
        if(ownedEntityIDs.size<maxOwnedEntities || maxOwnedEntities<0){
            ownedEntityIDs.add(id);
            return  true;
        }
        return  false;
    }
    public int getMaxOwnedEntities() {
        return maxOwnedEntities;
    }
    public void setMaxOwnedEntities(int maxOwnedEntities) {
        this.maxOwnedEntities = maxOwnedEntities;
    }
    public String getMaxEntitiesError() {
        return maxEntitiesError;
    }
    public void setMaxEntitiesError(String maxEntitiesError) {
        this.maxEntitiesError = maxEntitiesError;
    }
    public String getMaxEntitiesErrorTitle() {
        return maxEntitiesErrorTitle;
    }
    public void setMaxEntitiesErrorTitle(String maxEntitiesErrorTitle) {
        this.maxEntitiesErrorTitle = maxEntitiesErrorTitle;
    }
}
