package com.rarnu.hunter.fragment;

import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.devlib.component.PullDownLayout;
import com.rarnu.devlib.component.PullDownScrollView;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.DataClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.LocalDir;
import com.rarnu.hunter.loader.DataLoader;
import com.rarnu.utils.DownloadUtils;
import com.rarnu.utils.ResourceUtils;
import com.rarnu.utils.UIUtils;

public class ContactFragment extends BaseFragment implements Loader.OnLoadCompleteListener<DataClass>, View.OnClickListener, PullDownLayout.RefreshListener {

    PullDownLayout pdl;
    PullDownScrollView svContact;
    TextView tvNoConnection;
    RelativeLayout pbLoading;

    TextView vMailWork;
    TextView vMailPrivate;
    TextView vQQ;
    TextView vHangouts;
    TextView vWX;
    TextView vPhoneWork;
    TextView vPhonePrivate;
    TextView vAddress;
    ImageView ivMap;

    DataLoader loader;

    public ContactFragment() {
        super();
        tabTitle = ResourceUtils.getString(R.string.title_contact);
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

        vMailWork = (TextView) innerView.findViewById(R.id.vMailWork);
        vMailPrivate = (TextView) innerView.findViewById(R.id.vMailPrivate);
        vQQ = (TextView) innerView.findViewById(R.id.vQQ);
        vHangouts = (TextView) innerView.findViewById(R.id.vHangouts);
        vWX = (TextView) innerView.findViewById(R.id.vWX);
        vPhoneWork = (TextView) innerView.findViewById(R.id.vPhoneWork);
        vPhonePrivate = (TextView) innerView.findViewById(R.id.vPhonePrivate);
        vAddress = (TextView) innerView.findViewById(R.id.vAddress);
        ivMap = (ImageView) innerView.findViewById(R.id.ivMap);

        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        pdl = (PullDownLayout) innerView.findViewById(R.id.pdl);
        svContact = (PullDownScrollView) innerView.findViewById(R.id.svContact);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        loader = new DataLoader(getActivity());
        pdl.sv = svContact;
    }

    @Override
    public void initEvents() {
        loader.registerListener(0, this);
        tvNoConnection.setOnClickListener(this);
        pdl.setRefreshListener(this);
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
        return R.layout.layout_contact;
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
    public void onLoadComplete(Loader<DataClass> loader, DataClass data) {
        if (getActivity() != null) {
            pdl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pdl.finishRefresh();
                }
            }, 500);

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
                loadMapT();
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

    private void loadMapT() {
        int width = UIUtils.getWidth() - UIUtils.dipToPx(24);
        int height = (int) (width * 1.0D * 311 / 497);
        RelativeLayout.LayoutParams rllp = (RelativeLayout.LayoutParams) ivMap.getLayoutParams();
        rllp.width = width;
        rllp.height = height;
        ivMap.setLayoutParams(rllp);
        DownloadUtils.downloadFileT(getActivity(), ivMap, MobileApi.MAP_URL, LocalDir.LOCALDIR, "map.png", null);

    }

    @Override
    public void onRefresh() {
        loader.startLoading();
    }
}
