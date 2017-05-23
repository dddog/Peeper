package com.peepers.peeper;

import android.support.annotation.DrawableRes;

public class SiteDto {

    private @DrawableRes
    int logoID;
    private String siteName;
    private String totalCount;

    public SiteDto(int logoID, String siteName, String totalCount) {
        this.logoID = logoID;
        this.siteName = siteName;
        this.totalCount = totalCount;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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
