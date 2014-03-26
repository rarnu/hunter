package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class TimelineClass implements Serializable {
    public int id;
    public String publishDate;
    public String comment;

    public static TimelineClass fromJson(JSONObject json) {
        TimelineClass tc = null;
        try {
            tc = new TimelineClass();
            tc.id = json.getInt("id");
            tc.publishDate = json.getString("publish_date");
            tc.comment = json.getString("comment");
        } catch (Exception e) {

        }
        return tc;
    }
}
