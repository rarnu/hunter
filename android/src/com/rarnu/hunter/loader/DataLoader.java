package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseClassLoader;
import com.rarnu.hunter.api.DataClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.LocalDir;
import com.rarnu.utils.FileUtils;

public class DataLoader extends BaseClassLoader<DataClass> {

    private boolean local = true;

    public DataLoader(Context context) {
        super(context);
    }

    @Override
    public DataClass loadInBackground() {

        DataClass dc = null;
        if (local) {
            local = false;
            dc = (DataClass) FileUtils.loadObjetFromFile(LocalDir.LOCALDIR + "_data");
        }
        if (dc == null) {
            dc = MobileApi.queryData();
            if (dc != null) {
                FileUtils.saveObjectToFile(dc, LocalDir.LOCALDIR + "_data");
            } else if (dc == null) {
                dc = (DataClass) FileUtils.loadObjetFromFile(LocalDir.LOCALDIR + "_data");
            }
        }

        return dc;
    }
}
