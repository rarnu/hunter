package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class UpdateClass implements Serializable {
    public int result;
    public int versionCode;
    public String versionName;
    public String versionDesc;
    public String versionFile;

    public static UpdateClass fromJson(JSONObject json) {
        UpdateClass uc = null;
        try {
            uc = new UpdateClass();
            uc.result = json.getInt("result");
            if (uc.result == 0) {
                uc.versionCode = json.getInt("code");
                uc.versionName = json.getString("name");
                uc.versionDesc = json.getString("desc");
                uc.versionFile = json.getString("file");
            }
        } catch (Exception e) {

        }
        return uc;
    }
}
