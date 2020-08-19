package com.jessematty.black.tower.GameBaseClasses.Engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.BooleanStatChangeSystem;
import com.jessematty.black.tower.Systems.ColorChangeSystem;
import com.jessematty.black.tower.Systems.DropSystem;
import com.jessematty.black.tower.Systems.DyingSystem;
import com.jessematty.black.tower.Systems.EquipItemSystem;
import com.jessematty.black.tower.Systems.ErrorSystem;
import com.jessematty.black.tower.Systems.PlaySoundSystem;
import com.jessematty.black.tower.Systems.SelfChangeNumericStatsSystem;
import com.jessematty.black.tower.Systems.NumericStatChangeSystem;
import com.jessematty.black.tower.Systems.SetSpeedToMovableSystem;
import com.jessematty.black.tower.Systems.SoundSystem;
import com.jessematty.black.tower.Systems.StringStatChangeSystem;
import com.jessematty.black.tower.Systems.TimeChangingStatSystem;
import com.jessematty.black.tower.Systems.UnEquipItemSystem;
import com.jessematty.black.tower.Systems.AnimationSystem;
import com.jessematty.black.tower.Systems.BoundingBoxRenderer;
import com.jessematty.black.tower.Systems.BreathSystem;
import com.jessematty.black.tower.Systems.ChangeBounds;
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

    private static Comparator<Entity> entityComparator= new Comparator<Entity>() {
        @Override
        public int compare(Entity entity1, Entity entity2) {

            Drawable drawable1=entity1.getComponent(Drawable.class);
            Drawable drawable2=entity2.getComponent(Drawable.class);
            if(drawable1!=null && drawable2!=null) {
                float layerNumber1 = drawable1.getLayerNumber();

                float  layerNumber2 = drawable2.getLayerNumber();


                if (layerNumber1 != layerNumber2) {
                    return (int) (layerNumber2 - layerNumber1);

                } else {

                    String name1=entity1.getComponent(Name.class).getText();
                    String name2=entity2.getComponent(Name.class).getText();



                    return (int) (drawable2.getSubLayerNumber() - drawable1.getSubLayerNumber());


                }

            }

            return 0;


        }
    };


    public  static void addBaseSystemsToEngine(Engine engine, MapDraw draw, ShapeRenderer shapes, boolean drawBounds, FrameBuffer mapBuffer, FrameBuffer lightBuffer ){

       Family drawableFamily= Family.all(Position.class, Drawable.class).get();
       RenderSystem renderSystem=new RenderSystem (draw.getGameComponentMapper(),drawableFamily, entityComparator,  draw.getBatch(), mapBuffer ,2);
       RenderSystem lightSystem=new RenderSystem(draw.getGameComponentMapper(), drawableFamily, entityComparator,  draw.getBatch(), lightBuffer, 3);

       engine.addSystem(renderSystem);
       //engine.addSystem(lightSystem);
       engine.addEntityListener(renderSystem);
       ZRPGPlayerSystem ZRPGPlayerSystem =new ZRPGPlayerSystem(draw);
       engine.addSystem(ZRPGPlayerSystem);
       MoveOnGroundSystem moveOnGroundSystem =new MoveOnGroundSystem(draw);

       engine.addSystem(moveOnGroundSystem);
       AnimationSystem animationSystem =new AnimationSystem(draw, renderSystem, 1);
        engine.addSystem(new SoundSystem(draw, 2));
        engine.addSystem( new PlaySoundSystem(draw, 3));
        engine.addSystem(animationSystem);
       engine.addSystem(new ChangeBounds(draw));
       engine.addSystem(new CollisionSystem(draw));
       engine.addSystem(new SetEntityPositionAndActionToOwnerSystem(0,draw));
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


         engine.addSystem(new SetSpeedToMovableSystem(draw));
       if(drawBounds){
           engine.addSystem(new BoundingBoxRenderer(shapes));
       }
       draw.getInput().addProcessor(ZRPGPlayerSystem);
   }


   public static  void addRenderSystemsToEngine( GameComponentMapper gameComponentMapper, Engine engine, Batch batch,  ShapeRenderer shapes, boolean drawBounds, FrameBuffer mapBuffer, FrameBuffer lightBuffer){

       Family drawableFamily= Family.all(Position.class, Drawable.class).get();
       RenderSystem renderSystem=new RenderSystem( gameComponentMapper, drawableFamily, entityComparator,  batch, mapBuffer ,2);
       RenderSystem lightSystem=new RenderSystem( gameComponentMapper, drawableFamily, entityComparator,  batch, lightBuffer, 3);
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
