package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *  class for a libGDX button that open a java swing jFileChooser for picking of files on desktop
 */


public class FileSelectPane extends VerticalGroup  {
    private JFileChooser chooser; // the java swing file chooser class
    private FileAction fileAction; // the file action call back method used for preforming action the file after is has been loaded
    private  Label pathLabel; // a libGDX label displaying path to the label
    private  File file; //the file you selected
    private FileSelectButton fileSelectButton; // button to select a file
    private FileNameExtensionFilter fileNameExtensionFilter; // a filter to limit which file extensions are allowed
    private boolean displayPath; // whether or not display the file path
    private int fileSelectMode; // 0= files 1=directories 2=files and directories
    public FileSelectPane(FileAction fileAction, Skin skin, String style, String buttonText,  boolean displayPath, int fileSelectionMode) {
        this.fileAction = fileAction;
        this.pathLabel = pathLabel;
        this.pathLabel= new Label("empty", skin);
        fileSelectButton= new FileSelectButton(buttonText, skin, style, fileAction);
        addActor(fileSelectButton);
        this.fileSelectMode=fileSelectionMode;
         if(displayPath==true) {
            addActor(pathLabel);
        }
        this.displayPath=displayPath;
    }
    public FileSelectPane(FileAction fileAction, Skin skin, int mode) {
       this(fileAction, skin, "default", "Select File", true, mode);
    }
    public FileSelectPane( Skin skin, String style, String buttonText, int mode) {
       this(null, skin, style, buttonText,  true, mode);
    }
    public FileSelectPane( Skin skin, String buttonText, int mode) {
      this( skin, "default", buttonText, mode);
    }
    private class FileSelectButton extends TextButton {
        public FileSelectButton(String text, Skin skin) {
            this(text, skin,"default");
        }
        public FileSelectButton(String text, Skin skin, FileAction action) {
            this(text, skin);
            fileAction = action;
        }
        public FileSelectButton(String text, Skin skin, String style, FileAction action) {
            this(text, skin, style);
        }
        public FileSelectButton(String text, Skin skin, String style) {
            super(text, skin, style);
            addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            chooser = new JFileChooser() {
                            };
                            chooser.setSize(500, 500);
                            chooser.setFileSelectionMode(fileSelectMode);
                            if(fileNameExtensionFilter!=null) {
                                chooser.addChoosableFileFilter(fileNameExtensionFilter);
                            }
                            int option = chooser.showOpenDialog(null);
                            if (option == JFileChooser.APPROVE_OPTION) {
                                 file = chooser.getSelectedFile();
                                pathLabel.setText(file.getAbsolutePath());
                                if (fileAction != null) {
                                   Runnable runnable= new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                fileAction.act(file);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                            };
                                   Gdx.app.postRunnable(runnable);
                                }
                            }
                        }
                    });
                    SwingUtilities.invokeLater(thread);
                }
            });
        }
        public File getFile() {
            if (chooser != null) {
                return chooser.getSelectedFile();
            } else {
                return null;
            }
        }
    }
    public File getFile() {
        return file;
    }
    public FileSelectButton getFileSelectButton() {
        return fileSelectButton;
    }
    public void setFileAction(FileAction fileAction) {
        this.fileAction = fileAction;
    }
    public boolean isDisplayPath() {
        return displayPath;
    }
    public void setFileTypes(String name, String ... fileTypes){
        fileNameExtensionFilter= new FileNameExtensionFilter(name, fileTypes);
    }
    public void setDisplayFilePath(boolean displayPath) {
        this.displayPath = displayPath;
        pathLabel.addAction(Actions.removeActor());
    }
}
