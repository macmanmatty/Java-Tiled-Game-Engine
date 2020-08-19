package com.jessematty.black.tower.Editor.EditMode.TopButtons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.MapTools.SelectMode;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Editor.EditMode.MapTools.PlaceMode;

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
    private final GameAssets assets;
    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;
    public MapEditButtons(MapEditScreen edit, Skin skin) {
        super(edit, "Top Buttons", skin, "default");
        this.assets=edit.getGameAssets();
        this.mapEditScreen =edit;
       
    }
    @Override
    public void makeWindow() {

        rotateLeft=new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("rotateLeft", "editorAssets")));
        rotateLeft.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.getClipBoard().rotateCellLeft();
                return  true;
            }


        });
        rotateRight=new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("rotateRight", "editorAssets")));
        rotateRight.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.getClipBoard().rotateCellRight();
                return  true;

            }
        });
        backToWorldEdit=new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("world", "editorAssets")));
        backToWorldEdit.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.getGameAssets().showPreviousScreen();
                return  true;

            }
        });
        dragAndSelectTiledMapCells =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("paintBucket", "editorAssets")));
        dragAndSelectTiledMapCells.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setSelectMode(SelectMode.SELECT);
                return  true;

            }
        });
        placeEntity =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("placeEntity", "editorAssets")));
        placeEntity.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setPlaceMode(PlaceMode.PLACE);
                return  true;

            }
        });
        dragAndSelectTiles =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("selectEntity", "editorAssets")));
        dragAndSelectTiles.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setSelectMode(SelectMode.SELECT);
                return  true;

            }
        });
        magicWand =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("wand", "editorAssets")));
        magicWand.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setSelectMode(SelectMode.WAND);
                return  true;

            }
        });
        placeBuilding =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("building", "editorAssets")));
        placeBuilding.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setPlaceMode(PlaceMode.PLACE);
                return  true;

            }
        });
        placeCells =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("placeTile", "editorAssets")));
        placeCells.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setPlaceMode(PlaceMode.PLACE);
                return  true;

            }
        });
        placeTiles =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("placeLandSquareTile", "editorAssets")));
        placeTiles.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setPlaceMode(PlaceMode.PLACE);
                return  true;

            }
        });
        bitmask =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("bitMask", "editorAssets")));
        bitmask.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;

            }
        });
        erase =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("terrianSelect", "editorAssets")));
        erase.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return  true;

            }
        });


        fill =new ImageButton(new TextureRegionDrawable( assets.getAtlasRegionByName("paintBucket", "editorAssets")));
        fill.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapEditScreen.setPlaceMode(PlaceMode.FILL_BUCKET);
                return  true;

            }
        });
        add(backToWorldEdit).align(Align.center).size(32, 32);
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
}
