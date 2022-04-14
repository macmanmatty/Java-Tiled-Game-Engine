package com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations;

import com.badlogic.gdx.utils.ObjectMap;

public class LPCAnimation {
    public String userSex;
    public String userKind;
    public String entityName;
    public ObjectMap<String, String> animationsNames= new ObjectMap<>();

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public ObjectMap<String, String> getAnimationsNames() {
        return animationsNames;
    }

    public void setAnimationsNames(ObjectMap<String, String> animationsNames) {
        this.animationsNames = animationsNames;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }
}
