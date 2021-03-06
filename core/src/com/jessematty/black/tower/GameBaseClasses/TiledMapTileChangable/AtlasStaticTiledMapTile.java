package com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public class AtlasStaticTiledMapTile implements  ColoredTiledMapTile {// class the saves the atlas region name of the tile
    // also has logic to change a  tiles color if used with the correct tiled map renderer
    private int id;
    private BlendMode blendMode = BlendMode.ALPHA;
    private MapProperties properties;
    private MapObjects objects;
    private TextureRegion textureRegion;
    private float offsetX;
    private float offsetY;
    private String[] names = new String[1]; // the tile name
    private NamedColor color = NamedColor.WHITE;
    public AtlasStaticTiledMapTile() {
    }
    public AtlasStaticTiledMapTile(AtlasNamedAtlasRegion atlasRegion) {
        this.textureRegion = atlasRegion;
        names[0] = atlasRegion.name;
    }
    public AtlasStaticTiledMapTile(AtlasNamedAtlasRegion textureRegion, NamedColor color, float brightness) {
        this.textureRegion = textureRegion;
        this.color = color;
        names[0] = textureRegion.name;
    }
    public AtlasStaticTiledMapTile(AtlasNamedAtlasRegion textureRegion, String name, NamedColor color, float brightness) {
        this.textureRegion = textureRegion;
        this.names = new String[1];
        names[0] = name;
        this.color = color;
    }
    public AtlasStaticTiledMapTile(AtlasStaticTiledMapTile other) {
        this.id = other.id;
        this.blendMode = other.blendMode;
        this.properties = other.properties;
        this.objects = other.objects;
        this.textureRegion = other.textureRegion;
        this.offsetX = other.offsetX;
        this.offsetY = other.offsetY;
        this.names = other.names;
        this.color = other.color;
    }
    public AtlasStaticTiledMapTile(AtlasNamedAtlasRegion textureRegion, String name) {
        this.textureRegion = textureRegion;
        this.names = new String[1];
        names[0] = name;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public BlendMode getBlendMode() {
        return blendMode;
    }
    @Override
    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }
    @Override
    public MapProperties getProperties() {
        if (properties == null) {
            properties = new MapProperties();
        }
        return properties;
    }
    @Override
    public MapObjects getObjects() {
        if (objects == null) {
            objects = new MapObjects();
        }
        return objects;
    }
    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
    @Override
    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
    @Override
    public float getOffsetX() {
        return offsetX;
    }
    @Override
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }
    @Override
    public float getOffsetY() {
        return offsetY;
    }
    @Override
    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }
    @Override
    public String[] getNames() {
        return names;
    }
    @Override
    public void setNames(String[] names) {
        this.names = names;
    }
    @Override
    public NamedColor getColor() {
        return color;
    }
    @Override
    public void setColor(NamedColor color) {
        this.color = color;
    }
}
