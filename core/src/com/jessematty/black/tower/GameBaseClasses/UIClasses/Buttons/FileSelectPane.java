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

public class FileSelectPane extends VerticalGroup  {

    private JFileChooser chooser;
    private FileAction fileAction;
    private  Label pathLabel;
    private  File file;
    private FileSelectButton fileSelectButton;
    private boolean directories;
    private boolean displayPath;

    public FileSelectPane(FileAction fileAction, Skin skin, String style, String buttonText, boolean directories, boolean displayPath) {
        this.fileAction = fileAction;
        this.pathLabel = pathLabel;
        this.pathLabel= new Label("empty", skin);
        fileSelectButton= new FileSelectButton(buttonText, skin, style, fileAction);
        this.directories=directories;
        addActor(fileSelectButton);
        if(displayPath==true) {
            addActor(pathLabel);
        }

        this.displayPath=displayPath;

    }


    public FileSelectPane(FileAction fileAction, Skin skin, boolean directories) {
       this(fileAction, skin, "default", "Select File", directories, true);

    }

    public FileSelectPane( Skin skin, String style, String buttonText, boolean directories) {
       this(null, skin, style, buttonText, directories, true);
    }

    public FileSelectPane( Skin skin, String buttonText, boolean directories) {
      this( skin, "default", buttonText,  directories);
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
                            if(directories==true) {
                                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            }
                            else{
                                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);


                            }
                            int option = chooser.showOpenDialog(null);
                            if (option == JFileChooser.APPROVE_OPTION) {
                                 file = chooser.getSelectedFile();
                                pathLabel.setText(file.getAbsolutePath());
                                if (fileAction != null) {
                                   Runnable runnable= new Runnable() {
                                        @Override
                                        public void run() {
                                            fileAction.act(file);

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

    public boolean isDirectories() {
        return directories;
    }

    public void setDirectories(boolean directories) {
        this.directories = directories;
    }

    public boolean isDisplayPath() {
        return displayPath;
    }

    public void setDisplayPath(boolean displayPath) {
        this.displayPath = displayPath;
        pathLabel.addAction(Actions.removeActor());
    }
}
