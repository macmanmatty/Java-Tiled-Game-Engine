package com.jessematty.black.tower.GameBaseClasses.UIClasses.Skins;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class NamedSkin extends Skin {
    private String name;
    private String atlasName;

    public NamedSkin() {
    }

    public NamedSkin(FileHandle skinFile) {
        super(skinFile);
        this.name=skinFile.name();
    }

    public NamedSkin(FileHandle skinFile, TextureAtlas atlas) {
        super(skinFile, atlas);
        this.name=skinFile.name();

    }

    public NamedSkin(TextureAtlas atlas) {
        super(atlas);
    }

    public NamedSkin(String name) {
        this.name = name;
    }

    public NamedSkin(FileHandle skinFile, String name) {
        super(skinFile);
        this.name = name;
    }

    public NamedSkin(FileHandle skinFile, TextureAtlas atlas, String name) {
        super(skinFile, atlas);
        this.name = name;
    }

    public NamedSkin(TextureAtlas atlas, String name) {
        super(atlas);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
