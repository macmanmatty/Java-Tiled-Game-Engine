package com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.ActionTextField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PointsField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.TextFieldOnEnterAction;
public class AnimationEditor extends Table {
    private AnimationPreview animationPreview= new AnimationPreview();
    private DragAndDrop dragAndDrop;
    private Array<AnimationFrame> animationFrames= new Array<>();
    private Slider slider;
    private boolean remakeTable;
    private int numberOfFrames;
    private ScrollPane scrollPane;
    private Button addFrame;
    private int currentNumberOfFrames;
    private SelectBox<NamedColor> colorSelectBox;
    private com.jessematty.black.tower.Components.Animation.Animation animation= new com.jessematty.black.tower.Components.Animation.Animation();
    private AtlasAnimatedTiledMapTile animatedTiledMapTile;
    private ActionTextField actionTextField;
    private PointsField pointsField;
    private TextureRegionPage regionGroup;
    private Direction direction=Direction.SAME;
    private ClipBoard clipBoard;



    public AnimationEditor(Skin skin , ClipBoard clipboard,  DragAndDrop dragAndDrop, Direction direction) {
        super(skin);
        this.clipBoard=clipboard;
        this.dragAndDrop=dragAndDrop;
        this.direction=direction;
        slider= new Slider(1, 100, 1, false, skin);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                animationPreview.setFrameRate((int) slider.getValue());
            }
        });
        addFrame= new TextButton("Add Frame", skin);
        addFrame.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addNewFrame();
                return  true;
            }
        });
        colorSelectBox= new SelectBox<NamedColor>(skin);
        colorSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                animation.setColor(colorSelectBox.getSelected());
            }
        });
        actionTextField= new ActionTextField("", skin);
        actionTextField.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
            @Override
            public void action() {
                animation.setAction(actionTextField.getText());
            }
        });
        pointsField= new PointsField(skin, true);
        final FloatField textFieldX=pointsField.getxField();
        textFieldX.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
            @Override
            public void action() {
                animation.setXOffset(textFieldX.getFloat());
            }
        });
        final FloatField textFieldY=pointsField.getxField();
        textFieldY.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
            @Override
            public void action() {
                animation.setYOffset(textFieldY.getFloat());
            }
        });
        final FloatField textFieldZ=pointsField.getxField();
        textFieldZ.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
            @Override
            public void action() {
                animation.setSubLayerNumber(textFieldZ.getInteger());
            }
        });
        makeTable();
        addNewFrame();
    }
    @Override
    public void act(float delta) {
        if(remakeTable==true || numberOfFrames!=animationFrames.size) {
            numberOfFrames = animationFrames.size;
            remakeTable=false;
            makeTable();
            
        }
        
        super.act(delta);
        
        
    }
    private void makeTable(){
        clear();
        int size=animationFrames.size;
        Table animationTable= new Table();
        if(size!=currentNumberOfFrames) {
            currentNumberOfFrames=size;
            for (int count = 0; count < size; count++) {
                AnimationFrame animationFrame = animationFrames.get(count);
                dragAndDrop.addSource(new AnimationSource(this, animationFrame));
                dragAndDrop.addTarget(new AnimationTarget(this, animationFrame));
                animationTable.add(animationFrame);
            }
            scrollPane = new ScrollPane(animationTable);
           top().add(scrollPane);
            add();
            animationPreview.setFrames(animationFrames);
           top().add(animationPreview);
            row();
            Label label= new Label("Animation FPS", getSkin());
            add(label);
            row();
            add(slider);
            add(addFrame);
            row();
            add(pointsField);
        }
    }
    public Array<AnimationFrame> getAnimationFrames() {
        return animationFrames;
    }
    public void addAnimationFrames(AnimationFrame... frames) {
        this.animationFrames.addAll(frames);
    }
    public void addNewFrame(){
        int frameNumber=animationFrames.size-1;
         AnimationFrame animationFrame =new AnimationFrame( this, frameNumber);
        if(animationFrames.size>0){
            AnimationFrame previousFrame=animationFrames.get(animationFrames.size-1);
            float width=previousFrame.getWidth();
            float height=previousFrame.getHeight();
            animationFrame.setSize(width, height);
        }
        animationFrames.add(animationFrame);
    }
    public void setAnimation(com.jessematty.black.tower.Components.Animation.Animation animation){
        this.animation=animation;
        AtlasNamedAtlasRegion [] frames=animation.getFrames();
        int size=frames.length;
        animationFrames.clear();
        for(int count=0; count<size; count++){
            animationFrames.add(new AnimationFrame(this, frames[count], count));
        }
        actionTextField.setText(animation.getAction());
        Vector2 vector2=animation.getOffsets();
        pointsField.setValues(vector2.x, vector2.y, animation.getSubLayerNumber());
    }
    public void setAnimation(AtlasAnimatedTiledMapTile animatedTiledMapTile){
        this.animatedTiledMapTile=animatedTiledMapTile;
        AtlasStaticTiledMapTile []  tiles=animatedTiledMapTile.getFrameTiles();
        int size=tiles.length;
        animationFrames.clear();
        for(int count=0; count<size; count++){
            TextureRegion region=tiles[count].getTextureRegion();
            if(region!=null) {
                animationFrames.add(new AnimationFrame(this, region, count));
            }
            
        }
        actionTextField.setText(animation.getAction());
        Vector2 vector2=animation.getOffsets();
        pointsField.setValues(vector2.x, vector2.y, animation.getSubLayerNumber());
    }
    public Animation getAnimation() {
        return animation;
    }
    public void forceRemakeTable() {
        remakeTable=true;
    }
    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }
    public void setDragAndDrop(DragAndDrop dragAndDrop) {
        this.dragAndDrop = dragAndDrop;
    }
    public SelectBox<NamedColor> getColorSelectBox() {
        return colorSelectBox;
    }
    public TextureRegionPage getRegionGroup() {
        return regionGroup;
    }
    public void setRegionGroup(TextureRegionPage regionGroup) {
        this.regionGroup = regionGroup;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ClipBoard getClipBoard() {
        return clipBoard;
    }
}
