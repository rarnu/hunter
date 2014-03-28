package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.view.Menu;
import android.widget.Toast;
import com.rarnu.devlib.base.BasePreferenceFragment;
import com.rarnu.hunter.*;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.UpdateClass;
import com.rarnu.hunter.common.Config;
import com.rarnu.hunter.common.Ids;
import com.rarnu.utils.DeviceUtilsLite;

public class SettingsFragment extends BasePreferenceFragment implements Preference.OnPreferenceClickListener {

    Preference prefAdmin;
    Preference prefManage;
    Preference prefCheckUpdate;
    Preference prefFeedback;
    Preference prefAbout;

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
        prefAdmin = findPreference(getString(R.string.id_admin));
        prefManage = findPreference(getString(R.string.id_manage));
        prefCheckUpdate = findPreference(getString(R.string.id_check_update));
        prefFeedback = findPreference(getString(R.string.id_feedback));
        prefAbout = findPreference(getString(R.string.id_about));
    }

    @Override
    public void initEvents() {
        prefAdmin.setOnPreferenceClickListener(this);
        prefManage.setOnPreferenceClickListener(this);
        prefCheckUpdate.setOnPreferenceClickListener(this);
        prefFeedback.setOnPreferenceClickListener(this);
        prefAbout.setOnPreferenceClickListener(this);
    }

    @Override
    public void initLogic() {
        setAdminSummary();
    }

    private void setAdminSummary() {
        if (Config.isAdminEnabled(getActivity())) {
            prefManage.setEnabled(true);
            prefAdmin.setSummary(getString(R.string.pref_summary_admin, Config.getAdminAccount(getActivity())));
        } else {
            prefManage.setEnabled(false);
            prefAdmin.setSummary(getString(R.string.pref_summary_not_admin));
        }
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

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.id_admin))) {
            if (Config.isAdminEnabled(getActivity())) {
                doLogout();
            } else {
                doLogin();
            }
        } else if (preference.getKey().equals(getString(R.string.id_manage))) {
            Intent inMgr = new Intent(getActivity(), ManageActivity.class);
            startActivity(inMgr);
        } else if (preference.getKey().equals(getString(R.string.id_check_update))) {
            checkUpdateT();
        } else if (preference.getKey().equals(getString(R.string.id_feedback))) {
            Intent inFeedback = new Intent(getActivity(), FeedbackActivity.class);
            startActivity(inFeedback);
        } else if (preference.getKey().equals(getString(R.string.id_about))) {
            Intent inAbout = new Intent(getActivity(), AboutDialog.class);
            startActivity(inAbout);
        }
        return true;
    }

    private void doLogin() {
        Intent inLogin = new Intent(getActivity(), AdminDialog.class);
        startActivityForResult(inLogin, Ids.REQUEST_ADMIN);
    }

    private void doLogout() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setMessage(R.string.confirm_logout)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Config.setAdminAccount(getActivity(), "");
                        Config.setAdminEnabled(getActivity(), false);
                        setAdminSummary();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Ids.REQUEST_ADMIN:
                setAdminSummary();
                break;
        }
    }

    private Handler hUpdate = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 && getActivity() != null) {
                UpdateClass uc = (UpdateClass) msg.obj;
                if (uc != null) {
                    if (uc.result == 0) {
                        showUpdateMsg(uc);
                    } else {
                        showNoUpdate();
                    }
                }
            }
            super.handleMessage(msg);
        }
    };

    private void showNoUpdate() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setMessage(R.string.toast_no_update)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private void showUpdateMsg(final UpdateClass uc) {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setMessage(getString(R.string.toast_update_found, uc.versionName, uc.versionDesc))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDownloadNewVersion(uc.versionFile);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();

    }

    private void doDownloadNewVersion(String file) {
        Intent inDownload = new Intent(Intent.ACTION_VIEW);
        inDownload.setData(Uri.parse(MobileApi.DOWNLOAD_URL + file));
        startActivity(inDownload);
    }

    private void checkUpdateT() {
        Toast.makeText(getActivity(), R.string.toast_check_update, Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int ver = DeviceUtilsLite.getAppVersionCode(getActivity());
                UpdateClass uc = MobileApi.checkUpdate(ver);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = uc;
                hUpdate.sendMessage(msg);
            }
        }).start();
    }
}
