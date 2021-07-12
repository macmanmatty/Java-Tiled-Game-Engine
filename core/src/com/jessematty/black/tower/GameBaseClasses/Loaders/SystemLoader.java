package com.jessematty.black.tower.GameBaseClasses.Loaders;

import com.badlogic.ashley.core.Engine;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class SystemLoader {
   private  final ClassLoader classLoader;

   private  final  Engine engine;
   private final MapDraw mapDraw;

    public SystemLoader(Engine engine, MapDraw mapDraw,  ClassLoader loader) {
        this.engine = engine;
        this.classLoader=loader;
        this.mapDraw=mapDraw;
    }

    public void loadClass(String directoryPath, String filePath) {
        File file = new File(directoryPath);

        try {
            // Convert File to a URL
            URL url = file.toURI().toURL();          // file:/c:/myclasses/
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory



            // Load in the class; MyClass.class should be located in

            Class clas = this.classLoader.loadClass(filePath);
            Constructor<GameEntitySystem> constructor=clas.getConstructor(MapDraw.class);
            GameEntitySystem gameEntitySystem= constructor.newInstance(mapDraw);
            engine.addSystem(gameEntitySystem);

        } catch (
                MalformedURLException e) {
        } catch (ClassNotFoundException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }



}
