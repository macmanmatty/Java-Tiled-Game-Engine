package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.utils.Array;

import org.apache.commons.lang3.StringUtils;

public class NameUtilities {


    /**
     * @param usedNames
     * @param currentName
     * @return
     */

    public static String newTextureRegionName(Array<String> usedNames, String currentName) {
        String lastChar = String.valueOf((currentName.charAt(currentName.length() - 1)));
        int counter = 2;
        String letter = "A";
        boolean useCounter = false;
        if (StringUtils.isAlpha(lastChar)) {
            useCounter = true;

        }
        String newName = currentName;
        while (InList.isInList(usedNames, newName)) {
            if (useCounter) {
                newName = currentName + counter;
                counter++;
            } else {
                newName = currentName + letter;
                letter = getNextLetter(letter);
                if (letter.equals("[")) {
                    currentName = newName;
                    letter = "A";

                }

            }

        }

        return newName;

    }




    /**
     *
     * @param letter
     * @return
     */
    public static String getNextLetter(String letter){
        if( letter.length()!=1 || !StringUtils.isAlpha(letter) || StringUtils.isEmpty(letter)){
            throw new IllegalArgumentException("String Letter Length Must Be One and Must Be A Letter");
        }
        int charValue = letter.charAt(0);
        String next = String.valueOf( (char) (charValue + 1));
        return  next;

    }


}
