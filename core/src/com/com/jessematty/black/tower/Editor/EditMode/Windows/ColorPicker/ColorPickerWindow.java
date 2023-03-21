package com.jessematty.black.tower.Editor.EditMode.Windows.ColorPicker;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jessematty.black.tower.Components.Interfaces.ColorSettable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerListener;
public class ColorPickerWindow  extends EditWindow implements ClipBoardChangeListener {
    private ColorPicker colorPicker= new ColorPicker();
    private ColorSettable colorSettable;
    private TextField colorName;
    public ColorPickerWindow(GameAssets assets, String title, Skin skin, String style) {
        super(assets, title, skin, style);
        colorName= new TextField("Enter Color Nmae" , skin);
        makeWindow();
    }
    @Override
    public void makeWindow() {
        add(colorPicker);
        row();
        add(colorName);
        pack();;
        validate();
        colorPicker.setListener(new ColorPickerListener() {
            @Override
            public void canceled(Color oldColor) {
            }
            @Override
            public void changed(Color newColor) {
            }
            @Override
            public void reset(Color previousColor, Color newColor) {
            }
            @Override
            public void finished(Color newColor) {
                String text=colorName.getText();
                NamedColor namedColor = new NamedColor(newColor, text);
                
                if(colorSettable!=null) {
                    colorSettable.setColor(namedColor);
                }
            }
        });
    }

    public void noInformation(){


    }

    public void setColorSettable(ColorSettable colorSettable) {
        this.colorSettable = colorSettable;
        colorPicker.setColor(colorSettable.getColor());
    }

    @Override
    public void objectChanged(Object object) {
        if(object instanceof ColorSettable){

            setColorSettable((ColorSettable) object);
        }

        else{

            noInformation();
        }

    }
}
