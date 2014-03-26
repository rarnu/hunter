package com.rarnu.hunter.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.devlib.component.PullDownListView;
import com.rarnu.devlib.component.intf.OnPullDownListener;
import com.rarnu.hunter.R;
import com.rarnu.hunter.adapter.TimelineAdapter;
import com.rarnu.hunter.api.AnnoClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.TimelineClass;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.TimelineLoader;
import com.rarnu.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class ManageMyFragment extends BaseFragment implements OnPullDownListener, View.OnClickListener, Loader.OnLoadCompleteListener<List<TimelineClass>>, AdapterView.OnItemLongClickListener {

    PullDownListView lv;
    List<TimelineClass> list;
    TimelineAdapter adapter;
    TimelineLoader loader;
    RelativeLayout pbLoading;
    TextView tvNoConnection;
    int currentPage = 1;
    boolean isBottom = false;
    EditText vHeadVicky;
    Button btnSubmit;
    MenuItem miAdd;

    Handler hHead = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String headText = (String) msg.obj;
                if (getActivity() != null) {
                    vHeadVicky.setText(headText);
                }
            }
            super.handleMessage(msg);
        }
    };

    public ManageMyFragment() {
        super();
        tabTitle = ResourceUtils.getString(R.string.title_manage_my);
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
        vHeadVicky = (EditText) innerView.findViewById(R.id.vHeadVicky);
        btnSubmit = (Button) innerView.findViewById(R.id.btnSubmit);
        lv = (PullDownListView) innerView.findViewById(R.id.lv);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        list = new ArrayList<TimelineClass>();
        adapter = new TimelineAdapter(getActivity(), list);
        lv.getListView().setAdapter(adapter);
        lv.enableAutoFetchMore(true, 1);
        loader = new TimelineLoader(getActivity());
    }

    @Override
    public void initEvents() {
        lv.setOnPullDownListener(this);
        lv.getListView().setOnItemLongClickListener(this);
        tvNoConnection.setOnClickListener(this);
        loader.registerListener(0, this);
    }

    @Override
    public void initLogic() {
        setIsBottom(false);
        loader.setPage(currentPage);
        doLoading();
    }

    private void setIsBottom(boolean bottom) {
        isBottom = bottom;
        lv.showAutoFetchMore(!isBottom);
    }

    private void doLoading() {
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        lv.notifyDidLoad();
        loader.startLoading();
        doLoadHeadT();
    }

    private void doLoadHeadT() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AnnoClass ac = MobileApi.queryAnno();
                if (ac != null) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = ac.comment;
                    hHead.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_manage_history;
    }

    @Override
    public String getMainActivityName() {
        return "";
    }

    @Override
    public void initMenu(Menu menu) {
        miAdd = menu.add(0, Ids.MENU_ID_ADD_TIMELINE, 99, R.string.menu_add_timeline);
        miAdd.setIcon(android.R.drawable.ic_menu_add);
        miAdd.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_ADD_TIMELINE:
                // TODO: add timeline
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

    @Override
    public void onRefresh() {
        currentPage = 1;
        loader.setPage(currentPage);
        loader.startLoading();
        setIsBottom(false);
    }

    @Override
    public void onMore() {
        currentPage++;
        loader.setPage(currentPage);
        loader.startLoading();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNoConnection:
                doLoading();
                break;
        }
    }

    @Override
    public void onLoadComplete(Loader<List<TimelineClass>> loader, List<TimelineClass> data) {
        if (currentPage == 1) {
            list.clear();
        }
        if (data != null && data.size() != 0) {
            list.addAll(data);
        } else {
            if (currentPage != 1) {
                currentPage--;
                if (getActivity() != null) {
                    setIsBottom(true);
                }
            }
        }
        if (getActivity() != null) {
            adapter.setNewList(list);
            lv.notifyDidLoad();
            lv.notifyDidRefresh();
            lv.notifyDidMore();

            pbLoading.setVisibility(View.GONE);
            if (list.size() == 0) {
                tvNoConnection.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        doPopupEditMenu((TimelineClass) lv.getListView().getItemAtPosition(position));
        return true;
    }

    private void doPopupEditMenu(TimelineClass timeline) {
        String[] items = new String[]{getString(R.string.popup_job_edit), getString(R.string.popup_job_delete)};
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // TODO: edit timeline
                                break;
                            case 1:
                                // TODO: delete timeline
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
