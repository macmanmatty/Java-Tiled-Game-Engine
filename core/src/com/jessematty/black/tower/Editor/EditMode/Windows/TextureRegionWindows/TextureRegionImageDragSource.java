package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;


public class TextureRegionImageDragSource extends Source {
    final Payload payload= new Payload();
    private final Target target;
   private final   Image image;
   private AtlasRegion region;
   private final ClipBoard clipBoard;
    public TextureRegionImageDragSource( ClipBoard clipBoard, AtlasNamedAtlasRegion region,   final Image image) {
        this( clipBoard, null,  region,  image);

    }
    public TextureRegionImageDragSource(final ClipBoard clipboard, Target target, final AtlasNamedAtlasRegion region, final Image image) {
        super(image);
        this.target=target;
        this.image=image;
        this.region=region;
        this.clipBoard=clipboard;
        image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(button==1) {
                    image.setDebug(true);
                    image.setColor(1, 1, 1, .5f);
                    clipboard.setCurrentObject(region);
                }

                else{

                    image.setDebug(true);
                    image.setColor(1, 1, 1, .5f);
                    Cell cell= new Cell();
                    cell.setTile(new AtlasStaticTiledMapTile(region));
                    clipboard.setCurrentObject(cell);
                }

                return  true;
            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                image.setColor(1, 1, 1, .5f);

                return  true;

            }

        });
    }
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Image validImage=new Image(region);

        payload.setDragActor(validImage);
        payload.setValidDragActor(validImage);
        Image invalidImage= new Image(region);
        invalidImage.setColor(1,1,1, .5f);
        payload.setInvalidDragActor(invalidImage);
        payload.setObject(region);
        return payload;
    }
    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        super.dragStop(event, x, y, pointer, payload, target);
    }
}
