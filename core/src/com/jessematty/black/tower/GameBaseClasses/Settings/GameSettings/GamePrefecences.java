package com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Game.GameKind;
import com.jessematty.black.tower.Game.TileKind;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;

public class GamePrefecences {
    private GameInputKeys gameInputKeys = new GameInputKeys();
    private String gameName;
   private  JsonLoader jsonLoader= GameAssets.getJsonLoader();
    Preferences settings ;


    public GamePrefecences(String gameName) {
        this.gameName = gameName;
settings = Gdx.app.getPreferences("preferences" + gameName);

        settings.putString("mainWindowText", "Hello World!");
        settings.putString("mainWindowTextureName", "");
        settings.putString("name", "Game");
        settings.putString("atlasName", "assets");
        settings.putFloat("screenWidth", 960f);
        settings.putFloat("screenHeight", 960f);
        settings.putString("textureAtlasPath", "/assets.atlas");
        settings.putFloat("volume", 1.0f);
        settings.putBoolean("playMusic", false);
        settings.putString("gameKind", GameKind.ZRPG.name());
        settings.putString("tileKind", TileKind.ORTHOGONAL.name());
        settings.putBoolean("loadAssetsOnStart", false);
        settings.putBoolean("loadEditorAssetsOnStart", true);
        settings.putString("unpackedGameAssetsPath", "/AssettsUnpacked/Editor/Editor Assets");
        settings.putString("unpackedEditorAssetsPath", "/AssettsUnpacked/Editor/Editor Assets");
        settings.putString("assetsPath", "./packedAssets/Game");
        settings.putString("gameMode", GameMode.ZRPG_2D_ORTHO.toString());


    }

    public Preferences getPreferences() {
        return Gdx.app.getPreferences("preferences" + gameName);

    }

    public GameInputKeys getGameInputKeys() {
        return gameInputKeys;
    }

    public void addPreference(Stat stat){
        String settingAsString=jsonLoader.getJson().prettyPrint(stat);

        settings.putString(stat.getName(), settingAsString);

    }

    public Stat getPreference(String name){

       String setting= settings.getString(name);
       Stat stat=null;
        if(setting!=null){

            stat= jsonLoader.getJson().fromJson(Stat.class, setting);

        }
        return stat;


    }





    public void setGameInputKeys(GameInputKeys gameInputKeys) {
        this.gameInputKeys = gameInputKeys;
    }

    public int getNumberOfPreferences(){

        return  settings.get().size();
    }



}



