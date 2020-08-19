package com.jessematty.black.tower.Editor.OldClasses;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

import java.util.ArrayList;
public class TextureRegionArrayModify {
    int xSize;
    int ySize;
    String name;
    TextButton addFrame;
   ArrayList<ArrayList<ImageButton>> textureButtons;
GameAssets assets;
Skin skin;
Table buttonTable;
HorizontalGroup hGroup;
    public TextureRegionArrayModify(int xSize, int ySize,  final GameAssets assetts, final Skin skin) {
        this.assets=assetts;
        this.skin=skin;
        this.xSize = xSize;
        this.ySize = ySize;
        addFrame= new TextButton("Add  Frame", skin);
        addFrame.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addFrame();
                return true;
            }
        });
        textureButtons= new ArrayList<ArrayList<ImageButton>>();
        buttonTable= new Table (skin);
        for (int countx=0; countx<xSize; countx++){
            for (int county=0; county<ySize; county++){
            }
            buttonTable.row();
        }
        hGroup.addActor(buttonTable);
        hGroup.addActor(addFrame);
    }
    public AtlasRegion[] []  getAtlasRegionArray() {
        AtlasRegion[] []  regions = new AtlasRegion [xSize][ySize];
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                regions[countx][county] = (AtlasRegion) textureButtons.get(countx).get(county).getBackground();
            }
        }
    return regions;
    }
    public AtlasRegion []   getFrames() {
        AtlasRegion[]   regions = new AtlasRegion [xSize];
                for (int county = 0; county < ySize; county++) {
                    regions[county] = (AtlasRegion) textureButtons.get(0).get(county).getBackground();
                }
        return regions;
    }
    public void setSize(int xSize, int ySize){
        this.xSize=xSize;
        this.ySize=ySize;
        textureButtons=  new ArrayList<ArrayList<ImageButton>>();
        buttonTable= new Table (skin);
        for (int countx=0; countx<xSize; countx++){
            for (int county=0; county<ySize; county++){
                ImageButton button=new ImageButton((Drawable) null);
                button.addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        ImageButton imageButton= (ImageButton) event.getTarget();
                        return true;
                    }
                });
                textureButtons.get(countx).get(county).add( button);
                buttonTable.add( button);
            }
            buttonTable.row();
        }
    }
    public void addFrame(){
       xSize++;
                ImageButton button=new ImageButton((Drawable) null);
                button.addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        ImageButton imageButton= (ImageButton) event.getTarget();
                        return true;
                    }
                });
                textureButtons.get(0).get(textureButtons.size()).add( button);
                buttonTable.add( button);
    }
    public void addFrames(int frames){
        xSize=xSize+frames;
        for(int count=0; count<frames; count++) {
            ImageButton button = new ImageButton((Drawable) null);
            button.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ImageButton imageButton = (ImageButton) event.getTarget();
                    return true;
                }
            });
            textureButtons.get(0).get(textureButtons.size()).add(button);
            buttonTable.add(button);
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HorizontalGroup getHGroup() {
        return hGroup;
    }
    public int getxSize() {
        return xSize;
    }
    public void setxSize(int xSize) {
        this.xSize = xSize;
    }
    public int getySize() {
        return ySize;
    }
    public void setySize(int ySize) {
        this.ySize = ySize;
    }
    public void setAtlasRegions(AtlasRegion[] []  regions ){
        int sizex=regions.length;
        int sizey=regions[0].length;
        for(int countx=0; countx<sizex; countx++) {
            for (int county = 0; county < sizey; county++) {
                ImageButton button = new ImageButton((new TextureRegionDrawable(regions[countx][county])));
                button.addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        ImageButton imageButton = (ImageButton) event.getTarget();
                        return true;
                    }
                });
                textureButtons.get(countx).get(county).add(button);
                buttonTable.add(button);
            }
        }
    }
    public void setAtlasRegions(AtlasRegion[]   regions ){
        int size=regions.length;
        for(int count=0; count<size; count++){
            ImageButton button = new ImageButton((new TextureRegionDrawable(regions[count])));
            button.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ImageButton imageButton = (ImageButton) event.getTarget();
                    return true;
                }
            });
            textureButtons.get(0).get(count).add(button);
            buttonTable.add(button);
        }
    }
}
