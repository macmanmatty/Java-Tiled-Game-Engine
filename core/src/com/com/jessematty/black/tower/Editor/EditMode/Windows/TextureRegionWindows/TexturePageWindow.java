package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;

public class TexturePageWindow   {
   private  NamedField texturePageHeight;
   private  NamedField texturePageWidth;
   private  NamedField texturePagePadding;
   private  NamedField texturePageName;
   private TextButton addTexturePage;
   private String atlasName;
   private Skin skin;


    public TexturePageWindow(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
       // super(mapEditScreen, title, skin, style);

    }



   // @Override
    public void makeWindow() {
        texturePageHeight= new NamedField(new Label("Page Height", skin), new PositiveIntegerField("1024", skin));
        texturePageWidth= new NamedField(new Label("Page Width", skin), new PositiveIntegerField("1024", skin));
        texturePagePadding= new NamedField(new Label("Page Texture Region Padding ", skin), new PositiveIntegerField("2", skin));
        texturePageName= new NamedField(new Label("Page Name", skin), new PositiveIntegerField("", skin));


        addTexturePage= new TextButton("Add New Page", skin);
        addTexturePage.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



                addPage();
                        return  true;

            }
        });






    }

    private void addPage(){

        String pageName=texturePageName.getField().getText();
        if(pageName.isEmpty()){

           // pageName="page"+getMapEditScreen().getWorldObjects().getTextureRegionGroups().size;
        }

        PositiveIntegerField widthField= (PositiveIntegerField) texturePageWidth.getField();
        int pageWidth= widthField .getInteger();

        if(pageName.isEmpty()){

          //  pageName="page"+getMapEditScreen().getWorldObjects().getTextureRegionGroups().size;
        }


        PositiveIntegerField heightField= (PositiveIntegerField) texturePageHeight.getField();
        int pageHeight= heightField .getInteger();

        if(pageName.isEmpty()){

          //  pageName="page"+getMapEditScreen().getWorldObjects().getTextureRegionGroups().size;
        }

        PositiveIntegerField paddingField= (PositiveIntegerField) texturePageHeight.getField();
        int pagePadding=paddingField .getInteger();

        if(pageName.isEmpty()){

          //  pageName="page"+getMapEditScreen().getWorldObjects().getTextureRegionGroups().size;
        }
        TextureRegionPage page= new TextureRegionPage(pageName, atlasName, pageWidth, pageHeight, pagePadding );
       // getMapEditScreen().getWorldObjects().getTextureRegionGroups().add(page);

    }


    public void addTexturePage(){


    }
}
