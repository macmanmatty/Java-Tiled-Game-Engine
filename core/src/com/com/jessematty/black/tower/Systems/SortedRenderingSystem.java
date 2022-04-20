package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import java.util.Comparator;
/**
 * class for systems  that renders  entities  TextureRegions based on their layer numbers in the  DrawableComponent
 * every time a new entity is added the array is resorted
 */
public  abstract class SortedRenderingSystem extends EntitySystem implements EntityListener  {
    /**
     *  // the family of components to act on
     *  in this case PositionComponent.class and DrawableComponent.class
     */
    private Family family;  // the family of components to act on
    /**
     * the array of sorted Entities
     */
    private Array<Entity> sortedEntities; // the sorted entities
    /**
     * // whether or not  to sort the array on entities
     */
    private boolean shouldSort;
    private Comparator<Entity> comparator; // entity comparator
    /**
     * // the libGDX sprite batch object used for rendering
     *
     */
    private  final  Batch batch;
    /**
     * // the framebuffer used for rendering the entity layer
     * if used with framer buffer layers
     */
    private  final FrameBuffer  frameBuffer; // the framebuffer used for rendering
    /**
     * the brightness level for the openGL shader
     *  0= total dark 1=normal 2=total white
     *
     */
        private float brightness=1; // sets the brightness for the fragment shader
    /**
     * Entity ComponentMappers
     */
    private ComponentMapper<DrawableComponent> drawableComponentMapper; // component mappers
    private ComponentMapper<PositionComponent> positionComponentMapper;
    /**
     *  flag for call batch.end(); if true this system will call batch.end(); otherwise batch.end() MUST be called somewhere else
     */
    private boolean callBatchEnd;
    /**
     * // flag for call frameBuffer.begin();
     */
    private boolean startFrameBuffer;
    /**
     * // flag for call frameBuffer.end(); if true this system will call frameBuffer.end(); otherwise if using a framebuffer  frameBuffer.end() MUST be called somewhere else
     */
    private boolean endFrameBuffer;
        public SortedRenderingSystem(  Family family, Comparator<Entity> comparator, Batch batch,  FrameBuffer buffer, int priority) {
            super(priority);
            this.family = family;
            this.sortedEntities = new Array(false, 160);
            this.comparator = comparator;
            this.batch=batch;
            this.frameBuffer=buffer;
            this.drawableComponentMapper=GameComponentMapper.getDrawableComponentMapper();
            this.positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
        }
    /**
     * sets the sorting flag to true
     * so next time the update function is called
     * the Array of Entities will be sorted
     */
    public void forceSort() {
            this.shouldSort = true;
        }
    /**
     * sorts the Array ofEntities and then sets the shouldSort flag to false
     */
    private void sort() {
            if (this.shouldSort) {
                this.sortedEntities.sort(this.comparator);
                this.shouldSort = false;
            }
        }
    /**
     * called when this System is added to the Engine
     * @param engine the Ashley Engine
     */
    public void addedToEngine(Engine engine) {
            ImmutableArray<Entity> newEntities = engine.getEntitiesFor(this.family);
            this.sortedEntities.clear();
            if (newEntities.size() > 0) {
                for(int i = 0; i < newEntities.size(); ++i) {
                    this.sortedEntities.add(newEntities.get(i));
                }
                this.sortedEntities.sort(this.comparator);
            }
            this.shouldSort = false;
            engine.addEntityListener(this.family, this);
        }
    /**
     *
     * called when this system is removed from the Engine
     * @param engine the Ashley Engine
     *  clears the Array and removes the entity listener from the engine
     */
    public void removedFromEngine(Engine engine) {
            engine.removeEntityListener(this);
            this.sortedEntities.clear();
            this.shouldSort = false;
        }
    /** called when a new Entity is added to the engine
     * this will   add the Entity   to the array of Entities  to render
     * if they have  a position  and drawable component
     *
     * @param entity the newly added Entity to the engine
     */
    public void entityAdded(Entity entity) {
            //get entity  components
            PositionComponent position=positionComponentMapper.get(entity);
            DrawableComponent drawableComponent =drawableComponentMapper.get(entity);
            // if entity has components  both position and  drawable add it to the list
            if(position!=null && drawableComponent !=null) {
                this.sortedEntities.add(entity);
                this.shouldSort = true;
            }
        }
    /** called when an   Entity  is removed  from the engine and if it
     * exists in the array   of Renderable Entities removes it.
     *
     * @param entity the newly Removed Entity
     */
    public void entityRemoved(Entity entity) {
            this.sortedEntities.removeValue(entity, true);
            this.shouldSort = true;
        }
        /** loops over the array of entities and draws  them
        also calls batch.begin(); if needed  and batch.end() on the sprite batch if the flag is for end batch is set
         otherwise uses a FrameBuffer for drawing  the entity layer.
         @param deltaTime  libGDX delta time
         **/
        public void update(float deltaTime) {
            this.sort();
            if(frameBuffer!=null){
                frameBuffer.begin();
            }
            // if batch isn't already started start it
            if( !batch.isDrawing()) {
                batch.begin();
            }
                if(frameBuffer!=null && startFrameBuffer){
                    frameBuffer.begin();
                }
            batch.getShader().setUniformf("bright", brightness);
            for(int i = 0; i < this.sortedEntities.size; ++i) {
                Entity entity=this.sortedEntities.get(i);
                this.processEntity(entity, deltaTime);
            }
            if(callBatchEnd) {
                batch.end();
            }
            if(frameBuffer!=null && endFrameBuffer){
                frameBuffer.end();
            }
        }
    /**  sorts then returns  the list on entities
     *
     * @return the sorted Array of Entities
     */
    public Array<Entity> getEntities() {
            this.sort();
            return this.sortedEntities;
        }
        public Family getFamily() {
            return this.family;
        }
        protected abstract void processEntity(Entity var1, float var2);
    public float getBrightness() {
        return brightness;
    }
    /** sets the brightness for the openGL shader
     * @param brightness the brightness level from 0=2
     * 0= total dark 1=normal 2=total white
     */
    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }
    public boolean isCallBatchEnd() {
        return callBatchEnd;
    }
    public void setCallBatchEnd(boolean callBatchEnd) {
        this.callBatchEnd = callBatchEnd;
    }
    public boolean isStartFrameBuffer() {
        return startFrameBuffer;
    }
    public void setStartFrameBuffer(boolean startFrameBuffer) {
        this.startFrameBuffer = startFrameBuffer;
    }
    public boolean isEndFrameBuffer() {
        return endFrameBuffer;
    }
    public void setEndFrameBuffer(boolean endFrameBuffer) {
        this.endFrameBuffer = endFrameBuffer;
    }
}
