package com.rarnu.hunter.loader;

import android.content.Context;
import com.rarnu.devlib.base.BaseClassLoader;
import com.rarnu.hunter.api.LoginClass;
import com.rarnu.hunter.api.MobileApi;

public class LoginLoader extends BaseClassLoader<LoginClass> {

    private String _user_name;
    private String _user_pwd;

    public LoginLoader(Context context) {
        super(context);
    }

    public void setUserInfo(String account, String pwd) {
        this._user_name = account;
        this._user_pwd = pwd;
    }

    @Override
    public LoginClass loadInBackground() {
        return MobileApi.login(_user_name, _user_pwd);
    }
}
