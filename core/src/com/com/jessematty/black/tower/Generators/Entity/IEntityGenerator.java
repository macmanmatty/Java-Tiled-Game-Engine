package com.jessematty.black.tower.Generators.Entity;


import com.badlogic.ashley.core.Entity;

public interface IEntityGenerator {
    Entity generateEntity(String name);

}
