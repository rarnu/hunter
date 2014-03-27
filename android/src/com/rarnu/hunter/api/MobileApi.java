package com.rarnu.hunter.api;

import android.util.Log;
import com.rarnu.utils.HttpRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MobileApi {

    private static final String SITE_URL = "http://rarnu.7thgen.info/vicky/";
    private static final String BASE_URL = SITE_URL + "api/";
    public static final String MAP_URL = SITE_URL + "image/map.png";

    public static List<JobClass> queryJob(int _page) {
        String ret = HttpRequest.get(BASE_URL + "api_query_job.php", String.format("_from=mobile&_page=%d", _page), HTTP.UTF_8);
        return parseString(ret);
    }

    public static List<JobClass> queryJobSearch(String _keyword, int _page) {
        try {
            _keyword = URLEncoder.encode(_keyword, HTTP.UTF_8);
        } catch (Exception e) {

        }
        String ret = HttpRequest.get(BASE_URL + "api_query_job_search.php", String.format("_from=mobile&_page=%d&_keyword=%s", _page, _keyword), HTTP.UTF_8);
        Log.e("queryJobSearch", ret);
        return parseString(ret);
    }

    public static List<JobClass> queryJobTop(int _page) {
        String ret = HttpRequest.get(BASE_URL + "api_query_job_top.php", String.format("_from=mobile&_page=%d", _page), HTTP.UTF_8);
        return parseString(ret);
    }

    private static List<JobClass> parseString(String jsonString) {
        List<JobClass> list = null;
        try {
            JSONObject jsonRet = new JSONObject(jsonString);
            JSONArray jarr = jsonRet.getJSONArray("data");
            list = new ArrayList<JobClass>();
            for (int i = 0; i < jarr.length(); i++) {
                list.add(JobClass.fromJson(jarr.getJSONObject(i)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static JobDetailClass queryJobDetail(int _id) {
        String ret = HttpRequest.get(BASE_URL + "api_query_job_detail.php", String.format("_from=mobile&_id=%d", _id), HTTP.UTF_8);
        JobDetailClass jdc = null;
        try {
            JSONObject json = new JSONObject(ret);
            jdc = JobDetailClass.fromJson(json);
        } catch (Exception e) {

        }
        return jdc;
    }

    public static AnnoClass queryAnno() {
        String ret = HttpRequest.get(BASE_URL + "api_query_anno.php", "_from=mobile", HTTP.UTF_8);
        AnnoClass ac = null;
        try {
            JSONObject json = new JSONObject(ret);
            ac = AnnoClass.fromJson(json);
        } catch (Exception e) {

        }
        return ac;
    }

    public static List<TimelineClass> queryTimeline(int _page) {
        String ret = HttpRequest.get(BASE_URL + "api_query_timeline.php", String.format("_from=mobile&_page=%d", _page), HTTP.UTF_8);
        List<TimelineClass> list = null;
        try {
            JSONObject json = new JSONObject(ret);
            JSONArray jarr = json.getJSONArray("data");
            list = new ArrayList<TimelineClass>();
            for (int i = 0; i < jarr.length(); i++) {
                list.add(TimelineClass.fromJson(jarr.getJSONObject(i)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static DataClass queryData() {
        String ret = HttpRequest.get(BASE_URL + "api_query_data.php", "", HTTP.UTF_8);
        DataClass dc = null;
        try {
            JSONObject json = new JSONObject(ret);
            dc = DataClass.fromJson(json);
        } catch (Exception e) {

        }
        return dc;
    }

    public static LoginClass login(String account, String password) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_user_name", account));
        param.add(new BasicNameValuePair("_user_pwd", password));
        String ret = HttpRequest.post(BASE_URL + "api_login.php", param, HTTP.UTF_8);
        LoginClass lc = null;
        try {
            JSONObject json = new JSONObject(ret);
            lc = LoginClass.fromJson(json);
        } catch (Exception e) {

        }
        return lc;
    }

    public static ResultClass editAnno(String comment) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_from", "mobile"));
        param.add(new BasicNameValuePair("_comment", comment));
        String ret = HttpRequest.post(BASE_URL + "api_edit_anno.php", param, HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass editData(DataClass data) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_from", "mobile"));
        param.add(new BasicNameValuePair("_id", String.valueOf(data.id)));
        param.add(new BasicNameValuePair("_mail_work", data.mailWork));
        param.add(new BasicNameValuePair("_mail_private", data.mailPrivate));
        param.add(new BasicNameValuePair("_qq", data.qq));
        param.add(new BasicNameValuePair("_wx", data.wx));
        param.add(new BasicNameValuePair("_hangouts", data.hangouts));
        param.add(new BasicNameValuePair("_phone_work", data.phoneWork));
        param.add(new BasicNameValuePair("_phone_private", data.phonePrivate));
        param.add(new BasicNameValuePair("_address", data.address));
        String ret = HttpRequest.post(BASE_URL + "api_edit_data.php", param, HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass publishJob(JobDetailClass jdc) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_from", "mobile"));
        param.add(new BasicNameValuePair("_company_name", jdc.companyName));
        param.add(new BasicNameValuePair("_company_desc", jdc.companyDesc));
        param.add(new BasicNameValuePair("_company_heads", String.valueOf(jdc.companyHeads)));
        param.add(new BasicNameValuePair("_work_area", jdc.workArea));
        param.add(new BasicNameValuePair("_work_years", String.valueOf(jdc.workYears)));
        param.add(new BasicNameValuePair("_education", String.valueOf(jdc.education)));
        param.add(new BasicNameValuePair("_in_heads", String.valueOf(jdc.inHeads)));
        param.add(new BasicNameValuePair("_publish_date", jdc.publishDate));
        param.add(new BasicNameValuePair("_end_aate", jdc.endDate));
        param.add(new BasicNameValuePair("_salary_range", jdc.salaryRange));
        param.add(new BasicNameValuePair("_job_title", jdc.jobTitle));
        param.add(new BasicNameValuePair("_job_accountability", jdc.jobAccoutability));
        param.add(new BasicNameValuePair("_job_requirement", jdc.jobRequirement));
        String ret = HttpRequest.post(BASE_URL + "api_publish.php", param, HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass editJob(JobDetailClass jdc) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_from", "mobile"));
        param.add(new BasicNameValuePair("_id", String.valueOf(jdc.id)));
        param.add(new BasicNameValuePair("_company_name", jdc.companyName));
        param.add(new BasicNameValuePair("_company_desc", jdc.companyDesc));
        param.add(new BasicNameValuePair("_company_heads", String.valueOf(jdc.companyHeads)));
        param.add(new BasicNameValuePair("_work_area", jdc.workArea));
        param.add(new BasicNameValuePair("_work_years", String.valueOf(jdc.workYears)));
        param.add(new BasicNameValuePair("_education", String.valueOf(jdc.education)));
        param.add(new BasicNameValuePair("_in_heads", String.valueOf(jdc.inHeads)));
        param.add(new BasicNameValuePair("_publish_date", jdc.publishDate));
        param.add(new BasicNameValuePair("_end_aate", jdc.endDate));
        param.add(new BasicNameValuePair("_salary_range", jdc.salaryRange));
        param.add(new BasicNameValuePair("_job_title", jdc.jobTitle));
        param.add(new BasicNameValuePair("_job_accountability", jdc.jobAccoutability));
        param.add(new BasicNameValuePair("_job_requirement", jdc.jobRequirement));
        String ret = HttpRequest.post(BASE_URL + "api_edit.php", param, HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass deleteJob(int _id) {
        String ret = HttpRequest.get(BASE_URL + "api_delete_job.php", String.format("_id=%d", _id), HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass publishTimeline(TimelineClass tc) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_from", "mobile"));
        param.add(new BasicNameValuePair("_publish_date", tc.publishDate));
        param.add(new BasicNameValuePair("_comment", tc.comment));
        String ret = HttpRequest.post(BASE_URL + "api_publish_timeline.php", param, HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass editTimeline(TimelineClass tc) {
        List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
        param.add(new BasicNameValuePair("_from", "mobile"));
        param.add(new BasicNameValuePair("_id", String.valueOf(tc.id)));
        param.add(new BasicNameValuePair("_publish_date", tc.publishDate));
        param.add(new BasicNameValuePair("_comment", tc.comment));
        String ret = HttpRequest.post(BASE_URL + "api_edit_timeline.php", param, HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static ResultClass deleteTimeline(int _id) {
        String ret = HttpRequest.get(BASE_URL + "api_delete_timeline.php", String.format("_id=%d", _id), HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }

    public static JobPropClass queryJobProp(int _id) {
        String ret = HttpRequest.get(BASE_URL + "api_query_job_prop.php", String.format("_id=%d", _id), HTTP.UTF_8);
        JobPropClass jpc = null;
        try {
            JSONObject json = new JSONObject(ret);
            jpc = JobPropClass.fromJson(json);
        } catch (Exception e) {

        }
        return jpc;
    }

    public static ResultClass editJobProp(JobPropClass jpc) {
        try {
            jpc.keywords = URLEncoder.encode(jpc.keywords, HTTP.UTF_8);
            jpc.key2 = URLEncoder.encode(jpc.key2, HTTP.UTF_8);
            jpc.key3 = URLEncoder.encode(jpc.key3, HTTP.UTF_8);
            jpc.key4 = URLEncoder.encode(jpc.key4, HTTP.UTF_8);
            jpc.key5 = URLEncoder.encode(jpc.key5, HTTP.UTF_8);
        } catch (Exception e) {

        }
        String ret = HttpRequest.get(BASE_URL + "api_edit_job_prop.php", String.format(
                "_id=%d&_keywords=%s&_key2=%s&_key3=%s&_key4=%s&_key5=%s&_emergency=%d&_color=%d&_status=%d",
                jpc.id, jpc.keywords, jpc.key2, jpc.key3, jpc.key4, jpc.key5, jpc.emergency, jpc.color, jpc.status), HTTP.UTF_8);
        ResultClass rc = null;
        try {
            JSONObject json = new JSONObject(ret);
            rc = ResultClass.fromJson(json);
        } catch (Exception e) {

        }
        return rc;
    }
}
