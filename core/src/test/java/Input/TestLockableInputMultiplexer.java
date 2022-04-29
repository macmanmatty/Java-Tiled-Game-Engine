package Input;
import com.badlogic.gdx.InputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputMultiplexer;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.internal.AllMembersSupplier;
import Input.TestInputProcessors.AllKeyInputLockedInputProcessor;
import Input.TestInputProcessors.AllMouseInputLockedInputProcessor;
import Input.TestInputProcessors.KeyDownInputLockedInputProcessor;
import Input.TestInputProcessors.KeyTypedInputLockedInputProcessor;
import Input.TestInputProcessors.KeyUpInputLockedInputProcessor;
import Input.TestInputProcessors.MouseMovedLockedInputProcessor;
import Input.TestInputProcessors.ScrolledLockedInputProcessor;
import Input.TestInputProcessors.TouchDownLockedInputProcessor;
import Input.TestInputProcessors.TouchDraggedLockedInputProcessor;
import Input.TestInputProcessors.TouchUpLockedInputProcessor;
import static org.junit.Assert.assertEquals;
public class TestLockableInputMultiplexer {
    AllKeyInputLockedInputProcessor allKeyInputLockedInputProcessor;
    AllMouseInputLockedInputProcessor allMouseInputLockedInputProcessor;
    KeyDownInputLockedInputProcessor keyDownInputLockedInputProcessor;
    KeyUpInputLockedInputProcessor keyUpInputLockedInputProcessor;
    KeyTypedInputLockedInputProcessor keyTypedInputLockedInputProcessor;
    MouseMovedLockedInputProcessor mouseMovedLockedInputProcessor;
    TouchDownLockedInputProcessor touchDownLockedInputProcessor;
    TouchUpLockedInputProcessor touchUpLockedInputProcessor;
    TouchDraggedLockedInputProcessor touchDraggedLockedInputProcessor;
    ScrolledLockedInputProcessor scrolledLockedInputProcessor;
    @Before
    public void createInputProcessors(){
    allKeyInputLockedInputProcessor= new AllKeyInputLockedInputProcessor();
    allMouseInputLockedInputProcessor= new AllMouseInputLockedInputProcessor();
    keyDownInputLockedInputProcessor= new KeyDownInputLockedInputProcessor();
    keyUpInputLockedInputProcessor= new KeyUpInputLockedInputProcessor();
    keyTypedInputLockedInputProcessor= new KeyTypedInputLockedInputProcessor();
    mouseMovedLockedInputProcessor= new MouseMovedLockedInputProcessor();
    touchDownLockedInputProcessor= new TouchDownLockedInputProcessor();
    touchUpLockedInputProcessor= new TouchUpLockedInputProcessor();
    touchDraggedLockedInputProcessor= new TouchDraggedLockedInputProcessor();
    scrolledLockedInputProcessor= new ScrolledLockedInputProcessor();
    }
    @Test
   public  void testLockTouchUp(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(scrolledLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(touchUpLockedInputProcessor);
        lockableInputMultiplexer.touchUp(0, 0, 0, 0);
        assertEquals("Touch Up", scrolledLockedInputProcessor.getText());
        assertEquals("", touchUpLockedInputProcessor.getText());
    }
    @Test
    public  void testLockTouchDown(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(scrolledLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.touchDown(0, 0, 0, 0);
        assertEquals("Touch Down", scrolledLockedInputProcessor.getText());
        assertEquals("", touchDownLockedInputProcessor.getText());
    }
    @Test
    public  void testLockScrolled(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(scrolledLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.scrolled(0);
        assertEquals("", scrolledLockedInputProcessor.getText());
        assertEquals("Scrolled", touchDownLockedInputProcessor.getText());
    }
    @Test
    public  void testLockMouseMoved(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(mouseMovedLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.mouseMoved(0, 0);
        assertEquals("", mouseMovedLockedInputProcessor.getText());
        assertEquals("Moused Moved", touchDownLockedInputProcessor.getText());
    }
    @Test
    public  void testLockTouchDragged(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(mouseMovedLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.touchDragged(0, 0, 0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Touch Dragged", mouseMovedLockedInputProcessor.getText());
    }
    @Test
    public  void testLockAllMouseInput(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(allMouseInputLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.mouseMoved(0, 0);
        lockableInputMultiplexer.scrolled(0);
        lockableInputMultiplexer.touchUp(0, 0, 0, 0);
        lockableInputMultiplexer.touchDragged(0, 0, 0);
        lockableInputMultiplexer.touchDown(0, 0, 0, 0);
        assertEquals("", allMouseInputLockedInputProcessor.getText());
    }
    @Test
    public  void testLockAllKeyInput(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(allMouseInputLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(allKeyInputLockedInputProcessor);
        lockableInputMultiplexer.keyUp(0);
        lockableInputMultiplexer.keyDown(0);
        lockableInputMultiplexer.keyTyped('L');
        assertEquals("", allKeyInputLockedInputProcessor.getText());
    }
    @Test
    public  void testLockKeyTyped(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(allMouseInputLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(keyTypedInputLockedInputProcessor);
        lockableInputMultiplexer.keyTyped('L');
        assertEquals("", keyTypedInputLockedInputProcessor.getText());
    }
    @Test
    public  void testLockKeyUp(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(allMouseInputLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(keyUpInputLockedInputProcessor);
        lockableInputMultiplexer.keyUp(0);
        assertEquals("", keyUpInputLockedInputProcessor.getText());
    }
    @Test
    public  void testLockKeyDown(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(allMouseInputLockedInputProcessor);
        lockableInputMultiplexer.addProcessor(keyDownInputLockedInputProcessor);
        lockableInputMultiplexer.keyDown(0);
        assertEquals("", keyDownInputLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultMouseMovedProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentMouseProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.mouseMoved(0, 0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Moused Moved", touchDownLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultTouchUpProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentUnlockedTouchUpMouseProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.touchUp(0, 0,0,0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Touch Up", touchDownLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultTouchDownProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentUnlockedTouchDownMouseProcessor(touchUpLockedInputProcessor);
        lockableInputMultiplexer.touchDown(0, 0,0,0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Touch Down", touchUpLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultScrolledProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentUnlockedScrolledMouseProcessor(touchUpLockedInputProcessor);
        lockableInputMultiplexer.scrolled(0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Scrolled", touchUpLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultKeyTypedProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentUnlockedKeyTypedProcessor(touchUpLockedInputProcessor);
        lockableInputMultiplexer.keyTyped('L');
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Key  Typed", touchUpLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultKeyUpProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentUnlockedKeyUpProcessor(touchUpLockedInputProcessor);
        lockableInputMultiplexer.keyUp(0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Key Up", touchUpLockedInputProcessor.getText());
    }
    @Test
    public  void setDefaultKeyDownProcessor(){
        LockableInputMultiplexer lockableInputMultiplexer=new LockableInputMultiplexer();
        lockableInputMultiplexer.addProcessor(touchDraggedLockedInputProcessor);
        lockableInputMultiplexer.setCurrentUnlockedKeyDownProcessor(touchDownLockedInputProcessor);
        lockableInputMultiplexer.keyDown(0);
        assertEquals("", touchDraggedLockedInputProcessor.getText());
        assertEquals("Key Down", touchDownLockedInputProcessor.getText());
    }
}
