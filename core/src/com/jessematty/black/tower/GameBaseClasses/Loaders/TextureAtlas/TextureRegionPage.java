package com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;

public class TextureRegionPage  {
    private String pageName;
    private Array<AtlasNamedAtlasRegion> pageRegions= new Array<>();
    private String atlasName;
    private int pageWidth;
    private int pageHeight;
    private long totalArea;
    private long availableArea;
    private int padding=2;
    private boolean full;
    private boolean addWhenFull;
    
    public TextureRegionPage(String pageName, String atlasName, int pageWidth, int pageHeight, int padding) {
        this.atlasName = atlasName;
        this.pageName = pageName;
        this.pageWidth = (int) MathUtilities.roundPowerOfTwo(pageWidth);
        this.pageHeight = (int) MathUtilities.roundPowerOfTwo(pageHeight);
        this.totalArea=this.pageWidth*this.pageHeight;
        this.padding=padding;
        this.availableArea=totalArea;
    }
    public TextureRegionPage(String pageName, String atlasName, PageDimensions pageDimensions) {
        this.atlasName = atlasName;
        this.pageName = pageName;
        this.pageWidth =pageDimensions.width;
        this.pageHeight=pageDimensions.height;
        this.totalArea=this.pageWidth*this.pageHeight;
        this.availableArea=totalArea;
    }
   
    public boolean  fitCheck(TextureRegion textureRegion){
        int width=textureRegion.getRegionWidth()+padding;
        int height=textureRegion.getRegionHeight()+padding;
       long area=width*height;
       if(this.availableArea<area){
           this.availableArea=this.availableArea-area;
           return true;
       }
       
       full=true;
       
        return false;
       
    }
    public boolean addRegion(AtlasNamedAtlasRegion region){
        if(full==false || addWhenFull==true) {
            region.setPageName(pageName);
            region.setAtlasName(atlasName);
            pageRegions.add(region);
            return true;
        }
        
        return false;
        
    }
    public boolean isAddWhenFull() {
        return addWhenFull;
    }
    public void setAddWhenFull(boolean addWhenFull) {
        this.addWhenFull = addWhenFull;
    }

    public Array<AtlasNamedAtlasRegion> getPageRegions() {
        return pageRegions;
    }

    public int getPageWidth() {
        return pageWidth;
    }

    public int getPageHeight() {
        return pageHeight;
    }

    public int getPadding() {
        return padding;
    }

    public long getTotalArea() {
        return totalArea;
    }

    public long getAvailableArea() {
        return availableArea;
    }

    public boolean isFull() {
        return full;
    }


    public AtlasNamedAtlasRegion findRegion(String name){
        int size=pageRegions.size;
        for(int count=0; count<size; count++){
            AtlasNamedAtlasRegion atlasNamedAtlasRegion=pageRegions.get(count);
            if(atlasNamedAtlasRegion.name.equals(name)){
                return atlasNamedAtlasRegion;
            }

        }


        return null;

    }


}
