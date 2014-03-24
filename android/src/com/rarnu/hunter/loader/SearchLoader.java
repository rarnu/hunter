package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseLoader;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.api.MobileApi;

import java.util.List;

public class SearchLoader extends BaseLoader<JobClass> {

    private int _page = 1;
    private String _keyword = "";

    public SearchLoader(Context context) {
        super(context);
    }

    public void setPage(int page) {
        this._page = page;
    }

    public void setKeyword(String key) {
        this._keyword = key;
    }

    @Override
    public List<JobClass> loadInBackground() {
        return MobileApi.queryJobSearch(_keyword, _page);
    }
}
