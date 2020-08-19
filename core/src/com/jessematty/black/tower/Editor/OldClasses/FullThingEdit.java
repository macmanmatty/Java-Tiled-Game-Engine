package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

import java.util.ArrayList;

public class FullThingEdit implements Screen, InputProcessor{// class for display WoodWand thing edit plus bounds edit



   private  AtlasRegion textureRegion;
  private   ArrayList<Vector2> polygonBoundsPoints = new ArrayList<Vector2>();
   private  ArrayList<Vector2> polygonBoundsPointsDraw = new ArrayList<Vector2>();


   private  Skin skin;
   private GameAssets assetts;
    private  Stage stage;
    private  ShapeRenderer shapeRenderer = new ShapeRenderer();   private TextButton clearLines;
    private   TextButton setBounds;
    private SelectBox<Direction> directions;
    private SelectBox<AtlasRegion> regions;

    private  Image image;
    private float imageWidth;
    private  float imageHeight;
    private   float imagePositionX;
    private   float imagePositionY;
    private  boolean boundChangeable;

       private com.jessematty.black.tower.Editor.OldClasses.ObjectEdit edit;
    private  VerticalGroup boundsEditGroup;
    Window textureWindow;



    private HorizontalGroup buttonsGroup;

    private  ScrollPane thingEditPane;




    private  CheckBox showThingEdit;
    private   CheckBox showBoundsEdit;
    private Viewport port;









    public FullThingEdit(AtlasRegion textureRegion, Skin skin, GameAssets assets, ObjectEdit edit) {

        this.textureRegion = textureRegion;
        this.skin = skin;
        this.assetts = assets;
        this.edit=edit;


    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);


        stage= new Stage();
        textureWindow= new Window("Texture Select", skin);
        directions=new SelectBox<Direction>(skin);
        regions= new SelectBox<AtlasRegion>(skin);







        Label label= new Label("Select WoodWand Texture Region Name To Display Them", skin);






        boundsEditGroup=new VerticalGroup();
        buttonsGroup= new HorizontalGroup();


              thingEditPane =  edit.getWindow();




        showThingEdit=new CheckBox("Show Thing Fields To Edit", skin);

        showThingEdit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                   thingEditPane.setVisible(true);
                }

                else {
                    thingEditPane.setVisible(false);

                }

            }
        });


        showBoundsEdit=new CheckBox("Show Thing Fields To Edit", skin);

        showBoundsEdit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    boundsEditGroup.setVisible(true);
                }

                else {
                    boundsEditGroup.setVisible(false);

                }

            }
        });

        clearLines=new TextButton("Clear All Lines", skin);
        clearLines.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                clearLines();

            }
        });


        setBounds=new TextButton("Clear All Lines", skin);
        setBounds.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                float []  bounds=getBounds();
                if(bounds!=null){
                }



            }
        });
                image =new Image(new TextureRegionDrawable(textureRegion));
                image.setPosition(0, 0);




        boundsEditGroup.addActor(image);
        boundsEditGroup.addActor(buttonsGroup);
        textureWindow.setPosition(0, image.getHeight()+30+regions.getHeight());


        stage.addActor(thingEditPane);
        thingEditPane.setPosition(350, 0);
        thingEditPane.setSize(500, 1150);
        stage.addActor(boundsEditGroup);
        stage.addActor(textureWindow);
        image= new Image();
        image.setPosition(0, 0);
        imagePositionX=0;
        imagePositionY=0;
        regions.setPosition(10, image.getHeight()+30);


        stage.addActor(image);
        stage.addActor(regions);
        Gdx.input.setInputProcessor(stage);




    }





    @Override
    public void render(float delta) { // draws the bounds lines
        Color color= Color.GRAY;
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        System.out.println(textureRegion);
        if(textureRegion!=null){

            image=new Image(new TextureRegionDrawable(textureRegion));


        }

 drawLines();



    }

    public void drawLines(){

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        int size=polygonBoundsPoints.size();
        for(int count=0; count<size; count=count+2) {
            if(count+1>size-1) {

                break;

            }


            shapeRenderer.line(polygonBoundsPointsDraw.get(count).x, polygonBoundsPointsDraw.get(count).y, polygonBoundsPointsDraw.get(count+1).x, polygonBoundsPointsDraw.get(count+1).y);
        }

        shapeRenderer.end();


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

    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


if( screenX>= imagePositionX && screenY>= imagePositionY && screenX<=imageWidth+imagePositionX&& screenY<=imageHeight+imagePositionY) {
    polygonBoundsPointsDraw.add(new Vector2(screenX, screenY));

    polygonBoundsPoints.add(new Vector2(screenX-imagePositionX, screenY-imagePositionY));
}



        return true;



    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public void clearLines(){

        polygonBoundsPoints.clear();
    }

    public float [] getBounds(){ // returns the polygon bounds  for the objects bounds
        int size=polygonBoundsPoints.size();
        if(size%2!=0 || size<6){

            return null;

        }


        float []  polygonPoints= new float[size*2];
        int counter=0;

        for (int count=0; count<size; count++){
            polygonPoints[counter]=polygonBoundsPoints.get(count).x;
            counter++;
            polygonPoints[counter]=polygonBoundsPoints.get(count).y;
            counter++;




        }




        return  polygonPoints;


    }





}
