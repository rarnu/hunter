package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.Menu;
import com.rarnu.devlib.base.BasePreferenceFragment;
import com.rarnu.hunter.AdminDialog;
import com.rarnu.hunter.ManageActivity;
import com.rarnu.hunter.R;
import com.rarnu.hunter.common.Config;
import com.rarnu.hunter.common.Ids;

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
            // TODO: check update
        } else if (preference.getKey().equals(getString(R.string.id_feedback))) {
            // TODO; feedback
        } else if (preference.getKey().equals(getString(R.string.id_about))) {
            // TODO: about
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
}
