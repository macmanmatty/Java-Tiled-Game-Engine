package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;


import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class NumberedButton extends Button {
	
		int number;
		
		
		
		public NumberedButton(int number,  Skin skin){
			super(skin);

			this.number=number;
			
			
		}
		public int getNumber(){
			return number;
		}
		
		
		
		
		

}
