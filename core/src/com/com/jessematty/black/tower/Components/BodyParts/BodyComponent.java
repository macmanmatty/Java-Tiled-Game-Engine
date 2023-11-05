package com.jessematty.black.tower.Components.BodyParts;
import com.badlogic.ashley.core.Component;
import java.util.HashMap;
public class BodyComponent implements Component {
   private  HashMap<String, String> bodyParts = new HashMap<String, String>();
    public BodyComponent() {
    }
    public HashMap<String, String> getBodyParts() {
        return bodyParts;
    }
    
}