package com.jessematty.black.tower.Generators.Components.BoundsChangeable;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Generators.Components.ExternalComponentDTO;

/**
 * DTO class for an bounds changeable component; @see BoundsChangeable.class
 */
public class BoundsChangeableComponentDTO implements ExternalComponentDTO {
    /**
     * the array of bounds DTOs
     */
    private Array<EntityBoundsDTO> boundsDTOArray = new Array<>();
    private boolean eightDirections;

    public Array<EntityBoundsDTO> getBoundsDTOArray() {
        return boundsDTOArray;
    }

    public void setBoundsDTOArray(Array<EntityBoundsDTO> boundsDTOArray) {
        this.boundsDTOArray = boundsDTOArray;
    }

    public boolean isEightDirections() {
        return eightDirections;
    }

    public void setEightDirections(boolean eightDirections) {
        this.eightDirections = eightDirections;
    }
}
