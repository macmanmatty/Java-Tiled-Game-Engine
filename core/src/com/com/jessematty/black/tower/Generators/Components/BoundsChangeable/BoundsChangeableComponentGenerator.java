package com.jessematty.black.tower.Generators.Components.BoundsChangeable;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Components.Position.BoundsChangeableComponent;
import com.jessematty.black.tower.Components.Position.EntityBounds;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationDTO;
import com.jessematty.black.tower.Generators.Components.ComponentGenerator;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * class for generating a new BoundsChangeableComponent object @See BoundsChangeableComponent.class
 */
public   class BoundsChangeableComponentGenerator implements ComponentGenerator<BoundsChangeableComponent> {
  private JsonLoader jsonLoader;
    public BoundsChangeableComponentGenerator(GameAssets gameAssets) {
        jsonLoader=gameAssets.getJsonLoader();
    }
    @Override
    public BoundsChangeableComponent generateComponent(String filePath, FileUtilities.FileHandleType type) {
        BoundsChangeableComponentDTO animationDTOS=jsonLoader.loadObject(BoundsChangeableComponentDTO.class, filePath, type);
        return generateAnimatableComponent(animationDTOS);
    }
    /**
     Generates a new BoundsChangeable Component from an BoundsChangeableDTO
     * from an array of entity bounds  DTOs
     * @param boundsChangeableComponentDTO
     * @return AnimatableComponent
     */
    public   BoundsChangeableComponent generateAnimatableComponent ( BoundsChangeableComponentDTO boundsChangeableComponentDTO){
        BoundsChangeableComponent boundsChangeableComponent= new BoundsChangeableComponent();
        boundsChangeableComponent.setEightDirections(boundsChangeableComponentDTO.isEightDirections());
        Array<EntityBoundsDTO> bounds=boundsChangeableComponentDTO.getBoundsDTOArray();
        for(EntityBoundsDTO entityBoundsDTO : bounds){
           addBounds(entityBoundsDTO, boundsChangeableComponent);
        }
        return boundsChangeableComponent;
    }
    /**
     * Generates a new BoundsChangeable Component from an BoundsChangeableDTO
     * @param boundsChangeableComponent the boundsChangeable Component
     * @return
     */
    private ObjectMap<String, ObjectMap<String, EntityBounds>> addBounds(EntityBoundsDTO entityBoundsDTO, BoundsChangeableComponent boundsChangeableComponent) {
        ObjectMap<String, ObjectMap<String, EntityBounds>> bounds=boundsChangeableComponent.getBounds();
        String direction=entityBoundsDTO.getDirection();
        String action=entityBoundsDTO.getAction();
        ObjectMap<String , EntityBounds> directionalBounds=bounds.get(action);
        if(directionalBounds==null){
            directionalBounds=new ObjectMap<>();
            bounds.put(action, directionalBounds);
        }
        EntityBounds entityBounds=generateBounds(entityBoundsDTO);
        directionalBounds.put(direction, entityBounds);

        return bounds;
    }
    /**
     * Generates a new Entity Bounds Object from an Entity BoundsDTO  Object
     * and the current  number .
     * @param entityBoundsDTO the Entity bounds dto object
     * @return
     */
    private EntityBounds generateBounds(EntityBoundsDTO entityBoundsDTO) {
        EntityBounds entityBounds= new EntityBounds();
        if(!entityBoundsDTO.isHasBounds()){
            entityBounds.setHasBounds(false);
            return entityBounds;
        }
        Polygon polygon= entityBoundsDTO.getBounds();
        float boundsX=entityBoundsDTO.getBoundsX();
        float boundsY=entityBoundsDTO.getBoundsY();
        if(entityBoundsDTO.isRectangle()){
            entityBounds.setBoundsRectangle(true);
            entityBounds.setBounds(boundsX, boundsY);
        }
        else if (entityBounds.getBounds()!=null){
            entityBounds.setBounds(polygon);
        }
        else{
            if (boundsX==0f && boundsY==0f){
                entityBounds.setHasBounds(false);
            }
        }
        entityBounds.setBoundsOffset(entityBoundsDTO.getxOffset(), entityBoundsDTO.getyOffset());
        entityBounds.setDrawBounds(entityBoundsDTO.isDrawBounds());

        return  entityBounds;
    }

}
