package com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public class SpeechBubble extends Label {
    public SpeechBubble(  CharSequence text, Skin skin, String style) {
        super(text, skin, style);

    }

    public SpeechBubble( GameAssets assets,CharSequence text, Skin skin) {
        super(text, skin);
        getStyle().background=new TextureRegionDrawable(assets.getAtlasRegionByName("speechBubble"));
    }
}
