package com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor;
import com.badlogic.gdx.graphics.Color;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel.Nameable;
/** 
 * class that extends  libGDX color class with name variable to be used for select boxes 
 * and list with libGDX UI classes
 */
public class NamedColor extends Color implements Nameable {
    /**
     * the name of the color
     */
   private  String name;
    /**
     *  100% white RGBA  color
     */
   public static final  NamedColor WHITE= new NamedColor(1,1,1,1 , "White");
    /**
     * 100% black RGBA color
     */
    public static final  NamedColor BLACK= new NamedColor(0,0,0 , 1, "Black");
    public NamedColor(String name) {
        this.name = name;
    }
    public NamedColor(int rgba8888, String name) {
        super(rgba8888);
        this.name = name;
    }
    public NamedColor(int rgba8888) {
        super(rgba8888);
        this.name = getName();
    }
    public NamedColor() {
    }
    public NamedColor(float r, float g, float b, float a, String name) {
        super(r, g, b, a);
        this.name = name;
    }
    public NamedColor(float r, float g, float b, float a) {
        super(r, g, b, a);
        this.name = getName();
    }
    public NamedColor(Color color, String name) {
        super(color);
        if( name!=null&& !name.isEmpty()) {
            this.name = name;
        }
        else{
            this.name=getName();
        }
    }
    public NamedColor(Color color) {
        super(color);
         name= makeName(color);
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name=name;
    }
    @Override
    public String toString() {
        return name;
    }

    /**
     * creates a name for the color based on it's R,G, B and A values
     * @param color
     * @return
     */
    public  static String makeName(Color color){
        String name="color R "+String.valueOf(color.r)+" G "+String.valueOf(color.g)+" B "+String.valueOf(color.b)+" A "+String.valueOf(color.a);
        return name;
    }
}
