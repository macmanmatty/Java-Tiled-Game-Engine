package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class OrthographicCameraControls {


    private OrthographicCamera camera;
    private float translateDistanceX =32;
    private float translateDistanceY =32;
    private float zoomDistance=1;
    private final  Array<InputKeyCombo> cameraControlKeyCombos= new Array<>();
    private float  defaultZoom;
    
    

    public OrthographicCameraControls(OrthographicCamera camera) {
        this.camera = camera;
        cameraControlKeyCombos.add(new InputKeyCombo(up, "moveUp", Keys.UP));
        cameraControlKeyCombos.add(new InputKeyCombo(down, "moveDown", Keys.DOWN));
        cameraControlKeyCombos.add(new InputKeyCombo(left, "moveLeft", Keys.LEFT));
        cameraControlKeyCombos.add(new InputKeyCombo(right, "moveRight", Keys.RIGHT));
        cameraControlKeyCombos.add(new InputKeyCombo(zoomIn, "zoomIn", Keys.PLUS));
        cameraControlKeyCombos.add(new InputKeyCombo(zoomIn, "zoomIn", Keys.EQUALS));
        cameraControlKeyCombos.add(new InputKeyCombo(upLeft, "moveUp", Keys.UP, Keys.LEFT));
        cameraControlKeyCombos.add(new InputKeyCombo(downLeft, "moveDown", Keys.DOWN, Keys.LEFT));
        cameraControlKeyCombos.add(new InputKeyCombo(upRight, "moveLeft", Keys.UP, Keys.RIGHT));
        cameraControlKeyCombos.add(new InputKeyCombo(downRight, "moveRight",Keys.DOWN, Keys.RIGHT));
        cameraControlKeyCombos.add(new InputKeyCombo(zoomStandard, "zoomIn", Keys.EQUALS, Keys.ALT_LEFT));
        cameraControlKeyCombos.add(new InputKeyCombo(zoomOut, "zoomOut", Keys.MINUS));
        defaultZoom=camera.zoom;


    }

    private final  KeyAction up= new KeyAction() {
        @Override
        public void act() {
            camera.translate(0, translateDistanceY);
            System.out.println("up pressed");

        }
    };

    private final  KeyAction zoomStandard= new KeyAction() {
        @Override
        public void act() {
            camera.zoom=defaultZoom;

        }
    };

    private final  KeyAction down= new KeyAction() {
        @Override
        public void act() {
            camera.translate(0, -translateDistanceY);

        }
    };

    private final   KeyAction left= new KeyAction() {
        @Override
        public void act() {
            camera.translate(-translateDistanceX, 0);


        }
    };

    private final    KeyAction right= new KeyAction() {
        @Override
        public void act() {

            camera.translate(translateDistanceX, 0);


        }
    };

    private final    KeyAction zoomIn= new KeyAction() {
        @Override
        public void act() {

            camera.zoom=camera.zoom+zoomDistance;


        }
    };


    private final    KeyAction zoomOut= new KeyAction() {
        @Override
        public void act() {

            camera.zoom=camera.zoom-zoomDistance;


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
            camera.translate(-translateDistanceX, translateDistanceY);

        }
    };

    private final  KeyAction downRight= new KeyAction() {
        @Override
        public void act() {
            camera.translate(translateDistanceX, -translateDistanceY);

        }
    };

    private final   KeyAction downLeft= new KeyAction() {
        @Override
        public void act() {
            camera.translate(-translateDistanceX, -translateDistanceY);


        }
    };

    private final    KeyAction upRight= new KeyAction() {
        @Override
        public void act() {

            camera.translate(translateDistanceX, translateDistanceY);


        }
    };


    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public float getTranslateDistanceX() {
        return translateDistanceX;
    }

    public void setTranslateDistanceX(float translateDistanceX) {
        this.translateDistanceX = translateDistanceX;
    }

    public float getTranslateDistanceY() {
        return translateDistanceY;
    }

    public void setTranslateDistanceY(float translateDistanceY) {
        this.translateDistanceY = translateDistanceY;
    }

    public float getZoomDistance() {
        return zoomDistance;
    }

    public void setZoomDistance(float zoomDistance) {
        this.zoomDistance = zoomDistance;
    }

    public Array<InputKeyCombo> getCameraControlKeyCombos() {
        return cameraControlKeyCombos;
    }
    public void enableControlledMovement(){
        int size=cameraControlKeyCombos.size;
    for(int count=0; count<size; count++) {

        InputKeyCombo inputKeyCombo=cameraControlKeyCombos.get(count);
        inputKeyCombo.setDisabled(false);
        }

    }

    public void disableControlledMovement(){
        int size=cameraControlKeyCombos.size;
        for(int count=0; count<size; count++) {

            InputKeyCombo inputKeyCombo=cameraControlKeyCombos.get(count);
            inputKeyCombo.setDisabled(true);
        }

    }
}
