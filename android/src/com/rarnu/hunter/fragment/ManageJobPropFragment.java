package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.JobPropClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.ResultClass;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.JobPropLoader;

public class ManageJobPropFragment extends BaseFragment implements View.OnClickListener, Loader.OnLoadCompleteListener<JobPropClass> {

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

    ArrayAdapter<String> adapterEmergency;
    ArrayAdapter<String> adapterColor;
    ArrayAdapter<String> adapterStatus;

    JobPropClass item = null;

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
        initSpinner();
    }

    private void initSpinner() {
        String[] strEmergency = new String[]{"非常高", "高", "普通", "低", "非常低"};
        adapterEmergency = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, strEmergency);
        vEmergency.setAdapter(adapterEmergency);
        vEmergency.setSelection(2);

        String[] strColor = new String[]{"黑色", "红色", "蓝色", "绿色"};
        adapterColor = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, strColor);
        vColor.setAdapter(adapterColor);
        vColor.setSelection(0);

        String[] strStatus = new String[]{"打开", "关闭"};
        adapterStatus = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, strStatus);
        vStatus.setAdapter(adapterStatus);
        vStatus.setSelection(0);
    }

    @Override
    public void initEvents() {
        tvNoConnection.setOnClickListener(this);
        loader.registerListener(0, this);
    }

    @Override
    public void initLogic() {
        int id = getActivity().getIntent().getIntExtra("id", -1);
        loader.setId(id);
        doLoading();
    }

    private void doLoading() {
        svProp.setVisibility(View.GONE);
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        loader.startLoading();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_SAVE:
                savePropT();
                break;
        }
        return true;
    }

    private Handler hSave = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 && getActivity() != null) {
                miSave.setEnabled(true);
                pbSaving.setVisibility(View.GONE);
                if (msg.arg1 == 0) {
                    Toast.makeText(getActivity(), R.string.data_succ, Toast.LENGTH_SHORT).show();
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), R.string.data_fail, Toast.LENGTH_SHORT).show();
                }
            }
            super.handleMessage(msg);
        }
    };

    private void savePropT() {
        if (item == null) {
            return;
        }
        item.keywords = vKey1.getText().toString();
        item.key2 = vKey2.getText().toString();
        item.key3 = vKey3.getText().toString();
        item.key4 = vKey4.getText().toString();
        item.key5 = vKey5.getText().toString();
        item.emergency = 2 - vEmergency.getSelectedItemPosition();
        item.color = vColor.getSelectedItemPosition();
        item.status = vStatus.getSelectedItemPosition();
        pbSaving.setVisibility(View.VISIBLE);
        miSave.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultClass rc = MobileApi.editJobProp(item);
                Message msg = new Message();
                msg.what = 1;
                if (rc != null) {
                    msg.arg1 = rc.result;
                } else {
                    msg.arg1 = 1;
                }
                hSave.sendMessage(msg);
            }
        }).start();

    }

    @Override
    public void onGetNewArguments(Bundle bn) {

    }

    @Override
    public Bundle getFragmentState() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNoConnection:
                doLoading();
                break;
        }
    }

    @Override
    public void onLoadComplete(Loader<JobPropClass> loader, JobPropClass data) {
        item = data;
        if (getActivity() != null) {
            pbLoading.setVisibility(View.GONE);
            if (data != null) {
                vKey1.setText(data.keywords);
                vKey2.setText(data.key2);
                vKey3.setText(data.key3);
                vKey4.setText(data.key4);
                vKey5.setText(data.key5);
                vEmergency.setSelection(2 - data.emergency);
                vColor.setSelection(data.color);
                vStatus.setSelection(data.status);
                svProp.setVisibility(View.VISIBLE);
            } else {
                tvNoConnection.setVisibility(View.VISIBLE);
                svProp.setVisibility(View.GONE);
            }
        }
    }
}
