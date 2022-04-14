package com.jessematty.black.tower.GameBaseClasses.Dice;

public abstract  class Dice {
    private  final String name;
    private double  maxNumber;
    private double minNumber;
    private double rollNumber;


    protected abstract  double rollDice();

    public Dice(String name, double maxNumber, double minNumber) {
        this.name = name;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    public  void roll(){
        this.rollNumber=rollDice();


    }

    public double getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(double maxNumber) {
        this.maxNumber = maxNumber;
    }

    public double getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(double minNumber) {
        this.minNumber = minNumber;
    }

    public double getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }
}
