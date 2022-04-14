package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;

/**
 * class that holds all of the input processors for map editing
 */
public class MapEditInputProcessors {
    private MapEditScreen mapEditScreen;
    private ObjectMap< String, MapEditProcessor> inputProcessors= new ObjectMap<>();
    private MapEditProcessor currentInputProcessor;

    public MapEditInputProcessors(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
        EntityPlacementProcessor entityPlacementProcessor= new EntityPlacementProcessor(mapEditScreen);
        inputProcessors.put(EntityPlacementProcessor.class.toString(), entityPlacementProcessor);
        FillBucketProcessor fillBucketProcessor= new FillBucketProcessor(mapEditScreen);
        inputProcessors.put(FillBucketProcessor.class.toString(),fillBucketProcessor);
        SelectInputProcessor selectInputProcessor= new SelectInputProcessor(mapEditScreen);
        inputProcessors.put(SelectInputProcessor.class.toString(),selectInputProcessor);
        TiledMapStatic2DGridTilePlacementProcessor tiledMapStatic2DGridTilePlacementProcessor= new TiledMapStatic2DGridTilePlacementProcessor( mapEditScreen);
        inputProcessors.put(TiledMapStatic2DGridTilePlacementProcessor.class.toString(),tiledMapStatic2DGridTilePlacementProcessor);
        TiledMapStaticTilePlacementProcessor tiledMapStaticTilePlacementProcessor= new TiledMapStaticTilePlacementProcessor( mapEditScreen);
        inputProcessors.put(TiledMapStaticTilePlacementProcessor.class.toString(),tiledMapStaticTilePlacementProcessor);
        CopyInputProcessor copyInputProcessor= new CopyInputProcessor( mapEditScreen);
        inputProcessors.put(CopyInputProcessor.class.toString(),copyInputProcessor);


    }
    public ObjectMap<String, MapEditProcessor> getInputProcessors() {
        return inputProcessors;
    }

    public void setInputProcessors(ObjectMap<String, MapEditProcessor> inputProcessors) {
        this.inputProcessors = inputProcessors;
    }

    public MapEditProcessor getCurrentInputProcessor() {
        return currentInputProcessor;
    }


}
