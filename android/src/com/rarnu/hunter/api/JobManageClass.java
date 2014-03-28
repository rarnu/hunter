package com.rarnu.hunter.api;

import org.json.JSONObject;

import java.io.Serializable;

public class JobManageClass implements Serializable {
    public int id;
    public String jobTitle;
    public String workArea;
    public String companyName;
    public int color;
    public int status;

    public static JobManageClass fromJson(JSONObject json) {
        try {
            JobManageClass jc = new JobManageClass();
            jc.id = json.getInt("id");
            jc.jobTitle = json.getString("job_title");
            jc.workArea = json.getString("work_area");
            jc.companyName = json.getString("company_name");
            jc.color = json.getInt("color");
            jc.status = json.getInt("status");
            return jc;
        } catch (Exception e) {
            return null;
        }
    }
}
