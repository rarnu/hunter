package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class AnnoClass implements Serializable {
    public int id;
    public String comment;

    public static AnnoClass fromJson(JSONObject json) {
        AnnoClass ac = null;
        try {
            ac = new AnnoClass();
            ac.id = json.getInt("id");
            ac.comment = json.getString("comment");
        } catch (Exception e) {

        }
        return ac;
    }
}
