package com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputMultiplexer;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;

import java.util.List;
public class OptionPane extends Dialog{
	private int count;
	private boolean lockInput;
	
	public OptionPane(Skin skin, String title, String text, String buttonText) {
		super(title, skin);
	
		text(text);
		TextButton button = new TextButton(buttonText, skin);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			hide();
				return true;
			}
		});
		add(button);
		
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setSize(getPrefWidth(), getPrefHeight());
	}
	public OptionPane( Skin skin, String title, String text, String buttonText, AtlasRegion imageView) {
 
		super(title, skin);
		text(text);
		TextButton button = new TextButton(buttonText, skin);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			hide();
				return true;
			}
		});
		Image image = new Image(imageView);
		addActor(image);
		add(button);
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setSize(getPrefWidth(), getPrefHeight());

	}

	public OptionPane(Skin skin, String title,  String text, String buttonText, String button2Text, Image image,  final OptionPaneAction optionPaneAction) {
		super(title, skin);
	text(text);
		TextButton button = new TextButton(buttonText, skin);
		TextButton button2 = new TextButton(button2Text, skin);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				hide();
				optionPaneAction.act();
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

	}
	public OptionPane(Skin skin, String title,  String text, String buttonText, String button2Text, final OptionPaneAction optionPaneAction) {
		super(title, skin);
		text(text);
		TextButton button = new TextButton(buttonText, skin);
		TextButton button2 = new TextButton(button2Text, skin);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				optionPaneAction.act();
				hide();
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
		add(button);
		add(button2);
		setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setSize(getPrefWidth(), getPrefHeight());

	}
		
		
		
		public OptionPane(Skin skin, String title,   String text, List<String> buttonText, final List<OptionPaneAction> actions){
			super(title, skin);
			text(text);
			int size=buttonText.size();
			for ( count=0; count<size; count++){
				TextButton button = new TextButton(buttonText.get(count), skin);
				button.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						actions.get(count).act();
						hide();
						return true;
					}
				});
				add(button);
		    }
			setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			setSize(getPrefWidth(), getPrefHeight());

		}

	public boolean isLockInput() {
		return lockInput;
	}

	public void setLockInput(boolean lockInput) {
		this.lockInput = lockInput;
	}
}
