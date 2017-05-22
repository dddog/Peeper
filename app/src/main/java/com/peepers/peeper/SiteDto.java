package com.peepers.peeper;

import android.support.annotation.DrawableRes;

public class SiteDto {

    private @DrawableRes
    int logoID;
    private int totalCount;

    public SiteDto(int logoID, int totalCount) {
        this.logoID = logoID;
        this.totalCount = totalCount;
    }

    public int getLogoID() {
        return logoID;
    }

    public void setLogoID(int logoID) {
        this.logoID = logoID;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
