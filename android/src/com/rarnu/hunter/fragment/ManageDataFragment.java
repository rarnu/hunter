package com.rarnu.hunter.fragment;

import android.os.Bundle;
import android.view.Menu;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.utils.ResourceUtils;

public class ManageDataFragment extends BaseFragment {

    public ManageDataFragment() {
        super();
        tabTitle = ResourceUtils.getString(R.string.title_manage_data);
    }

    @Override
    public int getBarTitle() {
        return 0;
    }

    @Override
    public int getBarTitleWithPath() {
        return 0;
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
        return R.layout.layout_manage_contact;
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