package com.rarnu.hunter.fragment;

import android.accounts.OnAccountsUpdateListener;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.FeedbackClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.utils.AccountUtils;
import com.rarnu.utils.DeviceUtilsLite;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedbackFragment extends BaseFragment {

    EditText etFeedback;
    MenuItem miSave;

    @Override
    public int getBarTitle() {
        return R.string.pref_feedback;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.pref_feedback;
    }

    @Override
    public String getCustomTitle() {
        return null;
    }

    @Override
    public void initComponents() {
        etFeedback = (EditText) innerView.findViewById(R.id.etFeedback);
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initLogic() {

    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_feedback;
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
                sendFeedbackT();
                break;
        }
        return true;
    }

    private void sendFeedbackT() {

        final FeedbackClass fc = new FeedbackClass();
        fc.type = 1;
        fc.device = Build.MODEL;
        fc.os = Build.VERSION.RELEASE;
        fc.app = DeviceUtilsLite.getAppVersionName(getActivity());
        fc.accounts = AccountUtils.getBindedAccountsAsString(getActivity());
        fc.publishDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        fc.comment = etFeedback.getText().toString();

        if (fc.comment.equals("")) {
            Toast.makeText(getActivity(), R.string.toast_feedback_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                MobileApi.addFeedback(fc);
            }
        }).start();

        getActivity().finish();
    }

    @Override
    public void onGetNewArguments(Bundle bn) {

    }

    @Override
    public Bundle getFragmentState() {
        return null;
    }
}
