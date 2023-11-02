package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputMultiplexer;
public class ClosableWindow extends Window {
    /**
    // creates a window with button in the upper left corner that closes it and 
    // if wanted resize and move buttons. Additionally you can add a lockable
     // input processor to block
    //  any other input from being accepted  when the mose is over this  window.
    **/
    /**
     *  the buttons on the top for close resize drag etc.
     */
    /**
     * button for closing the window
     */
    private ImageButton close;
    /**
     * button to lock window resizing
     */
    private ImageButton resize;
    /**
     * 
     * button to lock window moving
     */
    private ImageButton move;
    /**
     * if true the window will be removed on close rather than just simply hidden
     */
    private boolean removeOnClose=true;
    /**
     * the style name of the window
     */
    private String styleName="";
    /**
     * if true the resize locking button will be displayed 
     * in the top right corner of the window
     */
    private boolean displayResizeButton =true;
    /**
     * if true the the window move locking button will be displayed  
     * in the top right corner of the window
     */
    private boolean displayMoveButton =true;
    /**
     * if true when the window is the stage focused
     * all other input will be locked aka not processed.
     */
    private boolean lockOtherInputOnStageFocus;
    /**
     * if true when the window is the stage focused
     * all other windows  will be locked aka not usable
     */
    private boolean lockOtherWindowsOnStageFocus;
    /**
     * whether or not this window is the stage focused actor
     */
    private boolean stageFocused;
    /**
     * the style of the closable buttons
     */
    private String buttonStyle;
    /**
     * the style for the window
     */
    private ClosableWindowStyle closableWindowStyle;
    /**
     * the colors of the close , resize and move buttons
     */
    private Color closeButtonColor=Color.RED;
    private Color moveButtonColor=Color.GREEN;
    private Color resizeButtonColor=Color.YELLOW;
    /**
     *  the games lockable input multiplexer
     */
    private LockableInputMultiplexer lockableInputMultiplexer;
    /**
     * Functional interface for a method that is called when the window is closed
     */
    private OnCloseAction onCloseAction;
    
    public ClosableWindow(String title, Skin skin) {
        this(title, skin, false);
    }
    public ClosableWindow(String title, Skin skin, LockableInputMultiplexer lockableInputMultiplexer) {
        this(title, skin, false);
        this.lockableInputMultiplexer=lockableInputMultiplexer;
    }
    public ClosableWindow(String title, Skin skin, boolean removeOnClose) {
        this(title, skin, "default");
        this.removeOnClose = removeOnClose;
    }
    public ClosableWindow(String title, Skin skin, boolean removeOnClose, boolean displayResizeButton, boolean displayMoveButton) {
        this(title, skin,"default", removeOnClose, displayResizeButton, displayMoveButton);
        this.removeOnClose = removeOnClose;
        this.displayResizeButton = displayResizeButton;
        this.displayMoveButton = displayMoveButton;
    }
    public ClosableWindow(String title, Skin skin, String styleName, boolean removeOnClose, boolean displayResizeButton, boolean displayMoveButton) {
        super(title, skin, styleName);
        this.removeOnClose = removeOnClose;
        this.displayResizeButton = displayResizeButton;
        this.displayMoveButton = displayMoveButton;
        remakeTitle();
        if(!styleName.isEmpty() && !styleName.equals("default")) {
            this.styleName = styleName;
        }
        addListeners();
    }
    public ClosableWindow(String title, Skin skin, String styleName) {
        this(title, skin, styleName, true, true, true);
    }
    public ClosableWindow(String title, Skin skin, ClosableWindowStyle closableWindowStyle) {
        this(title, skin);
        this.closableWindowStyle = closableWindowStyle;
    }
    /**
     * method to close the window
     */
    protected void close(){
        if(onCloseAction!=null){
            onCloseAction.act();
        }
        if(removeOnClose==false) {
            setVisible(false);
        }
        else{
            addAction(Actions.removeActor());
            if(lockableInputMultiplexer!=null){
                lockableInputMultiplexer.clearAllCurrentInputProcessors();
            }
        }
    }
    /**
     *  remakes the title bar on window resize and re-adds the  top buttons close resize and lock
     */
    protected void remakeTitle() {
        getTitleTable().clear();
        getTitleTable().add(getTitleLabel()).expandX().fillX().minWidth(0);
        HorizontalGroup buttons= new HorizontalGroup();
        buttons.space(5);
        if(closableWindowStyle!=null && closableWindowStyle.closeButtonStyle!=null){
            close= new ImageButton(closableWindowStyle.closeButtonStyle);
        }
        else {
            close = new ImageButton(getSkin(), "close" + styleName);
            close.getImage().setColor(closeButtonColor);
        }
        close.addListener(new ClickListener() {
        @Override
        public void clicked (InputEvent event,float x, float y){
            close();
        }
    });
        buttons.addActor(close);
        if(displayMoveButton) {
            if (closableWindowStyle != null && closableWindowStyle.moveButtonStyle != null) {
                move = new ImageButton(closableWindowStyle.moveButtonStyle);
            } else {
                move = new ImageButton(getSkin(), "close" + styleName);
                move.getImage().setColor(moveButtonColor);
            }
            move.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setResizable(!isResizable());
                }
            });
            buttons.addActor(move);
        }
        if(displayResizeButton) {
            if (closableWindowStyle != null && closableWindowStyle.resizeButtonStyle != null) {
                resize= new ImageButton(closableWindowStyle.resizeButtonStyle);
            } else {
                resize = new ImageButton(getSkin(), "close" + styleName);
                resize.getImage().setColor(resizeButtonColor);
            }
            resize.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setMovable(!isMovable());
                }
            });
            buttons.addActor(resize);
        }
        getTitleTable().add(buttons).padRight(5);
    }
    @Override
    public void pack() {
        super.pack();
    }
    public boolean isRemoveOnClose() {
        return removeOnClose;
    }
    public void setRemoveOnClose(boolean removeOnClose) {
        this.removeOnClose = removeOnClose;
    }
    public ImageButton getClose() {
        return close;
    }
    public void setClose(ImageButton close) {
        this.close = close;
        remakeTitle();
    }
    public ImageButton getResize() {
        return resize;
    }
    public void setResize(ImageButton resize) {
        this.resize = resize;
        remakeTitle();
    }
    public ImageButton getMove() {
        return move;
    }
    public void setMove(ImageButton move) {
        this.move = move;
    }
    public String getStyleName() {
        return styleName;
    }
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    public boolean isDisplayResizeButton() {
        return displayResizeButton;
    }
    public void setDisplayResizeButton(boolean displayResizeButton) {
        this.displayResizeButton = displayResizeButton;
        remakeTitle();
    }
    public boolean isDisplayMoveButton() {
        return displayMoveButton;
    }
    public void setDisplayMoveButton(boolean displayMoveButton) {
        this.displayMoveButton = displayMoveButton;
        remakeTitle();
    }
    public String getButtonStyle() {
        return buttonStyle;
    }
    public void setButtonStyle(String buttonStyle) {
        this.buttonStyle = buttonStyle;
        remakeTitle();
    }
    public ClosableWindowStyle getClosableWindowStyle() {
        return closableWindowStyle;
    }
    public void setClosableWindowStyle(ClosableWindowStyle closableWindowStyle) {
        this.closableWindowStyle = closableWindowStyle;
        remakeTitle();
    }
    public Color getCloseButtonColor() {
        return closeButtonColor;
    }
    public void setCloseButtonColor(Color closeButtonColor) {
        this.closeButtonColor = closeButtonColor;
        remakeTitle();
    }
    public Color getMoveButtonColor() {
        return moveButtonColor;
    }
    public void setMoveButtonColor(Color moveButtonColor) {
        this.moveButtonColor = moveButtonColor;
        remakeTitle();
    }
    public Color getResizeButtonColor() {
        return resizeButtonColor;
    }
    public void setResizeButtonColor(Color resizeButtonColor) {
        this.resizeButtonColor = resizeButtonColor;
        remakeTitle();
    }
    public boolean isLockOtherWindowsOnStageFocus() {
        return lockOtherWindowsOnStageFocus;
    }
    public void setLockOtherWindowsOnStageFocus(boolean lockOtherWindowsOnStageFocus) {
        this.lockOtherWindowsOnStageFocus = lockOtherWindowsOnStageFocus;
    }
    public class ClosableWindowStyle {
        ImageButtonStyle closeButtonStyle;
        ImageButtonStyle resizeButtonStyle;
        ImageButtonStyle moveButtonStyle;
        LabelStyle titleLabelStyle;
        WindowStyle windowStyle;
    }
    public void setLockOtherInput(boolean lockOtherInput) {
        if (lockableInputMultiplexer!=null && lockOtherInput==true) {
            lockableInputMultiplexer.setCurrentMouseProcessor(getStage());
            lockableInputMultiplexer.setCurrentKeyInputProcessor(getStage());
        }
        else{
            if(lockableInputMultiplexer!=null) {
                lockableInputMultiplexer.clearAllCurrentInputProcessors();
            }
        }
    }
    public LockableInputMultiplexer getLockableInputMultiplexer() {
        return lockableInputMultiplexer;
    }
    public void setLockableInputMultiplexer(LockableInputMultiplexer lockableInputMultiplexer) {
        this.lockableInputMultiplexer = lockableInputMultiplexer;
    }
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        // actor is set to not visible and is the focused stage actor  unlock all other listeners
        if( getStage()!=null && getStage().getKeyboardFocus().equals(this)) {
            if (visible == false && lockableInputMultiplexer != null) {
                lockableInputMultiplexer.clearAllCurrentInputProcessors();
            }
        }
    }
    public void addListeners(){
        addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if (lockOtherInputOnStageFocus) {
                            stageFocused = true;
                            if (lockableInputMultiplexer != null) {
                                lockableInputMultiplexer.setCurrentUnlockedKeyDownProcessor(getStage());
                                lockableInputMultiplexer.setCurrentMouseProcessor(getStage());
                            }
                        }
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (lockOtherInputOnStageFocus==true && stageFocused==true ){
                    stageFocused=false;
                    lockableInputMultiplexer.clearAllCurrentInputProcessors();
                }
            }
        });
    }
    public  void releaseInputLock(){
        lockableInputMultiplexer.clearAllCurrentInputProcessors();
    }
    public boolean isLockOtherInputOnStageFocus() {
        return lockOtherInputOnStageFocus;
    }
    public void setLockOtherInputOnStageFocus(boolean lockOtherInputOnStageFocus) {
        this.lockOtherInputOnStageFocus = lockOtherInputOnStageFocus;
    }
    private boolean inBounds(){
        float mouseX = Gdx.input.getX();
        float mouseY=Gdx.input.getY();
       Vector2 stageMouseCoordinates= getStage().screenToStageCoordinates(new Vector2(mouseX, mouseY));
        return true;
    }
    public OnCloseAction getOnCloseAction() {
        return onCloseAction;
    }
    public void setOnCloseAction(OnCloseAction onCloseAction) {
        this.onCloseAction = onCloseAction;
    }
}
