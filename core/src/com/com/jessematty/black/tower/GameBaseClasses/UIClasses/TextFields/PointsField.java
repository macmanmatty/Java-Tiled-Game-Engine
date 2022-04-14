package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class PointsField extends HorizontalGroup {
    private FloatField xField;
    private Label xLabel;
    private FloatField yField;
    private Label yLabel;
    private FloatField zField;
    private Label zLabel;
    private boolean hasZ;
    private Skin skin;
    private float space=4;
    private float textFieldLength=50f;









    public PointsField(Skin skin, boolean hasZ) {
        this.hasZ=hasZ;
        this.skin=skin;


    }

    public void make(){
        clear();
        this.xField = new FloatField("0", skin);
        this.xLabel = new Label("X", skin);
        this.yField = new FloatField("0", skin);
        this.yLabel = new Label("Y", skin);
        xField.setWidth(textFieldLength);
        addActor(xLabel);
        addActor(xField);
        addActor(yLabel);
        addActor(yField);
        yField.setWidth(textFieldLength);
        if(hasZ==true){
            this.zField = new FloatField("0", skin);

            this.zLabel = new Label("Z", skin);

            addActor(zLabel);
            addActor(zField);
            zField.setWidth(textFieldLength);

        }

        space(space);
    }


    public FloatField getxField() {
        return xField;
    }

    public void setxField(FloatField xField) {
        this.xField = xField;
    }

    public Label getxLabel() {
        return xLabel;
    }

    public void setxLabel(Label xLabel) {
        this.xLabel = xLabel;
    }

    public FloatField getyField() {
        return yField;
    }

    public void setyField(FloatField yField) {
        this.yField = yField;
    }

    public Label getyLabel() {
        return yLabel;
    }

    public void setyLabel(Label yLabel) {
        this.yLabel = yLabel;
    }

    public FloatField getzField() {
        return zField;
    }

    public void setzField(FloatField zField) {
        this.zField = zField;
    }

    public Label getzLabel() {
        return zLabel;
    }

    public void setzLabel(Label zLabel) {
        this.zLabel = zLabel;
    }

    public boolean isHasZ() {
        return hasZ;
    }

    public void setHasZ(boolean hasZ) {
        this.hasZ = hasZ;
        make();
    }

    public Vector2 getVector2(){

        float  x=xField.getFloat();
        float y =yField.getY();


        return new Vector2(x,y);

    }

    public Vector3 getVector3(){

        float  x=xField.getFloat();
        float y =yField.getY();
        float z=0;
        if(zField!=null){

            z=zField.getFloat();
        }
        return new Vector3(x,y, z);

    }

    @Override
    public float getSpace() {
        return space;
    }

    public void setSpace(float space) {
        this.space = space;
        make();
    }

    public float getTextFieldLength() {
        return textFieldLength;
    }

    public void setTextFieldLength(float textFieldLength) {
        this.textFieldLength = textFieldLength;

        make();
    }

    public void setValues(float x, float y ,float z){

        xField.setText(String.valueOf(x));
        yField.setText(String.valueOf(y));
        if(zField!=null) {
            zField.setText(String.valueOf(z));


        }


    }

    public void setValues(float x, float y){

        xField.setText(String.valueOf(x));
        yField.setText(String.valueOf(y));

    }


}
