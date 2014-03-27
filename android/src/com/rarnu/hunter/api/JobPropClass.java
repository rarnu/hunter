package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class JobPropClass implements Serializable {
    public int id;
    public String keywords;
    public String key2;
    public String key3;
    public String key4;
    public String key5;
    public int emergency;
    public int color;
    public int status;

    public static JobPropClass fromJson(JSONObject json) {
        JobPropClass jpc = null;
        try {
            jpc = new JobPropClass();
            jpc.id = json.getInt("id");
            jpc.keywords = json.getString("keywords");
            jpc.key2 = json.getString("key2");
            jpc.key3 = json.getString("key3");
            jpc.key4 = json.getString("key4");
            jpc.key5 = json.getString("key5");
            jpc.emergency = json.getInt("emergency");
            jpc.color = json.getInt("color");
            jpc.status = json.getInt("status");
        } catch (Exception e) {

        }
        return jpc;
    }
}
