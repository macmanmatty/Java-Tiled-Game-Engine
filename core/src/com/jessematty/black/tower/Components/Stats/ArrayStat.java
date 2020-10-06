package com.jessematty.black.tower.Components.Stats;

import com.badlogic.gdx.utils.Array;

public class ArrayStat<T>extends Stat {

    Array<T> stat= new Array<>();



    public ArrayStat() {
    }

    public ArrayStat(boolean displayable, String name) {
        super(displayable, name);
    }

    public ArrayStat(String name) {
        super(name);
    }

    public ArrayStat(Stat other) {
        super(other);
    }

    public Array<T> getStat() {
        return stat;
    }

    @Override
    public Stat makeCopy() {
        return new ArrayStat(this);
    }
}
