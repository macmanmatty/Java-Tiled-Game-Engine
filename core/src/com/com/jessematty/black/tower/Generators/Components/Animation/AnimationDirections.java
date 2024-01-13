package com.jessematty.black.tower.Generators.Components.Animation;

/**
 * enum for supported number of animation directions
 */
public enum AnimationDirections {
    ONE(1),  FOUR(4), EIGHT(8);
    int directions;

    AnimationDirections(int directions) {
        this.directions = directions;
    }

    public int getDirections() {
        return directions;
    }
}
