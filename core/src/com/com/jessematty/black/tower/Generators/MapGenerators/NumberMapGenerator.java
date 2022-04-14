package com.jessematty.black.tower.Generators.MapGenerators;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;

//  class for  making a 2d array of  ints that resemble geographical height landSquareTileMap
public class NumberMapGenerator {
	RandomNumbers value=new RandomNumbers();
	int xSize;
	int ySize;
	Array<Integer> mapNumbers = new Array<Integer>(); // the  unique numbers in the height map array.
	public NumberMapGenerator(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}
	public void setMapSize(int xSize, int ySize){
		this.xSize=xSize;
		this.ySize=ySize;
	}
	
	public int[][] makeNumberMap(int maxValue, int minValue, int smoothness){ // makes 2d array of ints that resembles WoodWand rain landSquareTileMap
		int[][] heightMap= new int[xSize][ySize]; // the numerical height map
		int x;
		int y;
		int a;
		int b;
		// make random number map
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				heightMap[countx][county]=value.getRandomNumber(minValue, maxValue);
			}
		}
		//average numbers to create  the height map
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
					heightMap[countx][county]=((heightMap[a][county]+heightMap[x][county]+heightMap[a][b]+heightMap[a][y]+heightMap[countx][b]+heightMap[countx][y]+heightMap[x][y]+heightMap[x][b])/8);
				}
			}
		}

		createMapNumbers(heightMap);

		return heightMap;
	}

	// find the unique numbers in the soil map array
	private void createMapNumbers(int [] [] soilMap){

		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				boolean inMap= numberCheck(soilMap[countx][county]);
				if(inMap==false){
					mapNumbers.add(soilMap[countx][county]);
				}
			}
		}

	}
	private boolean numberCheck(int number){
		int size= mapNumbers.size;
		for (int count=0; count<size; count++){
			if(mapNumbers.get(count)==number){
				return true;
			}
		}
		return false;
	}
	public Array<Integer> getMapNumbers() {
		return mapNumbers;
	}
}
	
	
	
