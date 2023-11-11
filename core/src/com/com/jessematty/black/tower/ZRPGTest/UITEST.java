package com.jessematty.black.tower.ZRPGTest;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.EntitySelectWindow;


/**
 * class for testing newly created UI components
 */
public class UITEST  implements Screen {


    private final GameAssets gameAssetts;
    /**
     * stage for testing UI  components
     */
    Stage testStage= new Stage();
    Array<Entity> entityArray= new Array<Entity>();
    EntitySelectWindow entitySelectWindow;

    public UITEST(GameAssets gameAssets) {
        super();
        this.gameAssetts=gameAssets;
        GameAssets.getGameInput().addProcessor(testStage);
        JsonLoader jsonLoader= new JsonLoader();
        jsonLoader.writeObjectToFile(TestEntities.getAll(), "/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/android/assets/Entities/testEntities.json", true);
        testStage.setDebugInvisible(false);
        TextureAtlas atlas= gameAssets.loadInternalTextureAtlas("textureAtlases/testAssets/testAssets.atlas");
        gameAssets.finishLoading();
        for(int count=0; count<30; count++){
            NameComponent nameComponent= new NameComponent("name "+count);
            Entity entity= new Entity();
            entity.add(nameComponent);
            entityArray.add(entity);

        }
        Skin skin=gameAssets.loadInternalSkin("os8ui/OS Eight", "os8ui/OS Eight");
        entitySelectWindow= new EntitySelectWindow(skin, entityArray, gameAssets, null);

        entitySelectWindow.setPosition(200,200);
        entitySelectWindow.setMovable(true);
        entitySelectWindow.setSkin(skin);

        testStage.addActor(entitySelectWindow);
        testStage.setDebugAll(true);


    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 0); // clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        testStage.act();
        testStage.draw();

    }

    @Override
    public void resize(int width, int height) {
    }



}