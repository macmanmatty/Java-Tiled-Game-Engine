package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

public class Quest implements Component {

    private String questGiverId;

    public String getQuestGiverId() {
        return questGiverId;
    }

    public void setQuestGiverId(String questGiverId) {
        this.questGiverId = questGiverId;
    }
}

