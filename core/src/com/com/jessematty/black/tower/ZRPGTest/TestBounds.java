package com.jessematty.black.tower.ZRPGTest;

import com.jessematty.black.tower.Components.Position.EntityBounds;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Generators.Components.Animation.AnimatableComponentDTO;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationDTO;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationDirections;
import com.jessematty.black.tower.Generators.Components.BoundsChangeable.BoundsChangeableComponentDTO;
import com.jessematty.black.tower.Generators.Components.BoundsChangeable.EntityBoundsDTO;

public class TestBounds {
    static BoundsChangeableComponentDTO lizardBounds= new BoundsChangeableComponentDTO();

    static EntityBoundsDTO up= new EntityBoundsDTO();
    static EntityBoundsDTO down= new EntityBoundsDTO();
    static EntityBoundsDTO left= new EntityBoundsDTO();
    static EntityBoundsDTO right= new EntityBoundsDTO();



    static {
        lizardBounds.setEightDirections(false);
        up.setBounds(64, 64);
        up.setDirection(Direction.UP.toString());
        down.setBounds(64, 40);
        down.setDirection(Direction.DOWN.toString());
        left.setBounds(40, 64);
        left.setDirection(Direction.LEFT.toString());
        right.setBounds(40, 64);
        right.setDirection(Direction.RIGHT.toString());

        lizardBounds.getBoundsDTOArray().add(up);
        lizardBounds.getBoundsDTOArray().add(down);
        lizardBounds.getBoundsDTOArray().add(left);
        lizardBounds.getBoundsDTOArray().add(right);

    }
}
