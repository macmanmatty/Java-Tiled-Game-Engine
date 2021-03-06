package com.jessematty.black.tower.Editor.EditMode.Windows.ColorPicker;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jessematty.black.tower.Components.ColorSettable;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerListener;
public class ColorPickerWindow  extends MapEditWindow  implements ClipBoardChangeListener {
    private ColorPicker colorPicker= new ColorPicker();
    private ColorSettable colorSettable;
    private TextField colorName;
    public ColorPickerWindow(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
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
