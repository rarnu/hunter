package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class LoginClass implements Serializable {
    public int result;
    public int id;
    public String account;

    public static LoginClass fromJson(JSONObject json) {
        LoginClass lc = null;
        try {
            lc = new LoginClass();
            lc.result = json.getInt("result");
            if (lc.result == 0) {
                lc.id = json.getInt("id");
                lc.account = json.getString("account");
            }
        } catch (Exception e) {

        }
        return lc;
    }
}
