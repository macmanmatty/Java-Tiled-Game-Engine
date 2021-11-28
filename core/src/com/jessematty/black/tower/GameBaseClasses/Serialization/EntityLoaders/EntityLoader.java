package com.jessematty.black.tower.GameBaseClasses.Serialization.EntityLoaders;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Entity.EntitySerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.TextureAtlas.AtlasNamedAtlasRegionSerializer;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.World;

import java.io.File;

public class EntityLoader {

   private  GameAssets assetts;
   private JsonLoader jsonLoader;
   private String fileSeperator;
   private World world;
   private Engine engine;
    public EntityLoader(GameAssets assetts) {

        this.assetts = assetts;
        jsonLoader = assetts.getJsonLoader();
        fileSeperator = FileUtilities.getFileSeparator();
        world = new World();
        engine = world.getEngine();
        jsonLoader.getJson().setSerializer(Entity.class, new EntitySerializer(assetts));
        jsonLoader.getJson().setSerializer(AtlasNamedAtlasRegion.class, new AtlasNamedAtlasRegionSerializer(assetts));

    }
    public   void  loadEntities(String path){

        FileAction loadEntity= new FileAction() {
            @Override
            public void act(File file) {

                if(FileUtilities.getExtensionOfFile(file).equalsIgnoreCase("entity")) {
                    Entity entity = jsonLoader.loadObject(Entity.class, file.getAbsolutePath());
                    if (entity != null) {
                        world.addEntityToWorld(entity);
                    }
                }
                else if (FileUtilities.getExtensionOfFile(file).equals("entities")){

                    Entity []  entities = jsonLoader.loadObject(Entity[].class, file.getAbsolutePath());
                    if (entities != null) {
                        int size=entities.length;
                        for(int count=0; count<size; count++) {
                            world.addEntityToWorld(entities[count]);

                        }


                    }                }

            }
        };

        FileUtilities.actOnFiles(path, loadEntity);

    }

    public void saveEntities (World world, String directory){
        ObjectMap<String, Entity> entityObjectMap=world.getEntitiesInWorld();
        directory=directory+FileUtilities.getFileSeparator()+"worldEntities.entities";
        assetts.saveObject(entityObjectMap, directory, false);

    }





}
