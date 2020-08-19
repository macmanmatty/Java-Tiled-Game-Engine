package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.jessematty.black.tower.Components.Container;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.UIWindow;

public class UIBarSystem extends GameEntitySystem {

       private  ComponentMapper<Pack> pack;
       private ComponentMapper<ID> idComponentMapper;
       private ComponentMapper<Item> itemComponentMapper;
       private ComponentMapper<Container> containerComponentMapper;
       private ComponentMapper<OwnerComponent> ownerComponent;
       private UIWindow topBar;
       private UIWindow bottomBar;
       private UIWindow leftBar;
       private UIWindow rightBar;


    public UIBarSystem(MapDraw draw) {
        super(draw);

    }



    @Override
    public void addedToEngine(Engine engine) {
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
        pack=getGameComponentMapper().getPackComponentMapper();


    }

    @Override
    public void update(float deltaTime) {
        if(bottomBar!=null) {
            bottomBar.update();
        }
        if(topBar!=null){
        topBar.update();
        }
        if(rightBar!=null){
            rightBar.update();

        }
        if(leftBar!=null) {
            leftBar.update();

        }


    }

    public UIWindow getTopBar() {
        return topBar;
    }

    public void setTopBar(UIWindow topBar) {
        this.topBar = topBar;
    }

    public UIWindow getBottomBar() {
        return bottomBar;
    }

    public void setBottomBar(UIWindow bottomBar) {
        this.bottomBar = bottomBar;
    }

    public UIWindow getLeftBar() {
        return leftBar;
    }

    public void setLeftBar(UIWindow leftBar) {
        this.leftBar = leftBar;
    }

    public UIWindow getRightBar() {
        return rightBar;
    }

    public void setRightBar(UIWindow rightBar) {
        this.rightBar = rightBar;
    }
}
