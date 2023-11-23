package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.utils.Array;

/** interface for key input classes that hold keys pressed to actions used for the key input change window
 * **/

public interface InputKeys {
    Array<InputKeyCombo> getKeys();
}
