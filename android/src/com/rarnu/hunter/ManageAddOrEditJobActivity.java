package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.ManageAddOrEditJobFragment;

public class ManageAddOrEditJobActivity extends BaseActivity {

    ManageAddOrEditJobFragment aejf = null;

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (aejf == null) {
            aejf = new ManageAddOrEditJobFragment();
        }
        return aejf;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
