package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

import java.util.ArrayList;


public class BookEdit extends ItemEdit {

   ArrayList<String > bookPagesText= new ArrayList<String>();


    TextButton addItem;
    TextButton removeItem;
    TextButton addPage;
    TextField languageName;
    TextButton addLanguage;
    VerticalGroup languageGroup2;


    VerticalGroup bookTextGroup;

    CheckBox showBookGroup;
    CheckBox showPagesToEdit;
    CheckBox showLanguageEdit;

    VerticalGroup bookGroup;
    SelectBox<String> language;



    ArrayList <TextField> bookText= new ArrayList<TextField>();



    public BookEdit(GameAssets asetts, final Skin skin ) {
        bookGroup= new VerticalGroup();
        bookTextGroup= new VerticalGroup();
        languageGroup2= new VerticalGroup();


   TextField field1= new TextField("", skin);
        final HorizontalGroup bookTextGroup= new HorizontalGroup();
        Label label1= new Label("Page #1 Text", skin);
        bookTextGroup.addActor(label1);
        bookTextGroup.addActor(field1);
        bookText.add(field1);
        this.bookTextGroup.addActor(bookTextGroup);

        addPage= new TextButton("Add Page To Book", skin);
        addPage.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                TextField field2= new TextField("", skin);

                HorizontalGroup bookTextGroup= new HorizontalGroup();
                Label label1= new Label("Page #" +bookText.size()+"  Text", skin);
                bookTextGroup.addActor(label1);
                bookTextGroup.addActor(field2);
                bookText.add(field2);
                bookTextGroup.addActor(bookTextGroup);

            }
        });



        final HorizontalGroup languageGroup= new HorizontalGroup();

        final Label label2= new Label("The Books Langugae", skin);


        showBookGroup= new CheckBox("Show Book Fileds To Edit", skin);


        showBookGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    bookGroup.setVisible(true);
                }

                else {
                    bookGroup.setVisible(false);

                }

            }
        });

        showPagesToEdit= new CheckBox("Show Book Pages To Edit", skin);


        showPagesToEdit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    bookTextGroup.setVisible(true);
                }

                else {
                    bookTextGroup.setVisible(false);

                }

            }
        });

        showLanguageEdit= new CheckBox("Show Language Group ", skin);


        showLanguageEdit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    languageGroup2.setVisible(true);
                }

                else {
                    languageGroup2.setVisible(false);

                }

            }
        });

        this.bookTextGroup.addActor(addPage);


        languageName= new TextField("", skin);
        HorizontalGroup languageEditGroup = new HorizontalGroup();
        Label label = new Label("new Language Name", skin);
        languageEditGroup.addActor(label);
        languageEditGroup.addActor(languageName);
        addLanguage= new TextButton("Add New Language To Game", skin);
        addLanguage.addListener(new ClickListener(){


            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
              //obejects.addEntity(languageName.getMessageText());


            }
        });
        languageGroup2.addActor(languageGroup);

        languageGroup2.addActor(addLanguage);

        showBookGroup.add(languageGroup);
       Label label3= new Label("Select an Item To Add It's Info To The Book", skin);
       showBookGroup.add(label3);

       window.add(showBookGroup);

        window.row();
       window.add(languageGroup2);
        languageGroup2.columnAlign(Align.left);

        window.row();

       window.add(showPagesToEdit);
       window.row();
       window.add(this.bookTextGroup);
       window.add(bookGroup);
        bookGroup.columnAlign(Align.left);

        window.pack();






    }


}
