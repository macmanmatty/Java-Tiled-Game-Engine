package com.jessematty.black.tower.GameBaseClasses.UIClasses;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionImageComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponents;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.World;

import java.util.ArrayList;

public class PackDisplay {
private Skin skin;
private PackComponent packComponent;
private Window window;
private GameAssets gameAssets;
private World world;
private ArrayList<CheckBox> itemBoxes= new ArrayList<CheckBox>();
private ArrayList<Entity> usableItems= new ArrayList<Entity>();
private ComponentMapper<NameComponent> nameComponentMapper;
	private ComponentMapper<ImageComponent> itemImageComponentMapper;
	private ComponentMapper<ItemActionComponents> actionComponentsComponentMapper;


	public PackDisplay(Skin skin, GameAssets gameAssets) {
		this.skin = skin;
		this.gameAssets = gameAssets;
	
		nameComponentMapper=GameComponentMapper.getNameComponentMapper();
		itemImageComponentMapper=GameComponentMapper.getImageComponentMapper();
		actionComponentsComponentMapper=GameComponentMapper.getActionComponentsComponentMapper();


	}

	public Window displayPack(PackComponent packComponent, ZRPGCharacter player, Skin skin, String style, Class<? extends Component> ...components){ // displays the contents of fighters pack
		this.packComponent = packComponent;
		this.skin=skin;
	window= new Window("Pack Contents", skin);
		ArrayList<Integer> itemAmounts= new ArrayList<Integer>();
		Table table= new Table();
		Array<String>itemsInPack=null;


		Array<Entity> matchingItems= GameComponentMapper.getEntitiesWithComponentsById(world, itemsInPack, components);
		ArrayList<Entity>singleItems = new ArrayList<Entity>();
		int size=matchingItems.size;
		if (matchingItems.isEmpty()==false){
		String nameItem1=nameComponentMapper.get(matchingItems.get(0)).getStat();
		singleItems.add(matchingItems.get(0));
		int itemAmount=1;
		String nameItem2="";
		for (int count=1; count<size; count++){
			Entity entity=matchingItems.get(count);
			nameItem2=nameComponentMapper.get(entity).getName();
			if (nameItem1.equalsIgnoreCase(nameItem2)){
				itemAmount++;
			}
			else{
				singleItems.add(matchingItems.get(count));
				itemAmounts.add(itemAmount);
				itemAmount=1;
				nameItem1=nameItem2;
				
			}
		}
		itemAmounts.add(itemAmount);
		 size=singleItems.size();
		String name;
		Label itemLabel;
		Image itemImage=null;
		for (int count=0; count<size; count++){
				final Entity item=singleItems.get(count);
			name=nameComponentMapper.get(item).getStat();
			 itemLabel=new Label(name, skin);
			 ImageComponent imageComponentCompenent =itemImageComponentMapper.get(item);
			 if(imageComponentCompenent !=null){
			 	itemImage= imageComponentCompenent.getImage();
			 }

			table.add(itemLabel);
			if(itemImage!=null) {
				table.add((Actor) itemImage);
			}
			ItemActionComponents itemActionComponents =actionComponentsComponentMapper.get(item);
			if(itemActionComponents !=null){

				HorizontalGroup buttons= new HorizontalGroup();
				Array<ItemActionImageComponent>  actionComponentsArray= itemActionComponents.getActionComponents();

				int actions=actionComponentsArray.size;
				for(int count2=0; count2<actions; count2++){
					final ItemActionImageComponent componentClass=actionComponentsArray.get(count2);

					final TextButton actionButton= new TextButton(componentClass.toString(), skin);
					actionButton.addListener(new ClickListener(){

						@Override
						public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
								item.add(componentClass);
							return true;

						}
					});




				}

			}



			String text=name + "Amount: "+itemAmounts.get(count);
			itemLabel=new Label(text, skin);
			table.row();
		}
		ScrollPane pane= new ScrollPane(table);
		window.add(pane);
		window.row();
		window.setSize(900, 500);
		}
		
			
	
		else{
			Label label= new Label ("pack is empty", skin);
			window.add(label);
			window.row();
			
			
			
			
		}
		TextButton exit = new TextButton("close window", skin,"Brick");
		exit.setText("Close Window");
		exit.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				window.remove();
				return true;
			}});
		HorizontalGroup bottomButtons= new HorizontalGroup();
		bottomButtons.getChildren().add(exit);
		window.add(bottomButtons);
		gameAssets.getMapDraw().getUiStage().addWindow(window,player.getPositionComponent().getLocationX()+100, player.getPositionComponent().getLocationY()+100);
		return window;
	}



	private class ItemBox extends VerticalGroup {

		Image itemImage;
		Label name;


		public ItemBox(Image itemImage, int quantity,  String name, Skin  skin) {
			this.itemImage = itemImage;
			String fullName= name+ "number of: "+quantity;
			this.name = new Label(name, skin);

		}


	}
		
}
