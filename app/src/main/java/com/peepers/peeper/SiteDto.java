package com.peepers.peeper;

import android.support.annotation.DrawableRes;

public class SiteDto {

    private @DrawableRes
    int logoID;
    private String totalCount;

    public SiteDto(int logoID, String totalCount) {
        this.logoID = logoID;
        this.totalCount = totalCount;
    }

    public int getLogoID() {
        return logoID;
    }

    public void setLogoID(int logoID) {
        this.logoID = logoID;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
