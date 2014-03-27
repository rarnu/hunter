package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.ManageAddOrEditJobFragment;
import com.rarnu.hunter.fragment.ManageAddOrEditTimelineFragment;

public class ManageAddOrEditTimelineActivity extends BaseActivity {

    ManageAddOrEditTimelineFragment aetf = null;

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (aetf == null) {
            aetf = new ManageAddOrEditTimelineFragment();
        }
        return aetf;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
