package com.rarnu.hunter.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rarnu.devlib.base.BaseDialogFragment;
import com.rarnu.hunter.R;
import com.rarnu.utils.DeviceUtilsLite;

public class AboutFragment extends BaseDialogFragment implements ViewTreeObserver.OnGlobalLayoutListener {

    RelativeLayout layAbout;
    TextView tvAppVersion;

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
        layAbout = (RelativeLayout) innerView.findViewById(R.id.layAbout);
        tvAppVersion = (TextView) innerView.findViewById(R.id.tvAppVersion);
    }

    @Override
    public void initEvents() {
        layAbout.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void initLogic() {
        tvAppVersion.setText(DeviceUtilsLite.getAppVersionName(getActivity()));
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.dialog_about;
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
    public void onGlobalLayout() {
        int width = layAbout.getWidth();
        int height = (int) (width * 1.0D * 278 / 400);
        ViewGroup.LayoutParams vglp = layAbout.getLayoutParams();
        vglp.height = height;
        layAbout.setLayoutParams(vglp);
    }
}
