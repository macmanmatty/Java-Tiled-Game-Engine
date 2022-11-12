import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

import java.io.File;

public class TexturePackerLoader {

    public TexturePackerLoader() {
    }
    String fileOutputPath=System.getenv("fileOutputPath");
    String atlasFileName =System.getenv("atlasFileName");

    private TexturePacker texturePacker= new TexturePacker(new Settings());

    public void pack(Settings settings){
        texturePacker = new TexturePacker(settings);
        texturePacker.pack(new File(fileOutputPath), atlasFileName);

    }
    public static void process(Settings settings, String inputPath, String outputPath, String packFileName){

        TexturePacker.process(settings, inputPath, outputPath, packFileName);

    }


}
