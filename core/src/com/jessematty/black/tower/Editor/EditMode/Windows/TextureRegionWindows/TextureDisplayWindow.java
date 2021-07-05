package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.FileSelectPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.SelectBox.ObservableSelectBox;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Editor.EditMode.Brushes.CellSettable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Textures.TextureImage;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;

import java.io.File;
import java.io.IOException;

public class TextureDisplayWindow extends MapEditWindow implements com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureRegionSettable {
    private TextureImage currentTexture;
    private int regionHeight = 32;
    private int regionWidth = 32;
    private float minSplitHeight = 8f;
    private float minSplitWidth = 8f;
    private Array<CellSettable> cellSettables = new Array<>();
    private Array<TextureRegionSettable> textureRegionSettables = new Array<>();
    private TextureRegion[][] selectedTextures;
    private TextureRegion[][] textureRegions;
    private boolean[][] selectMap;
    private float startX;
    private float startY;
    private int textureWidth;
    private int textureHeight;
    private final ClipBoard clipBoard;
    private TextureRegion emptyRegion;
    private NamedField enterTileHeight;
    private NamedField enterTileWidth;
    private ObservableSelectBox<TextureImage> textures;
    private Table imageTable = new Table();
    private ScrollPane imagePane;
    private Array<TextureImage> textureArray = new Array<>();
    private float imageTableWidth = 320;
    private float imageTableHeight = 320;
    private DragAndDrop dragAndDrop;
    private TextureAtlas textureAtlas;

    public TextureDisplayWindow(com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen,  Skin skin) {
        this(mapEditScreen, "Textures", skin, "default");

    }

    public TextureDisplayWindow(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
        clipBoard = mapEditScreen.getClipBoard();
        textures = new ObservableSelectBox<TextureImage>(getSkin(), textureArray);
        textures.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TextureImage textureImage=textures.getSelected();

                setCurrentTexture(textureImage);
                setTileSize(textureImage.getRegionWidth(), textureImage.getRegionHeight());
            }
        });
        dragAndDrop = mapEditScreen.getDragAndDrop();
    }

    private void selectImages(Rectangle selectedArea) {
        int x = (int) (selectedArea.x / regionWidth);
        int y = (int) (selectedArea.y / regionHeight);
        int xMax = (int) (selectedArea.width / regionWidth) + 1;
        int yMax = (int) (selectedArea.height / regionHeight) + 1;
        if (xMax > textureWidth) {
            xMax = textureWidth;
        }
        if (yMax > textureHeight) {
            yMax = textureHeight;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        selectedTextures = new TextureRegion[xMax][yMax];
        for (int countx = x; countx < xMax; countx++) {
            for (int county = y; county < yMax; county++) {
                selectMap[countx][county] = true;
            }
        }
        setSelectedCells();
    }

    private void setSelectedCells() {
        int maxX = 0;
        int maxY = 0;
        int xMax = (int) (textureWidth / regionWidth) + 1;
        int yMax = (int) (textureHeight / regionHeight) + 1;
        if (selectMap == null) {
            return;
        }
        if (yMax < 0) {
            yMax = 1;
        }
        if (xMax < 0) {
            xMax = 1;
        }
        for (int countx = 0; countx < xMax; countx++) {
            for (int county = 0; county < yMax; county++) {
                if (selectMap[countx][county] == true) {
                    maxX = countx;
                    maxY = county;
                }
            }
        }
        int minX = maxX;
        int minY = maxY;
        for (int countx = maxX; countx <= 0; countx--) {
            for (int county = maxY; county <= 0; county--) {
                if (selectMap[countx][county] == true) {
                    minX = countx;
                    minY = county;
                }
            }
        }
        selectedTextures = new TextureRegion[maxX - minX][maxY - minY];
        for (int countx = minX; countx <= maxX; countx++) {
            for (int county = minY; county <= maxY; county++) {
                TextureRegion region = textureRegions[countx][county];
                if (selectMap[countx][county] == true) {
                    selectedTextures[countx - minX][county - minY] = region;
                } else {
                    selectedTextures[countx - minX][county - minY] = emptyRegion;
                }
            }
        }
    }

    public TextureImage getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(String path) {
        Texture texture = new Texture(Gdx.files.absolute(path));
        TextureImage textureImage = new TextureImage(path, texture, regionWidth, regionHeight);
            textureArray.add(textureImage);


        setCurrentTexture(textureImage);
    }

    public void setCurrentTexture(TextureImage texture) {

        this.currentTexture = texture;
        this.textureWidth = texture.getTexture().getWidth();
        this.textureHeight = texture.getTexture().getHeight();
        makeImages();
    }

    private void makeImages() {
        imageTable.clear();
        String[] parts = currentTexture.getPath().split(FileUtilities.getFileSeparator());
        String imageName = parts[parts.length - 1];
        textureRegions = new TextureRegion(currentTexture.getTexture()).split(regionWidth, regionHeight);
        int xImages = textureRegions.length;
        int yImages = textureRegions[0].length;
        selectMap = new boolean[xImages][yImages];
        for (int countx = 0; countx < xImages; countx++) {
            for (int county = 0; county < yImages; county++) {
                float imageStartX = countx * regionWidth;
                float imageStartY = county * regionHeight;
                final TextureRegion region = textureRegions[countx][county];
                final Image image = new Image(region);
                textureRegions[countx][county] = region;
                final int finalCountx = countx;
                final int finalCounty = county;
                final String name = imageName + "x" + countx + "y" + county;
                image.addListener(new ClickListener() {



                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        image.setDebug(true);
                        KeyListener keyListener = getMapEditScreen().getGameAssets().getGameInput().getKeyListener();

                        if(button==0) {

                            AtlasNamedAtlasRegion atlasRegion = new AtlasNamedAtlasRegion(region);
                            atlasRegion.name = name;

                            clipBoard.setCurrentObject(atlasRegion);

                            if (textureAtlas != null) {
                                textureAtlas.addRegion(atlasRegion.name, atlasRegion);

                            }
                            if (keyListener.anyKeysPressed(Keys.SHIFT_LEFT, Keys.SHIFT_RIGHT)) {
                                selectMap[finalCountx][finalCounty] = !selectMap[finalCountx][finalCounty];
                                setSelectedCells();
                            }
                        }

                        if(button==1){

                            AtlasNamedAtlasRegion atlasRegion = new AtlasNamedAtlasRegion(region);
                            atlasRegion.name = name;
                            Cell cell= new Cell();
                            StaticTiledMapTile staticTiledMapTile= new StaticTiledMapTile(atlasRegion);
                            cell.setTile(staticTiledMapTile);

                            clipBoard.setCurrentObject(cell);

                            if (textureAtlas != null) {
                                textureAtlas.addRegion(atlasRegion.name, atlasRegion);

                            }
                            if (keyListener.anyKeysPressed(Keys.SHIFT_LEFT, Keys.SHIFT_RIGHT)) {
                                selectMap[finalCountx][finalCounty] = !selectMap[finalCountx][finalCounty];
                                setSelectedCells();
                            }

                        }


                        return  true;
                    }



                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        image.setColor(1, 1, 1, .5f);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        image.setColor(1, 1, 1, 1);
                    }
                });

                imageTable.add(image).size(regionWidth, regionHeight);
            }
            imageTable.row();
        }
        imageTable.invalidateHierarchy();
        imageTable.setDebug(true);
        //saveTextures();
        invalidateHierarchy();
        //clear();
        //makeWindow();
    }

    public void setTileSize(int regionWidth, int regionHeight) {
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;
        currentTexture.setRegionHeight(regionHeight);
        currentTexture.setRegionWidth(regionWidth);
        makeImages();
    }

    public void makeWindow() {
        Label selectATexture = new Label("Select a Texture", getSkin());
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.addActor(selectATexture);
        horizontalGroup.addActor(textures);
        FileSelectPane fileSelectPane = new FileSelectPane(getSkin(), "Load New Texture");
        fileSelectPane.setDisplayFilePath(false);
        fileSelectPane.setFileAction(new FileAction() {
            @Override
            public void act(File file) {
                setCurrentTexture(file.getPath());
            }
        });
        top().add(horizontalGroup);
        row();
        final PositiveIntegerField heightField = new PositiveIntegerField("", getSkin());
        heightField.forceSetPrefWidth(30);
        final PositiveIntegerField widthField = new PositiveIntegerField("", getSkin());
        widthField.forceSetPrefWidth(30);
        this.enterTileHeight = new NamedField(heightField, new Label("Width", getSkin()));
        this.enterTileWidth = new NamedField(widthField, new Label("Height", getSkin()));
        enterTileHeight.getLabel().setFontScale(.6f);
        enterTileWidth.getLabel().setFontScale(.6f);
        HorizontalGroup horizontalGroup1 = new HorizontalGroup();
        horizontalGroup1.addActor(enterTileHeight);
        horizontalGroup1.addActor(enterTileWidth);
        horizontalGroup1.space(5);
        TextButton splitTexture = new TextButton("Split Texture", getSkin());
        splitTexture.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (heightField.getInteger() < minSplitHeight || widthField.getInteger() < minSplitWidth) {
                    OptionPane pane = new OptionPane(getSkin(), " Too Small", " Split Height must be at least " + minSplitHeight + " pixles  Split Width must be at least " + minSplitWidth + "pixles", "ok");
                    getStage().addActor(pane);
                    return;
                }
                setTileSize(heightField.getInteger(), widthField.getInteger());
            }
        });
        horizontalGroup1.addActor(splitTexture);
        top().left().add(horizontalGroup1);
        row();
        imageTable.addListener(new DragScrollListener(imagePane) {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
                startX = x;
                startY = y;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                Rectangle rectangle = new Rectangle(startX, startY, x - startX, y - startY);
                //selectImages(rectangle);
            }
        });
        imagePane = new ScrollPane(imageTable);
        imagePane.setTransform(true);
        imagePane.setFadeScrollBars(false);

        row();
        add(imagePane).size(imageTableWidth, imageTableHeight);

        row();
        bottom().center().add(fileSelectPane);
        pack();
        validate();
        invalidateHierarchy();
    }

    public float getMinSplitWidth() {
        return minSplitWidth;
    }

    public void setMinSplitWidth(float minSplitWidth) {
        this.minSplitWidth = minSplitWidth;
    }

    public float getMinSplitHeight() {
        return minSplitHeight;
    }

    public void setMinSplitHeight(float minSplitHeight) {
        this.minSplitHeight = minSplitHeight;
    }

    public float getImageTableWidth() {
        return imageTableWidth;
    }

    public void setImageTableWidth(float imageTableWidth) {
        this.imageTableWidth = imageTableWidth;
    }

    public float getImageTableHeight() {
        return imageTableHeight;
    }

    public void setImageTableHeight(float imageTableHeight) {
        this.imageTableHeight = imageTableHeight;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }


    public void saveTextures() {

        int xSize = textureRegions.length;
        int ySize = textureRegions[0].length;
        NamedTextureAtlas atlas= new NamedTextureAtlas();
        atlas.setAtlasFileName("atlas1");

        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {

                atlas.addRegion("regionx"+countx+"y"+county, textureRegions[countx][county]);


            }


        }

        try {
            getGameAssets().saveTextureAtlas("/output", atlas,1024, 1024, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
