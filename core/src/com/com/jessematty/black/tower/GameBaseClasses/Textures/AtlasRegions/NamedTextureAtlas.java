package com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.Utilities.TextureTools;


/**
 * class that has texture atlas referenced by a name
 */

public class NamedTextureAtlas extends TextureAtlas {

    private String atlasFileName="atlas";

    public NamedTextureAtlas() {
    }

    public NamedTextureAtlas(String internalPackFile) {
        super(internalPackFile);
    }

    public NamedTextureAtlas(FileHandle packFile) {
        super(packFile);
    }

    public NamedTextureAtlas(FileHandle packFile, boolean flip) {
        super(packFile, flip);
    }

    public NamedTextureAtlas(FileHandle packFile, FileHandle imagesDir) {
        super(packFile, imagesDir);
    }

    public NamedTextureAtlas(FileHandle packFile, FileHandle imagesDir, boolean flip) {
        super(packFile, imagesDir, flip);
    }

    public NamedTextureAtlas(TextureAtlasData data) {
        super(data);
    }

    /**
     * add  AtlasNamedAtlasRegion from a texture region and names with given name  if no name is
     * supplied a random one will be given
     * @param name the name of teh atlas region
     * @param atlasRegion the texture region to add
     * @return the newly created AtlasNamedAtlasRegion Instance
     */
    public AtlasNamedAtlasRegion addRegion(String name, AtlasNamedAtlasRegion atlasRegion) {
        if(name==null || name.isEmpty()) {
            name=atlasRegion.name;
            if(name==null || name.isEmpty()) {
                name = TextureTools.randomName(atlasFileName);
            }

        }
         atlasRegion.setAtlasName(atlasFileName);
        super.addRegion(name, atlasRegion);
        return  atlasRegion;
    }

    /**
     * creates a new AtlasNamedAtlasRegion from a texture region and name if no name is
     * supplied a random one will be given
     * @param name the name of teh atlas region
     * @param region the texture region to add
     * @return the newly created AtlasNamedAtlasRegion Instance
     */
    public AtlasNamedAtlasRegion addRegion(String name, TextureRegion region) {
        if(name==null || name.isEmpty()) {
            name = TextureTools.randomName(atlasFileName);
        }

       AtlasRegion atlasRegion= new AtlasRegion(region);
       atlasRegion.name=name;
        AtlasNamedAtlasRegion atlasNamedAtlasRegion= new  AtlasNamedAtlasRegion( atlasRegion);

        atlasNamedAtlasRegion.setAtlasName(atlasFileName);
        super.addRegion(name, atlasRegion);
        return  atlasNamedAtlasRegion;
    }

    public String getAtlasFileName() {
        return atlasFileName;
    }

    public void setAtlasFileName(String atlasFileName) {
        this.atlasFileName = atlasFileName;
    }

    public void addTextureRegionPage(TextureRegionPage textureRegionPage){


    }

}
