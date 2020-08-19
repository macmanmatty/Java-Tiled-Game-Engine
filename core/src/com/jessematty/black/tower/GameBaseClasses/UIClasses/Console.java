package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import java.util.ArrayList;


public class Console {

	VerticalGroup consoleDisplay;
	ArrayList<Label> consoleLabels;
	Skin skin;
	Console(Skin skin){
		consoleLabels= new ArrayList<Label>();
		this.skin=skin;
		
		
		
	}
	
	public void  addDisplayInfo(String text){ // adds text to the bottom console
		Label label = new Label(text,skin );
		consoleLabels.add(label);
		int size=consoleLabels.size();
	
		
		
		
		
		
		
		
		
	}
	
private ScrollPane displayConsole(){ // get the vbox to display
	consoleDisplay=new VerticalGroup();

		int size=consoleLabels.size();
		
		for (int count=0 ; count<size; count++){
			consoleDisplay.getChildren().add(consoleLabels.get(count));
		}
		
		
		return  new ScrollPane(consoleDisplay);
		
		
		
		
	}
		
	public ScrollPane	getConsole(){
			return displayConsole();
		}
		
		             
		 
		
	
	
	

}
