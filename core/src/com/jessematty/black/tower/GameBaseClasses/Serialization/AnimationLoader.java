package com.jessematty.black.tower.GameBaseClasses.Serialization;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCAnimations;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class AnimationLoader {
   private  ObjectMap<String , LPCAnimations> animations= new ObjectMap<>();
   private AnimatableComponent animatableComponent;


    public void getFileNames(File file){


        File [] files=file.listFiles();

        int size=files.length;
        for(int count=0; count<size; count++){
            File currentFile=files[count];
            if(currentFile.isDirectory()){

                getFileNames(currentFile);
            }

            else{

                String fileName=currentFile.getName();


                String []  names = fileName.split("Female|female");

                if (names[0] != fileName&&!(names[0].isEmpty())) {
                    String nameToKeep = names[0];
                    String fullPath=currentFile.getAbsolutePath();
                    LPCAnimations lpcAnimation = animations.get(nameToKeep);
                    if (lpcAnimation ==null) {
                        lpcAnimation =new LPCAnimations(nameToKeep);
                        animations.put(nameToKeep, lpcAnimation);
                        lpcAnimation.hasFemale=true;
                        lpcAnimation.filePath= StringUtils.removeEnd(fullPath, fileName);
                        lpcAnimation.femaleName=fileName;


                    } else {
                        lpcAnimation.numberOfImages++;


                    }
                    hasNames( fullPath, lpcAnimation, fileName);
                }
                else {
                    names = fileName.split("Male|male");
                    if (names[0] != fileName&&!(names[0].isEmpty())) {
                        String nameToKeep = names[0];
                        String fullPath=currentFile.getAbsolutePath();




                        LPCAnimations lpcAnimation = animations.get(nameToKeep);
                        if (lpcAnimation ==null) {
                            lpcAnimation =new LPCAnimations(nameToKeep);
                            animations.put(nameToKeep, lpcAnimation);
                            lpcAnimation.hasMale=true;
                            lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);
                            lpcAnimation.maleName=fileName;


                        } else {
                            lpcAnimation.numberOfImages++;


                        }
                        hasNames( fullPath, lpcAnimation, fileName);
                    } else {

                        names = fileName.split("Child|child");

                        if (names[0] != fileName&&!(names[0].isEmpty())) {
                            String nameToKeep = names[0];
                            String fullPath=currentFile.getAbsolutePath();




                            LPCAnimations lpcAnimation = animations.get(nameToKeep);
                            if (lpcAnimation ==null) {
                                lpcAnimation =new LPCAnimations(nameToKeep);
                                animations.put(nameToKeep, lpcAnimation);
                                lpcAnimation.hasFemale=true;
                                lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);
                                lpcAnimation.childName=fileName;


                            } else {
                                lpcAnimation.numberOfImages++;


                            }
                            hasNames( fullPath, lpcAnimation, fileName);
                        } else {
                            names = fileName.split("Skeleton|skeleton|skelton|Skelton");

                            if (names[0] != fileName&&!(names[0].isEmpty())) {
                                String nameToKeep = names[0];
                                String fullPath=currentFile.getAbsolutePath();




                                LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                if (lpcAnimation ==null) {
                                    lpcAnimation =new LPCAnimations(nameToKeep);
                                    animations.put(nameToKeep, lpcAnimation);
                                    lpcAnimation.hasFemale=true;
                                    lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);
                                    lpcAnimation.skeletonName=fileName;


                                } else {
                                    lpcAnimation.numberOfImages++;


                                }
                                hasNames( fullPath, lpcAnimation, fileName);
                            } else {
                                names = fileName.split("Lizard|lizard");

                                if (names[0] != fileName&&!(names[0].isEmpty())) {
                                    String nameToKeep = names[0];
                                    String fullPath=currentFile.getAbsolutePath();




                                    LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                    if (lpcAnimation ==null) {
                                        lpcAnimation =new LPCAnimations(nameToKeep);
                                        animations.put(nameToKeep, lpcAnimation);
                                        lpcAnimation.hasFemale=true;
                                        lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);
                                        lpcAnimation.lizardName=fileName;


                                    } else {
                                        lpcAnimation.numberOfImages++;


                                    }
                                    hasNames( fullPath, lpcAnimation, fileName);
                                } else {

                                    names = fileName.split("Die|die");

                                    if (names[0] != fileName&&!(names[0].isEmpty())) {
                                        String nameToKeep = names[0];
                                        String fullPath=currentFile.getAbsolutePath();




                                        LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                        if (lpcAnimation ==null) {
                                            lpcAnimation =new LPCAnimations(nameToKeep);
                                            animations.put(nameToKeep, lpcAnimation);
                                            lpcAnimation.hasFemale=true;
                                            lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);


                                        } else {
                                            lpcAnimation.numberOfImages++;


                                        }
                                        hasNames( fullPath, lpcAnimation, fileName);
                                    } else {
                                        if (names[0] != fileName&&!(names[0].isEmpty())) {
                                            String nameToKeep = names[0];
                                            String fullPath=currentFile.getAbsolutePath();




                                            LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                            if (lpcAnimation ==null) {
                                                lpcAnimation =new LPCAnimations(nameToKeep);
                                                animations.put(nameToKeep, lpcAnimation);
                                                lpcAnimation.hasFemale=true;
                                                lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);


                                            } else {
                                                lpcAnimation.numberOfImages++;


                                            }
                                            hasNames( fullPath, lpcAnimation, fileName);
                                        } else {

                                            names = fileName.split("Slash|slash");

                                            if (names[0] != fileName&&!(names[0].isEmpty())) {
                                                String nameToKeep = names[0];
                                                String fullPath=currentFile.getAbsolutePath();




                                                LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                                if (lpcAnimation ==null) {
                                                    lpcAnimation =new LPCAnimations(nameToKeep);
                                                    animations.put(nameToKeep, lpcAnimation);
                                                    lpcAnimation.hasFemale=true;
                                                    lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);


                                                } else {
                                                    lpcAnimation.numberOfImages++;


                                                }
                                                hasNames( fullPath, lpcAnimation, fileName);
                                            } else {

                                                names = fileName.split("Thrust|thrust");

                                                if (names[0] != fileName&&!(names[0].isEmpty())) {
                                                    String nameToKeep = names[0];
                                                    String fullPath=currentFile.getAbsolutePath();




                                                    LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                                    if (lpcAnimation ==null) {
                                                        lpcAnimation =new LPCAnimations(nameToKeep);
                                                        animations.put(nameToKeep, lpcAnimation);
                                                        lpcAnimation.hasFemale=true;
                                                        lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);


                                                    } else {
                                                        lpcAnimation.numberOfImages++;


                                                    }
                                                    hasNames( fullPath, lpcAnimation, fileName);
                                                } else {

                                                    if (names[0] != fileName&&!(names[0].isEmpty())) {
                                                        String nameToKeep = names[0];
                                                        String fullPath=currentFile.getAbsolutePath();




                                                        LPCAnimations lpcAnimation = animations.get(nameToKeep);
                                                        if (lpcAnimation ==null) {
                                                            lpcAnimation =new LPCAnimations(nameToKeep);
                                                            animations.put(nameToKeep, lpcAnimation);
                                                            lpcAnimation.hasFemale=true;
                                                            lpcAnimation.filePath=StringUtils.removeEnd(fullPath, fileName);


                                                        } else {
                                                            lpcAnimation.numberOfImages++;


                                                        }
                                                        hasNames( fullPath, lpcAnimation, fileName);
                                                    }


                                                }


                                            }


                                        }


                                    }


                                }


                            }

                        }

                    }
                }

            }


        }





    }

    public void  hasNames(String fullPath, LPCAnimations lpcAnimation, String fileName){
        if (StringUtils.containsIgnoreCase(fileName, "walk")){
            lpcAnimation.hasWalk=true;

        }

        if (StringUtils.containsIgnoreCase(fileName, "thrust")){
            lpcAnimation.hasThrust=true;

        }
        if (StringUtils.containsIgnoreCase(fileName, "shoot")){
            lpcAnimation.hasShoot=true;

        }

        if (StringUtils.containsIgnoreCase(fileName, "slash")){
            lpcAnimation.hasSlash=true;

        }

        if (StringUtils.containsIgnoreCase(fileName, "spellCast")){
            lpcAnimation.hasSpellCast=true;

        }

        if (StringUtils.containsIgnoreCase(fileName, "die")){
            lpcAnimation.hasDie=true;

        }

        if (StringUtils.containsIgnoreCase(fileName, "female")){
            lpcAnimation.hasFemale=true;
            lpcAnimation.femaleName=fileName;

        }
        else if (StringUtils.containsIgnoreCase(fileName, "male")){
            lpcAnimation.hasMale=true;
            lpcAnimation.maleName=fileName;

        }

        if (StringUtils.containsIgnoreCase(fileName, "child")){
            lpcAnimation.hasChild=true;
            lpcAnimation.childName=fileName;

        }

        if (StringUtils.containsIgnoreCase(fileName, "lizard")){
            lpcAnimation.hasLizard=true;
            lpcAnimation.lizardName=fileName;

        }

        if (StringUtils.containsIgnoreCase(fileName, "skeleton")){
            lpcAnimation.hasSkeleton=true;
            lpcAnimation.skeletonName=fileName;

        }

        if (StringUtils.containsIgnoreCase(fileName, "skelton")){
            lpcAnimation.hasSkeleton=true;
            lpcAnimation.skeletonName=fileName;


        }
        if (StringUtils.containsIgnoreCase(fullPath,  "bodyPart")){
            lpcAnimation.isBodyPart=true;
            lpcAnimation.kind="bodyPart";


        }

        if (StringUtils.containsIgnoreCase(fullPath,  "body ")){
            lpcAnimation.isBody=true;
            lpcAnimation.kind="body";

        }

        if (StringUtils.containsIgnoreCase(fullPath,  "armor")){
            lpcAnimation.isArmor=true;
            lpcAnimation.kind="armor";


        }
        if (StringUtils.containsIgnoreCase(fullPath,  "weapon")){
            lpcAnimation.isWeapon=true;
            lpcAnimation.kind="weapon";


        }

        if (StringUtils.containsIgnoreCase(fullPath,  "shield")){
            lpcAnimation.isShield=true;
            lpcAnimation.kind="shield";


        }

        if (StringUtils.containsIgnoreCase(fullPath,  "pack")){
            lpcAnimation.isPack=true;
            lpcAnimation.kind="pack";


        }
        if (StringUtils.containsIgnoreCase(fullPath,  "grayScale") ||StringUtils.containsIgnoreCase(fullPath,  "gray Scale")|| StringUtils.containsIgnoreCase(fullPath,  "greyScale") ||StringUtils.containsIgnoreCase(fullPath,  "grey Scale") ){
            lpcAnimation.isGrayScale=true;

        }



    }

    public class AnimationLoaderSpecs{
        private String splitOn=".";
        private int layerNumberUp=0;
        private int layerNumberDown=0;
        private int layerNumberLeft=0;
        private int layerNumberRight=0;
        private int layerNumberRightUp=0;
        private int layerNumberRightDown=0;
        private int layerNumberLeftUp=0;
        private int layerNumberLeftDown=0;
        private NamedColor color= NamedColor.WHITE;
        private float brightness=1;
        private int frameRate=1;
        private Vector2 offsets= new Vector2();
        private String name;

    }





}
