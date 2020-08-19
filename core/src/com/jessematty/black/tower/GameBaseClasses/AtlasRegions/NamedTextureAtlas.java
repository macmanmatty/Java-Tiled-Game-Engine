package com.jessematty.black.tower.GameBaseClasses.AtlasRegions;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;

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
         atlasRegion.setAtlasName(name);
        super.addRegion(name, atlasRegion);
        return  atlasRegion;
    }


    public String getAtlasFileName() {
        return atlasFileName;
    }

    public void setAtlasFileName(String atlasFileName) {
        this.atlasFileName = atlasFileName;
    }
}
