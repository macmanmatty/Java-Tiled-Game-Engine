package com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.WorldEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPaneAction;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
public class CreateWorldOptionPane extends EditWindow {
    private PositiveIntegerField xSize;
    private PositiveIntegerField ySize;
    private com.badlogic.gdx.scenes.scene2d.ui.TextField name;
    private Button createWorld;
    private World world;
    private WorldEditScreen worldEditScreen;
    public CreateWorldOptionPane(WorldEditScreen worldEditScreen, String title, Skin skin, String style, World world) {
        super( worldEditScreen, title,  skin, style);
        this.world = world;
        this.worldEditScreen = worldEditScreen;
    }
    private   void createWorld(){
        if(world.isNewWorld()) {
            setWorld();
        }
        else{
            OptionPaneAction createMap=new OptionPaneAction() {
                @Override
                public void act() {
                    setWorld();
                }
            };
            getEditScreen().getUiStage().addActor(new OptionPane( skin, "Error!", "Creating a New World Will Overwrite the Current One. Do You Wish To Continue?", "yes", "no", createMap));
        }
    }
    private void setWorld(){
        int xMaps=xSize.getInteger();
        int yMaps=ySize.getInteger();
        if(yMaps<=0 || xMaps<=0){
            getEditScreen().getUiStage().addActor(new OptionPane(  skin, "Error!",   "Map Size Can't be Zero!!", "ok"));
        }
        else {
            MapTools.newWorld(name.getText(), xMaps, yMaps);
            worldEditScreen.createWorldMapsOverview(xSize.getInteger(), ySize.getInteger());
            addAction(Actions.removeActor());
        }
    }
    public void makeWindow() {
        xSize=new PositiveIntegerField("2", skin);
        ySize=new PositiveIntegerField("2", skin);
        name= new TextField("Enter world Name" , skin);
        Label label=new Label("Enter X World Size", skin);
       HorizontalGroup xSizeBox= new HorizontalGroup();
       Label label1=new Label("Enter Y World Size", skin);
       xSizeBox.addActor(label);
       xSizeBox.addActor(xSize);
        HorizontalGroup ySizeBox= new HorizontalGroup();
        ySizeBox.addActor(label1);
        ySizeBox.addActor(ySize);
        Label label2=new Label("Enter World Name", skin);
        HorizontalGroup nameBox= new HorizontalGroup();
        nameBox.addActor(label2);
        nameBox.addActor(name);
        createWorld= new TextButton("Create World",skin);
        createWorld.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createWorld();
            }
        });
        add(xSizeBox);
        row();
        add(ySizeBox);
        row();
        add(nameBox);
        row();
        add(createWorld);
        row();
        pack();
        validate();
        setMovable(true);
    }
}
