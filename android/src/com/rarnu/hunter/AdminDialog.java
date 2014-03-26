package com.rarnu.hunter;

import android.app.Fragment;
import android.os.Bundle;
import com.rarnu.devlib.base.BaseDialog;
import com.rarnu.hunter.fragment.AdminFragment;

public class AdminDialog extends BaseDialog {

    AdminFragment af = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);
    }

    @Override
    public boolean getCondition() {
        return false;
    }

    @Override
    public Fragment replaceFragment() {
        if (af == null) {
            af = new AdminFragment();
        }
        return af;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
