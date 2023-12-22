package com.jessematty.black.tower.Editor.EditMode.Windows.AnimationEditor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;

public class DirectionalAnimationEditor  extends Table {
    private  boolean eightDirections;
    private AnimationEditor  [] animationEditors;
    private DragAndDrop dragAndDrop;
    private int numberOfDirections =8;
    private NamedColor namedColor= NamedColor.WHITE;
    private String currentAction ="";
    private SelectBox<NamedColor> colorSelectBox;
    private SelectBox<String> actionSelectBox;
    private AnimatableComponent animatable;
    private TextField actionTextField;
    private Button addAction;
    private Button setAnimations;
    private ClipBoard clipBoard;
    
    
    

    public DirectionalAnimationEditor ( ClipBoard clipBoard, DragAndDrop dragAndDrop,  boolean eightDirections, Skin skin) {
        super(skin);
        this.eightDirections = eightDirections;
        this.dragAndDrop=dragAndDrop;
        this.clipBoard=clipBoard;

        if(eightDirections==false){
            numberOfDirections =4;
        }
        animationEditors=new  AnimationEditor[numberOfDirections];
    }
    
    public void makeUIElements(){
        colorSelectBox= new SelectBox<NamedColor>(getSkin());
        colorSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        
        colorSelectBox.getItems().add(NamedColor.WHITE);
        actionSelectBox= new SelectBox<>(getSkin());
        actionSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                
                currentAction=actionSelectBox.getSelected();
                setAnimations(animatable);
                
                
            }
        });
        setAnimations= new TextButton("Save Animations", getSkin());
        setAnimations.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(animatable!=null) {
                    for (int count = 0; count < numberOfDirections; count++) {
                        com.jessematty.black.tower.Components.Animation.Animation animation=animationEditors[count].getAnimation();
                        if(animation!=null) {
                            animatable.addAnimation(animation);
                        }
                    }
                }
                return true;
            }
        });
        addAction= new TextButton("Add New Action", getSkin());
        addAction.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(animatable!=null){
                String action = actionTextField.getText();
                Direction [] directions=Direction.values();
                if (!action.isEmpty()) {
                    for (int count = 0; count < numberOfDirections; count++) {
                        com.jessematty.black.tower.Components.Animation.Animation animation = new com.jessematty.black.tower.Components.Animation.Animation();
                        animation.setAction(action);
                        animation.setDirection(directions[count]);
                        animation.setColor(colorSelectBox.getSelected());
                        animatable.addAnimation(new com.jessematty.black.tower.Components.Animation.Animation());
                    }
                }
            }
                return true;
            }
        });
        
        actionTextField= new TextField("", getSkin());
  
        makeTable();
        
        
        
    }
    public void makeTable(){
        HorizontalGroup horizontalGroup= new HorizontalGroup();
        horizontalGroup.addActor( new NamedField("Enter Action Name", getSkin(), actionTextField));
        horizontalGroup.addActor(addAction);
        horizontalGroup.addActor(actionSelectBox);
        horizontalGroup.addActor(colorSelectBox);
        row();
            animationEditors[0]=new AnimationEditor( getSkin(), clipBoard, dragAndDrop, Direction.UP);
        animationEditors[1]=new AnimationEditor( getSkin(),clipBoard,  dragAndDrop, Direction.RIGHT);
        animationEditors[2]=new AnimationEditor( getSkin(),clipBoard , dragAndDrop, Direction.DOWN);
        animationEditors[3]=new AnimationEditor( getSkin(),clipBoard,  dragAndDrop, Direction.LEFT);
        if(eightDirections==true) {
            animationEditors[4] = new AnimationEditor(getSkin(),clipBoard,  dragAndDrop, Direction.RIGHTUP);
            animationEditors[5] = new AnimationEditor(getSkin(),clipBoard,  dragAndDrop, Direction.RIGHTDOWN);
            animationEditors[6] = new AnimationEditor(getSkin(),clipBoard,  dragAndDrop, Direction.LEFTDOWN);
            animationEditors[7] = new AnimationEditor(getSkin(), clipBoard, dragAndDrop, Direction.LEFTUP);
        }
        for(int count=0; count<numberOfDirections; count++){
            add(animationEditors[count]);
            row();
        }
        add( setAnimations);
        
    }
    public void setAnimations(AnimatableComponent animatable){
        this.animatable=animatable;
        if(eightDirections==true){
            numberOfDirections =8;
        }
        else{
            
            numberOfDirections =4;
        }
        makeTable();
        Direction [] directions=Direction.values();
        
        for(int count=0; count<numberOfDirections; count++){
            animationEditors[count].setAnimation(getAnimation(animatable, currentAction, directions[count]));
            
            
        }
      
    }
    
    private com.jessematty.black.tower.Components.Animation.Animation getAnimation(AnimatableComponent animatable, String action , Direction direction){
            Animation animation= animatable.getAnimations().get(direction.toString()).get(action);



        return animation;
    }
    public AnimatableComponent getAnimatable() {
        return animatable;
    }
    public void setAnimatable(AnimatableComponent animatable) {
        this.animatable = animatable;
       Keys<String> keys= animatable.getAnimations().get(Direction.UP.toString()).keys();
       Array<String > keysArray= keys.toArray(new Array<String>());
        actionSelectBox.setItems(keysArray);

        
    }
}
