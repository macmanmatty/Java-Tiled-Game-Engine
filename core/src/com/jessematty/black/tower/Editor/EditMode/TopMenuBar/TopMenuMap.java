package com.jessematty.black.tower.Editor.EditMode.TopMenuBar;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;

public class TopMenuMap {
   private  MenuBar menuBar= new MenuBar();
    private final Menu file= new Menu("File");
    private final Menu edit= new Menu("Edit");
    private final Menu world= new Menu ("World");
    private final Menu map = new Menu("Map");
    private final Menu windows= new Menu("Windows");
    ObjectMap<String, MenuItem> menuItems= new ObjectMap<>();
    private final MapEditScreen mapEditScreen;


    public TopMenuMap(final MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;


        MenuItem saveWorld = new MenuItem("Save World");
        saveWorld.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        world.addItem(saveWorld);
        menuItems.put( saveWorld.getText().toString(), saveWorld);


        MenuItem loadWorld = new MenuItem("Load World");
        loadWorld.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        world.addItem(loadWorld);
        menuItems.put( loadWorld.getText().toString(), loadWorld);



        MenuItem loadTileSet = new MenuItem("Load Tile Set");
        loadTileSet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        file.addItem(loadTileSet);
        menuItems.put( loadTileSet.getText().toString(), loadTileSet);



        MenuItem createTileSet = new MenuItem("Create Tile Set From Image ");
        createTileSet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        file.addItem(createTileSet);

        menuItems.put( createTileSet.getText().toString(), createTileSet);


        MenuItem loadEntities = new MenuItem("load Entities");
        loadEntities.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        file.addItem(loadEntities);
        menuItems.put( loadEntities.getText().toString(), saveWorld);



        MenuItem loadMap = new MenuItem("Load Map");
        loadMap.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        map.addItem(loadMap);
        menuItems.put( loadMap.getText().toString(), loadMap);
        
        MenuItem saveMap = new MenuItem("Save Map");
        saveMap.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        map.addItem(saveMap);
        menuItems.put( saveMap.getText().toString(), saveMap);

        MenuItem loadTiledMap = new MenuItem("Load  TMX  Tiled Map");
        loadTiledMap.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        map.addItem(loadTiledMap);
        menuItems.put( loadTiledMap.getText().toString(), loadTiledMap);

        menuItems.put( loadMap.getText().toString(), loadMap);

        MenuItem newMap = new MenuItem("New Map");
        newMap.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {




            }
        });
        map.addItem(newMap);
        menuItems.put("New Map", newMap);




        MenuItem bitMaskMap = new MenuItem("Create TileSet");
        bitMaskMap.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        map.addItem(bitMaskMap);
        menuItems.put( bitMaskMap.getText().toString(), bitMaskMap);


        MenuItem changeMapSize = new MenuItem("Change Map Dimensions");
        changeMapSize.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        map.addItem(changeMapSize);
        menuItems.put( changeMapSize.getText().toString(), changeMapSize);



        MenuItem generateTileSet = new MenuItem("Generate BitMaskable  Tile Set");
        generateTileSet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        edit.addItem(generateTileSet);
        menuItems.put( generateTileSet.getText().toString(), generateTileSet);






        MenuItem changeWorldSize = new MenuItem("Change World Size");
        changeWorldSize.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        world.addItem(changeWorldSize);



        menuItems.put( changeWorldSize.getText().toString(), changeWorldSize);

        menuBar.addMenu(file);
        menuBar.addMenu(edit);
        menuBar.addMenu(map);
        menuBar.addMenu(world);





    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public ObjectMap<String, MenuItem> getMenuItems() {
        return menuItems;
    }
}
