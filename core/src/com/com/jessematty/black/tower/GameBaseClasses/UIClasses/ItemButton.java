package com.jessematty.black.tower.GameBaseClasses.UIClasses;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;

public class ItemButton extends TextButton {
	public ItemButton(String text, Skin skin) {
		super(text, skin);
	}
	Entity item;
	int cost;
	ZRPGCharacter fighters;
	public ItemButton(String text, final Skin skin, final Entity item, final ZRPGCharacter player, PackButtonKind kind) {
		super(text, skin, "Brick");
		this.item = item;
		this.fighters = player;

	}
	public ItemButton(String text, Skin skin, Entity item, int cost) {
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
	public ZRPGCharacter getFighters() {
		return fighters;
	}
}
