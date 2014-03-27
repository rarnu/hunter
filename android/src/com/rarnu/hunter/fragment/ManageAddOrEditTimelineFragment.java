package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.rarnu.devlib.base.BaseFragment;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.MobileApi;
import com.rarnu.hunter.api.ResultClass;
import com.rarnu.hunter.api.TimelineClass;
import com.rarnu.hunter.common.Ids;

import java.util.Calendar;

public class ManageAddOrEditTimelineFragment extends BaseFragment implements View.OnClickListener {

    Button vPublishDate;
    EditText vComment;
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
        vPublishDate = (Button) innerView.findViewById(R.id.vPublishDate);
        vComment = (EditText) innerView.findViewById(R.id.vComment);
        pbSaving = (RelativeLayout) innerView.findViewById(R.id.pbSaving);
    }

    @Override
    public void initEvents() {
        vPublishDate.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        int mode = getActivity().getIntent().getIntExtra("mode", -1);
        if (mode == Ids.REQUEST_EDIT_TIMELINE) {
            getActivity().getActionBar().setTitle(R.string.title_edit_timeline);
            TimelineClass tc = (TimelineClass) getActivity().getIntent().getSerializableExtra("item");
            vPublishDate.setText(tc.publishDate);
            vComment.setText(tc.comment);
        } else {
            getActivity().getActionBar().setTitle(R.string.title_add_timeline);
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            String strDate = String.format("%d-%d-%d", year, month + 1, day);
            vPublishDate.setText(strDate);
        }
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.layout_manage_publish_timeline;
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
                saveTimeline();
                break;
        }
        return true;
    }

    private void saveTimeline() {
        TimelineClass tc = new TimelineClass();
        tc.publishDate = vPublishDate.getText().toString();
        tc.comment = vComment.getText().toString();

        if (tc.publishDate.equals("") || tc.comment.equals("")) {
            Toast.makeText(getActivity(), R.string.msg_timeline_data_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        int mode = getActivity().getIntent().getIntExtra("mode", -1);
        if (mode == Ids.REQUEST_ADD_TIMELINE) {
            doSaveTimelineT(tc, Ids.REQUEST_ADD_TIMELINE);
        } else {
            tc.id = ((TimelineClass) getActivity().getIntent().getSerializableExtra("item")).id;
            doSaveTimelineT(tc, Ids.REQUEST_EDIT_TIMELINE);
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

    private void doSaveTimelineT(final TimelineClass tc, final int mode) {
        miSave.setEnabled(false);
        pbSaving.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ResultClass rc = null;
                switch (mode) {
                    case Ids.REQUEST_ADD_TIMELINE:
                        rc = MobileApi.publishTimeline(tc);
                        break;
                    case Ids.REQUEST_EDIT_TIMELINE:
                        rc = MobileApi.editTimeline(tc);
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
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.vPublishDate:
                selectDate();
                break;
        }
    }

    private void selectDate() {

        String[] arrDate = vPublishDate.getText().toString().split("-");

        int year = Integer.parseInt(arrDate[0]);
        int month = Integer.parseInt(arrDate[1]) - 1;
        int day = Integer.parseInt(arrDate[2]);

        final DatePickerDialog dialogDate = new DatePickerDialog(getActivity(), null, year, month, day);
        dialogDate.setButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatePicker dp = dialogDate.getDatePicker();
                int year = dp.getYear();
                int month = dp.getMonth();
                int day = dp.getDayOfMonth();
                String strDate = String.format("%d-%d-%d", year, month + 1, day);
                vPublishDate.setText(strDate);
            }
        });
        dialogDate.setButton2(getString(R.string.cancel), (DialogInterface.OnClickListener) null);
        dialogDate.show();

    }
}
