package com.rarnu.hunter.fragment;

import android.os.Bundle;
import android.view.Menu;
import com.rarnu.devlib.base.BasePreferenceFragment;
import com.rarnu.hunter.R;

public class SettingsFragment extends BasePreferenceFragment {
    @Override
    public int getBarTitle() {
        return R.string.title_settings;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.title_settings;
    }

    @Override
    public String getCustomTitle() {
        return null;
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initLogic() {

    }

    @Override
    public int getFragmentLayoutResId() {
        return R.xml.settings;
    }

    @Override
    public String getMainActivityName() {
        return "";
    }

    @Override
    public void initMenu(Menu menu) {

    }

    @Override
    public void onGetNewArguments(Bundle bn) {

    }

    @Override
    public Bundle getFragmentState() {
        return null;
    }
}
