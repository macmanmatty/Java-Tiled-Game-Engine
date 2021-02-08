package com.jessematty.black.tower.Editor.EditMode.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;

import java.util.List;

public class BasicEditKeys {

    private  final  MapEditScreen mapEditScreen;
    private final KeyListener keyListener;


  

    private final  KeyAction up= new KeyAction() {
        @Override
        public void act() {
            mapEditScreen.getCamera().translate(0, mapEditScreen.getTileHeight());
            System.out.println("up pressed");

        }
    };

    private final  KeyAction down= new KeyAction() {
        @Override
        public void act() {
            mapEditScreen.getCamera().translate(0, -mapEditScreen.getTileHeight());

        }
    };

    private final   KeyAction left= new KeyAction() {
        @Override
        public void act() {
            mapEditScreen.getCamera().translate(-mapEditScreen.getTileWidth(), 0);


        }
    };

    private final    KeyAction right= new KeyAction() {
        @Override
        public void act() {

            mapEditScreen.getCamera().translate(mapEditScreen.getTileWidth(), 0);


        }
    };

    private final    KeyAction zoomIn= new KeyAction() {
        @Override
        public void act() {

            mapEditScreen.getCamera().zoom--;


        }
    };


    private final    KeyAction zoomOut= new KeyAction() {
        @Override
        public void act() {

          mapEditScreen.getCamera().zoom++;


        }
    };
    private final   KeyAction newEntity= new KeyAction() {
        @Override
        public void act() {

        }
    };

    private final  KeyAction upLeft= new KeyAction() {
        @Override
        public void act() {
            mapEditScreen.getCamera().translate(-mapEditScreen.getTileHeight(), mapEditScreen.getTileHeight());

        }
    };

    private final  KeyAction downRight= new KeyAction() {
        @Override
        public void act() {
            mapEditScreen.getCamera().translate(mapEditScreen.getTileWidth(), -mapEditScreen.getTileHeight());

        }
    };

    private final   KeyAction downLeft= new KeyAction() {
        @Override
        public void act() {
            mapEditScreen.getCamera().translate(-mapEditScreen.getTileWidth(), -mapEditScreen.getTileHeight());


        }
    };

    private final    KeyAction upRight= new KeyAction() {
        @Override
        public void act() {

            mapEditScreen.getCamera().translate(mapEditScreen.getTileWidth(), mapEditScreen.getTileHeight());


        }
    };





    private final  KeyAction bitmask= new KeyAction() {
        @Override
        public void act() {

        }
    };

    private final    KeyAction select= new KeyAction() {
        @Override
        public void act() {

        }
    };

    private final   KeyAction rotateRight= new KeyAction() {
        @Override
        public void act() {

        }
    };

    private final   KeyAction rotateLeft= new KeyAction() {
        @Override
        public void act() {

        }
    };


    public BasicEditKeys(MapEditScreen mapEditScreen, KeyListener keyListener) {
        this.mapEditScreen=mapEditScreen;
        this.keyListener=keyListener;
        keyListener.getInputKeyComboMap().put( "moveUp", new InputKeyCombo(up, "moveUp", Keys.UP));
        keyListener.getInputKeyComboMap().put("moveDown",new InputKeyCombo(down, "moveDown", Keys.DOWN));
        keyListener.getInputKeyComboMap().put("moveLeft",new InputKeyCombo(left, "moveLeft", Keys.LEFT));
        keyListener.getInputKeyComboMap().put("moveRight",new InputKeyCombo(right, "moveRight", Keys.RIGHT));
        keyListener.getInputKeyComboMap().put("zoomIn",new InputKeyCombo(zoomIn, "zoomIn", Keys.PLUS));
        keyListener.getInputKeyComboMap().put("zoomIn",new InputKeyCombo(zoomIn, "zoomIn", Keys.EQUALS));


        keyListener.getInputKeyComboMap().put("zoomOut",new InputKeyCombo(zoomOut, "zoomOut", Keys.MINUS));




    }

    
    public MapEditScreen getMapEditScreen() {
        return mapEditScreen;
    }

   





}
