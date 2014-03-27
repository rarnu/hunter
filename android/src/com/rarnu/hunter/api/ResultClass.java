package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class ResultClass implements Serializable {
    public int result;

    public static ResultClass fromJson(JSONObject json) {
        ResultClass rc = null;
        try {
            rc = new ResultClass();
            rc.result = json.getInt("result");
        } catch (Exception e) {

        }
        return rc;
    }
}
