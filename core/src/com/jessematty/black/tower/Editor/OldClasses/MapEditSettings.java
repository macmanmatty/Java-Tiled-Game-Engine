package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.FileSelectPane;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



   public  class MapEditSettings implements Screen {

        private TextField name;
        private TextField atlasName;
        private TextButton packAssetts;
        private TextButton newMap;
        private TextButton editMap;
        private FileSelectPane selectAssettsInputDir;
        private FileSelectPane loadMap;

        Label assetsDirLabel;

        private JFileChooser chooser;
        private JFrame frame= new JFrame();
        ArrayList<File> filesArray = new ArrayList<File>();




        private PositiveIntegerField numberOfMaps;

        private  TextButton newGameMaps;
        private Skin skin;
        private GameAssets assetts;
        private MapEditScreen mapEditScreen;
        private VerticalGroup verticalGroup;
        Stage stage;
        World maps;
        private VerticalGroup settingsGroup;
        private HorizontalGroup packGroup;






        public MapEditSettings(final Skin skin, final GameAssets assetts) {
            this.skin = skin;

            this.assetts = assetts;
            stage=new Stage();

            verticalGroup = new VerticalGroup();
            settingsGroup= new VerticalGroup();
            atlasName= new TextField("", skin);
            packGroup=new HorizontalGroup();
            numberOfMaps= new PositiveIntegerField("", skin);

            final HorizontalGroup numberOfMapsGroup= new HorizontalGroup();
            Label numberOfMapsLabel= new Label("Number Of Land Maps", skin);
            numberOfMapsGroup.addActor(numberOfMapsLabel);
            numberOfMapsGroup.addActor(this.numberOfMaps);



            HorizontalGroup atlasNameGroup = new HorizontalGroup();
            Label label= new Label("New  Atlas Name", skin);
            atlasNameGroup.addActor(label);
            atlasNameGroup.addActor(atlasName);

            name= new TextField("",skin);
            HorizontalGroup gameNameGRoup= new HorizontalGroup();
            Label label2= new Label("Enter The Games Name", skin);
            gameNameGRoup.addActor(label2);
            gameNameGRoup.addActor(name);
            assetsDirLabel= new Label("", skin);



            editMap= new TextButton("Edit Map", skin, "Brick");
            editMap.addListener( new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    assetts.setScreen(mapEditScreen);




                }
            });





            packAssetts= new TextButton("Pack Assetts", skin, "Brick");
            packAssetts.addListener( new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    String path= selectAssettsInputDir.getFile().getAbsolutePath();
                }
            });
            loadMap= new FileSelectPane(null,  "", "Brick", true);

            loadMap.addListener( new ClickListener(){

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);


                    Thread thread= new Thread(new Runnable() {
                        @Override
                        public void run() {
                            chooser = new JFileChooser(){

                                @Override
                                public void approveSelection() {
                                    super.approveSelection();

                                    String path=getSelectedFile().getAbsolutePath();
                                    try {
                                        maps= assetts.loadGame(path);
                                    } catch (MapLoadingExeception mapLoadingExeception) {
                                        mapLoadingExeception.printStackTrace();
                                    }


                                }
                            };
                            chooser.setSize(500, 500);
                            JPanel panel= new JPanel();

                            panel.setSize(chooser.getWidth(), chooser.getHeight());
                            panel.add(chooser);
                            frame.add(panel);
                            frame.setSize(chooser.getWidth(),chooser.getHeight());

                            frame.setVisible(true);


                        }
                    });
                    SwingUtilities.invokeLater(thread);

                }
            });











            newGameMaps = new TextButton("Create New Game", skin, "Brick");
            newGameMaps.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    assetts.setScreen(mapEditScreen);





                }
            });


            selectAssettsInputDir=new FileSelectPane(skin, "", "Brick", true);
            selectAssettsInputDir.addListener( new ClickListener(){

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);


                    Thread thread= new Thread(new Runnable() {
                        @Override
                        public void run() {
                            chooser = new JFileChooser(){
                                @Override
                                public void approveSelection() {
                                    super.approveSelection();
                                    filesArray.add(getSelectedFile());


                                }
                            };
                            chooser.setSize(500, 500);
                            JPanel panel= new JPanel();

                            panel.setSize(chooser.getWidth(), chooser.getHeight());
                            panel.add(chooser);
                            frame.add(panel);
                            frame.setSize(chooser.getWidth(),chooser.getHeight());

                            frame.setVisible(true);


                        }
                    });
                    SwingUtilities.invokeLater(thread);

                }
            });

            HorizontalGroup mapButtonsGroup= new HorizontalGroup();
            mapButtonsGroup.addActor(newGameMaps);
            mapButtonsGroup.addActor(loadMap);
            mapButtonsGroup.addActor(editMap);





            packGroup.addActor(assetsDirLabel);
            packGroup.addActor(selectAssettsInputDir);
            packGroup.addActor(packAssetts);
            settingsGroup.addActor(gameNameGRoup);
            settingsGroup.addActor(atlasNameGroup);
            settingsGroup.addActor(numberOfMapsGroup);
            settingsGroup.addActor(packGroup);
            verticalGroup.addActor(mapButtonsGroup);
            verticalGroup.addActor(settingsGroup);
            verticalGroup.setPosition(400, 400);
            stage.addActor(verticalGroup);
            Gdx.input.setInputProcessor(stage);


        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {
            Color color= Color.FOREST;
            Gdx.gl.glClearColor(color.r,color.g,color.b,color.a);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
            String text="";
            if(selectAssettsInputDir.getFile()!=null){
                text=selectAssettsInputDir.getFile().getAbsolutePath();

            }

            assetsDirLabel.setText(text);

        }

        @Override
        public void resize(int width, int height) {

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
        public void dispose() {
           stage.clear();
            stage.dispose();

        }
    }


