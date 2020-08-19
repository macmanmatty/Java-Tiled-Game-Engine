package com.jessematty.black.tower.GameBaseClasses.Utilities;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.Node;
import java.util.List;
public class InList {
	public InList() {
	}

    public  static boolean stringNonCaseSenstiveIsInList(String name, List<String> names) {
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

	public static boolean isInList(TextureAtlas atlas, TextureRegion region) {

		Array<AtlasRegion> regions=atlas.getRegions();
		int number = regions.size;
		for (int count = 0; count < number; count++) {
			if (regions.get(count).equals(region)) {
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
			if (maps.get(count).getMapName().equals(Name)) {
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
	public static  boolean isInList(Array<? extends Object> names, Array<? extends Object> otherNames) {
		int number = otherNames.size;
		int number2 = names.size;
		for (int count = 0; count < number; count++) {
			for (int count2 = 0; count2 < number2; count2++) {
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
	
	
	public static  boolean isInList(Object  name, Array<? extends Object> names) {
		Object manClass;
		int number = names.size;
		for (int count = 0; count < number; count++) {
			manClass = names.get(count);
			if (name.equals(manClass)) {
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
	
	
	
	
