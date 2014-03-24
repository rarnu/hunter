package com.rarnu.hunter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.rarnu.devlib.base.BaseActivity;
import com.rarnu.hunter.fragment.MainFragment;
import com.rarnu.utils.ResourceUtils;
import com.rarnu.utils.UIUtils;

public class MainActivity extends BaseActivity {

    MainFragment mf = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UIUtils.initDisplayMetrics(this, getWindowManager(), false);
        ResourceUtils.init(this);
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        // getActionBar().setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_launcher;
    }

    @Override
    public Fragment replaceFragment() {
        if (mf == null) {
            mf = new MainFragment();
        }
        return mf;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
