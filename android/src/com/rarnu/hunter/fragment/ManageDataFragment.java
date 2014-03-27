package com.rarnu.hunter.fragment;

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
import com.rarnu.hunter.api.DataClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.ResultClass;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.DataLoader;
import com.rarnu.utils.ResourceUtils;

public class ManageDataFragment extends BaseFragment implements Loader.OnLoadCompleteListener<DataClass>, View.OnClickListener {

    MenuItem miSave;
    ScrollView svContact;
    TextView tvNoConnection;
    RelativeLayout pbLoading;
    EditText vMailWork;
    EditText vMailPrivate;
    EditText vQQ;
    EditText vHangouts;
    EditText vWX;
    EditText vPhoneWork;
    EditText vPhonePrivate;
    EditText vAddress;
    DataLoader loader;
    DataClass item = null;
    RelativeLayout pbSaving;

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
        vMailWork = (EditText) innerView.findViewById(R.id.vMailWork);
        vMailPrivate = (EditText) innerView.findViewById(R.id.vMailPrivate);
        vQQ = (EditText) innerView.findViewById(R.id.vQQ);
        vHangouts = (EditText) innerView.findViewById(R.id.vHangouts);
        vWX = (EditText) innerView.findViewById(R.id.vWX);
        vPhoneWork = (EditText) innerView.findViewById(R.id.vPhoneWork);
        vPhonePrivate = (EditText) innerView.findViewById(R.id.vPhonePrivate);
        vAddress = (EditText) innerView.findViewById(R.id.vAddress);

        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        svContact = (ScrollView) innerView.findViewById(R.id.svContact);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        loader = new DataLoader(getActivity());
        pbSaving = (RelativeLayout) innerView.findViewById(R.id.pbSaving);
    }

    @Override
    public void initEvents() {
        loader.registerListener(0, this);
        tvNoConnection.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        doLoading();
    }

    private void doLoading() {
        svContact.setVisibility(View.GONE);
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        loader.startLoading();
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
                doSaveDataT();
                break;
        }
        return true;
    }

    private Handler hData = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 && getActivity() != null) {
                pbSaving.setVisibility(View.GONE);
                Toast.makeText(getActivity(), msg.arg1 == 0 ? R.string.data_succ : R.string.data_fail, Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    private void doSaveDataT() {
        if (item == null) {
            return;
        }

        item.mailWork = vMailWork.getText().toString();
        item.mailPrivate = vMailPrivate.getText().toString();
        item.qq = vQQ.getText().toString();
        item.wx = vWX.getText().toString();
        item.hangouts = vHangouts.getText().toString();
        item.phoneWork = vPhoneWork.getText().toString();
        item.phonePrivate = vPhonePrivate.getText().toString();
        item.address = vAddress.getText().toString();
        pbSaving.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultClass rc = MobileApi.editData(item);
                Message msg = new Message();
                msg.what = 1;
                // TODO: save data error?
                if (rc != null) {
                    msg.arg1 = rc.result;
                } else {
                    msg.arg1 = 1;
                }
                hData.sendMessage(msg);
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
    public void onLoadComplete(Loader<DataClass> loader, DataClass data) {
        item = data;
        if (getActivity() != null) {
            pbLoading.setVisibility(View.GONE);
            if (data != null) {
                vMailWork.setText(data.mailWork);
                vMailPrivate.setText(data.mailPrivate);
                vQQ.setText(data.qq);
                vHangouts.setText(data.hangouts);
                vWX.setText(data.wx);
                vPhoneWork.setText(data.phoneWork);
                vPhonePrivate.setText(data.phonePrivate);
                vAddress.setText(data.address);
                svContact.setVisibility(View.VISIBLE);
            } else {
                tvNoConnection.setVisibility(View.VISIBLE);
                svContact.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNoConnection:
                doLoading();
                break;
        }
    }
}
