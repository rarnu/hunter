package com.rarnu.hunter.fragment;

import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.devlib.component.PullDownListView;
import com.rarnu.devlib.component.intf.OnPullDownListener;
import com.rarnu.hunter.R;
import com.rarnu.hunter.adapter.JobAdapter;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.loader.TopViewLoader;
import com.rarnu.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class TopViewFragment extends BaseFragment implements OnPullDownListener, Loader.OnLoadCompleteListener<List<JobClass>> {

    PullDownListView lv;
    List<JobClass> list;
    JobAdapter adapter;
    TopViewLoader loader;

    public TopViewFragment() {
        super();
        tabTitle = ResourceUtils.getString(R.string.title_topview);
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
        list = new ArrayList<JobClass>();
        adapter = new JobAdapter(getActivity(), list);
        lv.getListView().setAdapter(adapter);
        loader = new TopViewLoader(getActivity());
        lv.enableAutoFetchMore(true, 1);
    }

    @Override
    public void initEvents() {
        lv.setOnPullDownListener(this);
        loader.registerListener(0, this);
    }

    @Override
    public void initLogic() {
        loader.startLoading();
        lv.notifyDidLoad();
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_topview;
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
    public void onRefresh() {
        lv.notifyDidRefresh();
    }

    @Override
    public void onMore() {
        lv.notifyDidMore();
    }

    @Override
    public void onLoadComplete(Loader<List<JobClass>> loader, List<JobClass> data) {
        list.clear();
        if (data != null) {
            list.addAll(data);
        }
        if (getActivity() != null) {
            adapter.setNewList(list);
        }
    }
}
