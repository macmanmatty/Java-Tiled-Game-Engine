package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;

import java.util.ArrayList;
public class TextureRegionSelect {// finds all textureRegions that start with  a given name and makes a  UI table of  them
    private ArrayList<TextureRegionImageDragSource> regions= new ArrayList<TextureRegionImageDragSource>();
    private GameAssets assetts;
    private Table table;
    private Skin skin;
    private final  ClipBoard clipBoard;
    
    public TextureRegionSelect(ClipBoard board, GameAssets assetts, Skin skin) {
        this.assetts = assetts;
        this.skin=skin;
        table = new Table( skin);
        this.clipBoard=board;
    }
    public void makeTable( String regionName, int number,  String atlasName, boolean doubleX){
        int counter=0;
        AtlasNamedAtlasRegion textureRegion=null;
       int counter2=0;
        while( counter2<number){
            textureRegion=assetts.getAtlasRegionByName(regionName+counter2, atlasName);
            if(textureRegion!=null) {
               TextureRegionImageDragSource textureRegionImage= new TextureRegionImageDragSource(clipBoard, null, textureRegion, new Image(textureRegion));
                regions.add(textureRegionImage );
            }
            counter2++;
        }
        int  size=regions.size();
        float width=1;
        float maxWidth=1;
        for(int count=0; count<size; count++) {
           width=regions.get(count).getActor().getWidth();
           if(width>maxWidth){
               maxWidth=width;
           }
        }
        int divisor=(int)(300/maxWidth);
        for(int count=0; count<size; count++) {
                table.add(regions.get(count).getActor());
                if (counter%divisor==0){
                    table.row();
                }
                counter++;
            }
    System.out.println("regions " + regions.size());
        return;
    }

    public ScrollPane getTable() {
       return  new ScrollPane(table);
    }
}
