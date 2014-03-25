package com.rarnu.hunter.api;

import android.util.Log;
import com.rarnu.utils.HttpRequest;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MobileApi {

    private static final String BASE_URL = "http://rarnu.7thgen.info/vicky/api/";

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
}
