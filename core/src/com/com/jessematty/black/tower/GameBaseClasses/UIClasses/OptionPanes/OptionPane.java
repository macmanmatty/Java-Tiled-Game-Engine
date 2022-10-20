package com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputMultiplexer;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;

import java.util.List;
public class OptionPane extends Dialog{
	/**
	 * class for various option pane like dialogs used to display text  or give choices
	 */

	/**
	 *  Option Pane With A  Single Button the closes the pane upon clicking it
	 * @param skin the libGDX skin to be used wth option pane
	 * @param title the title of the option pane
	 * @param text the text of the option pane
	 * @param buttonText the buttons text
	 */
	
	public OptionPane(Skin skin, String title, String text, String buttonText, String style) {
		super(title, skin, style);

		text(text);
		TextButton button = new TextButton(buttonText, skin, style);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				closePane();
				return true;
			}

		});
		add(button);
		
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		pack();
		validate();
	}


	/**
	 *  Option Pane With An image and a   Single Button the closes the pane upon clicking it
	 * @param skin the libGDX skin to be used wth option pane
	 * @param title the title of the option pane
	 * @param text the text of the option pane
	 * @param imageView  the image to be displayed  on the OptionPane
	 * @param buttonText the buttons text
	 */
	public OptionPane( Skin skin, String title, String text, String buttonText, AtlasRegion imageView , String style) {
 
		super(title, skin, style);
		text(text);
		TextButton button = new TextButton(buttonText, skin, style);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				closePane();
				return true;
			}
		});
		Image image = new Image(imageView);
		add(image);
		add(button);
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setSize(getPrefWidth(), getPrefHeight());
		pack();
		validate();

	}
	/**
	 * Option Pane With  two buttons  and an Image  one  closes the pane upon clicking it the other button button performs  the passed in option pane acton.
	 * @param skin the libGDX skin to be used wth option pane
	 * @param title the title of the option pane
	 * @param text the text of the option pane
	 * @param image  the option panes image
	 * @param button2Text the text of the other button
	 * @param buttonText the buttons text
	 * @param optionPaneAction  the acton to be preformed  when the first button is clicked
	 */
	public OptionPane(Skin skin, String title,  String text, String buttonText, String button2Text, Image image,  final OptionPaneAction optionPaneAction, String style) {
		super(title, skin, style);
	text(text);
		TextButton button = new TextButton(buttonText, skin, style);
		TextButton button2 = new TextButton(button2Text, skin, style);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				optionPaneAction.act();
				closePane();
				return true;
			}
		});
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				hide();
				return true;
			}
		});
		addActor(image);
		add(button);
		add(button2);
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setSize(getPrefWidth(), getPrefHeight());
		pack();
		validate();

	}
	/**
	 * Option Pane With  two butons   one  closes the pane upon clicking it the other button button performs  the passed in option pane acton.
	 * @param skin the libGDX skin to be used wth option pane
	 * @param title the title of the option pane
	 * @param text the text of the option pane
	 * @param button2Text the text of the other button
	 * @param buttonText the buttons text
	 * @param optionPaneAction  the acton to be preformed  when the first button is clicked
	 */
	public OptionPane(Skin skin, String title,  String text, String buttonText, String button2Text, final OptionPaneAction optionPaneAction, String style) {
		super(title, skin, style);
		text(text);
		TextButton button = new TextButton(buttonText, skin, style);
		TextButton button2 = new TextButton(button2Text, skin, style);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				optionPaneAction.act();
				closePane();
				return true;
			}
		});
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				closePane();
				return true;
			}
		});
		add(button);
		add(button2);
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setSize(getPrefWidth(), getPrefHeight());
		pack();
		validate();

	}

	/**
	 * Option Pane With  many buttons that wll perform the   the passed in option pane actions upon clicking.
	 * @param skin the libGDX skin to be used wth option pane
	 * @param title the title of the option pane
	 * @param text the text of the option pane
	 * @param buttonText the buttons text
	 * @param actions  the actions to be preformed  when the buttons are clicked
	 */
		
		public OptionPane(Skin skin, String title,   String text, List<String> buttonText, final List<OptionPaneAction> actions,  String style){
			super(title, skin, style);
			text(text);
			int size=buttonText.size();
			for ( int count=0; count<size; count++){
				TextButton button = new TextButton(buttonText.get(count), skin, style);
				int finalCount = count;
				button.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						actions.get(finalCount).act();
					closePane();
						return true;
					}
				});
				add(button);
		    }
			setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			setSize(getPrefWidth(), getPrefHeight());
			pack();
			validate();

		}

	/**
	 * method to close the option pane hides the window and unlocks the input processors
	 */
	private void closePane(){
		hide();
		GameAssets.getGameInput().getLockableInputMultiplexer().unlockAllProcessors();

	}

}
