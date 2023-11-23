package com.jessematty.black.tower.Components.AttachEntity;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;

public class Attachable implements Component {
    private float heightOffset;
    private float positionYOffset;
    private float positionXOffset;
    private  boolean removable;
    private String unDetachableMessage ="This Item Cannot Be Unattached";
    private String unDetachableTitle =" Item Cannot Be Unattached";
    private String unAttachableMessage="This Item Cannot Be Attached";
    private String unAttachableTitle =" Item Cannot Be Attached";
    private Array<String> attachableGroups= new Array<>();
    private boolean drawAttachedItem;
    public Attachable() {
    }

    public Attachable(Entity owned, Entity owner)  {
        PositionComponent ownerPosition=owner.getComponent(PositionComponent.class);
        PositionComponent entityOwnedPosition=owned.getComponent(PositionComponent.class);
        entityOwnedPosition.setPosition(ownerPosition.getLocationX()+positionXOffset, ownerPosition.getLocationY()+positionYOffset);
    }

    public Attachable( float heightOffset, float positionYOffset, float positionXOffset) {
        this.heightOffset = heightOffset;
        this.positionYOffset = positionYOffset;
        this.positionXOffset = positionXOffset;
        drawAttachedItem=true;

    }

    public float getHeightOffset() {
        return heightOffset;
    }
    public void setHeightOffset(float heightOffset) {
        this.heightOffset = heightOffset;
    }
    public float getPositionYOffset() {
        return positionYOffset;
    }
    public void setPositionYOffset(float positionYOffset) {
        this.positionYOffset = positionYOffset;
    }
    public float getPositionXOffset() {
        return positionXOffset;
    }
    public void setPositionXOffset(float positionXOffset) {
        this.positionXOffset = positionXOffset;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public Array<String> getAttachableGroups() {
        return attachableGroups;
    }

    public boolean isDrawAttachedItem() {
        return drawAttachedItem;
    }

    public void setDrawAttachedItem(boolean drawAttachedItem) {
        this.drawAttachedItem = drawAttachedItem;
    }

    public String getUnDetachableMessage() {
        return unDetachableMessage;
    }

    public void setUnDetachableMessage(String unDetachableMessage) {
        this.unDetachableMessage = unDetachableMessage;
    }

    public String getUnDetachableTitle() {
        return unDetachableTitle;
    }

    public void setUnDetachableTitle(String unDetachableTitle) {
        this.unDetachableTitle = unDetachableTitle;
    }

    public String getUnAttachableMessage() {
        return unAttachableMessage;
    }

    public void setUnAttachableMessage(String unAttachableMessage) {
        this.unAttachableMessage = unAttachableMessage;
    }

    public String getUnAttachableTitle() {
        return unAttachableTitle;
    }

    public void setUnAttachableTitle(String unAttachableTitle) {
        this.unAttachableTitle = unAttachableTitle;
    }
}
