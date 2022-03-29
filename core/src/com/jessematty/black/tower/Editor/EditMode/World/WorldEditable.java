package com.jessematty.black.tower.Editor.EditMode.World;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.FlagComponents.AddedToEngine;
import com.jessematty.black.tower.Components.FlagComponents.NotAddedToEngine;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Crafting.Craft;
import com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables.CraftLookUpTable;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GamePrefecences;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.Settings.WorldSettings;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class WorldEditable extends World {

    /**
     * class that extends world to make it editable and save references to  common  objects
     */

    private WorldObjects worldObjects= new WorldObjects();

        /**
         *
         * @param xMaps number of maps that connect horizontally
         * @param yMaps number of maps that connect vertically
         */
        public WorldEditable(int xMaps, int yMaps) {
            super(xMaps, yMaps);
        }
        /**
         *
         * @param xMaps number of maps that connect horizontally
         * @param yMaps number of maps that connect vertically
         * @param name the name of the map
         */
        public WorldEditable(int xMaps, int yMaps, String name) {
           super(xMaps, yMaps, name);

        }

        /**
         *   // set a given array square to a LandMap instance
         *   // and add it's objects to the game objects
         * @param mapToPlace
         * @param x
         * @param y
         */
        public void placeMap(LandMap mapToPlace, int x, int y){

        GameMap currentMapAtLocation=getGameMapOrNull(x, y);
        if(currentMapAtLocation==null && mapToPlace!=null) { // no map exists place the map
            if (mapToPlace != null) {
                setMap(x, y, mapToPlace);
                getMapObjects(mapToPlace);
            }

            return;
        }
        else{ // remove current map and place new one
            removeMap(x, y);
            setMap(x, y, mapToPlace);
            getMapObjects(mapToPlace);
        }


    }




    private void getMapObjects(LandMap mapToPlace) {


    }
    private void deleteMap() {

    }


        /**
         * sets a  given map at location x, y as the start map for the player
         * @param x
         * @param y
         */

        public void setStartMap(int x, int y){
            super.setStartMap(x, y);

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
        public void addEntityToMap(Entity entity, int mapX, int mapY,  int x, int y, int z) {
            PositionComponent positionComponent = GameComponentMapper.getPositionComponentMapper().get(entity);

            if (positionComponent == null) {
                positionComponent = new PositionComponent();
                entity.add(positionComponent);
            }
            positionComponent.setMapWorldLocationX(mapX);
            positionComponent.setMapWorldLocationY(mapY);
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
            GameMap map= getGameMapOrNull(position.getMapWorldLocationX(), position.getMapWorldLocationY());
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




