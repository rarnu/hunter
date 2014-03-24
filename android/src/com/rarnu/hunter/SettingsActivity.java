package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.SettingsFragment;

public class SettingsActivity extends BaseActivity {

    SettingsFragment sf;

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (sf == null) {
            sf = new SettingsFragment();
        }
        return sf;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
