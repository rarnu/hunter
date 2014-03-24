package com.rarnu.hunter.api;

import org.json.JSONObject;

public class JobClass {
    public int id;
    public String jobTitle;
    public String workArea;
    public String companyName;
    public int color;

    public static JobClass fromJson(JSONObject json) {
        try {
            JobClass jc = new JobClass();
            jc.id = json.getInt("id");
            jc.jobTitle = json.getString("job_title");
            jc.workArea = json.getString("work_area");
            jc.companyName = json.getString("company_name");
            jc.color = json.getInt("color");
            return jc;
        } catch (Exception e) {
            return null;
        }
    }
}
