package com.jessematty.black.tower.GameBaseClasses.Rendering;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;

public class DayAndNightTiledMapRenderer extends OrthogonalTiledMapRenderer { // tiledMap renderer  for rendering maps that have the brightness change With sun the sun
    private float light=1; // the current light 0=dark
   private float maximumAmountOfSunLight =1; // the maximum amount of light on the map;
   private float currentMaximumAmountOfSunlight; // the current mac amount of light on map based of the time year.
    private float maxDayLight =43200; // the time when max day light is reached
    private GameTime gameTime;  // the game time object
    private float mapLatitude=0; // the maps latitude  as it wold be on actual map or how much brightness and hours of day light change with  the time of year.
    private boolean indoorMap; // whether or not the map is indoors if indoors don't change light  with the  sun.
    private  float sunlightIncrease =0.001f; // the amount of light
    private float sunlightMultiplier=0.001f;
    private float brightness=.5f;



   public DayAndNightTiledMapRenderer(BrightnessBatch batch, GameTime time, TiledMap map, boolean indoorMap, float mapLatitude, float maximumAmountOfSunLight) {

        super(map, batch);


        this.gameTime=time;
        this.maximumAmountOfSunLight = maximumAmountOfSunLight;
        this.indoorMap=indoorMap;
        this.mapLatitude=mapLatitude;
        this.currentMaximumAmountOfSunlight = maximumAmountOfSunLight;




    }





    public DayAndNightTiledMapRenderer(BrightnessBatch batch, GameTime time, TiledMap map, boolean indoorMap, float mapLatitude, float maximumAmountOfSunLight, float sunlightIncrease, float sunlightMultiplier) {

        super(map, batch);


        this.gameTime=time;
        this.maximumAmountOfSunLight = maximumAmountOfSunLight;
        this.indoorMap=indoorMap;
        this.mapLatitude=mapLatitude;
        this.currentMaximumAmountOfSunlight = maximumAmountOfSunLight;
        this.sunlightIncrease=sunlightIncrease;
        this.sunlightMultiplier=sunlightMultiplier;





    }



    public DayAndNightTiledMapRenderer(TiledMap map, float unitScale) {
        super(map, unitScale, new BrightnessBatch());
    }

    @Override
    public void render() {

        beginRender();
        batch.enableBlending();
        batch.setColor(1, 1, 1, light);
        batch.getShader().setUniformf("bright", brightness);

        for (MapLayer layer : map.getLayers()) {
                renderMapLayer(layer);
            }




        endRender();


    }
    public void updateLight(){


        if(indoorMap==false) {
            double daySeconds = gameTime.getDaySeconds();
            if (daySeconds > maxDayLight) {

                if (light > 0) {
                    light = light - sunlightIncrease;
                }


            } else {

                if (light < maximumAmountOfSunLight) {
                    light = light + sunlightIncrease;
                }


            }


            float days = gameTime.getCurrentNumberOfDaysLapsedInCurrentYear();
            float hours = gameTime.getCurrentNumbersOfHoursLapsedInCurrentDay();

            if (hours == 24) {

                if (days < 182) {


                    maxDayLight = maxDayLight + (mapLatitude);
                    currentMaximumAmountOfSunlight = currentMaximumAmountOfSunlight + (mapLatitude * sunlightMultiplier);

                } else {

                    maxDayLight = maxDayLight - mapLatitude;
                    currentMaximumAmountOfSunlight = currentMaximumAmountOfSunlight - (mapLatitude * sunlightMultiplier);

                }

            }


        }


    }

    public float getMaximumAmountOfSunLight() {
        return maximumAmountOfSunLight;
    }



    public float getSunlightIncrease() {
        return sunlightIncrease;
    }



    public float getSunlightMultiplier() {
        return sunlightMultiplier;
    }





}
