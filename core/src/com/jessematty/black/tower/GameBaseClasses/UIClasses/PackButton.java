package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;


public class PackButton extends TextButton {
	public PackButton(String text, Skin skin) {
		super(text, skin);
	}


	Entity item;
	int cost;
	ZRPGPlayer fighters;
	GameAssets assetts;


	public PackButton(String text, final Skin skin, final Entity item, final ZRPGPlayer player, PackButtonKind kind) {
		super(text, skin, "Brick");
		this.item = item;
		this.fighters = player;
		switch (kind) {
			case SET_ITEM_TO_USE:

				addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Entity item = getItem();





						return true;

					}
				});


				break;
			case Light:
				addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

						Entity item=getItem();









						return true;

					}
				});

				break;
			case BUY:
				break;
			case SELL:
				break;
			case EQUIP:
				addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						Entity item = getItem();
					}

				});

				break;
			case USE:

				addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Entity item =getItem();

						return true;

					}
				});
				break;
			case DROP:

				addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Entity item = getItem();




						return true;

					}
				});
				break;
			case SELECT:
				break;
			case INFO:
                addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Position position=player.getPosition();

						Window window=new ObjectInfo(assetts).displayInfo( player,item, new Skin(), "");
                        assetts.getMapDraw().addWindow(window, position.getScreenLocationX(), position.getScreenLocationY());




                        return true;

                    }
                });

				break;
		}


	}

	public PackButton(String text, Skin skin, Entity item, int cost) {
		super(text, skin, "Brick");
		this.item = item;
		this.cost = cost;

	}

	public int getCost() {
		return cost;
	}

	public Entity getItem() {
		return item;
	}

	public ZRPGPlayer getFighters() {
		return fighters;
	}
}
