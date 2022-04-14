package com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;

import java.util.Iterator;

/**
 * class the represents a page of texture regions aka a single .png image
 */
public class TextureRegionPage  {
    /**
     * the name of the page
     */
    private String pageName;
    /**
     * the texture regions in the page
     */
    private Array<AtlasNamedAtlasRegion> pageRegions= new Array<>();
    /**
     * the name of the texture atlas
     */
    private String atlasName;
    /**
     * the  page width aka .png image width
     */
    private int pageWidth;
    /**
     * the  page height aka .png image width
     */
    private int pageHeight;
    /**
     * the  total area of the page in pixels
     */
    private long totalArea;
    /**
     * the  total available area of the page in pixels
     * available area = pageArea-the area of the regions currently on the page
     */
    private long availableArea;
    /**
     * the padding in between the images on the page
     */
    private int padding=2;
    /**
     * whether or not the page has room to accept more images
     */
    private boolean full;
    /**
     * whether or not to add more images once the page is full
     */
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
    /**
     * check to see if a given region can fit on the page
     * @param textureRegion the region to check and see if it can fit on the page
     * @return whether or not the region can fit
     */
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
    /**
     * adds a region to the page if the page is not full or you can 
     * more images to the page once it is full
     * @param region the region to add to the page
     * @return whether or not the region was added to the page
     */
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
    /**
     * finds a region in the page by name
     * @param name the region to find
     * @return the regions if it exists
     */
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

    /**
     * add  an array of  atlas named atlas regions until the page is full
     * returns the regions  that couldn't be added
     * @param regionsToAdd the regions to  be  to added
     * @return the regions that couldn't be added
     */
    public Array<AtlasNamedAtlasRegion> addAll(Array<AtlasNamedAtlasRegion> regionsToAdd){
        Iterator<AtlasNamedAtlasRegion> regionIT=regionsToAdd.iterator();
        while (regionIT.hasNext() ){
            AtlasNamedAtlasRegion region=regionIT.next();
            if(fitCheck(region)){
               boolean added =addRegion(region);
               if(!added){
                   break;
               }
            }
            else{
                break;
            }
            regionIT.remove();
        }

        return  regionsToAdd;
    }

}
