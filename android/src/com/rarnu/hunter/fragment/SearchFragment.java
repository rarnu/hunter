package com.rarnu.hunter.fragment;

import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.devlib.component.PullDownListView;
import com.rarnu.devlib.component.intf.OnPullDownListener;
import com.rarnu.hunter.JobDetailActivity;
import com.rarnu.hunter.R;
import com.rarnu.hunter.adapter.JobAdapter;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.loader.SearchLoader;
import com.rarnu.utils.InputMethodUtils;
import com.rarnu.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements OnPullDownListener, Loader.OnLoadCompleteListener<List<JobClass>>, View.OnClickListener, AdapterView.OnItemClickListener {

    PullDownListView lv;
    List<JobClass> list;
    JobAdapter adapter;
    SearchLoader loader;
    RelativeLayout pbLoading;
    TextView tvNoConnection;
    int currentPage = 1;
    boolean isBottom = false;
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
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        list = new ArrayList<JobClass>();
        adapter = new JobAdapter(getActivity(), list);
        lv.getListView().setAdapter(adapter);
        lv.enableAutoFetchMore(true, 1);
        loader = new SearchLoader(getActivity());
    }

    @Override
    public void initEvents() {
        lv.setOnPullDownListener(this);
        lv.getListView().setOnItemClickListener(this);
        tvNoConnection.setOnClickListener(this);
        loader.registerListener(0, this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        setIsBottom(false);
        lv.notifyDidLoad();
    }

    private void doLoading() {
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        lv.notifyDidLoad();
        loader.startLoading();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNoConnection:
                doLoading();
                break;
            case R.id.btnSearch:
                InputMethodUtils.hideInputMethod(getActivity());
                String key = etKeyword.getText().toString();
                if (loader != null && !key.equals("")) {
                    currentPage = 1;
                    loader.setKeyword(key);
                    loader.setPage(currentPage);
                    lv.removeFooterView();
                    doLoading();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDetail = new Intent(getActivity(), JobDetailActivity.class);
        inDetail.putExtra("job", (JobClass) lv.getListView().getItemAtPosition(position));
        startActivity(inDetail);
    }
}
