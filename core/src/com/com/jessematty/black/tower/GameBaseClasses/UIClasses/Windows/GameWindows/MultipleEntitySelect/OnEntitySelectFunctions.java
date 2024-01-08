package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.EntityInfoWindow;

public class OnEntitySelectFunctions {

    private final MapDraw mapDraw;

    public OnEntitySelectFunctions(MapDraw mapDraw) {
        this.mapDraw = mapDraw;
    }

    public OnSelected<Entity> packDisplay = new OnSelected<Entity>() {
        @Override
        public void onSelected(Entity item) {
            ContainerComponent packComponent = GameComponentMapper.getContainerComponentMapper().get(item);
            OnSelected<Entity> onSelected = new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity item) {
                    EntityInfoWindow entityInfoWindow= new EntityInfoWindow(mapDraw.getGameAssets().getCurrentSkin(), "default", item, mapDraw.getGameAssets());
                    mapDraw.getUiStage().addWindow(entityInfoWindow, ScreenPosition.CENTER);
                }
            };
            if (packComponent != null) {
                EntitySelectWindow packDisplay = new EntitySelectWindow("Pack Contents", mapDraw.getGameAssets().getCurrentSkin(), mapDraw.getWorld().getEntitiesFromEntityIdsArray(packComponent.getEntitiesInContainerIds()), mapDraw.getGameAssets(), "Info", onSelected);
                mapDraw.getUiStage().addWindow(packDisplay, ScreenPosition.CENTER);
            }

        }
    };

}
