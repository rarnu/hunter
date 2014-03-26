package com.rarnu.hunter.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.devlib.component.PullDownListView;
import com.rarnu.devlib.component.intf.OnPullDownListener;
import com.rarnu.hunter.R;
import com.rarnu.hunter.adapter.JobAdapter;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.JobLoader;
import com.rarnu.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class ManageJobFragment extends BaseFragment implements OnPullDownListener, View.OnClickListener, Loader.OnLoadCompleteListener<List<JobClass>>, AdapterView.OnItemLongClickListener {

    MenuItem miAdd;
    PullDownListView lv;
    List<JobClass> list;
    JobAdapter adapter;
    JobLoader loader;
    RelativeLayout pbLoading;
    TextView tvNoConnection;
    int currentPage = 1;
    boolean isBottom = false;

    public ManageJobFragment() {
        super();
        tabTitle = ResourceUtils.getString(R.string.title_manage_job);
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
        lv = (PullDownListView) innerView.findViewById(R.id.lv);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        list = new ArrayList<JobClass>();
        adapter = new JobAdapter(getActivity(), list);
        lv.getListView().setAdapter(adapter);
        lv.enableAutoFetchMore(true, 1);
        loader = new JobLoader(getActivity());
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

    private void doLoading() {
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        lv.notifyDidLoad();
        loader.startLoading();
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_manage_job;
    }

    @Override
    public String getMainActivityName() {
        return "";
    }

    @Override
    public void initMenu(Menu menu) {
        miAdd = menu.add(0, Ids.MENU_ID_ADD_JOB, 99, R.string.menu_add_job);
        miAdd.setIcon(android.R.drawable.ic_menu_add);
        miAdd.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_ADD_JOB:
                // TODO: add job
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

    private void setIsBottom(boolean bottom) {
        isBottom = bottom;
        lv.showAutoFetchMore(!isBottom);
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
    public void onLoadComplete(Loader<List<JobClass>> loader, List<JobClass> data) {
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
        doPopupEditMenu((JobClass) lv.getListView().getItemAtPosition(position));
        return true;
    }

    private void doPopupEditMenu(JobClass job) {
        String[] items = new String[]{getString(R.string.popup_job_edit), getString(R.string.popup_job_prop), getString(R.string.popup_job_delete)};
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hint)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // TODO: edit job
                                break;
                            case 1:
                                // TODO: edit prop
                                break;
                            case 2:
                                // TODO: delete job
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
