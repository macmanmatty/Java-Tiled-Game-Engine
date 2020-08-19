package com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas;

public enum PageDimensions {
    D1024x1024(1024, 1024), D2048x1024(2048, 1024),
    D2048x2048(2048, 2048), D4096x2048(4096, 2048),
    D4096x4096(4096, 4096),  D8192x4096(8192, 4096),
    D8192x8192(8192, 8192),


    ;


    int width;
    int height;


    PageDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
