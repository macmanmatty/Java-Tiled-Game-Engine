package com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings;

import com.jessematty.black.tower.Components.Stats.Stat;

public class KeyStat extends Stat {
    @Override
    public Stat makeCopy() {
        return new KeyStat();
    }
}
