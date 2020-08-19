package com.jessematty.black.tower.Components.Stats;

public class StringStat extends Stat {

 private  String stat= "";



    public StringStat(StringStat other) {
        super(other);
        this.stat = other.stat;
    }

    public StringStat(boolean displayable, String name, String stat) {
        super(displayable, name);
        this.stat = stat;
    }

    public StringStat() {
    }

    public String getText() {


        return stat;
    }

    public StringStat(String name) {
        super(name);
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
    public void addTextToStat(String stat){
        this.stat=this.stat+stat;
    }

    public void removeTextFromStat(String stat){
       this.stat= this.stat.replace(stat, "");


    }

    public void setText(String text){

        this.stat=text;

    }

    public void insert(String text, int place){

      String string1=  this.stat.substring(place, stat.length()-1);
        String string2=  this.stat.substring(0, place+1);
        this.stat=string1+text+string2;



    }

    public boolean containsText(String text){

        return  this.stat.contains(text);

    }




    @Override
    public String toString() {
        return name+": "+stat;
    }


    @Override
    public Stat makeCopy() {
        return  new StringStat(this);
    }


}
