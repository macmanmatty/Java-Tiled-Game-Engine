package com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.PixmapPacker.Page;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO.ImageFormat;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO.SaveParameters;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Utilities.NameUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.TextureTools;

import java.io.IOException;
import java.util.UUID;

// class that saves  a  dynamiclly created texture atlas to png images  and a .atlas file at runtime;


public class TextureAtlasPacker  implements Disposable {
    private Pixmap currentTexturePixmap; // the current pixMapTo Be PACKED
    private Texture currentTexture; // the current texture
    private NamedTextureAtlas textureAtlas= new NamedTextureAtlas(); // the atlas to pack to
    private String atlasSaveName; // the name of the atlas
    private PixmapPacker pixmapPacker; // the libgdx Pixmap packer for saving the image file
    private Array<Page> pages=new Array<>();
    private String atlasSaveDirectory;
    private Array<String> textureRegionNames= new Array<>();

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
    public void packPages(  String saveDirectory , String atlasName,  Array<TextureRegionPage> textureRegionPages, int pageWidth, int pageHeight) throws IOException {
        textureAtlas.setAtlasFileName(atlasName);
        this.atlasSaveDirectory =saveDirectory;
        this.atlasSaveName =atlasName;
        this.pages=new Array<>();
        textureAtlas= new NamedTextureAtlas(atlasName);

        int size=textureRegionPages.size;

        for(int count=0; count<size; count++){
            TextureRegionPage page=textureRegionPages.get(count);
            Array<AtlasNamedAtlasRegion> pageRegions=page.getPageRegions();
            createPage(pageRegions,page.getPageWidth(), page.getPageHeight(), page.getPadding() );
        }
        getAndSaveTextureAtlas(pageWidth, pageHeight);
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
        this.atlasSaveDirectory =saveDirectory;
        pages=new Array<>();
        textureAtlas= new NamedTextureAtlas();
        textureAtlas.setAtlasFileName(atlasName);
        this.atlasSaveName =atlasName;
        Array<TextureAtlas> atlases= new Array<>();
        atlases.add(atlas);
        packAtlases(saveDirectory, atlasName,atlases, pageWidth, pageHeight, padding);
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
        this.atlasSaveDirectory =saveDirectory;
        textureAtlas.setAtlasFileName(atlasName);
        pages=new Array<>();
        textureAtlas= new NamedTextureAtlas();
        textureAtlas.setAtlasFileName(atlasName);
        int size=atlases.size;
        for(int count=0; count<size; count++){
            Array<AtlasRegion> pageRegions=atlases.get(count).getRegions();

            createPage(pageRegions,pageWidth, pageHeight, padding);
        }

        getAndSaveTextureAtlas(pageWidth, pageHeight);
    }


    public void packRegions(Array<AtlasNamedAtlasRegion> atlasNamedAtlasRegions, String atlasName, int pageWidth, int pageHeight, int padding) throws IOException {
        pages=new Array<>();
        textureAtlas= new NamedTextureAtlas();
        textureAtlas.setAtlasFileName(atlasName);
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
       getAndSaveTextureAtlas(pageWidth, pageHeight);
    }
    // creates a pixmap page  aka image  of texture regions


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
         pixmapPacker.setPadding(2);
        int size=atlasNamedAtlasRegions.size;

        for(int count=0; count<size; count++){
            AtlasRegion atlasNamedAtlasRegion=atlasNamedAtlasRegions.get(count);
            String name=atlasNamedAtlasRegion.name;
            // if a  texture shares a name  give it a different one
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
                if(currentTexture!=null) {
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
    private Pixmap getTexturePixmap(Texture texture){
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
       Pixmap map = texture.getTextureData().consumePixmap();
        return  map;
    }


    /**
     *   creates a  pixmap from a atlas region
     * @param atlasRegion
     * @return
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
     * @param width the texture width
     * @param height the texture height
     * @throws IOException
     */
    private void getAndSaveTextureAtlas( int width, int height) throws IOException {
 
        pixmapPacker.generateTextureAtlas(TextureFilter.Linear, TextureFilter.Linear, false);

      PixmapPackerIO pixmapPackerIO= new PixmapPackerIO();

        String fullPath= atlasSaveDirectory + FileUtilities.getFileSeparator()+ atlasSaveName +".atlas";

        System.out.println(atlasSaveDirectory);
        SaveParameters saveParameters= new SaveParameters();
        saveParameters.useIndexes=true;
        saveParameters.format= ImageFormat.PNG;

        pixmapPackerIO.save(Gdx.files.absolute(fullPath), pixmapPacker, saveParameters);

    }
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

    @Override
    public void dispose() {
        textureAtlas.dispose();
        pixmapPacker.dispose();
        currentTexture.dispose();
        currentTexturePixmap.dispose();
    }
}
