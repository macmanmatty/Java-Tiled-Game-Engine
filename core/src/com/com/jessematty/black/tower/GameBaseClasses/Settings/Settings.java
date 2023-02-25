package com.jessematty.black.tower.GameBaseClasses.Settings;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Map  of objects used for settings.
 */
public class Settings  {
    final  public ObjectMap<String, Object> settings= new ObjectMap<>();
    public Settings() {
    }
    public ObjectMap<String, Object> getSettings() {
        return settings;
    }

    /**
     *
     * @param key they key of teh object to retrieve
     * @param objectClass the objects class class object
     * @param <T> the returned  objects class
     * @return an object of type T
     */
    public <T> T getSimpleSetting(String key, Class<T> objectClass) {
        return (T) settings.get(key);
    }
}
