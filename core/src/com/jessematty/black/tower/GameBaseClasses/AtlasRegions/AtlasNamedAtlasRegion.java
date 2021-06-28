package com.jessematty.black.tower.GameBaseClasses.AtlasRegions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


// atlas region that stores the name of the atlas it is stored in
public class AtlasNamedAtlasRegion  extends AtlasRegion  {
   private  String atlasName;
   private String pageName;


    public AtlasNamedAtlasRegion(AtlasRegion region, String atlasName) {
        super(region);
        this.atlasName = atlasName;
    }

    public AtlasNamedAtlasRegion(AtlasNamedAtlasRegion region) {
        super(region);
        this.atlasName=region.atlasName;
        this.pageName=region.pageName;


    }



    public AtlasNamedAtlasRegion(Texture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    public AtlasNamedAtlasRegion(AtlasRegion region) {
        super(region);
    }

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
