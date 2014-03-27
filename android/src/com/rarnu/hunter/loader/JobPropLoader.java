package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseClassLoader;
import com.rarnu.hunter.api.JobPropClass;
import com.rarnu.hunter.api.MobileApi;

public class JobPropLoader extends BaseClassLoader<JobPropClass> {

    private int _id;

    public JobPropLoader(Context context) {
        super(context);
    }

    public void setId(int id) {
        this._id = id;
    }

    @Override
    public JobPropClass loadInBackground() {
        return MobileApi.queryJobProp(_id);
    }
}
