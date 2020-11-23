package com.jessematty.black.tower.Editor.EditMode.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeys;

public class EditInputKeys  implements InputKeys {
  private  ObjectMap<String, InputKeyCombo> keys= new ObjectMap<>();



    public void addKey(String keyName, InputKeyCombo inputKeyCombo) {

        this.keys.put(keyName, inputKeyCombo);

    }


  public void addKey(String keyName, int key1,  int key2){

      keys.put(keyName, new InputKeyCombo(key1, key2));
  }


    public ObjectMap<String, InputKeyCombo> getKeys() {
        return keys;
    }



    // adds the basic  input method names and keys
    public void addAllStandardKeys(){

      addKey("scrollUp", Keys.UP,-1 );
        addKey("scrollDown", Keys.DOWN,-1 );
        addKey("scrollLeft", Keys.LEFT,-1 );
        addKey("scrollRight", Keys.RIGHT,-1 );
        addKey("BitMask", Keys.B,-1 );
        addKey("bucketFill", Keys.F,-1 );
        addKey("zoomOut", Keys.MINUS,-1 );
        addKey("zoomIn", Keys.PLUS,-1 );



    }
}
