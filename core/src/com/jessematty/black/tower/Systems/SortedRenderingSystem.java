package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public  abstract class SortedRenderingSystem extends EntitySystem implements EntityListener  {

        private Family family;
        private Array<Entity> sortedEntities;
        private final ImmutableArray<Entity> entities;
        private boolean shouldSort;
        private Comparator<Entity> comparator;
        private  final  Batch batch;
        private  final FrameBuffer  frameBuffer;
        private float brightness=1;

        public SortedRenderingSystem(Family family, Comparator<Entity> comparator, Batch batch, FrameBuffer frameBuffer) {
            this(family, comparator, batch, frameBuffer, 1);

        }

        public SortedRenderingSystem(Family family, Comparator<Entity> comparator, Batch batch,  FrameBuffer buffer, int priority) {
            super(priority);
            this.family = family;
            this.sortedEntities = new Array(false, 16);
            this.entities = new ImmutableArray(this.sortedEntities);
            this.comparator = comparator;
            this.batch=batch;
            this.frameBuffer=buffer;
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

        public void removedFromEngine(Engine engine) {
            engine.removeEntityListener(this);
            this.sortedEntities.clear();
            this.shouldSort = false;
        }

        public void entityAdded(Entity entity) {
            this.sortedEntities.add(entity);
            this.shouldSort = true;
        }

        public void entityRemoved(Entity entity) {
            this.sortedEntities.removeValue(entity, true);
            this.shouldSort = true;
        }

        public void update(float deltaTime) {
            this.sort();
            if(frameBuffer!=null){
                frameBuffer.begin();
            }
          batch.begin();
            batch.getShader().setUniformf("bright", brightness);

            for(int i = 0; i < this.sortedEntities.size; ++i) {
                this.processEntity((Entity)this.sortedEntities.get(i), deltaTime);
            }

            batch.end();
            if(frameBuffer!=null){
                frameBuffer.end();
            }

        }

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

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }
}


