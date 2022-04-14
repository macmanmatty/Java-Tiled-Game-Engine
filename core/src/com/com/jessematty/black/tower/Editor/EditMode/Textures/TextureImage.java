package com.jessematty.black.tower.Editor.EditMode.Textures;

import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.Objects;

public class TextureImage {
    private String path;
    private Texture texture;
    private int regionHeight;
    private int regionWidth;
    private String name;



    public TextureImage(String path, Texture texture) {
        this.path = path;
        this.texture = texture;
        File file= new File(path);
        name=file.getName();
    }

    public TextureImage(String path, Texture texture, int regionWidth, int regionHeight) {
        this.path = path;
        this.texture = texture;
        this.regionHeight = regionHeight;
        this.regionWidth = regionWidth;
        File file= new File(path);
        name=file.getName();
    }

    public String getPath() {
        return path;
    }

    public Texture getTexture() {

        return texture;
    }

    public int getRegionHeight() {
        return regionHeight;
    }

    public void setRegionHeight(int regionHeight) {
        this.regionHeight = regionHeight;
    }

    public int getRegionWidth() {
        return regionWidth;
    }

    public void setRegionWidth(int regionWidth) {
        this.regionWidth = regionWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextureImage)) return false;
        TextureImage that = (TextureImage) o;
        return getTexture().equals(that.getTexture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTexture());
    }

    @Override
    public String toString() {
        return name;

    }


}
