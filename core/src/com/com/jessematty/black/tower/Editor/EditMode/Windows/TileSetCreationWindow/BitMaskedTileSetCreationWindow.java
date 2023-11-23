package com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.TextureAtlasRegionNames;
public class BitMaskedTileSetCreationWindow extends EditWindow {
    protected SelectBox<String> textureNames; // the selected texture names to choose from and display
    protected SelectBox<TextureAtlasRegionNames> atlasNames; // select box for texture atlases used
    protected Skin skin;
    protected Button addOuterSet;
    protected Button addInnerSet;
    protected Button addDiagonalSet;
    protected Button addSingleSet;
    protected Button addZeroRegion;
    protected Button add255Region;
    protected Button newSet;
    protected String name;
    protected TextField tileSetName;
    protected  final HorizontalGroup outerGroup= new HorizontalGroup();
    protected  final HorizontalGroup innerGroup= new HorizontalGroup();
    protected  final HorizontalGroup diagonalsGroup= new HorizontalGroup();
    protected  final HorizontalGroup singleTilesGroup= new HorizontalGroup();
    protected  final HorizontalGroup zeroTilesGroup= new HorizontalGroup();
    protected  final HorizontalGroup centerTilesGroup= new HorizontalGroup();
    protected  List<TileSet> bitMaskableTileSetList;
    protected  TileSet bitmaskableTileSet;
    public BitMaskedTileSetCreationWindow(final GameAssets assets, final Skin skin) {
        super(assets, "Create Tile Set", skin, "default");
        this.skin = skin;
        textureNames = new SelectBox<String>(skin);
        this.bitmaskableTileSet = bitmaskableTileSet;
        makeUI();
    }
    public void  makeUI(){
        Label label= new Label("Drag Texture Regions to Create A  Bitmasking  Tile Set", skin);
        addOuterSet= new TextButton("Add Additional Outer Corner  Tile  Set", skin);
        addOuterSet.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               // outerGroup.addActor(new OuterCornerTileSet(bitmaskableTileSet, getMapEditScreen()).makeTable());
                return true;
                
            }
        });
        addInnerSet= new TextButton("Add Additional Inner Corner  Tile  Set", skin);
        addInnerSet.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              //  innerGroup.addActor(new InnerCornerTileSet(bitmaskableTileSet, getMapEditScreen()).makeTable());
                return true;
            }
        });
        addDiagonalSet= new TextButton("Add Additional Diagonal Corner  Tile  Set", skin);
        addDiagonalSet.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              // diagonalsGroup.addActor(new DiagonalsTileSet(bitmaskableTileSet, getMapEditScreen()).makeTable());
               return true;
            }
        });
        addSingleSet= new TextButton("Add Additional Diagonal Corner  Tile  Set", skin);
        addSingleSet.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               // singleTilesGroup.addActor(new SingleTilesTileSet(bitmaskableTileSet, getMapEditScreen()).makeTable());
                return true;
            }
        });
        addZeroRegion= new TextButton("Add Additional Diagonal Corner  Tile  Set", skin);
        addZeroRegion.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //  addRegion(0);
                return true;
            }
        });
        add255Region= new TextButton("Add Additional Diagonal Corner  Tile  Set", skin);
       add255Region.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //(255);
                return true;
            }
        });
      bitMaskableTileSetList= new List<TileSet>(skin);
      bitMaskableTileSetList.addListener(new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
              setBitmaskableTileSet(bitMaskableTileSetList.getSelected());
          }
      });
      tileSetName= new TextField("", skin);
        newSet= new TextButton("Create New BitMasked Tile Set", skin);
        newSet.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String name=tileSetName.getText();
                TileSet bitMaskableTileSet=null;
                if( name==null ||name.isEmpty()) {
                    bitMaskableTileSet = new TileSet("TileSet " + bitMaskableTileSetList.getItems().size);
                }
                else{
                    bitMaskableTileSet= new TileSet(name);
                }
                setBitmaskableTileSet(bitMaskableTileSet);
                bitMaskableTileSetList.getItems().add(bitMaskableTileSet);
                return true;
            }
        });
    }
    
    public void applyChanges(){
        
        
    }
    public void setAtlasNames(TextureAtlasRegionNames [] names){
        atlasNames.setItems(names);
    }
//    public void addRegion(int maskNumber){
//        AtlasRegion atlasRegion=assetts.getAtlasRegionByName(String.valueOf(maskNumber), "assetts.atlas");
//        Image image= new TextureRegionSettableImage(getMapEditScreen().getClipBoard(), atlasRegion);
//        image.setSize(getMapEditScreen().getTileWidth(), getMapEditScreen().getTileHeight());
//        zeroTilesGroup.addActor(new TileSetTextureRegionDraggableTarget(getMapEditScreen().getClipBoard(), 0, bitmaskableTileSet, image).getActor());
//    }
    @Override
    public void makeWindow() {
    }
    public TileSet getBitmaskableTileSet() {
        return bitmaskableTileSet;
    }
    public void setBitmaskableTileSet(TileSet bitmaskableTileSet) {
        this.bitmaskableTileSet = bitmaskableTileSet;
    }
}
