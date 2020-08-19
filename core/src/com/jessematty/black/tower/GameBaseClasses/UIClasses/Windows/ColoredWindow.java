package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ColoredWindow extends Window {
    Color titleColor;
    Color bodyColor;
    NinePatchDrawable topPatch;
    NinePatchDrawable bottomPatch;




    public ColoredWindow(String title, Skin skin, Color titleColor, Color bodyColor) {
        super(title, skin);
        this.titleColor = titleColor;
        this.bodyColor = bodyColor;
       topPatch= (NinePatchDrawable) skin.getDrawable("WindowTop");
     bottomPatch= (NinePatchDrawable) skin.getDrawable("WindowBottom");
               topPatch.getPatch().getTexture().getTextureData().prepare();
        Pixmap bgPixmap = topPatch.getPatch().getTexture().getTextureData().consumePixmap();

                bgPixmap.setColor(titleColor);
        bgPixmap.fill();
        TextureRegionDrawable  textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        getTitleTable().setBackground(textureRegionDrawableBg);
        bottomPatch.getPatch().getTexture().getTextureData().prepare();
        bgPixmap = bottomPatch.getPatch().getTexture().getTextureData().consumePixmap();
        bgPixmap.setColor(bodyColor);
        bgPixmap.fill();
         textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
      setBackground(textureRegionDrawableBg);



    }



    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        topPatch.getPatch().getTexture().getTextureData().prepare();
        Pixmap bgPixmap = topPatch.getPatch().getTexture().getTextureData().consumePixmap();


        bgPixmap.setColor(titleColor);
        bgPixmap.fill();
        TextureRegionDrawable  textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        getTitleTable().setBackground(textureRegionDrawableBg);
        bottomPatch.getPatch().getTexture().getTextureData().prepare();
        bgPixmap = bottomPatch.getPatch().getTexture().getTextureData().consumePixmap();
        bgPixmap.setColor(bodyColor);
        bgPixmap.fill();
        textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        setBackground(textureRegionDrawableBg);





    }

    @Override
    public void layout() {
        super.layout();

        topPatch.getPatch().getTexture().getTextureData().prepare();
        Pixmap bgPixmap = topPatch.getPatch().getTexture().getTextureData().consumePixmap();

        bgPixmap.setColor(titleColor);
        bgPixmap.fill();
        TextureRegionDrawable  textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        getTitleTable().setBackground(textureRegionDrawableBg);
        bottomPatch.getPatch().getTexture().getTextureData().prepare();
        bgPixmap = bottomPatch.getPatch().getTexture().getTextureData().consumePixmap();
        bgPixmap.setColor(bodyColor);
        bgPixmap.fill();
        textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        setBackground(textureRegionDrawableBg);




    }
}
