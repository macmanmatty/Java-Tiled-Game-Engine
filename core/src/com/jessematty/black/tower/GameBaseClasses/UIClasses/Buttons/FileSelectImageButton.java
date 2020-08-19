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
