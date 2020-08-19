package com.jessematty.black.tower.Maps.Buildings.Shops;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.Buildings.Rooms.Room;

public  class Shop  extends Building {




	public Shop() {
	}

	public Shop(GameAssets assetManager,  Skin skin) {
		super(assetManager,  skin);
	}

	public Shop(int xSize, int ySize, GameAssets gameAssets) {
		super( xSize, ySize, gameAssets);
	}

	public Shop( int xSize, int ySize, GameAssets gameAssets, Skin skin) {
		super(xSize, ySize, gameAssets, skin);
	}
}
