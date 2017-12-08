package com.jansen.sander.wallofshame.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sander on 5/12/2017.
 */


@Entity
public class Shame {

    @PrimaryKey(autoGenerate = true)
    private int sid;

    @ColumnInfo (name = "shame_message")
    private String shameMessage;

    @ColumnInfo (name = "shame_duts")
    private String shameDuts;

    @ColumnInfo(name = "shame_rating")
    private int starRating;

    public int getSid() {
        return sid;
    }

    public String getShameMessage() {
        return shameMessage;
    }

    public String getShameDuts() {
        return shameDuts;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Shame(String shameMessage, String shameDuts, int starRating){
        this.shameMessage = shameMessage;
        this.shameDuts = shameDuts;
        this.starRating = starRating;
    }
}
