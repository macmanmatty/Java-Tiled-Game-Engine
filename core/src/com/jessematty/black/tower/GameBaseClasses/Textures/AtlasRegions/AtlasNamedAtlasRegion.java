package com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;


// atlas region that stores the name of the atlas it is stored in
public class AtlasNamedAtlasRegion  extends AtlasRegion  {
   private  String atlasName; // the name of the atlas
   private String pageName=" "; // the page to put the region into used to make sure that texture gets on the correct page


    /**
     *
     * @param region
     * @param atlasName
     */

    public AtlasNamedAtlasRegion(AtlasRegion region, String atlasName) {
        super(region);
        this.atlasName = atlasName;
    }


    /**
     *
     * @param region
     * @param regionName
     * @param atlasName
     */
    public AtlasNamedAtlasRegion(AtlasRegion region, String regionName,  String atlasName) {
        super(region);
        this.atlasName = atlasName;
        region.name=regionName;
    }

    /**
     *
     * @param region
     */
    public AtlasNamedAtlasRegion(AtlasNamedAtlasRegion region) {
        super(region);
        this.atlasName=region.atlasName;
        this.pageName=region.pageName;


    }


    /**
     *
     * @param texture
     * @param x
     * @param y
     * @param width
     * @param height
     */

    public AtlasNamedAtlasRegion(Texture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    /**
     *
     * @param region
     */

    public AtlasNamedAtlasRegion(AtlasRegion region) {
        super(region);
    }

    /**
     *
     * @param region
     */
    public AtlasNamedAtlasRegion(TextureRegion region) {
        super(region);
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }



}
