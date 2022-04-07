package com.jessematty.black.tower.GameBaseClasses.Engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.AddOwnerSystem;
import com.jessematty.black.tower.Systems.BooleanStatChangeSystem;
import com.jessematty.black.tower.Systems.ChangeBoundsSystem;
import com.jessematty.black.tower.Systems.ChangeHeldItemBoundsSystem;
import com.jessematty.black.tower.Systems.ColorChangeSystem;
import com.jessematty.black.tower.Systems.DropSystem;
import com.jessematty.black.tower.Systems.DyingSystem;
import com.jessematty.black.tower.Systems.EquipItemSystem;
import com.jessematty.black.tower.Systems.ErrorSystem;
import com.jessematty.black.tower.Systems.PlaySoundSystem;
import com.jessematty.black.tower.Systems.RemoveOwnerSystem;
import com.jessematty.black.tower.Systems.SelfChangeNumericStatsSystem;
import com.jessematty.black.tower.Systems.NumericStatChangeSystem;
import com.jessematty.black.tower.Systems.SetPositionMarkersSystem;
import com.jessematty.black.tower.Systems.SetSpeedToMovableSystem;
import com.jessematty.black.tower.Systems.SlashSystem;
import com.jessematty.black.tower.Systems.SoundSystem;
import com.jessematty.black.tower.Systems.StringStatChangeSystem;
import com.jessematty.black.tower.Systems.ThrustSystem;
import com.jessematty.black.tower.Systems.TimeChangingStatSystem;
import com.jessematty.black.tower.Systems.UnEquipItemSystem;
import com.jessematty.black.tower.Systems.AnimationSystem;
import com.jessematty.black.tower.Systems.BoundingBoxRenderer;
import com.jessematty.black.tower.Systems.BreathSystem;
import com.jessematty.black.tower.Systems.ChangeTileNumericStatSystem;
import com.jessematty.black.tower.Systems.CollisionSystem;
import com.jessematty.black.tower.Systems.DieSystem;
import com.jessematty.black.tower.Systems.GrowSystem;
import com.jessematty.black.tower.Systems.HungerSystem;
import com.jessematty.black.tower.Systems.MoveOnGroundSystem;
import com.jessematty.black.tower.Systems.ZRPGPlayerSystem;
import com.jessematty.black.tower.Systems.RemoveEntityFromEngineSystem;
import com.jessematty.black.tower.Systems.RenderSystem;
import com.jessematty.black.tower.Systems.SetEntityPositionAndActionToOwnerSystem;
import com.jessematty.black.tower.Systems.ThirstSystem;
import com.jessematty.black.tower.Systems.TileHumiditySystem;
import com.jessematty.black.tower.Systems.TileRainSystem;
import com.jessematty.black.tower.Systems.TileWeatherSeasonSystem;
import com.jessematty.black.tower.Systems.TimeRemovingEntitySystem;

import java.util.Comparator;

public class EngineSetup {

    private  static  boolean hasPackSystem=true;

     static ComponentMapper<DrawableComponent> drawableComponentMapper=ComponentMapper.getFor(DrawableComponent.class);

    /**
     *  used the for the render system  to determine the draw order of of entities on teh screen
     *  layer number = y position of entity
     *  sublayer=  number draw order of attached entities  that are bound to the same y position as parent entity
     */
    private static Comparator<Entity> entityComparator= new Comparator<Entity>() {
        @Override
        public int compare(Entity entity1, Entity entity2) {


            DrawableComponent drawableComponent1 =drawableComponentMapper.get(entity1);
            DrawableComponent drawableComponent2 =drawableComponentMapper.get(entity2);
            if(drawableComponent1 !=null && drawableComponent2 !=null) {
                float layerNumber1 = drawableComponent1.getLayerNumber();

                float  layerNumber2 = drawableComponent2.getLayerNumber();


                if (layerNumber1 != layerNumber2) {
                    return Float.compare(layerNumber1, layerNumber2);

                } else {





                    return Float.compare(drawableComponent1.getSubLayerNumber() , drawableComponent2.getSubLayerNumber());


                }

            }
           return 0;



        }
    };

    /**
     * addes the base game systems to the engine these do NOT get added to the world as they should not be removed  or modified once the game has started
     * @param engine the games engine
     * @param draw the map drawing class
     * @param shapes // the libGDX shape drawing Batch class
     * @param drawBounds // whether or not to draw bounding rectangles for the entity bounds
     * @param mapBuffer the frame buffer for the map used for drawing lights
     * @param lightBuffer the frame buffer for the light objects on the map  used for drawing lights
     */
    public  static void addBaseSystemsToEngine(Engine engine, MapDraw draw, ShapeRenderer shapes, boolean drawBounds, FrameBuffer mapBuffer, FrameBuffer lightBuffer ){

        GameComponentMapper.getDrawableComponentMapper();
       Family drawableFamily= Family.all(PositionComponent.class, DrawableComponent.class).get();
       RenderSystem renderSystem=new RenderSystem (drawableFamily, entityComparator,  draw.getBatch(), mapBuffer ,2);
       renderSystem.setCallBatchEnd(true);
       RenderSystem lightSystem=new RenderSystem( drawableFamily, entityComparator,  draw.getBatch(), lightBuffer, 3);

       engine.addSystem(renderSystem);
       //engine.addSystem(lightSystem);
       engine.addEntityListener(renderSystem);
       ZRPGPlayerSystem ZRPGPlayerSystem =new ZRPGPlayerSystem(draw);
       engine.addSystem(ZRPGPlayerSystem);
       MoveOnGroundSystem moveOnGroundSystem =new MoveOnGroundSystem(draw);

       engine.addSystem(moveOnGroundSystem);
        engine.addSystem(new SetPositionMarkersSystem(draw, 0));
        engine.addSystem(new SetEntityPositionAndActionToOwnerSystem(1,draw));

        engine.addSystem(new ChangeBoundsSystem(draw, 2));
        engine.addSystem(new ChangeHeldItemBoundsSystem(draw, 3));
        AnimationSystem animationSystem =new AnimationSystem(draw, renderSystem, 4);
        engine.addSystem(new SoundSystem(draw, 5));
        engine.addSystem( new PlaySoundSystem(draw, 6));
        engine.addSystem(animationSystem);
       engine.addSystem(new CollisionSystem(draw));
       engine.addSystem(new RemoveEntityFromEngineSystem(draw));
       engine.addSystem(new DieSystem(draw));
       engine.addSystem(new DyingSystem(draw));
       engine.addSystem(new GrowSystem(60, draw));
       engine.addSystem(new BreathSystem(draw));
       engine.addSystem(new HungerSystem(draw));
       engine.addSystem(new ThirstSystem(draw));
       engine.addSystem(new TimeRemovingEntitySystem(draw));
       engine.addSystem(new TileWeatherSeasonSystem(60, draw));
       engine.addSystem(new TileHumiditySystem(60, draw));
       engine.addSystem(new TileRainSystem(60, draw));
       engine.addSystem(new ChangeTileNumericStatSystem(60, draw));
       engine.addSystem( new SelfChangeNumericStatsSystem(3600,  draw));
       engine.addSystem(new NumericStatChangeSystem(draw));
       engine.addSystem( new BooleanStatChangeSystem(draw));
       engine.addSystem( new StringStatChangeSystem(draw));
       engine.addSystem(new TimeChangingStatSystem(draw));
       engine.addSystem(new ErrorSystem(draw));
       engine.addSystem(new EquipItemSystem(draw));
       engine.addSystem(new UnEquipItemSystem(draw));
       engine.addSystem(new DropSystem(draw));
       engine.addSystem(new ColorChangeSystem(draw));
       engine.addSystem(new AddOwnerSystem(draw));
       engine.addSystem(new RemoveOwnerSystem(draw));
       engine.addSystem(new SlashSystem(draw));
       engine.addSystem(new ThrustSystem(draw));


         engine.addSystem(new SetSpeedToMovableSystem(draw));
       if(drawBounds){
           engine.addSystem(new BoundingBoxRenderer(shapes));
       }
       draw.getGameAssets().getGameInput().addProcessor(ZRPGPlayerSystem);
   }


   public static  void addRenderSystemsToEngine(  Engine engine, Batch batch,  ShapeRenderer shapes, boolean drawBounds, FrameBuffer mapBuffer, FrameBuffer lightBuffer){

       Family drawableFamily= Family.all(PositionComponent.class, DrawableComponent.class).get();
       RenderSystem renderSystem=new RenderSystem(  drawableFamily, entityComparator,  batch, mapBuffer ,2);
       RenderSystem lightSystem=new RenderSystem( drawableFamily, entityComparator,  batch, lightBuffer, 3);
       engine.addSystem(renderSystem);
       //engine.addSystem(lightSystem);
       engine.addEntityListener(renderSystem);
       if(drawBounds){
           engine.addSystem(new BoundingBoxRenderer( shapes));
       }

   }


   public static  void removeSystemsFromEngine( Engine engine, Array<EntitySystem> systems){
       int size=systems.size;
       for(int count=0; count<size; count++){

           engine.removeSystem(systems.get(count));
       }


   }


    public static  void addSystemsToEngine( Engine engine, Array<EntitySystem> systems){
        int size=systems.size;
        for(int count=0; count<size; count++){

            engine.addSystem(systems.get(count));
        }


    }

    public static boolean isHasPackSystem() {
        return hasPackSystem;
    }

    public static void setHasPackSystem(boolean hasPackSystem) {
        EngineSetup.hasPackSystem = hasPackSystem;
    }
}
