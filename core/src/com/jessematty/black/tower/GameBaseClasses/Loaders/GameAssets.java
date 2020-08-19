package com.jessematty.black.tower.GameBaseClasses.Loaders;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureAtlasPacker;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.GameBaseClasses.Loaders.World.WorldReader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.World.WorldWriter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedScreen;
import com.jessematty.black.tower.Maps.World;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
public class GameAssets { // class that holds  the assett assetManager and game instance for changing screens
    // loading world  and assetts Never createFromJson anymore than one instance of this.
    private ArrayList<TextureAtlasRegionNames> regionNames= new ArrayList<TextureAtlasRegionNames>();
    private World world;
    private TextureAtlas currentAtlas;
    private Screen previousScreen;
    private Skin skin;
    private final  AssetManager assetManager;
    private  final Game game; // the games instance
    private MapDraw mapDraw;
    private JsonLoader jsonLoader= new JsonLoader();
    private ObjectMap<String , NamedScreen> screens= new ObjectMap<>();
    private TextureAtlasPacker textureAtlasPacker= new TextureAtlasPacker();
    public GameAssets(Game game){
         assetManager = new AssetManager();
        this.game = game;
     }
     public void setup(){
         this.skin= loadInternalSkin("GameUI/blackTower", "GameUI/blackTower");
     }
    public void showPreviousScreen(){ // changes the screen back to the screen that was displayed before
       game.setScreen(previousScreen);
    }
   
    public ArrayList<TextureAtlasRegionNames> loadNames(String path){
        TextureAtlasRegionNames  names= getAssettNames(path);
        regionNames.addAll(Arrays.asList(names));
        return regionNames;
    }
    public World loadGame(String path) throws MapLoadingExeception { // deserlalizies  the world game object
        com.jessematty.black.tower.GameBaseClasses.Loaders.World.WorldReader worldReader= new WorldReader(this);
        setWorld(world);
        return world;
 }
 public Skin loadInternalSkin(String skinName, String atlasName){ // loads a skin  with given name and texture atlas
     TextureAtlas atlas = new TextureAtlas("skins/"+atlasName+".atlas");
     Skin skin= new Skin (Gdx.files.internal("skins/"+skinName+".json"), atlas);
   assetManager.load("skins/"+skinName+".json", Skin.class, new SkinLoader.SkinParameter("skins/"+atlasName+".atlas"));
   return skin;
 }
 
    public Skin loadInternalSkin(String skinName){ // loads a skin  with given name and texture atlas
        TextureAtlas atlas = new TextureAtlas("skins/"+skinName+".atlas");
        Skin skin= new Skin (Gdx.files.internal("skins/"+skinName+".json"), atlas);
        assetManager.load("skins/"+skinName+".json", Skin.class, new SkinLoader.SkinParameter("skins/"+skinName+".atlas"));
        return skin;
    }
    public Skin loadExternalSkin(String skinName, String atlasName, String path){ // loads a skin  with given name and texture atlas
    String fullAtlasPath=path+ FileUtilities.getFileSeparator()+atlasName;
    String fullSkinPath=path+ FileUtilities.getFileSeparator()+skinName;
    TextureAtlas atlas = new TextureAtlas(fullAtlasPath);
    Skin skin= new Skin (Gdx.files.absolute(fullSkinPath), atlas);
    assetManager.load(fullSkinPath, Skin.class, new SkinLoader.SkinParameter(fullAtlasPath));
    return skin;
}
 public  Skin getSkin(String name){
     Skin skin= assetManager.get(name, Skin.class)  ;
     return skin;
 }



    public void saveGame(World world, String path){ // serailizes  a game world instance  using binary serialaztion
        new WorldWriter(this, world).saveWorld(path);
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
    }// returns texture region based on a given name from a given atlas name loaded into the asset manager if it exists else returns null
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
    public AtlasNamedAtlasRegion getAtlasRegionByName(String atlasRegionName){ // returns texture region based on a name from the current loaded atlas
        return   new AtlasNamedAtlasRegion(currentAtlas.findRegion(atlasRegionName));
    }
    public void addRegionToCurrentAtlas( String name, TextureRegion region){ // adds a region to the current atlas;
        currentAtlas.addRegion(name, region);
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }
   public Texture loadTextureFromFile(){ // loads WoodWand texture froma file
        File image=null;
        Texture texture=null;
        JFrame frame = new JFrame();
        JFileChooser chooser= new JFileChooser();
        frame.add(chooser);
        frame.toFront();
        frame.setVisible(true);
        File file=chooser.getSelectedFile();
        String path=file.getPath();
        String extension= getExtensionOfFile(file);
        if(extension.equalsIgnoreCase("png")) {
            assetManager.load(path, Texture.class);
        }
else if(extension.equalsIgnoreCase("atlas") ){
           assetManager.load(path, TextureAtlas.class);
       }
       return texture;
    }
public TiledMap loadTMXMapFromFile() { // loads WoodWand tiled landSquareTileMap from WoodWand file
    File image = null;
    Texture texture = null;
    JFrame frame = new JFrame();
    JFileChooser chooser = new JFileChooser();
    frame.add(chooser);
    frame.toFront();
    frame.setVisible(true);
    File file = chooser.getSelectedFile();
    String path = file.getPath();
    String extension = getExtensionOfFile(file);
    if (extension.equalsIgnoreCase("tmx")) {
         TiledMap map = new TmxMapLoader().load(path);
         assetManager.load(path, TiledMap.class);
        assetManager.finishLoading();
        return map;
    }
return null;
}
    public TiledMap loadExternalTMXMap(String path) { // loads tiles TMXTileMap froma given name
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
    public void splitTexture(int width, int height, Texture texture, String atlasName, String textureName){
         AtlasRegion region = new AtlasRegion(texture, 0, 0 , texture.getWidth(), texture.getHeight());
       AtlasRegion [] []regionSplit= (AtlasRegion[][]) region.split(width, height);
       int xsize=regionSplit.length;
       int ysize=regionSplit[0].length;
       for (int countx=0; countx<xsize; countx++){
           for (int county=0; county<ysize; county++){
               addAtlasRegionToAtlas(regionSplit[countx][county], textureName+"."+countx+"."+county,atlasName );
           }
           }
    }
    public void saveAssettNames(TextureAtlasRegionNames name , String path){
        jsonLoader.writeObjectToFile(name, path,  false);
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
    public  String getExtensionOfFile(File file) // returns the extension of given file like .png or .tmx ECT.
        {
        String fileExtension="";
        // Get file Name first
        String fileName=file.getName();
        // If fileName do not contain "." or starts with "." then it is not a  valid file
        if(fileName.contains(".") && fileName.lastIndexOf(".")!= 0)
        {
        fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
        }
        return fileExtension;
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
        game.dispose();
    }
    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
        this.mapDraw= new MapDraw( this,world, true);
        //this.currentAtlas= loadTextureAtlasByPath(world.getTextureAtlasPath());
    }
    public void saveTextureAtlas( String path,  NamedTextureAtlas atlas, int pageWidth, int pageHeight, int padding) throws IOException {
            textureAtlasPacker.packAtlas(path, atlas.getAtlasFileName(), atlas, pageWidth, pageHeight, padding);
    }
    public void showGame( ZRPGPlayer player){
        mapDraw.setPlayer(player);
        game.setScreen(mapDraw);
    }
    public Skin getDefaultSkin() {
        return skin;
    }
    public static String getOperatingSystem(){
        return System.getProperties().getProperty("os.name").toLowerCase();
    }
    public MapDraw getMapDraw() {
        return mapDraw;
    }
    public JsonLoader getJsonLoader() {
        return jsonLoader;
    }
    public void exit() {
        dispose();
        System.exit(0);
    }
}
