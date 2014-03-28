package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseLoader;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.LocalDir;
import com.rarnu.utils.FileUtils;

import java.util.List;

public class JobLoader extends BaseLoader<JobClass> {

    private int _page = 1;
    private boolean local = true;

    public JobLoader(Context context) {
        super(context);
    }

    public void setPage(int page) {
        this._page = page;
    }

    public int getPage() {
        return _page;
    }

    @Override
    public List<JobClass> loadInBackground() {
        List<JobClass> list = null;
        if (_page == 1 && local) {
            local = false;
            list = (List<JobClass>) FileUtils.loadListFromFile(LocalDir.LOCALDIR + "_job");
        }
        if (list == null) {
            list = MobileApi.queryJob(_page);
            if (_page == 1 && list != null && list.size() != 0) {
                FileUtils.saveListToFile(list, LocalDir.LOCALDIR + "_job");
            }
        }
        return list;
    }
}
