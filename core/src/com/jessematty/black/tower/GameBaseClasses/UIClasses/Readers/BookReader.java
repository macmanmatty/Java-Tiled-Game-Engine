package com.jessematty.black.tower.GameBaseClasses.UIClasses.Readers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Readers.Reader;

import java.util.ArrayList;

public class BookReader extends Reader {
    protected Button pageUp;
    protected  Button pageDown;

    Readable currentReadable;

    ArrayList<Readable> readables;
    Skin skin;
    String styleName;
    String text;








    public BookReader(GameAssets assetts) {
        super(assetts);


    }

    public Window  displayText (final ArrayList<Readable> readables){
        this.readables=readables;





    final int pages= readables.size();



        pageUp= new ImageButton(skin, "pageUp");
        pageUp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(currentPage<pages-1){

                    currentPage++;
                    currentReadable=readables.get(currentPage);

                }



            }
        });


        pageDown= new ImageButton(skin, "pageUp");
        pageUp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(currentPage>0){

                    currentPage--;
                    currentReadable=readables.get(currentPage);

                }



            }
        });




        Window window = new Window("",skin, styleName);
       Label label= new Label(text,skin, styleName);
       window.add(label);

       window.row();
       window.add(pageDown);
       window.add(pageUp);


        window.pack();
        window.validate();
        return window;


    }

}
