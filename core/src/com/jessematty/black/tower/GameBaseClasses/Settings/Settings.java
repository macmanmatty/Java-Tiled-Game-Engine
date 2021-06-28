package com.jessematty.black.tower.GameBaseClasses.Settings;

import com.badlogic.gdx.utils.ObjectMap;

public class Settings  {


    public Settings() {
    }

    public ObjectMap<String, Object> settings= new ObjectMap<>();



    public ObjectMap<String, Object> getSettings() {
        return settings;
    }

    public void setSettings(ObjectMap<String, Object> settings) {
        this.settings = settings;
    }

    public <T> T getSimpleSetting(String key, Class<T> objectClass) {

        return (T) settings.get(key);

    }
}
