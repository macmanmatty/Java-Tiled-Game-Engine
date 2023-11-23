package com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;

public class TileSetTextureRegionDraggableTarget extends Target {

    private final int bitmaskNumber;
    private final TileSet bitMaskableTileSet;
    private  AtlasRegion region;
    private ClipBoard clipBoard;

    public TileSetTextureRegionDraggableTarget(final ClipBoard clipBoard, int bitmaskNumber, TileSet bitMaskableTileSet, final Image region) {
        super(region);
        this.bitmaskNumber=bitmaskNumber;
        this.bitMaskableTileSet =bitMaskableTileSet;
        this.clipBoard=clipBoard;
        region.addListener( new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                region.setDrawable(new TextureRegionDrawable((Texture) clipBoard.getCurrentObject()));
                return true;


            }
        });



        
        
    }



    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return false;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        Object object=payload.getObject();
        if(object instanceof AtlasRegion) {
            AtlasRegion region = (AtlasRegion) object;
           Image image= (Image) getActor();
           image.setDrawable(new TextureRegionDrawable(region));
           this.region=region;




        }


    }

    public AtlasRegion getRegion() {
        return region;
    }
}
