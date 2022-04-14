package com.jessematty.black.tower.GameBaseClasses.Serialization.Json.Entity;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class DirectionSerializer implements Json.Serializer<Direction> { // serializer for the libgdx atlas region class


    public DirectionSerializer() {

    }

    @Override
    public void write(Json json, Direction direction, Class aClass) {
        json.writeObjectStart();
       json.writeValue( "name", direction.toString().toLowerCase()); // write the json name
       json.writeObjectEnd();

    }

    @Override
    public Direction read(Json json, JsonValue jsonValue, Class aClass) {

        String name=json.readValue("name", String.class, jsonValue); // load the  atlas region from asset manager

        return  Direction.getDirection(name);





    }
}

