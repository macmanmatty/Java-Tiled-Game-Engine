import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.jessematty.black.tower.Editor.GameEditor;

public class EditorLauncher {

	public EditorLauncher() {
	}

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle( "Game Editor");
		config.setWindowSizeLimits(1200, 1400, 1200, 1400);
		new Lwjgl3Application(new GameEditor(), config);
	}

	public void start(){

	}


}
