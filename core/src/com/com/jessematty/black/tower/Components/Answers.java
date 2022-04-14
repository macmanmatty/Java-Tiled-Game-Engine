package com.jessematty.black.tower.Components;

import com.badlogic.gdx.utils.ObjectMap;

public class Answers {

    private ObjectMap<String, String> questionsAndAnswers= new ObjectMap<>();
    private  String defaultResponse;
    public ObjectMap<String, String> getQuestionsAndAnswers() {
        return questionsAndAnswers;
    }
    public String getAnswer(String question){
        return questionsAndAnswers.get(question, defaultResponse);
    }
}
