package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseClassLoader;
import com.rarnu.hunter.api.DataClass;
import com.rarnu.hunter.api.MobileApi;

public class DataLoader extends BaseClassLoader<DataClass> {
    public DataLoader(Context context) {
        super(context);
    }

    @Override
    public DataClass loadInBackground() {
        return MobileApi.queryData();
    }
}
