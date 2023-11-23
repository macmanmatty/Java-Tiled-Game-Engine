package com.jessematty.black.tower.Components.Other;

public enum Sex {

    MALE("Male"), FEMALE("Female"), SKELETON("Skeleton");

    String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

}
