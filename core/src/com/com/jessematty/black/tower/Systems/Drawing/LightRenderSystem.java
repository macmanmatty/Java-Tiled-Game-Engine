package com.jessematty.black.tower.Systems.Drawing;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.jessematty.black.tower.Components.Other.Light;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import java.util.Comparator;
public  class LightRenderSystem extends SortedIteratingSystem{
    /**
     * the lights to render to the frame buffer
     */
    private ComponentMapper<Light> lights;
    /**
     * the positions of the lights
     */
    private ComponentMapper<PositionComponent> positions;
    private final  MapDraw mapDraw;
    /**
     * sprite batch for drawing the lights
     */
    private Batch batch;
    /**
     *  the frame buffer to render the lights to
     */
    private FrameBuffer frameBuffer;
    public LightRenderSystem(MapDraw draw, Family family , Comparator<Entity>  comparator, Batch batch, FrameBuffer frameBuffer, int priority) {
        super(family, comparator, priority);
        this.mapDraw=draw;
        this.batch = batch;
        this.frameBuffer = frameBuffer;
    }

    /**
     *  sets the up the component mappers
     * @param engine the game engine
     */
    @Override
    public void addedToEngine(Engine engine) {
        lights= GameComponentMapper.getLightComponentMapper();
        positions=GameComponentMapper.getPositionComponentMapper();
    }
    /**
     * @param deltaTime  libGDX delta time
     *
     * updates the light entities in the game
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        batch.begin();
        ImmutableArray<Entity> entities =getEntities();
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Light light = lights.get(entity);
                PositionComponent position = positions.get(entity);
                batch.setColor(light.getColor());
                batch.getShader().setUniformf("bright", light.getBrightness());
                float positionX = position.getLocationX() + light.getDrawOffsetX();
                float positionY = position.getLocationY() + light.getDrawOffsetY();
        }
        batch.end();
    }
    public FrameBuffer getFrameBuffer() {
        return frameBuffer;
    }
    public void setFrameBuffer(FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
    }
    @Override
    protected void processEntity(Entity entity, float v) {
    }
}