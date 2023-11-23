package com.jessematty.black.tower.Editor.EditMode.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Maps.GameMap;

public class MapEditButtons extends MapEditWindow {
    private Array<ImageButton> topButtons= new Array<>();
    private ImageButton rotateLeft;
    private ImageButton rotateRight;
    private ImageButton backToWorldEdit;
    private ImageButton dragAndSelectTiledMapCells;
    private ImageButton dragAndSelectTiles;
    private ImageButton placeEntity;
    private ImageButton magicWand;
    private ImageButton placeBuilding;
    private ImageButton placeCells;
    private ImageButton placeTiles;
    private ImageButton bitmask;
    private ImageButton erase;
    private ImageButton fill;
    private ImageButton  back;
    private final MapEditScreen mapEditScreen;
    public MapEditButtons( MapEditScreen edit, Skin skin) {
        super(edit.getGameAssets(), "Top Buttons", skin, "default");
        this.mapEditScreen =edit;
       
    }
    @Override
    public void makeWindow() {
        rotateLeft=new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("rotateLeft", "editorAssets")));
        rotateLeft.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.getClipBoard().rotateCellLeft();
                return  true;
            }
        });
        rotateRight=new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("rotateRight", "editorAssets")));
        rotateRight.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.getClipBoard().rotateCellRight();
                return  true;
            }
        });
        backToWorldEdit=new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("world", "editorAssets")));
        backToWorldEdit.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.getGameAssets().showPreviousScreen();
                return  true;
            }
        });
        dragAndSelectTiledMapCells =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("paintBucket", "editorAssets")));
        dragAndSelectTiledMapCells.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        placeEntity =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("placeEntity", "editorAssets")));
        placeEntity.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        dragAndSelectTiles =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("selectEntity", "editorAssets")));
        dragAndSelectTiles.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        magicWand =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("wand", "editorAssets")));
        magicWand.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        placeBuilding =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("building", "editorAssets")));
        placeBuilding.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        placeCells =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("placeTile", "editorAssets")));
        placeCells.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        placeTiles =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("placeLandSquareTile", "editorAssets")));
        placeTiles.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        bitmask =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("bitMask", "editorAssets")));
        bitmask.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
            }
        });
        erase =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("terrianSelect", "editorAssets")));
        erase.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
        fill =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("paintBucket", "editorAssets")));
        fill.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;
            }
        });
       back =new ImageButton(new TextureRegionDrawable( getGameAssets().getInternalAtlasRegionByName("paintBucket", "editorAssets")));
        back.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.backToWorldEdit();
                mapEditScreen.getGameAssets().setScreen("worldEdit");
                return true;
            }}
            );
        add(rotateLeft).align(Align.center).size(32, 32);
        add(rotateRight).align(Align.center).size(32, 32);
        add(placeEntity).align(Align.center).size(32, 32);
        add(dragAndSelectTiles).align(Align.center).size(32, 32);
        add(magicWand).align(Align.center).size(32, 32);
        add(placeBuilding).align(Align.center).size(32, 32);
        add(placeCells).align(Align.center).size(32, 32);
        add(placeTiles).align(Align.center).size(32, 32);
        add(bitmask).align(Align.center).size(32, 32);
        add(erase).align(Align.center).size(32, 32);
        add(fill).align(Align.center).size(32, 32);
        pack();
        validate();
        setResizable(false);
        
        
        
    }

    @Override
    public void setMap(GameMap gameMap) {

    }
}
