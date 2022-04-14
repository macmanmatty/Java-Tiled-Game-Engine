package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FileSelectImageButton extends ImageButton {
    private JFileChooser chooser;
    private JFrame frame= new JFrame();
    public FileSelectImageButton(TextureRegionDrawable textureRegion) {
        super(textureRegion);

        addListener( new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);


                Thread thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        chooser = new JFileChooser(){
                        };
                        chooser.setSize(500, 500);
                        JPanel panel= new JPanel();

                        panel.setSize(chooser.getWidth(), chooser.getHeight());
                        panel.add(chooser);
                        frame.add(panel);
                        frame.setSize(chooser.getWidth(),chooser.getHeight());
                        chooser.showOpenDialog(panel);
                        frame.setVisible(true);
                        action();
                        frame.setVisible(false);




                    }
                });
                SwingUtilities.invokeLater(thread);

            }
        });

    }

    /**
     *  adds a listener to the button so when clicked  a java swing JFrame with  a JFileChooser Opens
     */
    private void  addListenerToButton(){
        addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // create new thread as all swing UI MUST happen on the Java Swing UI thread
                Thread thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        chooser = new JFileChooser(){
                        };
                        // add chooser to JFrame
                        chooser.setSize(500, 500);
                        JPanel panel= new JPanel();
                        panel.setSize(chooser.getWidth(), chooser.getHeight());
                        panel.add(chooser);
                        frame.add(panel);
                        frame.setSize(chooser.getWidth(),chooser.getHeight());
                        chooser.showOpenDialog(panel);
                        frame.setVisible(true);
                        action();
                        frame.setVisible(false);
                    }
                });
                // run the The Chooser Code on the Java Swing UI Thread
                SwingUtilities.invokeLater(thread);
            }
        });
    }

    /**
     *  gets  the file file from the JFileChooser
     * @return File the selected from the JFileChooser
     */
    public File getFile(){
        if(chooser!=null) {
            return chooser.getSelectedFile();
        }
        else{
            return null;
        }
    }
    public void action(){


    }


}
