package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.Entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Components.NewComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.SerializableComponet;
import com.jessematty.black.tower.Components.Tiles.Tile;
import com.jessematty.black.tower.Components.Transient;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.TransientChecker;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class LandSquareTileKryoSerializer extends Serializer<LandSquareTile> {

    private final GameAssets gameAssets;
    private final TransientChecker transientChecker = new TransientChecker();


    public LandSquareTileKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    @Override
    public void write(Kryo kryo, Output output, LandSquareTile entity) {

        Array<Component> components= new Array<>();
        for (Component component : entity.getComponents()) {


            if(component.getClass().isAnnotationPresent(NewComponent.class)){
                try {
                    component=component.getClass().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            if(component.getClass().isAnnotationPresent(Transient.class)){
                entity.remove(component.getClass());


            }
            if(component instanceof SerializableComponet){
                ((SerializableComponet) component).serialize();
            }
            components.add(component);



        }


        kryo.writeClassAndObject(output, components);





    }

    @Override
    public LandSquareTile read(Kryo kryo, Input input, Class<LandSquareTile> type) {

            LandSquareTile landSquareTile = new LandSquareTile();
            Array<Component> components= (Array<Component>) kryo.readClassAndObject(input);
            int size=components.size;
            for(int count=0; count<size; count++) {
                Component component=components.get(count);
                if(components.get(count) instanceof  SerializableComponet){
                    ((SerializableComponet) component).deSerialize(gameAssets);
                }
                landSquareTile.add(components.get(count));
                if(component instanceof  Tile){
                    landSquareTile.setTileComponent((Tile) component);
                }
                if(component instanceof  PositionComponent){
                    landSquareTile.setPosition((PositionComponent) component);
                }



            }




            return  landSquareTile;





    }
}
