package com.jessematty.black.tower.GameBaseClasses.Utilities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

import java.util.Objects;

public class TextureTools {


    private  TextureTools() {
    }

    // check to see if texture atlas has a given texture region
    public static  boolean textureInAtlas(TextureRegion region, TextureAtlas namedTextureAtlas) {
        
        return  textureInArray(region, namedTextureAtlas.getRegions());
        
    }
    
    public static  boolean textureInGroups(TextureRegion region, Array<TextureRegionPage> pages) {
        int size=pages.size;
        for(int count=0; count<size; count++) {
             boolean inGroup=textureInArray(region, pages.get(count).getPageRegions());
             if(inGroup==true){
                 return true;
             }
        }
        
        
        return  false;
        
        
    }

    public AtlasRegion [] [] splitTexture(int width, int height, Texture texture, String atlasName, String textureName){
        AtlasRegion region = new AtlasRegion(texture, 0, 0 , texture.getWidth(), texture.getHeight());
        AtlasRegion [] []regionSplit= (AtlasRegion[][]) region.split(width, height);

        return  regionSplit;

    }
    
    
        public static  boolean textureInArray(TextureRegion region, Array<? extends TextureRegion> regions) {
        int size=regions.size;
        for(int count=0; count<size; count++){
            if(regionsEquals(region,  regions.get(count))){
                return  false;
            }
        }
        return true;
    }
    // check to see if two texture regions or sub classes  of texture region contain the same image 
    // by comparing x y , width , height and texture  of each region
    public static  boolean regionsEquals(TextureRegion region,  TextureRegion region2){
        int width1=region.getRegionWidth();
        int width2=region2.getRegionWidth();
        int height1=region.getRegionHeight();
        int height2=region2.getRegionHeight();
        int x1=region.getRegionX();
        int x2=region2.getRegionX();
        int y1=region.getRegionY();
        int y2=region2.getRegionY();
        Texture texture1=region.getTexture();
        Texture texture2=region2.getTexture();
       if(height1!=height2 || width1!=width2  || x1!=x2 || y1!=y2 || texture1!=texture2){
            return  false;
        }
       
       return true;
       
    }


    public static  void addRegionsToTextureAtlas(TextureAtlas atlas, TextureAtlas atlasToAddTo){
       Array<AtlasRegion>  regions=atlas.getRegions();
       int size=regions.size;
       for(int count=0; count<size; count++){
           AtlasRegion atlasNamedAtlasRegion=regions.get(count);
           atlasToAddTo.addRegion(atlasNamedAtlasRegion.name, atlasNamedAtlasRegion);


       }


    }



    public boolean equals(TextureRegion r1, TextureRegion r2) {

        return
                Objects.equals(r1.getRegionX(), r2.getRegionX()) &&
                Objects.equals(r1.getRegionY(), r2.getRegionY()) &&
                Objects.equals(r1.getRegionWidth(), r2.getRegionWidth()) &&
                Objects.equals(r1.getRegionHeight(), r2.getRegionHeight()) &&
                Objects.equals(r1.getTexture(), r2.getTexture());

    }
    
}
