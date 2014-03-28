package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.FeedbackFragment;

public class FeedbackActivity extends BaseActivity{

    FeedbackFragment ff = null;

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (ff == null) {
            ff = new FeedbackFragment();
        }
        return ff;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
