package com.jessematty.black.tower.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class Attachable implements Component {
    private float heightOffset;
    private float positionYOffset;
    private float positionXOffset;
    private  boolean removable;
    private String unDetachableMessage ="This Item Cannot Be Unattached";
    private String unDetachableTile =" Item Cannot Be Unattached";
    private String unAttachableMessage="This Item Cannot Be Attached";
    private String unAttachableTile=" Item Cannot Be Attached";
    private Array<String> attachableGroups= new Array<>();
    private boolean drawAttachedItem;
    public Attachable() {
    }

    public Attachable(Entity owned, Entity owner)  {
        Position ownerPosition=owner.getComponent(Position.class);
        Position entityOwnedPosition=owned.getComponent(Position.class);
        entityOwnedPosition.setSetScreenLocations(ownerPosition.getScreenLocationX()+positionXOffset, ownerPosition.getScreenLocationY()+positionYOffset);
    }

    public Attachable( float heightOffset, float positionYOffset, float positionXOffset) {
        this.heightOffset = heightOffset;
        this.positionYOffset = positionYOffset;
        this.positionXOffset = positionXOffset;

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

    public String getUnDetachableTile() {
        return unDetachableTile;
    }

    public void setUnDetachableTile(String unDetachableTile) {
        this.unDetachableTile = unDetachableTile;
    }

    public String getUnAttachableMessage() {
        return unAttachableMessage;
    }

    public void setUnAttachableMessage(String unAttachableMessage) {
        this.unAttachableMessage = unAttachableMessage;
    }

    public String getUnAttachableTile() {
        return unAttachableTile;
    }

    public void setUnAttachableTile(String unAttachableTile) {
        this.unAttachableTile = unAttachableTile;
    }
}
