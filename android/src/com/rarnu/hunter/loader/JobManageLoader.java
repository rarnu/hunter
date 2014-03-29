package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseLoader;
import com.rarnu.hunter.api.JobManageClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.LocalDir;
import com.rarnu.utils.FileUtils;

import java.util.List;

public class JobManageLoader extends BaseLoader<JobManageClass> {

    private int _page = 1;

    private boolean local = true;

    public JobManageLoader(Context context) {
        super(context);
    }

    public void setPage(int page) {
        this._page = page;
    }

    public int getPage() {
        return _page;
    }

    @Override
    public List<JobManageClass> loadInBackground() {
        List<JobManageClass> list = null;
        if (_page == 1 && local) {
            local = false;
            list = (List<JobManageClass>) FileUtils.loadListFromFile(LocalDir.LOCALDIR + "_job_manage");
        }
        if (list == null) {
            list = MobileApi.queryManage(_page);
            if (_page == 1 && list != null && list.size() != 0) {
                FileUtils.saveListToFile(list, LocalDir.LOCALDIR + "_job_manage");
            } else if (_page == 1 && (list == null || list.size() == 0)) {
                list = (List<JobManageClass>) FileUtils.loadListFromFile(LocalDir.LOCALDIR + "_job_manage");
            }
        }
        return list;
    }
}
