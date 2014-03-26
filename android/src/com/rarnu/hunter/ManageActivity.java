package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.ManageFragment;

public class ManageActivity extends BaseActivity {

    ManageFragment mf = null;

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (mf == null) {
            mf = new ManageFragment();
        }
        return mf;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
