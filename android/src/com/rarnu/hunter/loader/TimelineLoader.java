package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseLoader;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.TimelineClass;

import java.util.List;

public class TimelineLoader extends BaseLoader<TimelineClass> {

    private int _page = 1;

    public TimelineLoader(Context context) {
        super(context);
    }

    public void setPage(int page) {
        this._page = page;
    }

    public int getPage() {
        return _page;
    }

    @Override
    public List<TimelineClass> loadInBackground() {
        return MobileApi.queryTimeline(_page);
    }
}
