package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.ManageJobPropFragment;

public class ManageJobPropActivity extends BaseActivity {

    ManageJobPropFragment mjpf = null;

    @Override
    public int getIcon() {
        return 0;
    }

    @Override
    public Fragment replaceFragment() {
        if (mjpf == null) {
            mjpf = new ManageJobPropFragment();
        }
        return mjpf;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
