package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputMultiplexer;

public class InputMultiplexerWithKeyListener extends InputMultiplexer {


   private  KeyListener keyListener= new KeyListener();
    public InputMultiplexerWithKeyListener() {
        addProcessor(keyListener);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }
}
