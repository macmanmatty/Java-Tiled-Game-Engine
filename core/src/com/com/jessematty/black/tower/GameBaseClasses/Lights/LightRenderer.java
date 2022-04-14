package com.jessematty.black.tower.GameBaseClasses.Lights;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Maps.GameMap;

import java.util.ArrayList;
import java.util.List;

import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LightRenderer {

    private World world;
    private RayHandler rayHandler;
    private GameMap map;
    private List<LightSource> lights= new ArrayList<LightSource>();
    private OrthographicCamera camera;
    private float  mapLight;
    private Viewport viewport;
    private Color dayLightColor = new Color(0, 0, 0, 0);

    public LightRenderer(GameMap map, OrthographicCamera camera, Viewport viewport) {
        this.map=map;
        this.camera=camera;
        this.viewport=viewport;

        world = new World(new Vector2(0, 0), false);
        rayHandler= new RayHandler(world);
        rayHandler.setShadows(false);
        rayHandler.useDiffuseLight(true);
        RayHandler.setGammaCorrection(true);
        rayHandler.setAmbientLight(0f, 0f, 0f, 0f);
        rayHandler.setCulling(true);
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);
        rayHandler.setCombinedMatrix(camera);


    }

    public void  render(){
        rayHandler.setCombinedMatrix(camera);
        rayHandler.setAmbientLight(dayLightColor);
        rayHandler.update();
        rayHandler.render();

        int size=lights.size();

        for(int count=0; count<size; count++){
            LightSource source=lights.get(count);
            LightKind lightKind=source.getLightKind();
            switch (lightKind){


                   case POINT:
                    new PointLight(rayHandler, source.getRays(), source.getColor(), source.getDiameter(), source.getLightCenterX(), source.getLightCenterY());
                    break;

                    case DIRECTIONAL:
                        new DirectionalLight(rayHandler, source.getRays(), source.getColor(), source.getLightAngle());

                    break;
                   case CONE:

                       new ConeLight(rayHandler, source.getRays(), source.getColor(), source.getDiameter(), source.getLightCenterX(), source.getLightCenterY(), source.getLightDegrees(), source.getLightAngle());

                    break;
            }


        }


    }

    public void setLight(float light){
        dayLightColor = new Color(dayLightColor.r+light, dayLightColor.g+light, dayLightColor.b+light, dayLightColor.a+light);


    }

    public List<LightSource> getLights() {
        return lights;
    }

    public void setLights(List<LightSource> lights) {
        this.lights = lights;
    }


   public void  dispose(){

        rayHandler.dispose();
        world.dispose();



    }



}
