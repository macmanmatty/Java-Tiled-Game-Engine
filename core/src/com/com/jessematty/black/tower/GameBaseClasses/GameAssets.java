package com.jessematty.black.tower.GameBaseClasses;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.badlogic.gdx.utils.OrderedMap;
import com.esotericsoftware.kryo.Kryo;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Input.GameInput;
import com.jessematty.black.tower.GameBaseClasses.Logging.ScreenLogger;
import com.jessematty.black.tower.GameBaseClasses.Screens.NamedScreen;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Components.AnimatableSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.EntityKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.LandSquareTileKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.ObjectMap.ObjectMapSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.ObjectMap.OrderedMapSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.World.BuildingKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.World.MapKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.World.WorldKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureAtlasPacker;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.TiledMapKryoSerializer;
import com.jessematty.black.tower.GameBaseClasses.Serialization.World.WorldReader;
import com.jessematty.black.tower.GameBaseClasses.Serialization.World.WorldWriter;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GamePrefecences;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Skins.NamedSkin;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.IOException;
import java.util.UUID;
/**
 * // class that holds  the libGDX assetManager and game instance for changing screens
 *   has Kryo instance  for  loading  and saving world  you should have anymore than one instance of this class.
 */
public class GameAssets implements Disposable {
    /**
     *  the current game world
     */
    private World world; // the game world
    /**
     *  the default game skin loaded skin
     */
    private Skin defaultSkin;
    /**
     *  the current loaded skin
     *  changes with each  map
     */
    private Skin currentSkin;
    /**
     * The Style Used By the libGDX skin
     */
    String currentSkinStyle ="Brick";
    /**
     * libGDX assetManager  @see AssetManager
     */
    private final  AssetManager assetManager;
    /**
     * libGDX Game instance used for switching screens and openGL / openAL stuff
     */
    private  final Game game; // the games instance
    /**
     * screen class that draws the / show the game
     */
    private MapDraw mapDraw;
    /**
     * object for loading json based objects
     */
    private  final JsonLoader jsonLoader= new JsonLoader();
    /**
     * map of all screens currently loaded in the game
     */
    private ObjectMap<String , NamedScreen> screens= new ObjectMap<>();
    /**
     *  the games settings
     */
    private GamePrefecences settings;
    /**
     * the currently displayed libGDX screen
     */
    private Screen currentScreen;
    /**
     * the previously displayed libGDX screen
     */
    private Screen previousScreen;
    /**
     * the games input class
     */
    private final static GameInput gameInput= new GameInput();
    /**
     * the games input class
     */
    private TextureAtlas currentTextureAtlas= new TextureAtlas();
    /**
     * the Kryo object for saving and loading the game
     */
    private final Kryo kryo= new Kryo();
    private final static ScreenLogger screenLogger = new ScreenLogger();
    public GameAssets( String gameName, Game game){
         assetManager = new AssetManager();
        this.game = game;
       settings = new GamePrefecences(gameName);
    }
    /**
     * loads the default game  libGDX skin  and registers the default games saving classes with kryo
     */
    public void setup(){
        // register serializing classes
        Gdx.input.setInputProcessor(gameInput.getLockableInputMultiplexer());
        this.currentSkin = loadInternalSkin("GameUI/blackTower", "GameUI/blackTower");
        this.defaultSkin= currentSkin;
        getScreenLogger().setSkin(currentSkin);
         kryo.register(TiledMap.class, new TiledMapKryoSerializer( true,  this));
         kryo.register(Entity.class,  new EntityKryoSerializer(this));
         kryo.register(LandSquareTile.class, new LandSquareTileKryoSerializer(this));
         kryo.register(AnimatableComponent.class, new AnimatableSerializer(this));
        kryo.register(World.class, new WorldKryoSerializer(this));
       kryo.register(LandMap.class, new MapKryoSerializer(this));
        kryo.register(Building.class, new BuildingKryoSerializer(this));
        kryo.register(ObjectMap.class, new ObjectMapSerializer());
        kryo.register(OrderedMap.class, new OrderedMapSerializer());
    }
    public void showPreviousScreen(){ // changes the screen back to the screen that was displayed before
       game.setScreen(previousScreen);
    }
    /**
     * Loads a world Object form a file
     * @param path  the path to the game file
     * @return World the game object
     */
    public World loadGame(String path)  {
       WorldReader worldReader= new WorldReader(this);
        world=worldReader.loadWorld(path);
        return world;
 }
    /**
     * Loads a libGDX UI Skin Internally
     * @param skinName  the name of the skin
     * @param  atlasName the name of the atlas for the given skin
     * @return Skin a libGDX UI  Skin
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
     * Loads a libGDX UI Skin Internally
     * @param skinName  the name of the skin
     * @return Skin a libGDX UI  Skin
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
     * loads a libGGX UI skin externally
     * @param skinName  the name of the skin
     * @param  path the path to the skin
     * @param  atlasName the name of the atlas for the given skin
     * @return Skin a libGDX UI  Skin
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
  
    /**
     * serializes  a game world instance  using kryo
     * @param world the world to save
     * @param path the path to save it to
     */
    public void saveGame(World world, String path) throws IOException {
        new WorldWriter(this).saveWorld(world, path);
    }
    /**
     * serializes  a game world instance  using kryo and packs it texture atlas using the TextureAtlasPackerClass
     * @param world the world to save
     * @param path the path to save it to
     * @param packWidth the max width  to use for the images with texture atlas should be  a power of two
     * @param packHeight the max height  to use for the images with texture atlas should be  a power of two
     */
    public void saveGameWithAssets( World world, String path,   int packWidth, int packHeight) throws IOException {
      WorldWriter worldWriter=  new WorldWriter(this);
              worldWriter.saveWorldWithAssets( world , path, packWidth, packHeight);
    }
    /***
     *  returns  a atlas  region from a  currently LOADED texture atlas  based on  which set and bitmask number is used and set number
     * @param atlasName the name of the atlas to get the texture from
     * @param baseRegionName the name fo the atlas region minus the mask number as the set number
     * @param bitmaskNumber the bit mask number for the region
     * @param setNumber the set number of the region
     * @return AtlasRegion may be null if no region was found
     */
    public AtlasRegion getBitMaskedAtlasRegion (String atlasName, String baseRegionName, int bitmaskNumber, float setNumber) {
        String atlasRegionName= baseRegionName+ bitmaskNumber+","+setNumber;
      TextureAtlas atlas = assetManager.get(atlasName,   TextureAtlas.class);
      AtlasRegion region=atlas.findRegion(atlasRegionName);
          return region;
    }
    /**
     * / returns AtlasNamedAtlasRegion From a Currently LOADED  based on a given name from a given atlas name loaded into the asset manager if it exists else returns null
     * @param atlasRegionName the name of the atlasRegion
     * @param  atlasName the name of libGDX texture atlas the atlas the region is in
     * @return  AtlasNamedAtlasRegion may be null if no region exists
     */
    public AtlasNamedAtlasRegion getInternalAtlasRegionByName(String atlasRegionName,  String atlasName){
        TextureAtlas atlas= assetManager.get("textureAtlases/"+atlasName+".atlas", TextureAtlas.class);
        AtlasRegion region=atlas.findRegion(atlasRegionName);
        if(region!=null) {
            return new AtlasNamedAtlasRegion(region, atlasName);
        }
        else{
            return  null;
        }
    }
    /**
    /**
     * / returns AtlasNamedAtlasRegion From a Currently LOADED  based on a given name from a given atlas name loaded into the asset manager if it exists else
     * checks the default loaded atlas if it can't found there returns null
     * @param atlasRegionName the name of the atlasRegion
     * @param  atlasPath the name of libGDX texture atlas the atlas the region is in
     * @return  AtlasNamedAtlasRegion may be null if no region exists
     */
    public AtlasNamedAtlasRegion getAtlasRegionByName(String atlasRegionName,  String atlasPath){
        AtlasRegion region=null;
        if(assetManager.isLoaded(atlasPath)) {
            TextureAtlas atlas = assetManager.get(atlasPath, TextureAtlas.class);
           region = atlas.findRegion(atlasRegionName);
        }
         if(region!=null) {
             return new AtlasNamedAtlasRegion(region, atlasPath);
         }
         else{
             return  getAtlasRegionByName(atlasRegionName);
         }
    }
    /**
     * / returns a AtlasNamedAtlasRegion based on a given name from the current  texture atlas  loaded into the asset manager if it exists else returns null
     * @param atlasRegionName the name of the atlasRegion
     * @return AtlasNamedAtlasRegion may be null if no region exists
     */    public AtlasNamedAtlasRegion getAtlasRegionByName(String atlasRegionName){ // returns texture region based on a name from the current loaded atlas
        AtlasRegion atlasRegion=currentTextureAtlas.findRegion(atlasRegionName);
        if(atlasRegion==null){
            System.out.println("Region is Null: "+atlasRegionName);
            return  null;
        }
         return   new AtlasNamedAtlasRegion(atlasRegion);
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
        return map;
    }
    /**
     *  retrieves a LOADED  libGDX  tiled map from the asset manager
     * @param name the name of the map to retrieve
     * @return
     */
    public TiledMap getMap(String name) { // returns a tiled Map based on the name given
        TiledMap map = assetManager.get("maps/"+name, TiledMap.class);
        return map;
    }
    /**
     *  loads a texture internally from  the textureAtlases Folder
     * @param name the name of the  texture to load
     * @return
     */
    public Texture loadTexture(String name) { // loads a texture based on name
        Texture  map = new Texture("textureAtlases/"+name);
        assetManager.load("textureAtlases/"+name, Texture.class);
        return map;
    }
    /**
     *  returns a libGDX texture atlas located  externally form the jar file
     * @param path  the  full path to the texture atlas to load
     * @return TextureAtlas the loaded texture atlas
     */
    public TextureAtlas loadExternalTextureAtlas(String path) { // loads a texture atlas based on the give path.
        NamedTextureAtlas atlas = new NamedTextureAtlas(Gdx.files.absolute(path));
        atlas.setAtlasFileName(path);
        assetManager.load(path, TextureAtlas.class);
        return atlas;
    }
    /**
     *  returns a libGDX texture atlas located  externally form the jar file
     * @param internalPath the internal   path found under android/assets to the texture atlas
     * @return TextureAtlas the loaded texture atlas
     */
    public NamedTextureAtlas loadInternalTextureAtlas(String internalPath) { // loads a texture atlas based on the give path.
        NamedTextureAtlas atlas = new NamedTextureAtlas(internalPath);
        atlas.setAtlasFileName(internalPath);
        assetManager.load(internalPath, TextureAtlas.class);
        return atlas;
    }
    public void finishLoading(){
         assetManager.finishLoading();
    }
    public float getProgress(){
       float progress=  assetManager.getProgress();
       return  progress;
    }

   public void  loadTextureAtlasAsync(final String path){
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                assetManager.load(path, TextureAtlas.class);
                finishLoading();
            }
        };
        Gdx.app.postRunnable(runnable);
   }
    /**
     *
     * @param region the region to add
     * @param regionName the name you wish to give the region
     * @param atlasName the atlas to add it to
     * @return
     */
    public AtlasNamedAtlasRegion addAtlasRegionToAtlas(TextureRegion region, String regionName, String atlasName){ // adds a nex texture region to a given texture atlas
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
    /** sets the game screen to a new screen instance and adds it to the map  of screens
     *
     * @param screen the screen to change to
     */
    public void setScreen(NamedScreen screen){
        screens.put(screen.getName(), screen);
        previousScreen=currentScreen;
        currentScreen=screen;
        game.setScreen(screen);
    }
    /**
     *
     * @param screenName sets the currenbt screen to a a screen with given name located in the map of game screens
     * @throws IllegalArgumentException if no screen with given name is found.
     */
    public void setScreen(String screenName){
        Screen screen=screens.get(screenName);
        if(screen!=null) {
            game.setScreen(screen);
            previousScreen=currentScreen;
            currentScreen=screen;
        }
        else{
            throw new IllegalArgumentException("No Screen With Name Found");
        }
    }
    public <T> T  loadInternalObject(String filePath, Class<T> thingClass){
        T object= jsonLoader.loadInternalObject(thingClass, filePath);
        return  object;
    }
    /**
     *  the libGDX dispose method called before closing the game to prevent memory leaks
     */
    public void dispose(){
        world.dispose();
        Values<NamedScreen> namedScreens=screens.values();
        while(namedScreens.hasNext()){
            NamedScreen screen= (NamedScreen) namedScreens.next();
            screen.dispose();
        }
        mapDraw.dispose();
        assetManager.dispose();
        game.dispose();
    }
    public World getWorld() {
        return world;
    }
    public void saveTextureAtlas( String saveDirectory,  NamedTextureAtlas atlas, int pageWidth, int pageHeight, int padding) throws IOException {
            new TextureAtlasPacker().packAtlas(saveDirectory, atlas.getAtlasFileName(), atlas, pageWidth, pageHeight, padding);
    }
    public void saveTextureAtlas( String saveDirectory,  TextureAtlas atlas, String name,  int pageWidth, int pageHeight, int padding) throws IOException {
        new TextureAtlasPacker().packAtlas(saveDirectory, name, atlas, pageWidth, pageHeight, padding);
    }
    /**
     * set the games world and the current textureAtlas to the world's texture atlas  and creates a new MapDraw class that renders the world
     * @param world the world to set to
     */
    public void setWorld(World world){
        this.world = world;
        this.mapDraw= new MapDraw( this, true);
        mapDraw.setWorld(world);
        mapDraw.showCurrentWorld();
        mapDraw.setPlayer(new ZRPGCharacter( world, world.getPlayer()));
    }
    public void showGame(){
        game.setScreen(mapDraw);
    }
    public Skin getDefaultSkin() {
        return currentSkin;
    }
    public MapDraw getMapDraw() {
        return mapDraw;
    }
    public  JsonLoader getJsonLoader() {
        return jsonLoader;
    }
    public void exit() {
        dispose();
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
    public  static GameInput getGameInput() {
        return gameInput;
    }
    public Skin getCurrentSkin() {
        return currentSkin;
    }
    public void setCurrentSkin(Skin currentSkin) {
        this.currentSkin = currentSkin;
    }
    public TextureAtlas getCurrentTextureAtlas() {
        return currentTextureAtlas;
    }
    public void setCurrentTextureAtlas(TextureAtlas currentTextureAtlas) {
        this.currentTextureAtlas = currentTextureAtlas;
    }
    public String getCurrentSkinStyle() {
        return currentSkinStyle;
    }
    public void setCurrentSkinStyle(String currentSkinStyle) {
        this.currentSkinStyle = currentSkinStyle;
    }
    public static ScreenLogger getScreenLogger() {
        return screenLogger;
    }
}
