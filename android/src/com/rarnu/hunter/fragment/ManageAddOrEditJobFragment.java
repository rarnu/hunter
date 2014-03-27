package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import com.rarnu.hunter.InputDialog;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.JobDetailClass;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.ResultClass;
import com.rarnu.hunter.common.Ids;
import com.rarnu.hunter.loader.JobDetailLoader;

import java.util.Calendar;

public class ManageAddOrEditJobFragment extends BaseFragment implements Loader.OnLoadCompleteListener<JobDetailClass>, View.OnClickListener {


    EditText vCompanyName;
    Button vCompanyDesc;
    String strCompanyDesc;
    Spinner vCompanyHeads;
    EditText vInHeads;
    EditText vWorkArea;
    Spinner vWorkYears;
    Spinner vEducation;
    EditText vSalaryRange;
    Button vPublishDate;
    Button vEndDate;
    EditText vJobTitle;
    Button vJobAccountability;
    String strJobAccountability;
    Button vJobRequirement;
    String strJobRequirement;

    ArrayAdapter<String> adapterCompanyHeads;
    ArrayAdapter<String> adapterWorkYears;
    ArrayAdapter<String> adapterEducation;

    JobDetailLoader loader;
    ScrollView svDetail;

    TextView tvNoConnection;
    RelativeLayout pbLoading;
    RelativeLayout pbSaving;

    MenuItem miSave;

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
        vCompanyName = (EditText) innerView.findViewById(R.id.vCompanyName);
        vCompanyDesc = (Button) innerView.findViewById(R.id.vCompanyDesc);
        vCompanyHeads = (Spinner) innerView.findViewById(R.id.vCompanyHeads);
        vInHeads = (EditText) innerView.findViewById(R.id.vInHeads);
        vWorkArea = (EditText) innerView.findViewById(R.id.vWorkArea);
        vWorkYears = (Spinner) innerView.findViewById(R.id.vWorkYears);
        vEducation = (Spinner) innerView.findViewById(R.id.vEducation);
        vSalaryRange = (EditText) innerView.findViewById(R.id.vSalaryRange);
        vPublishDate = (Button) innerView.findViewById(R.id.vPublishDate);
        vEndDate = (Button) innerView.findViewById(R.id.vEndDate);
        vJobTitle = (EditText) innerView.findViewById(R.id.vJobTitle);
        vJobAccountability = (Button) innerView.findViewById(R.id.vJobAccountability);
        vJobRequirement = (Button) innerView.findViewById(R.id.vJobRequirement);

        tvNoConnection = (TextView) innerView.findViewById(R.id.tvNoConnection);
        svDetail = (ScrollView) innerView.findViewById(R.id.svDetail);
        pbLoading = (RelativeLayout) innerView.findViewById(R.id.pbLoading);
        pbSaving = (RelativeLayout) innerView.findViewById(R.id.pbSaving);
        loader = new JobDetailLoader(getActivity());

        vPublishDate.setText("长期招聘");
        vEndDate.setText("长期招聘");

        initSpinner();
    }

    private void initSpinner() {
        String[] strCompanyHeads = new String[]{"保密", "50人以下", "50~100人", "100~500人", "500~1000人", "1000~5000人", "5000人以上"};
        adapterCompanyHeads = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, strCompanyHeads);
        vCompanyHeads.setAdapter(adapterCompanyHeads);
        vCompanyHeads.setSelection(0);

        String[] strWorkYears = new String[]{"不限", "一年以上", "二年以上", "三年以上", "五年以上", "八年以上", "十年以上"};
        adapterWorkYears = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, strWorkYears);
        vWorkYears.setAdapter(adapterWorkYears);
        vWorkYears.setSelection(0);

        String[] strEducation = new String[]{"不限", "大专以上", "本科以上", "硕士以上", "海外留学"};
        adapterEducation = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, strEducation);
        vEducation.setAdapter(adapterEducation);
        vEducation.setSelection(0);
    }

    @Override
    public void initEvents() {
        loader.registerListener(0, this);
        tvNoConnection.setOnClickListener(this);
        vCompanyDesc.setOnClickListener(this);
        vJobAccountability.setOnClickListener(this);
        vJobRequirement.setOnClickListener(this);
        vPublishDate.setOnClickListener(this);
        vEndDate.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        int mode = getActivity().getIntent().getIntExtra("mode", -1);
        if (mode == Ids.REQUEST_EDIT_JOB) {
            getActivity().getActionBar().setTitle(R.string.title_edit_job);
            loader.setId(getActivity().getIntent().getIntExtra("id", -1));
            doLoading();
        } else {
            getActivity().getActionBar().setTitle(R.string.title_add_job);
        }
    }

    private void doLoading() {
        svDetail.setVisibility(View.GONE);
        tvNoConnection.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        loader.startLoading();
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_manage_publish_job;
    }

    @Override
    public String getMainActivityName() {
        return "";
    }

    @Override
    public void initMenu(Menu menu) {
        miSave = menu.add(0, Ids.MENU_ID_SAVE, 99, R.string.submit);
        miSave.setIcon(android.R.drawable.ic_menu_save);
        miSave.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Ids.MENU_ID_SAVE:
                saveJob();
                break;
        }
        return true;
    }

    private void saveJob() {
        JobDetailClass jdc = new JobDetailClass();
        jdc.companyName = vCompanyName.getText().toString();
        jdc.companyDesc = strCompanyDesc;
        jdc.companyHeads = vCompanyHeads.getSelectedItemPosition();
        jdc.workArea = vWorkArea.getText().toString();
        jdc.workYears = vWorkYears.getSelectedItemPosition();
        jdc.education = vEducation.getSelectedItemPosition();
        try {
            jdc.inHeads = Integer.parseInt(vInHeads.getText().toString());
        } catch (Exception e) {
            jdc.inHeads = 0;
        }
        jdc.publishDate = vPublishDate.getText().toString();
        jdc.endDate = vEndDate.getText().toString();
        jdc.salaryRange = vSalaryRange.getText().toString();
        jdc.jobTitle = vJobTitle.getText().toString();
        jdc.jobAccoutability = strJobAccountability;
        jdc.jobRequirement = strJobRequirement;

        if (jdc.publishDate.equals("长期招聘")) {
            jdc.publishDate = "";
        }
        if (jdc.endDate.equals("长期招聘")) {
            jdc.endDate = "";
        }

        if (jdc.companyName.equals("") || jdc.inHeads == 0 || jdc.workArea.equals("") || jdc.jobTitle.equals("")) {
            Toast.makeText(getActivity(), R.string.msg_job_data_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        int mode = getActivity().getIntent().getIntExtra("mode", -1);
        if (mode == Ids.REQUEST_ADD_JOB) {
            doSaveJobT(jdc, Ids.REQUEST_ADD_JOB);
        } else {
            jdc.id = getActivity().getIntent().getIntExtra("id", -1);
            doSaveJobT(jdc, Ids.REQUEST_EDIT_JOB);
        }
    }

    private Handler hSave = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 && getActivity() != null) {
                miSave.setEnabled(true);
                pbSaving.setVisibility(View.GONE);
                if (msg.arg1 == 0) {
                    Toast.makeText(getActivity(), R.string.data_succ, Toast.LENGTH_SHORT).show();
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), R.string.data_fail, Toast.LENGTH_SHORT).show();
                }
            }
            super.handleMessage(msg);
        }
    };

    private void doSaveJobT(final JobDetailClass jdc, final int mode) {
        miSave.setEnabled(false);
        pbSaving.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultClass rc = null;
                switch (mode) {
                    case Ids.REQUEST_ADD_JOB:
                        rc = MobileApi.publishJob(jdc);
                        break;
                    case Ids.REQUEST_EDIT_JOB:
                        rc = MobileApi.editJob(jdc);
                        break;
                }
                Message msg = new Message();
                msg.what = 1;
                if (rc != null) {
                    msg.arg1 = rc.result;
                } else {
                    msg.arg1 = 1;
                }
                hSave.sendMessage(msg);
            }
        }).start();
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
                strCompanyDesc = data.companyDesc;
                vCompanyHeads.setSelection(data.companyHeads);
                vInHeads.setText(String.valueOf(data.inHeads));
                vJobTitle.setText(data.jobTitle);
                strJobAccountability = data.jobAccoutability;
                strJobRequirement = data.jobRequirement;
                vWorkArea.setText(data.workArea);
                vSalaryRange.setText(data.salaryRange);
                vWorkYears.setSelection(data.workYears);
                vEducation.setSelection(data.education);
                vPublishDate.setText(data.publishDate);
                vEndDate.setText(data.endDate);
                svDetail.setVisibility(View.VISIBLE);
            } else {
                tvNoConnection.setVisibility(View.VISIBLE);
                svDetail.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNoConnection:
                doLoading();
                break;
            case R.id.vCompanyDesc:
                Intent inCD = new Intent(getActivity(), InputDialog.class);
                inCD.putExtra("text", strCompanyDesc);
                startActivityForResult(inCD, Ids.REQUEST_INPUT_COMPANY_DESC);
                break;
            case R.id.vJobAccountability:
                Intent inJA = new Intent(getActivity(), InputDialog.class);
                inJA.putExtra("text", strJobAccountability);
                startActivityForResult(inJA, Ids.REQUEST_INPUT_JOB_ACCOUNTABILITY);
                break;
            case R.id.vJobRequirement:
                Intent inJR = new Intent(getActivity(), InputDialog.class);
                inJR.putExtra("text", strJobRequirement);
                startActivityForResult(inJR, Ids.REQUEST_INPUT_JOB_REQUIREMENT);
                break;
            case R.id.vPublishDate:
                selectDate(0);
                break;
            case R.id.vEndDate:
                selectDate(1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Ids.REQUEST_INPUT_COMPANY_DESC:
                strCompanyDesc = data.getStringExtra("text");
                break;
            case Ids.REQUEST_INPUT_JOB_ACCOUNTABILITY:
                strJobAccountability = data.getStringExtra("text");
                break;
            case Ids.REQUEST_INPUT_JOB_REQUIREMENT:
                strJobRequirement = data.getStringExtra("text");
                break;
        }
    }

    private void selectDate(final int from) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String[] arrDate = null;

        if (from == 0) {
            String pdstr = vPublishDate.getText().toString();
            if (!pdstr.equals("长期招聘")) {
                arrDate = vPublishDate.getText().toString().split("-");
            }
        } else {
            String edstr = vEndDate.getText().toString();
            if (!edstr.equals("长期招聘")) {
                arrDate = vEndDate.getText().toString().split("-");
            }
        }
        if (arrDate != null && arrDate.length == 3) {
            year = Integer.parseInt(arrDate[0]);
            month = Integer.parseInt(arrDate[1]) - 1;
            day = Integer.parseInt(arrDate[2]);
        }
        final DatePickerDialog dialogDate = new DatePickerDialog(getActivity(), null, year, month, day);
        dialogDate.setButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatePicker dp = dialogDate.getDatePicker();
                int year = dp.getYear();
                int month = dp.getMonth();
                int day = dp.getDayOfMonth();
                String strDate = String.format("%d-%d-%d", year, month + 1, day);
                if (from == 0) {
                    vPublishDate.setText(strDate);
                } else {
                    vEndDate.setText(strDate);
                }
            }
        });
        dialogDate.setButton2(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (from == 0) {
                    vPublishDate.setText("长期招聘");
                } else {
                    vEndDate.setText("长期招聘");
                }
            }
        });
        dialogDate.show();

    }
}
