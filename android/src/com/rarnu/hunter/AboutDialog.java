package com.rarnu.hunter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.WindowManager;
import com.rarnu.devlib.base.BaseDialog;
import com.rarnu.hunter.fragment.AboutFragment;
import com.rarnu.utils.UIUtils;

public class AboutDialog extends BaseDialog {

    AboutFragment af = null;

    @Override
    public boolean getCondition() {
        return false;
    }

    @Override
    public Fragment replaceFragment() {
        if (af == null) {
            af = new AboutFragment();
        }
        return af;
    }

    @Override
    public int customTheme() {
        return 0;
    }

}
