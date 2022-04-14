package com.jessematty.black.tower.Generators;

import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;

public class BuildingHeightMapMaker {
	RandomNumbers value=new RandomNumbers();
	 public BuildingHeightMapMaker(){}
	 public float[][] makeHeightMap(int maxHeight, int smoothness, int xSize, int ySize){
			float[][] heightMap= new float[xSize][ySize];
			int x;
			int y;
			int a;
			int b;
			for (int countx=0; countx<xSize;  countx++){
				for (int county=0; county<ySize; county++){
					heightMap[countx][county]=value.getRandomNumber(1, maxHeight);
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
					heightMap[countx][county]=((heightMap[a][county]+heightMap[x][county]+heightMap[a][b]+heightMap[a][y]+heightMap[countx][b]+heightMap[countx][y]+heightMap[x][y]+heightMap[x][b])/8);
							}
							}
					}
		return heightMap;
		}
		 
		 
		 
		 
		 
		 
		 
		 
		 
}
