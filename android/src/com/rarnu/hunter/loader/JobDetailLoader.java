package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseClassLoader;
import com.rarnu.hunter.api.JobDetailClass;
import com.rarnu.hunter.api.MobileApi;

public class JobDetailLoader extends BaseClassLoader<JobDetailClass> {

    private int _id;

    public JobDetailLoader(Context context) {
        super(context);
    }

    public void setId(int id) {
        this._id = id;
    }

    @Override
    public JobDetailClass loadInBackground() {
        return MobileApi.queryJobDetail(_id);
    }
}
