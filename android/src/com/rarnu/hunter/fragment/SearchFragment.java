package com.rarnu.hunter.fragment;

import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.devlib.component.PullDownListView;
import com.rarnu.devlib.component.intf.OnPullDownListener;
import com.rarnu.hunter.R;
import com.rarnu.hunter.adapter.JobAdapter;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.loader.SearchLoader;
import com.rarnu.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements OnPullDownListener, Loader.OnLoadCompleteListener<List<JobClass>>, View.OnClickListener {

    PullDownListView lv;
    List<JobClass> list;
    JobAdapter adapter;
    SearchLoader loader;
    EditText etKeyword;
    ImageView btnSearch;

    public SearchFragment() {
        super();
        tabTitle = ResourceUtils.getString(R.string.title_search);
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
        etKeyword = (EditText) innerView.findViewById(R.id.etKeyword);
        btnSearch = (ImageView) innerView.findViewById(R.id.btnSearch);
        list = new ArrayList<JobClass>();
        adapter = new JobAdapter(getActivity(), list);
        lv.getListView().setAdapter(adapter);
        loader = new SearchLoader(getActivity());
        lv.enableAutoFetchMore(true, 1);
    }

    @Override
    public void initEvents() {
        lv.setOnPullDownListener(this);
        loader.registerListener(0, this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        lv.notifyDidLoad();
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_search;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                String key = etKeyword.getText().toString();
                if (loader != null && !key.equals("")) {
                    loader.setKeyword(key);
                    loader.setPage(1);
                    loader.startLoading();
                }
                break;
        }
    }
}
