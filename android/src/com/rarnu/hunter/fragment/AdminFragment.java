package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.rarnu.devlib.base.BaseDialogFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.LoginClass;
import com.rarnu.hunter.common.Config;
import com.rarnu.hunter.loader.LoginLoader;

public class AdminFragment extends BaseDialogFragment implements View.OnClickListener, Loader.OnLoadCompleteListener<LoginClass> {

    ImageView btnCancel;
    ImageView btnSave;
    EditText etAccount;
    EditText etPassword;
    ProgressBar pbLogin;
    LoginLoader loader;

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
        btnCancel = (ImageView) innerView.findViewById(R.id.btnCancel);
        btnSave = (ImageView) innerView.findViewById(R.id.btnSave);
        pbLogin = (ProgressBar) innerView.findViewById(R.id.pbLogin);
        etAccount = (EditText) innerView.findViewById(R.id.etAccount);
        etPassword = (EditText) innerView.findViewById(R.id.etPassword);
        loader = new LoginLoader(getActivity());
    }

    @Override
    public void initEvents() {
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        loader.registerListener(0, this);
    }

    @Override
    public void initLogic() {

    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.dialog_admin;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                doLogin();
                break;
            case R.id.btnCancel:
                getActivity().finish();
                break;
        }
    }

    private void doLogin() {

        String acc = etAccount.getText().toString();
        String pwd = etPassword.getText().toString();
        if (acc.equals("") || pwd.equals("")) {
            Toast.makeText(getActivity(), R.string.msg_account_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        btnSave.setVisibility(View.GONE);
        pbLogin.setVisibility(View.VISIBLE);
        btnCancel.setEnabled(false);
        etAccount.setEnabled(false);
        etPassword.setEnabled(false);
        loader.setUserInfo(acc, pwd);
        loader.startLoading();
    }

    @Override
    public void onLoadComplete(Loader<LoginClass> loader, LoginClass data) {
        if (getActivity() != null) {
            btnSave.setVisibility(View.VISIBLE);
            pbLogin.setVisibility(View.GONE);
            btnCancel.setEnabled(true);
            etAccount.setEnabled(true);
            etPassword.setEnabled(true);
            if (data != null && data.result == 0) {
                returnLoginOk(data);
            } else {
                Toast.makeText(getActivity(), R.string.msg_login_error, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void returnLoginOk(LoginClass data) {
        Config.setAdminEnabled(getActivity(), true);
        Config.setAdminId(getActivity(), data.id);
        Config.setAdminAccount(getActivity(), data.account);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}
