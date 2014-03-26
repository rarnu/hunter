package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class DataClass implements Serializable {
    public int id;
    public String mailWork;
    public String mailPrivate;
    public String qq;
    public String wx;
    public String hangouts;
    public String phoneWork;
    public String phonePrivate;
    public String address;

    public static DataClass fromJson(JSONObject json) {
        DataClass dc = null;
        try {
            dc = new DataClass();
            dc.id = json.getInt("id");
            dc.mailWork = json.getString("mail_work");
            dc.mailPrivate = json.getString("mail_private");
            dc.qq = json.getString("qq");
            dc.wx = json.getString("wx");
            dc.hangouts = json.getString("hangouts");
            dc.phoneWork = json.getString("phone_work");
            dc.phonePrivate = json.getString("phone_private");
            dc.address = json.getString("address");
        } catch (Exception e) {

        }
        return dc;
    }
}
