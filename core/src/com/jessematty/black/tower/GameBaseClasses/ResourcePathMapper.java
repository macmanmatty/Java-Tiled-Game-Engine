package com.jessematty.black.tower.GameBaseClasses;

import com.badlogic.gdx.utils.ObjectMap;

public class ResourcePathMapper {
    /**
     *  the paths to the resources first map key is type second map key is  resource name  to the resource mapped to the resourceName path
     */
   private final ObjectMap<Class, ObjectMap<String, String>>  resourcePathMaps= new ObjectMap<>();

    /**
     * adds a resource to the map of resource
     * @param resourceClass  the class type of the resource ie TextureAtlas.class
     * @param name the name of the resource such as   assets
     * @param path the ABSOLUTE PATH to the resource
     */
   private void  putResourcePath(Class resourceClass, String name, String path ){

       if(resourcePathMaps.get(resourceClass)==null){
           ObjectMap<String, String> resourcePaths= new ObjectMap<>();
           resourcePaths.put(name, path);
           resourcePathMaps.put(resourceClass, resourcePaths );
       }

   }

    /**
     *  returns a path for given resource class type and name
     * @param resourceClass the class type of the resource ie TextureAtlas.class
     * @param resourceName the name of the resource such as   assets
     * @return
     */
   public String getResourcePath( Class resourceClass, String resourceName){
       return  resourcePathMaps.get(resourceClass).get(resourceName);
   }
    public ObjectMap<Class, ObjectMap<String, String>> getResourcePathMaps() {
        return resourcePathMaps;
    }
}
