package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseClassLoader;
import com.rarnu.hunter.api.JobDetailClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.LocalDir;
import com.rarnu.utils.FileUtils;

public class JobDetailLoader extends BaseClassLoader<JobDetailClass> {

    private int _id;

    private boolean local = true;

    public JobDetailLoader(Context context) {
        super(context);
    }

    public void setId(int id) {
        this._id = id;
    }

    @Override
    public JobDetailClass loadInBackground() {

        JobDetailClass dc = null;
        if (local) {
            local = false;
            dc = (JobDetailClass) FileUtils.loadObjetFromFile(LocalDir.LOCALDIR + "_job_detail_" + String.valueOf(_id));
        }
        if (dc == null) {
            dc = MobileApi.queryJobDetail(_id);
            if (dc != null) {
                FileUtils.saveObjectToFile(dc, LocalDir.LOCALDIR + "_job_detail_" + String.valueOf(_id));
            } else if (dc == null) {
                dc = (JobDetailClass) FileUtils.loadObjetFromFile(LocalDir.LOCALDIR + "_job_detail_" + String.valueOf(_id));
            }
        }

        return dc;
    }
}
