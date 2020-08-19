package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClosebleWindow extends Window { // creates a table with button in the upper left corner that closes it.
    private ImageButton close;
    private ImageButton resize;
    private ImageButton move;
    private boolean removeOnClose;

    public ClosebleWindow(String title, Skin skin) {
        super(title, skin);
        addButton();


    }


    public ClosebleWindow(String title, Skin skin, boolean removeOnClose) {
        super(title, skin);
        this.removeOnClose = removeOnClose;
    }

    public ClosebleWindow(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
        addButton();
    }


    private void addButton() {


    close=new ImageButton( getSkin(), "close");
        close.getImage().setColor(Color.RED);




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
        move=new ImageButton( getSkin(), "close");
        move.getImage().setColor(Color.GREEN);


        move.addListener(new ClickListener() {

                                      @Override
                                      public void clicked (InputEvent event,float x, float y){
                                          setResizable(!isResizable());


                                      }
                                  });

        resize=new ImageButton( getSkin(), "close");
        resize.getImage().setColor(Color.YELLOW);


        resize.addListener(new ClickListener() {

            @Override
            public void clicked (InputEvent event,float x, float y){
                setMovable(!isMovable());
            }
        });

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
}
