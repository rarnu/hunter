package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseLoader;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.api.MobileApi;

import java.util.List;

public class TopViewLoader extends BaseLoader<JobClass> {

    private int _page = 1;

    public TopViewLoader(Context context) {
        super(context);
    }

    public void setPage(int page) {
        this._page = page;
    }

    @Override
    public List<JobClass> loadInBackground() {
        return MobileApi.queryJobTop(_page);
    }
}