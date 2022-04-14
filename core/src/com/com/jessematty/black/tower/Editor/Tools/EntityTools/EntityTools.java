package com.jessematty.black.tower.Editor.Tools.EntityTools;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;

public class EntityTools {
    public EntityTools() {
    }
    // centers the screen  to the location of an entity
    public static void  goToEntity(MapEditScreen mapEditScreen, Entity entity){
        if(entity==null){
            return;
        }
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        // has no position can go not  to enity
        if(positionComponent==null){
            return;
        }
        else{
            float locationX=positionComponent.getLocationX();
            float locationY=positionComponent.getLocationY();
           // mapEditScreen.setCameraToMapCoordinates(locationX, locationY);
        }
    }

    public void getEntityStat(String name , Class<?extends Stat> statClass){
      
         ComponentMapper<BooleanStats> booleanStatsComponentMapper= GameComponentMapper.getBooleanStatsComponentMapper();
         ComponentMapper<NumericStats> numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();
         ComponentMapper<StringStats> stringStatsComponentMapper=GameComponentMapper.getStringStatsComponentMapper();
        ComponentMapper<BooleanStatsChangeable> booleanStatsChangeableComponentMapper= GameComponentMapper.getBooleanStatsChangableComponentMapper();
        ComponentMapper<NumericStatsChangeable> numericStatsChangableComponentMapper=GameComponentMapper.getNumericStatsChangableComponentMapper();
        ComponentMapper<StringStatsChangeable> stringStatsChangableComponentMapper=GameComponentMapper.getStringStatsChangableComponentMapper();


        if(statClass==NumericStat.class){



        }

        else if(statClass== BooleanStat.class){


        }



        else if(statClass== StringStat.class){


        }

        else if(statClass== NumericStatChangeable.class){


        }


              else if(statClass== NumericStatChangeable.class){


              }
              else if(statClass== BooleanStatChangeable.class){


              }



    }
    public  String getOwnerName(World world, Entity entity){
        OwnedComponent ownedComponent= entity.getComponent(OwnedComponent.class);
        if(ownedComponent==null){

            return  "Not Owned By Another Entity";

        }

        else{

            String id = ownedComponent.getOwnerEntityID();
            Entity owner=world.getEntity(id);
            if(owner==null){

                 return "Owner Does Not Exist";

            }

            Name nameComponent=owner.getComponent(Name.class);

            if(nameComponent==null){

              return  id;


            }

            else{

                return nameComponent.getName();

            }




        }


    }





    // gets an image for entity
    public static Image getEntityImage(Entity entity){
        if(entity==null){
            return null;
        }
        DrawableComponent drawableComponent=entity.getComponent(DrawableComponent.class);
        // has no drawable and therefore no image
        if(drawableComponent==null){
            EditorImageComponent editorImageComponent=entity.getComponent(EditorImageComponent.class);
            if(editorImageComponent==null){
                return  null;
            }
            Color color=editorImageComponent.getColor();
            TextureRegion region=editorImageComponent.getAtlasRegion();
            return createImage(color, region);
        }
        else{
            Color color=drawableComponent.getColor();
            TextureRegion region=drawableComponent.getTextureRegion();
            return createImage( color, region);
        }
    }
    
    // creates an  image from taxture region with a given color 
    private static  Image  createImage( Color color, TextureRegion region){
            Image image= new Image(new TextureRegionDrawable(region));
            if(color!=null) {
                image.setColor(color);
            }
            return image;
        }
    // inverts the draw bounds flag on  a entities   position component
    public  static void  setDrawBounds(Entity entity){
        if(entity==null){
            return;
        }
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        // has no position cant have bounds to set
        if( positionComponent==null){
            return;
        }
        else{
            positionComponent.setDrawBounds(!positionComponent.isDrawBounds());
        }
    }




    // creates a basic entity
    public Entity createNewEntity(boolean isPhysical, boolean hasLocation, boolean moves,  boolean animatable, boolean changesStats,  Image image, String entityName, Component... components){
        Entity entity= new Entity();
        Name name= new Name(true, entityName);
        entity.add(new ID());
        entity.add(name);
        entity.add(new BooleanStats());
        entity.add(new StringStats());
        entity.add(new NumericStats());

        if(changesStats) {
            entity.add(new NumericStatsChangeable());
            entity.add(new StringStatsChangeable());
            entity.add(new BooleanStatsChangeable());
        }

        if(animatable){
            entity.add(new AnimatableComponent());
        }
        if(image!=null){

            ImageComponent imageComponent= new ImageComponent();
            entity.add(imageComponent);
        }

        if(hasLocation){
            entity.add(new PositionComponent());
        }
        if(moves){
            entity.add(new MovableComponent());

        }


        if(isPhysical){
            entity.add(new PhysicalObjectComponent());


        }
        for(int count=0; count<components.length; count++) {
            entity.add(components[count]);

        }
        return  entity;

    }


    public static void placeEntity(GameMap currentMap, World world,   Entity entity, float x, float y ){


            Entity entityToPlace = (Entity) entity;

            PositionComponent position = GameComponentMapper.getPositionComponentMapper().get(entityToPlace);
            position.setMapID(currentMap.getId());
            position.setLocationX(x);
            position.setLocationY(y);
            world.addEntityToWorld(entityToPlace);
        }





}
