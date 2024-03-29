package com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.TimeUtils;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;


// animated tiled map tile class that implements the ColoredTiledMapTile  interface
// linked to a texture atlas for saving of the tile and the texture atlas
public class AtlasAnimatedTiledMapTile implements  ColoredTiledMapTile {
    String  [] names;
    NamedColor color = NamedColor.WHITE;
    private static long lastTiledMapRenderTime = 0;
    private int id;
    private BlendMode blendMode = BlendMode.ALPHA;
    private MapProperties properties;
    private MapObjects objects;
    private AtlasStaticTiledMapTile[] frameTiles;
    private int[] animationIntervals;
    private int frameCount = 0;
    private int loopDuration;
    private static final long initialTimeOffset = TimeUtils.millis();
    public AtlasAnimatedTiledMapTile(float interval, Array<AtlasStaticTiledMapTile> frameTiles) {
        this.frameTiles = new AtlasStaticTiledMapTile[frameTiles.size];
        this.frameCount = frameTiles.size;
        this.names= new String[frameTiles.size];
        this.loopDuration = frameTiles.size * (int)(interval * 1000f);
        this.animationIntervals = new int[frameTiles.size];
        for (int i = 0; i < frameTiles.size; ++i) {
            this.frameTiles[i] = frameTiles.get(i);
            this.names[i]=frameTiles.get(i).getNames()[0];
            this.animationIntervals[i] = (int)(interval * 1000f);
        }
    }
    public AtlasAnimatedTiledMapTile( int []  intervals, AtlasStaticTiledMapTile [] frameTiles) {
        this.frameTiles = new AtlasStaticTiledMapTile[frameTiles.length];
        this.names= new String[frameTiles.length];
        this.frameCount = frameTiles.length;
        this.animationIntervals = intervals;
        this.loopDuration = 0;
        for (int i = 0; i < intervals.length; ++i) {
            this.frameTiles[i] = frameTiles[i];
            this.names[i]=frameTiles[i].getNames()[0];
            this.loopDuration += intervals[i];
        }
    }


    public AtlasAnimatedTiledMapTile(AnimatedTiledMapTile other, String atlasName) {
        this.id = other.getId();
        this.blendMode = other.getBlendMode();
        this.properties = other.getProperties();
        this.objects = other.getObjects();
        this.color = NamedColor.WHITE;
        StaticTiledMapTile [] otherTiles=other.getFrameTiles();
        this.frameTiles= new AtlasStaticTiledMapTile[otherTiles.length];

    }


    public AtlasAnimatedTiledMapTile(AtlasAnimatedTiledMapTile other) {
        this.names = other.names;
        this.color = other.color;
        this.id = other.id;
        this.blendMode = other.blendMode;
        this.properties = other.properties;
        this.objects = other.objects;
        this.frameTiles = other.frameTiles;
        this.animationIntervals = other.animationIntervals;
        this.frameCount = other.frameCount;
        this.loopDuration = other.loopDuration;
    }

    @Override
    public int getId () {
        return id;
    }
    @Override
    public void setId (int id) {
        this.id = id;
    }
    @Override
    public BlendMode getBlendMode () {
        return blendMode;
    }
    @Override
    public void setBlendMode (BlendMode blendMode) {
        this.blendMode = blendMode;
    }
    public int getCurrentFrameIndex () {
        int currentTime = (int)(lastTiledMapRenderTime % loopDuration);
        for (int i = 0; i < animationIntervals.length; ++i) {
            int animationInterval = animationIntervals[i];
            if (currentTime <= animationInterval) return i;
            currentTime -= animationInterval;
        }
        throw new GdxRuntimeException(
                "Could not determine current animation frame in AnimatedTiledMapTile.  This should never happen.");
    }
    public TiledMapTile getCurrentFrame () {
        return frameTiles[getCurrentFrameIndex()];
    }
    @Override
    public TextureRegion getTextureRegion () {
        return getCurrentFrame().getTextureRegion();
    }
    @Override
    public void setTextureRegion (TextureRegion textureRegion) {
        throw new GdxRuntimeException("Cannot set the texture region of AnimatedTiledMapTile.");
    }
    @Override
    public float getOffsetX () {
        return getCurrentFrame().getOffsetX();
    }
    @Override
    public void setOffsetX (float offsetX) {
        throw new GdxRuntimeException("Cannot set offset of AnimatedTiledMapTile.");
    }
    @Override
    public float getOffsetY () {
        return getCurrentFrame().getOffsetY();
    }
    @Override
    public void setOffsetY (float offsetY) {
        throw new GdxRuntimeException("Cannot set offset of AnimatedTiledMapTile.");
    }
    public int[] getAnimationIntervals () {
        return animationIntervals;
    }
    public void setAnimationIntervals (int[] intervals) {
        if (intervals.length == animationIntervals.length) {
            this.animationIntervals = intervals;
            loopDuration = 0;
            for (int i = 0; i < intervals.length; i++) {
                loopDuration += intervals[i];
            }
        } else {
            throw new GdxRuntimeException("Cannot set " + intervals.length
                    + " frame intervals. The given int[] must have a size of " + animationIntervals.length + ".");
        }
    }
    @Override
    public MapProperties getProperties () {
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
    /** Function is called by BatchTiledMapRenderer render(), lastTiledMapRenderTime is used to keep all of the tiles in lock-step
     * animation and avoids having to call TimeUtils.millis() in getTextureRegion() */
    public static void updateAnimationBaseTime () {
        lastTiledMapRenderTime = TimeUtils.millis() - initialTimeOffset;
    }
    public AtlasStaticTiledMapTile[] getFrameTiles () {
        return frameTiles;
    }
    @Override
    public String[] getNames() {
        return names;
    }
    @Override
    public void setNames(String[] names) {
        this.names = names;
    }
    public NamedColor getColor() {
        return color;
    }
    public void setColor(NamedColor color) {
        this.color = color;
    }
}
