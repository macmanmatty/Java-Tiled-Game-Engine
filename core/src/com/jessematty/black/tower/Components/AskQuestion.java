package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class AskQuestion implements Component {

        private ObjectMap<String, String> answers = new ObjectMap<>();



    private  String defaultAnswer;

    public String getAnswer(String question){
        String answer= answers.get(question);

        if(answer!=null) {


            return  answer;
        }

     return defaultAnswer;


    }


    public String getDefaultAnswer() {
        return defaultAnswer;
    }

    public void setDefaultAnswer(String defaultAnswer) {
        this.defaultAnswer = defaultAnswer;
    }

    public ObjectMap<String, String> getAnswers() {
        return answers;
    }


}

