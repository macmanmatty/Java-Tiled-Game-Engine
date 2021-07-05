package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.Components;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class PlayerSerializer extends Serializer<AnimatableComponent> {

    private final GameAssets gameAssets;

    public PlayerSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    @Override
    public void write(Kryo kryo, Output output, AnimatableComponent animatableComponent) {
        kryo.writeClassAndObject(output, animatableComponent.getAnimations());
        kryo.writeObject(output, animatableComponent.isEightDirections());
        kryo.writeObject(output, animatableComponent.isSingleImage());
        kryo.writeObject(output, animatableComponent.getCurrentAction());
        kryo.writeObject(output, animatableComponent.getCurrentFrameNumber());
        kryo.writeObject(output, animatableComponent.getCurrentFrameRate());
        kryo.writeObject(output, animatableComponent.getCurrentLayerNumber());
        kryo.writeClassAndObject(output, animatableComponent.getCurrentDirection());
        kryo.writeObject(output, animatableComponent.isSingleImage());
        if(animatableComponent.isSingleImage()) {
            kryo.writeObject(output, animatableComponent.getStaticTexture().name);
            kryo.writeObject(output, animatableComponent.getStaticTexture().getAtlasName());

        }







    }

    @Override
    public AnimatableComponent read(Kryo kryo, Input input, Class<AnimatableComponent> type) {

        AnimatableComponent animatableComponent= new AnimatableComponent();
       animatableComponent.setAnimations((ObjectMap<String, ObjectMap<String, Animation>>) kryo.readClassAndObject(input));
       animatableComponent.setEightDirections( kryo.readObject(input, Boolean.class));
        animatableComponent.setSingleImage(kryo.readObject(input, Boolean.class));
       animatableComponent.setCurrentAction( kryo.readObject(input, String.class));
        animatableComponent.setCurrentFrameNumber(kryo.readObject(input, Integer.class));
        animatableComponent.setCurrrentFrameRate(kryo.readObject(input, Integer.class));
        animatableComponent.setCurrentLayerNumber(kryo.readObject(input, Integer.class));
        animatableComponent.setCurrentDirection((Direction) kryo.readClassAndObject(input));
        boolean singleImage=kryo.readObject(input, Boolean.class);
        animatableComponent.setSingleImage(singleImage);
        if(singleImage) {
            AtlasNamedAtlasRegion atlasRegion = gameAssets.getAtlasRegionByName(kryo.readObject(input, String.class), kryo.readObject(input, String.class));
            animatableComponent.setStaticTexture(atlasRegion);
        }

    ObjectMap<String, ObjectMap<String, Animation>> animations=animatableComponent.getAnimations();
        Keys<String> keys=animations.keys();
        while(keys.hasNext==true ) {
            String key=keys.next();
            ObjectMap<String,  Animation> names=animations.get(key);
            Keys<String> keys2=names.keys();
            while( keys2.hasNext==true ) {
                String key2=keys2.next();
                Animation animation=names.get(key2);
                animation.deSerialize(gameAssets);

            }
        }


        return animatableComponent;

    }
}
