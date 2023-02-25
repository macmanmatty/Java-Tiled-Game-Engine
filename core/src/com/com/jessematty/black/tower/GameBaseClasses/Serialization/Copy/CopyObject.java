package com.jessematty.black.tower.GameBaseClasses.Serialization.Copy;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Json.Entity.EntitySerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.TextureAtlas.AtlasNamedAtlasRegionSerializer;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
public class CopyObject {
    private JsonLoader jsonLoader= new JsonLoader();

    public CopyObject(GameAssets gameAssets) {

        jsonLoader.getJson().setSerializer(Entity.class, new EntitySerializer(gameAssets));
        jsonLoader.getJson().setSerializer(AtlasNamedAtlasRegion.class, new AtlasNamedAtlasRegionSerializer(gameAssets));
    }
    public<T> T copyObject(T object, Class<T>  objectClass){
        return  jsonLoader.copyObject(object, objectClass);
    }
}
