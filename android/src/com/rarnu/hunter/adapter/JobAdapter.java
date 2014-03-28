package com.rarnu.hunter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rarnu.devlib.base.adapter.BaseAdapter;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.JobClass;
import com.rarnu.utils.DrawableUtils;

import java.util.List;

public class JobAdapter extends BaseAdapter<JobClass> {

    public JobAdapter(Context context, List<JobClass> list) {
        super(context, list);
    }

    @Override
    public String getValueText(JobClass item) {
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
        JobClass item = list.get(position);
        if (item != null) {
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
            holder.tvArea.setText(context.getString(R.string.item_work_area_fmt, item.workArea));
            holder.tvCompany.setText(context.getString(R.string.item_company_fmt, item.companyName));
        }
        return v;
    }
}
