package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class ErrorComponent implements Component {
  private   String errorMessage;
  private String title="Error!";
  private String buttonText="ok";


    public ErrorComponent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorComponent(String errorMessage, String title) {
        this.errorMessage = errorMessage;
        this.title = title;
    }

    public ErrorComponent(String errorMessage, String title, String buttonText) {
        this.errorMessage = errorMessage;
        this.title = title;
        this.buttonText = buttonText;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getTitle() {
        return title;
    }

    public String getButtonText() {
        return buttonText;
    }
}
