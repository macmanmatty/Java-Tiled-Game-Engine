package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TextureAtlas.TextureRegionPage;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.TextureAtlasRegionNames;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.Unused.Node;

import java.util.List;
public class InList {
	private InList() {
	}
    public  static boolean stringNonCaseSensitiveIsInList(String name, List<String> names) {
        int number = names.size();
        for (int count = 0; count < number; count++) {
            if (name.equalsIgnoreCase(names.get(count)) == true) {
                return true;
            }
        }
        return false;
    }
	public  static boolean isInList(Object object, List<? extends Object> names) {
		int number = names.size();
		for (int count = 0; count < number; count++) {
			if (object.equals(names.get(count)) == true) {
				return true;
			}
		}
		return false;
	}
	public  static boolean isInList(Object object, Object [] names) {
		int number = names.length;
		for (int count = 0; count < number; count++) {
			if (object.equals(names[count]) == true) {
				return true;
			}
		}
		return false;
	}
	public  static boolean isInList(Object object, Keys<? extends Object> keys) {
		while (keys.hasNext()){
			if (object.equals(keys.next())) {
				return true;
			}
		}
		return false;
	}
	public  static boolean isInList(Object object, Array<? extends Object> names) {
		int number = names.size;
		for (int count = 0; count < number; count++) {
			if (object.equals(names.get(count)) == true) {
				return true;
			}
		}
		return false;
	}
	public static boolean isInList(TextureAtlas atlas, TextureRegion region) {
		Array<AtlasRegion> regions=atlas.getRegions();
		int number = regions.size;
		for (int count = 0; count < number; count++) {
			if (TextureTools.textureRegionsEquals(regions.get(count), region)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isInList(TextureRegionPage textureRegionPage, String name) {
		Array<AtlasNamedAtlasRegion> regions=textureRegionPage.getPageRegions();
		int number = regions.size;
		for (int count = 0; count < number; count++) {
			if (regions.get(count).name.equals(name)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isInList(List<? extends Object> names, Array<? extends Object> otherNames) {
		int number = otherNames.size;
		int number2 = names.size();
		for (int count = 0; count < number; count++) {
			for (int count2 = 0; count2 < number2; count2++) {
				if (names.get(count2).equals(otherNames.get(count))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static  GameMap getMap(List<GameMap> maps, String Name) {
		int size = maps.size();
		for (int count = 0; count < size; count++) {
			if (maps.get(count).getGameMapSettings().getSimpleSetting("name", String.class).equals(Name)) {
				return maps.get(count);
			}
		}
		return maps.get(0);
	}
	public static  boolean isInList(List<? extends Object> names, List<? extends Object> otherNames) {
		int number = otherNames.size();
		int number2 = names.size();
		for (int count = 0; count < number; count++) {
			for (int count2 = 0; count2 < number2; count2++) {
				if (names.get(count2).equals(otherNames.get(count))) {
					return true;
				}
			}
		}
		return false;
	}
    public static  boolean isInList(List<? extends Object> names, Object  [] otherNames) {
        int number = otherNames.length;
        int number2 = names.size();
        for (int count = 0; count < number; count++) {
            for (int count2 = 0; count2 < number2; count2++) {
                if (names.get(count2).equals(otherNames[count])) {
                    return true;
                }
            }
        }
        return false;
    }
	public static  boolean isInList(Object  []  names, Object[] otherNames) {
		int number = otherNames.length;
		int number2 = names.length;
		for (int count = 0; count < number; count++) {
			for (int count2 = 0; count2 < number2; count2++) {
				if (names[count2].equals(otherNames[count])) {
					return true;
				}
			}
		}
		return false;
	}
	/** check if two lists have one object that is the same if so returns true else returns false
	 * also returns true if both lists are empty
	 */
	public static  boolean isInList(Array<? extends Object> names, Array<? extends Object> otherNames) {
		int size = otherNames.size;
		int size2 = names.size;
		if(size==0 && size2==0){
			return  true;
		}
		for (int count = 0; count < size; count++) {
			for (int count2 = 0; count2 < size2; count2++) {
				if (names.get(count2).equals(otherNames.get(count))) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean vectorCheck(Array<Node> nodes, int x, int y) {
		int number = nodes.size;
		for (int count = 0; count < number; count++) {
			int xNode = nodes.get(count).getxLocation();
			int yNode = nodes.get(count).getyLocation();
			if (xNode == x && yNode == y) {
				return true;
			}
		}
		return false;
	}
	public static int getIndex(Object  name, List<? extends Object> array) { // gets the index of a string in a list  of strings if string is not the list returns -1
		int number = array.size();
		for (int count = 0; count < number; count++) {
			if (name.equals(array.get(count)) == true) {
				return count;
			}
		}
		return -1;
	}
	
	
	public static  boolean isInList(Array<? extends Object> names, Object object) {
		int number = names.size;
		for (int count = 0; count < number; count++) {
			 Object object2 = names.get(count);
			if (object.equals(object2)) {
				return true;
			}
		}
		return false;
	}
	public static  boolean isInListOrAll(Iterable<String> names, String name) {
		if(name.equalsIgnoreCase("all")){
			return true;
		}
		for (String name2: names) {
			if (name.equals(name2)) {
				return true;
			}
		}
		return false;
	}

	/** checks if two lists have one object that is the same or if either list contains "all" if so returns true else returns false
	 * also returns true if both lists are empty
	 */
	public static  boolean isInListOrAll(Array<String> names, Array< String> otherNames) {
		int size = otherNames.size;
		int size2 = names.size;
		if(names.contains("all", false) || otherNames.contains("all", false)){
			return true;
		}

		if(size==0 && size2==0){
			return  true;
		}
		for (int count = 0; count < size; count++) {
			for (int count2 = 0; count2 < size2; count2++) {
				if (names.get(count2).equals(otherNames.get(count))) {
					return true;
				}
			}
		}
		return false;
	}
	/** checks to see if an object is in a given array
	 * if equals is false == will be used for comparison else
	 * the object equals method will be used
	 *
	 * @param names
	 * @param object
	 * @param equals
	 * @return
	 */
	public static  boolean isInList(Array<? extends Object> names, Object object, boolean equals) {
		int number = names.size;
		for (int count = 0; count < number; count++) {
			Object object2 = names.get(count);
			if (object.equals(object2) && equals==false) {
				return true;
			}
			else if (object.equals(object2)){
				return true;
			}
		}
		return false;
	}
	public static  boolean allInList(List list, List list2){
		int size=list.size();
		int size2=list2.size();
		if(size2>size){
			return  false;
		}
		for(int count=0; count<size; count++){
			for(int count2=0; count2<size2; count2++){
				if(!(list.get(count).equals(list2.get(count2)))){
					return false;
				}
			}
		}
		return true;
	}
	public  static TextureAtlasRegionNames isInList(List<TextureAtlasRegionNames> regionNames, String atlasName) {
		int size = regionNames.size();
		for (int count = 0; count < size; count++) {
			if (regionNames.get(count).getAtlasName().equals(atlasName)) {
				return regionNames.get(count);
			}
		}
			return null;
	}
	public static  boolean isInList(List<TextureAtlasRegionNames> regionNames, TextureAtlasRegionNames atlasName) {
		int size = regionNames.size();
		for (int count = 0; count < size; count++) {
			if (regionNames.get(count).getAtlasName().equals(atlasName)) {
				return true;
			}
		}
		return false;
	}
}
	
	
	
	
