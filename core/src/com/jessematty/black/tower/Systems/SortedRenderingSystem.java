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
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

import java.util.Comparator;

public  abstract class SortedRenderingSystem extends EntitySystem implements EntityListener  {

        private Family family;  // the family of components to act on
        private Array<Entity> sortedEntities; // the sorted entities
        private final ImmutableArray<Entity> entities; // the list of entities to loop over
        private boolean shouldSort; // whether or not  to sort the array on entities
        private Comparator<Entity> comparator; // entity comparator
        private  final  Batch batch; // the libGDX sprite batch object used for rendering
        private  final FrameBuffer  frameBuffer; // the framebuffer used for rendering
        private float brightness=1; // sets the brightness for the fragment shader
        private ComponentMapper<DrawableComponent> drawableComponentMapper; // component mappers
        private ComponentMapper<PositionComponent> positionComponentMapper;
       private boolean callBatchEnd; // flag for call batch.end(); if true this system will call batch.end(); otherwise batch.end() MUST be called somewhere else
        private boolean startFrameBuffer; // flag for call frameBuffer.begin();
        private boolean endFrameBuffer; // flag for call frameBuffer.end(); if true this system will call frameBuffer.end(); otherwise if using a framebuffer  frameBuffer.end() MUST be called somewhere else





        public SortedRenderingSystem( Family family, Comparator<Entity> comparator, Batch batch, FrameBuffer frameBuffer) {
            this( family, comparator, batch, frameBuffer, 1);

        }

        public SortedRenderingSystem(  Family family, Comparator<Entity> comparator, Batch batch,  FrameBuffer buffer, int priority) {
            super(priority);
            this.family = family;
            this.sortedEntities = new Array(false, 160);
            this.entities = new ImmutableArray(this.sortedEntities);
            this.comparator = comparator;
            this.batch=batch;
            this.frameBuffer=buffer;

            this.drawableComponentMapper=GameComponentMapper.getDrawableComponentMapper();
            this.positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
        }


        public void forceSort() {
            this.shouldSort = true;
        }

        private void sort() {
            if (this.shouldSort) {
                this.sortedEntities.sort(this.comparator);
                this.shouldSort = false;
            }

        }

        public void addedToEngine(Engine engine) {
            ImmutableArray<Entity> newEntities = engine.getEntitiesFor(this.family);
            System.out.println("Number of Entites to Render "+newEntities.size());



            this.sortedEntities.clear();
            if (newEntities.size() > 0) {
                for(int i = 0; i < newEntities.size(); ++i) {
                    this.sortedEntities.add(newEntities.get(i));
                    System.out.println(newEntities.get(i).getComponent(Name.class).getStat());
                }

                this.sortedEntities.sort(this.comparator);
            }

            this.shouldSort = false;
            engine.addEntityListener(this.family, this);
        }

        public void removedFromEngine(Engine engine) {
            engine.removeEntityListener(this);
            this.sortedEntities.clear();
            this.shouldSort = false;
        }


        // adds listens  for added  entities and  add them  if they have  a position  and drawable component
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

        // listens for an removed  entity  from the engine and if it exists in the list removes it
        public void entityRemoved(Entity entity) {
            this.sortedEntities.removeValue(entity, true);
            this.shouldSort = true;
        }

        // loops over the array of entities and draws  them

    // also calls batch.begin(); if needed  and batch.end() on the sprite batch if the flag is for end batch is set
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

        //  sorts then returns  the list on entities
        public ImmutableArray<Entity> getEntities() {
            this.sort();
            return this.entities;
        }

        public Family getFamily() {
            return this.family;
        }

        protected abstract void processEntity(Entity var1, float var2);



    public float getBrightness() {
        return brightness;
    }
    // sets the brightness for the shader

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


