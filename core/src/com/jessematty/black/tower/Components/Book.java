package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;


public class Book implements Component {
   private  Array<Page> pages= new Array<>();

    public Array<Page> getPages() {
        return pages;
    }
}
