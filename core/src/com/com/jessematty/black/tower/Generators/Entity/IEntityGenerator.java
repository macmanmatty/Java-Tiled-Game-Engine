package com.jessematty.black.tower.Generators.Entity;


import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.EntityLoadingException;

public interface IEntityGenerator<T> {
    EntityBag generateEntity(T dtoObject) throws EntityLoadingException;

}
