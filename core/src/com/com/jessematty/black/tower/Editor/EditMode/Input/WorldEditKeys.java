package com.jessematty.black.tower.Editor.EditMode.Input;

import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;

public class WorldEditKeys {

    private  final MapEditScreen mapEditScreen;
    private  KeyListener keyListener;




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


    public WorldEditKeys(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
    }
    

    
    public MapEditScreen getMapEditScreen() {
        return mapEditScreen;
    }

   





}
