package com.rarnu.hunter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rarnu.devlib.base.adapter.BaseAdapter;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.JobManageClass;
import com.rarnu.utils.DrawableUtils;

import java.util.List;

public class JobManageAdapter extends BaseAdapter<JobManageClass> {

    public JobManageAdapter(Context context, List<JobManageClass> list) {
        super(context, list);
    }

    @Override
    public String getValueText(JobManageClass item) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.item_job, parent, false);
        }
        JobHolder holder = (JobHolder) v.getTag();
        if (holder == null) {
            holder = new JobHolder();
            holder.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            holder.tvArea = (TextView) v.findViewById(R.id.tvArea);
            holder.tvCompany = (TextView) v.findViewById(R.id.tvCompany);
            v.setTag(holder);
        }
        JobManageClass item = list.get(position);
        if (item != null) {

            if (item.status == 0) {
                holder.tvTitle.setText(item.jobTitle);
                holder.tvTitle.setTextColor(DrawableUtils.getTextColorPrimary(context));
                switch (item.color) {
                    case 1:
                        holder.tvTitle.setTextColor(Color.RED);
                        break;
                    case 2:
                        holder.tvTitle.setTextColor(Color.BLUE);
                        break;
                    case 3:
                        holder.tvTitle.setTextColor(Color.GREEN);
                        break;
                }
            } else {
                holder.tvTitle.setTextColor(Color.GRAY);
                SpannableString sp = new SpannableString(item.jobTitle);
                sp.setSpan(new StrikethroughSpan(), 0, item.jobTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.tvTitle.setText(sp);
            }
            holder.tvArea.setText(context.getString(R.string.item_work_area_fmt, item.workArea));
            holder.tvCompany.setText(context.getString(R.string.item_company_fmt, item.companyName));
        }
        return v;
    }
}
