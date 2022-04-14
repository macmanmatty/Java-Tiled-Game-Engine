package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileSelect {

    JFrame frame= new JFrame();
    JFileChooser fileChooser= new JFileChooser();

    public FileSelect() {

        frame.add(fileChooser);

    }

    public File getFile(){
        frame.setVisible(true);
        File file=fileChooser.getSelectedFile();
        return file;


    }
}
