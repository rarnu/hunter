package com.rarnu.hunter.api;


import org.json.JSONObject;

import java.io.Serializable;

public class JobDetailClass implements Serializable {
    public int id;
    public String companyName;
    public String companyDesc;
    public int companyHeads;
    public String workArea;
    public int workYears;
    public int education;
    public int inHeads;
    public String publishDate;
    public String endDate;
    public String salaryRange;
    public String jobTitle;
    public String jobAccoutability;
    public String jobRequirement;
    public String keywords;
    public int emergency;
    public String tags;
    public int color;
    public int status;

    public static JobDetailClass fromJson(JSONObject json) {
        JobDetailClass jdc = null;
        try {
            jdc = new JobDetailClass();
            jdc.id = json.getInt("id");
            jdc.companyName = json.getString("company_name");
            jdc.companyDesc = json.getString("company_desc");
            jdc.companyHeads = json.getInt("company_heads");
            jdc.workArea = json.getString("work_area");
            jdc.workYears = json.getInt("work_years");
            jdc.education = json.getInt("education");
            jdc.inHeads = json.getInt("in_heads");
            jdc.publishDate = json.getString("publish_date");
            jdc.endDate = json.getString("end_date");
            jdc.salaryRange = json.getString("salary_range");
            jdc.jobTitle = json.getString("job_title");
            jdc.jobAccoutability = json.getString("job_accountability");
            jdc.jobRequirement = json.getString("job_requirement");
            jdc.keywords = json.getString("keywords");
            jdc.emergency = json.getInt("emergency");
            jdc.tags = json.getString("tags");
            jdc.color = json.getInt("color");
            jdc.status = json.getInt("status");
        } catch (Exception e) {

        }
        return jdc;
    }

}
