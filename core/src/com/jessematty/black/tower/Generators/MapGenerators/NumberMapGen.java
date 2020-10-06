package com.jessematty.black.tower.Generators.MapGenerators;

import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;

import java.util.ArrayList;

public class NumberMapGen {
	RandomNumbers value=new RandomNumbers();
	int xSize;
	int ySize;
	float[][] rivers;

	int riverNumber;
	
	
	public NumberMapGen(){}
	
	
	public void setMapSize(int xSize, int ySize){ // makes WoodWand 2d array of  ints that resemble geographical height landSquareTileMap
		this.xSize=xSize;
		this.ySize=ySize;
	}
	
	
	public float[][] makeHeightMap(int minHeight, int maxHeight, int smoothness, int mountianNumber){
		this.riverNumber=riverNumber;
		rivers= new float[2][riverNumber];
		float[][] mountians= new float[2][mountianNumber];
				
		float[][] heightMap= new float[xSize][ySize];
		int x;
		int y;
		int a;
		int b;
		
		

		
		
		for (int count=0; count<mountianNumber; count++){
			float random=value.getRandomNumber(0, 100);
			
			
			
			
			if (random%2==0){
			mountians[0][count]=0;
			}

			else {
			mountians[0][count]=1;
			}
			if(mountians[0][count]==0){
			mountians[1][count]=value.getRandomNumber(10, xSize);
			}
			else{
				mountians[1][count]=value.getRandomNumber(10, ySize);
			}
			
		}	
			
			

		
		
		
		
		
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				heightMap[countx][county]=value.getRandomNumber(minHeight, maxHeight);

				for (int count=0; count<mountianNumber; count++){ // makes the mountians on the height landSquareTileMap
					
					if (mountians[0][count]==0){
					if(county==mountians[1][count]){
						heightMap[countx][county]=value.getRandomNumber((int)(maxHeight/2), (maxHeight*2));
					}
					}
					else{
						if(countx==mountians[1][count]){
							heightMap[countx][county]=value.getRandomNumber((int)(maxHeight/2),( maxHeight*2));
						}
					}
					}
				
				
				
				
					
			
			}	
		}	
				float random=value.getRandomNumber(0,  xSize);
				float random2=value.getRandomNumber(0,  ySize);
				
				
		
		for(int smooth=0; smooth<smoothness; smooth++){ // smooths out the landSquareTileMap for more realism
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				x=countx-1;
				y=county-1;
				a=countx+1;
				b=county+1;
				int time=0;
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
				int t=1;
				
				
			
					
				
				
				
				
			}
		}
		}
		
		
			
			
			
			
		
		
		
		
		
				
		
		
		
	return heightMap;
	
	}



	
	
		
	


	public float[][] makeRainMap(int maxRain, int minRain, int riverNumber, int smoothness){ // makes 2d array of ints that resembles WoodWand rain landSquareTileMap
		float[][] rainMap= new float[xSize][ySize];
		int x;
		int y;
		int a;
		int b;


		for (int count=0; count<riverNumber; count++){
			float random=value.getRandomNumber(0, 100);




			if (random%2==0){
				rivers[0][count]=0;
			}

			else {
				rivers[0][count]=1;
			}


			if(rivers[0][count]==0){
				rivers[1][count]=value.getRandomNumber(10, xSize-10);
			}
			else{
				rivers[1][count]=value.getRandomNumber(10, ySize-10);
			}

		}
			
			

		
		
		
		
		
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				rainMap[countx][county]=value.getRandomNumber(minRain, maxRain);
				for(int count=0; count<riverNumber; count++){
				if (rivers[0][count]==0){
					if(countx==rivers[1][count]){
						rainMap[countx][county]=maxRain;
					}
					else{
						if(county==rivers[1][count]){
							rainMap[countx][county]=maxRain;
						}
					}
					}
			
					
			
			}	
		}	
		}

		for (int countx=0; countx<xSize;  countx++) {
			for (int county = 0; county < ySize; county++) {
				for (int count = 0; count < riverNumber; count++) { //makes the rivers on the height landSquareTileMap

					if (rivers[0][count]>1) {
						if (county == rivers[1][count]) {
							rainMap[countx][county] = -1 * value.getRandomNumber((int) (maxRain / 2), maxRain *2 );
						}
					}
					else {
						if (countx == rivers[1][count]) {
							rainMap[countx][county] = -value.getRandomNumber((int)(maxRain / 2), maxRain*2 );
							;
						}
					}
				}
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
				
				rainMap[countx][county]=((rainMap[a][county]+rainMap[x][county]+rainMap[a][b]+rainMap[a][y]+rainMap[countx][b]+rainMap[countx][y]+rainMap[x][y]+rainMap[x][b])/8);
		
			}
		}
		}
		
		
		
		
		
	return rainMap;
	
	}

	
	
	public float[][] makeTempMap(int maxTemp, int minTemp, int smoothness, int maxTempChange,  boolean coldUp){ //  // makes 2d array of ints that resembles WoodWand temperature  landSquareTileMap
		float[][] tempMap= new float[xSize][ySize];
		int x;
		int y;
		int a;
		int b;
		float squareTemp;
		

		
		
		
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				int tempChange=value.getRandomNumber(0,maxTempChange);

				y=county-1;
				if(y<0){
					y=0;

				}


				if (countx==0 && coldUp==true){
					tempMap[countx][county]=minTemp;
					}

					else{
					tempMap[countx][county]=maxTemp;


				}
				
				if (countx==xSize && coldUp==true){
					tempMap[countx][county]=maxTemp;
					}

				else{
					tempMap[countx][county]=minTemp;


				}

				if(coldUp==true) {
					squareTemp = tempMap[countx][y] + tempChange;
				}

				else {
					squareTemp = tempMap[countx][y] - tempChange;

				}



				if (squareTemp<minTemp){
					squareTemp=minTemp;
				}
				if (squareTemp>maxTemp){
					squareTemp=maxTemp;
			}
							
				
				tempMap[countx][county]=squareTemp;
				
				
				float random=value.getRandomNumber(1,10);
				if (random%5==0){
					tempChange--;
				}
				if (tempChange<0){
					tempChange=0;
					
				}
				
			
					
			
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
				
				tempMap[countx][county]=((tempMap[a][county]+tempMap[x][county]+tempMap[a][b]+tempMap[a][y]+tempMap[countx][b]+tempMap[countx][y]+tempMap[x][y]+tempMap[x][b])/8);
		
			}
		}
		}






		return tempMap;
	
	
	
	

}


	public float[][] makeSoilFertilityMap(int maxFert, int minFert, int smoothness){ // makes 2d array of ints that resembles WoodWand rain landSquareTileMap
		float[][] rainMap= new float[xSize][ySize];
		int x;
		int y;
		int a;
		int b;









		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				rainMap[countx][county]=value.getRandomNumber(minFert, maxFert);

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

					rainMap[countx][county]=((rainMap[a][county]+rainMap[x][county]+rainMap[a][b]+rainMap[a][y]+rainMap[countx][b]+rainMap[countx][y]+rainMap[x][y]+rainMap[x][b])/8);

				}
			}
		}





		return rainMap;

	}







	public float[][] makeMap(int maxNumber, int minNumber, int smoothness){ // makes 2d array of ints that resembles WoodWand rain landSquareTileMap
		float[][] map= new float[xSize][ySize];
		int x;
		int y;
		int a;
		int b;









		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				map[countx][county]=value.getRandomNumber(minNumber, maxNumber);

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

					map[countx][county]=((map[a][county]+map[x][county]+map[a][b]+map[a][y]+map[countx][b]+map[countx][y]+map[x][y]+map[x][b])/8);

				}
			}
		}





		return map;

	}




 public float [][] makeTwoTerrianMap(int maxNumber, int minNumber, int smoothness){ // makes 2d array of ints that resembles a rain map
		float[][]map= new float[xSize][ySize];
		int x;
		int y;
		int a;
		int b;






		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				map[countx][county]=value.getRandomNumber(minNumber, maxNumber)*(3/2);

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

					map[countx][county]=((map[a][county]+map[x][county]+map[a][b]+map[a][y]+map[countx][b]+map[countx][y]+map[x][y]+map[x][b])/8);

				}
			}
		}








		return map;

	}


	public int numberCheck(ArrayList<Integer> numbers, int number) {
		int size=numbers.size();


		for(int count=0; count<size; count++) {
			if(numbers.get(count)==number) {

				return count;

			}



		}


		return -1;



	}


}


	
	
	


