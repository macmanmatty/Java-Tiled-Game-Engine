package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class ClosableWindow extends Window { // creates a table with button in the upper left corner that closes it.
    private ImageButton close;
    private ImageButton resize;
    private ImageButton move;
    private boolean removeOnClose=true;
    private String styleName="";
    private boolean hasResize=true;
    private boolean hasMove =true;
    private String buttonStyle;
    private ClosableWindowStyle closableWindowStyle;
    private Color closeButtonColor=Color.RED;
    private Color moveButtonColor=Color.GREEN;
    private Color resizeButtonColor=Color.YELLOW;

    public ClosableWindow(String title, Skin skin) {
        this(title, skin, false);
    }
    public ClosableWindow(String title, Skin skin, boolean removeOnClose) {
        this(title, skin, "default");
        this.removeOnClose = removeOnClose;

    }

    public ClosableWindow(String title, Skin skin, boolean removeOnClose, boolean hasResize, boolean hasMove) {
        this(title, skin,"default", removeOnClose, hasResize, hasMove);
        this.removeOnClose = removeOnClose;
        this.hasResize = hasResize;
        this.hasMove = hasMove;
    }

    public ClosableWindow(String title, Skin skin, String styleName, boolean removeOnClose, boolean hasResize, boolean hasMove) {
        super(title, skin, styleName);
        this.removeOnClose = removeOnClose;
        this.hasResize = hasResize;
        this.hasMove = hasMove;
        remakeTitle();
        if(!styleName.isEmpty() && !styleName.equals("default")) {
            this.styleName = styleName;
        }
    }

    public ClosableWindow(String title, Skin skin, String styleName) {
        this(title, skin, styleName, true, true, true);


    }

    public ClosableWindow(String title, Skin skin, ClosableWindowStyle closableWindowStyle) {
        super(title, skin);
        this.closableWindowStyle = closableWindowStyle;


    }


    private void remakeTitle() {


        getTitleTable().clear();
        getTitleTable().add(getTitleLabel()).expandX().fillX().minWidth(0);
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
            if(removeOnClose==false) {
                setVisible(false);
            }
            else{
                addAction(Actions.removeActor());
            }
        }
    });



        if(hasMove==true) {
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
        }

        if(hasResize==true) {
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

        }

        HorizontalGroup buttons= new HorizontalGroup();
        buttons.space(5);
        buttons.addActor(close);
        buttons.addActor(move);
        getTitleTable().add(close).size(15, 15).pad(0,0,0,5);
        getTitleTable().pad(0,0,0,5).add(move).size(15, 15).pad(0,0,0,5);
        getTitleTable().add(resize).size(15, 15);
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

    public boolean isHasResize() {
        return hasResize;
    }

    public void setHasResize(boolean hasResize) {
        this.hasResize = hasResize;
    }

    public boolean isHasMove() {
        return hasMove;
    }

    public void setHasMove(boolean hasMove) {
        this.hasMove = hasMove;
    }

    public String getButtonStyle() {
        return buttonStyle;
    }

    public void setButtonStyle(String buttonStyle) {
        this.buttonStyle = buttonStyle;
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

    public class ClosableWindowStyle {
        ImageButtonStyle closeButtonStyle;
        ImageButtonStyle resizeButtonStyle;
        ImageButtonStyle moveButtonStyle;
        LabelStyle titleLabelStyle;
        WindowStyle windowStyle;

    }




}
