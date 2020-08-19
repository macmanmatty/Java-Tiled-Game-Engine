package com.jessematty.black.tower.Editor.World;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskableTileSet;
public class WorldObjects {
    private Array<NumericStat> numericStats =  new Array<NumericStat>();
    private Array<BooleanStat> booleanStats =  new Array<BooleanStat>();
    private Array<StringStat> stringStats =  new Array<StringStat>();
    private Array<String> changeActions= new Array<>();
    private Array<Entity> entitiesInWorld = new Array<>();
    private Array<BitMaskableTileSet> tileSet= new Array<BitMaskableTileSet>();
    private Array<String> groups= new Array<>();
    private Array<TextureRegionPage> textureRegionGroups= new Array<>();
    private Array<NamedColor> namedColors= new Array<>();
    private Array<String> textures = new Array<>();
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
        if(!InList.isInList(color, namedColors)) {
            namedColors.add(color);
        }
    }
    public void  addStat(StringStat stat){
        if(!InList.isInList(stat, stringStats)) {
            stringStats.add(stat);
        }
    }
    public void  addStat(BooleanStat stat){
        if(!InList.isInList(stat, booleanStats)) {
            booleanStats.add(stat);
        }
    }
    public void  addStat(NumericStat stat){
        if(!InList.isInList(stat, numericStats)) {
            numericStats.add(stat);
        }
    }
    public Array<TextureRegionPage> getTextureRegionGroups() {
        return textureRegionGroups;
    }
}
