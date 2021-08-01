package com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureRegionPage;


// class that has texture atlas referenced by a name
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

    public AtlasNamedAtlasRegion addRegion(String name, AtlasNamedAtlasRegion atlasRegion) {
         atlasRegion.setAtlasName(atlasFileName);
        super.addRegion(name, atlasRegion);
        return  atlasRegion;
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
