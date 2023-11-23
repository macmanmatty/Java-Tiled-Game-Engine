package com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO.ImageFormat;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO.SaveParameters;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Utilities.NameUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.TextureTools;

import java.io.IOException;
import java.util.UUID;
/** class that saves  a  dynamically created texture atlas to png images  and a .atlas file at runtime
 **/
public class TextureAtlasPacker  implements Disposable {
    /**
     * the current pixmap of the current texture page to be packed
     */
    private Pixmap currentTexturePixmap;
    /**
     * the current texture to pack
     */
    private Texture currentTexture; // the current texture
    /**
     * the libGDX Pixmap packer class for saving the image file
     */
    private PixmapPacker pixmapPacker;
    /**
     * the names uof the atlas regions being packed
     */
    private Array<String> textureRegionNames= new Array<>();
    /**
     * whether or not to dispose the texture after writing it to the pixmap
     */
    private boolean disposeTextureAfterWrite;
    public TextureAtlasPacker() {
    }
    /**
     *    packs an array of texture region pages to create a .png file and a .atlas
     * @param saveDirectory the  full path to  the directory  to save to
     * @param atlasName the name of the atlas file
     * @param textureRegionPages the array of texture region pages to pack
     * @param pageWidth the page max width
     * @param pageHeight the page max height
     * @throws IOException
     */
    public void packPages(  String saveDirectory , String atlasName,  Array<TextureRegionPage> textureRegionPages) throws IOException {
        int size=textureRegionPages.size;
        for(int count=0; count<size; count++){
            TextureRegionPage page=textureRegionPages.get(count);
            Array<AtlasNamedAtlasRegion> pageRegions=page.getPageRegions();
            createPage(pageRegions,page.getPageWidth(), page.getPageHeight(), page.getPadding() );
        }
        getAndSaveTextures(  saveDirectory, atlasName);
    }
    /**
     *    packs an texture atlas  to create a .png file and a .atlas
     * @param saveDirectory the full path  the directory  to save to
     * @param atlasName the name of the atlas file
     * @param atlas the texture atlas to pack
     * @param pageWidth the page max width
     * @param pageHeight the page max height
     * @throws IOException
     */
    public void packAtlas( String saveDirectory, String atlasName, TextureAtlas atlas, int pageWidth, int pageHeight, int padding) throws IOException {
        Array<AtlasRegion> pageRegions=atlas.getRegions();
        createPage(pageRegions,pageWidth, pageHeight, padding);
        getAndSaveTextures(saveDirectory, atlasName);
    }

    /**
     *    packs an texture atlas  to create a .png file and a .atlas
     * @param saveDirectory the full path  the directory  to save to
     * @param namedTextureAtlas the texture atlas to pack
     * @param pageWidth the page max width
     * @param pageHeight the page max height
     * @throws IOException
     */
    public void packAtlas( String saveDirectory, NamedTextureAtlas namedTextureAtlas, int pageWidth, int pageHeight, int padding) throws IOException {
        Array<AtlasRegion> pageRegions=namedTextureAtlas.getRegions();
        String atlasName=namedTextureAtlas.getAtlasFileName();
       Array<TextureRegionPage>  pages=createPages(atlasName, pageWidth, pageHeight, padding, namedTextureAtlas.getRegions());
       packPages(saveDirectory, atlasName, pages);
    }
    /**
     *    packs an array  of  texture atlases  to create a .png file and a .atlas file
     * @param saveDirectory the full path  the directory  to save to
     * @param atlasName the name of the atlas file
     * @param atlases the texture atlases to pack
     * @param pageWidth the page max width
     * @param pageHeight the page max height
     * @throws IOException
     */
    public void packAtlases(  String saveDirectory, String atlasName,  Array<TextureAtlas> atlases, int pageWidth, int pageHeight, int padding) throws IOException {
        int size=atlases.size;
        for(int count=0; count<size; count++){
            Array<AtlasRegion> pageRegions=atlases.get(count).getRegions();
            createPage(pageRegions,pageWidth, pageHeight, padding);
        }
        getAndSaveTextures(saveDirectory, atlasName);
    }
    /**
     * packs an array of atlas regions into a .atlas file and  corresponding images
     * @param atlasNamedAtlasRegions the array of atlas regions to pack
     * @param atlasName the name you wish  the atlas to have
     * @param pageWidth the  width of the pages should be a power of two
     * @param pageHeight the  height of the pages should be a power of two
     * @param padding the padding in between the images
     * @throws IOException file handling exception
     */
    public void packRegions( String atlasName, String saveDirectory, Array<AtlasNamedAtlasRegion> atlasNamedAtlasRegions,  int pageWidth, int pageHeight, int padding) throws IOException {
        ObjectMap< String, Array<AtlasNamedAtlasRegion>> pages= new ObjectMap<String, Array<AtlasNamedAtlasRegion>>();
        int size=atlasNamedAtlasRegions.size;
        Array<AtlasNamedAtlasRegion> nonDuplicateAtlasRegions= new Array<>();
        for(int count=0; count<size; count++) {
            AtlasNamedAtlasRegion region=atlasNamedAtlasRegions.get(count);
            boolean duplicateRegion= TextureTools.textureInArray(region, nonDuplicateAtlasRegions);
            if(duplicateRegion==false){
                nonDuplicateAtlasRegions.add(region);
            }
        }
          size=nonDuplicateAtlasRegions.size;
            for(int count=0; count<size; count++){
            AtlasNamedAtlasRegion atlasNamedAtlasRegion=nonDuplicateAtlasRegions.get(count);
            Array<AtlasNamedAtlasRegion> page=pages.get(atlasNamedAtlasRegion.getPageName());
            if(page==null){
                page=new  Array<AtlasNamedAtlasRegion>();
                pages.put(atlasNamedAtlasRegion.getPageName(), page);
            }
            page.add(atlasNamedAtlasRegion);
        }
        Keys<String> keys=pages.keys();
       while(keys.hasNext){
           Array<AtlasNamedAtlasRegion> regions=pages.get(keys.next());
           createPage( regions , pageWidth, pageHeight, padding);
       }
       getAndSaveTextures(saveDirectory, atlasName);
    }
    private Array <TextureRegionPage> createPages( String atlasName, int width, int height, int padding , Array<AtlasRegion> atlasNamedAtlasRegions){
        OrderedMap<String, Array<AtlasNamedAtlasRegion>> atlasNamedRegionMap= new OrderedMap<>();
        int size=atlasNamedAtlasRegions.size;
        for(AtlasRegion atlasRegion : atlasNamedAtlasRegions) {
            AtlasNamedAtlasRegion atlasNamedAtlasRegion=null;
            if (atlasRegion instanceof AtlasNamedAtlasRegion) {
               atlasNamedAtlasRegion = (AtlasNamedAtlasRegion) atlasRegion;
            }
            else{
                atlasNamedAtlasRegion= new AtlasNamedAtlasRegion(atlasRegion);
            }
            String pageName=atlasNamedAtlasRegion.getPageName();
           Array<AtlasNamedAtlasRegion> regions= atlasNamedRegionMap.get(pageName);
            if(regions==null){
                regions= new Array<>();
                regions.add(atlasNamedAtlasRegion);
                atlasNamedRegionMap.put(pageName, regions);
            }
            else{
                regions.add(atlasNamedAtlasRegion);
            }

        }

        return createPagesFromTextureRegionMap(atlasNamedRegionMap, atlasName, width, height, padding);
    }

    /**
     *  creates  the pages   to be packed with atlas region packer
     * @param regions the map of  regions to create a page from
     * @param atlasName the name of the texture atlas
     * @param width the page width
     * @param height the page height
     * @param padding the page padding in between texture regions
     *     ie the  page dimensions will larger than the total area of teh texture regions on the page
     * @return the array of created pages
     */
    private Array<TextureRegionPage> createPagesFromTextureRegionMap(OrderedMap<String, Array<AtlasNamedAtlasRegion>> regions,  String atlasName, int width , int height, int padding){
        Array<TextureRegionPage> pages = new Array<>();
        Values<Array<AtlasNamedAtlasRegion>>  values=regions.values();
        Array<AtlasNamedAtlasRegion> leftOvers= new Array<>();
        int counter=0;
        while(values.hasNext()){
            Array<AtlasNamedAtlasRegion> atlasRegions= values.next();
          leftOvers.addAll( makePages(atlasRegions, counter, atlasName, width, height, padding, false));
        }
        makePages(leftOvers, counter+1, atlasName, width, height , padding, true );


        return pages;
    }

    /**
     *   recursive method  to create  the pages   to be packed with atlas region packer
     * @param atlasRegions the regions to create a page from
     * @param counter the number of pages created already
     * @param atlasName the name of the texture atlas
     * @param width the page width
     * @param height the page height
     * @param padding the page padding in between texture regions
     * @param forceMakePage if true a page will be created even if  there is empty space
     *     ie the  page dimensions will larger than the total area of teh texture regions on the page
     * @return the left over texture regions that won't fit on a given page
     * if the total are of these regions is  greater is than page dimensions  of forceMakePage == true
     * a new page will be created  otherwise  they will be returned
     *
     */
    public  Array<AtlasNamedAtlasRegion> makePages(Array<AtlasNamedAtlasRegion> atlasRegions, int counter,  String atlasName,  int width, int height , int padding, boolean forceMakePage){
        if(atlasRegions.size==0){
            return atlasRegions;
        }
        TextureRegionPage textureRegionPage = new TextureRegionPage("page"+counter, atlasName, width, height, padding);
        atlasRegions =textureRegionPage.addAll(atlasRegions);
        if((TextureTools.calculateTextureRegionArea(atlasRegions)>width*height) || forceMakePage) {
            atlasRegions = makePages(atlasRegions, counter, atlasName, width, height, padding, forceMakePage);
        }
            return  atlasRegions;
    }

    /**
     *    creates a texture page to be later packed
     * @param atlasNamedAtlasRegions the regions to pack
     * @param pageWidth the page max width
     * @param pageHeight the page max height
     * @param padding the amount of padding between textures
     * @throws IOException
     */
    private void createPage(  Array< ? extends AtlasRegion> atlasNamedAtlasRegions, int pageWidth, int pageHeight, int padding)  {
         pixmapPacker= new PixmapPacker(pageWidth, pageHeight, Format.RGBA8888, padding, false);
         pixmapPacker.setPackToTexture(true);
         pixmapPacker.setPadding(padding);
        int size=atlasNamedAtlasRegions.size;
        for(int count=0; count<size; count++){
            AtlasRegion atlasNamedAtlasRegion=atlasNamedAtlasRegions.get(count);
            String name=atlasNamedAtlasRegion.name;
            // if a  texture region shares a name  give it a different one
            if(InList.isInList(textureRegionNames,  name)){
                 name=NameUtilities.newTextureRegionName(textureRegionNames, name);
               atlasNamedAtlasRegion.name=name;
            }
            // if texture has no name give it random one
            if(name==null || name.isEmpty()){
                name= UUID.randomUUID().toString();
                atlasNamedAtlasRegion.name=name;
            }
            textureRegionNames.add(name);
            Texture texture=atlasNamedAtlasRegion.getTexture();
            if(currentTexture!=texture){
                if(currentTexture!=null && disposeTextureAfterWrite) {
                   currentTexture.dispose();
                }
                if(currentTexturePixmap!=null){
                    currentTexturePixmap.dispose();
                }
                currentTexture=texture;
                currentTexturePixmap=getTexturePixmap(texture);
            }
            Pixmap regionPixmap=makeRegionPixmap(atlasNamedAtlasRegion);
         pixmapPacker.pack(name, regionPixmap);
        }
    }
    /**
     * creates a  Pixmap from a texture
     * @param texture the texture to get data from
     * @return the Pixmap data
     */
    private Pixmap getTexturePixmap(Texture texture){
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
       Pixmap map = texture.getTextureData().consumePixmap();
        return  map;
    }
    
    /**
     *   creates a  pixmap from a atlas region
     * @param atlasRegion the region to create teh pixmap from
     * @return the Pixmap data
     */
    private Pixmap makeRegionPixmap( AtlasRegion atlasRegion){
        int regionWidth=atlasRegion.getRegionWidth();
        int regionHeight=atlasRegion.getRegionHeight();
        // create pixmap
        Pixmap regionPixmap= new Pixmap(regionWidth, regionHeight, Format.RGBA8888);
        // copy data to pixmap
        for (int x = 0; x < regionWidth; x++) {
            for (int y = 0; y < regionHeight; y++) {
                int colorInt = currentTexturePixmap.getPixel(atlasRegion.getRegionX() + x, atlasRegion.getRegionY() + y);
            regionPixmap.drawPixel( x, y, colorInt);
            }
        }
        return  regionPixmap;
    }
    /**
     *   saves a texture atlas  to  the .png page files  and a .atlas file  containing the data and images   for the texture regions
     * @throws IOException
     */
    private void getAndSaveTextures(String atlasSaveDirectory, String atlasName) throws IOException {
 
        pixmapPacker.generateTextureAtlas(TextureFilter.Linear, TextureFilter.Linear, false);
      PixmapPackerIO pixmapPackerIO= new PixmapPackerIO();
        String fullPath= atlasSaveDirectory + FileUtilities.getFileSeparator()+ atlasName +".atlas";
        SaveParameters saveParameters= new SaveParameters();
        saveParameters.useIndexes=true;
        saveParameters.format= ImageFormat.PNG;
        pixmapPackerIO.save(Gdx.files.absolute(fullPath), pixmapPacker, saveParameters);
    }
    /**
     * writes a texture to a .png image file
     * @param texture the texture to write
     * @param path the file output path
     * @param name the name of the file
     */
    public static  void writeTextureToPng(Texture texture, String path, String name){
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap pixmap = texture.getTextureData().consumePixmap();
        writePixmapToPng(path, name, pixmap);
    }
    public static  void writePixmapToPng( String path, String name, Pixmap pixmap){
        String fullPath=path+name+".png";
        PixmapIO.writePNG(Gdx.files.external(fullPath), pixmap);
    }
    /**
     * libGDX dispose  method must be called   after saving the TexureAtlas to avoid memory leaks
     */
    @Override
    public void dispose() {
        pixmapPacker.dispose();
        currentTexture.dispose();
        currentTexturePixmap.dispose();
    }
    public boolean isDisposeTextureAfterWrite() {
        return disposeTextureAfterWrite;
    }
    public void setDisposeTextureAfterWrite(boolean disposeTextureAfterWrite) {
        this.disposeTextureAfterWrite = disposeTextureAfterWrite;
    }
}
