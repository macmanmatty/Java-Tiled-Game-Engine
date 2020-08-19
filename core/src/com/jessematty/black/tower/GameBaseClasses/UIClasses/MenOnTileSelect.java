package com.jessematty.black.tower.GameBaseClasses.UIClasses;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;
import java.util.Iterator;
public class MenOnTileSelect
 {
	ArrayList<CheckBox> manChecks;
	LandSquareTile tile;
	public MenOnTileSelect(){}
	ZRPGPlayer entity;
	GameAssets gameAssets;
	Window window;
	Skin skin;
	ComponentMapper<ImageComponent> imageComponentComponentMapper;
	 ComponentMapper<Name> nameComponentMapper;
	 public Actor selectMan(final ZRPGPlayer player, final Skin skin, GameAssets gameAssets, String styleName) {
		 this.skin = skin;
		 this.gameAssets = gameAssets;
		 GameComponentMapper gameComponentMapper = gameAssets.getMapDraw().getGameComponentMapper();
		 gameComponentMapper.getImageComponentMapper();
		 nameComponentMapper = gameComponentMapper.getNameComponentMapper();
		 manChecks = new ArrayList<CheckBox>();
		 window = new Window("window", skin);
		 Table windowTable = window.center();
		 String name = player.getName().getText();
		 Label label = new Label(name, skin, styleName);
		 window.add(name);

		 Image image = player.getPlayerEntity().getComponent(ImageComponent.class).getImage();
		 window.add(image);
		 StringStats stringStats = player.getStringStats();

		 Keys<String> stringStatNames = stringStats.getStringStats().keys();

		 for (Iterator<String> stringIterator = stringStatNames.iterator();  stringIterator.hasNext();) {
			 StringStat stat = stringStats.getStringStat(stringIterator.next());
			 if (stat.isDisplayable()) {
				 String statString = stat.getName() + ": " + stat.getText();
				 Label numericStatLabel = new Label(statString, skin);
				 window.add(numericStatLabel);
			 }


		 }

		 NumericStats stats=player.getNumericStats();

		 Keys<String> statNames= stats.getNumericStats().keys();
			for(Iterator<String> stringIterator=statNames.iterator(); stringIterator.hasNext();){
				NumericStat stat=stats.getNumericStat(stringIterator.next());
				if(stat.isDisplayable()) {
					String statString = stat.getName() + ": " + stat.getIntValue();
					if(stat.isDisplayMinAndMax()){
						statString=statString+" "+stat.getMinIntValue() +" / "+ stat.getMaxIntValue();

					}
					Label numericStatLabel=new Label(statString,skin );
					window.add(numericStatLabel);
				}
			}


			BooleanStats booleanStats=player.getBooleanStats();
		 Keys<String> booleanStatNames= booleanStats.getBooleanStats().keys();
		 for(Iterator<String> stringIterator=booleanStatNames.iterator(); stringIterator.hasNext();){
			 BooleanStat stat=booleanStats.getBooleanStat(stringIterator.next());
			 if(stat.isDisplayable()) {
				 String statString = stat.getName() + ": " + stat.getFlag();
				 Label numericStatLabel=new Label(statString,skin );
				 window.add(numericStatLabel);
			 }

		 }
			Holder[]  heldItems = player.getHolders();
			
			for(int count=0; count<2; count++) {
				Entity item = gameAssets.getMapDraw().getWorld().getEntity(heldItems[count].getItemToHoldId());
				if (item != null) {
					Name nameComponent = nameComponentMapper.get(item);
					String itemName="";
					
					if (nameComponent != null) {
						itemName=nameComponent.getText();
					}
					Label itemLabel = new Label("Hand"+count+  ": " + itemName, skin);
					window.add(itemLabel);
					ImageComponent imageComponent = imageComponentComponentMapper.get(item);
					if(imageComponent!=null){
						
						Image image1=imageComponent.getImage();
						if (image1 != null) {
							
							window.add(image1);
						}
						window.row();
					}
					
					
					
				}
			}
			


			window.pack();
        return window;
		}




public Actor occupantInfo(Entity occupant) {
	VerticalGroup infoBox= new VerticalGroup();
	Label name= new Label("", skin);
	Label info= new Label ("", skin);
	TextButton exit = new TextButton("Close Window", skin, "Brick");
	exit.addListener(new InputListener(){
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	return true;
		}});
	infoBox.addActor(name);
	infoBox.addActor(info);
	infoBox.addActor(exit);
	return infoBox;
}
}
