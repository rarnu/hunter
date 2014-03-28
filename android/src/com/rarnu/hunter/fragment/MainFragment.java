package com.rarnu.hunter.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import com.rarnu.devlib.base.BaseTabFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.SettingsActivity;
import com.rarnu.hunter.api.FeedbackClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.utils.AccountUtils;
import com.rarnu.utils.DeviceUtilsLite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainFragment extends BaseTabFragment {

    MenuItem miSettings;
    MenuItem miShare;

    JobsFragment jf;
    SearchFragment sf;
    TopViewFragment tf;
    HistoryFragment hf;
    ContactFragment cf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jf = new JobsFragment();
        sf = new SearchFragment();
        tf = new TopViewFragment();
        hf = new HistoryFragment();
        cf = new ContactFragment();
    }

    @Override
    public void initFragmentList(List<Fragment> listFragment) {
        listFragment.add(jf);
        listFragment.add(sf);
        listFragment.add(tf);
        listFragment.add(hf);
        listFragment.add(cf);
    }

    @Override
    public int getBarTitle() {
        return R.string.app_name;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.app_name;
    }

    @Override
    public String getCustomTitle() {
        return null;
    }

    @Override
    public String getMainActivityName() {
        return "";
    }

    @Override
    public void initLogic() {
        super.initLogic();
        sendDefaultFeedback();
    }

    private void sendDefaultFeedback() {

        final FeedbackClass fc = new FeedbackClass();
        fc.type = 0;
        fc.device = Build.MODEL;
        fc.os = Build.VERSION.RELEASE;
        fc.app = DeviceUtilsLite.getAppVersionName(getActivity());
        fc.accounts = AccountUtils.getBindedAccountsAsString(getActivity());
        fc.publishDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        fc.comment = "Started";

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

    }

    @Override
    public void initMenu(Menu menu) {
        miSettings = menu.add(0, Ids.MENU_ID_SETTINGS, 99, R.string.menu_settings);
        miSettings.setIcon(R.drawable.ic_menu_preferences);
        miSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        ShareActionProvider provider = new ShareActionProvider(getActivity());
        provider.setShareIntent(getShareIntent());
        miShare = menu.add(0, Ids.MENU_ID_SHARE, 98, R.string.menu_share);
        miShare.setIcon(R.drawable.ic_menu_share);
        miShare.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        miShare.setActionProvider(provider);
    }

    private Intent getShareIntent() {
        Intent inShare = new Intent(Intent.ACTION_SEND);
        inShare.setType("image/*");
        inShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_title));
        inShare.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_body));
        return inShare;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_SETTINGS:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onGetNewArguments(Bundle bn) {

    }

    @Override
    public Bundle getFragmentState() {
        return null;
    }
}
