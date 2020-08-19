package com.jessematty.black.tower.GameBaseClasses.Utilities;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

public class FileWalker {
  ArrayList<File>   files= new ArrayList<File>();
  ArrayList<String> fileNames= new ArrayList<String>();
  Character exclude= new Character('?'); // exclude files or folders  that start with this char








    public ArrayList<File> findFiles(String path){ // gets all assetts names used as strings in WoodWand selceted directory with given path
        // returns  the fisrt part of the name before the number  like tile1 tile2 tile 3 would save the name  tile.
        // these are later use dfor displaying and selecting texture regions

        File folder= new File(path);
        File [] listOfFiles=folder.listFiles((FileFilter) null);
        ArrayList<String> names= new ArrayList<String>();

        int size=listOfFiles.length;
        for (int count = 0; count < size; count++) {
            if(!(listOfFiles[count].getName().charAt(0)==exclude)) {
                if (listOfFiles[count].isFile()) {
                    files.add(listOfFiles[count]);
                    fileNames.add(listOfFiles[count].getName());
                } else if (listOfFiles[count].isDirectory()) {


                    findFiles(listOfFiles[count].getPath());


                }

            }

        }





        return files;

    }


    public ArrayList<String> getSingleFileNames( String path){

        findFiles(path);



        Collections.sort(fileNames);
         int size=fileNames.size();
        String name1=fileNames.get(0);

        ArrayList<String> singleNames= new ArrayList<String>(100);
        singleNames.add(name1);
        for(int count=1; count<size; count++){
            String name2=fileNames.get(count);

            if(!(name1.equals(name2))){
                singleNames.add(name2);

            }


        }
       return singleNames;




    }



    public  String getExtensionOfFile(File file) // returns the extension of given file like .png or .tmx ECT.
    {
        String fileExtension="";
        // Get file Name first
        String fileName=file.getName();

        // If fileName do not contain "." or starts with "." then it is not WoodWand valid file
        if(fileName.contains(".") && fileName.lastIndexOf(".")!= 0)
        {
            fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
        }

        return fileExtension;
    }




    public ArrayList<String> getFileNames(String path) {
        findFiles(path);
        return fileNames;
    }


    public char getExclude() {
        return exclude;
    }

    public void setExclude(char exclude) {
        this.exclude = exclude;
    }
}
