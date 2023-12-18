package libGDX;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class HeadlesslibGDX {
    /**
     * Initialise a test headless application.
     * you must create a new HeadLessLibgdx instance for any tests
     * that require core libGDX functionality to be tested
     *
     */
     static HeadlessApplication headlessApplication;
   static  {
        if (headlessApplication == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            headlessApplication = new HeadlessApplication(new Game() {
                @Override
                public void create() {

                }
            }, config);
        }
    }

    public static HeadlessApplication getHeadlessApplication() {
        return headlessApplication;
    }


}
