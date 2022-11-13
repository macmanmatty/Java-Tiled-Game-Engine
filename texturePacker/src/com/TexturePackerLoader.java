import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class TexturePackerLoader {

    /**
     * class to run texture packer uses older libGDX versions to run properly
     */
    public TexturePackerLoader() {
    }
     //static String atlasFileOutputPath =System.getenv("atlasFileOutputPath");
     static String atlasFileInputPath ="/Users/jessematty/AndroidStudioProjects/BlackTowerHTML/TestAssets/";
    static String atlasFileOutputPath ="/Users/jessematty/AndroidStudioProjects/BlackTowerHTML/android/assets/textureAtlases/testAssets";
    static  String atlasFileName ="testAssets.atlas";

  //  static String atlasFileInputPath =System.getenv("atlasFileInputPath");
    // static  String atlasFileName =System.getenv("atlasFileName");

    public static void main(String [] args){
        pack(new Settings());
    }
    public static  void pack(Settings settings){
        TexturePacker.process(settings, atlasFileInputPath, atlasFileOutputPath, atlasFileName);

    }
}
