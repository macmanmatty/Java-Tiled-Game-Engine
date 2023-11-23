package Input;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.Input.Keys;
import com.jessematty.black.tower.GameBaseClasses.Input.DualActionKeyInputCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyPressMode;

import org.junit.Test;
public class TestKeyListener {
   private KeyListener keyListener= new KeyListener();
    @Test
    public void testMultipleKeysPressedDownCorrect(){
        final boolean[] called = {false};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0] =true;
            }
        };
    InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, "name", Keys.M, Keys.N);
    keyListener.addInputKeyCombo(inputKeyCombo);
    keyListener.keyDown(Keys.M);
    keyListener.keyDown(Keys.N);
    assertEquals(true, called[0]);
    }
    @Test
    public void removeKeyInputCombo(){
        final boolean[] called = {false};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0] =true;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, "name", Keys.M, Keys.N);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.removeInputKeyCombo(inputKeyCombo);
        assertEquals(0, keyListener.getInputKeyCombos().size);
    }
    @Test
    public void testMultipleKeysPressedDownIncorrect(){
        final boolean[] called = {false};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0] =true;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, "name", Keys.M, Keys.N);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        keyListener.keyDown(Keys.B);
        assertEquals(false, called[0]);
    }
    @Test
    public void testSingleKeyPressedDown(){
        final boolean[] called = {false};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0] =true;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, "name", Keys.M);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        assertEquals(true, called[0]);
    }
    @Test
    public void testSingleKeyPressedDownIncorrect(){
        final boolean[] called = {false};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0] =true;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, "name", Keys.M);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.X);
        keyListener.keyTyped('V');
        assertEquals(false, called[0]);
    }
    @Test
    public void testMultipleFunctionsCalled(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, "name", Keys.M, Keys.N);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        keyListener.keyDown(Keys.N);
        keyListener.keyUp(Keys.M);
        keyListener.keyUp(Keys.N);
        InputKeyCombo inputKeyCombo2= new InputKeyCombo(keyAction, "name", Keys.B, Keys.Z);
        keyListener.addInputKeyCombo(inputKeyCombo2);
        keyListener.keyDown(Keys.B);
        keyListener.keyDown(Keys.Z);
        assertEquals(2, called[0]);
    }
    @Test
    public void testMultipleFunctionsCalledDifferentKeyModes(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, KeyPressMode.KEY_UP, "name", Keys.M, Keys.N);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        keyListener.keyUp(Keys.M);
        InputKeyCombo inputKeyCombo2= new InputKeyCombo(keyAction, "name", Keys.B, Keys.Z);
        keyListener.addInputKeyCombo(inputKeyCombo2);
        keyListener.keyDown(Keys.B);
        keyListener.keyDown(Keys.Z);
        assertEquals(2, called[0]);
    }
    @Test
    public void testKeyUp(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, KeyPressMode.KEY_UP, "name", Keys.M, Keys.N);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        keyListener.keyUp(Keys.M);
        assertEquals(1, called[0]);
    }
    @Test
    public void testKeyUpIncorrect(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, KeyPressMode.KEY_UP, "name", Keys.M, Keys.N);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.C);
        keyListener.keyUp(Keys.C);
        keyListener.keyDown(Keys.K);
        keyListener.keyUp(Keys.K);
        keyListener.keyTyped('V');
        assertEquals(0, called[0]);
    }
    @Test
    public void testKeyPressed(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, KeyPressMode.KEY_PRESSED, "name", Keys.M);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        keyListener.update(1);
        keyListener.update(1);
        keyListener.update(1);
        keyListener.keyTyped('V');
        assertEquals(3, called[0]);
    }
    @Test
    public void testMultiplePressModes(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        KeyPressMode []  keyPressMode= new KeyPressMode []{KeyPressMode.KEY_DOWN, KeyPressMode.KEY_PRESSED};
        InputKeyCombo inputKeyCombo= new InputKeyCombo(keyAction, keyPressMode, "name", Keys.M);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.M);
        keyListener.update(1);
        keyListener.update(1);
        keyListener.update(1);
        keyListener.keyTyped('V');
        assertEquals(4, called[0]);
    }
    @Test
    public void dualKeyInputCombo(){
        final int[] called = {0};
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        DualActionKeyInputCombo inputKeyCombo= new DualActionKeyInputCombo(keyAction,  "name", Keys.C);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.C);
        keyListener.keyUp(Keys.C);
        keyListener.keyDown(Keys.K);
        keyListener.keyUp(Keys.K);
        keyListener.keyTyped('V');
        assertEquals(2, called[0]);
    }
    @Test
    public void dualKeyInputComboDifferentActions(){
        final int[] called = {0};
        KeyAction keyUpAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        KeyAction keyDownAction= new KeyAction() {
            @Override
            public void act() {
                called[0]=-2;
            }
        };
        DualActionKeyInputCombo inputKeyCombo= new DualActionKeyInputCombo(keyUpAction,keyDownAction,  "name", Keys.C);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.keyDown(Keys.C);
        keyListener.keyUp(Keys.C);
        keyListener.keyDown(Keys.K);
        keyListener.keyUp(Keys.K);
        keyListener.keyTyped('V');
        assertEquals(-1, called[0]);
    }
    @Test
    public void removeDualInputKeyCombo(){
        final int[] called = {0};
        KeyAction keyUpAction= new KeyAction() {
            @Override
            public void act() {
                called[0]++;
            }
        };
        KeyAction keyDownAction= new KeyAction() {
            @Override
            public void act() {
                called[0]=-2;
            }
        };
        DualActionKeyInputCombo inputKeyCombo= new DualActionKeyInputCombo(keyUpAction,keyDownAction,  "name", Keys.C);
        keyListener.addInputKeyCombo(inputKeyCombo);
        keyListener.removeInputKeyCombo(inputKeyCombo);
        assertEquals(0, keyListener.getInputKeyCombos().size);
    }
}
