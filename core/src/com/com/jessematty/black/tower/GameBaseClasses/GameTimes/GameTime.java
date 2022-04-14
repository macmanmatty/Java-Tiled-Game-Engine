package com.jessematty.black.tower.GameBaseClasses.GameTimes;

/**
 * class that converts game time in to actual time
 */
public class GameTime {
    /**
     * time counting variables
     */
    private double   gameSeconds;
    private float microSecondCounter;
    private float secondCounter;
    private float minuteCounter;
    private float hourCounter;
    private float dayCounter;
    private float daySeconds;
    private float yearCounter;
    /**
     * variables to set the rate of time increase
     */
    private float microSecondsPerSecond =60;
    private float secondsPerMinute =60;
    private float minutesPerHour=60;
    private float hoursPerDay=24;
    private float timeSwitch=12;
    private float daysPerYear=365;
    private float summerStart=13140;
    private float fallStart=13140*2;
    private float winterStart=13140*3;
    private float springStart=13140*4;


    private Season season = Season.SPRING;
    private Month month=Month.MARCH;
    boolean amTime;
    /**
     method that counts the game time
     **/
    public void countTime() {
        if(microSecondCounter==microSecondsPerSecond) {
            gameSeconds++;
            daySeconds++;
            secondCounter++;
            microSecondCounter=0;
        }
        if (gameSeconds % secondsPerMinute == 0) {
            minuteCounter++;
            secondCounter=0;
        }
        if (minuteCounter == minutesPerHour) {
            hourCounter++;
            minuteCounter = 0;
        }
        if (hourCounter == timeSwitch) {
            amTime = false;
        }
        if (hourCounter == hoursPerDay) {
            dayCounter++;
            hourCounter = 0;
            daySeconds=0;
            amTime = true;
        }
        if (dayCounter == daysPerYear) {
            yearCounter++;
            dayCounter = 0;
        }
        seasonSet();
        setMonth();
        microSecondCounter++;
    }
    // sets the season
    private void seasonSet() { // sets the squares season based on gameTime
        if (gameSeconds % summerStart == 0) {
            season = Season.SUMMER;
        } else if (gameSeconds % winterStart  == 0) {
            season = Season.FALL;
        } else if (gameSeconds % fallStart  == 0) {
            season = Season.WINTER;
        } else if (gameSeconds % springStart == 0) {
            season = Season.SPRING;
        }
    }
    public void setMonth(){
    }
    public double getTotalGameTimeLapsedInSeconds() {
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
    public float getCurrentSeconds() {
        return secondCounter;
    }
    public Month getMonth() {
        return month;
    }

    public float getMicroSecondsPerSecond() {
        return microSecondsPerSecond;
    }

    public void setMicroSecondsPerSecond(float microSecondsPerSecond) {
        this.microSecondsPerSecond = microSecondsPerSecond;
    }

    public float getMicroSecondCounter() {
        return microSecondCounter;
    }

    public void setMicroSecondCounter(float microSecondCounter) {
        this.microSecondCounter = microSecondCounter;
    }

    public float getSecondsPerMinute() {
        return secondsPerMinute;
    }

    public void setSecondsPerMinute(float secondsPerMinute) {
        this.secondsPerMinute = secondsPerMinute;
    }

    public float getMinutesPerHour() {
        return minutesPerHour;
    }

    public void setMinutesPerHour(float minutesPerHour) {
        this.minutesPerHour = minutesPerHour;
    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(float hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public float getDaysPerYear() {
        return daysPerYear;
    }

    public void setDaysPerYear(float daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    public float getTimeSwitch() {
        return timeSwitch;
    }

    public void setTimeSwitch(float timeSwitch) {
        this.timeSwitch = timeSwitch;
    }
    
    
}
