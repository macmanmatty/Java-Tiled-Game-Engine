package com.jessematty.black.tower.GameBaseClasses.UIClasses.EditBounds;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
public class EditBounds extends Image  {
    private TextureRegion region;
    private Entity entity;
    private PositionComponent position;
    private Array<Vector2> points= new Array<>();
    private Array<Line> lines= new Array<Line>();
    private int clicks;
    private KeyListener keyListener;
    private ShapeRenderer shapeRenderer;
    private Color lineColor= new Color(1,0,0,1);
    private Line currentLine;
    private boolean boundsSet;
    public EditBounds(TextureRegion region, KeyListener keyListener, ShapeRenderer shapeRenderer) {
        super(region);
        this.keyListener = keyListener;
        this.shapeRenderer = shapeRenderer;
    }
    public EditBounds(Texture texture, KeyListener keyListener, ShapeRenderer shapeRenderer) {
        super(texture);
        this.keyListener = keyListener;
        this.shapeRenderer = shapeRenderer;
    }
    public EditBounds(Drawable drawable, KeyListener keyListener, ShapeRenderer shapeRenderer) {
        super(drawable);
        this.keyListener = keyListener;
        this.shapeRenderer = shapeRenderer;
    }
    public EditBounds(TextureRegion region) {
        super(region);
    }
    public EditBounds(Texture texture) {
        super(texture);
    }
    public EditBounds(Drawable drawable) {
        super(drawable);
    }
    public EditBounds() {
     
        addListener( new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                
                int size=points.size-1;
                if(size>=0) {
                    float point1X = points.get(size).x;
                    float point1Y = points.get(size).y;
                    currentLine = new Line(point1X, point1Y, x, y);
                }
                
                
                return  true;
                
                
                
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                float pointX=x-getX();
                float pointY=y-getY();
                points.add(new Vector2(pointX, pointY));
                if(clicks%2==0 && clicks!=0){
                    
                    lines.add(currentLine);
                     pointCheck(x,y);
                }
                return true;
            }
        });
    }
    private void pointCheck(float x, float y) {
        
        int size=points.size;
        for(int count=0; count<size; count++){
            
            Vector2 point=points.get(count);
            if(point.x==x && point.y==y){
                
                  boundsSet =setEntityBounds();
                 return;
                
                
            }
            
            
        }
        
        
    }
    private boolean setEntityBounds() {
        if(points.size>2) {
            float[] verticies = new float[points.size * 2];
            int size=points.size;
            int counter=0;
            for(int count=0; count<size; count++) {
                Vector2 point=points.get(count);
                verticies[counter]=point.x;
                counter++;
                verticies[counter]=point.y;
                counter++;
                
                
            }
            try {
                Polygon polygon = new Polygon(verticies);
                position.setBounds(polygon);
                return true;
            }
            
            catch (IllegalArgumentException e){
                
                
                return  false;
                
                
            }
            
        }
    
        
        return false;
        
        
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(shapeRenderer!=null) {
            shapeRenderer.setColor(lineColor);
            int size = lines.size;
            for (int count = 0; count < size; count++) {
                Line line=lines.get(count);
                shapeRenderer.line(line.x1, line.y1, line.x2, line.y2);
            }
            
            shapeRenderer.setColor(lineColor.r, lineColor.g, lineColor.b, .5f);
            shapeRenderer.line(currentLine.x1, currentLine.y1, currentLine.x2, currentLine.y2);
        }
    }
    public TextureRegion getRegion() {
        return region;
    }
    public void setRegion(TextureRegion region) {
      setDrawable(new TextureRegionDrawable(region));
    }
    public Entity getEntity() {
        return entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
        position=entity.getComponent(PositionComponent.class);
    }
    public KeyListener getKeyListener() {
        return keyListener;
    }
    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }
    public Color getLineColor() {
        return lineColor;
    }
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    private Vector2 getStraightLine( float pointX, float pointY, float screenX, float screenY){
        Direction direction=Direction.getDirection(pointX, pointY, screenX, screenY);
        switch (direction){
            case UP:
            case DOWN:
                return  new Vector2(pointX, screenY );
            case LEFT:
            case RIGHT:
                return  new Vector2(screenX , pointY);
            case LEFTUP:
                return  new Vector2(screenX , pointY);
            case LEFTDOWN:
                break;
            case RIGHTUP:
                break;
            case RIGHTDOWN:
                break;
            case SAME:
                break;
        }
            return  new Vector2();
    }
}
