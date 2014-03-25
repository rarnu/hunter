package com.rarnu.hunter.fragment;

import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.hunter.api.JobDetailClass;
import com.rarnu.hunter.loader.JobDetailLoader;

public class JobDetailFragment extends BaseFragment implements Loader.OnLoadCompleteListener<JobDetailClass>, View.OnClickListener {


    TextView vCompanyName;
    TextView vCompanyDesc;
    TextView vCompanyHeads;
    TextView vInHeads;
    TextView vWorkArea;
    TextView vWorkYears;
    TextView vEducation;
    TextView vSalaryRange;
    TextView vPublishDate;
    TextView vEndDate;
    TextView vJobTitle;
    TextView vJobAccountability;
    TextView vJobRequirement;

    JobDetailLoader loader;
    ScrollView svDetail;

    TextView tvNoConnection;
    RelativeLayout pbLoading;

    JobClass job = null;

    @Override
    public int getBarTitle() {
        return R.string.title_job_detail;
    }

    @Override
    public int getBarTitleWithPath() {
        return R.string.title_job_detail;
    }

    @Override
    public String getCustomTitle() {
        return null;
    }

    @Override
    public void initComponents() {
        vCompanyName = (TextView) innerView.findViewById(R.id.vCompanyName);
        vCompanyDesc = (TextView) innerView.findViewById(R.id.vCompanyDesc);
        vCompanyHeads = (TextView) innerView.findViewById(R.id.vCompanyHeads);
        vInHeads = (TextView) innerView.findViewById(R.id.vInHeads);
        vWorkArea = (TextView) innerView.findViewById(R.id.vWorkArea);
        vWorkYears = (TextView) innerView.findViewById(R.id.vWorkYears);
        vEducation = (TextView) innerView.findViewById(R.id.vEducation);
        vSalaryRange = (TextView) innerView.findViewById(R.id.vSalaryRange);
        vPublishDate = (TextView) innerView.findViewById(R.id.vPublishDate);
        vEndDate = (TextView) innerView.findViewById(R.id.vEndDate);
        vJobTitle = (TextView) innerView.findViewById(R.id.vJobTitle);
        vJobAccountability = (TextView) innerView.findViewById(R.id.vJobAccountability);
        vJobRequirement = (TextView) innerView.findViewById(R.id.vJobRequirement);

        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        svDetail = (ScrollView) innerView.findViewById(R.id.svDetail);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        loader = new JobDetailLoader(getActivity());

    }

    @Override
    public void initEvents() {
        loader.registerListener(0, this);
        tvNoConnection.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        job = (JobClass) getActivity().getIntent().getSerializableExtra("job");
        loader.setId(job.id);
        doLoading();
    }

    private void doLoading() {
        svDetail.setVisibility(View.GONE);
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        loader.startLoading();
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_job_detail;
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
    public void onLoadComplete(Loader<JobDetailClass> loader, JobDetailClass data) {
        if (getActivity() != null) {
            pbLoading.setVisibility(View.GONE);
            if (data != null) {
                if (data.companyDesc.equals("")) {
                    data.companyDesc = "暂无";
                }
                if (data.salaryRange.equals("")) {
                    data.salaryRange = "面议";
                }
                if (data.publishDate.equals("")) {
                    data.publishDate = "长期招聘";
                }
                if (data.endDate.equals("")) {
                    data.endDate = "长期招聘";
                }
                vCompanyName.setText(data.companyName);
                vCompanyDesc.setText(data.companyDesc);
                vCompanyHeads.setText(companyHeadsToString(data.companyHeads));
                vInHeads.setText(String.valueOf(data.inHeads));
                vJobTitle.setText(data.jobTitle);
                vJobAccountability.setText(data.jobAccoutability);
                vJobRequirement.setText(data.jobRequirement);
                vWorkArea.setText(data.workArea);
                vSalaryRange.setText(data.salaryRange);
                vWorkYears.setText(workYearsToString(data.workYears));
                vEducation.setText(educationToString(data.education));
                vPublishDate.setText(data.publishDate);
                vEndDate.setText(data.endDate);
                svDetail.setVisibility(View.VISIBLE);
            } else {
                tvNoConnection.setVisibility(View.VISIBLE);
                svDetail.setVisibility(View.GONE);

            }
        }
    }


    private String companyHeadsToString(int heads) {
        String chstr = "保密";
        switch (heads) {
            case 1:
                chstr = "50人以下";
                break;
            case 2:
                chstr = "50~100人";
                break;
            case 3:
                chstr = "100~500人";
                break;
            case 4:
                chstr = "500~1000人";
                break;
            case 5:
                chstr = "1000~5000人";
                break;
            case 6:
                chstr = "5000人以上";
                break;

        }
        return chstr;
    }

    private String workYearsToString(int workYears) {
        String wystr = "不限";
        switch (workYears) {
            case 1:
                wystr = "一年以上";
                break;
            case 2:
                wystr = "二年以上";
                break;
            case 3:
                wystr = "三年以上";
                break;
            case 4:
                wystr = "五年以上";
                break;
            case 5:
                wystr = "八年以上";
                break;
            case 6:
                wystr = "十年以上";
                break;
        }

        return wystr;
    }

    public String educationToString(int education) {
        String edustr = "不限";
        switch (education) {
            case 1:
                edustr = "大专以上";
                break;
            case 2:
                edustr = "本科以上";
                break;
            case 3:
                edustr = "硕士以上";
                break;
            case 4:
                edustr = "海外留学";
                break;
        }
        return edustr;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNoConnection:
                doLoading();
                break;
        }
    }
}
