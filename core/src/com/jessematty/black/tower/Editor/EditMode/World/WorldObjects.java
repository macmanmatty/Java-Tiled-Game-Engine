package com.jessematty.black.tower.Editor.World;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit.NamedComponent;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskableTileSet;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class WorldObjects {
    private Array<NumericStat> numericStats =  new Array<NumericStat>();
    private Array<BooleanStat> booleanStats =  new Array<BooleanStat>();
    private Array<StringStat> stringStats =  new Array<StringStat>();
    private Array<String> changeActions= new Array<>();
    private Array<NamedComponent> components= new Array<>();
    private Array<Entity> entitiesInWorld = new Array<>();
    private Array<BitMaskableTileSet> tileSet= new Array<BitMaskableTileSet>();
    private Array<String> groups= new Array<>();
    private Array<TextureRegionPage> textureRegionGroups= new Array<>();
    private Array<NamedColor> namedColors= new Array<>();
    private Array<String> textures = new Array<>();
    private Array<AnimatableComponent> animatableComponents= new Array<>();
    private Array<EditorImageComponent> editorImageComponents= new Array<>();
    private Array<ImageComponent> imageComponents= new Array<>();
    private Array<TileSet> tileSets= new Array<>();
    private Array<GameEntitySystem> entitySystems= new Array<>();

    public WorldObjects() {
        numericStats.add( new NumericStat(true, "speed", 0, 0 , Double.MAX_VALUE));
        changeActions.add("ingest");
        changeActions.add("touch");
        changeActions.add("penetrate");
        changeActions.add("self");
        namedColors.add(new NamedColor(Color.WHITE, "White"));
    }

    public Array<NumericStat> getNumericStats() {
        return numericStats;
    }

    public void setNumericStats(Array<NumericStat> numericStats) {
        this.numericStats = numericStats;
    }

    public Array<BooleanStat> getBooleanStats() {
        return booleanStats;
    }

    public void setBooleanStats(Array<BooleanStat> booleanStats) {
        this.booleanStats = booleanStats;
    }

    public Array<StringStat> getStringStats() {
        return stringStats;
    }

    public void setStringStats(Array<StringStat> stringStats) {
        this.stringStats = stringStats;
    }

    public Array<Entity> getEntitiesInWorld() {
        return entitiesInWorld;
    }
    public Array<String> getChangeActions() {
        return changeActions;
    }
    public Array<String> getGroups() {
        return groups;
    }
    public Array<BitMaskableTileSet> getTileSet() {
        return tileSet;
    }
    public Array<String> getTextures() {
        return textures;
    }

    public Array<NamedColor> getNamedColors() {
        return namedColors;
    }
    public void  addColor(NamedColor color){
        if(!InList.isInList(namedColors, color)) {
            namedColors.add(color);
        }
    }
    public void  addStat(StringStat stat){
        if(!InList.isInList(stringStats, stat)) {
            stringStats.add(stat);
        }
    }
    public void  addStat(BooleanStat stat){
        if(!InList.isInList(booleanStats, stat)) {
            booleanStats.add(stat);
        }
    }
    public void  addStat(NumericStat stat){
        if(!InList.isInList(numericStats, stat)) {
            numericStats.add(stat);
        }
    }
    public Array<TextureRegionPage> getTextureRegionGroups() {
        return textureRegionGroups;
    }

    public Array<AnimatableComponent> getAnimatableComponents() {
        return animatableComponents;
    }

    public Array<EditorImageComponent> getEditorImageComponents() {
        return editorImageComponents;
    }

    public Array<ImageComponent> getImageComponents() {
        return imageComponents;
    }

    public Array<TileSet> getTileSets() {
        return tileSets;
    }

    public Array<NamedComponent> getComponents() {
        return components;
    }

    public  void addComponent( String name , Component component){

        components.add(new NamedComponent(name, component));


    }

    public Array<GameEntitySystem> getEntitySystems() {
        return entitySystems;
    }


}
