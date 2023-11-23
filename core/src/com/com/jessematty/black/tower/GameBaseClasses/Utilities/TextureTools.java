package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureRegionPage;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
public class TextureTools {
    final static List<Texture> textures = Collections.synchronizedList(new ArrayList<Texture>());
    final static List<String> texturePaths = Collections.synchronizedList(new ArrayList<String>());

    private TextureTools() {
    }

    // check to see if texture atlas has a given texture region
    public static boolean textureInAtlas(TextureRegion region, TextureAtlas namedTextureAtlas) {

        return textureInArray(region, namedTextureAtlas.getRegions());

    }

    public static boolean textureInGroups(TextureRegion region, Array<TextureRegionPage> pages) {
        int size = pages.size;
        for (int count = 0; count < size; count++) {
            boolean inGroup = textureInArray(region, pages.get(count).getPageRegions());
            if (inGroup == true) {
                return true;
            }
        }


        return false;


    }

    public static String randomName(String base){
        return base+ new UID().toString();
    }

    public static  AtlasRegion[][] splitTexture(int width, int height, Texture texture, String atlasName, String textureName) {
        AtlasRegion region = new AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        AtlasRegion[][] regionSplit = (AtlasRegion[][]) region.split(width, height);
        return regionSplit;
    }


    public static boolean textureInArray(TextureRegion region, Array<? extends TextureRegion> regions) {
        int size = regions.size;
        for (int count = 0; count < size; count++) {
            if (textureRegionsEquals(region, regions.get(count))) {
                return false;
            }
        }
        return true;
    }

    /**
     * check to see if two texture regions or sub classes
     * of texture region contain the same image
     * by comparing x y , width , height and texture  of each region
     **/

    public static boolean textureRegionsEquals(TextureRegion region, TextureRegion region2) {
        int width1 = region.getRegionWidth();
        int width2 = region2.getRegionWidth();
        int height1 = region.getRegionHeight();
        int height2 = region2.getRegionHeight();
        int x1 = region.getRegionX();
        int x2 = region2.getRegionX();
        int y1 = region.getRegionY();
        int y2 = region2.getRegionY();
        Texture texture1 = region.getTexture();
        Texture texture2 = region2.getTexture();
        if (height1 != height2 || width1 != width2 || x1 != x2 || y1 != y2 || texture1 != texture2) {
            return false;
        }

        return true;

    }

    /**
     * calculates the total area for an array of texture regions
     *
     * @param regions the array of regions to calculate the area for
     * @return the total area of the regions as a double
     */
    public static  double calculateTextureRegionArea(Array<? extends TextureRegion> regions) {
        double area = 0;
        for (TextureRegion textureRegion : regions) {
            area = area + (textureRegion.getRegionWidth() * textureRegion.getRegionHeight());

        }
        return area;
    }

    /**
     * calculates the total area for an array of textures
     *
     * @param regions the array of textures to calculate the area for
     * @return the total area of the regions as a double
     */
    public static  double caclulateTextureArea(Array<Texture> regions) {
        double area = 0;
        for (Texture textureRegion : regions) {
            area = area + (textureRegion.getWidth() * textureRegion.getHeight());

        }
        return area;
    }


    public static void addTexturesASRegionsToTextureAtlas(TextureAtlas atlas, TextureAtlas atlasToAddTo) {
        Array<AtlasRegion> regions = atlas.getRegions();
        int size = regions.size;
        for (int count = 0; count < size; count++) {
            AtlasRegion atlasNamedAtlasRegion = regions.get(count);
            atlasToAddTo.addRegion(atlasNamedAtlasRegion.name, atlasNamedAtlasRegion);
        }
    }

    public static void addTexturesASRegionsToTextureAtlas(TextureAtlas atlas, String path) throws InterruptedException {
        getTextures(path);
        addTexturesASRegionsToTextureAtlas(texturePaths, textures, atlas);
    }

    /**
     * // adds an array list of textures to a libGDX TextureAtlas as texture regions
     * by creating new texture regions encompassing the whole texture
     *
     * @param paths        the paths of the textures
     * @param textures     the texture to add to the atlas
     * @param atlasToAddTo the  libGdX TextureAtlas  to add them to
     */
    private static void addTexturesASRegionsToTextureAtlas(List<String> paths, List<Texture> textures, TextureAtlas atlasToAddTo) {
        int size = textures.size();
        for (int count = 0; count < size; count++) {
            atlasToAddTo.addRegion(FilenameUtils.getName(paths.get(count)), new TextureRegion(textures.get(count), 0, 0));
        }
    }

    /**
     * gets all Image files from a directory  and turns them into libGDX Textures
     *
     * @param directory the directory to scan
     * @throws InterruptedException
     */
    public static void getTextures(final String directory) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        texturePaths.clear();
        textures.clear();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtilities.actOnFiles(directory, new
                        FileAction() {
                            @Override
                            public void act(final File file) throws Exception {
                                Gdx.app.postRunnable(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (FileUtilities.isImageFile(file)) {
                                            String path = file.getAbsolutePath();
                                            Texture texture = new Texture(new FileHandle(path));
                                            textures.add(texture);
                                            texturePaths.add(path);
                                        }
                                    }
                                });
                            }
                        });
                countDownLatch.countDown();
            }
        });
        thread.start();
        countDownLatch.await();
    }
}
