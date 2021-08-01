package com.jessematty.black.tower.Editor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.PackAssets;
import com.jessematty.black.tower.Editor.EditMode.Screens.WorldEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.FileSelectPane;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.World;
import com.kotcrab.vis.ui.VisUI;
import java.io.File;
public class EditorMainScreen implements NamedScreen {
        private GameAssets gameAssets;
        private FileSelectPane loadWorld;
        private  Button editWorld;
        private  Button settings;
        private  Button tools;
        private  Button packAssetts;
        private Stage stage;
        private  Button exit;
        private File file;
        private Skin skin;
        private Label errorLabel;
        private List<World> worlds;
        private final String name="Editor Main Screen";
        public EditorMainScreen(GameAssets  assets) {
            this.gameAssets=assets;
        }
        public void show() {
            this.skin=gameAssets.loadInternalSkin("os8ui/OS Eight", "os8ui/OS Eight");
            gameAssets.loadTextureAtlasFromExternalFile("/AssetsPacked/editorAssets.atlas");
            if(!VisUI.isLoaded()) {
                VisUI.load(skin);
            }
            gameAssets.finishLoading();
            stage=new Stage();
            Gdx.input.setInputProcessor(stage);
            FileAction fileAction = new FileAction() {
                @Override
                public void act(File file) {
                    setFile(file);
                }
            };
            loadWorld = new FileSelectPane(fileAction,  skin, "default",  "load map",   true);
            tools = new TextButton("Tools", skin);
            worlds= new List<World>(skin);
            Label title = new Label("World Editor", skin);
            title.setFontScale(3);
            exit = new TextButton("Exit", skin);
            exit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameAssets.exit();
                }
            });
            editWorld= new TextButton("Edit World", skin);
            editWorld.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameAssets.setScreen(new WorldEditScreen(skin,  gameAssets, worlds.getSelected()));
                }
            });
            packAssetts= new TextButton("Pack Assetts", skin);
            packAssetts.addListener( new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameAssets.setScreen(new PackAssets(gameAssets, skin));
                }
            });
            VerticalGroup selectWorld= new VerticalGroup();
            selectWorld.addActor(new Label("Select A World To Edit", skin));
            selectWorld.addActor(worlds);
            selectWorld.addActor(editWorld);
            stage.addActor(selectWorld);
            HorizontalGroup buttons = new HorizontalGroup();
            buttons.addActor(packAssetts);
            buttons.addActor(loadWorld);
            buttons.addActor(editWorld);
            buttons.addActor(exit);
            title.setPosition(stage.getWidth()/2, stage.getHeight()*.85f);
            stage.addActor(title);
            buttons.setPosition(stage.getWidth()/2, stage.getHeight()*.333f);
            stage.addActor(buttons);
            worlds.setPosition(400, 1200);
            stage.addActor(worlds);
        }
        @Override
        public void render (float delta) {
            Gdx.gl.glClearColor(Color.FOREST.r, Color.FOREST.g, Color.FOREST.b, Color.FOREST.a );
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
        }
        @Override
        public void dispose () {
            stage.dispose();
        }
        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width, height);
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
    public GameAssets getGameAssets() {
            return gameAssets;
        }
        public File getFile() {
            return file;
        }
        public void setFile(File file) {
            this.file = file;
        }
    @Override
    public String getName() {
        return name;
    }
}
