package com.jessematty.black.tower.GameBaseClasses.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.FileSelectPane;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.ZRPGTest.TestMap;

import java.io.File;

public class MainScreen implements Screen  {
    private Stage stage;
   private  GameAssets gameAssets;
    private Skin skin;
   private  Label errorLabel;
    public MainScreen(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    public void makeMainWindow(){
    }
    @Override
    public void show() {
        code(false);
    }
    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width, height);
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose () {
        stage.clear();
        stage.dispose();
    }
    public void code(boolean pack){
        if(pack==true) {
        }
        skin= gameAssets.getDefaultSkin();
        gameAssets.finishLoading();
       // assetts.loadTextureAtlasFromExternalFile("textureAtlases/assets.atlas" );
        gameAssets.finishLoading();
        stage= new Stage(new ScreenViewport());
        errorLabel= new Label("", skin);
        stage.addActor(new Image(new TextureRegionDrawable(new TextureRegion(new Texture("mainScreen.png")))));
        
        VerticalGroup fighterGroup= new VerticalGroup();
        Label label2= new Label("Select A Fighter To Play As", skin);
        fighterGroup.addActor(label2);
        fighterGroup.setVisible(false);
        TextButton settings= new TextButton("Pack Assetts", skin,"Brick");
        settings.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
       TextButton quit= new TextButton("Exit", skin, "Brick");
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();
                gameAssets.dispose();
                System.exit(0);
            }
        });
        TextButton about= new TextButton("About", skin, "Brick");
        about.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                    testMap();
            }
        });
        TextButton playGame= new TextButton("Play Game", skin, "Brick");
        playGame.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        FileAction loadGame= new FileAction() {
            @Override
            public void act(File file) throws Exception {
                World newWorld= gameAssets.loadGame(file.getPath());
                gameAssets.setWorld(newWorld);
                gameAssets.showGame();

            }
        };
      FileSelectPane newGame= new FileSelectPane( loadGame, skin, "Brick","Load Game" , false, 0);
        newGame.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        HorizontalGroup buttons= new HorizontalGroup();
        Label label =new Label("Black Tower" , skin) ;
        buttons.getChildren().add(playGame);
        buttons.addActor(newGame);
        buttons.getChildren().add(settings);
        buttons.getChildren().add(quit);
        buttons.getChildren().add(about);
        buttons.setPosition(150, 200);
        stage.addActor(label);
        stage.addActor(buttons);
        stage.addActor(fighterGroup);
        stage.addActor(errorLabel);
        Gdx.input.setInputProcessor(stage);
    }
    public void testMap() {
        new TestMap(gameAssets).createMapByTMXFile();
    }
}
