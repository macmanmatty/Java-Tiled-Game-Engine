package com.jessematty.black.tower.Components.Position;
import com.badlogic.ashley.core.Component;

/**
 * Component class for Entities that are Physical  aka take up space ona map
 */
public class PhysicalObjectComponent implements Component { // component for objects that have a  mass and  a volume
    /**
     * entities mass in world mass units can't be less than or equal to zero
     */
    private float mass = 1f; // the entities mass
    /**
     * entities volume in world volume units can't be less than or equal to zero
     */
    private float volume = 1f;// the entities  volume
    /**
     * entity solidity  0=gas .5=fluid 1=solid used for collision Detection
     */
    private float solidity = 1f;
    /**
     * whether or not the entity can be collided into
     */
    private boolean collidable;


    public PhysicalObjectComponent(float mass, float volume) {
        this.mass = mass;
        this.volume = volume;
    }

    public PhysicalObjectComponent(float mass, float volume, float solidity) {
        this.mass = mass;
        this.volume = volume;
        this.solidity = solidity;
    }

    public PhysicalObjectComponent() {
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        if(mass<=0){
            this.mass=1;
        }
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if(this.volume<=0){
            this.volume=1;
        }
    }

    public float getDensity() {
        return mass / volume;
    }

    public float getSolidity() {
        return solidity;
    }

    public void setSolidity(float solidity) {
        this.solidity = solidity;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }


}
