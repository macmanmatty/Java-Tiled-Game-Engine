package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

public class Page implements Component {
   private  String text;
   private  String language;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
