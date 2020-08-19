package com.jessematty.black.tower.GameBaseClasses.Generators.Sets;

import java.util.HashMap;

public class TileSet {

    protected String terrianKind;
    protected int terrianNumber;
   protected  int cliffNumber;
    protected int riverBankNumber;
    protected boolean colored;

    Enum kind;

  protected HashMap<Integer, Integer> setNumbers = new HashMap<Integer, Integer>(); // number of images for each mask number


    protected HashMap<Integer, Integer> setTextureSizes = new HashMap<Integer, Integer>(); // texture size for each number
    

    public TileSet() {





    }

    
   public void setTileNumbers(){

        setNumbers.put(0, 1);

        setNumbers.put(11,1);

        setNumbers.put(22,1);

        setNumbers.put(31,1);
        setNumbers.put(104,1);
        setNumbers.put(107,1);
        setNumbers.put(126,1);
        setNumbers.put(127,1);
        setNumbers.put(208,1);
        setNumbers.put(214,1);
        setNumbers.put(219,1);

        setNumbers.put(223,1);
        setNumbers.put(248,1);
        setNumbers.put(251,1);
        setNumbers.put(254,1);
        setNumbers.put(255,1);

       setTextureSizes.put(0, 1);

       setTextureSizes.put(11,1);

       setTextureSizes.put(22,1);

       setTextureSizes.put(31,1);
       setTextureSizes.put(104,1);
       setTextureSizes.put(107,1);
       setTextureSizes.put(126,1);
       setTextureSizes.put(127,1);
       setTextureSizes.put(208,1);
       setTextureSizes.put(214,1);
       setTextureSizes.put(219,1);

       setTextureSizes.put(223,1);
       setTextureSizes.put(248,1);
       setTextureSizes.put(251,1);
       setTextureSizes.put(254,1);
       setTextureSizes.put(255,1);


   }
    public int getTerrianNumber() {
        return terrianNumber;
    }

    public void setTerrianNumber(int terrianNumber) {
        this.terrianNumber = terrianNumber;
    }

    public int getCliffNumber() {
        return cliffNumber;
    }

    public void setCliffNumber(int cliffNumber) {
        this.cliffNumber = cliffNumber;
    }

    public int getRiverBankNumber() {
        return riverBankNumber;
    }

    public void setRiverBankNumber(int riverBankNumber) {
        this.riverBankNumber = riverBankNumber;
    }

   
    public int getNumberOfSets (int bitNumber){



        return setNumbers.get(bitNumber);

        
        
        
        
    }

    public void setSetNumbers(int bitNumber, int number){
        setNumbers.put(bitNumber, number);





    }

    public int getTextureSize(int bitNumber){



        return setTextureSizes.get(bitNumber);





    }

    public void setTextureSize(int bitNumber, int number){
        setTextureSizes.put(bitNumber, number);





    }

    public String getTerrianKind() {
        return terrianKind;
    }

    public void setTerrianKind(String terrianKind) {
        this.terrianKind = terrianKind;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }


}
