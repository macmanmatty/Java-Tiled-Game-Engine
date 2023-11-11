package com.jessematty.black.tower.GameBaseClasses.UIClasses.Actors;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
public class ZRPGActors {
    private Image targetImage;
    public ZRPGActors(MapDraw draw) {
       // TextureRegion region=draw.getAssetts().getAtlasRegionByName("target", "assetts");
      //  targetImage=new Image(region);
        //draw.getUiStage().addActor(targetImage);
    }
    public Image getTargetImage() {
        return targetImage;
    }
    public void setTargetImage(Image targetImage) {
        this.targetImage = targetImage;
    }
}
