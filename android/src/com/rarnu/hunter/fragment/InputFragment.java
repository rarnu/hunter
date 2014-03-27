package com.rarnu.hunter.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.rarnu.devlib.base.BaseDialogFragment;
import com.rarnu.hunter.R;

public class InputFragment extends BaseDialogFragment implements View.OnClickListener {

    EditText etInput;
    ImageView btnSave;
    ImageView btnCancel;

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
        etInput = (EditText) innerView.findViewById(R.id.etInput);
        btnSave = (ImageView) innerView.findViewById(R.id.btnSave);
        btnCancel = (ImageView) innerView.findViewById(R.id.btnCancel);
    }

    @Override
    public void initEvents() {
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        etInput.setText(getActivity().getIntent().getStringExtra("text"));
    }

    @Override
    public int getFragmentLayoutResId() {
        return R.layout.dialog_input;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                returnText();
                break;
            case R.id.btnCancel:
                getActivity().finish();
                break;
        }
    }

    private void returnText() {
        Intent inRet = new Intent();
        inRet.putExtra("text", etInput.getText().toString());
        getActivity().setResult(Activity.RESULT_OK, inRet);
        getActivity().finish();
    }
}
