package com.rarnu.hunter;

import android.app.Fragment;
import com.rarnu.devlib.base.BaseDialog;
import com.rarnu.hunter.fragment.InputFragment;

public class InputDialog extends BaseDialog {

    InputFragment inputF = null;

    @Override
    public boolean getCondition() {
        return false;
    }

    @Override
    public Fragment replaceFragment() {
        if (inputF == null) {
            inputF = new InputFragment();
        }
        return inputF;
    }

    @Override
    public int customTheme() {
        return 0;
    }
}
