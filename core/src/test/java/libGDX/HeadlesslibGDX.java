package libGDX;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.jessematty.black.tower.GameBaseClasses.BlackTower;

public class HeadlesslibGDX {
    /**
     * Initialise a test headless application.
     */
     static HeadlessApplication headlessApplication;
   static  {
        if (headlessApplication == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            headlessApplication = new HeadlessApplication(new BlackTower(), config);
        }
    }

    public static HeadlessApplication getHeadlessApplication() {
        return headlessApplication;
    }


}
