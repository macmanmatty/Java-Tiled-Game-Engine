package com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators;

import java.util.ArrayList;

public class LandMapSpecs extends MapSpecs {
    int numberOfDirtTileSets;
    int numberOfSandTileSets;
    int numberOfStoneTileSets;
    int numberOfGrassTileSets;
    int numberOfRoadTileSets;
    int numberOfCliffTileSets;



    int maxSoilDepth;
    int minSoilDepth;
    int maxTemp;
    int minTemp;
    boolean coldUp=false;
    int maxWaterTable;
    int minWaterTable;
    int maxHeight;
    int minHeight;
    int maxCliffHeight;
    int minCliffHeight;
    int minRivers;
    int maxRivers;

    int maxPeat;
    int minPeat;
    int maxLoam;
    int minLoam;
    int maxSand;
    int minSand;
    int maxClay;
    int minClay;
    int minChalk;
    int maxChalk;
    int minSlit;
    int maxSilt;
    int maxN;
    int minN;
    int maxP;
    int minP;
    int maxK;
    int minK;
    int maxFe;
    int minFe;
    int maxCa;
    int minCa;
    int maxS;
    int minS;
    int maxMg;
    int minMg;
    int maxPb;
    int minPb;
    int minAr;
    int maxAr;
    int maxAg;
    int minAg;
    int maxAu;
    int minAu;
    int maxCu;
    int minCu;
   int  soilSmoothness;
   int nutrientSmoothness;
   int tempChange;
   ArrayList<String> preMadeTmxMapSectionPaths = new ArrayList<String>();






    public  void setSpecs(){

        super.setSpecs();
        xSize=1;
        ySize=1;
        maxNumberOfFighters=1;
        minNumberOfFighters=1;
        fightersRegenerate=true;
        maxFireMagic=1;
        minFireMagic=1;
        maxWindMagic=1;
        minWindMagic=1;
        maxEarthMagic=1;
        minEarthMagic=1;
        maxWaterMagic=1;
        minWaterMagic=1;
        minLightMagic=1;
        maxLightMagic=1;
        minDarkMagic=1;
        maxDarkMagic=1;
        magicSmoothness=1;
         numberOfDirtTileSets=1;
         numberOfSandTileSets=1;
         numberOfStoneTileSets=1;
         numberOfGrassTileSets=1;
         numberOfRoadTileSets=1;
         numberOfCliffTileSets=1;



         maxSoilDepth=1;
         minSoilDepth=1;
         maxTemp=1;
         minTemp=1;
        coldUp=true;
         maxWaterTable=1;
         minWaterTable=1;
         maxHeight=1;
         minHeight=1;
         maxCliffHeight=1;
         minCliffHeight=1;
         minRivers=1;
         maxRivers=1;

         maxPeat=1;
         minPeat=1;
         maxLoam=1;
         minLoam=1;
         maxSand=1;
         minSand=1;
         maxClay=1;
         minClay=1;
         minChalk=1;
         maxChalk=1;
         minSlit=1;
         maxSilt=1;
         maxN=1;
         minN=1;
         maxP=1;
         minP=1;
         maxK=1;
         minK=1;
         maxFe=1;
         minFe=1;
         maxCa=1;
         minCa=1;
         maxS=1;
         minS=1;
         maxMg=1;
         minMg=1;
         maxPb=1;
         minPb=1;
         minAr=1;
         maxAr=1;
         maxAg=1;
         minAg=1;
         maxAu=1;
         minAu=1;
         maxCu=1;
         minCu=1;
          soilSmoothness=1;
         nutrientSmoothness=1;
         tempChange=1;











    }



    public int getNumberOfDirtTileSets() {
        return numberOfDirtTileSets;
    }

    public void setNumberOfDirtTileSets(int numberOfDirtTileSets) {
        this.numberOfDirtTileSets = numberOfDirtTileSets;
    }

    public int getMaxSoilDepth() {
        return maxSoilDepth;
    }

    public void setMaxSoilDepth(int maxSoilDepth) {
        this.maxSoilDepth = maxSoilDepth;
    }

    public int getMinSoilDepth() {
        return minSoilDepth;
    }

    public void setMinSoilDepth(int minSoilDepth) {
        this.minSoilDepth = minSoilDepth;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public boolean isColdUp() {
        return coldUp;
    }

    public void setColdUp(boolean coldUp) {
        this.coldUp = coldUp;
    }

    public int getMaxWaterTable() {
        return maxWaterTable;
    }

    public void setMaxWaterTable(int maxWaterTable) {
        this.maxWaterTable = maxWaterTable;
    }

    public int getMinWaterTable() {
        return minWaterTable;
    }

    public void setMinWaterTable(int minWaterTable) {
        this.minWaterTable = minWaterTable;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxCliffHeight() {
        return maxCliffHeight;
    }

    public void setMaxCliffHeight(int maxCliffHeight) {
        this.maxCliffHeight = maxCliffHeight;
    }

    public int getMinCliffHeight() {
        return minCliffHeight;
    }

    public void setMinCliffHeight(int minCliffHeight) {
        this.minCliffHeight = minCliffHeight;
    }

    public int getMinRivers() {
        return minRivers;
    }

    public void setMinRivers(int minRivers) {
        this.minRivers = minRivers;
    }

    public int getMaxRivers() {
        return maxRivers;
    }

    public void setMaxRivers(int maxRivers) {
        this.maxRivers = maxRivers;
    }



    public int getMaxPeat() {
        return maxPeat;
    }

    public void setMaxPeat(int maxPeat) {
        this.maxPeat = maxPeat;
    }

    public int getMinPeat() {
        return minPeat;
    }

    public void setMinPeat(int minPeat) {
        this.minPeat = minPeat;
    }

    public int getMaxLoam() {
        return maxLoam;
    }

    public void setMaxLoam(int maxLoam) {
        this.maxLoam = maxLoam;
    }

    public int getMinLoam() {
        return minLoam;
    }

    public void setMinLoam(int minLoam) {
        this.minLoam = minLoam;
    }

    public int getMaxSand() {
        return maxSand;
    }

    public void setMaxSand(int maxSand) {
        this.maxSand = maxSand;
    }

    public int getMinSand() {
        return minSand;
    }

    public void setMinSand(int minSand) {
        this.minSand = minSand;
    }

    public int getMaxClay() {
        return maxClay;
    }

    public void setMaxClay(int maxClay) {
        this.maxClay = maxClay;
    }

    public int getMinClay() {
        return minClay;
    }

    public void setMinClay(int minClay) {
        this.minClay = minClay;
    }

    public int getMinChalk() {
        return minChalk;
    }

    public void setMinChalk(int minChalk) {
        this.minChalk = minChalk;
    }

    public int getMaxChalk() {
        return maxChalk;
    }

    public void setMaxChalk(int maxChalk) {
        this.maxChalk = maxChalk;
    }

    public int getMinSlit() {
        return minSlit;
    }

    public void setMinSlit(int minSlit) {
        this.minSlit = minSlit;
    }

    public int getMaxSilt() {
        return maxSilt;
    }

    public void setMaxSilt(int maxSilt) {
        this.maxSilt = maxSilt;
    }

    public int getMaxN() {
        return maxN;
    }

    public void setMaxN(int maxN) {
        this.maxN = maxN;
    }

    public int getMinN() {
        return minN;
    }

    public void setMinN(int minN) {
        this.minN = minN;
    }

    public int getMaxP() {
        return maxP;
    }

    public void setMaxP(int maxP) {
        this.maxP = maxP;
    }

    public int getMinP() {
        return minP;
    }

    public void setMinP(int minP) {
        this.minP = minP;
    }

    public int getMaxK() {
        return maxK;
    }

    public void setMaxK(int maxK) {
        this.maxK = maxK;
    }

    public int getMinK() {
        return minK;
    }

    public void setMinK(int minK) {
        this.minK = minK;
    }

    public int getMaxFe() {
        return maxFe;
    }

    public void setMaxFe(int maxFe) {
        this.maxFe = maxFe;
    }

    public int getMinFe() {
        return minFe;
    }

    public void setMinFe(int minFe) {
        this.minFe = minFe;
    }

    public int getMaxCa() {
        return maxCa;
    }

    public void setMaxCa(int maxCa) {
        this.maxCa = maxCa;
    }

    public int getMinCa() {
        return minCa;
    }

    public void setMinCa(int minCa) {
        this.minCa = minCa;
    }

    public int getMaxS() {
        return maxS;
    }

    public void setMaxS(int maxS) {
        this.maxS = maxS;
    }

    public int getMinS() {
        return minS;
    }

    public void setMinS(int minS) {
        this.minS = minS;
    }

    public int getMaxMg() {
        return maxMg;
    }

    public void setMaxMg(int maxMg) {
        this.maxMg = maxMg;
    }

    public int getMinMg() {
        return minMg;
    }

    public void setMinMg(int minMg) {
        this.minMg = minMg;
    }

    public int getMaxPb() {
        return maxPb;
    }

    public void setMaxPb(int maxPb) {
        this.maxPb = maxPb;
    }

    public int getMinPb() {
        return minPb;
    }

    public void setMinPb(int minPb) {
        this.minPb = minPb;
    }

    public int getMinAr() {
        return minAr;
    }

    public void setMinAr(int minAr) {
        this.minAr = minAr;
    }

    public int getMaxAr() {
        return maxAr;
    }

    public void setMaxAr(int maxAr) {
        this.maxAr = maxAr;
    }

    public int getMaxAg() {
        return maxAg;
    }

    public void setMaxAg(int maxAg) {
        this.maxAg = maxAg;
    }

    public int getMinAg() {
        return minAg;
    }

    public void setMinAg(int minAg) {
        this.minAg = minAg;
    }

    public int getMaxAu() {
        return maxAu;
    }

    public void setMaxAu(int maxAu) {
        this.maxAu = maxAu;
    }

    public int getMinAu() {
        return minAu;
    }

    public void setMinAu(int minAu) {
        this.minAu = minAu;
    }

    public int getMaxCu() {
        return maxCu;
    }

    public void setMaxCu(int maxCu) {
        this.maxCu = maxCu;
    }

    public int getMinCu() {
        return minCu;
    }

    public void setMinCu(int minCu) {
        this.minCu = minCu;
    }

    public int getSoilSmoothness() {
        return soilSmoothness;
    }

    public void setSoilSmoothness(int soilSmoothness) {
        this.soilSmoothness = soilSmoothness;
    }

    public int getNutrientSmoothness() {
        return nutrientSmoothness;
    }

    public void setNutrientSmoothness(int nutrientSmoothness) {
        this.nutrientSmoothness = nutrientSmoothness;
    }

    public int getMagicSmoothness() {
        return magicSmoothness;
    }

    public void setMagicSmoothness(int magicSmoothness) {
        this.magicSmoothness = magicSmoothness;
    }

    public int getTempChange() {
        return tempChange;
    }

    public void setTempChange(int tempChange) {
        this.tempChange = tempChange;
    }


    public int getNumberOfSandTileSets() {
        return numberOfSandTileSets;
    }

    public void setNumberOfSandTileSets(int numberOfSandTileSets) {
        this.numberOfSandTileSets = numberOfSandTileSets;
    }

    public int getNumberOfStoneTileSets() {
        return numberOfStoneTileSets;
    }

    public void setNumberOfStoneTileSets(int numberOfStoneTileSets) {
        this.numberOfStoneTileSets = numberOfStoneTileSets;
    }

    public int getNumberOfGrassTileSets() {
        return numberOfGrassTileSets;
    }

    public void setNumberOfGrassTileSets(int numberOfGrassTileSets) {
        this.numberOfGrassTileSets = numberOfGrassTileSets;
    }

    public int getNumberOfRoadTileSets() {
        return numberOfRoadTileSets;
    }

    public void setNumberOfRoadTileSets(int numberOfRoadTileSets) {
        this.numberOfRoadTileSets = numberOfRoadTileSets;
    }

    public int getNumberOfCliffTileSets() {
        return numberOfCliffTileSets;
    }

    public void setNumberOfCliffTileSets(int numberOfCliffTileSets) {
        this.numberOfCliffTileSets = numberOfCliffTileSets;
    }


    public ArrayList<String> getPreMadeTmxMapSectionPaths() {
        return preMadeTmxMapSectionPaths;
    }

    public void setPreMadeTmxMapSectionPaths(ArrayList<String> preMadeTmxMapSectionPaths) {
        this.preMadeTmxMapSectionPaths = preMadeTmxMapSectionPaths;
    }
}
