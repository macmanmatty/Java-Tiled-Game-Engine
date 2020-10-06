package com.jessematty.black.tower.Editor.EditMode.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.ProgressListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.FileSelectPane;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;

public class PackAssets implements NamedScreen  {
    private TexturePacker.Settings texturePackerSettings= new TexturePacker.Settings();
    private VerticalGroup verticalGroup = new VerticalGroup();
    private Stage stage= new Stage();
    private  CheckBox alais;
    private  CheckBox premultiplyAlpha;
    private  CheckBox combineSubDirectories;
    private  CheckBox duplicatePadding;
    private  CheckBox stripWhiteSpaceX;
    private  CheckBox stripWhiteSpaceY;
    private  CheckBox bleed;
    private  CheckBox debug;
    private  CheckBox fast;
    private  CheckBox edgePadding;
    private  CheckBox flattenPaths;
    private  CheckBox ignoreBlankImages;
    private  CheckBox limitMemory;
    private  CheckBox  multipleOfFour;
    private  CheckBox powerOfTwo;
    private  CheckBox rotation;
    private  CheckBox silent;
    private  CheckBox square;
    private  CheckBox useIndexes;
    private  NamedField alphaThreshHold;
   private  SelectBox<Format> formats;
    private  NamedField jpegQuality;
    private  NamedField bleedIterations;
    private  NamedField minHeight;
    private  NamedField maxHeight;
    private  NamedField minWidth;
    private  NamedField maxWidth;
    private  NamedField paddingY;
    private  NamedField paddingX;
   private Button packAssetts;
   private Button exit;
   private FileSelectPane outputPathButton;
   private FileSelectPane inputPathButton;
   private NamedField atlasName;
   private GameAssets assetts;
   private Skin skin;
   private Thread packAssettsThread;
   private Label progress;
   private Button stop;
   private ProgressBar progressBar;
   private Object lock=  new Object();
   private ProgressListener progressListener = new ProgressListener() {
       @Override
       public void progress(float progress) {

           progressBar.setValue(progress);
       }
   };

    private final String  name="Pack Assets Screen";

    public PackAssets(GameAssets assetts, Skin skin) {
        this.assetts = assetts;
        this.skin = skin;
    }
    private void packAssetts(){
        texturePackerSettings.format=formats.getSelected();

        texturePackerSettings.alphaThreshold=Integer.valueOf(alphaThreshHold.getField().getText());

        texturePackerSettings.jpegQuality=Float.valueOf(jpegQuality.getField().getText());

        texturePackerSettings.bleedIterations=Integer.valueOf(bleedIterations.getField().getText());

        texturePackerSettings.minHeight=Integer.valueOf(minHeight.getField().getText());

        texturePackerSettings.maxHeight=Integer.valueOf(maxHeight.getField().getText());

        texturePackerSettings.minWidth=Integer.valueOf(minWidth.getField().getText());

        texturePackerSettings.maxWidth=Integer.valueOf(maxWidth.getField().getText());

        texturePackerSettings.paddingY=Integer.valueOf(paddingY.getField().getText());

        texturePackerSettings.paddingX=Integer.valueOf(paddingX.getField().getText());


        if(inputPathButton.getFile()==null || outputPathButton.getFile()==null){

           OptionPane optionPane= new OptionPane(skin, "Check Paths", " You Must Enter a Input Directory and Output Directory  to pack", "ok");

           stage.addActor(optionPane);

            return;

        }
        if(atlasName.getField().getText().isEmpty() ){



            OptionPane optionPane= new OptionPane(skin, "Check Paths", "  Atlas Name Must Not Be Empty" , "ok");

            stage.addActor(optionPane);

            return;

        }
        progress.setText("Packing Assetts!");

        Runnable runnable= new Runnable() {
            @Override
            public void run() {


                synchronized (lock) {

                    String packFilePath=inputPathButton.getFile().getAbsolutePath()+ FileUtilities.getFileSeparator() +"pack.json";
                    assetts.getJsonLoader().writeObjectToFile(texturePackerSettings, packFilePath, false);
                    TexturePacker.process(inputPathButton.getFile().getAbsolutePath(), outputPathButton.getFile().getAbsolutePath(), atlasName.getField().getText());
                }
            }

            };


        packAssettsThread= new Thread(runnable);
        packAssettsThread.start();

    }

    public void stop(){
        progress.setText("Packing Stopped!");

        packAssettsThread.stop();
    }



    @Override
    public void show() {

        Label label= new Label("Assett Packer", skin);
        label.setFontScale(2);
        verticalGroup.addActor(label);
        atlasName= new NamedField("Enter Atlas Name", skin, new TextField("", skin));
        exit= new TextButton("Exit", skin);
        exit.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                assetts.setScreen("Editor Main Screen");
            }
        });

        outputPathButton= new FileSelectPane(skin, "Select Assetts Output Directory", true);
        inputPathButton= new FileSelectPane(skin, "Select Assetts Input Directory ", true);
        verticalGroup.addActor(inputPathButton);
        verticalGroup.addActor(outputPathButton);
        verticalGroup.addActor(atlasName);
        packAssetts= new TextButton("Pack Assetts!", skin);
        packAssetts.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                packAssetts();
            }
        });


      stop= new TextButton("Stop Packing", skin);
        stop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stop();
            }
        });
         alais = new CheckBox("Use Alises For Duplicate Textures", skin);
         alais.setChecked(texturePackerSettings.alias);
         alais.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 texturePackerSettings.alias=alais.isChecked();
             }
         });
         verticalGroup.addActor(alais);
         premultiplyAlpha= new CheckBox("PreMultiply Alpha Channel", skin);
        premultiplyAlpha.setChecked(texturePackerSettings.alias);
        premultiplyAlpha.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.premultiplyAlpha=premultiplyAlpha.isChecked();
            }
        });
        verticalGroup.addActor(premultiplyAlpha);
        combineSubDirectories= new CheckBox("Combine Sub Directories", skin);
        combineSubDirectories.setChecked(texturePackerSettings.combineSubdirectories);
        combineSubDirectories.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.combineSubdirectories=combineSubDirectories.isChecked();
                System.out.println("Combine subdirectories " +texturePackerSettings.combineSubdirectories);
            }
        });  
        
        verticalGroup.addActor(combineSubDirectories);
        
        duplicatePadding= new CheckBox("Duplicate Padding", skin);
        duplicatePadding.setChecked(texturePackerSettings.duplicatePadding);
        duplicatePadding.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.duplicatePadding=duplicatePadding.isChecked();
            }
        });
        verticalGroup.addActor(duplicatePadding);         
         
         stripWhiteSpaceX= new CheckBox("Strip X White Space", skin);
        stripWhiteSpaceX.setChecked(texturePackerSettings.stripWhitespaceX);
        stripWhiteSpaceX.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.stripWhitespaceX=stripWhiteSpaceX.isChecked();
            }
        });
        verticalGroup.addActor(stripWhiteSpaceX);         
        stripWhiteSpaceY= new CheckBox("Strip Y White Space",  skin);
        stripWhiteSpaceY.setChecked(texturePackerSettings.stripWhitespaceY);
        stripWhiteSpaceY.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.stripWhitespaceY=stripWhiteSpaceY.isChecked();
            }
        });
        verticalGroup.addActor(stripWhiteSpaceY);  
        debug= new CheckBox("Debug", skin);
        debug.setChecked(texturePackerSettings.debug);
        debug.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.debug=debug.isChecked();
            }
        });
        verticalGroup.addActor(debug);
      
        bleed= new CheckBox("Bleed",  skin);
        bleed.setChecked(texturePackerSettings.bleed);
        bleed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.bleed=bleed.isChecked();
            }
        });
        verticalGroup.addActor(bleed);
       
        fast= new CheckBox("Use Fast Packing Algorithim" , skin);
        fast.setChecked(texturePackerSettings.fast);
        fast.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.fast=fast.isChecked();
            }
        });
        verticalGroup.addActor(fast);
        
         edgePadding= new CheckBox("Padd edges",  skin);
        edgePadding.setChecked(texturePackerSettings.edgePadding);
        edgePadding.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.edgePadding=edgePadding.isChecked();
            }
        });
        verticalGroup.addActor(edgePadding);
         
         flattenPaths= new CheckBox("Flatten Paths" , skin);
        flattenPaths.setChecked(texturePackerSettings.flattenPaths);
        flattenPaths.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.flattenPaths=flattenPaths.isChecked();
            }
        });
        verticalGroup.addActor(flattenPaths);
         
      
         
         ignoreBlankImages= new CheckBox("Ignore Blank Images" , skin);
        ignoreBlankImages.setChecked(texturePackerSettings.ignoreBlankImages);
        ignoreBlankImages.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.ignoreBlankImages=ignoreBlankImages.isChecked();
            }
        });
        verticalGroup.addActor(ignoreBlankImages);
         
         limitMemory= new CheckBox("Limit Memory Usage", skin);
        limitMemory.setChecked(texturePackerSettings.limitMemory);
        limitMemory.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.limitMemory=limitMemory.isChecked();
            }
        });
        verticalGroup.addActor(limitMemory);
         
          multipleOfFour= new CheckBox("Mulitple of Four Textures" , skin);
        multipleOfFour.setChecked(texturePackerSettings.multipleOfFour);
        multipleOfFour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.multipleOfFour=multipleOfFour.isChecked();
            }
        });
        verticalGroup.addActor(multipleOfFour);
          
         powerOfTwo= new CheckBox("Makes Textures Power Of Two" , skin);
        powerOfTwo.setChecked(texturePackerSettings.pot);
        powerOfTwo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.pot=powerOfTwo.isChecked();
            }
        });
        verticalGroup.addActor(powerOfTwo);
         
         rotation= new CheckBox("Allow texture Rotation", skin);
        rotation.setChecked(texturePackerSettings.rotation);
        rotation.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.rotation=rotation.isChecked();
            }
        });
        verticalGroup.addActor(rotation);
         
         silent= new CheckBox("slient" , skin);
        silent.setChecked(texturePackerSettings.silent);
        silent.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.silent=silent.isChecked();
            }
        });
        verticalGroup.addActor(silent);
         
         square= new CheckBox("Square", skin);
        square.setChecked(texturePackerSettings.square);
        square.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.square=square.isChecked();
            }
        });
        verticalGroup.addActor(square);
         

         
         useIndexes= new CheckBox("Use Indexes" , skin);
         
        useIndexes.setChecked(texturePackerSettings.useIndexes);
        useIndexes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                texturePackerSettings.useIndexes=useIndexes.isChecked();
            }
        });
        verticalGroup.addActor(useIndexes);
        
        Format []  formatsArray=Format.values();
         formats= new SelectBox<Format>(skin);
         formats.setItems(formatsArray);
         formats.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 
                 texturePackerSettings.format=formats.getSelected();
             }
         });
         formats.setSelected(texturePackerSettings.format);
         verticalGroup.addActor(formats);
         
         alphaThreshHold= new NamedField("Enter Alpha Threshold", skin, new PositiveFloatField("", skin));
         alphaThreshHold.getField().setText(""+texturePackerSettings.alphaThreshold);
         alphaThreshHold.addListener(new InputListener(){
             @Override
             public boolean keyUp(InputEvent event, int keycode) {
                 
                 if(keycode== Keys.ENTER){
                     
                     texturePackerSettings.alphaThreshold=Integer.valueOf(alphaThreshHold.getField().getText());
                 }
                 
                 return true;
             }
         });
         verticalGroup.addActor(alphaThreshHold);
         jpegQuality= new NamedField("Enter JPEG Quality", skin, new PositiveFloatField("", skin));
        jpegQuality.getField().setText(""+texturePackerSettings.jpegQuality);
        jpegQuality.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.jpegQuality=Float.valueOf(jpegQuality.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(jpegQuality);
         bleedIterations= new NamedField("Enter Bleed Iterations", skin, new PositiveFloatField("", skin));
        bleedIterations.getField().setText(""+texturePackerSettings.bleedIterations);
        bleedIterations.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.bleedIterations=Integer.valueOf(bleedIterations.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(bleedIterations);
         minHeight= new NamedField("Enter Min Height", skin, new PositiveFloatField("", skin));
        minHeight.getField().setText(""+texturePackerSettings.minHeight);
        minHeight.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.minHeight=Integer.valueOf(minHeight.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(minHeight);
         maxHeight= new NamedField("Enter Max Height", skin, new PositiveFloatField("", skin));
        maxHeight.getField().setText(""+texturePackerSettings.maxHeight);
        maxHeight.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.maxHeight=Integer.valueOf(maxHeight.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(maxHeight);
         minWidth= new NamedField("Enter Min Width", skin, new PositiveFloatField("", skin));
        minWidth= new NamedField("Enter JPEG Quality", skin, new PositiveFloatField("", skin));
        minWidth.getField().setText(""+texturePackerSettings.minWidth);
        minWidth.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.minWidth=Integer.valueOf(minWidth.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(minWidth);
         maxWidth= new NamedField("Enter Max Width", skin, new PositiveFloatField("", skin));
        maxWidth.getField().setText(""+texturePackerSettings.maxWidth);
        maxWidth.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.maxWidth=Integer.valueOf(maxWidth.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(maxWidth);
         paddingY= new NamedField("Enter Padding Y", skin, new PositiveFloatField("", skin));
        paddingY.getField().setText(""+texturePackerSettings.paddingY);
        paddingY.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.paddingY=Integer.valueOf(paddingY.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(paddingY);
         paddingX= new NamedField("tEnter Padding X", skin, new PositiveFloatField("", skin));
        paddingX.getField().setText(""+texturePackerSettings.paddingX);
        paddingX.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    texturePackerSettings.paddingX=Integer.valueOf(paddingX.getField().getText());
                }
                return true;
            }
        });
        verticalGroup.addActor(paddingX);
        HorizontalGroup buttons= new HorizontalGroup();
        buttons.addActor(packAssetts);
        buttons.addActor(stop);
        buttons.addActor(exit);
        verticalGroup.addActor(buttons);
        verticalGroup.space(4.5f);
        verticalGroup.setPosition(stage.getWidth()/2, stage.getHeight());
        Gdx.input.setInputProcessor(stage);
        progressBar= new ProgressBar(0, 100, 1, false, skin);
        progress= new Label("", skin);
        verticalGroup.addActor(progress);
       verticalGroup.addActor(progressBar);

        stage.addActor(verticalGroup);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.FOREST.r, Color.FOREST.g, Color.FOREST.b, Color.FOREST.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

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
    @Override
    public void dispose() {
        stage.dispose();
    }

    public String getName() {
        return name;
    }
}
