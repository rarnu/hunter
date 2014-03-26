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
}
