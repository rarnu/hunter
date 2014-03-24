package com.rarnu.hunter.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.rarnu.devlib.base.BaseTabFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.SettingsActivity;
import com.rarnu.hunter.common.Ids;

import java.util.List;

public class MainFragment extends BaseTabFragment {

    MenuItem miSettings;
    MenuItem miShare;

    JobsFragment jf;
    SearchFragment sf;
    TopViewFragment tf;
    HistoryFragment hf;
    ContactFragment cf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jf = new JobsFragment();
        sf = new SearchFragment();
        tf = new TopViewFragment();
        hf = new HistoryFragment();
        cf = new ContactFragment();
    }

    @Override
    public void initFragmentList(List<Fragment> listFragment) {
        listFragment.add(jf);
        listFragment.add(sf);
        listFragment.add(tf);
        listFragment.add(hf);
        listFragment.add(cf);
    }

    @Override
    public int getBarTitle() {
        return R.string.app_name;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.app_name;
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
        miSettings = menu.add(0, Ids.MENU_ID_SETTINGS, 99, R.string.menu_settings);
        miSettings.setIcon(R.drawable.ic_menu_preferences);
        miSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        miShare = menu.add(0, Ids.MENU_ID_SHARE, 98, R.string.menu_share);
        miShare.setIcon(R.drawable.ic_menu_share);
        miShare.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_SHARE:
                // TODO: share
                break;
            case Ids.MENU_ID_SETTINGS:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onGetNewArguments(Bundle bn) {

    }

    @Override
    public Bundle getFragmentState() {
        return null;
    }
}
