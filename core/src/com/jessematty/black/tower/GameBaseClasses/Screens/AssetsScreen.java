package com.jessematty.black.tower.GameBaseClasses.Screens;

import com.badlogic.ashley.core.Entity;
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
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator.LPCActorGenerator;
import com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators.LandMapSpecs;
import com.jessematty.black.tower.GameBaseClasses.Loaders.Copy.CopyObject;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

public class AssetsScreen implements Screen  {


    Stage stage;
    GameAssets assetts;
    Skin skin;
    Label errorLabel;
    World world;
    ZRPGPlayer player;






    public AssetsScreen(GameAssets assetts) {
        this.assetts = assetts;

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


        skin=assetts.getDefaultSkin();
        assetts.loadInternalTextureAtlas("assetts.atlas" );
      assetts.loadNames("/Users/jessematty/AndroidStudioProjects/Black Tower/android/assets/textureAtlases/assettsregionNames.json");

        assetts.finishLoading();

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
                assetts.dispose();

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


                assetts.showGame( player);


            }
        });






      TextButton newGame= new TextButton("Play Game", skin, "Brick");

        newGame.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);



            }
        });





        TextButton editMode= new TextButton("Edit Mode", skin, "Brick");

        editMode.addListener( new ClickListener(){
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
        buttons.getChildren().add(editMode);
        buttons.setPosition(150, 200);
        stage.addActor(label);
        stage.addActor(buttons);
        stage.addActor(fighterGroup);
        stage.addActor(errorLabel);
        Gdx.input.setInputProcessor(stage);

    }





    public void testMap(){




        assetts.finishLoading();

        LandMapSpecs specs= (LandMapSpecs) assetts.loadObject("/Users/jessematty/AndroidStudioProjects/Black Tower/android/assets/maps/mapLandSpecs1.json", LandMapSpecs.class);



        System.out.println(specs);







        Skin skin=assetts.getDefaultSkin();


        LandMap map;







        LandMapGenerator generator= new LandMapGenerator(assetts, specs);
      generator.makeTiledMap();

        map= (LandMap) generator.makeMap();
        LandMapGenerator generator2= new LandMapGenerator(assetts, specs);

        generator2.makeTiledMap();
       LandMap map2= (LandMap) generator2.makeMap();
       map.setMapName("First Map");
       map2.setMapName("second Map");



       int xSize=map.getXSize();
       int ySize=map.getYSize()-1;

        World world= new World(1, 1);
        world.placeMap(map2, 0, 0);
        world.setLoadPath("/world/");




        LPCActorGenerator lpcActorGenerator= new LPCActorGenerator( world, assetts);
       Entity entity=lpcActorGenerator.generateLPCCharacter( "assetts.atlas", "lizardMale", "lizard", "lizard", .67f,new NamedColor(0, 1, .1f, 1),100, 100,32,64,100, 100,100,100,100,100,100,100,true,true);

       assetts.setWorld(world);

        Entity entity2=new CopyObject(assetts).copyObject(entity, Entity.class);


        ZRPGPlayer player= new ZRPGPlayer(world, entity2);
       entity2.add(player);
        Position position =entity2.getComponent(Position.class);
        position.setScreenLocationX(66);
        position.setScreenLocationY(200);
        position.setMapWorldLocationX(map2.getWorldX());
        position.setMapWorldLocationY(map2.getWorldY());

        position.setScreenLocationX(66);
        position.setScreenLocationY(500);
        position.setBounds(32, 48);
        position.setBoundsXOffset(16);
        position.setHeight(10);









        Entity entity1= lpcActorGenerator.generateObject("assetts.atlas", "tree114");

        position =entity1.getComponent(Position.class);
        position.setScreenLocationX(66);
        position.setScreenLocationY(50);
        position.setBounds(100, 100);
       position.setHeight(10);


        position.setScreenLocationX(66);
        position.setScreenLocationY(50);

        position.setMapWorldLocationX(map2.getWorldX());
        position.setMapWorldLocationY(map2.getWorldY());




//        Entity hood=lpcActorGenerator.generateArmor("assetts.atlas", "hoodClothMale", "name", "armor", true,  true, true, new Color(1,1,1,1), 1, 100,100,100,100,100,100,100,new NumericStatsChangable(), new BooleanStatsChangable());

        //map2.addEntity(hood);


       // Entity sword=lpcActorGenerator.generateMeeleWeapon("assetts.atlas", "hoodClothMale", "name", "armor", true,  true, true, new Color(1,1,1,1), 1, 100,100,100,100,100,100,100,new NumericStatsChangable(), new BooleanStatsChangable());
        entity.getComponent(Holder.class);


        assetts.showGame(player);
        assetts.getMapDraw().setDrawEntityDebugBounds(true);


        Entity wings=lpcActorGenerator.makeWings("assetts.atlas" , "wingsLizardMale", entity, "wings", entity.getComponent(Body.class), new NamedColor(.1f, 1, 0, 1),  1.1f, 4, 12, 12);

        world.addEntity(entity2);
        world.addEntity(wings);
       // world.addEntity(entity1);


















    }


}
