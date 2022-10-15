package com.jessematty.black.tower.Editor.EditMode.World;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class WorldEditable extends World {

    /**
     * class that extends world to make it editable and save references to  common  objects
     */

    /**
     *  The List Of Objects In The  World
     */
    private WorldObjects worldObjects= new WorldObjects();


        public WorldEditable() {
            super();
        }
        /**
         *
         * @param name the name of the map
         */
        public WorldEditable( String name) {
           super( name);

        }

    private void getMapObjects(LandMap mapToPlace) {


    }
    private void deleteMap() {

    }

    /**
         * // adds entity to ashley engine
         * @param entity entity to add
         */
        /**
         * // adds an entity to a game map with position coordinates
         * @param entity
         *
         */
        public void addEntityToMap(Entity entity, String mapId,  int x, int y, int z) {
            PositionComponent positionComponent = GameComponentMapper.getPositionComponentMapper().get(entity);

            if (positionComponent == null) {
                positionComponent = new PositionComponent();
                entity.add(positionComponent);
            }
            positionComponent.setMapID(mapId);
            positionComponent.setPosition(x, y, z);
            this.addEntityToWorld(entity);

        }


        public void addEntityToMap(Entity entity, PositionComponent position){

            super.addEntityToMap(entity, position);
        }
        /**
         * // adds entity to the world, and if applicable the engine and   and the map  and tiles stored in its position
         * @param entity the entity to add
         */
        public void addEntityToWorld(Entity entity) {
         super.addEntityToWorld(entity);
        }
        /**
         *   adds a bag of entities  to the world, engine, and the map stored in its position
         * @param entityBag the list on entities to add to the world
         */
        public void addEntityToWorld(EntityBag entityBag) {
            Array<Entity> entities=entityBag.getEntities();
            int size=entities.size;
            for(int count=0; count<size; count++) {
                Entity entity=entities.get(count);
                addEntityToWorld(entity);
            }
        }

        public void removeEntityFromMap(Entity entity, PositionComponent position){
            GameMap map= getMap(position.getMapId());
            if(map==null){
                return;
            }
            map.removeEntity(  entity);
        }
        /**
         *   adds an antity   to the world, engine, and the map stored in its position
         * @param entity the entity to add to the world
         */    public void removeEntityFromWorld(Entity entity){
           super.removeEntityFromWorld(entity);
        }

        public void addSystem(EntitySystem system, boolean addToEngine){
           super.addSystem(system, addToEngine);
        }
        public void removeSystem(GameEntitySystem system) {
           super.removeSystem(system);
        }

    }




