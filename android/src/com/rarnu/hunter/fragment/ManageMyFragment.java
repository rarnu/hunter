package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.rarnu.hunter.InputDialog;
import com.rarnu.hunter.ManageAddOrEditTimelineActivity;
import com.rarnu.hunter.R;
import com.rarnu.hunter.adapter.TimelineAdapter;
import com.rarnu.hunter.api.AnnoClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.ResultClass;
import com.rarnu.hunter.api.TimelineClass;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.TimelineLoader;
import com.rarnu.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class ManageMyFragment extends BaseFragment implements OnPullDownListener, View.OnClickListener, Loader.OnLoadCompleteListener<List<TimelineClass>>, AdapterView.OnItemClickListener {

    PullDownListView lv;
    List<TimelineClass> list;
    TimelineAdapter adapter;
    TimelineLoader loader;
    RelativeLayout pbLoading;
    TextView tvNoConnection;
    int currentPage = 1;
    boolean isBottom = false;
    String vHeadVicky = "";
    Button btnEdit;
    MenuItem miAdd;
    RelativeLayout pbDeleting;

    private Handler hHead = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 && getActivity() != null) {
                Toast.makeText(getActivity(), msg.arg1 == 0 ? R.string.data_succ : R.string.data_fail, Toast.LENGTH_SHORT).show();
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
        btnEdit = (Button) innerView.findViewById(R.id.btnEdit);
        lv = (PullDownListView) innerView.findViewById(R.id.lv);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        list = new ArrayList<TimelineClass>();
        adapter = new TimelineAdapter(getActivity(), list);
        lv.getListView().setAdapter(adapter);
        lv.enableAutoFetchMore(true, 1);
        loader = new TimelineLoader(getActivity());
        pbDeleting = (RelativeLayout) innerView.findViewById(R.id.pbDeleting);
    }

    @Override
    public void initEvents() {
        btnEdit.setOnClickListener(this);
        lv.setOnPullDownListener(this);
        lv.getListView().setOnItemClickListener(this);
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
        vHeadVicky = "";
        new Thread(new Runnable() {
            @Override
            public void run() {
                AnnoClass ac = MobileApi.queryAnno();
                if (ac != null) {
                    vHeadVicky = ac.comment;
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

                Intent inAT = new Intent(getActivity(), ManageAddOrEditTimelineActivity.class);
                inAT.putExtra("mode", Ids.REQUEST_ADD_TIMELINE);
                startActivityForResult(inAT, Ids.REQUEST_ADD_TIMELINE);
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
            case R.id.btnEdit:
                if (vHeadVicky.equals("")) {
                    return;
                }
                Intent inInput = new Intent(getActivity(), InputDialog.class);
                inInput.putExtra("text", vHeadVicky);
                startActivityForResult(inInput, Ids.REQUEST_INPUT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Ids.REQUEST_INPUT:
                doEditAnnoT(data.getStringExtra("text"));
                break;
        }
    }


    private void doEditAnnoT(final String anno) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultClass rc = MobileApi.editAnno(anno);
                if (rc != null && rc.result == 0) {
                    vHeadVicky = anno;
                }
                Message msg = new Message();
                msg.what = 1;
                if (rc != null) {
                    msg.arg1 = rc.result;
                } else {
                    msg.arg1 = 1;
                }
                hHead.sendMessage(msg);
            }
        }).start();

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

    private void doPopupEditMenu(final TimelineClass timeline, final int position) {
        String[] items = new String[]{getString(R.string.popup_job_edit), getString(R.string.popup_job_delete)};
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent inET = new Intent(getActivity(), ManageAddOrEditTimelineActivity.class);
                                inET.putExtra("mode", Ids.REQUEST_EDIT_TIMELINE);
                                inET.putExtra("item", timeline);
                                startActivityForResult(inET, Ids.REQUEST_EDIT_TIMELINE);
                                break;
                            case 1:
                                doDeleteConfirm(timeline.id, position);
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        doPopupEditMenu((TimelineClass) lv.getListView().getItemAtPosition(position), position);
    }

    private void doDeleteConfirm(final int id, final int position) {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setMessage(R.string.msg_delete_timeline)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDeleteT(id, position);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private Handler hDelete = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 && getActivity() != null) {
                pbDeleting.setVisibility(View.GONE);
                if (msg.arg1 == 0) {
                    list.remove(msg.arg2);
                    adapter.setNewList(list);
                } else {
                    Toast.makeText(getActivity(), R.string.delete_fail, Toast.LENGTH_SHORT).show();
                }
            }
            super.handleMessage(msg);
        }
    };

    private void doDeleteT(final int id, final int position) {
        pbDeleting.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultClass rc = MobileApi.deleteTimeline(id);
                Message msg = new Message();
                msg.what = 1;
                if (rc != null) {
                    msg.arg1 = rc.result;
                } else {
                    msg.arg1 = 1;
                }
                msg.arg2 = position;
                hDelete.sendMessage(msg);
            }
        }).start();
    }
}
