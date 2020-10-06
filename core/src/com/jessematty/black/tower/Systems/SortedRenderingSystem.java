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
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

import java.util.Comparator;

public  abstract class SortedRenderingSystem extends EntitySystem implements EntityListener  {

        private Family family;
        private Array<Entity> sortedEntities; // the sorted entities
        private final ImmutableArray<Entity> entities; // the list of entities to loop over
        private boolean shouldSort;
        private Comparator<Entity> comparator; // entity comparator
        private  final  Batch batch;
        private  final FrameBuffer  frameBuffer;
        private float brightness=1; // sets the brightness for the fragment shader
        private ComponentMapper<Drawable> drawableComponentMapper; // component mappers
        private ComponentMapper<PositionComponent> positionComponentMapper;
       private final GameComponentMapper gameComponentMapper;


        public SortedRenderingSystem(GameComponentMapper gameComponentMapper, Family family, Comparator<Entity> comparator, Batch batch, FrameBuffer frameBuffer) {
            this(gameComponentMapper, family, comparator, batch, frameBuffer, 1);

        }

        public SortedRenderingSystem( GameComponentMapper gameComponentMapper, Family family, Comparator<Entity> comparator, Batch batch,  FrameBuffer buffer, int priority) {
            super(priority);
            this.family = family;
            this.sortedEntities = new Array(false, 160);
            this.entities = new ImmutableArray(this.sortedEntities);
            this.comparator = comparator;
            this.batch=batch;
            this.frameBuffer=buffer;

            this.gameComponentMapper=gameComponentMapper;
            this.drawableComponentMapper=gameComponentMapper.getDrawableComponentMapper();
            this.positionComponentMapper=gameComponentMapper.getPositionComponentMapper();
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
            Drawable drawable=drawableComponentMapper.get(entity);
            // if entity has components  both position and  drawable add it to the list
            if(position!=null && drawable!=null) {
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

    // also calls batch.begin(); and batch.end() on the sprite batch
        public void update(float deltaTime) {
            this.sort();
            if(frameBuffer!=null){
                frameBuffer.begin();
            }
          batch.begin();
            batch.getShader().setUniformf("bright", brightness);

            for(int i = 0; i < this.sortedEntities.size; ++i) {
                Entity entity=this.sortedEntities.get(i);
                this.processEntity(entity, deltaTime);


            }

            batch.end();
            if(frameBuffer!=null){
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



    public GameComponentMapper getGameComponentMapper() {
        return gameComponentMapper;
    }
}


