package com.jessematty.black.tower.GameBaseClasses.GameTimes;


// class for the game time
public class GameTime {

    private double   gameSeconds;
    private float microSecondCounter;
    private float secondCounter;
    private float minuteCounter;
    private float hourCounter;
    private float dayCounter;
    private float daySeconds;
    private float yearCounter;
    private Season season = Season.SPRING;
    private Month month=Month.MARCH;




    boolean amTime;



    // method that counts the game time
    // 60 micro seconds = 1 second
    // 60 seconds= 1 minute etc.
    public void countTime() {
        if(microSecondCounter==60) {
            gameSeconds++;
            daySeconds++;
            secondCounter++;
            microSecondCounter=0;
        }


        if (gameSeconds % 60 == 0) {
            minuteCounter++;
            secondCounter=0;
        }
        if (minuteCounter == 60) {
            hourCounter++;
            minuteCounter = 0;
        }
        if (hourCounter == 12) {
            amTime = false;
        }
        if (hourCounter == 24) {
            dayCounter++;
            hourCounter = 0;
            daySeconds=0;
            amTime = true;
        }
        if (dayCounter == 365) {
            yearCounter++;
            dayCounter = 0;
        }

        seasonSet();
        setMonth();
        microSecondCounter++;

    }


    // sets the season
    private void seasonSet() { // sets the squares season based on gameTime
        if (gameSeconds % 13140 == 0) {
            season = Season.SUMMER;
        } else if (gameSeconds % (13140 * 2) == 0) {
            season = Season.FALL;
        } else if (gameSeconds % (13140 * 3) == 0) {
            season = Season.WINTER;
        } else if (gameSeconds % (3140 * 4) == 0) {
            season = Season.SPRING;
        }

    }

    public void setMonth(){


    }



    public double getTotalGameTimeLaspedInSeconds() {
        return gameSeconds;
    }


    public float getDaySeconds() {
        return daySeconds;
    }

    public float getCurrentNumberOfDaysLapsedInCurrentYear() {
        return dayCounter;
    }

    public float getCurrentNumbersOfHoursLapsedInCurrentDay(){

        return hourCounter;
    }

    public double getGameSeconds() {
        return gameSeconds;
    }

    public Season getSeason() {
        return season;
    }

    public Month getMonth() {
        return month;
    }
}
