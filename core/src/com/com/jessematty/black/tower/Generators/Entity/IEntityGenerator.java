package com.jessematty.black.tower.Generators.Entity;


import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;

public interface IEntityGenerator<T> {
    EntityBag generateEntity(T dtoObject);

}
