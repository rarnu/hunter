package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.R;
import com.rarnu.hunter.fragment.JobDetailFragment;

public class JobDetailActivity extends BaseActivity {

    JobDetailFragment jdf = null;

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (jdf == null) {
            jdf = new JobDetailFragment();
        }
        return jdf;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
