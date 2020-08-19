package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MapLayerHideButton extends ImageButton {
    boolean visible=true;
    public MapLayerHideButton(final TiledMapTileLayer layer, final Drawable imageUp, final Drawable imageDown) {

        super(imageUp, imageDown);

        addListener( new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(visible==true) {
                    layer.setVisible(true);
                    setBackground(imageUp);

                }

                else {

                    layer.setVisible(false);
                    setBackground(imageDown);

                }


                return true;

            }
        });



    }
}
