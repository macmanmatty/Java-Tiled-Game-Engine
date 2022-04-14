package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Engine;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Table.UITable;

public class UIBarSystem extends GameEntitySystem {


       private UITable topBar;
       private UITable bottomBar;
       private UITable leftBar;
       private UITable rightBar;


    public UIBarSystem(MapDraw draw) {
        super(draw);

    }



    @Override
    public void addedToEngine(Engine engine) {


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

    public UITable getTopBar() {
        return topBar;
    }

    public void setTopBar(UITable topBar) {
        this.topBar = topBar;
    }

    public UITable getBottomBar() {
        return bottomBar;
    }

    public void setBottomBar(UITable bottomBar) {
        this.bottomBar = bottomBar;
    }

    public UITable getLeftBar() {
        return leftBar;
    }

    public void setLeftBar(UITable leftBar) {
        this.leftBar = leftBar;
    }

    public UITable getRightBar() {
        return rightBar;
    }

    public void setRightBar(UITable rightBar) {
        this.rightBar = rightBar;
    }
}
