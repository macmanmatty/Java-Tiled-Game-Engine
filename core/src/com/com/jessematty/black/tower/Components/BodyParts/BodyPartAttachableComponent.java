package com.jessematty.black.tower.Components.BodyParts;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;


/**
 * component for a body part attachable item
 */
public class BodyPartAttachableComponent implements Component {
    /**
     * the list of body parts an item may be attached to.
     */
    Array<String> bodyPart= new Array<>();
    public BodyPartAttachableComponent() {
    }

    public Array<String> getBodyPart() {
        return bodyPart;
    }


}

