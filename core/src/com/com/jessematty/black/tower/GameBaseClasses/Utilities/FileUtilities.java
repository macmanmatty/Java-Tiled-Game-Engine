package com.jessematty.black.tower.GameBaseClasses.Utilities;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class FileUtilities {
        private static ArrayList<File> files = new ArrayList<File>();
        private static  ArrayList<String> fileNames = new ArrayList<String>();
        private  static Character exclude = new Character('?'); // exclude files or folders  that start with this char
        private static   ArrayList<File> directories = new ArrayList<File>();
        private  static  List<String> imageExtensions = new ArrayList<String>(); // list of supported images for album artwork
    private static  List<String> codecExtensions = new ArrayList<String>();
    private static  String fileSeperator;
        private  FileUtilities() {
            imageExtensions.add("jpg");
            imageExtensions.add("png");
        }

        public static String  getFileSeparator(){
            fileSeperator=System.getProperty("file.separator");

            return  fileSeperator;

        }

    public static String getOperatingSystem(){
        return System.getProperties().getProperty("os.name").toLowerCase();
    }
        public static ArrayList<File> findFiles(String path) {
            files.clear();
            fileNames.clear();
            return  findFilesInternal(path);
        }
        private static  ArrayList<File> findFilesInternal(String path){
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles((FileFilter) null);
            ArrayList<String> names = new ArrayList<String>();
            int size = listOfFiles.length;
            for (int count = 0; count < size; count++) {
                if (!(listOfFiles[count].getName().charAt(0) == exclude)) {
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
        public static  List<File> findFiles(String path, List<String> extensions) {
            files.clear();
            fileNames.clear();
            return  findFilesInternal(path, extensions);
        }
        private  static List<File> findFilesInternal(String path, List<String> extensions) {
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles((FileFilter) null);
            ArrayList<String> names = new ArrayList<String>();
            int size = listOfFiles.length;
            for (int count = 0; count < size; count++) {
                File file=listOfFiles[count];
                if (!(listOfFiles[count].getName().charAt(0) == exclude)) {
                    if (listOfFiles[count].isFile()) {
                        if(matchesExtension(file, extensions)) {
                            files.add(file);
                            fileNames.add(file.getName());
                        }
                    } else if (listOfFiles[count].isDirectory()) {
                        findFiles(listOfFiles[count].getPath());
                    }
                }
            }
            return files;
        }
    public static  List<File> findFiles(List<File> filesToSearch) {
        files.clear();
        fileNames.clear();
        return  findFilesInternal(filesToSearch);
    }



    public static  List<File>  actOnFiles(List<File> files, FileAction fileAction) {
        files.clear();
        fileNames.clear();
     return actOnFilesInternal( files, fileAction);
    }

    public static  List<File> actOnFiles(String path, FileAction fileAction) {
        files.clear();
        fileNames.clear();
        return  actOnFilesInternal(path, fileAction);
    }

    private static  List<File> actOnFilesInternal(String path, FileAction fileAction){ // gets all assetts names used as strings in WoodWand selceted directory with given path

        File folder= new File(path);
        File [] listOfFiles=folder.listFiles((FileFilter) null);
        ArrayList<String> names= new ArrayList<String>();

        int size=listOfFiles.length;
        for (int count = 0; count < size; count++) {
            if(!(listOfFiles[count].getName().charAt(0)==exclude)) {
                File file=listOfFiles[count];
                if (file.isFile()) {
                    files.add(file);
                    fileNames.add(file.getName());
                    try {
                        fileAction.act(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {


                    actOnFilesInternal(file.getPath(), fileAction);


                }

            }

        }





        return files;

    }


    private static List<File> actOnFilesInternal(List<File> filesToSearch, FileAction fileAction) {
        int size=filesToSearch.size();
        for(int count=0; count<size; count++){
            File file=filesToSearch.get(count);
            if(file.isFile()){
                files.add(file);
                fileNames.add(file.getName());
                try {
                    fileAction.act(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(file.isDirectory()){
                List<File> files= findFilesInternal(file.getAbsolutePath());
                actOnFilesInternal(files, fileAction);
            }
        }
        return files;
    }




    public static ArrayList<File> findFilesInternal(List<File> filesToSearch) {
            int size=filesToSearch.size();
            for(int count=0; count<size; count++){
                File file=filesToSearch.get(count);
                if(file.isFile()){
                    files.add(file);
                    fileNames.add(file.getName());
                }
                else if(file.isDirectory()){
                    List<File> files= findFilesInternal(file.getAbsolutePath());
                    files.addAll(files);
                }
            }
            return files;
        }
        public static  ArrayList<File> findFiles(List<File> filesToSearch, List<String> extensions) {
            int size=filesToSearch.size();
            for(int count=0; count<size; count++){
                File file=filesToSearch.get(count);
                if(file.isFile() ){
                    if(matchesExtension(file, extensions)) {
                        files.add(file);
                    }
                }
                else if(file.isDirectory()){
                    List<File> files= findFilesInternal(file.getAbsolutePath(), extensions);
                    files.addAll(files);
                }
            }
            return files;
        }
        public static  ArrayList<File> findDirectories(String path) { // gets all  directories in a given directiory including subDirectories
            return findFilesInternal(path);
        }
        public static  ArrayList<File> findDirectoriesInternal(String path){ // gets all  directories in a given directiory including subDirectories
            File folder= new File(path);
            File [] listOfFiles=folder.listFiles();
            int size=listOfFiles.length;
            for (int count = 0; count < size; count++) {
                if (listOfFiles[count].isDirectory()) { // if it is a  directory search it by recursivly calling this method.
                    directories.add(listOfFiles[count]);
                    findDirectories(listOfFiles[count].getPath());
                }
            }
            return directories;
        }
        public  static ArrayList<String> getSingleFileNames( String path){
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
        public  static  String getExtensionOfFile(File file) // returns the extension of given file like .png or .tmx ECT.
        {
            String fileExtension="";
            // Get file Name first
            String fileName=file.getName();
            // If fileName do not contain "." or starts with "." then it is not a valid file
            if(fileName.contains(".") && fileName.lastIndexOf(".")!= 0)
            {
                fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
            }
            return fileExtension;
        }
        public static  boolean isImageFile(File file) { // checks to see the file extension matches on the  given audio file extensions
            String extension=getExtensionOfFile(file);
            int size= imageExtensions.size();
            for( int count=0; count<size; count++){
                if(extension.equalsIgnoreCase(imageExtensions.get(count))){
                    return true;
                }
            }
            return false;
        }

           public static  boolean createDirectories(String path){
        // creates directories from a path  to a directory returns true if the directory was created or if it allready exists returns false if the
        // directory was not created  or if the path  is not to a  directory.
        File file= new File(path);
        if(!file.isDirectory()){
            return false;

        }


        if( !file.exists()) {
            return file.mkdirs();
        }

        return true;


    }

    public static  File createFile(String path, String fileName) throws IOException {
        // creates directories from a path  to a directory returns the file or null if no file was created
        if(fileName==null ||  path==null|| path.isEmpty()){

            return null;

        }
        File directory= new File(path);

        if( !directory.exists()) {
             directory.mkdirs();
        }

        String fullPath=path+getFileSeparator()+fileName;

        File file= new File(fullPath);
        if(!file.exists()){

                file.createNewFile();


        }


        return file;



    }




    public static  boolean isAudioFile(File file) { // checks to see the file extension matches on the  given audio file extensions
            String extension=getExtensionOfFile(file);
            int size= codecExtensions.size();
            for( int count=0; count<size; count++){
                if(extension.equalsIgnoreCase(codecExtensions.get(count))){
                    return true;
                }
            }
            return false;
        }
        public static  ArrayList<String> getFileNames(String path) {
            findFiles(path);
            return fileNames;
        }
        public static  File renameDuplicateFile(File fileToMove, File directory) {
            String newName = "";
            boolean duplicateName = true;
            if (duplicateName == true) { // if  the file  with  same  name is already exists keeping counter to till files does not exist.
                int counter = 2;
                while (duplicateName == true) {
                    String fileName=fileToMove.getName();
                    String name= FilenameUtils.getBaseName(fileName);
                    String extension=FilenameUtils.getExtension(fileName);
                    newName=name+counter+"."+extension;
                    duplicateName = checkFileName(directory.listFiles(), newName);
                    counter++;
                }
            }
            fileToMove= reNameFile(fileToMove, newName);
            return fileToMove;
        }
        private static  boolean checkFileName(File[] files, String name) {
            int size = files.length;
            for (int count = 0; count < size; count++) {
                String name2 = files[count].getName();
                if (name.equals(name2)) {
                    return true;
                }
            }
            return false;
        }
        private  static File  reNameFile(File fileToMove, String newName){
            File renamedFile=  new File(fileToMove.getParentFile().getAbsolutePath()+fileSeperator+newName);
            fileToMove.renameTo(renamedFile);
            return renamedFile;
        }
        public  static boolean matchesExtension(File file, List<String> extensions){
            String fileExtension=getExtensionOfFile(file);
            int size=extensions.size();
            for(int count=0; count<size; count++){
                if(fileExtension.equalsIgnoreCase(extensions.get(count))){
                    return  true;
                }
            }
            return false;
        }
        public static   void copyFileToFile(File in, File out) throws IOException {
            FileChannel inChannel = new
                    FileInputStream(in).getChannel();
            FileChannel outChannel = new
                    FileOutputStream(out).getChannel();
            try {
                inChannel.transferTo(0, inChannel.size(),
                        outChannel);
            }
            catch (IOException e) {
                throw e;
            }
            finally {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
            }
        }

    public  static Character getExclude() {
        return exclude;
    }
    public  static void setExclude(Character exclude) {
        exclude = exclude;
    }
}
