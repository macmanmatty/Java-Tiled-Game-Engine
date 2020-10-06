package com.jessematty.black.tower.GameBaseClasses.Loaders.Copy;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.TextureAtlas.AtlasNamedAtlasRegionSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.EntitySerializer;

public class CopyObject {


    private JsonLoader jsonLoader;




    public CopyObject(GameAssets gameAssets) {
        this.jsonLoader=gameAssets.getJsonLoader();
        jsonLoader.getJson().setSerializer(Entity.class, new EntitySerializer(gameAssets));
        jsonLoader.getJson().setSerializer(AtlasNamedAtlasRegion.class, new AtlasNamedAtlasRegionSerializer(gameAssets));

    }

    public<T> T copyObject(T object, Class<T>  objectClass){


        return  jsonLoader.copyObject(object, objectClass);

    }
}
