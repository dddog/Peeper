package com.peepers.peeper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dddog on 2017/05/19.
 */

public class DaumDto implements Parcelable {
    private String title;
    private String description;
    private String link;
    private String pubDate;

    public DaumDto(String title, String description, String link, String pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
    }

    protected DaumDto(Parcel in) {
        title = in.readString();
        description = in.readString();
        link = in.readString();
        pubDate = in.readString();
    }

    public static final Creator<DaumDto> CREATOR = new Creator<DaumDto>() {
        @Override
        public DaumDto createFromParcel(Parcel in) {
            return new DaumDto(in);
        }

        @Override
        public DaumDto[] newArray(int size) {
            return new DaumDto[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.link);
        dest.writeString(this.pubDate);
    }
}
