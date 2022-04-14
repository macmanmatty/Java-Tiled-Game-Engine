package com.jessematty.black.tower.Components.Stats;

import com.jessematty.black.tower.Components.CompareMode;

public   class FollowingNumericStat extends com.jessematty.black.tower.Components.Stats.NumericStat {
    private CompareMode compareMode;


    public FollowingNumericStat(NumericStat stat, CompareMode compareMode) {
        super(stat);
        this.compareMode=compareMode;
    }

    public FollowingNumericStat(FollowingNumericStat other) {
        super(other);
        this.compareMode = other.compareMode;
    }

    public CompareMode getCompareMode() {
        return compareMode;
    }

    public void setCompareMode(CompareMode compareMode) {
        this.compareMode = compareMode;
    }
}
