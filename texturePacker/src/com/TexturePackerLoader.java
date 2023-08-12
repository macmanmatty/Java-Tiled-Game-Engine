import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class TexturePackerLoader {

    /**
     * class to run texture packer uses older libGDX versions to run properly
     */
    public TexturePackerLoader() {
    }
    static String atlasFileOutputPath ="/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine/android/assets/testAssets/Atlas";
    static String atlasFileInputPath ="/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine/testAssetsUnpacked";
    static  String atlasFileName ="testAssets.atlas";
    /**
     * main method used to call the pack method to pack textures.
     * @param args
     */
    public static void main(String [] args){
        pack(new Settings());
        System.out.println("packed");
    }
    public static  void pack(Settings settings){
        settings.flattenPaths=true;
        TexturePacker.process(settings, atlasFileInputPath, atlasFileOutputPath, atlasFileName);

    }
}
