package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.esotericsoftware.kryo.Kryo;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Input.GameInput;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputMultiplexer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.LoadingException;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GamePrefecences;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureAtlasPacker;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.Components.AnimatableSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.TiledMapSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.Entity.EntityKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.Entity.LandSquareTileKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World.BuildingKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World.MapKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World.WorldKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Skins.NamedSkin;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIException;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.GameBaseClasses.Loaders.World.WorldReader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.World.WorldWriter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class GameAssets { // class that holds  the assett assetManager and game instance for changing screens
    // loading world  and assetts Never createFromJson anymore than one instance of this.
    private ArrayList<TextureAtlasRegionNames> regionNames= new ArrayList<TextureAtlasRegionNames>();
    private World world;
    private TextureAtlas currentAtlas;
    private Screen previousScreen;
    private Skin skin; // the current skin
    private final  AssetManager assetManager;
    private  final Game game; // the games instance
    private MapDraw mapDraw;
    private static  final JsonLoader jsonLoader= new JsonLoader();
    private ObjectMap<String , NamedScreen> screens= new ObjectMap<>();
    private TextureAtlasPacker textureAtlasPacker= new TextureAtlasPacker();
    private GamePrefecences settings;
    private final static GameInput gameInput= new GameInput();

    private final Kryo kryo= new Kryo();
    public GameAssets( String gameName, Game game){
         assetManager = new AssetManager();
        this.game = game;
       settings = new GamePrefecences(gameName);
        ;
//        if(settings.getPreferences().getBoolean("loadAssetsOnStart")==true){
//            String assetsPath=settings.getPreferences().getString("unpackedGameAssetsPath");
//            TexturePacker.Settings settings= new TexturePacker.Settings();
//            TexturePacker.process(settings, assetsPath, "/assets", "assets");
//
//
//    }
//        if(settings.getPreferences().getBoolean("loadEditorAssetsOnStart")==true){
//
//        String assetsPath=settings.getPreferences().getString("unpackedEditorGameAssetsPath");
//        TexturePacker.Settings settings= new TexturePacker.Settings();
//        TexturePacker.process(settings, assetsPath, "/editorAssets", "editorAssets");
//
//    }



}
     public void setup(){

        // register seralise de seralize classes
         this.skin= loadInternalSkin("GameUI/blackTower", "GameUI/blackTower");
         kryo.register(TiledMap.class, new TiledMapSerializer(true, this));
         kryo.register(Entity.class,  new EntityKryoSerializer(this));
         kryo.register(LandSquareTile.class, new LandSquareTileKryoSerializer(this));
         kryo.register(AnimatableComponent.class, new AnimatableSerializer(this));
        kryo.register(World.class, new WorldKryoSerializer(this));
       kryo.register(LandMap.class, new MapKryoSerializer(this));
        kryo.register(Building.class, new BuildingKryoSerializer(this));



     }

     private void setPreferences(){


     }

     // loads a json file if doesn't exist creates it.
     public<T> T loadOrCreateJsonFile(String path, String name, Class<T> objectType) throws IOException {
         File file= new File(path);
         if(!file.exists()) {
             FileUtilities.createFile(path, name);
         }
         return jsonLoader.loadObject(objectType, path);

     }
     

    public void showPreviousScreen(){ // changes the screen back to the screen that was displayed before
       game.setScreen(previousScreen);
    }



    /**
     * Loads a world Object
     * @param path  the path to the game file
     * @return World the game object
     */


    public World loadGame(String path)  { // deserlalizies  the world game object
       WorldReader worldReader= new WorldReader(this);
        world=worldReader.loadWorld(path);
        return world;
 }






    /**
     * Loads a libgdx UI Skin Internally
     * @param skinName  the name of the skin
     * @param  atlasName the name of the atlas for the given skin
     * @return Skin a libgdx UI  Skin
     */

    public Skin loadInternalSkin(String skinName, String atlasName) { // loads a skin  with given name and texture atlas internally from the app
     TextureAtlas atlas = new TextureAtlas("skins/"+atlasName+".atlas");
     NamedSkin skin= new NamedSkin (Gdx.files.internal("skins/"+skinName+".json"), atlas);
   assetManager.load("skins/"+skinName+".json", Skin.class, new SkinLoader.SkinParameter("skins/"+atlasName+".atlas"));

   if(skin==null){

       return assetManager.get("blackTower", Skin.class);


   }

   return skin;
 }


    /**
     * Loads a libgdx UI Skin Internally
     * @param skinName  the name of the skin
     * @return Skin a libgdx UI  Skin
     */

    public Skin loadInternalSkin(String skinName) { // loads a skin  with given name and texture atlas
        TextureAtlas atlas = new TextureAtlas("skins/"+skinName+".atlas");
        Skin skin= new Skin (Gdx.files.internal("skins/"+skinName+".json"), atlas);
        assetManager.load("skins/"+skinName+".json", Skin.class, new SkinLoader.SkinParameter("skins/"+skinName+".atlas"));

        if(skin==null){

            return assetManager.get("blackTower", Skin.class);

        }

        return skin;
    }


    /**
     * loads a LIBGX UI skin externally
     * @param skinName  the name of the skin
     * @param  path the path to the skin
     * @param  atlasName the name of the atlas for the given skin
     * @return Skin a libgdx UI  Skin
     */
    public Skin loadExternalSkin(String skinName, String atlasName, String path){ // loads an external  skin  with given name ,  texture atlas. file path
    String fullAtlasPath=path+ FileUtilities.getFileSeparator()+atlasName;
    String fullSkinPath=path+ FileUtilities.getFileSeparator()+skinName;
    TextureAtlas atlas = new TextureAtlas(fullAtlasPath);
    Skin skin= new Skin (Gdx.files.absolute(fullSkinPath), atlas);
    assetManager.load(fullSkinPath, Skin.class, new SkinLoader.SkinParameter(fullAtlasPath));
    return skin;
}
 public Skin getSkin(String name){
  Skin skin= assetManager.get(name, Skin.class)  ;
     return skin;
 }



    public void saveGame(World world, String path){ // serailizes  a game world instance  using binary serialaztion
        new WorldWriter(this).saveWorld(world, path);
    }
    public AtlasRegion getBitMaskedAtlasRegion (String atlasName, String kind, int bitmaskNumber, float setNumber) { // returns WoodWand texture region based on  which set and bitmask numer is used and set number
        String index= kind+ bitmaskNumber+","+setNumber;
      TextureAtlas atlas = assetManager.get(atlasName,   TextureAtlas.class);
      AtlasRegion region=atlas.findRegion(index);
          return region;
    }
    public Array<AtlasRegion> getRegions( String atlasName, String name){
        TextureAtlas atlas = assetManager.get(atlasName,   TextureAtlas.class);
        Array<AtlasRegion> regions= atlas.findRegions(name);
        return regions;
    }
    /**
     * / returns texture region based on a given name from a given atlas name loaded into the asset manager if it exists else returns null
     * @param atlasRegionName the name of the atlasRegion
     * @param  atlasName the name of libgdx texture atlas the atlas the region is in
     * @return AtlasNamedAtlasRegion
     */
    public AtlasNamedAtlasRegion getAtlasRegionByName(String atlasRegionName,  String atlasName){
         TextureAtlas atlas= assetManager.get("textureAtlases/"+atlasName+".atlas", TextureAtlas.class);
         AtlasRegion region=atlas.findRegion(atlasRegionName);
         if(region!=null) {
             return new AtlasNamedAtlasRegion(region, atlasName);
         }
         else{

             return  null;

         }
    }


    // get a region from the current loaded texture  atlas
    public AtlasNamedAtlasRegion getAtlasRegionByName(String atlasRegionName){ // returns texture region based on a name from the current loaded atlas
        return   new AtlasNamedAtlasRegion(currentAtlas.findRegion(atlasRegionName));
    }
    public void addRegionToCurrentAtlas( String name, TextureRegion region){ // adds a region to the current atlas;
        currentAtlas.addRegion(name, region);
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }


    /**
     * / loads tiles TMXTileMap from a given file path
     * @param path  the path to the map
     * @return TiledMap
     */
    public TiledMap loadExternalTMXMap(String path) { // loads tiles TMXTileMap from a given file path
        TiledMap map = new TmxMapLoader().load(path);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load(path, TiledMap.class);
        return map;
    }
    public TiledMap getMap(String name) { // returns a tiled Map based on the name given
        TiledMap map = assetManager.get("maps/"+name, TiledMap.class);
        return map;
    }
    public Texture loadTexture(String name) { // loads a texture based on name
        Texture  map = new Texture("textureAtlases/"+name);
        assetManager.load("textureAtlases/"+name, Texture.class);
        return map;
    }
    public TextureAtlas loadInternalTextureAtlas(String name) { // loads a texture atlas bed  the atlas name;
        String path="textureAtlases/"+name+".atlas";
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(path));
        assetManager.load(path, TextureAtlas.class);
        return atlas;
    }
    public TextureAtlas loadTextureAtlasByPath( String path) { // loads a texture atlas bed  the atlas name;
        TextureAtlas atlas = new TextureAtlas(path);
        assetManager.load(path, TextureAtlas.class);
        return atlas;
    }
    public TextureAtlas getTextureAtlas(String atlasName){ // returns a texture atlas based on name
        TextureAtlas atlas= assetManager.get("textureAtlases/"+atlasName, TextureAtlas.class);
        return atlas;
    }
    public TextureAtlas loadTextureAtlasFromExternalFile(String path) { // loads a texture atlas based on the give path.
        TextureAtlas  atlas = new TextureAtlas(path);
        assetManager.load(path, TextureAtlas.class);
        return atlas;
    }
    public void finishLoading(){
         assetManager.finishLoading();
    }


    public AtlasNamedAtlasRegion addAtlasRegionToAtlas(TextureRegion region, String regionName, String atlasName){ // adds a nex texture region to WoodWand given texture atlas
        TextureAtlas atlas= assetManager.get("textureAtlases/"+atlasName, TextureAtlas.class);
        AtlasRegion region2=  atlas.addRegion(regionName, region);
        return new AtlasNamedAtlasRegion(region2, atlasName);
    }
    public AtlasNamedAtlasRegion addAtlasRegionToAtlas(AtlasNamedAtlasRegion atlasNamedAtlasRegion, int xOffeset, int yOffset, int width, int height, String regionName, String atlasName){ // does same  but with additional parameters
        NamedTextureAtlas atlas=  assetManager.get("textureAtlases/"+atlasName, NamedTextureAtlas.class);
        AtlasNamedAtlasRegion region2=  atlas.addRegion(regionName, (AtlasNamedAtlasRegion)atlasNamedAtlasRegion);
       return region2;
    }
    // creates a page and adds it to a  paged texture atlas
    public NamedTextureAtlas createAndAddPageToPagedTextureAtlas(String atlasName, Array<AtlasNamedAtlasRegion> regions){
        NamedTextureAtlas atlas=  assetManager.get("textureAtlases/"+atlasName, NamedTextureAtlas.class);
        int size=regions.size;
        for(int count=0; count<size; count++){
            AtlasNamedAtlasRegion atlasNamedAtlasRegion=regions.get(count);
            String name=atlasNamedAtlasRegion.name;
            // if a  texture has no name give it a random one
            if(name==null || name.isEmpty()){
                name= UUID.randomUUID().toString();
               atlasNamedAtlasRegion.name=name;
            }
            atlas.addRegion(atlasNamedAtlasRegion.name, atlasNamedAtlasRegion);
        }
       return  atlas;
    }

    public TextureAtlasRegionNames getAssettNames(  String path) {
        TextureAtlasRegionNames names = jsonLoader.loadObject(TextureAtlasRegionNames.class, path);
        return names;
    }
    // set the game screen to a new screen instance and add it to the map  of screens
    public void setScreen(NamedScreen screen){
        screens.put(screen.getName(), screen);
        game.setScreen(screen);
    }
    // gets a screen instance by name
    public void setScreen(String screenName){
        Screen screen=screens.get(screenName);
        if(screen!=null) {
            game.setScreen(screen);
        }
    }
          public <T> T  loadObject(String filePath, Class<T> thingClass){
        T object= jsonLoader.loadObject(thingClass, filePath);
        return  object;
    }
    public Object loadArrayList(String filePath,  Class typeClass){
        Object object= jsonLoader.loadArrayFromFile( typeClass, filePath);
        return  object;
    }
    public void saveObject(Object object, String path, boolean append){
        jsonLoader.writeObjectToFile(object, path, append);
    }
    public void saveTiledMap(TiledMap map, int xSize, int ySize , String path, String atlasName){
        jsonLoader.saveTiledMap(map, xSize, ySize, path,  atlasName, this);
    }
    public TiledMap loadSavedTiledMap( String path){
       TiledMap map= jsonLoader.loadTiledMap(path, this);
       return map;
    }
    public ArrayList<TextureAtlasRegionNames> getRegionNames() {
        return regionNames;
    }
    public void addTextUreAtlasRegionName( TextureAtlasRegionNames regionName){
         regionNames.add(regionName);
    }
    public void dispose(){
        assetManager.dispose();
        previousScreen.dispose();
        currentAtlas.dispose();
        mapDraw.dispose();
        Values<NamedScreen> namedScreens=screens.values();
        while(namedScreens.hasNext()){
            NamedScreen screen= (NamedScreen) namedScreens.next();
            screen.dispose();
        }
        game.dispose();
    }
    public World getWorld() {
        return world;
    }

    public void saveTextureAtlas( String path,  NamedTextureAtlas atlas, int pageWidth, int pageHeight, int padding) throws IOException {
            textureAtlasPacker.packAtlas(path, atlas.getAtlasFileName(), atlas, pageWidth, pageHeight, padding);
    }
    public void setWorld(World world){

        this.world = world;
        this.mapDraw= new MapDraw( this,world, true);
        mapDraw.setPlayer(new ZRPGPlayer(world, world.getPlayer()));
    }

    public void showGame(){
        game.setScreen(mapDraw);

    }


    public Skin getDefaultSkin() {
        return skin;
    }

    public MapDraw getMapDraw() {
        return mapDraw;
    }
    public static  JsonLoader getJsonLoader() {
        return jsonLoader;
    }
    public void exit() {
        dispose();
        System.exit(0);
    }


    public GamePrefecences getSettings() {
        return settings;
    }

    public void setSettings(GamePrefecences settings) {
        this.settings = settings;
    }

    public Kryo getKryo() {
        return kryo;
    }


    public static  GameInput getGameInput() {
        return gameInput;
    }
}
