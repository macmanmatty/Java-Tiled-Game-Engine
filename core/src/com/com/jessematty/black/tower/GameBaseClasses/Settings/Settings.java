package com.jessematty.black.tower.GameBaseClasses.Settings;
import com.badlogic.gdx.utils.ObjectMap;
public class Settings  {
    final  public ObjectMap<String, Object> settings= new ObjectMap<>();
    public Settings() {
    }
    public ObjectMap<String, Object> getSettings() {
        return settings;
    }
    public <T> T getSimpleSetting(String key, Class<T> objectClass) {
        return (T) settings.get(key);
    }
}
