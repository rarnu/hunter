package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseLoader;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.TimelineClass;
import com.rarnu.hunter.common.LocalDir;
import com.rarnu.utils.FileUtils;

import java.util.List;

public class TimelineLoader extends BaseLoader<TimelineClass> {

    private int _page = 1;

    private boolean local = true;

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

        List<TimelineClass> list = null;
        if (_page == 1 && local) {
            local = false;
            list = (List<TimelineClass>) FileUtils.loadListFromFile(LocalDir.LOCALDIR + "_timeline");
        }
        if (list == null) {
            list = MobileApi.queryTimeline(_page);
            if (_page == 1 && list != null && list.size() != 0) {
                FileUtils.saveListToFile(list, LocalDir.LOCALDIR + "_timeline");
            } else if (_page == 1 && (list == null || list.size() == 0)) {
                list = (List<TimelineClass>) FileUtils.loadListFromFile(LocalDir.LOCALDIR + "_timeline");
            }
        }
        return list;
    }
}
