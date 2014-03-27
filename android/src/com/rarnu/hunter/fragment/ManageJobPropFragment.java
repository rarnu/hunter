package com.rarnu.hunter.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.JobPropLoader;

public class ManageJobPropFragment extends BaseFragment {

    // TODO: edit job prop

    MenuItem miSave;
    ScrollView svProp;
    EditText vKey1, vKey2, vKey3, vKey4, vKey5;
    Spinner vEmergency;
    Spinner vColor;
    Spinner vStatus;
    TextView tvNoConnection;
    RelativeLayout pbLoading;
    RelativeLayout pbSaving;
    JobPropLoader loader;

    @Override
    public int getBarTitle() {
        return R.string.title_prop;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.title_prop;
    }

    @Override
    public String getCustomTitle() {
        return null;
    }

    @Override
    public void initComponents() {
        svProp = (ScrollView) innerView.findViewById(R.id.svProp);
        vKey1 = (EditText) innerView.findViewById(R.id.vKey1);
        vKey2 = (EditText) innerView.findViewById(R.id.vKey2);
        vKey3 = (EditText) innerView.findViewById(R.id.vKey3);
        vKey4 = (EditText) innerView.findViewById(R.id.vKey4);
        vKey5 = (EditText) innerView.findViewById(R.id.vKey5);
        vEmergency = (Spinner) innerView.findViewById(R.id.vEmergency);
        vColor = (Spinner) innerView.findViewById(R.id.vColor);
        vStatus = (Spinner) innerView.findViewById(R.id.vStatus);
        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        pbSaving = (RelativeLayout) innerView.findViewById(R.id.pbSaving);
        loader = new JobPropLoader(getActivity());
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initLogic() {

    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_manage_job_prop;
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
    public void onGetNewArguments(Bundle bn) {

    }

    @Override
    public Bundle getFragmentState() {
        return null;
    }
}
