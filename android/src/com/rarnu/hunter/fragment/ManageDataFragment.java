package com.rarnu.hunter.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.common.Ids;
import com.rarnu.utils.ResourceUtils;

public class ManageDataFragment extends BaseFragment {

    MenuItem miSave;

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
        miSave = menu.add(0, Ids.MENU_ID_SAVE, 99, R.string.submit);
        miSave.setIcon(android.R.drawable.ic_menu_save);
        miSave.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_SAVE:
                // TODO: save data
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
