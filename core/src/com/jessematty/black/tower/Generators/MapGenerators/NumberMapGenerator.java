package com.jessematty.black.tower.Generators.MapGenerators;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import java.util.ArrayList;
public class NumberMapGenerator {
	RandomNumbers value=new RandomNumbers();
	int xSize;
	int ySize;
	Array<Integer> numbers = new Array<Integer>();
	public NumberMapGenerator(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}
	public void setMapSize(int xSize, int ySize){ // makes a 2d array of  ints that resemble geographical height landSquareTileMap
		this.xSize=xSize;
		this.ySize=ySize;
	}
	
	public int[][] makeNumberMap(int maxValue, int minValue, int smoothness){ // makes 2d array of ints that resembles WoodWand rain landSquareTileMap
		int[][] soilMap= new int[xSize][ySize];
		int x;
		int y;
		int a;
		int b;
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				soilMap[countx][county]=value.getRandomNumber(minValue, maxValue);
			}
		}
		for (int countx=0; countx<xSize;  countx++) {
			for (int county = 0; county < ySize; county++) {
				}
			}
		for(int smooth=0; smooth<smoothness; smooth++){
			for (int countx=0; countx<xSize;  countx++){
				for (int county=0; county<ySize; county++){
					x=countx-1;
					y=county-1;
					a=countx+1;
					b=county+1;
					if (x<0){
						x=0;
					}
					if (y<0){
						y=0;
					}
					if (a>=xSize){
						a=xSize-1;
					}
					if (b>=ySize){
						b=ySize-1;
					}
					soilMap[countx][county]=((soilMap[a][county]+soilMap[x][county]+soilMap[a][b]+soilMap[a][y]+soilMap[countx][b]+soilMap[countx][y]+soilMap[x][y]+soilMap[x][b])/8);
				}
			}
		}
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				boolean inMap= numberCheck(soilMap[countx][county]);
				if(inMap==false){
					numbers.add(soilMap[countx][county]);
				}
			}
			}
		return soilMap;
	}
	private boolean numberCheck(int number){
		int size= numbers.size;
		for (int count=0; count<size; count++){
			if(numbers.get(count)==number){
				return true;
			}
		}
		return false;
	}
	public Array<Integer> getNumbers() {
		return numbers;
	}
}
	
	
	
