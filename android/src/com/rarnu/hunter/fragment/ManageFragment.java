package com.rarnu.hunter.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import com.rarnu.devlib.base.BaseTabFragment;
import com.rarnu.hunter.R;

import java.util.List;

public class ManageFragment extends BaseTabFragment {

    ManageJobFragment mjf = null;
    ManageMyFragment mmf = null;
    ManageDataFragment mdf = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mjf = new ManageJobFragment();
        mmf = new ManageMyFragment();
        mdf = new ManageDataFragment();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initFragmentList(List<Fragment> listFragment) {
        listFragment.add(mjf);
        listFragment.add(mmf);
        listFragment.add(mdf);
    }

    @Override
    public int getBarTitle() {
        return R.string.title_manage;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.title_manage;
    }

    @Override
    public String getCustomTitle() {
        return null;
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
